package com.example.adailson.confii;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.adailson.confii.daos.BancoController;
import com.example.adailson.confii.model.Despesa;

import java.util.ArrayList;

public class Meses extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        BancoController bd = new BancoController();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meses);
        ListView listView = (ListView) findViewById(R.id.lvwMes);
        listView.setAdapter(new MesAdapter(this, lista));
    }
}
