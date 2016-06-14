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

/**
 * Created by antoniolacombesigrist on 23/05/16.
 */
public class GruposActivity extends AppCompatActivity {

    private ArrayList<String> somaNomeProcedimentos;
    private ArrayList<String> somaValorProcedimentos;
    private ArrayList<String> somaNumeroAuxiliares;

    int posicaoGrupo = 0;
    int posicaoCategoria = 0;
    int posicaoSubcategoria = 0;
    private ArrayList<Classificacao> listaDeGrupos = new ArrayList<>();
    private Classificacao classificacaoAtual;
    private Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grupos);

        database = Database.getInstance();
        classificacaoAtual = database.retornaClassificaoAtual();
        listaDeGrupos = classificacaoAtual.retornaLista();

        ListView mList = (ListView) findViewById(R.id.listViewGrupo);

        somaNomeProcedimentos = getIntent().getStringArrayListExtra("somaNomeProcedimentos");
        somaValorProcedimentos = getIntent().getStringArrayListExtra("somaValorProcedimentos");
        somaNumeroAuxiliares = getIntent().getStringArrayListExtra("somaNumeroAuxiliares");


        posicaoCategoria = getIntent().getIntExtra("posicaoCategoria", 0);
        posicaoSubcategoria = getIntent().getIntExtra("posicaoSubcategoria", 0);


        preencheLista();
        registerClickCallBack();

    }
    private void preencheLista(){
        ArrayList<String> nomeDosGrupos = new ArrayList<>();

        for (Classificacao classificacao : classificacaoAtual.retornaLista()) {
            nomeDosGrupos.add(classificacao.retornaNome());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,
                R.layout.da_item,
                nomeDosGrupos);
        ListView mLista = (ListView) findViewById(R.id.listViewGrupo);
        mLista.setAdapter(adapter);
    }


    private void registerClickCallBack() {

        ListView mList = (ListView) findViewById(R.id.listViewGrupo);
        assert mList != null;
        mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View viewClicked, int position, long id) {

                database.mudaClassificacaoAtual(listaDeGrupos.get(position));


                //Log.i("posicao", Integer.toString(position));
                Intent intent = new Intent(GruposActivity.this, ProcedimentosActivity.class);
                intent
                        .putExtra("posicaoCategoria", posicaoCategoria)
                        .putExtra("posicaoSubcategoria", posicaoSubcategoria)
                        .putExtra("posicaoGrupo",position)
                        .putStringArrayListExtra("somaNomeProcedimentos",somaNomeProcedimentos)
                        .putStringArrayListExtra("somaValorProcedimentos",somaValorProcedimentos)
                        .putStringArrayListExtra("somaNumeroAuxiliares",somaNumeroAuxiliares);

                startActivity(intent);


                TextView textView = (TextView) viewClicked;
                String message = textView.getText().toString();
                Toast.makeText(GruposActivity.this, message, Toast.LENGTH_LONG).show();
            }
        });
    }

}
