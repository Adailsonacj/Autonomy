package com.example.adailson.confii;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

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

        BancoController crud = new BancoController(getBaseContext(), "gasto", 1);
        Calendar calendar = Calendar.getInstance();
        int mes = calendar.get(Calendar.MONTH) +1;
        int ano = calendar.get(Calendar.YEAR);
        ArrayList<FundoModel>  listaFundos = crud.getFundos(mes, ano);
        float valorTotalFundos = 0;
        for(int i =0; i<listaFundos.size(); i++){
            valorTotalFundos += listaFundos.get(i).getValorEntra();
        }
        ArrayList<DespesaModel> listaDespesas= crud.getGastos(mes, ano);
        float valorTotalDEspesas = 0;
        for(int i=0; i< listaDespesas.size(); i++){
            valorTotalDEspesas += listaDespesas.get(i).getValor();
        }

        Float valorPorcentagem = valorTotalDEspesas*100/valorTotalFundos;
        int valorPorcentagemInt = valorPorcentagem.intValue();

        pb = (ProgressBar) findViewById(R.id.progressBar);
        pb.setProgress(valorPorcentagemInt);



        //LinearLayout linearLayout = (LinearLayout) findViewById(R.idDespesa.linearLayout);
        //Intent vrIntent = getIntent();
        //Bundle dados = vrIntent.getExtras();
        //if (dados.getInt("semfundo") == 1) {


        //   Snackbar.make( linearLayout ,"Seu texto aqui!",Snackbar.LENGTH_LONG).setAction("Action", null).show();
        //Snackbar.make(Principal.this, "Adicione primeiro um fundo ao mês selecionado", Snackbar.LENGTH_LONG).setAction("Action", null).show();

        //}

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_resumo_despesa, menu);
        return true;
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
