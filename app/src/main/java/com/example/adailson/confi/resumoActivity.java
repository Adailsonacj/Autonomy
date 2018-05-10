package com.example.adailson.confi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.adailson.confi.daos.BancoController;

public class resumoActivity extends AppCompatActivity {
    private TextView verGasto;
    private TextView verEconomia;
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resumo);
        BancoController crud = new BancoController(getBaseContext(), "gasto", 1);
        crud.getGastos(4);
        spinner = (Spinner) findViewById(R.id.spinner);
        setContentView(R.layout.activity_resumo);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, crud.getMeses());
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinner.setAdapter(arrayAdapter);
        verGasto = (TextView) findViewById(R.id.verGasto);
        verGasto.setText("R$" + crud.getGastos(4));
        verEconomia = (TextView) findViewById(R.id.verEconomia);
        verEconomia.setText("R$" + crud.getMeses());
    }
}
