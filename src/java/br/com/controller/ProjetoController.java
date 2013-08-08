package br.com.controller;

import br.com.dao.PersistenciaFactoryPostgres;
import br.com.dao.RepositorioProjeto;
import br.com.dao.RepositorioUsuario;
import br.com.model.AreaConhecimento;
import br.com.model.Papel;
import br.com.model.Permissao;
import br.com.model.Projeto;
import br.com.model.TipoProjeto;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProjetoController {

    HttpServletRequest request;

    /**
     * Método que recebe os dados do formulário para efetivar o cadastro de um
     * projeto no banco de dados
     *
     * @return ModelAndView
     */
    @RequestMapping(value = "/projeto_adiciona", method = RequestMethod.POST)
    public ModelAndView projetoAdiciona(@ModelAttribute Projeto p_projeto, HttpServletRequest p_request,
            @RequestParam(value = "arquivo_xx", required = false) MultipartFile arquivo) {

        //Projeto projeto = new Projeto();
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

        //for(int i = 0; i < custeios_val.length; i++){
        //    System.out.println("CUSTEIO val: "+custeios_val[i]);
        //}
        
        //for(int i = 0; i < custeios_desc.length; i++){
        //    System.out.println("CUSTEIO desc: "+custeios_desc[i]);
        //}
        
        //for (int i = 0; i < participantes_aluno.length; i++) {
        //    System.out.println("PARTICIPANTE(ALUNO)[" + i + "] " + participantes_aluno[i]);
        //}
        //for (int i = 0; i < p_projeto.getCusteios().size(); i++) {
        //    System.out.println("CUSTEIO[" + i + "] " + p_projeto.getCusteios().get(i).getValor() + " | DESC: " + p_projeto.getCusteios().get(i).getDescricao());
        //}
        //for (int i = 0; i < p_projeto.getCapitais().size(); i++) {
        //    System.out.println("CAPITAL[" + i + "] " + p_projeto.getCapitais().get(i).getValor() + " | DESC: " + p_projeto.getCapitais().get(i).getDescricao());
        //}
        //System.out.println("SIGILO: " + p_projeto.getSigilo());
        System.out.println("************************* DADOS PROJETO **********************");

        RepositorioProjeto rpProjeto = new PersistenciaFactoryPostgres().createPersistenciaProjeto();

        //System.out.println("RETORNO CADASTRO: "+rpProjeto.inserir(p_projeto));
        int idRetorno = rpProjeto.inserir(p_projeto);

        if (idRetorno != -1) {
            //p_projeto.setCusteios(idRetorno, custeios_val, custeios_desc);
            //p_projeto.setCapitais(idRetorno, capitais_val, capitais_desc);
            
            //rpProjeto.inserirCusteios(p_projeto.getCusteios());
        }

        //System.out.println("QUANTIDADE CUSTEIOS: "+p_projeto.getCusteios().size());
        //for(int i = 0; i < p_projeto.getCusteios().size(); i++){
        //    System.out.println("CUSTEIO TIPO: "+p_projeto.getCusteios().get(i).getTipoCusto());
        //    System.out.print("CUSTEIO VALOR: "+p_projeto.getCusteios().get(i).getValor());
        //    System.out.print("CUSTEIO DESCRICAO: "+p_projeto.getCusteios().get(i).getDescricao());
        //}
        if (!arquivo.isEmpty() && idRetorno != -1) {
            processarArquivo(idRetorno, arquivo);
        }

        //RepositorioProjeto rpProjeto = new PersistenciaFactoryPostgres().createPersistenciaProjeto();
        //System.out.println("CONEXÃO:    "+rpProjeto);
        return new ModelAndView("projeto_adiciona");
    }

    private void processarArquivo(int id, MultipartFile arq) {
        //private void processarArquivo(MultipartFile avatar) {

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

        RepositorioUsuario reposit = new PersistenciaFactoryPostgres().createPersistenciaUsuario();
        RepositorioProjeto repositPro = new PersistenciaFactoryPostgres().createPersistenciaProjeto();
        ModelAndView mv = new ModelAndView("projeto_adiciona");
        mv.addObject("partipantes_aluno", reposit.listar(Papel.ALUNO));
        mv.addObject("partipantes_professor", reposit.listar(Papel.PROFESSOR));
        mv.addObject("area_conhecimento", repositPro.listarAreas());
        mv.addObject("tipo_projeto", TipoProjeto.values());
        return mv;
    }

    /**
     * Método que apenas carrega o formulário para edição de projetos
     *
     * @return ModelAndView
     */
    @RequestMapping(value = "/projeto_edita_show")
    public ModelAndView projetoEditaShow() {

        return new ModelAndView("projeto_edita");
    }

    @RequestMapping(value = "/projeto_edita")
    public ModelAndView projetoEdita() {

        return new ModelAndView("projeto_lista");
    }

}
