/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.controller;

import Exceptions.PersistenciaException;
import br.com.model.Usuario;
import br.com.repositorio.RepositorioPostgresFactory;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author rafael
 */
@Controller
public class UsuarioController {

    @RequestMapping(value = {"/autentica", "autentica"})
    public ModelAndView autentica(@RequestParam("login") String login, @RequestParam("senha") String senha, HttpServletRequest request, HttpServletResponse response) throws IOException {

        try {

            Usuario user = new RepositorioPostgresFactory().createRepositorioUsuario().autenticaUsuario(login, senha);
            request.getSession().setAttribute("usuario", user);
            response.sendRedirect("");
            return null;
        } catch (PersistenciaException pe) {

            request.setAttribute("exception", pe);
            return new ModelAndView("login");
        }
    }
    
    @RequestMapping(value = {"/login", "login"})
    public ModelAndView showLogin(HttpServletRequest request, HttpServletResponse response) throws IOException{
        
        return new ModelAndView("login");
    }
}
