package utility;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 61990 on 2017/10/27.
 */
public class DFA_State {
    private int DFA_ID;
    private List<Integer> states;
    private boolean isTerminal;
    public DFA_State(){
        DFA_ID = StaticVal.getDFA_ID();
        states=new ArrayList<>();
        isTerminal=false;
    }
}
