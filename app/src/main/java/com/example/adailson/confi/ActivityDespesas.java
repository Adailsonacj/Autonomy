package com.example.adailson.confi;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.adailson.confi.model.Despesa;

import java.util.ArrayList;

public class ActivityDespesas extends Activity {

    ListView listView = null;
    Bundle vrDados = new Bundle();
    ArrayList<Despesa> lista = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        lista.add(new Despesa("10/10;2010", "descricao teste", 10, 1));

        lista.add(new Despesa("10/10;2010", "descricao asdasdasd", 10, 1));

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_despesas);
        listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(new DespesasAdapter(this, lista));
    }
}
