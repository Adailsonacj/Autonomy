package com.example.adailson.confii;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.adailson.confii.daos.BancoController;
import com.example.adailson.confii.model.DespesaModel;
import com.example.adailson.confii.model.MesModel;

import java.util.ArrayList;

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
        setContentView(R.layout.activity_despesas);
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
