package edu.insper.br.calculadoradehonorarios;

import java.util.ArrayList;

public class Categoria extends Classificacao {

    public Categoria (String categoria) {
        nome = categoria.substring(0, categoria.indexOf("("));
        char tirar = '"';
        if (nome.charAt(0) == tirar) {
            nome = nome.substring(1);
        }
        lista = new ArrayList<>();


    }
}
