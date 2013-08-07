/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.model;

/**
 *
 * @author Ricardo
 */
public enum Papel {

    PRO_REITOR {
        @Override
        public String toString() {
            return "PRO_REITOR";
        }
    }, CORDENADOR {
        @Override
        public String toString() {
            return "CORDENADOR";
        }
    }, PROFESSOR {
        @Override
        public String toString() {
            return "PROFESSOR";
        }
    }, ALUNO {
        @Override
        public String toString() {
            return "ALUNO";
        }
    }, EXTERNO {
        @Override
        public String toString() {
            return "EXTERNO";
        }
    };

    public static Papel fromString(String text) {
        if (text != null) {
            for (Papel c : Papel.values()) {
                if (text.equalsIgnoreCase(c.toString())) {
                    return c;
                }
            }
        }
        return null;
    }
}
