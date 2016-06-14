package edu.insper.br.calculadoradehonorarios;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private ArrayList<Classificacao> listaDeCategorias = new ArrayList<>();

    private ArrayList<String> somaNomeProcedimentos;
    private ArrayList<String> somaValorProcedimentos;
    private ArrayList<String> somaNumeroAuxiliares;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);

        listaDeCategorias = Database.getInstance().retornaListaDeCategorias();

        somaNomeProcedimentos = getIntent().getStringArrayListExtra("somaNomeProcedimentos");
        somaValorProcedimentos = getIntent().getStringArrayListExtra("somaValorProcedimentos");
        somaNumeroAuxiliares = getIntent().getStringArrayListExtra("somaNumeroAuxiliares");

        populateListView();
        registerClickCallBack();

        Button botaoFinalizar = (Button) findViewById(R.id.buttonFinalizar);
        assert botaoFinalizar!= null;

        if(!somaNomeProcedimentos.isEmpty()){botaoFinalizar.setVisibility(View.VISIBLE);}

        botaoFinalizar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, CheckoutActivity.class)
                            .putStringArrayListExtra("somaNomeProcedimentos",somaNomeProcedimentos)
                            .putStringArrayListExtra("somaValorProcedimentos",somaValorProcedimentos)
                            .putStringArrayListExtra("somaNumeroAuxiliares",somaNumeroAuxiliares);

                    startActivity(intent);}
            });

            //Button botaoBack = (Button) findViewById(R.id.buttonBack);
            //assert botaoBack!= null;

    }

    private void registerClickCallBack() {
        ListView mList = (ListView) findViewById(R.id.ListViewMain);
        assert mList != null;
        mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View viewClicked, int position, long id) {

                    Intent intent = new Intent(MainActivity.this, SubcategActivity.class)
                            .putExtra("posicaoCategoria",position)
                            .putStringArrayListExtra("somaNomeProcedimentos",somaNomeProcedimentos)
                            .putStringArrayListExtra("somaValorProcedimentos",somaValorProcedimentos)
                            .putStringArrayListExtra("somaNumeroAuxiliares",somaNumeroAuxiliares);

                    Database.getInstance().mudaClassificacaoAtual(listaDeCategorias.get(position));

                    startActivity(intent);


                    TextView textView = (TextView) viewClicked;
                    String message = textView.getText().toString();
                    Toast.makeText(MainActivity.this,message,Toast.LENGTH_LONG).show();
                }
            });
    }

    private void populateListView(){


        ArrayList<String> nomesDasCategorias = new ArrayList<>();

        for(Classificacao classificacao:listaDeCategorias){
            String nome = classificacao.retornaNome();
            nomesDasCategorias.add(nome);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,
                R.layout.da_item,
                nomesDasCategorias);
        ListView mList = (ListView) findViewById(R.id.ListViewMain);
        assert mList != null;
        mList.setAdapter(adapter);
    }



}

