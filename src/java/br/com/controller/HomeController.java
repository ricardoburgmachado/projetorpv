package br.com.controller;

import br.com.model.Permissao;
import br.com.model.Usuario;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Ricardo
 */
@Controller
public class HomeController extends GenericController{

    @RequestMapping(value = {"", "/"})
    public ModelAndView home(HttpServletRequest request, HttpServletResponse response) throws IOException {
        
        Usuario user = (Usuario) request.getSession().getAttribute("usuario");
        
        if(!verificaAutorizacao(user, Permissao.ACESSO)){
            
            return new ModelAndView("login");
        }

        return new ModelAndView("index");
    }
}
