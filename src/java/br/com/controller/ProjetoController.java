package br.com.controller;

import br.com.model.Projeto;
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
        int idRetornadoBD = 1;//SIMULADO

        System.out.println("************************* DADOS PROJETO **********************");

        System.out.println("ID: " + p_projeto.getId());
        System.out.println("TITULO: " + p_projeto.getTitulo());
        System.out.println("PALAVRAS CHAVE: " + p_projeto.getPalavrasChave());
        System.out.println("RESUMO: " + p_projeto.getResumo());
        //System.out.println("PARTICIPANTES(alunos): " + p_projeto.getParticipantes());
        //System.out.println("PARTICIPANTES(professores): " + p_projeto.getParticipantes());
        System.out.println("ÁREA CONHECIMENTO: " + p_projeto.getAreaConhecimento());
        System.out.println("TIPO PROJETO: " + p_projeto.getTipoProjeto());

        String[] custeios_val = request.getParameterValues("custeio_val_x");
        String[] custeios_desc = request.getParameterValues("custeio_desc_x");
        String[] capitais_val = request.getParameterValues("capital_val_x");
        String[] capitais_desc = request.getParameterValues("capital_desc_x");

        String[] participantes_aluno = request.getParameterValues("participantes_aluno");
        String[] participantes_prof = request.getParameterValues("participantes_professor");
        
        for (int i = 0; i < participantes_aluno.length; i++) {
            System.out.println("PARTICIPANTE(ALUNO)[" + i + "] " + participantes_aluno[i]);
        }
        
        
        p_projeto.setCusteios(custeios_val, custeios_desc);
        p_projeto.setCapitais(capitais_val, capitais_desc);

        /*
        for (int i = 0; i < p_projeto.getCusteios().size(); i++) {
            System.out.println("CUSTEIO[" + i + "] " + p_projeto.getCusteios().get(i).getValor() + " | DESC: " + p_projeto.getCusteios().get(i).getDescricao());
        }
        for (int i = 0; i < p_projeto.getCapitais().size(); i++) {
            System.out.println("CAPITAL[" + i + "] " + p_projeto.getCapitais().get(i).getValor() + " | DESC: " + p_projeto.getCapitais().get(i).getDescricao());
        }
        */ 
        
        System.out.println("SIGILO: " + p_projeto.getSigilo());

        System.out.println("************************* DADOS PROJETO **********************");

        if (!arquivo.isEmpty() && idRetornadoBD != -1) {
            //processarArquivo(idRetornadoBD, arquivo);
        }

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

        return new ModelAndView("projeto_adiciona");
    }

}
