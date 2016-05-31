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
    private ArrayList<Categoria> categorias = new ArrayList<>();

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
                    Categoria categoria = new Categoria(nomeDaCategoria, categoriaCounter);
                    Subcategoria subcategoria = new Subcategoria(nomeDaCategoria, subcategoriaCounter);
                    Grupo grupo = new Grupo(nomeDaCategoria, counter);

                    if (!categorias.isEmpty()) {
                        Categoria categoriaAtual = categorias.get(categorias.size()-1);
                        if (!categoriaAtual.retornaNomeDaCategoria().equals(categoria.retornaNomeDaCategoria())) {
                            subcategoria.criarGrupo(grupo);
                            categoria.criarSubcategoria(subcategoria);
                            categorias.add(categoria);
                            Log.i("categoria", categoria.retornaNomeDaCategoria());
                        } else {
                            if (!categoriaAtual.retornaSubcategorias().isEmpty()) {
                                Subcategoria subcategoriaAtual = categoriaAtual.retornaUltimaSubcategoria();
                                if (!subcategoriaAtual.retornaNomeDaSubcategoria().equals(subcategoria.retornaNomeDaSubcategoria())) {
                                    subcategoria.criarGrupo(grupo);
                                    categoriaAtual.criarSubcategoria(subcategoria);
                                } else {
                                    subcategoriaAtual.criarGrupo(grupo);
                                }
                            }
                        }
                    } else {
                        subcategoria.criarGrupo(grupo);
                        categoria.criarSubcategoria(subcategoria);
                        categorias.add(categoria);
                    }
                } else {
                    if (!categorias.isEmpty()) {
                        Categoria categoriaAtual = categorias.get(categorias.size() - 1);
                        Subcategoria subcategoriaAtual = categoriaAtual.retornaUltimaSubcategoria();
                        Grupo grupoAtual = subcategoriaAtual.retornaUltimoGrupo();

                        float valor = Float.parseFloat(row[3]);
                        String nome = row[2];
                        int numeroDeAuxiliares = Integer.parseInt(row[5]);
                        Procedimento procedimento = new Procedimento(valor, numeroDeAuxiliares, nome);
                        grupoAtual.adcionaProcedimento(procedimento);
                    }
                }
                counter++;
            }

        }
        catch (IOException e) {
            throw new RuntimeException("Error in reading CSV file: "+e);

        }
        catch (ArrayIndexOutOfBoundsException e) {
            Log.d("ERRO", "IOB");
        }
    }

    public ArrayList<Categoria> retornaListaDeCategorias () {
        return categorias;
    }


}
