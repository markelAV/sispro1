package app.Model.While;

public class InvalidOperationException extends Exception {
    public InvalidOperationException(){
        super("Невалидная операция");
    }
}
