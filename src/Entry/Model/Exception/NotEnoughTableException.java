package Entry.Model.Exception;

/**
 * Exception to denote when a reservation cannot be done
 */
public class NotEnoughTableException extends Exception{

    /**
     * Creates the new exception and adds a default error message;
     */
    public NotEnoughTableException(){
        super("Not enough room to fit you at that time");
    }
}
