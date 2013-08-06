/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Exceptions;

/**
 *
 * @author rafael
 */


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
