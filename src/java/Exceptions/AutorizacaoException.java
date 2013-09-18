/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Exceptions;

/**
 *
 * @author rafael
 */
public class AutorizacaoException extends RuntimeException {

    public AutorizacaoException() {
    }

    public AutorizacaoException(String message) {
        super(message);
    }
}
