package edu.insper.br.calculadora2;

import java.util.ArrayList;

public class Categoria {
    private String nome;
    private int posicao;
    private int numeroDeSubcategorias;
    private ArrayList<Subcategoria> subcategorias;
    private ArrayList<ArrayList<Subcategoria>> lista_sub;

    public void setLista_sub(ArrayList<ArrayList<Subcategoria>> lista_sub) {
        this.lista_sub = lista_sub;
    }

    public Categoria (String categoria, int posicao) {
        nome = categoria.substring(0, categoria.indexOf("("));
        char tirar = '"';
        if (nome.charAt(0) == tirar) {
            nome = nome.substring(1);
        }
        this.posicao = posicao;
        numeroDeSubcategorias = 0;
        subcategorias = new ArrayList<>();
        lista_sub = new ArrayList<>();
    }

    public void criarSubcategoria (Subcategoria subcategoria) {
        subcategorias.add(subcategoria);
    }

    public String retornaNomeDaCategoria () {
        return nome;
    }

    public ArrayList<Subcategoria> retornaSubcategorias () {
        return subcategorias;
    }

    public boolean temSubcategoria (Subcategoria subcategoria) {
        boolean temSubcategoria = subcategorias.contains(subcategoria);
        return temSubcategoria;
    }

    public Subcategoria retornaUltimaSubcategoria () {
        try {
            return subcategorias.get(subcategorias.size()-1);
        }
        catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
            return null;
        }
    }
}
