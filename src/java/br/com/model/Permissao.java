package br.com.model;

public enum Permissao {

    CRUD_PROJETO, ACESSO, SUBMISSAO_HOMOLOGACAO;
    
    public static Permissao fromString(String text) {
        if (text != null) {
            for (Permissao c : Permissao.values()) {
                if (text.equalsIgnoreCase(c.toString())) {
                    return c;
                }
            }
        }
        return null;
    }
}
