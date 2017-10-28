package Process;

import exception.NotREsException;
import utility.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

/**
 * Created by 61990 on 2017/10/27.
 */
public class DFA_collection {
    //保存处理过的expression
    private List<Token_DFA> DFA_Expressions;

    public List<Token_DFA> getDFA_Expressions() {
        return DFA_Expressions;
    }

    private List<Token_NFA> NFA_Expressions;

    public DFA_collection(List<Token_NFA> NFA_Expressions) {
        this.NFA_Expressions = NFA_Expressions;
        DFA_Expressions = new ArrayList<>();
    }

    /**
     *
     * 处理后缀表达式到NFA
     */
    public void handle_NFA_to_DFA() throws NotREsException {
        for (int i = 0; i < NFA_Expressions.size(); i++) {
            try {
                Token_NFA NFA = NFA_Expressions.get(i);
                //todo 返回一个DFA
                DFA_Expressions.add(new Token_DFA(NFA,NFA_to_DFA(NFA.getNFA())));
            } catch (Exception e) {
                e.printStackTrace();
                throw new NotREsException(i);
            }
        }
    }

    private DFA NFA_to_DFA(NFA nfa) {
        List<NFA_Edge> edges = nfa.getNFA_Edges();
        List<Integer> closure0 = new ArrayList<>();
        closure0.add(0);
        List<Integer> closure = getClosure(closure0,edges);
        return null;
    }
    private List<Integer> getClosure(List<Integer> nums,List<NFA_Edge> NFAs){
        List<Integer> closure = new ArrayList<>();
        List<Integer> hasCal = new ArrayList<>();
        while(nums.size()!=0) {
            for (int i = 0; i < nums.size(); i++) {
                for (int j = 0; j < NFAs.size(); j++) {
                    if (NFAs.get(j).getFirst_id() == nums.get(i)) {
                        int num = NFAs.get(j).getSecond_id();
                        if(!hasCal.contains(num)){
                            closure.add(num);
                            nums.add(num);
                        }
                    }
                }
                hasCal.add(nums.get(i));
                nums.remove(i);
                i--;
            }
        }

        return closure;
    }


}
