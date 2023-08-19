package chloroplast;

import chloroplast.exception.SyntaxError;

import java.util.List;

public class Syntax {
    public static void SyntaxAnalyze(List<lexer.Token> Tokens) throws SyntaxError {
        int i = 0;
        int status = 0;
        while (i < Tokens.size()){
            lexer.Token tk = Tokens.get(i);
            if(tk.type.equals(lexer.Token.TOKENTYPE.IDF)){
                i++;
                if(!Tokens.get(i).type.equals(lexer.Token.TOKENTYPE.IDF)){
                    throw new SyntaxError("Unexpected character appears in \"" + Tokens.get(i)  +"\"");
                }
            }
            else if (tk.type.equals(lexer.Token.TOKENTYPE.LP)) {//如果遇到了左括号
                i++;
                 if(Tokens.get(i).type.equals(lexer.Token.TOKENTYPE.IDF)){//在括号里面的标识符
                     i++;//先不检查，略过
                 }
                 if(Tokens.get(i).type.equals(lexer.Token.TOKENTYPE.OPR)){//在括号中的运算符
                     i++;
                 }
                 if(Tokens.get(i).type.equals(lexer.Token.TOKENTYPE.NUM)){
                     i++;
                 }
                 if(!Tokens.get(i).toString().endsWith(")")){
                     i++;
                 }
            }
            else if (tk.type.equals(lexer.Token.TOKENTYPE.END)) {

            }
        }
    }
}
