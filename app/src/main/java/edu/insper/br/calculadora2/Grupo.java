package edu.insper.br.calculadora2;

/**
 * Created by Khalil on 22/05/2016.
 */

public class  Grupo {
    private String nome;
    private int posicao;

    public Grupo (String categoria, int posicao) {
        nome = categoria.substring(categoria.indexOf(")")+1);
        this.posicao = posicao;
    }

    public String retornaNomeDoGrupo () {
        return nome;
    }
}
