package br.com.model;

public enum Permissao {

    CRUD_PROJETO, ACESSO, SUBMISSAO_HOMOLOGACAO, CRUD_EDITAL, 
    INSCRICAO_EDITAL, EXIBE_EDITAL, LISTAGEM_SUBMETIDOS, CANCELAMENTO_INSCRICAO, LISTAGEM_INSCRICOES,
    PRESTAR_CONTAS, DOWNLOAD_EDITAL, ALTERAR_STATUS_PROJETO, CONTEMPLACAO_PROJETO;

    
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
