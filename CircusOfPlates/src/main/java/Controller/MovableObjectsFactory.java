package Controller;

import Model.ImageObject;
import View.CircusWorld;
import java.awt.Color;

public class MovableObjectsFactory {
    private static MovableObjectsFactory fact=null;
    private CircusWorld world= null;
    
    private MovableObjectsFactory(CircusWorld world){
        this.world = world;
    }
    
    public static MovableObjectsFactory getInstance(CircusWorld world){
        if (fact==null)
            fact = new MovableObjectsFactory(world);
        return fact;
    }
    
    public ImageObject getObject(int imgNum){
        int x = (int) (Math.random()*world.getWidth());
        int y = (int) (Math.random()*world.getHeight()/4);
        switch (imgNum) {
            case 1,9,10 -> {
                return new ImageObject(x,y,0,Color.red,"plate1.png");
            }
            case 2,11,12 -> {
                return new ImageObject(x,y,0,Color.blue,"plate2.png");
            }
            case 3,13,14 -> {
                return new ImageObject(x,y,0,Color.green,"plate3.png");
            }
            case 4,15,16 -> {
                return new ImageObject(x,y,0,Color.black,"plate4.png");
            }
            case 5 -> {
                return new ImageObject(x,y,1,null,"evil.png");
            }
            case 6 -> {
                return new ImageObject(x,y,2,null,"heart.png");
            }
            case 7 -> {
                return new ImageObject(x,y,3,null,"bomb.png");
            }
            case 8 -> {
                return new ImageObject(x,y,4,null,"same.png");
            }
        }
        return null;  
    }
}
