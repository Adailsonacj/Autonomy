package com.example.adailson.confii;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.adailson.confii.model.Despesa;

public class Principal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
    }

    public void btnAdicionarDespesa(View view) {
        Intent vrintention = new Intent(this, AddDespesa.class);
        startActivity(vrintention);
    }

    public void btnVisualizarGastos(View v) {
        Intent vrintention = new Intent(this, Despesas.class);
        startActivity(vrintention);
    }
}
