/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.us17dao;

import br.com.us17model.ItemProjeto;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Wolmir
 */
public class ItemProjetoMock implements ItemProjetoDAO {
    private List<ItemProjeto> prjs;
    private static ItemProjetoMock instance;
    
    public static String[] generateRandomWords(int numberOfWords) {
        String[] randomStrings = new String[numberOfWords];
        Random random = new Random();
        for (int i = 0; i < numberOfWords; i++) {
            char[] word = new char[random.nextInt(8) + 3]; // words of length 3 through 10. (1 and 2 letter words are boring.)
            for (int j = 0; j < word.length; j++) {
                word[j] = (char) ('a' + random.nextInt(26));
            }
            randomStrings[i] = new String(word);
        }
        return randomStrings;
    }
    
    public static String toPhrase(String[] strs) {
        String ret = "";
        for(String str: strs) {
            ret += str + " ";
        }
        return ret;
    }
    
    private ItemProjetoMock() {
        prjs = new ArrayList<ItemProjeto>();
        for (int i = 0; i < 10; i++) {
            ItemProjeto ip = new ItemProjeto();
            ip.setId(i);
            ip.setProfessor(ItemProjetoMock.toPhrase(ItemProjetoMock.generateRandomWords(2)));
            ip.setResumo(ItemProjetoMock.toPhrase(ItemProjetoMock.generateRandomWords(10)));
            ip.setTitulo(ItemProjetoMock.toPhrase(ItemProjetoMock.generateRandomWords(3)));
            prjs.add(ip);
        }
    }

    @Override
    public List<ItemProjeto> getNHomologados() {
        return this.prjs;
    }

    @Override
    public void homologar(ItemProjeto item_projeto) {
        for (ItemProjeto ip: prjs) {
            if (ip.getId() == item_projeto.getId()) {
                prjs.remove(ip);
                break;
            }
        }
    }
    
    public static ItemProjetoMock getInstance() {
        if (instance == null) {
            instance = new ItemProjetoMock();
        }
        return instance;
    }
}
