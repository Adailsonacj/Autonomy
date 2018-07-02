package com.example.adailson.confii;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.Calendar;


public class Principal extends AppCompatActivity {

    Bundle vrDados = new Bundle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        /*
        Intent vrIntent = getIntent();
        Bundle dados = vrIntent.getExtras();
        if(dados.getInt("semfundo")==1){
            //Snackbar.make(View, "Adicione primeiro um fundo ao mês selecionado", Snackbar.LENGTH_LONG)
              //      .setAction("Action", null).show();

        }
        */
    }

    public void btnFundos(View v) {
        Intent it = new Intent(Principal.this, Fundos.class);
        vrDados.putInt("idBtn", 2);
        it.putExtras(vrDados);
        startActivity(it);
    }

    public void btnVisualizarGastos(View v) {
        Intent it = new Intent(Principal.this, Despesas.class);
        startActivity(it);
    }
}
