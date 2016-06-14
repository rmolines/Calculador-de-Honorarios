package edu.insper.br.calculadoradehonorarios;

import java.util.ArrayList;
public class Subcategoria extends Classificacao {

    public Subcategoria (String categoria) {
        nome = categoria.substring(categoria.indexOf("(")+1, categoria.indexOf(")"));
        lista = new ArrayList<>();
    }
}
