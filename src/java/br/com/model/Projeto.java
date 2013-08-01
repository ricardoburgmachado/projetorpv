
package br.com.model;

import java.util.ArrayList;
import java.util.Map;

public class Projeto {
    
    private int id;
    private String titulo;
    private String palavrasChave;
    private String resumo;
    private TipoProjeto tipoProjeto;
    private ArrayList participantes;
    private Professor professor;
    private String arquivo;
    private Map custeio;
    private Map capital;
    private String areaConhecimento;
    private Campus campus;

    
    
    
    
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
    public ArrayList getParticipantes() {
        return participantes;
    }

    /**
     * @param participantes the participantes to set
     */
    public void setParticipantes(ArrayList participantes) {
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
    public String getArquivo() {
        return arquivo;
    }

    /**
     * @param arquivo the arquivo to set
     */
    public void setArquivo(String arquivo) {
        this.arquivo = arquivo;
    }

    /**
     * @return the custeio
     */
    public Map getCusteio() {
        return custeio;
    }

    /**
     * @param custeio the custeio to set
     */
    public void setCusteio(Map custeio) {
        this.custeio = custeio;
    }

    /**
     * @return the capital
     */
    public Map getCapital() {
        return capital;
    }

    /**
     * @param capital the capital to set
     */
    public void setCapital(Map capital) {
        this.capital = capital;
    }

    /**
     * @return the areaConhecimento
     */
    public String getAreaConhecimento() {
        return areaConhecimento;
    }

    /**
     * @param areaConhecimento the areaConhecimento to set
     */
    public void setAreaConhecimento(String areaConhecimento) {
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
    
    
}
