package edu.insper.br.calculadoradehonorarios;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ProcedimentosActivity extends AppCompatActivity {

    private int posicao = 0;
    private int posicao2 = 0;
    private String csvFile = "tuss.csv";
    private BufferedReader br = null;
    private int clique = 0;
    private ArrayList<String> listaDeProcedimentos;
    private CSVReader cvsReader;
    private ArrayList<Categoria> listaDeCategorias = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_procedimentos);

        ListView mList = (ListView) findViewById(R.id.listViewProcedimentos);

        posicao = getIntent().getIntExtra("posicao",0);
        posicao2 = getIntent().getIntExtra("posicao2",0);

        populateListView();
        AssetManager assetManager = getAssets();
        cvsReader = new CSVReader(assetManager);



    }

    private void registerClickCallBack() {

        ListView mList = (ListView) findViewById(R.id.listViewProcedimentos);
        assert mList != null;
        mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View viewClicked, int position, long id) {

                Intent intent = new Intent(ProcedimentosActivity.this, SubcategActivity.class).putExtra("posicao",position);
                int posicaoCategoria = getIntent().getIntExtra("posicaoCategoria",0);
                int posicaoSubcategoria = getIntent().getIntExtra("posicaoSubcategoria", 0);
                int posicaoGrupo = getIntent().getIntExtra("posicaoGrupo", 0);

                Procedimento procedimento = acharProcedimento(posicaoCategoria, posicaoSubcategoria, posicaoGrupo, position);
                intent.putExtra("custo", procedimento.retornaValor());
                intent.putExtra("auxiliares", procedimento.retornaNumeroDeAuxiliares());
                intent.putExtra("nome", procedimento.retornaNome());

                startActivity(intent);


                TextView textView = (TextView) viewClicked;
                String message = textView.getText().toString();
                Toast.makeText(ProcedimentosActivity.this,message,Toast.LENGTH_LONG).show();
            }
        });
    }

    private Procedimento acharProcedimento (int posicaoCategoria, int posicaoSubcategoria, int posicaoGrupo, int posicaoProcedimento) {
        Categoria categoria = listaDeCategorias.get(posicaoCategoria);
        Subcategoria subcategoria = categoria.retornaSubcategorias().get(posicaoSubcategoria);
        Grupo grupo = subcategoria.retornaGrupos().get(posicaoGrupo);
        Procedimento procedimento = grupo.retornaListaDeProcedimentos().get(posicaoProcedimento);

        return procedimento;
    }

    private void populateListView(){


        ArrayList<Categoria> categorias = new ArrayList<>();
        AssetManager assetManager = getAssets();
        CSVReader cvsReader = new CSVReader(assetManager);
        categorias = cvsReader.retornaListaDeCategorias();
        ArrayList<String> categorias_nomes = new ArrayList<>();


        ArrayList<ArrayList<Subcategoria>> list_sub = new ArrayList<>();
        ArrayList<ArrayList<Grupo>> lista_sub_grupos = new ArrayList<>();
        ArrayList<ArrayList<Grupo>> lista_grupo_escolhido = new ArrayList<>();
        ArrayList<Grupo> lista_grupo_escolhido2 = new ArrayList<>();
        ArrayList<String> nomeGrupo = new ArrayList<>();
        ArrayList<Integer> posicaoGrupo = new ArrayList<>();


        for (Categoria i : categorias) {
            list_sub.add(i.retornaSubcategorias());
        }

        for (ArrayList<Subcategoria> i: list_sub) {
            for (Subcategoria j: i){
                lista_grupo_escolhido.add(j.retornaGrupos());
            }
        }

        lista_grupo_escolhido2 = lista_grupo_escolhido.get(posicao2);

        for (Grupo i:lista_grupo_escolhido2){
            nomeGrupo.add(i.retornaNomeDoGrupo());

        }

        for (Grupo i:lista_grupo_escolhido2){
            posicaoGrupo.add(i.retornaPosicao());

        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,
                R.layout.da_item,
                run(assetManager,posicaoGrupo,posicao));
        ListView mLista = (ListView) findViewById(R.id.listViewProcedimentos);
        mLista.setAdapter(adapter);

    }

    public ArrayList<String> run (AssetManager assetManager, ArrayList<Integer> posicaoGrupo, int posicao) {

        ArrayList<String> lista_procedimentos = new ArrayList<>();
        String csvFile = "tuss.csv";
        BufferedReader br = null;
        String line = "";
        String splitBy = ",";

        int counter = 0;

        try {
            InputStream csvStream = assetManager.open(csvFile);
            BufferedReader reader = new BufferedReader(new InputStreamReader(csvStream, "UTF8"));
            String csvLine;

            int contador = 0;

            while ((csvLine = reader.readLine()) != null) {
                String[] row = csvLine.split(",");
                if (contador>posicaoGrupo.get(posicao) && contador<posicaoGrupo.get(posicao+1)){
                    lista_procedimentos.add(row[2]);
                }

                contador++;


            }
        }
            catch(IOException e){
                throw new RuntimeException("Error in reading CSV file: " + e);
            }
            catch(ArrayIndexOutOfBoundsException e){
                Log.d("ERRO", "IOB");
            }

        listaDeProcedimentos = lista_procedimentos;
        return listaDeProcedimentos;
    }



}
