package chloroplast;

import chloroplast.exception.SyntaxError;

import java.util.ArrayList;
import java.util.List;

/**
 * <h1>USAGE</h1>
 *
 */
public class Lexical {
    static SyntaxError syne;
    static int Err = 0;

    private boolean isKey(int c) {
        char a = (char) c;
        return (a >= 'a' && a <= 'z') || (a >= 'A' && a <= 'Z') || a == '_';
    }

    private boolean isNum(int c) {
        return Character.isDigit((char) c);
    }

    public static List<lexer.Token> tokenize(String inputString) throws SyntaxError {
        String alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String digits = "0123456789";
        String operators = "+-*";
        String note = "/";
        String LPs = "([{<";
        String LRs = "}])>";
        String end = ";";
        String sim = ".,|?!$#@%^&*_";
        // ... 其他字符集定义

        ArrayList<lexer.Token> tokens = new ArrayList<>();

        int i = 0;
        while (i < inputString.length()) {
            char ch = inputString.charAt(i);
            StringBuilder token = new StringBuilder();

            if (alphabet.indexOf(ch) != -1) { // 字母
                while (i < inputString.length() && alphabet.indexOf(inputString.charAt(i)) != -1) {
                    token.append(inputString.charAt(i));
                    i++;
                }
                tokens.add(new lexer.Token(lexer.Token.TOKENTYPE.IDF, token.toString()));
            } else if (digits.indexOf(ch) != -1) { // 数字
                while (i < inputString.length() && digits.indexOf(inputString.charAt(i)) != -1) {
                    token.append(inputString.charAt(i));
                    i++;
                }
                tokens.add(new lexer.Token(lexer.Token.TOKENTYPE.NUM, token.toString()));
            } else if (end.indexOf(ch) != -1) { // 终止符（;）
                tokens.add(new lexer.Token(lexer.Token.TOKENTYPE.END, String.valueOf(ch)));
                i++;
            } else if (LPs.indexOf(ch) != -1) { // 左括号
                tokens.add(new lexer.Token(lexer.Token.TOKENTYPE.LP, String.valueOf(ch)));
                i++;
            } else if (LRs.indexOf(ch) != -1) { // 右括号
                tokens.add(new lexer.Token(lexer.Token.TOKENTYPE.LR, String.valueOf(ch)));
                i++;
            } else if (operators.indexOf(ch) != -1) { // 运算符
                tokens.add(new lexer.Token(lexer.Token.TOKENTYPE.OPR, String.valueOf(ch)));
                i++;
            } else if (inputString.startsWith("\"")) { //
                if (inputString.endsWith("\"")) {
                    tokens.add(new lexer.Token(lexer.Token.TOKENTYPE.STR, inputString));
                    i++;
                } else {
                    throw new SyntaxError("This string has not a end '\"'" + "\nat: " + inputString + " <-" + "\ncharLocalization");
                }

            } else if (sim.indexOf(ch) != -1) {//符号
                tokens.add(new lexer.Token(lexer.Token.TOKENTYPE.SIM, String.valueOf(ch)));
                i++;
            } else if (note.indexOf(ch) != -1) {//斜杠(可能是注释，也可能是除法符号)
                if (i + 1 < inputString.length() && inputString.charAt(i + 1) == '*') {//块注释
                    // 块注释开始
                    i += 2; // 跳过 '*' 和 '/' 字符
                    while (i + 1 < inputString.length() && !(inputString.charAt(i) == '*' && inputString.charAt(i + 1) == '/')) {
                        i++; // 继续向后遍历
                    }
                    i += 2; // 跳过注释结束的 '*' 和 '/' 字符
                } else {
                    if (i + 1 < inputString.length() && inputString.charAt(i + 1) == '/') {//注释
                        i++;
                    } else {
                        tokens.add(new lexer.Token(lexer.Token.TOKENTYPE.OPR, String.valueOf(ch)));
                    }
                }
                i++;
            } else {
                i++;
            }


        }
        return tokens;
    }
}

