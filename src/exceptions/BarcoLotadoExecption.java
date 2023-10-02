package exceptions;

public class BarcoLotadoExecption extends RuntimeException{
    public BarcoLotadoExecption() {
        super("Barco já está lotado!");
    }
}
