package edu.insper.br.calculadoradehonorarios;

import java.util.ArrayList;

/**
 * Created by Khalil on 22/05/2016.
 */

public class  Grupo {
    private String nome;
    private int posicao;
    private ArrayList<Procedimento> listaDeProcedimentos = new ArrayList<>();

    public Grupo (String categoria, int posicao) {
        nome = categoria.substring(categoria.indexOf(")")+1);
        this.posicao = posicao;
    }

    public String retornaNomeDoGrupo () {
        return nome;
    }


    public int retornaPosicao () {
        return posicao;
    }

    public void adcionaProcedimento (Procedimento procedimento) {
        listaDeProcedimentos.add(procedimento);
    }

    public ArrayList<Procedimento> retornaListaDeProcedimentos () {
        return listaDeProcedimentos;
    }

}