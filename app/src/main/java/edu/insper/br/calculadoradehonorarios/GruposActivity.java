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


    int posicaoGrupo = 0;
    int posicaoCategoria = 0;
    int posicaoSubcategoria = 0;
    private ArrayList<Grupo> lista_grupo_escolhido2 = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grupos);

        ListView mList = (ListView) findViewById(R.id.listViewGrupo);

        posicaoGrupo = getIntent().getIntExtra("posicao",0);
        posicaoCategoria = getIntent().getIntExtra("posicaoCategoria", 0);
        posicaoSubcategoria = getIntent().getIntExtra("posicaoSubcategoria", 0);


        populateListView();
        registerClickCallBack();

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
        ArrayList<String> nomeGrupo = new ArrayList<>();


        for (Categoria i : categorias) {
            list_sub.add(i.retornaSubcategorias());
        }

        for (ArrayList<Subcategoria> i: list_sub) {
            for (Subcategoria j: i){
                lista_grupo_escolhido.add(j.retornaGrupos());
            }
        }

        lista_grupo_escolhido2 = lista_grupo_escolhido.get(posicaoGrupo);

        for (Grupo i:lista_grupo_escolhido2){
            nomeGrupo.add(i.retornaNomeDoGrupo());

        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,
                R.layout.da_item,
                nomeGrupo);
        ListView mLista = (ListView) findViewById(R.id.listViewGrupo);
        mLista.setAdapter(adapter);

    }

    private void registerClickCallBack() {

        ListView mList = (ListView) findViewById(R.id.listViewGrupo);
        assert mList != null;
        mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View viewClicked, int position, long id) {

                Intent intent = new Intent(GruposActivity.this, ProcedimentosActivity.class).putExtra("posicao",position).putExtra("posicaoGrupo",posicaoGrupo);

                startActivity(intent);


                TextView textView = (TextView) viewClicked;
                String message = textView.getText().toString();
                Toast.makeText(GruposActivity.this, message, Toast.LENGTH_LONG).show();
            }
        });
    }

}
