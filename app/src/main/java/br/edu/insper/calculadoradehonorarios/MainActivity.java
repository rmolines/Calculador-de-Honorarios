package br.edu.insper.calculadoradehonorarios;

import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        AssetManager assetManager = getAssets();
        CVSReader cvsReader = new CVSReader();
        cvsReader.run(assetManager);
    }
}
