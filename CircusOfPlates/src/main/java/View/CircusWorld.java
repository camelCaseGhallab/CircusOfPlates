package View;

import Controller.Caretaker;
import Controller.CheckPoint;
import Controller.MovableObjectsFactory;
import Controller.LevelContext;
import Model.ClownObject;
import Model.ImageObject;
import eg.edu.alexu.csd.oop.game.GameObject;
import eg.edu.alexu.csd.oop.game.World;
import java.awt.Color;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class CircusWorld implements World{

    private static int MAX_TIME;
    private static int DEFAULT_SPEED;
    private static int DEFAULT_CONTROL_SPEED;
    private static int NUM_OF_MOVING_OBJS;
    
    private long startTime;
    private int score;
    private int highscore;
    private int lives;
    
    private final int width;
    private final int height;
    
    private final List<GameObject> constant;
    private final List<GameObject> moving;
    private final List<GameObject> control;
    
    private Stack<GameObject> rightStack;
    private Stack<GameObject> leftStack;
    private final Caretaker ct = new Caretaker();
    
    public CircusWorld(int width, int height, Controller.Level l){
        this.width = width;
        this.height = height;
        setLevel(new LevelContext(l));
        this.startTime = System.currentTimeMillis();
        this.score=0;
        this.lives=5;
        this.constant = new LinkedList<>();
        this.moving = new LinkedList<>();
        this.control = new LinkedList<>();
        this.rightStack = new Stack<>();
        this.leftStack = new Stack<>();
        ct.saveleft(new CheckPoint((Stack) leftStack.clone()));
        ct.saveright(new CheckPoint((Stack) rightStack.clone()));
        createGameObject();
    }
    
    private void createGameObject(){
        constant.add(new ImageObject(0, 0, "background.png"));
        for(int i = 0;i<5;i++)
            constant.add(new ImageObject(60*i, 0, "heart.png"));
        spawn();
        control.add(new ClownObject(width/3, (int)(height*0.515),"clown.png"));
    }
    
    private void setLevel(LevelContext con){
        DEFAULT_SPEED = con.setSpeed();
        MAX_TIME = con.setTime();
        NUM_OF_MOVING_OBJS = con.setNoofFallingShapes();
        DEFAULT_CONTROL_SPEED = con.setControlSpeed();
    }
    
    @Override
    public List<GameObject> getConstantObjects() {
        return constant;
    }

    @Override
    public List<GameObject> getMovableObjects() {
        return moving;
    }

    @Override
    public List<GameObject> getControlableObjects() {
        return control;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    private boolean intersect(GameObject obj1, GameObject obj2){
        return (Math.abs((obj1.getX()+obj1.getWidth()/2) - (obj2.getX()+obj2.getWidth()/2)) <= obj1.getWidth())
                && (Math.abs((obj1.getY()+obj1.getHeight()/2) - (obj2.getY()+obj2.getHeight()/2)) <= obj1.getHeight());
    }
    
    @Override
    public boolean refresh() {
        boolean timeout = System.currentTimeMillis() - startTime > MAX_TIME;
        ImageObject clown = (ImageObject) control.get(0);
        ImageObject plateleft = new ImageObject(clown.getX()+10, (int)(clown.getY()-leftStack.size()*20+60), "plate.png");
        ImageObject plateright = new ImageObject(clown.getX()+207, (int)(clown.getY()-rightStack.size()*20+60), "plate.png");
        if(plateleft.getY()<100)
        {
            for(int j=0;j<leftStack.size();j++){
                        constant.remove(leftStack.get(j));
                    }
                    leftStack.clear();
                    ct.saveleft(new CheckPoint((Stack) leftStack.clone()));
        }
        else if(plateright.getY()<100)
        {
            for(int j=0;j<rightStack.size();j++){
                        constant.remove(rightStack.get(j));
                    }
                    rightStack.clear();
                    ct.saveright(new CheckPoint((Stack) rightStack.clone()));
        }
        
        for(int i=0;i<leftStack.size();i++){
            leftStack.get(i).setX(clown.getX()+1);
            leftStack.get(i).setY(clown.getY()-i*20+35);
        }
        for(int i=0;i<rightStack.size();i++){
            rightStack.get(i).setX(clown.getX()+197);
            rightStack.get(i).setY(clown.getY()-i*20+35);
        }
        for(Iterator it = moving.iterator(); it.hasNext();){
            GameObject o = (GameObject)it.next();
            int type = ((ImageObject)o).getType();
            o.setY((o.getY() + DEFAULT_SPEED));
                if(o.getY()>=getHeight()){
                    o.setY(-1 * (int)(Math.random() * getHeight()));
                    o.setX((int)(Math.random() * getWidth()));	
		}
            if(type == 0){
                if(intersect(o,plateleft) ){
                    leftStack.add(o);
                    ct.saveleft(new CheckPoint((Stack) leftStack.clone()));
                    constant.add(o);
                    it.remove();
                    addScore();
                } else if (intersect(o,plateright)){
                    rightStack.add(o);
                    ct.saveright(new CheckPoint((Stack) rightStack.clone()));
                    constant.add(o);
                    it.remove();
                    addScore();
                }
            }else if (type == 1){
                if(intersect(o,plateleft) || intersect(o,plateright)){
                    removeLife();
                    it.remove();
                }
            }else if (type == 2){
                if(intersect(o,plateleft) || intersect(o,plateright)){
                    addLife();
                    it.remove();
                }
            }else if (type == 3){
                if(intersect(o,plateleft) ){
                    it.remove();
                    for(int j=0;j<leftStack.size();j++){
                        constant.remove(leftStack.get(j));
                    }
                    leftStack.clear();
                    ct.saveleft(new CheckPoint((Stack) leftStack.clone()));
                } else if (intersect(o,plateright)){
                    it.remove();
                    for(int j=0;j<rightStack.size();j++){
                        constant.remove(rightStack.get(j));
                    }
                    rightStack.clear();
                    ct.saveright(new CheckPoint((Stack) rightStack.clone()));
                }
            }else if (type==4){
                if(intersect(o,plateleft) || intersect(o,plateright)){
                    it.remove();
                    Color color=null;
                    int k=(int) (Math.random()*4+1);
                    switch (k){
                        case 1 -> color = Color.red;
                        case 2 -> color = Color.blue;
                        case 3 -> color = Color.green;
                        case 4 -> color = Color.black;
                    }
                    for(int j=0;j<moving.size();j++){
                        if(((ImageObject)moving.get(j)).getType() == 0){
                            try {
                                moving.get(j).getSpriteImages()[0]=ImageIO.read(new FileInputStream("plate"+k+".png"));
                                ((ImageObject)moving.get(j)).setColor(color);
                            } catch (IOException ex) {
                                Logger.getLogger(CircusWorld.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }  
                    }
                }
            }
        }
        spawn();
        return !timeout && lives>0;
    }
    
    private void spawn(){
        MovableObjectsFactory fact = MovableObjectsFactory.getInstance(this);
        for(int i=0;i<NUM_OF_MOVING_OBJS-moving.size();i++){
            int imgNum = (int) (Math.random()*16+1);
            ImageObject img = fact.getObject(imgNum);
            boolean b = true;
            for(GameObject o:moving){
                if(intersect(img, o)){
                    i--;
                    b=false;
                    break;
                }
            }
            if(b)
                moving.add(img);
        }
    }
    
    private void addScore(){
        int x = leftStack.size();
        int y = rightStack.size();
        if(x>2){
            List<GameObject> l = leftStack.subList(x-3, x);
            if(((ImageObject)l.get(0)).getColor() == ((ImageObject)l.get(1)).getColor() 
                    &&((ImageObject)l.get(0)).getColor() == ((ImageObject)l.get(2)).getColor()){
                score++;
                constant.remove(l.get(0));
                constant.remove(l.get(1));
                constant.remove(l.get(2));
                leftStack = (Stack<GameObject>) ct.popleft().getStack().clone();
            }
        }
        
        if(y>2){
            List<GameObject> l = rightStack.subList(y-3, y);
            if(((ImageObject)l.get(0)).getColor() == ((ImageObject)l.get(1)).getColor() 
                    &&((ImageObject)l.get(0)).getColor() == ((ImageObject)l.get(2)).getColor()){
                score++;
                constant.remove(l.get(0));
                constant.remove(l.get(1));
                constant.remove(l.get(2));
                rightStack = (Stack<GameObject>) ct.popright().getStack().clone();
            }
        }
    }
    
    private void removeLife(){
        ((ImageObject)constant.get(lives)).setVisible(false);
        lives--;
    }
    
    private void addLife(){
        if(lives==5)
            return;
        lives++;
        ((ImageObject)constant.get(lives)).setVisible(true);
    }
    
    @Override
    public String getStatus() {
        return "Score: "+score+"   |    Time="+ Math.max(0, (MAX_TIME-(System.currentTimeMillis()-startTime))/1000);
    }

    @Override
    public int getSpeed() {
        return DEFAULT_SPEED;
    }

    @Override
    public int getControlSpeed() {
        return DEFAULT_CONTROL_SPEED;
    }
    
}
