package utility;

/**
 * Created by 61990 on 2017/10/27.
 */
public class Token_DFA {
    private String token;
    private String yylval;
    private DFA DFA;
    /**
     * 保存生成的 DFA 和从NFA得来的 token
     */
    public Token_DFA(Token_NFA NFA_Expressions, DFA dfa) {
        this.token = NFA_Expressions.getToken();
        this.yylval = NFA_Expressions.getYylval();
        this.DFA = dfa;
    }

    public String getToken() {
        return token;
    }

    public String getYylval() {
        return yylval;
    }

    public DFA getDFA() {
        return DFA;
    }
}
