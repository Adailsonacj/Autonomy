package com.example.adailson.confii;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.adailson.confii.daos.BancoController;
import com.example.adailson.confii.model.DespesaModel;

import java.util.ArrayList;

public class ResumoDespesa extends AppCompatActivity {

    Bundle vrDados = new Bundle();
    private boolean checked;
    private DespesaModel despesa;
    private String descricao;
    private String data;
    private float valor;
    private int pg;
    private int idFundo;
    EditText edDescricao;
    TextView twData;
    EditText edValor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resumo_despesa);

        edDescricao = (EditText) findViewById(R.id.edDescricao);
        twData = (TextView) findViewById(R.id.twData);
        edValor = (EditText) findViewById(R.id.edValor);
        Intent vrIntent = getIntent();
        Bundle dados = vrIntent.getExtras();
        int id = dados.getInt("id");
        descricao = dados.getString("descricao");
        data = dados.getString("data");
        valor = dados.getFloat("valor");
        pg = dados.getInt("pg");
        idFundo = dados.getInt("idFundo");
        despesa = new DespesaModel(id, data, descricao, valor, 0, idFundo);
        CheckBox cbPg = (CheckBox) findViewById(R.id.checkBox);
        TableRow rowPg = (TableRow) findViewById(R.id.rowPg);
        if (pg == 1) {
            edDescricao.setEnabled(false);
            edValor.setEnabled(false);
            rowPg.setBackgroundColor(Color.parseColor("#A9F5E1"));
            cbPg.setChecked(true);
            cbPg.setEnabled(false);
        }
        edDescricao.setText(dados.getString("descricao"));
        twData.setText(dados.getString("data"));
        edValor.setText(dados.getFloat("valor") + "");
    }

    public void cbPg(View v) {
        checked = ((CheckBox) v).isChecked();
        if (checked == true) {
            BancoController crud = new BancoController(getBaseContext(), "gasto", 1);
            float valorRest = crud.getValorRestFundoId(idFundo);
            crud.setValorRest(idFundo, valorRest - valor);
        }
    }

    public void btnEditar(View v) {
        valor = Float.parseFloat(edValor.getText().toString());
        if (pg == 0) {
            if (checked == true) {
                despesa.setPg(1);
            }
            BancoController crud = new BancoController(getBaseContext(), "gasto", 1);
            float valorAllRest = 0;
            for (int i = 0; i < crud.getAllDespesaFundo(idFundo).size(); i++) {
                valorAllRest += crud.getAllDespesaFundo(idFundo).get(i).getValor();
            }
            //variável que guarda o Valor restante de determinado fundo
            float valorTotalFundo = crud.getValorFundoId(idFundo);
            //verifica se o valor da nova despesa somado ao valor de todas as outras despesas relacionadas ao mesmo fundo
            //uçtrapassam ao valor restante do fundo.
            if (valor + valorAllRest <= valorTotalFundo) {
                //Método para editar Despesa.
                descricao = edDescricao.getText().toString();
                despesa.setDescricao(descricao);
                despesa.setValor(valor);
                if (crud.atualizaDespesa(despesa) == true) {
                    Snackbar.make(v, "Concluído!" + despesa.getPg(), Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                } else {
                    Snackbar.make(v, "Houve um erro!", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
                finish();
            } else {
                Snackbar.make(v, "O fundo não possui saldo sulficiente.", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
        }
    }
}
