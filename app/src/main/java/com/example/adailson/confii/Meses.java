package com.example.adailson.confii;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.adailson.confii.daos.BancoController;
import com.example.adailson.confii.model.MesModel;

import java.util.ArrayList;

public class Meses extends Activity {

    Bundle vrDados = new Bundle();
    private int idBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        BancoController bd = new BancoController(getBaseContext(), "gasto", 1);
        ArrayList<MesModel> meses = bd.getMeses();
        final MesAdapter mesAdapter = new MesAdapter(this, meses);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meses);
        ListView listView = (ListView) findViewById(R.id.lvwMes);
        listView.setAdapter(mesAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                MesModel ixMes = mesAdapter.getItem(i);
                Toast.makeText(Meses.this, "Clicado " + ixMes.getNumeroMes(), Toast.LENGTH_SHORT).show();
                Intent it = new Intent(Meses.this, Despesas.class);
                vrDados.putString("nome", ixMes.getNome());
                vrDados.putInt("numeroMes", ixMes.getNumeroMes());
                vrDados.putInt("numeroAno", ixMes.getNumeroAno());
                it.putExtras(vrDados);
                startActivity(it);
            }
        });

    }
}
