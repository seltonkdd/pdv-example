package selton.dev.pdv_exemplo.exception.custom;

public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException (String entity) {
        super(entity + " não encontrado.");
    }
}
