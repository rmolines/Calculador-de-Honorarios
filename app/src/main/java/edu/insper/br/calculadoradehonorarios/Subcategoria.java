package edu.insper.br.calculadoradehonorarios;

import java.util.ArrayList;
public class Subcategoria {
    private String nome;
    private int posicao;
    private int numeroDeGrupos;
    private ArrayList<Grupo> grupos;

    public Subcategoria (String categoria, int posicao) {
        nome = categoria.substring(categoria.indexOf("(")+1, categoria.indexOf(")"));
        this.posicao = posicao;
        numeroDeGrupos = 0;
        grupos = new ArrayList<>();
    }

    public void criarGrupo (Grupo grupo) {
        grupos.add(grupo);
    }

    public String retornaNomeDaSubcategoria () {
        return nome;
    }

    public ArrayList<Grupo> retornaGrupos () {
        return  grupos;
    }

    public boolean temGrupo (Grupo grupo) {

        boolean temGrupo = grupos.contains(grupo);
        return temGrupo;
    }

    public Grupo retornaUltimoGrupo () {
        try {
            return grupos.get(grupos.size()-1);
        }
        catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
            return null;
        }
    }
}
