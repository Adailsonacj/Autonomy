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
    DespesaModel despesa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resumo_despesa);

        EditText edDescricao = (EditText) findViewById(R.id.edDescricao);
        EditText edData = (EditText) findViewById(R.id.edData);
        EditText edValor = (EditText) findViewById(R.id.edValor);
        Intent vrIntent = getIntent();
        Bundle dados = vrIntent.getExtras();
        int id = dados.getInt("id");
        String descricao = dados.getString("descricao");
        String data = dados.getString("data");
        float valor = dados.getFloat("valor");
        despesa = new DespesaModel(id, data,descricao,valor,0);
        edDescricao.setText(dados.getString("descricao"));
        edData.setText(dados.getString("data"));
        edValor.setText(dados.getFloat("valor") + "");
    }

    public void cbPg(View v) {
        checked = ((CheckBox) v).isChecked();
        if (checked == true) {
        }
    }

    public void btnEditar(View v) {
        if (checked == true) {
            despesa.setPg(1);
        }
        BancoController crud = new BancoController(getBaseContext(), "gasto", 1);
        //Método para editar Despesa.
        if (crud.atualizaDespesa(despesa) == true) {
            Snackbar.make(v, "Concluído!" + despesa.getPg(), Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        } else {
            Snackbar.make(v, "Houve um erro!", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }
    }
}
