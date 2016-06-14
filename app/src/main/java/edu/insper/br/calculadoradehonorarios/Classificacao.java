package edu.insper.br.calculadoradehonorarios;

import java.util.ArrayList;


public class Classificacao {
    protected String nome;
    protected ArrayList<Classificacao> lista;
    protected ArrayList<String> listaDeStrings;


    public String retornaNome() {
        return nome;
    }

    public ArrayList<Classificacao> retornaLista() {
        return lista;
    }

    public Classificacao retornaUltimoDaLista() {
        try {
            return lista.get(lista.size()-1);
        }
        catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<String> retornaListaDeStrings () {
        return listaDeStrings;
    }

    public void adcionarClassificao(Classificacao classificacao) {
        lista.add(classificacao);
    }
}
