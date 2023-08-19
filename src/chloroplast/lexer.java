package chloroplast;

import chloroplast.exception.SyntaxError;
import org.w3c.dom.NodeList;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import chloroplast.Lexical.*;


public class lexer {
    String FilePath = "";
    File lexFile = new File(FilePath);
    public lexer(String FilePath){
        this.FilePath = FilePath;
    }



    /**
     * 规定Token类型
     * 任何可辨识字符被认作Token
     */
    static class Token{
        TOKENTYPE type;
        int times = 0;
        String value = "";
        public Token(TOKENTYPE type,String value){
            this.type = type;
            this.value = value;
        }
        @Override
        public String toString(){
            return switch (type) {
                case STR -> "STR|" + value;
                case DOT -> "DOT|" + value;
                case IDF -> "IDF|" + value;
                case NUM -> "NUM|" + value;
                case OPR -> "OPR|" + value;
                case LP -> "LP|"+value;
                default -> "INVALID";
            };
    }

        public TOKENTYPE getType() {
            return type;
        }

        public String getValue() {
            return value;
        }

        static enum TOKENTYPE{
        STR,DOT,IDF,NUM,OPR,END,LP,LR,SIM,NOTE,NOTEM,DOC
    }
    }

    public static void main(String[] args) throws SyntaxError {
        ArrayList<String> inputList = new ArrayList<>();
        String lexFile = "D:\\input.txt";
        String line = "";

        try (BufferedReader br = new BufferedReader(new FileReader(lexFile))) {
            while ((line = br.readLine()) != null) {
                inputList.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (String inputString : inputList) {
            List<lexer.Token> tokens = Lexical.tokenize(inputString);


            for (Token token : tokens) {
                System.out.println(token.getType() + "|" + token.getValue());
            }
        }
    }
}
