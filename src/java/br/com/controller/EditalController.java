/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.controller;

import Exceptions.AutorizacaoException;
import Exceptions.DadoInconsistenteException;
import Exceptions.PersistenciaException;
import Exceptions.PrivacidadeException;
import br.com.model.Arquivo;
import br.com.model.Edital;
import br.com.model.Permissao;
import br.com.model.ProReitor;
import br.com.model.TipoProjeto;
import br.com.model.Usuario;
import br.com.repositorio.RepositorioEdital;
import br.com.repositorio.RepositorioFacade;
import br.com.repositorio.RepositorioPostgresFactory;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class EditalController extends GenericController {

    HttpServletRequest request;
    RepositorioFacade facade;

    public EditalController() {

        this.facade = new RepositorioFacade();
    }

    /**
     * Método que apenas carrega o formulário para cadastro de editais
     *
     * @param p_request
     * @return ModelAndView
     */
    @RequestMapping(value = "/edital_adiciona_show")
    public ModelAndView editalAdicionaShow(HttpServletRequest p_request) {

        this.request = p_request;
        Usuario user = (Usuario) request.getSession().getAttribute("usuario");

        if (!verificaAutorizacao(user, Permissao.CRUD_EDITAL)) {

            return new ModelAndView("login");
        }

        ModelAndView mv = new ModelAndView("edital_adiciona");
        return mv;
    }

    @RequestMapping(value = "/edital_adiciona", method = RequestMethod.POST)
    public ModelAndView editalAdiciona(@ModelAttribute Edital edital, HttpServletRequest p_request,
            @RequestParam(value = "arquivo_xx", required = false) MultipartFile arquivo) throws IOException {

        this.request = p_request;

        Usuario user = (Usuario) request.getSession().getAttribute("usuario");
        if (!verificaAutorizacao(user, Permissao.CRUD_EDITAL)) {

            return new ModelAndView("login");
        }

        if (!arquivo.isEmpty()) {
            String[] split = arquivo.getOriginalFilename().split("\\.");
            Arquivo arq = new Arquivo(arquivo.getName(), arquivo.getContentType(), arquivo.getBytes());
            edital.setArquivo(arq);
        }

        ProReitor pro = new ProReitor();
        pro.setId(user.getId());
        edital.setProReitor(pro);

        edital.setTipo(user.getArea());

        String[] pI = request.getParameterValues("prazoInicial_xx");
        String[] pF = request.getParameterValues("prazoFinal_xx");

        if (pI[0] != null && !pI[0].equals("")) {
            String[] pISplit = pI[0].split("-");
            edital.setPrazoInicial(new Date(Integer.parseInt(pISplit[0]) - 1900, Integer.parseInt(pISplit[1]) - 1, Integer.parseInt(pISplit[2])));
        }

        if (pF[0] != null && !pF[0].equals("")) {
            String[] pFSplit = pF[0].split("-");
            edital.setPrazoFinal(new Date(Integer.parseInt(pFSplit[0]) - 1900, Integer.parseInt(pFSplit[1]) - 1, Integer.parseInt(pFSplit[2])));
        }
        List<String> inconsistencias = new LinkedList<>();

        try {
            new RepositorioPostgresFactory().createRepositorioEdital().adiciona(edital);
        } catch (DadoInconsistenteException diex) {
            do {
                inconsistencias.add(diex.getMessage());
                diex = diex.getException();
            } while (diex != null);
        } catch (PersistenciaException pex) {

            inconsistencias.add(pex.getMessage());
        }
        ModelAndView mv;
        if (inconsistencias.size() > 0) {
            mv = new ModelAndView("edital_adiciona");
            mv.addObject("mensagem", inconsistencias);
        } else {
            RepositorioEdital repositorioEdital = new RepositorioPostgresFactory().createRepositorioEdital();
            List<Edital> editais = repositorioEdital.listarEditais(user.getId());
            mv = new ModelAndView("edital_lista");
            mv.addObject("editais", editais);
            mv.addObject("mensagem", "Edital cadastrado com sucesso");
        }
        return mv;
    }

    /**
     * Método que apenas carrega o formulário para edição de projetos, com os
     * campos preenchidos
     *
     * @param p_request
     * @param idEdital
     * @return ModelAndView
     */
    @RequestMapping(value = "/edital_edita_show")
    public ModelAndView editalEditaShow(HttpServletRequest p_request, @RequestParam int id) {

        this.request = p_request;
        System.out.println("************** ID VINDA POR PARAMETRO: " + id);


        Usuario user = (Usuario) request.getSession().getAttribute("usuario");
        if (!verificaAutorizacao(user, Permissao.CRUD_EDITAL)) {
            return new ModelAndView("login");
        }

        RepositorioEdital repositorioEdital = new RepositorioPostgresFactory().createRepositorioEdital();
        RepositorioFacade facade = new RepositorioFacade();
        Edital edital = facade.obter(id, user);
        ModelAndView mv = new ModelAndView("edital_edita");
        mv.addObject("edital", edital);
        return mv;
    }

    @RequestMapping(value = "/edital_lista_show")
    public ModelAndView editalListaShow(HttpServletRequest p_request) {

        this.request = p_request;
        Usuario user = (Usuario) request.getSession().getAttribute("usuario");
        if (!verificaAutorizacao(user, Permissao.CRUD_EDITAL)) {

            return new ModelAndView("login");
        }
        RepositorioEdital repositorioEdital = new RepositorioPostgresFactory().createRepositorioEdital();
        List<Edital> editais = repositorioEdital.listarEditais(user.getId());
        ModelAndView mv = new ModelAndView("edital_lista");
        mv.addObject("editais", editais);
        return mv;
    }

    @RequestMapping(value = "/edital_edita", method = RequestMethod.POST)
    public ModelAndView editalEdita(@ModelAttribute Edital edital, HttpServletRequest p_request,
            @RequestParam(value = "arquivo_xx", required = false) MultipartFile arquivo) throws IOException {

        this.request = p_request;

        Usuario user = (Usuario) request.getSession().getAttribute("usuario");
        if (!verificaAutorizacao(user, Permissao.CRUD_EDITAL)) {

            return new ModelAndView("login");
        }

        if (!arquivo.isEmpty()) {

            Arquivo arq = new Arquivo(arquivo.getName(), arquivo.getContentType(), arquivo.getBytes());
            edital.setArquivo(arq);
        }

        ProReitor pro = new ProReitor();
        pro.setId(user.getId());
        edital.setProReitor(pro);

        edital.setTipo(user.getArea());
        System.out.println("************ ID DO EDITAL: " + edital.getId());
        String[] pI = request.getParameterValues("prazoInicial_xx");
        String[] pF = request.getParameterValues("prazoFinal_xx");

        if (pI[0] != null && !pI[0].equals("")) {
            String[] pISplit = pI[0].split("-");
            edital.setPrazoInicial(new Date(Integer.parseInt(pISplit[0]) - 1900, Integer.parseInt(pISplit[1]) - 1, Integer.parseInt(pISplit[2])));
        }

        if (pF[0] != null && !pF[0].equals("")) {
            String[] pFSplit = pF[0].split("-");
            edital.setPrazoFinal(new Date(Integer.parseInt(pFSplit[0]) - 1900, Integer.parseInt(pFSplit[1]) - 1, Integer.parseInt(pFSplit[2])));
        }

        List<String> inconsistencias = new LinkedList<>();

        try {
            new RepositorioPostgresFactory().createRepositorioEdital().edita(edital);
        } catch (DadoInconsistenteException diex) {
            do {
                inconsistencias.add(diex.getMessage());
                diex = diex.getException();
            } while (diex != null);
        } catch (PersistenciaException pex) {

            inconsistencias.add(pex.getMessage());
        }
        ModelAndView mv;
        if (inconsistencias.size() > 0) {
            RepositorioEdital repositorioEdital = new RepositorioPostgresFactory().createRepositorioEdital();
            RepositorioFacade facade = new RepositorioFacade();
            Edital editalBD = facade.obter(edital.getId(), user);
            mv = new ModelAndView("edital_edita");
            mv.addObject("edital", editalBD);
            mv.addObject("mensagem", inconsistencias);
        } else {
            RepositorioEdital repositorioEdital = new RepositorioPostgresFactory().createRepositorioEdital();
            List<Edital> editais = repositorioEdital.listarEditais(user.getId());
            mv = new ModelAndView("edital_lista");
            mv.addObject("editais", editais);
            mv.addObject("mensagem", "Edital alterado com sucesso");
        }

        return mv;
    }

    @RequestMapping(value = "/edital_exclui")
    public ModelAndView excluiEdital(HttpServletRequest request, @RequestParam int id) {

        Usuario user = (Usuario) request.getSession().getAttribute("usuario");

        if (user == null) {

            return new ModelAndView("login");
        }

        List<String> inconsistencias = new ArrayList<>();
        RepositorioFacade facade = new RepositorioFacade();
        ModelAndView mv = new ModelAndView("edital_lista");

        try {

            facade.excluiEdital(id, user);
        } catch (AutorizacaoException aex) {

            return new ModelAndView("login");
        } catch (PrivacidadeException pex) {

            return this.editalListaShow(request);
        } catch (DadoInconsistenteException diex) {

            do {

                inconsistencias.add(diex.getMessage());
                diex = diex.getException();
            } while (diex != null);
        }

        if (!inconsistencias.isEmpty()) {

            mv.addObject("inconsistencias", inconsistencias);
        }

        return mv;
    }

    @RequestMapping(value = "/edital_exibe")
    public ModelAndView exibeEdital(HttpServletRequest request, @RequestParam int id) {

        Usuario user = (Usuario) request.getSession().getAttribute("usuario");

        if (user == null) {

            return new ModelAndView("login");
        }

        List<String> inconsistencias = new ArrayList<>();
        RepositorioFacade facade = new RepositorioFacade();
        ModelAndView mv = new ModelAndView("edital_exibe");

        try {

            mv.addObject("edital", facade.obter(id, user));
        } catch (AutorizacaoException aex) {

            return new ModelAndView("login");
        } catch (PrivacidadeException pex) {

            inconsistencias.add(pex.getMessage());
        } catch (DadoInconsistenteException diex) {

            do {

                inconsistencias.add(diex.getMessage());
                diex = diex.getException();
            } while (diex != null);
        }

        if (!inconsistencias.isEmpty()) {

            mv.addObject("inconsistencias", inconsistencias);

        }

        return mv;
    }

    @RequestMapping(value = "/edital_filtra_ajax")
    @ResponseBody
    public List<Edital> filtrarEditais(HttpServletRequest request, @RequestParam(required = true) int id_projeto) {

        Usuario user = (Usuario) request.getSession().getAttribute("usuario");
        RepositorioFacade facade = new RepositorioFacade();

        return facade.filtrarEditais(new Date(), id_projeto);
    }

    @RequestMapping(value = "/inscreve_edital")
    public ModelAndView inscreveProjeto(HttpServletRequest request, @RequestParam int id_projeto, @RequestParam int id_edital, @RequestParam MultipartFile file) {

        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
        RepositorioFacade facade = new RepositorioFacade();
        ModelAndView mv = new ProjetoController().filtraProjetos(request);
        List<String> inconsistencias = new ArrayList<>();

        if (file == null || createArquivo(file) == null) {

            mv.addObject("aviso", "Arquivo não informado! Mesmo assim a inscrição será efetuada!");
        }

        try {

            facade.inscreveEdital(id_edital, id_projeto, createArquivo(file), new Date(), usuario);
        } catch (AutorizacaoException aex) {

            return new ModelAndView("login");
        } catch (PrivacidadeException pex) {

            inconsistencias.add(pex.getMessage());
        } catch (DadoInconsistenteException diex) {

            do {
                inconsistencias.add(diex.getMessage());
                diex = diex.getException();
            } while (diex != null);
        }

        if (!inconsistencias.isEmpty()) {

            mv.addObject("inconsistencias", inconsistencias);
            return mv;
        }

        mv.addObject("sucesso", "Projeto inscrito com sucesso!");
        return mv;
    }

    private Arquivo createArquivo(MultipartFile file) throws DadoInconsistenteException {

        if (file.isEmpty()) {

            return null;
        }

        String titulo = file.getOriginalFilename();
        String extensao = file.getContentType();

        try {

            return new Arquivo(titulo, extensao, file.getBytes());
        } catch (IOException ex) {

            throw new DadoInconsistenteException("Arquivo Inválido!");
        }
    }

    @RequestMapping(value = "/arquivo_edital_download")
    public ModelAndView downloadArquivo(HttpServletRequest request, HttpServletResponse response, int id_edital, int id_arquivo) {

        Usuario user = (Usuario) request.getSession().getAttribute("usuario");
        RepositorioFacade facade = new RepositorioFacade();
        List<String> inconsistencias = new ArrayList<>();
        ModelAndView mv = new ModelAndView("edital_exibe");
        Arquivo arquivo = null;

        try {

            arquivo = facade.obtemArquivoEdital(id_edital, id_arquivo, user);
        } catch (AutorizacaoException aex) {

            return new ModelAndView("login");
        } catch (DadoInconsistenteException diex) {

            do {
                inconsistencias.add(diex.getMessage());
                diex = diex.getException();
            } while (diex != null);
        }

        if (!inconsistencias.isEmpty()) {

            mv.addObject("inconsistencias", inconsistencias);
            return mv;
        }

        iniciaDownload(response, arquivo);

        return null;
    }

    private void iniciaDownload(HttpServletResponse response, Arquivo arquivo) {

        response.reset();
        response.setContentType(arquivo.getExtensao());
        response.setHeader("Content-disposition", "attachment; filename=" + arquivo.getNomeArquivo());
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

    @RequestMapping(value = "/inscricao_lista")
    public ModelAndView listaInscricoes(HttpServletRequest request) {

        ModelAndView mv = new ModelAndView("inscricao_lista");
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");

        try {

            mv.addObject("inscricoes", this.facade.listarInscricoesDoUsuario(usuario));
        } catch (AutorizacaoException aex) {

            return new ModelAndView("login");
        }

        return mv;
    }

    @RequestMapping(value = "/inscricao_cancela")
    public ModelAndView cancelaInscricao(HttpServletRequest request, @RequestParam int id_edital, @RequestParam int id_projeto) {

        ModelAndView mv;
        Usuario user = (Usuario) request.getSession().getAttribute("usuario");
        List<String> inconsistencias = new ArrayList<>();

        try {

            this.facade.cancelaInscricao(id_edital, id_projeto, user, new Date());
        } catch (AutorizacaoException aex) {

            return new ModelAndView("login");
        } catch (PrivacidadeException pex) {

            inconsistencias.add(pex.getMessage());
        } catch (DadoInconsistenteException diex) {

            do {
                inconsistencias.add(diex.getMessage());
                diex = diex.getException();
            } while (diex != null);
        }

        mv = listaInscricoes(request);
        mv.addObject("inconsistencias", inconsistencias);
        return mv;
    }

    @RequestMapping(value = "/down_arquivo_inscricao")
    public ModelAndView downloadArquivoInscricao(HttpServletRequest request, HttpServletResponse response, @RequestParam int id_edital, @RequestParam int id_projeto) {

        ModelAndView mv;
        Usuario user = (Usuario) request.getSession().getAttribute("usuario");
        List<String> inconsistencias = new ArrayList<>();

        try {

            Arquivo arquivo = this.facade.obtemArquivoInscricao(user, id_edital, id_projeto);
            this.iniciaDownload(response, arquivo);
        } catch (AutorizacaoException aex) {

            return new ModelAndView("login");
        } catch (PrivacidadeException pex) {

            inconsistencias.add(pex.getMessage());
        } catch (DadoInconsistenteException diex) {

            do {
                inconsistencias.add(diex.getMessage());
                diex = diex.getException();
            } while (diex != null);
        }

        mv = listaInscricoes(request);
        mv.addObject("inconsistencias", inconsistencias);
        return mv;
    }
}
