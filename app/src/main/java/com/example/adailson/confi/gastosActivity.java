package com.example.adailson.confi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Toast;

import com.example.adailson.confi.daos.BancoController;

public class gastosActivity extends AppCompatActivity {

    //Atributos
    private EditText inputDate;
    private EditText inputDescricao;
    private EditText inputValor;
    private int dia;
    private int mes;
    private int ano;
    private Button salvar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gastos);

        final CalendarView calendarView = (CalendarView) findViewById(R.id.calendarView);

        //Iniciando componentes
        inputDate = (EditText) findViewById(R.id.inputDate);
        inputDescricao = (EditText) findViewById(R.id.inputDescricao);
        inputValor = (EditText) findViewById(R.id.inputValor);
        salvar = (Button) findViewById(R.id.salvaButton);

        // quando selecionado alguma data diferente da padrão
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                //Intent intent = new Intent(gastosActivity.this, MainActivity.class);
                //intent.putExtra("dataLongMiliseconds",
                  //      (Long) calendarView.getDate());
                //startActivity(intent);
                mes = month;
                ano = year;
                dia = dayOfMonth;
                inputDate.setText(""+dayOfMonth+"/"+month+"/"+year+"");
            }
        });
    }
    public void salvaGasto(View v){
        BancoController crud = new BancoController(getBaseContext(), "gasto",1);
        String descricao = inputDescricao.getText().toString();
        float valor = Float.parseFloat(inputValor.getText().toString());
        crud.insereGasto(dia,mes,ano,descricao,valor);
    }
}