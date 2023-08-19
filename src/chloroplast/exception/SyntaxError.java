package chloroplast.exception;

public class SyntaxError extends java.lang.Exception {
     public SyntaxError(){
         super();
         System.err.println("Chloroplast.SyntaxError");
     }
    public SyntaxError(String mess){
         super(mess);
         System.err.println("Chloroplast.SyntaxError : " + mess);

    }
}
