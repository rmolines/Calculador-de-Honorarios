package edu.insper.br.calculadoradehonorarios;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class SubcategActivity extends AppCompatActivity {

    private ArrayList<Classificacao> listaDeSubcategorias;
    private Classificacao classificacaoAtual;
    private Database database;

    private ArrayList<String> somaNomeProcedimentos;
    private ArrayList<String> somaValorProcedimentos;
    private ArrayList<String> somaNumeroAuxiliares;

    int posicaoCategoria = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista_main);

        database = Database.getInstance();
        classificacaoAtual = database.retornaClassificaoAtual();
        listaDeSubcategorias = classificacaoAtual.retornaLista();

        somaNomeProcedimentos = getIntent().getStringArrayListExtra("somaNomeProcedimentos");
        somaValorProcedimentos = getIntent().getStringArrayListExtra("somaValorProcedimentos");
        somaNumeroAuxiliares = getIntent().getStringArrayListExtra("somaNumeroAuxiliares");

        ListView mList = (ListView) findViewById(R.id.listViewSub);
        posicaoCategoria = getIntent().getIntExtra("posicaoCategoria",0);

        populateListView();
        registerClickCallBack();


    }

    private void registerClickCallBack() {

        ListView mList = (ListView) findViewById(R.id.listViewSub);
        assert mList != null;
        mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View viewClicked, int position, long id) {
                //Log.i("posicao", Integer.toString(position));

                database.mudaClassificacaoAtual(listaDeSubcategorias.get(position));

                Intent intent = new Intent(SubcategActivity.this, GruposActivity.class);
                intent.putExtra("posicaoCategoria", posicaoCategoria).putExtra("posicaoSubcategoria", position)
                        .putStringArrayListExtra("somaNomeProcedimentos",somaNomeProcedimentos)
                        .putStringArrayListExtra("somaValorProcedimentos",somaValorProcedimentos)
                        .putStringArrayListExtra("somaNumeroAuxiliares",somaNumeroAuxiliares);

                startActivity(intent);


                TextView textView = (TextView) viewClicked;
                String message = textView.getText().toString();
                Toast.makeText(SubcategActivity.this, message, Toast.LENGTH_LONG).show();
            }
        });
    }


    private void populateListView(){


        ArrayList<String> nomesDasSubcategorias = new ArrayList<>();

        for(Classificacao classificacao:classificacaoAtual.retornaLista()){
            String nome = classificacao.retornaNome();
            nomesDasSubcategorias.add(nome);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,
                R.layout.da_item,
                nomesDasSubcategorias);
        ListView mList = (ListView) findViewById(R.id.listViewSub);
        assert mList != null;
        mList.setAdapter(adapter);
    }

}
