package Controller;

public class LevelContext {
    private Level level;
    
    public LevelContext(Level level){
        this.level = level;
    }
    
    public int setSpeed(){
        return level.setSpeed();
    }
    
    public int setTime(){
        return level.setTime();
    }
    
    public int setNoofFallingShapes(){
        return level.nooffallingshapes();
    }
    
    public int setControlSpeed(){
        return level.controlspeed();
    }
   
}
