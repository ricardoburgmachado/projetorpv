/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Exceptions;

import java.util.List;

/**
 *
 * @author rafael
 */
public class DadoInconsistenteException extends RuntimeException{
    
    private String message;
    private DadoInconsistenteException exception;

    public DadoInconsistenteException(String message) {
        super(message);
        this.message = message;
    }

    public DadoInconsistenteException(DadoInconsistenteException exception, String message) {
        super(message, exception);
        this.message = message;
        this.exception = exception;
    }

    public DadoInconsistenteException getException() {
        return exception;
    }
}
