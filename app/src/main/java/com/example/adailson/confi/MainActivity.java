package com.example.adailson.confi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void btnAdicionarDespesa(View view) {
        Intent vrintention = new Intent(this, ActivityAddDespesa.class);
        startActivity(vrintention);
    }

    public void btnVisualizarGastos(View v) {
        Intent vrintention = new Intent(this, ActivityDespesas.class);
        startActivity(vrintention);
    }
}
