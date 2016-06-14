package edu.insper.br.calculadoradehonorarios;

import java.util.ArrayList;

/**
 * Created by Rafael on 5/30/2016.
 */
public class Procedimento extends Classificacao{
    private double valor;
    private int numeroDeAuxiliares;
    private ArrayList<Double> valorAuxiliares = new ArrayList<>();

    public Procedimento (float valor, int numeroDeAuxiliares, String nome) {
        listaDeStrings = new ArrayList<>();
        this.nome = nome;
        listaDeStrings.add("Procedimento: "+nome);
        this.valor = valor;
        listaDeStrings.add("Valor: R$ "+Float.toString(valor));
        this.numeroDeAuxiliares = numeroDeAuxiliares;
        listaDeStrings.add("Numero de Auxiliares: "+Integer.toString(numeroDeAuxiliares));
        calculaValorAuxiliares();

    }

    private void calculaValorAuxiliares () {
        for (int i = 0; i < numeroDeAuxiliares; i++) {
            double valorAuxiliar = 0;
            if (i == 0) {
                valorAuxiliar = valor*0.3;
                listaDeStrings.add("Auxiliar 1: R$ "+ Double.toString(valorAuxiliar));
            }
            if (i == 1) {
                valorAuxiliar = valor*0.2;
                listaDeStrings.add("Auxiliar 2: R$ "+ Double.toString(valorAuxiliar));
            }
            if (i == 2) {
                valorAuxiliar = valor*0.1;
                listaDeStrings.add("Auxiliar 3: R$ "+ Double.toString(valorAuxiliar));
            }
            if (i==3) {
                valorAuxiliar = valor*0.05;
                listaDeStrings.add("Auxiliar 4: R$ "+ Double.toString(valorAuxiliar));
            }
            valorAuxiliares.add(valorAuxiliar);
        }
    }

    public double retornaValor () {
        return valor;
    }

    public String retornaNome() {
        return nome;
    }


    public int retornaNumeroDeAuxiliares () {
        return numeroDeAuxiliares;
    }

    public ArrayList<String> retornaListaCalculo () {
        return listaDeStrings;
    }


}
