/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.model;

/**
 *
 * @author Ricardo
 */
public enum TipoAutorizacao {

    ADMIN, COMUM, BASICO;

    public static TipoAutorizacao fromString(String text) {
        if (text != null) {
            for (TipoAutorizacao c : TipoAutorizacao.values()) {
                if (text.equalsIgnoreCase(c.toString())) {
                    return c;
                }
            }
        }
        return null;
    }

}
