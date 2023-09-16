package Controller;

import eg.edu.alexu.csd.oop.game.GameObject;
import java.util.Stack;

public class CheckPoint {
    private Stack<GameObject> stack;
    
    public CheckPoint(Stack stack){
        this.stack = stack;
    }

    public Stack<GameObject> getStack() {
        return stack;
    }
}
