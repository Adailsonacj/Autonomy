package com.example.adailson.confii;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.adailson.confii.daos.BancoController;
import com.example.adailson.confii.model.DespesaModel;

import java.util.ArrayList;

public class ResumoDespesa extends AppCompatActivity {

    Bundle vrDados = new Bundle();
    boolean checked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resumo_despesa);

        EditText edDescricao = (EditText) findViewById(R.id.edDescricao);
        EditText edData = (EditText) findViewById(R.id.edData);
        EditText edValor = (EditText) findViewById(R.id.edValor);
        Intent vrIntent = getIntent();
        Bundle dados = vrIntent.getExtras();
        edDescricao.setText(dados.getString("descricao"));
        edData.setText(dados.getString("data"));
        edValor.setText(dados.getFloat("valor") + "");


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.btnEditar);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public void cbPg(View v) {
        checked = ((CheckBox) v).isChecked();
        if (checked == true) {
            Toast.makeText(ResumoDespesa.this, "Clicado ", Toast.LENGTH_SHORT).show();
        }
    }

    public void btnEditar(View v) {
        int pg = 0;
        if (checked == true) {
            pg = 1;
        }
        BancoController crud = new BancoController(getBaseContext(), "gasto", 1);
        //Método para editar Despesa.
    }
}
