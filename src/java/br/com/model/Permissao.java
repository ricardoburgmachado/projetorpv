package br.com.model;

public enum Permissao {

    //ADMIN, COMUM, BASICO;
    x, y, CRUD_PROJETO;;
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
