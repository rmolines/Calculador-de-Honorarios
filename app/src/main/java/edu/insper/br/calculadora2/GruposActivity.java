package edu.insper.br.calculadora2;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by antoniolacombesigrist on 23/05/16.
 */
public class GruposActivity extends AppCompatActivity {


    int posicao2 = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grupos);

        ListView mList = (ListView) findViewById(R.id.listViewGrupo);

        posicao2 = getIntent().getIntExtra("posicao2",0);

        populateListView();

    }

    private void populateListView(){

        ArrayList<Categoria> categorias = new ArrayList<>();
        AssetManager assetManager = getAssets();
        CVSReader cvsReader = new CVSReader(assetManager);
        categorias = cvsReader.retornaListaDeCategorias();
        ArrayList<String> categorias_nomes = new ArrayList<>();


        ArrayList<ArrayList<Subcategoria>> list_sub = new ArrayList<>();
        ArrayList<ArrayList<Grupo>> lista_sub_grupos = new ArrayList<>();
        ArrayList<ArrayList<Grupo>> lista_grupo_escolhido = new ArrayList<>();
        ArrayList<Grupo> lista_grupo_escolhido2 = new ArrayList<>();
        ArrayList<String> nomeGrupo = new ArrayList<>();


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

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,
                R.layout.da_item,
                nomeGrupo);
        ListView mLista = (ListView) findViewById(R.id.listViewGrupo);
        mLista.setAdapter(adapter);

    }

}
