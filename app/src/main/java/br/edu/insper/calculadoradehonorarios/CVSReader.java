package br.edu.insper.calculadoradehonorarios;

import android.content.res.AssetManager;
import android.util.Log;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Rafael on 5/17/2016.
 */
public class CVSReader {

    public void run (AssetManager assetManager) {


        String csvFile = "tuss.csv";
        BufferedReader br = null;
        String line = "";
        String splitBy = ",";


        Map<String, ArrayList<Map<String, ArrayList<String>>>> categorias = new HashMap<>();
        int counter = -1;
        try {
            InputStream csvStream = assetManager.open(csvFile);
            BufferedReader reader = new BufferedReader(new InputStreamReader(csvStream, "UTF8"));
            String csvLine;
            int categoriaCounter = 0;
            int subcategoriaCounter = 0;
            while ((csvLine = reader.readLine()) != null) {
                String[] row = csvLine.split(",");
                if (row.length == 3) {
                    String categoria = (row[2].substring(0, row[2].indexOf("(")));
                    String subcategoria = row[2].substring(row[2].indexOf("("), row[2].indexOf(")") + 1);
                    String grupo = row[2].substring(row[2].indexOf(")") + 1);

                    if (!categorias.containsKey(categoria)) {
                        categorias.put(categoria, new ArrayList<Map<String, ArrayList<String>>>());
                        ArrayList<Map <String, ArrayList<String>>> categoriaAtual = categorias.get(categoria);
                        categoriaAtual.add(new HashMap<String, ArrayList<String>>());
                        counter++;
                        Log.i("IF", Integer.toString(counter));
                        categoriaAtual.get(counter).put(subcategoria, new ArrayList<String>());
                        categoriaAtual.get(counter).get(subcategoria).add(grupo);
                    } else {
                        Map <String, ArrayList<String>> subcategoriaAtual = categorias.get(categoria).get(counter);
                        Log.i("ELSE", "");
                        if (!subcategoriaAtual.containsKey(subcategoria)) {
                            subcategoriaAtual.put(subcategoria, new ArrayList<String>());
                            subcategoriaAtual.get(subcategoria).add(grupo);
                        } else {
                            subcategoriaAtual.get(subcategoria).add(grupo);
                        }
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

        for (String i : categorias.keySet()) {
            Log.i("SUB", i);
        }
    }
}
