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
        BancoController bd = new BancoController(getBaseContext(), "gasto",1);

        ArrayList meses = bd.getMeses();
        /*if (bd.getMeses() != null) {
            for (int i = 0; i < bd.getMeses().size(); i++) {
                //if(bd.getMeses().get(i).equals(01)){
                meses.add(bd.getMeses().get(i));
                //}
            }
        }
        /*ArrayList meses = new ArrayList();
        meses.add("Janeiro/2018");
        meses.add("Fevereiro/2018");*/

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meses);
        ListView listView = (ListView) findViewById(R.id.lvwMes);
        listView.setAdapter(new MesAdapter(this, meses));
    }
}
