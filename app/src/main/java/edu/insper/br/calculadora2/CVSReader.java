package edu.insper.br.calculadora2;

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


public class CVSReader {
    private ArrayList<Categoria> categorias = new ArrayList<>();

    public CVSReader (AssetManager assetManager) {
        run(assetManager);
    }

    public void run (AssetManager assetManager) {

        String csvFile = "tuss.csv";
        BufferedReader br = null;
        String line = "";
        String splitBy = ",";

        int counter = -1;

        try {
            InputStream csvStream = assetManager.open(csvFile);
            BufferedReader reader = new BufferedReader(new InputStreamReader(csvStream, "UTF8"));
            String csvLine;
            int categoriaCounter = 0;
            int subcategoriaCounter = 0;
            int grupoCounter = 0;

            while ((csvLine = reader.readLine()) != null) {
                String[] row = csvLine.split(",");
                if (row.length == 3) {
                    String nomeDaCategoria = row[2];
                    Categoria categoria = new Categoria(nomeDaCategoria, categoriaCounter);
                    Subcategoria subcategoria = new Subcategoria(nomeDaCategoria, subcategoriaCounter);
                    Grupo grupo = new Grupo(nomeDaCategoria, grupoCounter);

                    if (!categorias.isEmpty()) {
                        Categoria categoriaAtual = categorias.get(categorias.size()-1);
                        if (!categoriaAtual.retornaNomeDaCategoria().equals(categoria.retornaNomeDaCategoria())) {
                            subcategoria.criarGrupo(grupo);
                            categoria.criarSubcategoria(subcategoria);
                            categorias.add(categoria);
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
                }
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
        System.out.println(categorias);
        return categorias;

    }
}
