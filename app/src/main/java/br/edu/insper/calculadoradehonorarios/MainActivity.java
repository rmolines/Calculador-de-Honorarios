package br.edu.insper.calculadoradehonorarios;

import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<Categoria> categorias = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        AssetManager assetManager = getAssets();
        CVSReader cvsReader = new CVSReader(assetManager);
        categorias = cvsReader.retornaListaDeCategorias();

    }
}
