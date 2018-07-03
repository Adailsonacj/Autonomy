package com.example.adailson.confii;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.adailson.confii.daos.BancoController;
import com.example.adailson.confii.model.DespesaModel;
import com.example.adailson.confii.model.FundoModel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Fundos extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Bundle vrDados = new Bundle();
    BancoController bd;
    ArrayList<FundoModel> lista;
    FundosAdapter fundosAdapter;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fundos);

        bd = new BancoController(getBaseContext(), "gasto", 1);
        Intent vrIntent = getIntent();
        Bundle dados = vrIntent.getExtras();
        //Lista sendo preenchida com despesas do mês atual/ Parâmetros vindos da tela principal
        recarregarTela(0, 0);
        //Objeto Spinner, preenchendo e métodos de seleção
        List<String> mesesNomes = new ArrayList();
        for (int i = 0; i < bd.getMesesFundos().size(); i++) {
            mesesNomes.add(bd.getMesesFundos().get(i).getNome());
        }
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, mesesNomes);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(this);

        //Clique na célula
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                FundoModel ixFundo = fundosAdapter.getItem(i);
                Intent it = new Intent(Fundos.this, ResumoDespesa.class);
                vrDados.putInt("id", ixFundo.getId());
                vrDados.putString("descricao", ixFundo.getNome());
                vrDados.putString("data", ixFundo.getData());
                vrDados.putFloat("valor", ixFundo.getValorEntra());
                it.putExtras(vrDados);
                startActivity(it);
                finish();
            }
        });
    }

    public void btnAddFundo(View v) {
        Intent vrintention = new Intent(this, AddFundo.class);
        startActivity(vrintention);
        finish();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String nome = parent.getItemAtPosition(position).toString();
        int numMes = bd.getMesesFundos().get(position).getNumeroMes();
        int numAno = bd.getMesesFundos().get(position).getNumeroAno();
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
            lista = bd.getFundos(numMes, numAno);
            int ii = lista.size();
            fundosAdapter = new FundosAdapter(this, lista);
            listView = (ListView) findViewById(R.id.listView);
            listView.setAdapter(fundosAdapter);
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
            lista = bd.getFundos(numMes, numAno);
            fundosAdapter = new FundosAdapter(this, lista);
            listView = (ListView) findViewById(R.id.listView);
            listView.setAdapter(fundosAdapter);
        }
    }
}
