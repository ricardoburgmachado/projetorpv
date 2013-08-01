package br.com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProjetoController {
    
    @RequestMapping(value = "/projeto_adiciona", method = RequestMethod.POST)
    public ModelAndView projetoAdiciona(){
    
        return new ModelAndView("projeto_adiciona");
    }
    
    @RequestMapping(value = "/projeto_adiciona_show")
    public ModelAndView projetoAdicionaShow(){
    
        return new ModelAndView("projeto_adiciona");
    }
    
}
