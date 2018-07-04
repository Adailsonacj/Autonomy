package com.example.adailson.confii;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.adailson.confii.daos.BancoController;
import com.example.adailson.confii.model.DespesaModel;

public class ResumoDespesa extends AppCompatActivity {

    Bundle vrDados = new Bundle();
    private boolean checked;
    private DespesaModel despesa;
    private String descricao;
    private String data;
    private float valorNovo;
    private float valorAnt;
    private int pg;
    int idDespesa;
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
        FloatingActionButton btnExcluir = (FloatingActionButton) findViewById(R.id.btnExcuir);
        CheckBox cbPg = (CheckBox) findViewById(R.id.checkBox);
        TableRow rowPg = (TableRow) findViewById(R.id.rowPg);
        Intent vrIntent = getIntent();
        Bundle dados = vrIntent.getExtras();
        idDespesa = dados.getInt("idDespesa");
        descricao = dados.getString("descricao");
        data = dados.getString("data");
        valorAnt = dados.getFloat("valor");
        pg = dados.getInt("pg");
        idFundo = dados.getInt("idFundo");
        despesa = new DespesaModel(idDespesa, data, descricao, valorNovo, 0, idFundo);

        if (pg == 1) {
            btnExcluir.setClickable(false);
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
    }

    public void btnEditar(View v) {
        valorNovo = Float.parseFloat(edValor.getText().toString());
        if (pg == 0) {
            if (checked == true) {
                despesa.setPg(1);
            }
            BancoController crud = new BancoController(getBaseContext(), "gasto", 1);
            float allDespesasFundo = 0;
            for (int i = 0; i < crud.getAllDespesaFundo(idFundo).size(); i++) {
                allDespesasFundo += crud.getAllDespesaFundo(idFundo).get(i).getValor();
            }
            //variável que guarda o Valor restante de determinado fundo
            float valorTotalFundo = crud.getValorFundoId(idFundo);
            //verifica se o valor da nova despesa somado ao valor de todas as outras despesas relacionadas ao mesmo fundo
            //uçtrapassam ao valor restante do fundo.
            if (valorNovo + allDespesasFundo - valorAnt <= valorTotalFundo) {
                if (checked == true) {
                    float valorRest = crud.getValorRestFundoId(idFundo);
                    crud.setValorRest(idFundo, valorRest - valorNovo);
                }
                //Método para editar Despesa.
                descricao = edDescricao.getText().toString();
                despesa.setDescricao(descricao);
                despesa.setValor(valorNovo);
                if (crud.atualizaDespesa(despesa) == true) {
                    Snackbar.make(v, "Concluído!" + despesa.getPg(), Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                } else {
                    Snackbar.make(v, "Houve um erro!", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
                Intent it = new Intent(ResumoDespesa.this, Despesas.class);
                startActivity(it);
                finish();
            } else {
                Snackbar.make(v, "O fundo não possui saldo sulficiente.", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
        }
    }

    public void btnExcuir(View v) {
        BancoController crud = new BancoController(getBaseContext(), "gasto", 1);
        crud.deleteDespesa(idDespesa);
        Intent it = new Intent(ResumoDespesa.this, Despesas.class);
        startActivity(it);
        finish();
    }
}
