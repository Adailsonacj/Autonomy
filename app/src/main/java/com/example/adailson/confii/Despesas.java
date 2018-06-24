package com.example.adailson.confii;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import com.example.adailson.confii.model.Despesa;
import java.util.ArrayList;

public class Despesas extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ArrayList<Despesa> lista = new ArrayList();
        for(int i =0; i<20; i++) {
            lista.add(new Despesa("12/03/201"+i+"", "Descrição teste" +i+"", 1+i, 53));
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_despesas);
        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(new DespesasAdapter(this, lista));
    }
}
