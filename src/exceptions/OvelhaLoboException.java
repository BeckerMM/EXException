package exceptions;

public class OvelhaLoboException extends RuntimeException{
    public OvelhaLoboException() {
        super("Você perdeu o lobo se alimentou da ovelha!");
    }
}
