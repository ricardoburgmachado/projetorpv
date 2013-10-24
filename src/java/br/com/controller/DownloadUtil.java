/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.controller;

import br.com.model.Arquivo;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author rafael
 */
public abstract class DownloadUtil {

    public static void iniciaDownload(HttpServletResponse response, Arquivo arquivo) {

        response.reset();

        String fileName = encodeFileName(arquivo.getNomeArquivo());

        response.setContentType(arquivo.getExtensao());
        response.setHeader("Content-disposition", "attachment; filename=" + fileName);
        OutputStream output = null;

        try {
            output = response.getOutputStream();
            output.write(arquivo.getDados());
        } catch (IOException ex) {

            System.out.println(ex.getMessage());
        } finally {

            try {
                output.close();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    private static String encodeFileName(String fileName) {
        
        try {
            fileName = URLEncoder.encode(fileName, "UTF-8");
            fileName = URLDecoder.decode(fileName, "ISO8859_1");
        } catch (UnsupportedEncodingException ex) {}
        finally{
            
            return fileName;
        }
    }
}
