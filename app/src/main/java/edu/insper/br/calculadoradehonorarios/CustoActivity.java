package edu.insper.br.calculadoradehonorarios;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.BufferedReader;

/**
 * Created by Rafael on 5/30/2016.
 */
public class CustoActivity extends AppCompatActivity {


    private int posicao = 0;
    private int posicao2 = 0;
    private String csvFile = "tuss.csv";
    private BufferedReader br = null;
    private int clique = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custo);

        ListView mList = (ListView) findViewById(R.id.listViewCusto);

        posicao = getIntent().getIntExtra("posicao",0);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,
                R.layout.da_item,
                run(assetManager,posicaoGrupo,posicao));
        ListView mLista = (ListView) findViewById(R.id.listViewProcedimentos);
        mLista.setAdapter(adapter);

    }
}
