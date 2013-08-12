/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author rafael
 */

@Controller
public class HomeController {
    
    @RequestMapping(value={"", "/"})
    public ModelAndView home(){
        
        return new ModelAndView("login");
    }
}
