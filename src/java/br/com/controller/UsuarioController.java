/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.controller;

import br.com.model.Usuario;
import br.com.repositorio.RepositorioPostgresFactory;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author rafael
 */
@Controller
public class UsuarioController {
    
    @RequestMapping("/login")
    public ModelAndView autentica(HttpServletRequest request){
        
        String login = request.getParameter("login");
        String senha = request.getParameter("senha");
        
        Usuario user = new RepositorioPostgresFactory().createRepositorioUsuario().autenticaUsuario(login, senha);
        
        ModelAndView mv = new ModelAndView("login");
        mv.addObject("usuario", user);
        
        return mv;
    }
    
}
