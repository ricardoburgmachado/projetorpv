package Exceptions;


public class PersistenciaException extends RuntimeException {
    
    private Exception exception;
    
    public PersistenciaException(String message, Exception exception){
        
        this(message);
        this.exception = exception;
    }
    
    public PersistenciaException(String message){
        
        super(message);
    }

    public Exception getException() {
        return exception;
    }
    
}
