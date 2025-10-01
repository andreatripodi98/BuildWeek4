package andreapia.exceptions;

public class IdNotValidException extends RuntimeException {
    public IdNotValidException(String id) {
        super(id + "l'id inserito non è valido");
    }
}
