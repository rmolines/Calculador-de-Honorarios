package edu.insper.br.calculadoradehonorarios;

import java.util.ArrayList;

/**
 * Created by Khalil on 22/05/2016.
 */

public class  Grupo extends Classificacao {

    public Grupo(String categoria, int posicao) {
        lista = new ArrayList<>();
        nome = categoria.substring(categoria.indexOf(")") + 1);
    }
}