package br.com.model;

import java.util.List;

/**
 *
 * @author rafael
 */
public class Aluno extends Usuario {

    private int matricula;

    public Aluno() {
    }

    public Aluno(String login, String senha, String nome, Campus campus, List<Permissao> permissoes) {
        super(login, senha, nome, campus, permissoes);
    }

    public Aluno(String login, String senha, String nome, Campus campus) {
        super(login, senha, nome, campus);
    }

    public Aluno(String login, String senha) {
        super(login, senha);
    }

    public Aluno(String nome, Campus campus) {
        super(nome, campus);
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
    
    @Override
    public String getPapel(){
        
        return "Aluno";
    }

}
