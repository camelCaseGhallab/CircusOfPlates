package Controller;

public class Level2 implements Level{

    @Override
    public int setSpeed() {
        return 2;
    }

    @Override
    public int setTime() {
        return 2*60*1000;
    }

    @Override
    public int nooffallingshapes() {
        return 20;
    }

    @Override
    public int controlspeed() {
        return 50;
    }
    
}
