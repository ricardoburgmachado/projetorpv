/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.controller;

import br.com.model.Arquivo;
import br.com.model.Edital;
import br.com.model.Permissao;
import br.com.model.ProReitor;
import br.com.model.Usuario;
import br.com.repositorio.RepositorioPostgresFactory;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class EditalController extends GenericController {

    HttpServletRequest request;

    /**
     * Método que apenas carrega o formulário para cadastro de editais
     *
     * @return ModelAndView
     */
    @RequestMapping(value = "/edital_adiciona_show")
    public ModelAndView editalAdicionaShow(HttpServletRequest p_request) {

        this.request = p_request;
        Usuario user = (Usuario) request.getSession().getAttribute("usuario");

        //%%%%%%%%%%%%%%%%%%% ATENÇÃO AQUI DEVE SER REVISTO ESSA LÓGICA DE AUTORIZAÇÃO  %%%%%%%%%%%%%%%%%%%
        if (!verificaAutorizacao(user, Permissao.CRUD_PROJETO)) {

            return new ModelAndView("login");
        }

        ModelAndView mv = new ModelAndView("edital_adiciona");
        return mv;
    }
    
    @RequestMapping(value = "/edital_adiciona", method = RequestMethod.POST)
    public ModelAndView editalAdiciona(@ModelAttribute Edital edital, HttpServletRequest p_request,
            @RequestParam(value = "arquivo_xx", required = false) MultipartFile arquivo) throws IOException {
        
        String[] split = arquivo.getOriginalFilename().split("\\.");
        Arquivo arq = new Arquivo(arquivo.getName(), split[split.length-1], arquivo.getBytes());
        ProReitor pro = new ProReitor();
        pro.setId(1);
        edital.setProReitor(pro);
        edital.setArquivo(arq);
        
        new RepositorioPostgresFactory().createRepositorioEdital().adiciona(edital);
        return null;
    }

   /**
     * Método que apenas carrega o formulário para edição de projetos, com os
     * campos preenchidos
     *
     * @param id
     * @return ModelAndView
     */
    @RequestMapping(value = "/edital_edita_show")
    public ModelAndView editalEditaShow(HttpServletRequest p_request, @RequestParam int id) {
        
        this.request = p_request;
        System.out.println("************** ID VINDA POR PARAMETRO: " + id);

        Usuario user = (Usuario) request.getSession().getAttribute("usuario");
        if (!verificaAutorizacao(user, Permissao.CRUD_PROJETO)) {

            return new ModelAndView("login");
        }


        ModelAndView mv = new ModelAndView("edital_edita");

        return mv;
    }
    
    
    @RequestMapping(value = "/edital_lista_show")
    public ModelAndView editalListaShow(HttpServletRequest p_request) {

        this.request = p_request;
        Usuario user = (Usuario) request.getSession().getAttribute("usuario");
        if (!verificaAutorizacao(user, Permissao.CRUD_PROJETO)) {

            return new ModelAndView("login");
        }

        ModelAndView mv = new ModelAndView("edital_lista");
        //if (projetos != null) {
        //    mv.addObject("projetos", projetos);
        //}
        return mv;
    }
    
    
    

}
