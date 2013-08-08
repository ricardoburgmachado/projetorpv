package br.com.model;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.web.multipart.MultipartFile;

public class Projeto {

    private int id;
    private String titulo;
    private String palavrasChave;
    private String resumo;
    private TipoProjeto tipoProjeto;
    private ArrayList<Usuario> participantes;
    private Professor professor;
    //private String arquivo;
    private MultipartFile arquivo;

    ArrayList<Custo> custeios = new ArrayList<Custo>();
    ArrayList<Custo> capitais = new ArrayList<Custo>();;

    private AreaConhecimento areaConhecimento;
    private Campus campus;
    private boolean sigilo;

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the titulo
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * @param titulo the titulo to set
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * @return the palavrasChave
     */
    public String getPalavrasChave() {
        return palavrasChave;
    }

    /**
     * @param palavrasChave the palavrasChave to set
     */
    public void setPalavrasChave(String palavrasChave) {
        this.palavrasChave = palavrasChave;
    }

    /**
     * @return the resumo
     */
    public String getResumo() {
        return resumo;
    }

    /**
     * @param resumo the resumo to set
     */
    public void setResumo(String resumo) {
        this.resumo = resumo;
    }

    /**
     * @return the tipoProjeto
     */
    public TipoProjeto getTipoProjeto() {
        return tipoProjeto;
    }

    /**
     * @param tipoProjeto the tipoProjeto to set
     */
    public void setTipoProjeto(TipoProjeto tipoProjeto) {
        this.tipoProjeto = tipoProjeto;
    }

    /**
     * @return the participantes
     */
    public ArrayList<Usuario> getParticipantes() {
        return participantes;
    }

    /**
     * @param participantes the participantes to set
     */
    public void setParticipantes(ArrayList<Usuario> participantes) {
        this.participantes = participantes;
    }

   
    
    /**
     * @return the professor
     */
    public Professor getProfessor() {
        return professor;
    }

    /**
     * @param professor the professor to set
     */
    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    /**
     * @return the arquivo
     */
    public MultipartFile getArquivo() {
        return arquivo;
    }

    /**
     * @param arquivo the arquivo to set
     */
    public void setArquivo(MultipartFile arquivo) {
        this.arquivo = arquivo;
    }

    /**
     * Método utilizado no cadastro de um projeto
     * @param custeioVal
     * @param custeioDesc 
     */
    public void setCusteios(int idProj, String[] custeioVal, String[] custeioDesc ) {
        //this.custeios = new ArrayList<Custo>();
        for (int i = 0; i < custeioVal.length; i++) {
            if(valorValido(custeioVal[i]) && descricaoValida(custeioDesc[i])){
                this.custeios.add(new Custo(idProj, TipoCusto.CUSTEIO, custeioDesc[i], Double.parseDouble(custeioVal[i])));
            }
        } 
    }
    
    public ArrayList<Custo> getCusteios(){
        return this.custeios;
    }
    
    public ArrayList<Custo> getCapitais(){
        return this.capitais;
    }
    
    
    /**
     * Método utilizado no cadastro de um projeto
     * @param custeioVal
     * @param custeioDesc 
     */
    public void setCapitais(int idProj, String[] capitalVal, String[] capitalDesc) {
        //this.capitais = new ArrayList<Custo>();
        for (int i = 0; i < capitalVal.length; i++) {
            if(valorValido(capitalVal[i]) && descricaoValida(capitalDesc[i])){
                this.capitais.add(new Custo(idProj, TipoCusto.CAPITAL, capitalDesc[i], Double.parseDouble(capitalVal[i])));
            }
        } 
    }

    public boolean valorValido(String valor) {
        try {
            double temp = Double.parseDouble(valor);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean descricaoValida(String desc) {       
        if(!desc.isEmpty() && desc != ""){
            return true;
        }else{
            return false;
        }
    }
    
    
    /**
     * @return the areaConhecimento
     */
    public AreaConhecimento getAreaConhecimento() {
        return areaConhecimento;
    }

    /**
     * @param areaConhecimento the areaConhecimento to set
     */
    public void setAreaConhecimento(AreaConhecimento areaConhecimento) {
        this.areaConhecimento = areaConhecimento;
    }

    /**
     * @return the campus
     */
    public Campus getCampus() {
        return campus;
    }

    /**
     * @param campus the campus to set
     */
    public void setCampus(Campus campus) {
        this.campus = campus;
    }

    /**
     * @return the sigilo
     */
    public boolean getSigilo() {
        return sigilo;
    }

    /**
     * @param sigilo the sigilo to set
     */
    public void setSigilo(boolean sigilo) {
        this.sigilo = sigilo;
    }

}
