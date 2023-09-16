package Controller;

import java.util.Stack;

public class Caretaker {
    private Stack<CheckPoint> savesleft = new Stack<>();
    private Stack<CheckPoint> savesright = new Stack<>();
    
    public void saveleft(CheckPoint save){
        savesleft.add(save);
    }
    
    public CheckPoint popleft(){
        savesleft.pop();
        savesleft.pop();
        savesleft.pop();
        return savesleft.peek();
    }
    
    public void saveright(CheckPoint save){
        savesright.add(save);
    }
    
    public CheckPoint popright(){
        savesright.pop();
        savesright.pop();
        savesright.pop();
        return savesright.peek();
    }
}
