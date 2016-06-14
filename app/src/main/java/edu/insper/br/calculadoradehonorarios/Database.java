package edu.insper.br.calculadoradehonorarios;

import android.content.res.AssetManager;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Rafael on 6/13/2016.
 */
public class Database {
    private static Database instance = null;
    private ArrayList<Classificacao> listaDeCategorias;
    private ArrayList<Classificacao> somaDeProcedimentos;
    private ArrayList<Classificacao> listaDeFavoritos;

    private Classificacao classificacaoAtual;

    private Database () {
        somaDeProcedimentos = new ArrayList<>();
        listaDeFavoritos = new ArrayList<>();
    }

    public static Database getInstance () {
        if (instance==null) {
            instance = new Database();
        }

        return instance;
    }

    public void criaListaDeCategorias (AssetManager assetManager) {
        CSVReader csvReader = new CSVReader(assetManager);
        listaDeCategorias = csvReader.retornaListaDeCategorias();
    }

    public ArrayList<Classificacao> retornaListaDeCategorias () {
        return listaDeCategorias;
    }

    public void adcionaProcedimento (Classificacao procedimento) {
        somaDeProcedimentos.add(procedimento);
    }

    public ArrayList<Classificacao> retornaSomaDeProcedimentos () {
        return somaDeProcedimentos;
    }

    public void apagaProcedimentos () {
        somaDeProcedimentos.clear();
    }

    public void adcionaAosFavoritos (Classificacao procedimento) {
        listaDeFavoritos.add(procedimento);
        for (String i: procedimento.retornaListaDeStrings()) {
            Log.i("string", i);
        }
    }

    public ArrayList<Classificacao> retornaListaDeFavoritos () {
        return listaDeFavoritos;
    }

    public void apagaFavoritos () {
        listaDeFavoritos.clear();
    }

    public void mudaClassificacaoAtual (Classificacao classificacaoAtual) {
        this.classificacaoAtual = classificacaoAtual;
    }

    public Classificacao retornaClassificaoAtual () {
        return classificacaoAtual;
    }

}
