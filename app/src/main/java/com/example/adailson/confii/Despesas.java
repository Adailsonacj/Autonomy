package com.example.adailson.confii;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.adailson.confii.daos.BancoController;
import com.example.adailson.confii.model.DespesaModel;
import com.example.adailson.confii.model.MesModel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Despesas extends Activity implements AdapterView.OnItemSelectedListener {

    Bundle vrDados = new Bundle();
    BancoController bd;
    ArrayList<DespesaModel> lista;
    DespesasAdapter despesasAdapter;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_despesas);
        bd = new BancoController(getBaseContext(), "gasto", 1);
        Intent vrIntent = getIntent();
        Bundle dados = vrIntent.getExtras();
        //Lista sendo preenchida com despesas do mês atual/ Parâmetros vindos da tela principal
        recarregarTela(0, 0);

        //Objeto Spinner, preenchendo e métodos de seleção
        List<String> mesesNomes = new ArrayList();
        for (int i = 0; i < bd.getMeses().size(); i++) {
            mesesNomes.add(bd.getMeses().get(i).getNome());
        }
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, mesesNomes);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(this);


        //ListView
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                DespesaModel ixDespesa = despesasAdapter.getItem(i);
                Intent it = new Intent(Despesas.this, ResumoDespesa.class);
                vrDados.putInt("idDespesa", ixDespesa.getId());
                vrDados.putString("descricao", ixDespesa.getDescricao());
                vrDados.putString("data", ixDespesa.getData());
                vrDados.putFloat("valor", ixDespesa.getValor());
                vrDados.putInt("pg", ixDespesa.getPg());
                vrDados.putInt("idFundo", ixDespesa.getIdFundo());
                it.putExtras(vrDados);
                startActivity(it);
                finish();
            }
        });
    }

    public void btnAddDespesa(View v) {
        Intent vrintention = new Intent(this, AddDespesa.class);
        startActivity(vrintention);
        finish();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        String nome = parent.getItemAtPosition(position).toString();
        int numMes = bd.getMeses().get(position).getNumeroMes();
        int numAno = bd.getMeses().get(position).getNumeroAno();
        recarregarTela(numMes, numAno);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }

    public void recarregarTela(int numMes, int numAno) {

        Calendar c = Calendar.getInstance();
        //Acrescendo o valor 1 por o "Mês" vem contando a partir de zero.
        if (numMes == 0 && numAno == 0) {
            numMes = c.get(Calendar.MONTH) + 1;
            numAno = c.get(Calendar.YEAR);

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
            //Recarrega ListView
            lista = bd.getGastos(numMes, numAno);
            despesasAdapter = new DespesasAdapter(this, lista);
            listView = (ListView) findViewById(R.id.listView);
            listView.setAdapter(despesasAdapter);
        } else {

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
            //Recarrega ListView
            lista = bd.getGastos(numMes, numAno);
            despesasAdapter = new DespesasAdapter(this, lista);
            listView = (ListView) findViewById(R.id.listView);
            listView.setAdapter(despesasAdapter);
        }
    }
}
