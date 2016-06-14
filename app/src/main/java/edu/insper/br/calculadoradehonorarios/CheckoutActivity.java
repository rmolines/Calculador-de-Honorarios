package edu.insper.br.calculadoradehonorarios;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class CheckoutActivity extends AppCompatActivity {


    private ArrayList<Classificacao> listaDeProcedimentos;
    private ArrayList<String> somaDeProcedimentos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        listaDeProcedimentos = Database.getInstance().retornaSomaDeProcedimentos();
        somaDeProcedimentos = new ArrayList<>();

        Button apagar = (Button) findViewById(R.id.apagarBotao) ;
        assert apagar != null;



        double valorfinal = 0;
        int numeroFinalDeAuxiliares = 0;

        for(Classificacao classificacao : listaDeProcedimentos){
            Procedimento procedimento = (Procedimento) classificacao;
            double f = procedimento.retornaValor();
            valorfinal = valorfinal + f;
            int numeroDeAuxiliares = procedimento.retornaNumeroDeAuxiliares();
            numeroFinalDeAuxiliares += numeroDeAuxiliares;

            for (String i : procedimento.retornaListaCalculo()) {
                somaDeProcedimentos.add(i);
            }
            somaDeProcedimentos.add("");
        }

        somaDeProcedimentos.add("Custo Total:"+Double.toString(valorfinal));

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,
                R.layout.da_item,
                somaDeProcedimentos);
        ListView mList = (ListView) findViewById(R.id.listSomaProcedimentos);
        assert mList != null;
        mList.setAdapter(adapter);

        apagar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Database.getInstance().apagaProcedimentos();

                Intent intent = new Intent(CheckoutActivity.this, TelaInicialActivity.class);
                startActivity(intent);
            }
        });

    }

}
