package br.com.controller;

import Exceptions.DadoInconsistenteException;
import Exceptions.PersistenciaException;
import br.com.repositorio.RepositorioProjeto;
import br.com.repositorio.RepositorioUsuario;
import br.com.model.AreaConhecimento;
import br.com.model.Professor;
import br.com.model.Projeto;
import br.com.model.TipoCusto;
import br.com.model.TipoProjeto;
import br.com.repositorio.RepositorioPostgresFactory;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
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
public class ProjetoController {

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

        //AQUI SERÁ FEITA A INCLUSÃO DOS DADOS DO PROJETO NO BD E RETORNARÁ O ID IDENTIFICADOR DO BD PARA PARA DEFINIR O NOME DO ARQUIVO        
        //int idRetornadoBD = 1;//SIMULADO
        System.out.println("************************* DADOS PROJETO **********************");

        System.out.println("ID: " + p_projeto.getId());
        System.out.println("TITULO: " + p_projeto.getTitulo());
        System.out.println("PALAVRAS CHAVE: " + p_projeto.getPalavrasChave());
        System.out.println("RESUMO: " + p_projeto.getResumo());
        //System.out.println("PARTICIPANTES(alunos): " + p_projeto.getParticipantes());
        //System.out.println("PARTICIPANTES(professores): " + p_projeto.getParticipantes());

        AreaConhecimento area = new AreaConhecimento();
        area.setId(request.getParameter("areaConhecimento_x"));

        //System.out.println("DEBUG AREA CONHECIMENTO: "+request.getParameter("areaConhecimento_x"));
        p_projeto.setAreaConhecimento(area);
        System.out.println("ÁREA CONHECIMENTO: " + p_projeto.getAreaConhecimento());
        System.out.println("TIPO PROJETO: " + p_projeto.getTipoProjeto());

        String[] custeios_val = request.getParameterValues("custeio_val_x");
        String[] custeios_desc = request.getParameterValues("custeio_desc_x");
        String[] capitais_val = request.getParameterValues("capital_val_x");
        String[] capitais_desc = request.getParameterValues("capital_desc_x");


        System.out.println("*********************** CAPITAIS VALOR: " + capitais_val);


        String[] participantes_aluno = request.getParameterValues("participantes_aluno");
        String[] participantes_prof = request.getParameterValues("participantes_professor");
        String[] participantes_externo = request.getParameterValues("participantes_externo");

        System.out.println("************************* DADOS PROJETO **********************");

        this.repositorioProjeto = new RepositorioPostgresFactory().createRepositorioProjeto();
        this.repositorioUsuario = new RepositorioPostgresFactory().createRepositorioUsuario();

        int idRetorno = repositorioProjeto.inserir(p_projeto);

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

        //System.out.println("QUANTIDADE CUSTEIOS: "+p_projeto.getCusteios().size());
        //for(int i = 0; i < p_projeto.getCusteios().size(); i++){
        //    System.out.println("CUSTEIO TIPO: "+p_projeto.getCusteios().get(i).getTipoCusto());
        //    System.out.print("CUSTEIO VALOR: "+p_projeto.getCusteios().get(i).getValor());
        //    System.out.print("CUSTEIO DESCRICAO: "+p_projeto.getCusteios().get(i).getDescricao());
        //}
        //RepositorioProjeto rpProjeto = new PersistenciaFactoryPostgres().createPersistenciaProjeto();
        //System.out.println("CONEXÃO:    "+rpProjeto);
        //return new ModelAndView("projeto_adiciona");
        //RepositorioUsuario reposit = new PersistenciaFactoryPostgres().createPersistenciaUsuario();
        //RepositorioProjeto repositPro = new PersistenciaFactoryPostgres().createPersistenciaProjeto();
        ModelAndView mv = new ModelAndView("projeto_adiciona");
        mv.addObject("participantes_aluno", repositorioUsuario.listar("ALUNO"));
        mv.addObject("participantes_externo", repositorioUsuario.listar("EXTERNO"));
        mv.addObject("participantes_professor", repositorioUsuario.listar("PROFESSOR"));
        mv.addObject("area_conhecimento", repositorioProjeto.listarAreas());
        mv.addObject("tipo_projeto", TipoProjeto.values());
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
    public ModelAndView projetoAdicionaShow() {

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
    public ModelAndView projetoEditaShow(@RequestParam int id) {

        System.out.println("************** ID VINDA POR PARAMETRO: " + id);

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

        //AQUI SERÁ FEITA A INCLUSÃO DOS DADOS DO PROJETO NO BD E RETORNARÁ O ID IDENTIFICADOR DO BD PARA PARA DEFINIR O NOME DO ARQUIVO        
        //int idRetornadoBD = 1;//SIMULADO
        System.out.println("************************* DADOS PROJETO **********************");

        System.out.println("ID: " + p_projeto.getId());
        System.out.println("TITULO: " + p_projeto.getTitulo());
        System.out.println("PALAVRAS CHAVE: " + p_projeto.getPalavrasChave());
        System.out.println("RESUMO: " + p_projeto.getResumo());
        //System.out.println("PARTICIPANTES(alunos): " + p_projeto.getParticipantes());
        //System.out.println("PARTICIPANTES(professores): " + p_projeto.getParticipantes());

        AreaConhecimento area = new AreaConhecimento();
        area.setId(request.getParameter("areaConhecimento_x"));

        //System.out.println("DEBUG AREA CONHECIMENTO: "+request.getParameter("areaConhecimento_x"));
        p_projeto.setAreaConhecimento(area);
        System.out.println("ÁREA CONHECIMENTO: " + p_projeto.getAreaConhecimento());
        System.out.println("TIPO PROJETO: " + p_projeto.getTipoProjeto());

        String[] custeios_val = request.getParameterValues("custeio_val_x");
        String[] custeios_desc = request.getParameterValues("custeio_desc_x");
        String[] capitais_val = request.getParameterValues("capital_val_x");
        String[] capitais_desc = request.getParameterValues("capital_desc_x");

        String[] participantes_aluno = request.getParameterValues("participantes_aluno");
        String[] participantes_prof = request.getParameterValues("participantes_professor");
        String[] participantes_externo = request.getParameterValues("participantes_externo");

        System.out.println("************************* DADOS PROJETO **********************");

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
            //repositorioProjeto.inserirParticipantes(p_projeto.getId(), participantes_aluno);            
        }
        if (participantes_prof != null) {
            p_projeto.setParticipantesString(participantes_prof);
            //repositorioProjeto.inserirParticipantes(p_projeto.getId(), participantes_prof);

        }
        if (participantes_externo != null) {
            p_projeto.setParticipantesString(participantes_externo);
            //repositorioProjeto.inserirParticipantes(p_projeto.getId(), participantes_externo);        
        }

        repositorioProjeto.editar(p_projeto);

        if (!arquivo.isEmpty()) {
            processarArquivo(p_projeto.getId(), arquivo);
        }

        ModelAndView mv = new ModelAndView("projeto_adiciona");
        mv.addObject("participantes_aluno", repositorioUsuario.listar("ALUNO"));
        mv.addObject("participantes_externo", repositorioUsuario.listar("EXTERNO"));
        mv.addObject("participantes_professor", repositorioUsuario.listar("PROFESSOR"));
        mv.addObject("area_conhecimento", repositorioProjeto.listarAreas());
        mv.addObject("tipo_projeto", TipoProjeto.values());
        return mv;
    }

    @RequestMapping(value = "/projeto_lista_show")
    public ModelAndView projetoListaShow() {

        return new ModelAndView("lista_projeto_teste");
    }

    @RequestMapping(value = "/submete_homologacao")
    public ModelAndView submeteProjeto(@RequestParam int id) {

        List<String> inconsistencias = new LinkedList<>();

        try {

            new RepositorioPostgresFactory().createRepositorioProjeto().submeterHomologacao(id);
        } catch (DadoInconsistenteException diex) {

            do {
                
                inconsistencias.add(diex.getMessage());
                diex = diex.getException();
            } while (diex != null);
        } catch(PersistenciaException pex){
            
            inconsistencias.add(pex.getMessage());
        }
        
        ModelAndView mv = this.projetoEditaShow(id);
        if(inconsistencias.isEmpty()){
            
            mv.addObject("sucesso", "Projeto submetido com sucesso!");
        }else{
            
            mv.addObject("inconsistencias", inconsistencias);
        }
        
        return mv;
    }

    private Projeto criaProjeto(HttpServletRequest request) {

        Projeto projeto = new Projeto();

        projeto.setId(Integer.parseInt(request.getParameter("id")));
        projeto.setTitulo(request.getParameter("titulo"));
        projeto.setResumo(request.getParameter("resumo"));
        projeto.setPalavrasChave(request.getParameter("palavrasChave"));
        projeto.setTipoProjeto(TipoProjeto.fromString("tipoProjeto"));
        projeto.setAreaConhecimento(new AreaConhecimento(Integer.valueOf(request.getParameter("areaConhecimento_x")), null));
        projeto.setProfessor((Professor) request.getSession().getAttribute("usuario"));
        projeto.setSigilo(Boolean.valueOf(request.getParameter("sigilo")));

        atribuiParticipantes(projeto, request.getParameterValues("participantes_aluno"));
        atribuiParticipantes(projeto, request.getParameterValues("participantes_professor"));
        atribuiParticipantes(projeto, request.getParameterValues("participantes_externo"));

        atribuiCustos(projeto, TipoCusto.CUSTEIO, request.getParameterValues("custeio_desc_x"), request.getParameterValues("custeio_val_x"));
        atribuiCustos(projeto, TipoCusto.CUSTEIO, request.getParameterValues("capital_desc_x"), request.getParameterValues("capital_val_x"));

        return projeto;
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
