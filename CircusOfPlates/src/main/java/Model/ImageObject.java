package Model;

import eg.edu.alexu.csd.oop.game.GameObject;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ImageObject implements GameObject{

    private static final int MAX_MSTATE = 1;
    
    private BufferedImage[] spriteImages;
    private int x,y,type;
    private boolean visible;
    private Color color;
    public ImageObject(int x, int y, String imgPath){
        this(x,y,0,null,imgPath);
    }
    public ImageObject(int x, int y, int type, Color color, String imgPath){
        this.x=x;
        this.y=y;
        this.type = type;
        this.color = color;
        this.spriteImages = new BufferedImage[MAX_MSTATE];
        this.visible = true;
        try{
            spriteImages[0]=ImageIO.read(new FileInputStream(imgPath));
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
  
    @Override
    public int getX() {
        return x;
    }

    @Override
    public void setX(int x) {
        this.x=x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public void setY(int y) {
        this.y=y;
    }

    @Override
    public int getWidth() {
        return spriteImages[0].getWidth();
    }

    @Override
    public int getHeight() {
        return spriteImages[0].getHeight();
    }

    @Override
    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible=visible;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
    
    @Override
    public BufferedImage[] getSpriteImages() {
        return spriteImages;
    }
}
