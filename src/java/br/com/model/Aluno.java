package br.com.model;

/**
 *
 * @author Ricardo
 */
public class Aluno extends Usuario{
    
    private String login;
    private String nome;
    private int matricula;
    private String senha;
    private TipoAutorizacao tipoAut;
    
    /**
     * @return the id
     */
    public String getLogin() {
        return this.login;
    }

    /**
     * @param id the id to set
     */
    public void setLogin(String l) {
        this.login = l;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the matricula
     */
    public int getMatricula() {
        return matricula;
    }

    /**
     * @param matricula the matricula to set
     */
    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    /**
     * @return the senha
     */
    public String getSenha() {
        return senha;
    }

    /**
     * @param senha the senha to set
     */
    public void setSenha(String senha) {
        this.senha = senha;
    }

    /**
     * @return the tipoAut
     */
    public TipoAutorizacao getTipoAut() {
        return tipoAut;
    }

    /**
     * @param tipoAut the tipoAut to set
     */
    public void setTipoAut(TipoAutorizacao tipoAut) {
        this.tipoAut = tipoAut;
    }
    
}
