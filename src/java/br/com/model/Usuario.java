package br.com.model;

import br.com.repositorio.RepositorioEdital;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author rafael
 */
public abstract class Usuario {

    private int id;
    private String login;
    private String senha;
    private String nome;
    private Campus campus;
    private List<Permissao> permissoes;
    private String area;

    public Usuario() {
        
        this.permissoes = new ArrayList<>();

    }

    public Usuario(String login, String senha, String nome, Campus campus, List<Permissao> permissoes) {
        this.login = login;
        this.senha = senha;
        this.nome = nome;
        this.campus = campus;
        this.permissoes = permissoes;
    }

    public Usuario(String login, String senha, String nome, Campus campus) {
        this(login, senha, nome, campus, new LinkedList<Permissao>());
    }

    public Usuario(String login, String senha) {
        this(login, senha, null, null);
    }

    public Usuario(String nome, Campus campus) {
        this(null, null, nome, campus);
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Permissao> getPermissoes() {
        return permissoes;
    }

    public void setPermissoes(List<Permissao> permissoes) {
        this.permissoes = permissoes;
    }

    public void addPermissao(Permissao permissao) {

        this.permissoes.add(permissao);
    }

    public void removePermissao(Permissao permissao) {

        this.permissoes.remove(permissao);
    }

    public Campus getCampus() {
        return campus;
    }

    public void setCampus(Campus campus) {
        this.campus = campus;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Usuario other = (Usuario) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Usuario{" + "login=" + login + ", senha=" + senha + ", nome=" + nome + ", campus=" + campus + ", permissoes=" + permissoes + '}';
    }
    
    public static Usuario instantiateUsuario(String papel){
        
        papel = papel.toLowerCase();
        
        switch(papel){
            
            case "aluno": return new Aluno();
            case "coordenador": return new Coordenador();
            case "pro_reitor": return new ProReitor();
            case "proreitor": return new ProReitor();
            case "professor": return new Professor();
            case "externo": return new Externo();
        }

        return null;
    }

    /**
     * @return the area
     */
    public String getArea() {
        return area;
    }

    /**
     * @param area the area to set
     */
    public void setArea(String area) {
        this.area = area;
    }
}
