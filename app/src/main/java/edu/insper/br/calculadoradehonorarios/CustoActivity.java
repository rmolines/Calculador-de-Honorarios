package edu.insper.br.calculadoradehonorarios;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.io.BufferedReader;

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


        ListView mLista = (ListView) findViewById(R.id.listViewProcedimentos);



    }
}
