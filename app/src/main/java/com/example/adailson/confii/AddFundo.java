package com.example.adailson.confii;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;

import com.example.adailson.confii.daos.BancoController;
import com.example.adailson.confii.model.DespesaModel;
import com.example.adailson.confii.model.FundoModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AddFundo extends AppCompatActivity {


    private EditText inputDate;
    private EditText inputDescricao;
    private EditText inputValor;
    private String dataStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_fundo);

        final CalendarView calendarView = (CalendarView) findViewById(R.id.calendarView);

        //Iniciando componentes
        inputDate = (EditText) findViewById(R.id.inputDate);
        inputDescricao = (EditText) findViewById(R.id.inputDescricao);
        inputValor = (EditText) findViewById(R.id.inputValor);

        // quando selecionado alguma data diferente da padrão
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                //Intent intent = new Intent(ActivityAddDespesa.this, MainActivity.class);
                //intent.putExtra("dataLongMiliseconds",
                //      (Long) calendarView.getDate());
                //startActivity(intent);
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                Calendar calendar = Calendar.getInstance();
                calendar.set(year, month, dayOfMonth);
                Date data = calendar.getTime();
                dataStr = "" + dateFormat.format(data) + "";
                inputDate.setText(dataStr);
            }
        });
    }

    public void salvaButton(View v) {

        BancoController crud = new BancoController(getBaseContext(), "gasto", 1);
        String descricao = inputDescricao.getText().toString();
        float valor = Float.parseFloat(inputValor.getText().toString());
        if (dataStr == null) {
            Snackbar.make(v, "Escolha a data", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        } else {
            FundoModel fundo = new FundoModel(0, dataStr, descricao, valor, valor);
            if (crud.insereFundo(fundo) == true) {
                Snackbar.make(v, "Fundo Inserido", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }else{
                Snackbar.make(v, "Ocorreu um erro", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
            //finish();
        }
    }

}
