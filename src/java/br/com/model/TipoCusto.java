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
public enum TipoCusto {
   
    CUSTEIO{
            @Override
            public String toString() {
                return "CUSTEIO";
            }
     },CAPITAL{
            @Override
            public String toString() {
                return "CAPITAL";
            }
     };
    
    
    

    public static TipoCusto fromString(String text) {
        if (text != null) {
            for (TipoCusto c : TipoCusto.values()) {
                if (text.equalsIgnoreCase(c.toString())) {
                    return c;
                }
            }
        }
        return null;
    }
}
