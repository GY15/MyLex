package utility;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 61990 on 2017/10/26.
 * 保存需要从文本里提取yylval 的token
 *
 */
public class StaticVal {
    /**
     *
     * 保存需要从文本里提取yylval 的token
     *
     */
    public static List<String> token;

    public static List<String> getTokens() {
        if(token ==null){
            token = new ArrayList<>();
        }
            return token;
    }
    public static void add(String token){
        getTokens().add(token);
    }

    /**
     *保存需要加连接符的特殊字符
     */
    public static char[] sign = new char[]{'=','>','<','!'};
    public static boolean isBegin(char c){
        for (int i =0 ;i<sign.length;i++){
            if (c==sign[i]){
                return true;
            }
        }
        return false;
    }
    public static char[] re_sign = new char[]{'+','?','!'};
    public static boolean isEnd(char c){
        for (int i =0 ;i<re_sign.length;i++){
            if (c==re_sign[i]){
                return true;
            }
        }
        return false;
    }
}
