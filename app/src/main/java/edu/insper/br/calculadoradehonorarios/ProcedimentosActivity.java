package edu.insper.br.calculadoradehonorarios;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.util.ArrayList;

public class ProcedimentosActivity extends AppCompatActivity {

    private ArrayList<String> somaNomeProcedimentos;
    private ArrayList<String> somaValorProcedimentos;
    private ArrayList<String> somaNumeroAuxiliares;
    private int posicao = 0;
    private int posicao2 = 0;
    private int posicaoGrupo = 0;
    private int posicaoCategoria = 0;
    private int posicaoSubcategoria = 0;
    private int posicaoProcedimento = 0;
    private String csvFile = "tuss.csv";
    private BufferedReader br = null;
    private int clique = 0;
    private ArrayList<Classificacao> listaDeProcedimentos = new ArrayList<>();
    private CSVReader cvsReader;
    private ArrayList<Classificacao> listaDeCategorias = new ArrayList<>();
    private Classificacao classificacaoAtual;
    private Database database;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_procedimentos);
        //Log.i("procedimentos", "rodouw");

        listaDeCategorias = Database.getInstance().retornaListaDeCategorias();

        database = Database.getInstance();
        classificacaoAtual = database.retornaClassificaoAtual();
        listaDeProcedimentos = classificacaoAtual.retornaLista();

        ListView mList = (ListView) findViewById(R.id.listViewProcedimentos);

        posicaoCategoria = getIntent().getIntExtra("posicaoCategoria", 0);
        posicaoGrupo = getIntent().getIntExtra("posicaoGrupo", 0);
        posicaoSubcategoria = getIntent().getIntExtra("posicaoSubcategoria", 0);
        posicaoProcedimento = posicao;

        criaLista();

        registerClickCallBack();


    }

    private void registerClickCallBack() {

        ListView mList = (ListView) findViewById(R.id.listViewProcedimentos);
        assert mList != null;
        mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View viewClicked, int position, long id) {
                //Log.i("posicao", Integer.toString(position));

                database.mudaClassificacaoAtual(listaDeProcedimentos.get(position));

                Intent intent = new Intent(ProcedimentosActivity.this, InfoActivity.class).putExtra("posicao",position);
                intent.putExtra("posicaoCategoria", posicaoCategoria).putExtra("posicaoSubcategoria", posicaoSubcategoria).putExtra("posicaoGrupo",posicaoGrupo).putExtra("posicaoProcedimento", position)
                        .putStringArrayListExtra("somaNomeProcedimentos",somaNomeProcedimentos)
                        .putStringArrayListExtra("somaValorProcedimentos",somaValorProcedimentos)
                        .putStringArrayListExtra("somaNumeroAuxiliares",somaNumeroAuxiliares);

                Classificacao procedimento = acharProcedimento(posicaoCategoria, posicaoSubcategoria, posicaoGrupo, position);


                startActivity(intent);


                TextView textView = (TextView) viewClicked;
                String message = textView.getText().toString();
                Toast.makeText(ProcedimentosActivity.this,message,Toast.LENGTH_LONG).show();
            }
        });
    }

    private Classificacao acharProcedimento (int posicaoCategoria, int posicaoSubcategoria, int posicaoGrupo, int posicaoProcedimento) {
        Classificacao categoria = listaDeCategorias.get(posicaoCategoria);
        Classificacao subcategoria = categoria.retornaLista().get(posicaoSubcategoria);
        Classificacao grupo = subcategoria.retornaLista().get(posicaoGrupo);
        Classificacao procedimento = grupo.retornaLista().get(posicaoProcedimento);

        return procedimento;
    }

    private void criaLista () {
        ArrayList<String> nomeDeProcedimentos = new ArrayList<>();

        for (Classificacao i : listaDeProcedimentos) {
            nomeDeProcedimentos.add(i.retornaNome());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.da_item, nomeDeProcedimentos);
        ListView mLista = (ListView) findViewById(R.id.listViewProcedimentos);
        mLista.setAdapter(adapter);
    }
}
