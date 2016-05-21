package br.edu.insper.calculadoradehonorarios;

import java.util.ArrayList;

/**
 * Created by Rafael on 5/20/2016.
 */
public class Grupo {
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
