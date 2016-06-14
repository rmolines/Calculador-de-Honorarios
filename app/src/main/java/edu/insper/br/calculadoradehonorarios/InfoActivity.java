package edu.insper.br.calculadoradehonorarios;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.util.Log;

import java.util.ArrayList;

public class InfoActivity extends AppCompatActivity {

    private int posicaoGrupo = 0;
    private int posicaoCategoria = 0;
    private int posicaoSubcategoria = 0;
    private int posicaoProcedimento = 0;
    private ArrayList<Classificacao> listaDeCategorias = new ArrayList<>();

    private ArrayList<String> somaNomeProcedimentos ;
    private ArrayList<String> somaValorProcedimentos ;
    private ArrayList<String> somaNumeroAuxiliares ;

    private Procedimento procedimentoAtual;
    private Classificacao classificacaoAtual;
    private Database database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        Button adicionaProcedimento = (Button) findViewById(R.id.botaoAdicionaProcedimento);
        assert adicionaProcedimento != null;

        Button adcionaAosFavoritos = (Button) findViewById(R.id.botaoAdcionaAosFavoritos);
        assert adcionaAosFavoritos != null;



        listaDeCategorias = Database.getInstance().retornaListaDeCategorias();

        database = Database.getInstance();
        classificacaoAtual = database.retornaClassificaoAtual();
        procedimentoAtual = (Procedimento) classificacaoAtual;

        criaLista();

        adcionaAosFavoritos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Database.getInstance().adcionaAosFavoritos(procedimentoAtual);
            }
        });

        adicionaProcedimento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                somaNomeProcedimentos = getIntent().getStringArrayListExtra("somaNomeProcedimentos");
                somaValorProcedimentos = getIntent().getStringArrayListExtra("somaValorProcedimentos");
                somaNumeroAuxiliares = getIntent().getStringArrayListExtra("somaNumeroAuxiliares");

                String nome = procedimentoAtual.retornaListaCalculo().get(0);

                Database.getInstance().adcionaProcedimento(procedimentoAtual);

                somaNomeProcedimentos.add(nome);
                somaValorProcedimentos.add(Double.toString(procedimentoAtual.retornaValor()));
                //somaValorProcedimentos.add(procedimento.retornaListaCalculo().get(1));
                somaNumeroAuxiliares.add(Integer.toString(procedimentoAtual.retornaNumeroDeAuxiliares()));

                Intent intent = new Intent(InfoActivity.this, MainActivity.class)
                        .putStringArrayListExtra("somaNomeProcedimentos",somaNomeProcedimentos)
                        .putStringArrayListExtra("somaValorProcedimentos",somaValorProcedimentos)
                        .putStringArrayListExtra("somaNumeroAuxiliares",somaNumeroAuxiliares);

                startActivity(intent);
            }
        });

    }

    private void criaLista () {

        System.out.println(procedimentoAtual.retornaListaCalculo().get(0));

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.da_item, procedimentoAtual.retornaListaCalculo());
        ListView mLista = (ListView) findViewById(R.id.listViewCalculadora);
        mLista.setAdapter(adapter);
    }


}
