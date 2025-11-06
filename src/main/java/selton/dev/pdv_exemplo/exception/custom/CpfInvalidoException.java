package selton.dev.pdv_exemplo.exception.custom;

public class CpfInvalidoException extends RuntimeException {
    public CpfInvalidoException() {
        super("CPF inválido.");
    }
}
