package Process;

import exception.NotFoundREsException;
import exception.WrongSort;
import utility.StaticVal;
import utility.Token;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 61990 on 2017/10/25.
 *
 * 用于读取.l 文件 读出
 * 并初始化
 * 并保留各个token
 *
 */
public class REs {
    //文件路径
    private String filePath;
    /**
     *用来保存所有的正则表达式  token yylval re
     */
    private List<Token> expressions;


    public REs(String path){
        this.filePath = path;
        expressions = new ArrayList<>();
    }

    /**
     * 从文件中读取RE的信息
     */
    public void createREs()throws IOException,NotFoundREsException {
        FileReader fr = null;
        File file = new File(filePath);
        fr = new FileReader(file);

        @SuppressWarnings("resource")
        BufferedReader br = new BufferedReader(fr);
        String temp = null;
        while ((temp = br.readLine()) != null) {
            if(temp.trim().equals("")||temp.trim().equals("%%")){
                continue;
            }

            String str = temp.split(" ")[0];
            if (temp.length() < (str.length()+1)){
                throw new  NotFoundREsException();
            }else {
                expressions.add(new Token(str, temp.substring(str.length() + 1).trim()));
            }
        }

//        System.out.println(StaticVal.getTokens().size());
    }

    /**
     * 加上连接符号
     * 再生成后缀表达式
     * */
    public void postfixERs() throws WrongSort{
        for (Token token : expressions){
            String expr = format(token.getExpression());
            expr = addConnector(expr);
        }
    }

    /*
   * 去掉大括号
   *
   * */
    private String format(String expression) throws WrongSort{
        for (int i=0; i<expression.length();i++){
            if( expression.charAt(i)=='[' ){
                for (int j = i+1;j<expression.length();j++){
                    if (expression.charAt(j)==']'){
                        String mid = expression.substring(i+1,j);
                        mid = addOR(mid);
                        expression =expression.substring(0,i)+mid+expression.substring(j+1);
                        i=0;
                        break;
                    }
                }
            }

        }
        return expression;
    }
    /*
    * 加上或，去掉中括号
    *
    * */
    public String addOR(String str)throws WrongSort{
        StringBuffer stringBuffer= new StringBuffer();
        stringBuffer.append("(");
        for (int i=0; i < str.length()-1;i++){
            if (str.charAt(i)=='\\') {
                stringBuffer.append("\\");
                continue;
            }
            stringBuffer.append(str.substring(i,i+1)+"|");

           if (str.charAt(i+1)=='-'){
               if(str.length()>i+2) {
                   if(((str.charAt(i) <= 'Z' && str.charAt(i) >= 'A')&&(str.charAt(i+2) <= 'Z' && str.charAt(i+2) >= 'A'))||
                       ((str.charAt(i) <= 'z' && str.charAt(i) >= 'a')&&(str.charAt(i+2) <= 'z' && str.charAt(i+2) >= 'a'))||
                               ((str.charAt(i) <= '9' && str.charAt(i) >= '0')&&(str.charAt(i+2) <= '9' && str.charAt(i+2) >= '0'))){
                        if(str.charAt(i)>=str.charAt(i+2)){
                            throw new WrongSort();
                        }

                       char begin = str.charAt(i);
                       char end = str.charAt(i + 2);
                       for (char j = (char) (begin + 1); j < end; j++) {
                           stringBuffer.append(String.valueOf(j) + "|");
                       }
                       i++;

                   }
               }
           }
        }

        stringBuffer.append(str.substring(str.length()-1)+")");
        return stringBuffer.toString();
    }

    /*
    * 加上连接符号
    *
    * */
    private String addConnector(String expression){

        for (int i=1; i<expression.length();i++){
            if (isAdd(expression.charAt(i-1),expression.charAt(i))){
                expression = expression.substring(0,i)+"▪"+expression.substring(i);
                i++;
            }

        }
        System.out.println(expression);
        return " ";
    }
    /*
    * 判断两个字符之间是否加▪
    * */
    private boolean isAdd(char ahead,char tail){
        if(tail=='('){
            if(ahead!='('){
                return true;
            }
        }else{
            return isEnd(ahead)&&isValid(tail);
        }
        return false;
    }
    /*
    *判断字符是否是要连接的
    * */
    private boolean isValid(char c){
        if ((c<='z'&&c>='a')||(c<='Z'&&c>='A')||(c<=9&&c>=0)|| StaticVal.isBegin(c)){
            return true;
        }
        return false;
    }
    private boolean isEnd(char c){
        if ((c<='z'&&c>='a')||(c<='Z'&&c>='A')||(c<=9&&c>=0)|| StaticVal.isBegin(c)||StaticVal.isEnd(c)){
            return true;
        }
        return false;
    }
}