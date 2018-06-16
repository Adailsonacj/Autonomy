package com.example.adailson.confi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.adailson.confi.daos.BancoController;
import com.example.adailson.confi.database.CamadaBanco;

import java.util.ArrayList;
import java.util.List;

public class resumoActivity extends AppCompatActivity {
    private TextView verGasto;
    private TextView verEconomia;
    private Button btnProx;
    private Button btnAnt;
    private int[] Mes;
    private int posicao = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resumo);
        BancoController crud = new BancoController(getBaseContext(), "gasto", 1);

        verGasto = (TextView) findViewById(R.id.verGasto);
        verEconomia = (TextView) findViewById(R.id.verEconomia);
    }

    public void mesProx(View v) {
        BancoController crud = new BancoController(getBaseContext(), "gasto", 1);
        if(posicao < getListaMes().length){
            posicao += getListaMes().length - getListaMes().length +1;
            //float ii = crud.getGastos(getListaMes()[posicao]);
            verGasto.setText("R$" + getListaMes()[posicao]);
            verEconomia.setText("Position: "+posicao);
        }else{
        }
    }

    public void mesAtn(View v) {

    }

    public int[] getListaMes() {
        BancoController crud = new BancoController(getBaseContext(), "gasto", 1);
        ArrayList<Integer> listaMes = crud.getMeses();
        int contador = listaMes.size();
        Mes = new int[contador];
        for (int i = 0; i < contador ; i++) {
            Mes[i] = listaMes.get(i);
        }
        return Mes;
    }
}
