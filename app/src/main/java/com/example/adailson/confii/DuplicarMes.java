package com.example.adailson.confii;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.adailson.confii.daos.BancoController;
import com.example.adailson.confii.model.MesModel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class DuplicarMes extends AppCompatActivity {

    private Calendar myCalendar = Calendar.getInstance();
    private DatePickerDialog.OnDateSetListener date;
    private EditText setSelecMes;
    private String mesForm = null;
    private Spinner spinnerMesAnt;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_duplicar_mes);

        setSelecMes = (EditText) findViewById(R.id.setSelecMes);
        date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                if (myCalendar.get(Calendar.MONTH) == 1) {
                    mesForm = "Janeiro/" + myCalendar.get(Calendar.YEAR);
                }
                if (myCalendar.get(Calendar.MONTH) == 2) {
                    mesForm = "Fevereiro/" + myCalendar.get(Calendar.YEAR);
                }
                if (myCalendar.get(Calendar.MONTH) == 3) {
                    mesForm = "Março/" + myCalendar.get(Calendar.YEAR);
                }
                if (myCalendar.get(Calendar.MONTH) == 4) {
                    mesForm = "Abril/" + myCalendar.get(Calendar.YEAR);
                }
                if (myCalendar.get(Calendar.MONTH) == 5) {
                    mesForm = "Maio/" + myCalendar.get(Calendar.YEAR);
                }
                if (myCalendar.get(Calendar.MONTH) == 6) {
                    mesForm = "Junho/" + myCalendar.get(Calendar.YEAR);
                }
                if (myCalendar.get(Calendar.MONTH) == 7) {
                    mesForm = "Julho/" + myCalendar.get(Calendar.YEAR);
                }
                if (myCalendar.get(Calendar.MONTH) == 8) {
                    mesForm = "Agosto/" + myCalendar.get(Calendar.YEAR);
                }
                if (myCalendar.get(Calendar.MONTH) == 9) {
                    mesForm = "Setembro/" + myCalendar.get(Calendar.YEAR);
                }
                if (myCalendar.get(Calendar.MONTH) == 10) {
                    mesForm = "Outubro/" + myCalendar.get(Calendar.YEAR);
                }
                if (myCalendar.get(Calendar.MONTH) == 11) {
                    mesForm = "Novembro/" + myCalendar.get(Calendar.YEAR);
                }
                if (myCalendar.get(Calendar.MONTH) == 12) {
                    mesForm = "Dezembro/" + myCalendar.get(Calendar.YEAR);
                }

                setSelecMes.setText(mesForm);

            }
        };
        setSelecMes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(DuplicarMes.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });




        spinnerMesAnt = (Spinner) findViewById(R.id.spinnerMesAnt);
        BancoController bd = new BancoController(getBaseContext(), "gasto", 1);
        List<String> mesesNomes = new ArrayList();
        for (int i = 0; i < bd.getMeses().size(); i++) {
            mesesNomes.add(bd.getMeses().get(i).getNome());
        }
        //Objeto Spinner, preenchendo e métodos de seleção
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, mesesNomes);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMesAnt.setAdapter(arrayAdapter);





    }
}
