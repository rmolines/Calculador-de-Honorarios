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

/**
 * Created by Rafael on 5/17/2016.
 */
public class CVSReader {

    public void run (AssetManager assetManager) {


        String csvFile = "tuss.csv";
        BufferedReader br = null;
        String line = "";
        String splitBy = ",";


        ArrayList<String []> resultList = new ArrayList<>();

        try {
            InputStream csvStream = assetManager.open(csvFile);
            BufferedReader reader = new BufferedReader(new InputStreamReader(csvStream, "UTF8"));
            String csvLine;
            while ((csvLine = reader.readLine()) != null) {
                String[] row = csvLine.split(",");
                resultList.add(row);
            }
        }
        catch (IOException e) {
            throw new RuntimeException("Error in reading CSV file: "+e);
        }
        catch (ArrayIndexOutOfBoundsException e) {
            Log.d("ERRO", "IOB");
        }
        for (String[] i : resultList) {
            String j = new String();
            for (String s : i) {
                j += s;
                j += ",";
            }
            Log.i("LINHA", j);
        }
    }
}
