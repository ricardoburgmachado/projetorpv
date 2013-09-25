package br.com.controller;

import Exceptions.AutorizacaoException;
import Exceptions.DadoInconsistenteException;
import Exceptions.PersistenciaException;
import Exceptions.PrivacidadeException;
import br.com.repositorio.RepositorioProjeto;
import br.com.repositorio.RepositorioUsuario;
import br.com.model.AreaConhecimento;
import br.com.model.Permissao;
import br.com.model.Professor;
import br.com.model.Projeto;
import br.com.model.StatusProjeto;
import br.com.model.TipoCusto;
import br.com.model.TipoProjeto;
import br.com.model.Usuario;
import br.com.repositorio.RepositorioFacade;
import br.com.repositorio.RepositorioPostgresFactory;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import javax.json.Json;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProjetoController extends GenericController {

    HttpServletRequest request;
    @Autowired(required = true)
    RepositorioProjeto repositorioProjeto;
    RepositorioUsuario repositorioUsuario;

    /**
     * Método que recebe os dados do formulário para efetivar o cadastro de um
     * projeto no banco de dados
     *
     * @return ModelAndView
     */
    @RequestMapping(value = "/projeto_adiciona", method = RequestMethod.POST)
    public ModelAndView projetoAdiciona(@ModelAttribute Projeto p_projeto, HttpServletRequest p_request,
            @RequestParam(value = "arquivo_xx", required = false) MultipartFile arquivo) {

        this.request = p_request;

        Usuario user = (Usuario) request.getSession().getAttribute("usuario");
        if (!verificaAutorizacao(user, Permissao.CRUD_PROJETO)) {

            return new ModelAndView("login");
        }

        //AQUI SERÁ FEITA A INCLUSÃO DOS DADOS DO PROJETO NO BD E RETORNARÁ O ID IDENTIFICADOR DO BD PARA PARA DEFINIR O NOME DO ARQUIVO        
        if (request.getParameter("areaConhecimento_x") != null || request.getParameter("areaConhecimento_x") != "") {
            AreaConhecimento area = new AreaConhecimento();
            area.setId(request.getParameter("areaConhecimento_x"));
            p_projeto.setAreaConhecimento(area);
        }

        String[] custeios_val = request.getParameterValues("custeio_val_x");
        String[] custeios_desc = request.getParameterValues("custeio_desc_x");
        String[] capitais_val = request.getParameterValues("capital_val_x");
        String[] capitais_desc = request.getParameterValues("capital_desc_x");

        String[] participantes_aluno = request.getParameterValues("participantes_aluno");
        String[] participantes_prof = request.getParameterValues("participantes_professor");
        String[] participantes_externo = request.getParameterValues("participantes_externo");

        Professor prof = (Professor) request.getSession().getAttribute("usuario");
        p_projeto.setProfessor(prof);

        //this.repositorioProjeto = new RepositorioPostgresFactory().createRepositorioProjeto();
        //this.repositorioUsuario = new RepositorioPostgresFactory().createRepositorioUsuario();
        //int idRetorno = repositorioProjeto.inserir(p_projeto);
        int idRetorno = -1;

        /**
         * **************************************** *
         */
        List<String> inconsistencias = new LinkedList<>();

        try {
            this.repositorioProjeto = new RepositorioPostgresFactory().createRepositorioProjeto();
            this.repositorioUsuario = new RepositorioPostgresFactory().createRepositorioUsuario();
            idRetorno = repositorioProjeto.inserir(p_projeto);
        } catch (DadoInconsistenteException diex) {
            do {
                inconsistencias.add(diex.getMessage());
                diex = diex.getException();
            } while (diex != null);
        } catch (PersistenciaException pex) {

            inconsistencias.add(pex.getMessage());
        }

        /**
         * **************************************** *
         */
        if (idRetorno != -1) {
            if (capitais_val != null && capitais_desc != null) {
                p_projeto.setCustos(idRetorno, TipoCusto.CAPITAL, capitais_val, capitais_desc);
            }
            if (custeios_val != null && custeios_desc != null) {
                p_projeto.setCustos(idRetorno, TipoCusto.CUSTEIO, custeios_val, custeios_desc);
            }
            repositorioProjeto.inserirCustos(p_projeto.getCustos());

            repositorioProjeto.inserirParticipantes(idRetorno, participantes_aluno);
            repositorioProjeto.inserirParticipantes(idRetorno, participantes_prof);
            repositorioProjeto.inserirParticipantes(idRetorno, participantes_externo);
        }

        if (!arquivo.isEmpty() && idRetorno != -1) {
            processarArquivo(idRetorno, arquivo);
        }

        ModelAndView mv;
        if (inconsistencias.size() > 0) {
            this.repositorioUsuario = new RepositorioPostgresFactory().createRepositorioUsuario();
            this.repositorioProjeto = new RepositorioPostgresFactory().createRepositorioProjeto();
            mv = new ModelAndView("projeto_adiciona");
            mv.addObject("participantes_aluno", repositorioUsuario.listar("ALUNO"));
            mv.addObject("participantes_externo", repositorioUsuario.listar("EXTERNO"));
            mv.addObject("participantes_professor", repositorioUsuario.listar("PROFESSOR"));
            mv.addObject("area_conhecimento", repositorioProjeto.listarAreas());
            mv.addObject("tipo_projeto", TipoProjeto.values());
            mv.addObject("mensagem", inconsistencias);
        } else {
            List projetos = this.repositorioProjeto.listarProjetos(p_projeto.getProfessor().getId());
            mv = new ModelAndView("lista_projeto");
            mv.addObject("mensagem", "Projeto cadastrado com sucesso");
            mv.addObject("projetos", projetos);
        }
        return mv;
    }

    private void processarArquivo(int id, MultipartFile arq) {

        String saveDirectory = this.request.getSession().getServletContext().getRealPath("/arquivos/" + id + "");
        File diretorio = new File(saveDirectory);
        if (!diretorio.exists()) {
            diretorio.mkdirs();
        }
        try {
            FileOutputStream arquivo = new FileOutputStream(diretorio.getAbsolutePath() + "/" + id + ".pdf");
            arquivo.write(arq.getBytes());
            arquivo.flush();
            arquivo.close();

            this.repositorioProjeto = new RepositorioPostgresFactory().createRepositorioProjeto();
            repositorioProjeto.insereArquivo(id);

        } catch (IOException ex) {
        }
    }

    /**
     * Método que apenas carrega o formulário para cadastro de projetos
     *
     * @return ModelAndView
     */
    @RequestMapping(value = "/projeto_adiciona_show")
    public ModelAndView projetoAdicionaShow(HttpServletRequest p_request) {

        this.request = p_request;
        Usuario user = (Usuario) request.getSession().getAttribute("usuario");
        if (!verificaAutorizacao(user, Permissao.CRUD_PROJETO)) {

            return new ModelAndView("login");
        }

        this.repositorioUsuario = new RepositorioPostgresFactory().createRepositorioUsuario();
        this.repositorioProjeto = new RepositorioPostgresFactory().createRepositorioProjeto();
        ModelAndView mv = new ModelAndView("projeto_adiciona");
        mv.addObject("participantes_aluno", repositorioUsuario.listar("ALUNO"));
        mv.addObject("participantes_externo", repositorioUsuario.listar("EXTERNO"));
        mv.addObject("participantes_professor", repositorioUsuario.listar("PROFESSOR"));
        mv.addObject("area_conhecimento", repositorioProjeto.listarAreas());
        mv.addObject("tipo_projeto", TipoProjeto.values());
        return mv;
    }

    /**
     * Método que apenas carrega o formulário para edição de projetos, com os
     * campos preenchidos
     *
     * @param id
     * @return ModelAndView
     */
    @RequestMapping(value = "/projeto_edita_show")
    public ModelAndView projetoEditaShow(HttpServletRequest p_request, @RequestParam int id) {

        this.request = p_request;
        System.out.println("************** ID VINDA POR PARAMETRO: " + id);

        Usuario user = (Usuario) request.getSession().getAttribute("usuario");
        if (!verificaAutorizacao(user, Permissao.CRUD_PROJETO)) {

            return new ModelAndView("login");
        }

        this.repositorioProjeto = new RepositorioPostgresFactory().createRepositorioProjeto();
        this.repositorioUsuario = new RepositorioPostgresFactory().createRepositorioUsuario();

        Projeto projetoBD = this.repositorioProjeto.obter(id);

        projetoBD.setParticipantesAluno(repositorioProjeto.getParticAlunos(id));
        projetoBD.setParticipantesProfessor(repositorioProjeto.getParticProfessores(id));
        projetoBD.setParticipantesExterno(repositorioProjeto.getParticExternos(id));

        ModelAndView mv = new ModelAndView("projeto_edita");

        mv.addObject("projeto", projetoBD);
        mv.addObject("area_conhecimento", repositorioProjeto.listarAreas());
        mv.addObject("custos", repositorioProjeto.getCustos(id));
        mv.addObject("participantes_aluno", repositorioUsuario.listar("ALUNO"));
        mv.addObject("participantes_externo", repositorioUsuario.listar("EXTERNO"));
        mv.addObject("participantes_professor", repositorioUsuario.listar("PROFESSOR"));

        return mv;
    }

    /**
     * Método que recebe e processa os dados de um projeto editado
     * atualizando-os no BD
     *
     * @param p_projeto
     * @param p_request
     * @param arquivo
     * @return
     */
    @RequestMapping(value = "/projeto_edita", method = RequestMethod.POST)
    public ModelAndView projetoEdita(@ModelAttribute Projeto p_projeto, HttpServletRequest p_request,
            @RequestParam(value = "arquivo_xx", required = false) MultipartFile arquivo) {

        this.request = p_request;

        Usuario user = (Usuario) request.getSession().getAttribute("usuario");
        if (!verificaAutorizacao(user, Permissao.CRUD_PROJETO)) {

            return new ModelAndView("login");
        }

        //AQUI SERÁ FEITA A INCLUSÃO DOS DADOS DO PROJETO NO BD E RETORNARÁ O ID IDENTIFICADOR DO BD PARA PARA DEFINIR O NOME DO ARQUIVO                
        AreaConhecimento area = new AreaConhecimento();
        area.setId(request.getParameter("areaConhecimento_x"));
        p_projeto.setAreaConhecimento(area);

        String[] custeios_val = request.getParameterValues("custeio_val_x");
        String[] custeios_desc = request.getParameterValues("custeio_desc_x");
        String[] capitais_val = request.getParameterValues("capital_val_x");
        String[] capitais_desc = request.getParameterValues("capital_desc_x");

        String[] participantes_aluno = request.getParameterValues("participantes_aluno");
        String[] participantes_prof = request.getParameterValues("participantes_professor");
        String[] participantes_externo = request.getParameterValues("participantes_externo");

        Professor prof = (Professor) this.request.getSession().getAttribute("usuario");
        p_projeto.setProfessor(prof);

        this.repositorioProjeto = new RepositorioPostgresFactory().createRepositorioProjeto();
        this.repositorioUsuario = new RepositorioPostgresFactory().createRepositorioUsuario();

        if (capitais_val != null && capitais_desc != null) {
            p_projeto.setCustos(p_projeto.getId(), TipoCusto.CAPITAL, capitais_val, capitais_desc);
        }

        if (custeios_val != null && custeios_desc != null) {
            p_projeto.setCustos(p_projeto.getId(), TipoCusto.CUSTEIO, custeios_val, custeios_desc);
        }

        if (participantes_aluno != null) {
            p_projeto.setParticipantesString(participantes_aluno);
        }
        if (participantes_prof != null) {
            p_projeto.setParticipantesString(participantes_prof);
        }
        if (participantes_externo != null) {
            p_projeto.setParticipantesString(participantes_externo);
        }

        /**
         * **************************************** *
         */
        List<String> inconsistencias = new LinkedList<>();

        try {
            repositorioProjeto.editar(p_projeto);
        } catch (DadoInconsistenteException diex) {
            do {
                inconsistencias.add(diex.getMessage());
                diex = diex.getException();
            } while (diex != null);
        } catch (PersistenciaException pex) {

            inconsistencias.add(pex.getMessage());
        }
        /**
         * **************************************** *
         */
        if (!arquivo.isEmpty()) {
            processarArquivo(p_projeto.getId(), arquivo);
        }

        ModelAndView mv;
        if (inconsistencias.size() > 0) {

            Projeto projetoBD = this.repositorioProjeto.obter(p_projeto.getId());

            projetoBD.setParticipantesAluno(repositorioProjeto.getParticAlunos(p_projeto.getId()));
            projetoBD.setParticipantesProfessor(repositorioProjeto.getParticProfessores(p_projeto.getId()));
            projetoBD.setParticipantesExterno(repositorioProjeto.getParticExternos(p_projeto.getId()));
            mv = new ModelAndView("projeto_edita");
            mv.addObject("projeto", projetoBD);
            mv.addObject("area_conhecimento", repositorioProjeto.listarAreas());
            mv.addObject("custos", repositorioProjeto.getCustos(p_projeto.getId()));
            mv.addObject("participantes_aluno", repositorioUsuario.listar("ALUNO"));
            mv.addObject("participantes_externo", repositorioUsuario.listar("EXTERNO"));
            mv.addObject("participantes_professor", repositorioUsuario.listar("PROFESSOR"));
            mv.addObject("mensagem", inconsistencias);
        } else {

            List projetos = this.repositorioProjeto.listarProjetos(p_projeto.getProfessor().getId());
            //mv = new ModelAndView("lista_projeto");
            mv = this.projetoEditaShow(request, p_projeto.getId());
            mv.addObject("projetos", projetos);
            mv.addObject("mensagem", "Projeto editado com sucesso!");
        }

        return mv;
    }

    @RequestMapping(value = "/projeto_lista_show")
    public ModelAndView projetoListaShow(HttpServletRequest p_request) {

        this.request = p_request;
        Usuario user = (Usuario) request.getSession().getAttribute("usuario");
        if (!verificaAutorizacao(user, Permissao.CRUD_PROJETO)) {

            return new ModelAndView("login");
        }

        RepositorioProjeto rp = new RepositorioPostgresFactory().createRepositorioProjeto();
        Professor prof = (Professor) p_request.getSession().getAttribute("usuario");
        List projetos = rp.listarProjetos(prof.getId());
        ModelAndView mv = new ModelAndView("lista_projeto");
        if (projetos != null) {
            mv.addObject("projetos", projetos);
        }
        return mv;
    }
    
    @RequestMapping (value= "/projeto_inscreve_show")
    public ModelAndView filtraProjetos(HttpServletRequest request) throws PersistenciaException{
        
        Usuario user = (Usuario) request.getSession().getAttribute("usuario");
        ModelAndView mv = new ModelAndView("projeto_inscreve");
        RepositorioFacade facade = new RepositorioFacade();
        List<String> inconsistencias = new ArrayList<>();
        
        if(user == null){
            
            return new ModelAndView("login");
        }
        
        try{
            
            Set<StatusProjeto> status = new HashSet<>();
            status.add(StatusProjeto.HOMOLOGADO);
            status.add(StatusProjeto.INSCRITO);
            List<Projeto> projetos = facade.filtrarProjetos(status, user);
            
            mv.addObject("projetos", projetos);
        }catch(AutorizacaoException aex){
            
            return new ModelAndView("login");
        }catch(DadoInconsistenteException diex){
            
            inconsistencias.add(diex.getMessage());
            mv.addObject("inconsistencias", inconsistencias);
            return mv;
        }
        
        return mv;
    }

    @RequestMapping(value = "/projeto_exibe")
    public ModelAndView projetoExibe(HttpServletRequest p_request, @RequestParam int id) {
        System.out.println("************** ID VINDA POR PARAMETRO: " + id);

        this.request = p_request;
        Usuario user = (Usuario) request.getSession().getAttribute("usuario");
        if (!verificaAutorizacao(user, Permissao.CRUD_PROJETO)) {

            return new ModelAndView("login");
        }

        this.repositorioProjeto = new RepositorioPostgresFactory().createRepositorioProjeto();
        Projeto projetoBD = this.repositorioProjeto.obter(id);
        projetoBD.setParticipantesAluno(repositorioProjeto.getParticAlunos(id));
        projetoBD.setParticipantesProfessor(repositorioProjeto.getParticProfessores(id));
        projetoBD.setParticipantesExterno(repositorioProjeto.getParticExternos(id));

        ModelAndView mv = new ModelAndView("projeto_exibe");
        mv.addObject("projeto", projetoBD);
        mv.addObject("custos", repositorioProjeto.getCustos(id));
        return mv;
    }

    @RequestMapping(value = "/projeto_exclui")
    public ModelAndView projetoExclui(@RequestParam int id, HttpServletRequest p_request) {

        this.request = p_request;
        Usuario user = (Usuario) request.getSession().getAttribute("usuario");
        if (!verificaAutorizacao(user, Permissao.CRUD_PROJETO)) {

            return new ModelAndView("login");
        }

        System.out.println("************** ID VINDA POR PARAMETRO: " + id);

        Professor prof = (Professor) request.getSession().getAttribute("usuario");

        this.repositorioProjeto = new RepositorioPostgresFactory().createRepositorioProjeto();

        /**
         * **************************************** *
         */
        List<String> inconsistencias = new LinkedList<>();

        try {
            this.repositorioProjeto.excluir(id);
        } catch (DadoInconsistenteException diex) {
            do {
                inconsistencias.add(diex.getMessage());
                diex = diex.getException();
            } while (diex != null);
        } catch (PersistenciaException pex) {
            inconsistencias.add(pex.getMessage());
        }
        /**
         * **************************************** *
         */
        ModelAndView mv;
        if (inconsistencias.size() > 0) {

            Projeto projetoBD = this.repositorioProjeto.obter(id);
            projetoBD.setParticipantesAluno(repositorioProjeto.getParticAlunos(id));
            projetoBD.setParticipantesProfessor(repositorioProjeto.getParticProfessores(id));
            projetoBD.setParticipantesExterno(repositorioProjeto.getParticExternos(id));

            mv = new ModelAndView("projeto_exibe");
            mv.addObject("projeto", projetoBD);
            mv.addObject("custos", repositorioProjeto.getCustos(id));
            mv.addObject("mensagem", inconsistencias);
        } else {

            List projetos = this.repositorioProjeto.listarProjetos(prof.getId());
            mv = new ModelAndView("lista_projeto");
            mv.addObject("projetos", projetos);
            mv.addObject("mensagem", "Projeto excluído com sucesso!");
        }

        return mv;
    }

    @RequestMapping(value = "/submete_homologacao")
    public ModelAndView submeteProjeto(@RequestParam int idProjeto, HttpServletRequest request) {

        Usuario user = (Usuario) request.getSession().getAttribute("usuario");

        if (!verificaAutorizacao(user, Permissao.SUBMISSAO_HOMOLOGACAO)) {

            return new ModelAndView("login");
        }

        List<String> inconsistencias;
        ModelAndView mv;

        try {

            inconsistencias = submete(idProjeto, user.getId());
        } catch (PrivacidadeException pex) {

            return this.projetoListaShow(request);
        }

        try {

            mv = this.projetoEditaShow(request, idProjeto);
        } catch (PersistenciaException ex) {

            mv = new ModelAndView("projeto_adiciona");
        }

        if (inconsistencias.isEmpty()) {

            mv.addObject("sucesso", "Projeto submetido com sucesso!");
        } else {

            mv.addObject("inconsistencias", inconsistencias);
        }

        return mv;
    }

    private List<String> submete(int idProjeto, int idResponsavel) throws PrivacidadeException {

        List<String> inconsistencias = new LinkedList<>();

        try {

            new RepositorioPostgresFactory().createRepositorioProjeto().submeterHomologacao(idProjeto, idResponsavel);
        } catch (PrivacidadeException pex) {

            throw pex;
        } catch (DadoInconsistenteException diex) {

            do {

                inconsistencias.add(diex.getMessage());
                diex = diex.getException();
            } while (diex != null);
        } catch (PersistenciaException pex) {

            inconsistencias.add(pex.getMessage());
        }

        return inconsistencias;
    }

    private void atribuiCustos(Projeto projeto, TipoCusto tipoCusto, String[] descricoes, String[] valores) {

        if (!verificaNulo(projeto) && !verificaNulo(tipoCusto) && !verificaNulo(descricoes) && !verificaNulo(valores)) {

            projeto.setCustos(projeto.getId(), tipoCusto, valores, descricoes);
        }
    }

    private void atribuiParticipantes(Projeto projeto, String[] participantes) {

        if (!verificaNulo(projeto) && !verificaNulo(participantes)) {

            projeto.setParticipantesString(participantes);
        }
    }

    private boolean verificaNulo(Object obj) {

        return obj == null;
    }
}
