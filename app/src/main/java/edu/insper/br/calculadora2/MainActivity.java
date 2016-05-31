package edu.insper.br.calculadora2;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;
import java.util.ArrayList;


    public class MainActivity extends AppCompatActivity {
        ArrayList<Categoria> categorias = new ArrayList<>();


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.content_main);

            populateListView();
            registerClickCallBack();

        }

        private void registerClickCallBack() {

            ListView mList = (ListView) findViewById(R.id.ListViewMain);
            assert mList != null;
            mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View viewClicked, int position, long id) {

                    Intent intent = new Intent(MainActivity.this, SubcategActivity.class).putExtra("posicao",position);

                    startActivity(intent);


                    TextView textView = (TextView) viewClicked;
                    String message = textView.getText().toString();
                    Toast.makeText(MainActivity.this,message,Toast.LENGTH_LONG).show();
                }
            });
        }

        private void populateListView(){

            AssetManager assetManager = getAssets();
            CVSReader cvsReader = new CVSReader(assetManager);
            categorias = cvsReader.retornaListaDeCategorias();
            ArrayList<String> categorias_nomes = new ArrayList<>();

            for(Categoria i:categorias){
                String nome = i.retornaNomeDaCategoria();
                categorias_nomes.add(nome);
            }

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                    this,
                    R.layout.da_item,
                    categorias_nomes);
            ListView mList = (ListView) findViewById(R.id.ListViewMain);
            assert mList != null;
            mList.setAdapter(adapter);

        }

    }

