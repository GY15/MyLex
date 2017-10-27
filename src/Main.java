
/**
 * Created by 61990 on 2017/10/25.
 */
import Process.*;
import exception.NotFoundREsException;
import exception.NotREsException;
import exception.WrongSort;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        String path = "files/REs.l";
        RE_collection handler = new RE_collection(path);
        try {
            handler.createREs();
            handler.postfixERs();
        }catch (IOException e1){
            e1.printStackTrace();
        }catch (NotFoundREsException e2){
            e2.printStackTrace();
        }catch (WrongSort e3){
            e3.printStackTrace();
        }catch (NotREsException e4){
            e4.printStackTrace();
        }
        NFA_collection handleRE = new NFA_collection(handler.getExpressions());
        try {
            handleRE.handle_RE_to_NFA();
        } catch (NotREsException e) {
            e.printStackTrace();
        }
//        StandardRE standardRE
// = handler.getStandardRE();
//        NFA nfa = standardRE.getNFA();
//        DFA dfa = new DFA(nfa);
//        MinDFA minDFA = dfa.getMinDFA();
    }
}
