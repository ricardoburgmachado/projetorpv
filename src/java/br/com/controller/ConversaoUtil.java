/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.controller;

import java.util.Date;

/**
 *
 * @author rafael
 */
public abstract class ConversaoUtil {
    
    /**
     * Converte string em java.util.Date
     * @param data Data no formato ano-mes-dia
     * @return Objeto java.util.Date
     */
    public static Date stringToDate(String data){
        
        if(data == null || data.equals("")){
            
            return null;
        }
        
        String[] campos = data.split("-");
        
        return new Date(Integer.parseInt(campos[2]) - 1900, Integer.parseInt(campos[1]) - 1, Integer.parseInt(campos[0]));
    }
}
