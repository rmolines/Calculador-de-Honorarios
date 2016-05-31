package edu.insper.br.calculadoradehonorarios;

import java.util.ArrayList;

/**
 * Created by Rafael on 5/30/2016.
 */
public class Procedimento {
    private double valor;
    private int numeroDeAxiliares;
    private String nome;
    private ArrayList<Double> valorAuxiliares;

    public Procedimento (float valor, int numeroDeAxiliares, String nome) {
        this.valor = valor;
        this.nome = nome;
        this.numeroDeAxiliares = numeroDeAxiliares;
        calculaValorAuxiliares();
    }

    private void calculaValorAuxiliares () {
        for (int i = 0; i < numeroDeAxiliares; i++) {
            double valorAuxiliar = 0;
            if (i == 0) {
                valorAuxiliar = valor*0.3;
            }
            if (i == 1) {
                valorAuxiliar = valor*0.2;
            }
            if (i == 2) {
                valorAuxiliar = valor*0.1;
            }
            if (i==3) {
                valorAuxiliar = valor*0.05;
            }
            valorAuxiliares.add(valorAuxiliar);
        }
    }

    public double retornaValor () {
        return valor;
    }

    public String retornaNome () {
        return nome;
    }

    public int retornaNumeroDeAuxiliares () {
        return numeroDeAxiliares;
    }
}
