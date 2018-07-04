package com.example.adailson.confii;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.adailson.confii.daos.BancoController;
import com.example.adailson.confii.model.FundoModel;

public class ResumoFundo extends AppCompatActivity {

    private boolean checked;
    private FundoModel fundo;
    private String descricao;
    private String data;
    private float valorEntraNovo;
    private float valorEntraAnti;
    private float valorRest;
    private int idDespesa;
    private int idFundo;
    private EditText edDescricao;
    private TextView twData;
    private TextView twValorRest;
    private EditText edValor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resumo_fundo);

        edDescricao = (EditText) findViewById(R.id.edDescricao);
        twData = (TextView) findViewById(R.id.twData);
        edValor = (EditText) findViewById(R.id.edValor);
        twValorRest = (TextView) findViewById(R.id.twValorRest);
        FloatingActionButton btnExcluir = (FloatingActionButton) findViewById(R.id.btnExcuir);
        Button btnEditar = (Button) findViewById(R.id.btnEditar);

        Intent vrIntent = getIntent();
        Bundle dados = vrIntent.getExtras();

        idDespesa = dados.getInt("idDespesa");
        descricao = dados.getString("descricao");
        data = dados.getString("data");
        valorEntraAnti = dados.getFloat("valorEntra");
        valorRest = dados.getFloat("valorRest");
        idFundo = dados.getInt("idFundo");

        edDescricao.setText(descricao);
        twData.setText(data);
        edValor.setText(valorEntraAnti + "");
        twValorRest.setText(valorRest + "");

        BancoController crud = new BancoController(getBaseContext(), "gasto", 1);
        if (crud.getAllDespesaFundo(idFundo).size() != 0) {
            edDescricao.setEnabled(false);
            edValor.setEnabled(false);
            btnExcluir.setClickable(false);
            btnEditar.setEnabled(false);
        }
    }

    public void btnEditar(View v) {
        //Valor novo dado pelo usuário sendo atribuido a variável
        valorEntraNovo = Float.parseFloat(edValor.getText().toString());
        descricao = edDescricao.getText().toString();
        BancoController crud = new BancoController(getBaseContext(), "gasto", 1);
        fundo = new FundoModel(idFundo, descricao, data, valorEntraNovo, valorEntraNovo);
        if (crud.atualizaFundo(fundo) == true) {
            Snackbar.make(v, "Concluído!", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
            Intent it = new Intent(ResumoFundo.this, Fundos.class);
            startActivity(it);
            finish();
        }
    }

    public void btnExcuir(View v) {
        BancoController crud = new BancoController(getBaseContext(), "gasto", 1);
        crud.deleteDespesa(idDespesa);
        Intent it = new Intent(ResumoFundo.this, Fundos.class);
        startActivity(it);
        finish();
    }
}
