/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Exceptions;

/**
 *
 * @author rafael
 */
public class PrivacidadeException extends DadoInconsistenteException{

    public PrivacidadeException(String message) {
        super(message);
    }

    public PrivacidadeException(DadoInconsistenteException exception, String message) {
        super(exception, message);
    }
}
