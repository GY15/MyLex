
/**
 * Created by 61990 on 2017/10/25.
 */
import exception.NotTokenException;
import process.*;
import exception.NotFoundREsException;
import exception.NotREsException;
import exception.WrongSort;
import utility.StaticVal;
import utility.Token;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
        DFA_collection handle = null;
        try {
            handleRE.handle_RE_to_NFA();
            handle = new DFA_collection(handleRE.getNFA_Expressions());
            handle.handle_NFA_to_DFA();
            handle.minDFA();
//            int i =1+1;
        } catch (NotREsException e) {
            e.printStackTrace();
        }
        //至此得到所有的Token
        HandleTxt handleTxt=null;
        List<List<Token>> tokenLists=null;
        try {
            handleTxt = new HandleTxt("files/input.txt", handle);
            tokenLists = handleTxt.getTokens();
        } catch (NotTokenException e) {
            e.printStackTrace();
        }
        for (List<Token> tokens : tokenLists){
            for (Token token : tokens){
                if(!token.getToken().equals("ε"))
                System.out.print(token.getToken()+" ");

            }
            System.out.println();
        }


//        StandardRE standardRE
// = handler.getStandardRE();
//        NFA nfa = standardRE.getNFA();
//        DFA dfa = new DFA(nfa);
//        MinDFA minDFA = dfa.getMinDFA();
    }
}
