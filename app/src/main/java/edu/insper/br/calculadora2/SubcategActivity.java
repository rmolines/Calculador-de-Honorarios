package edu.insper.br.calculadora2;

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


    int posicao = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista_main);

        ListView mList = (ListView) findViewById(R.id.listViewSub);
        posicao = getIntent().getIntExtra("posicao",0);

        populateListView();
        registerClickCallBack();


    }

    private void registerClickCallBack() {

        ListView mList = (ListView) findViewById(R.id.listViewSub);
        assert mList != null;
        mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View viewClicked, int position, long id) {

                Intent intent = new Intent(SubcategActivity.this, GruposActivity.class).putExtra("posicao2", position);

                startActivity(intent);


                TextView textView = (TextView) viewClicked;
                String message = textView.getText().toString();
                Toast.makeText(SubcategActivity.this, message, Toast.LENGTH_LONG).show();
            }
        });
    }


    private void populateListView(){

        ArrayList<Categoria> categorias = new ArrayList<>();
        AssetManager assetManager = getAssets();
        CVSReader cvsReader = new CVSReader(assetManager);
        categorias = cvsReader.retornaListaDeCategorias();
        ArrayList<String> categorias_nomes = new ArrayList<>();


        ArrayList<ArrayList<Subcategoria>> list_sub = new ArrayList<>();


        for (Categoria i : categorias) {
            list_sub.add(i.retornaSubcategorias());
        }

        ArrayList<Subcategoria> lista_sub_escolhida = new ArrayList<>();
        ArrayList<String> lista_sub_escolhida_nome = new ArrayList<>();

        lista_sub_escolhida = list_sub.get(posicao);

        for (Subcategoria i:lista_sub_escolhida){
            lista_sub_escolhida_nome.add(i.retornaNomeDaSubcategoria());

        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,
                R.layout.da_item,
                lista_sub_escolhida_nome);
        ListView mList = (ListView) findViewById(R.id.listViewSub);
        assert mList != null;
        mList.setAdapter(adapter);

    }

}
