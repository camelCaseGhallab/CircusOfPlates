package Controller;

public class Level3 implements Level{

    @Override
    public int setSpeed() {
        return 2;
    }

    @Override
    public int setTime() {
        return 1*60*1000;
    }

    @Override
    public int nooffallingshapes() {
        return 30;
    }

    @Override
    public int controlspeed() {
        return 30;
    }
    
}
