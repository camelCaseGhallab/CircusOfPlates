package Controller;

public class Level1 implements Level{

    @Override
    public int setSpeed() {
        return 1;
    }

    @Override
    public int setTime() {
        return 3*60*1000;
    }

    @Override
    public int nooffallingshapes() {
        return 10;
    }

    @Override
    public int controlspeed() {
        return 70;
    }

    
}
