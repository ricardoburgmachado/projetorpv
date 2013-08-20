/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.filtro;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 *
 * @author Ricardo
 */
public class FiltroControleAcesso extends HandlerInterceptorAdapter {
    /*
     @Override
     public boolean preHandle(HttpServletRequest request,
     HttpServletResponse response, Object controller) throws Exception {

        
     String uri = request.getRequestURI();
     if (uri.endsWith("autentica")) {
     return true;
     }

     if (request.getSession()
     .getAttribute("xxxxxxxx") == null) {
     return true;
     }
     response.sendRedirect("autentica");
     return false;
         
     */

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("Calling preHandle");
        return super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("Calling postHandle");
        
        
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("Calling afterCompletion");
        super.afterCompletion(request, response, handler, ex);
    }

}
