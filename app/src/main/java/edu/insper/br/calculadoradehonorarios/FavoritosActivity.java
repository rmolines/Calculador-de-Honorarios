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

import java.io.BufferedReader;
import java.util.ArrayList;

/**
 * Created by Rafael on 6/13/2016.
 */
public class FavoritosActivity extends AppCompatActivity {
    private ArrayList<Classificacao> favoritos;
    private ArrayList<String> procedimentosFavoritos;
    private Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favoritos);

        database = Database.getInstance();

        Button apagarFavoritos = (Button) findViewById(R.id.botaoApagarFavoritos);
        assert apagarFavoritos != null;

        favoritos = Database.getInstance().retornaListaDeFavoritos();
        procedimentosFavoritos = new ArrayList<>();

        populateListView();
        registerClickCallBack();

        apagarFavoritos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Database.getInstance().apagaFavoritos();
            }
        });

    }

    private void populateListView(){
        for(Classificacao classificacao : favoritos){
            Procedimento procedimento = (Procedimento) classificacao;
            String nome = procedimento.retornaNome();
            procedimentosFavoritos.add(nome);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,
                R.layout.da_item,
                procedimentosFavoritos);
        ListView mList = (ListView) findViewById(R.id.listFavoritos);
        assert mList != null;
        mList.setAdapter(adapter);

    }

    private void registerClickCallBack() {

        ListView mList = (ListView) findViewById(R.id.listFavoritos);
        assert mList != null;
        mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View viewClicked, int position, long id) {

                database.mudaClassificacaoAtual(favoritos.get(position));
                Intent intent = new Intent(FavoritosActivity.this, InfoActivity.class);

                startActivity(intent);


                TextView textView = (TextView) viewClicked;
                String message = textView.getText().toString();
                Toast.makeText(FavoritosActivity.this,message,Toast.LENGTH_LONG).show();
            }
        });
    }


}
