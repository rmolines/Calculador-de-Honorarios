package edu.insper.br.calculadoradehonorarios;

/**
 * Created by Khalil on 22/05/2016.
 */

import android.content.res.AssetManager;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class CSVReader {
    private ArrayList<Classificacao> categorias = new ArrayList<>();

    public CSVReader(AssetManager assetManager) {
        run(assetManager);
    }

    public void run (AssetManager assetManager) {

        String csvFile = "tuss.csv";
        BufferedReader br = null;
        String line = "";
        String splitBy = ",";

        int counter = 0;

        try {
            InputStream csvStream = assetManager.open(csvFile);
            BufferedReader reader = new BufferedReader(new InputStreamReader(csvStream, "UTF8"));
            String csvLine;
            int categoriaCounter = 0;
            int subcategoriaCounter = 0;
            int grupoCounter = 0;

            while ((csvLine = reader.readLine()) != null) {

                String[] row = csvLine.split(",");


                if (row.length == 2) {
                    String nomeDaCategoria = row[1];
                    Classificacao categoria = new Categoria(nomeDaCategoria);
                    Classificacao subcategoria = new Subcategoria(nomeDaCategoria);
                    Classificacao grupo = new Grupo(nomeDaCategoria, counter);

                    if (!categorias.isEmpty()) {
                        Classificacao categoriaAtual = categorias.get(categorias.size()-1);
                        if (!categoriaAtual.retornaNome().equals(categoria.retornaNome())) {
                            subcategoria.adcionarClassificao(grupo);
                            categoria.adcionarClassificao(subcategoria);
                            categorias.add(categoria);
                        } else {
                            if (!categoriaAtual.retornaLista().isEmpty()) {
                                Classificacao subcategoriaAtual = categoriaAtual.retornaUltimoDaLista();
                                if (!subcategoriaAtual.retornaNome().equals(subcategoria.retornaNome())) {
                                    subcategoria.adcionarClassificao(grupo);
                                    categoriaAtual.adcionarClassificao(subcategoria);
                                } else {
                                    subcategoriaAtual.adcionarClassificao(grupo);
                                }
                            }
                        }
                    } else {
                        subcategoria.adcionarClassificao(grupo);
                        categoria.adcionarClassificao(subcategoria);
                        categorias.add(categoria);
                    }
                } else {
                    if (!categorias.isEmpty()) {
                        Classificacao categoriaAtual = categorias.get(categorias.size() - 1);
                        Classificacao subcategoriaAtual = categoriaAtual.retornaUltimoDaLista();
                        Classificacao grupoAtual = subcategoriaAtual.retornaUltimoDaLista();
                        float valor = 0;
                        try {
                            if (!row[2].equals("#REF!")) {
                                if (row[2].indexOf(".") >= 0) {
                                    valor = Float.parseFloat(row[2].replace(".", ""));
                                } else {
                                    valor = Float.parseFloat(row[2]);
                                }
                            } else {
                                valor = 0;
                            }
                        }
                        catch (ArrayIndexOutOfBoundsException e) {
                            Log.d("erro", Integer.toString(row.length));
                            Log.d("linha", row[0]);
                        }
                        String nome = row[1];
                        int numeroDeAuxiliares = Integer.parseInt(row[4]);
                        Procedimento procedimento = new Procedimento(valor, numeroDeAuxiliares, nome);
                        grupoAtual.adcionarClassificao(procedimento);
                    }
                }
                counter++;
            }

        }
        catch (IOException e) {
            throw new RuntimeException("Error in reading CSV file: "+e);

        }

        catch (ArrayIndexOutOfBoundsException e) {
            Log.d("ERRO", Integer.toString(counter));
        }
    }

    public ArrayList<Classificacao> retornaListaDeCategorias () {
        return categorias;
    }
}
