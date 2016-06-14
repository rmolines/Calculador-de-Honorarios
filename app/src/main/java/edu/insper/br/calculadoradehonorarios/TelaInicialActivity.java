package edu.insper.br.calculadoradehonorarios;

import android.content.Intent;
import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class TelaInicialActivity extends AppCompatActivity {

    private ArrayList<String> somaNomeProcedimentos = new ArrayList<>();
    private ArrayList<String> somaValorProcedimentos = new ArrayList<>();
    private ArrayList<String> somaNumeroAuxiliares = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_inicial);


        AssetManager assetManager = getAssets();
        Database database = Database.getInstance();
        database.criaListaDeCategorias(assetManager);

        Button botaoLista = (Button) findViewById(R.id.buttonLista);
        assert botaoLista != null;

        Button botaoFavoritos = (Button) findViewById(R.id.buttonPreferidos);
        assert botaoFavoritos != null;

        botaoFavoritos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TelaInicialActivity.this, FavoritosActivity.class);

                startActivity(intent);
            }
        });

        botaoLista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(TelaInicialActivity.this, MainActivity.class)
                        .putStringArrayListExtra("somaNomeProcedimentos",somaNomeProcedimentos)
                        .putStringArrayListExtra("somaValorProcedimentos",somaValorProcedimentos)
                        .putStringArrayListExtra("somaNumeroAuxiliares",somaNumeroAuxiliares);

                startActivity(intent);
            }
        });

        Button botaoPreferidos = (Button) findViewById(R.id.buttonPreferidos);
        assert botaoPreferidos != null;

    }
}
