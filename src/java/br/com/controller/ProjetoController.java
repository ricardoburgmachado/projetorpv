package br.com.controller;

import Exceptions.AutorizacaoException;
import Exceptions.DadoInconsistenteException;
import Exceptions.PersistenciaException;
import Exceptions.PrivacidadeException;
import br.com.repositorio.RepositorioProjeto;
import br.com.repositorio.RepositorioUsuario;
import br.com.model.AreaConhecimento;
import br.com.model.Arquivo;
import br.com.model.Permissao;
import br.com.model.ProReitor;
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
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
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
    RepositorioFacade facade;

    public ProjetoController() {

        this.facade = new RepositorioFacade();
    }

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
        p_projeto.setInicio(ConversaoUtil.stringToDate(request.getParameter("inicio_projeto")));
        p_projeto.setFim(ConversaoUtil.stringToDate(request.getParameter("fim_projeto")));

        int idRetorno = -1;
        List<String> inconsistencias = new LinkedList<>();

        try {
            this.repositorioProjeto = new RepositorioPostgresFactory().createRepositorioProjeto();            
            idRetorno = repositorioProjeto.inserir(p_projeto);
        } catch (DadoInconsistenteException diex) {
            do {
                inconsistencias.add(diex.getMessage());
                diex = diex.getException();
            } while (diex != null);
        } catch (PersistenciaException pex) {

            inconsistencias.add(pex.getMessage());
        }

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
            mv = this.projetoAdicionaShow(p_request);
            mv.addObject("mensagem", inconsistencias);
        } else {
            mv = this.projetoListaShow(p_request);
            mv.addObject("mensagem", "Projeto cadastrado com sucesso");
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
     * @param p_request
     * @param id
     * @return ModelAndView
     */
    @RequestMapping(value = "/projeto_edita_show")
    public ModelAndView projetoEditaShow(HttpServletRequest p_request, @RequestParam int id) {

        this.request = p_request;

        ModelAndView mv = new ModelAndView("projeto_edita");
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
        Projeto projeto;

        try {

            projeto = facade.obtemProjeto(usuario, id);
            mv.addObject("projeto", projeto);
            mv.addObject("area_conhecimento", facade.listarAreas());
            mv.addObject("custos", projeto.getCustos());
            mv.addObject("participantes_aluno", facade.listarParticipantes("ALUNO"));
            mv.addObject("participantes_externo", facade.listarParticipantes("EXTERNO"));
            mv.addObject("participantes_professor", facade.listarParticipantes("PROFESSOR"));
        } catch (AutorizacaoException aex) {

            return new ModelAndView("login");
        }

        if (!projeto.getStatus().equals(StatusProjeto.CRIADO) && !projeto.getStatus().equals(StatusProjeto.SUBMETIDO_HOMOLOGACAO)) {

            mv.setViewName("projeto_exec_edita");
        }

        return mv;
    }

    @RequestMapping (value = "/projeto_exec_edita")
    public ModelAndView editaProjetoEmExecucao(HttpServletRequest request, @ModelAttribute Projeto p_projeto) {

        Usuario user = (Usuario) request.getSession().getAttribute("usuario");
        List<String> inconsistencias = new ArrayList<>();
        ModelAndView mv = this.projetoListaShow(request);

        try {

            Projeto projeto = this.facade.obtemProjeto(user, p_projeto.getId());
            projeto.setResumo(p_projeto.getResumo());
            projeto.setPalavrasChave(p_projeto.getPalavrasChave());
            projeto.setParticipantes(getParticipantes(request));
            
            this.facade.editaProjetoExec(projeto, user);

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

        if(!inconsistencias.isEmpty()){
            
            mv.addObject("inconsistencias", inconsistencias);
            mv.setViewName("projeto_exec_edita");
        }
        
        return mv;
    }

    private List<Usuario> getParticipantes(HttpServletRequest request) {

        List<Usuario> participantes = new ArrayList<>();

        String[] participantes_aluno = request.getParameterValues("participantes_aluno");
        String[] participantes_prof = request.getParameterValues("participantes_professor");
        String[] participantes_externo = request.getParameterValues("participantes_externo");
        
        participantes.addAll(criaListaParticipantes(participantes_prof));
        participantes.addAll(criaListaParticipantes(participantes_aluno));
        participantes.addAll(criaListaParticipantes(participantes_externo));

        return participantes;
    }

    private List<Usuario> criaListaParticipantes(String[] codParticipantes) {

        List<Usuario> lista = new ArrayList<>();
        
        if(codParticipantes == null){
            
            return lista;
        }

        for (String codParticipante : codParticipantes) {
            lista.add(new Usuario(Integer.parseInt(codParticipante)) {
            });
        }

        return lista;
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
        p_projeto.setInicio(ConversaoUtil.stringToDate(request.getParameter("inicio_projeto")));
        p_projeto.setFim(ConversaoUtil.stringToDate(request.getParameter("fim_projeto")));

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
            
            mv = this.projetoEditaShow(p_request, p_projeto.getId());
            mv.addObject("mensagem", inconsistencias);
        } else {
            
            mv = this.projetoListaShow(p_request);
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
        List projetos = rp.listarProjetos(user.getId());
        ModelAndView mv = new ModelAndView("lista_projeto");
        if (projetos != null) {
            mv.addObject("projetos", projetos);
        }
        return mv;
    }

    @RequestMapping(value = "/projeto_inscreve_show")
    public ModelAndView filtraProjetos(HttpServletRequest request) throws PersistenciaException {

        Usuario user = (Usuario) request.getSession().getAttribute("usuario");
        ModelAndView mv = new ModelAndView("projeto_inscreve");
        RepositorioFacade facade = new RepositorioFacade();
        List<String> inconsistencias = new ArrayList<>();

        if (user == null) {

            return new ModelAndView("login");
        }

        try {

            Set<StatusProjeto> status = new HashSet<>();
            status.add(StatusProjeto.HOMOLOGADO);
            status.add(StatusProjeto.INSCRITO);
            List<Projeto> projetos = facade.filtrarProjetosParaInscricao(status, user);


            mv.addObject("projetos", projetos);
        } catch (AutorizacaoException aex) {

            return new ModelAndView("login");
        } catch (DadoInconsistenteException diex) {

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

        this.repositorioProjeto = new RepositorioPostgresFactory().createRepositorioProjeto();
        Projeto projetoBD = facade.obtemProjeto(user, id);

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

            Projeto projetoBD = facade.obtemProjeto(user, id);
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

            inconsistencias = submete(idProjeto, user);
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

    private List<String> submete(int idProjeto, Usuario usuario) throws PrivacidadeException {

        List<String> inconsistencias = new LinkedList<>();

        try {

            new RepositorioPostgresFactory().createRepositorioProjeto().submeterHomologacao(idProjeto, usuario);
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

    @RequestMapping(value = "/projeto_lista_submetidos")
    public ModelAndView listaProjetosSubmetidos(HttpServletRequest request) {

        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
        ModelAndView mv = new ModelAndView("lista_projeto_coord");

        HashSet<StatusProjeto> status = new HashSet<>();
        status.add(StatusProjeto.SUBMETIDO_HOMOLOGACAO);

        try {

            mv.addObject("projetos", facade.listarProjetosSubmetidos(usuario, status));
        } catch (AutorizacaoException aex) {

            return new ModelAndView("login");
        }

        return mv;
    }

    @RequestMapping(value = "/projeto_altera_status_show")
    public ModelAndView projetoAlteraStatusShow(HttpServletRequest p_request, @RequestParam int id) {

        this.request = p_request;
        System.out.println("************** ID VINDA POR PARAMETRO: " + id);

        Usuario user = (Usuario) request.getSession().getAttribute("usuario");
        if (!verificaAutorizacao(user, Permissao.ALTERAR_STATUS_PROJETO)) {

            return new ModelAndView("login");
        }

        this.repositorioProjeto = new RepositorioPostgresFactory().createRepositorioProjeto();
        this.repositorioUsuario = new RepositorioPostgresFactory().createRepositorioUsuario();

        Projeto projetoBD = this.repositorioProjeto.obter(id);

        ModelAndView mv = new ModelAndView("projeto_altera_status");
        mv.addObject("projeto", projetoBD);

        return mv;
    }

    @RequestMapping(value = "/projeto_altera_status", method = RequestMethod.POST)
    public ModelAndView projetoAlteraStatus(@ModelAttribute Projeto p_projeto, HttpServletRequest p_request,
            @RequestParam(value = "arquivo_xx", required = false) MultipartFile arquivo) throws IOException {

        this.request = p_request;

        Usuario user = (Usuario) request.getSession().getAttribute("usuario");
        if (!verificaAutorizacao(user, Permissao.ALTERAR_STATUS_PROJETO)) {
            return new ModelAndView("login");
        }

        this.repositorioProjeto = new RepositorioPostgresFactory().createRepositorioProjeto();
        this.repositorioUsuario = new RepositorioPostgresFactory().createRepositorioUsuario();

        if (!arquivo.isEmpty()) {
            String[] split = arquivo.getOriginalFilename().split("\\.");
            Arquivo arq = new Arquivo(arquivo.getName(), arquivo.getContentType(), arquivo.getBytes());
            p_projeto.setRespaldo(arq);
        }

        boolean status = Boolean.valueOf(request.getParameter("status_x"));

        if (status) {
            p_projeto.setStatus(StatusProjeto.HOMOLOGADO);
        } else {
            p_projeto.setStatus(StatusProjeto.CRIADO);
        }

        List<String> inconsistencias = new LinkedList<>();

        try {
            repositorioProjeto.alteraStatusProjeto(p_projeto);
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
            this.repositorioProjeto = new RepositorioPostgresFactory().createRepositorioProjeto();
            Projeto projetoBD = this.repositorioProjeto.obter(p_projeto.getId());
            mv = new ModelAndView("projeto_altera_status");
            mv.addObject("projeto", projetoBD);
            mv.addObject("mensagem", inconsistencias);
        } else {
            this.repositorioProjeto = new RepositorioPostgresFactory().createRepositorioProjeto();
            Projeto projetoBD = this.repositorioProjeto.obter(p_projeto.getId());
            mv = new ModelAndView("projeto_altera_status");
            mv.addObject("projeto", projetoBD);
            mv.addObject("mensagem", "Status do projeto alterado com sucesso!");
        }

        return mv;
    }

    @RequestMapping(value = "/projeto_presta_contas_show")
    public ModelAndView projetoPrestaContasShow(HttpServletRequest p_request, @RequestParam int id) {

        this.request = p_request;
        System.out.println("************** ID VINDA POR PARAMETRO: " + id);

        Usuario user = (Usuario) request.getSession().getAttribute("usuario");
        //if (!verificaAutorizacao(user, Permissao.ALTERAR_STATUS_PROJETO)) {

        //    return new ModelAndView("login");
        //}
        this.repositorioProjeto = new RepositorioPostgresFactory().createRepositorioProjeto();
        this.repositorioUsuario = new RepositorioPostgresFactory().createRepositorioUsuario();

        Projeto projetoBD = this.repositorioProjeto.obter(id);

        ModelAndView mv = new ModelAndView("projeto_presta_contas");
        mv.addObject("projeto", projetoBD);

        return mv;
    }

    @RequestMapping(value = "/projeto_presta_contas", method = RequestMethod.POST)
    public ModelAndView projetoPrestaContas(@ModelAttribute Projeto p_projeto, HttpServletRequest p_request,
            @RequestParam(value = "arquivo_xx", required = false) MultipartFile arquivo) throws IOException {

        this.request = p_request;

        System.out.println(" *********************** CAIU EM PROJETO PRESTA CONTAS");

        Usuario user = (Usuario) request.getSession().getAttribute("usuario");
        if (!verificaAutorizacao(user, Permissao.PRESTAR_CONTAS)) {
            return new ModelAndView("login");
        }

        this.repositorioProjeto = new RepositorioPostgresFactory().createRepositorioProjeto();
        this.repositorioUsuario = new RepositorioPostgresFactory().createRepositorioUsuario();

        if (!arquivo.isEmpty()) {
            String[] split = arquivo.getOriginalFilename().split("\\.");
            Arquivo arq = new Arquivo(arquivo.getName(), arquivo.getContentType(), arquivo.getBytes());
            p_projeto.setPrestacaoConta(arq);
        }

        List<String> inconsistencias = new LinkedList<>();

        try {
            repositorioProjeto.prestaContas(p_projeto);
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
            this.repositorioProjeto = new RepositorioPostgresFactory().createRepositorioProjeto();
            Projeto projetoBD = this.repositorioProjeto.obter(p_projeto.getId());
            mv = new ModelAndView("projeto_presta_contas");
            mv.addObject("projeto", projetoBD);
            mv.addObject("mensagem", inconsistencias);
        } else {
            this.repositorioProjeto = new RepositorioPostgresFactory().createRepositorioProjeto();
            List projetos = repositorioProjeto.listarProjetos(user.getId());
            mv = new ModelAndView("lista_projeto");
            mv.addObject("projetos", projetos);
            mv.addObject("mensagem", "Prestação de contas realizada com sucesso!");
        }

        return mv;
    }

    @RequestMapping(value = "/projetos_registrados_lista_show")
    public ModelAndView projetosRegistradosListaShow(HttpServletRequest p_request) {

        this.request = p_request;
        //Usuario user = (Usuario) request.getSession().getAttribute("usuario");
        ProReitor user = (ProReitor) request.getSession().getAttribute("usuario");
        if(!verificaAutorizacao(user, Permissao.ACESSAR_PROJ_REGISTRADOS)) {

            return new ModelAndView("login");
        }
        RepositorioProjeto rp = new RepositorioPostgresFactory().createRepositorioProjeto();
        List projetos = rp.listarProjetoInscritos(user.getArea().toString());
        ModelAndView mv = new ModelAndView("projetos_registrados_lista");
        if (projetos != null) {
            mv.addObject("projetos", projetos);
        }
        return mv;
    }

    @RequestMapping(value = "/projeto_lista_altera_status")
    public ModelAndView projetoListaAlteraStatus(HttpServletRequest p_request) {

        this.request = p_request;
        Usuario user = (Usuario) request.getSession().getAttribute("usuario");
        if (!verificaAutorizacao(user, Permissao.ALTERAR_STATUS_PROJETO)) {

            return new ModelAndView("login");
        }

        RepositorioProjeto rp = new RepositorioPostgresFactory().createRepositorioProjeto();
        List projetos = rp.listarProjetos();
        ModelAndView mv = new ModelAndView("projeto_lista_altera_status");
        if (projetos != null) {
            mv.addObject("projetos", projetos);
        }
        return mv;
    }

}
