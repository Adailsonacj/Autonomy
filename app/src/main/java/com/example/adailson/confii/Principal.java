package com.example.adailson.confii;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.adailson.confii.daos.BancoController;
import com.example.adailson.confii.model.DespesaModel;
import com.example.adailson.confii.model.FundoModel;

import java.util.ArrayList;
import java.util.Calendar;

public class Principal extends AppCompatActivity {

    private static final int TIMER_RUNTIME = 1000;
    private ProgressBar pb;
    Bundle vrDados = new Bundle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        TextView tvMesAno = (TextView) findViewById(R.id.tvMesAno);

        BancoController crud = new BancoController(getBaseContext(), "gasto", 1);
        Calendar c = Calendar.getInstance();
        int mes = c.get(Calendar.MONTH) + 1;
        int ano = c.get(Calendar.YEAR);

        //Formatando o nome do mês e ano mostrados na tela
        String mesForm = null;
        if (c.get(Calendar.MONTH) == 1) {
            mesForm = "Janeiro/" + c.get(Calendar.YEAR);
        }
        if (c.get(Calendar.MONTH) == 2) {
            mesForm = "Fevereiro/" + c.get(Calendar.YEAR);
        }
        if (c.get(Calendar.MONTH) == 3) {
            mesForm = "Março/" + c.get(Calendar.YEAR);
        }
        if (c.get(Calendar.MONTH) == 4) {
            mesForm = "Abril/" + c.get(Calendar.YEAR);
        }
        if (c.get(Calendar.MONTH) == 5) {
            mesForm = "Maio/" + c.get(Calendar.YEAR);
        }
        if (c.get(Calendar.MONTH) == 6) {
            mesForm = "Junho/" + c.get(Calendar.YEAR);
        }
        if (c.get(Calendar.MONTH) == 7) {
            mesForm = "Julho/" + c.get(Calendar.YEAR);
        }
        if (c.get(Calendar.MONTH) == 8) {
            mesForm = "Agosto/" + c.get(Calendar.YEAR);
        }
        if (c.get(Calendar.MONTH) == 9) {
            mesForm = "Setembro/" + c.get(Calendar.YEAR);
        }
        if (c.get(Calendar.MONTH) == 10) {
            mesForm = "Outubro/" + c.get(Calendar.YEAR);
        }
        if (c.get(Calendar.MONTH) == 11) {
            mesForm = "Novembro/" + c.get(Calendar.YEAR);
        }
        if (c.get(Calendar.MONTH) == 12) {
            mesForm = "Dezembro/" + c.get(Calendar.YEAR);
        }

        tvMesAno.setText(mesForm);

        //Recebendo os fundos de determinado mes para mandar informação para ProgressBar
        ArrayList<FundoModel> listaFundos = crud.getFundos(mes, ano);
        float valorTotalFundos = 0;
        for (int i = 0; i < listaFundos.size(); i++) {
            valorTotalFundos += listaFundos.get(i).getValorEntra();
        }
        //Recebendo despesas de determinado mes para comparar no ProgressBar
        ArrayList<DespesaModel> listaDespesas = crud.getGastos(mes, ano);
        float valorTotalDEspesas = 0;
        for (int i = 0; i < listaDespesas.size(); i++) {
            valorTotalDEspesas += listaDespesas.get(i).getValor();
        }

        Float valorPorcentagem = valorTotalDEspesas * 100 / valorTotalFundos;
        //Transformando o valor para inteiro (Aceitável como parâmetro do ProgressBar)
        int valorPorcentagemInt = valorPorcentagem.intValue();

        pb = (ProgressBar) findViewById(R.id.progressBar);
        pb.setProgress(valorPorcentagemInt);

        TextView tvPorcent = (TextView) findViewById(R.id.tvPorcent);
        tvPorcent.setText("Você já ultilizou " + valorPorcentagemInt + "% dos fundos cadastrados neste mês.");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_resumo_despesa, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_settings:
                Intent it = new Intent(Principal.this, Config.class);
                startActivity(it);
                return true;
            case R.id.action_duplicarMes:
                Intent it1 = new Intent(Principal.this, DuplicarMes.class);
                startActivity(it1);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
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
