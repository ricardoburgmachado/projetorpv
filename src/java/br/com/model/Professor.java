package br.com.model;

/**
 *
 * @author Ricardo
 */
public class Professor extends Usuario {
    
    private String login;
    private String nome;
    private int siape;
    private TipoAutorizacao tipoAut;

    /**
     * @return the id
     */
    public String getLogin() {
        return login;
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
     * @return the siape
     */
    public int getSiape() {
        return siape;
    }

    /**
     * @param siape the siape to set
     */
    public void setSiape(int siape) {
        this.siape = siape;
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
