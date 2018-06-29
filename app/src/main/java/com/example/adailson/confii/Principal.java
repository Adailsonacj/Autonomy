package com.example.adailson.confii;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.Calendar;


public class Principal extends AppCompatActivity  {

    Bundle vrDados = new Bundle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
    }

    public void btnAdicionarDespesa(View view) {
        Intent vrintention = new Intent(this, AddDespesa.class);
        startActivity(vrintention);
    }

    public void btnFundos(View v) {
        Intent it = new Intent(Principal.this, Meses.class);
        vrDados.putInt("idBtn", 2);
        it.putExtras(vrDados);
        startActivity(it);
    }

    public void btnVisualizarGastos(View v) {
        Intent it = new Intent(Principal.this, Despesas.class);
        Calendar c = Calendar.getInstance();
        //Acrescendo o valor 1 por o "Mês" vem contando a partir de zero.
        int numMes = c.get(Calendar.MONTH) + 1;
        vrDados.putInt("numeroMes", numMes);

        String strMes = null;
        if (numMes == 1) {
            strMes = "Janeiro";
        }
        if (numMes == 2) {
            strMes = "Fevereiro";
        }
        if (numMes == 3) {
            strMes = "Março";
        }
        if (numMes == 4) {
            strMes = "Abril";
        }
        if (numMes == 5) {
            strMes = "Maio";
        }
        if (numMes == 6) {
            strMes = "Junho";
        }
        if (numMes == 7) {
            strMes = "Julho";
        }
        if (numMes == 8) {
            strMes = "Agosto";
        }
        if (numMes == 9) {
            strMes = "Setembro";
        }
        if (numMes == 10) {
            strMes = "Outubro";
        }
        if (numMes == 11) {
            strMes = "Novembro";
        }
        if (numMes == 12) {
            strMes = "Dezembro";
        }
        vrDados.putString("nome", strMes);
        vrDados.putInt("numeroAno", c.get(Calendar.YEAR));
        it.putExtras(vrDados);
        startActivity(it);
    }
}
