package Entry.Model.Exception;

public class NotEnoughTableException extends Exception{
    public NotEnoughTableException(){
        super("Not enough room to fit you at that time");
    }
}
