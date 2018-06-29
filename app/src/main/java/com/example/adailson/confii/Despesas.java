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

public class Despesas extends Activity {

    Bundle vrDados = new Bundle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        BancoController bd = new BancoController(getBaseContext(), "gasto", 1);
        Intent vrIntent = getIntent();
        Bundle dados = vrIntent.getExtras();
        ArrayList<DespesaModel> lista = bd.getGastos(dados.getInt("numeroMes"), dados.getInt("numeroAno"));


        final DespesasAdapter despesasAdapter = new DespesasAdapter(this, lista);
        super.onCreate(savedInstanceState);



        // Spinner element
        Spinner spinner = (Spinner) findViewById(R.id.spinner);


        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add("Automobile");
        categories.add("Business Services");
        categories.add("Computers");
        categories.add("Education");
        categories.add("Personal");
        categories.add("Travel");

        Spinner spn1 = (Spinner) findViewById(R.id.spinner);
        //Cria um ArrayAdapter usando um padrão de layout da classe R do android, passando o ArrayList nomes
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, categories);
        ArrayAdapter<String> spinnerArrayAdapter = arrayAdapter;
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spn1.setAdapter(spinnerArrayAdapter);



        TextView twMes = (TextView) findViewById(R.id.twMes);
        twMes.setText(dados.getString("nome"));
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
}
