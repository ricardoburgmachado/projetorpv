/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rafael
 */
public  class PropertiesFactory {

    public Properties getPropertiesBDPostgres() {

        Properties p = System.getProperties();
        try {
            
            p.load(new FileInputStream("ConfigBD.txt"));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PropertiesFactory.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PropertiesFactory.class.getName()).log(Level.SEVERE, null, ex);
        }

        return p;
    }
}
