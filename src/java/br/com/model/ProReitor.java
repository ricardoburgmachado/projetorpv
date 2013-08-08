package br.com.model;

public class ProReitor extends Usuario{

    private String login;
    private String senha;
    private String nome;
    private Permissao tipoAut;

    /**
     * @return the login
     */
    public String getLogin() {
        return login;
    }

    /**
     * @param login the login to set
     */
    public void setLogin(String login) {
        this.login = login;
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
     * @return the tipoAut
     */
    public Permissao getTipoAut() {
        return tipoAut;
    }

    /**
     * @param tipoAut the tipoAut to set
     */
    public void setTipoAut(Permissao tipoAut) {
        this.tipoAut = tipoAut;
    }

}
