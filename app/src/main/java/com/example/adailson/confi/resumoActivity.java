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
    private String[] mesesStr = new String[12];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resumo);
        BancoController crud = new BancoController(getBaseContext(), "gasto", 1);


        Spinner spin = (Spinner) findViewById(R.id.spinner);
        //spin.setOnItemSelectedListener(this);
        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, crud.getMeses());
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(aa);


        verGasto = (TextView) findViewById(R.id.verGasto);
        verEconomia = (TextView) findViewById(R.id.verEconomia);

        verEconomia.setText(crud.getMeses().size() + "");
    }

    public void mesProx(View v) {
        BancoController crud = new BancoController(getBaseContext(), "gasto", 1);
        if (posicao < getListaMes().length) {
            posicao += getListaMes().length - getListaMes().length + 1;
            //float ii = crud.getGastos(getListaMes()[posicao]);
            verGasto.setText("R$" + getListaMes()[posicao]);
            verEconomia.setText("Position: " + posicao);
        } else {
        }
    }

    public void mesAtn(View v) {

    }

    public String[] setMeses(ArrayList bdMeses) {
        for(int i =0; i<bdMeses.size();i++){

            if(bdMeses.get(i) == "6"){
                mesesStr[0] = "agora";
            }
        }
        mesesStr[1] = "Fevereiro";
        mesesStr[2] = "Março";
        mesesStr[3] = "Abril";
        mesesStr[4] = "Maio";
        mesesStr[5] = "Junho";
        mesesStr[6] = "Julho";
        mesesStr[7] = "Agosto";
        mesesStr[8] = "Setembro";
        mesesStr[9] = "Outubro";
        mesesStr[10] = "Novembro";
        mesesStr[11] = "Dezembro";
        return mesesStr;
    }

    public int[] getListaMes() {
        BancoController crud = new BancoController(getBaseContext(), "gasto", 1);
        ArrayList<Integer> listaMes = crud.getMeses();
        int contador = listaMes.size();
        Mes = new int[contador];
        for (int i = 0; i < contador; i++) {
            Mes[i] = listaMes.get(i);
        }
        return Mes;
    }
}
