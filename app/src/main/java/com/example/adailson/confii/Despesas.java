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
import java.util.List;

public class Despesas extends Activity  implements AdapterView.OnItemSelectedListener  {

    Bundle vrDados = new Bundle();
    BancoController bd;
    ArrayList<DespesaModel> lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_despesas);
        bd = new BancoController(getBaseContext(), "gasto", 1);
        Intent vrIntent = getIntent();
        Bundle dados = vrIntent.getExtras();
        //Lista sendo preenchida com despesas do mês atual/ Parâmetros vindos da tela principal
        carregaListView(dados.getInt("numeroMes"), dados.getInt("numeroAno"));

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

        //Seta nome Mês selecionado direto no cabeçalho da tela Despesas
        TextView twMes = (TextView) findViewById(R.id.twMes);
        twMes.setText(dados.getString("nome"));

        //ListView
        final DespesasAdapter despesasAdapter = new DespesasAdapter(this, lista);
        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(despesasAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                DespesaModel ixDespesa = despesasAdapter.getItem(i);
                Intent it = new Intent(Despesas.this, ResumoDespesa.class);
                vrDados.putInt("id", ixDespesa.getId());
                vrDados.putString("descricao", ixDespesa.getDescricao());
                vrDados.putString("data", ixDespesa.getData());
                vrDados.putFloat("valor", ixDespesa.getValor());
                vrDados.putInt("pg", ixDespesa.getPg());
                it.putExtras(vrDados);
                startActivity(it);
                finish();
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


        String nome = parent.getItemAtPosition(position).toString();
        int numMes = bd.getMeses().get(position).getNumeroMes();
        int numAno = bd.getMeses().get(position).getNumeroAno();
        carregaListView(numMes, numAno);

        Toast.makeText(Despesas.this, "Nome Selecionado: " + numMes +"/"+ numAno, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void carregaListView(int numMes, int numAno){
        lista = bd.getGastos(numMes, numAno);
    }
}
