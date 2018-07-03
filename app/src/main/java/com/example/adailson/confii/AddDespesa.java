package com.example.adailson.confii;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.adailson.confii.daos.BancoController;
import com.example.adailson.confii.model.DespesaModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class AddDespesa extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    //Atributos
    private EditText inputDate;
    private EditText inputDescricao;
    private EditText inputValor;
    private String dataStr;
    Bundle vrDados = new Bundle();
    private int numMes, numAno;
    private Button btnSalvar;
    private Spinner spinnerFundo;
    List<Integer> ListIdFundo;
    private int idFundo;
    Calendar myCalendar = Calendar.getInstance();
    DatePickerDialog.OnDateSetListener date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_despesa);

        final CalendarView calendarView = (CalendarView) findViewById(R.id.calendarView);

        //Iniciando componentes
        inputDate = (EditText) findViewById(R.id.inputDate);
        inputDescricao = (EditText) findViewById(R.id.inputDescricao);
        spinnerFundo = (Spinner) findViewById(R.id.spinerFundo);

        //Chamando DataPicker ao clicar no EditText
        /*
        date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            }
        };
        inputDescricao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    // TODO Auto-generated method stub
                    new DatePickerDialog(AddDespesa.this, date, myCalendar
                            .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                            myCalendar.get(Calendar.DAY_OF_MONTH)).show();

            }
        });
        */

        inputValor = (EditText) findViewById(R.id.inputValor);
        btnSalvar = (Button) findViewById(R.id.salvaButton);

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
                numMes = month + 1;
                numAno = year;
                if (carregaSpinner(month + 1, year) == true) {
                    Date data = calendar.getTime();
                    dataStr = "" + dateFormat.format(data) + "";
                    inputDate.setText(dataStr);
                } else {
                    Intent it = new Intent(AddDespesa.this, Principal.class);
                    vrDados.putInt("semFundo", 1);
                    it.putExtras(vrDados);
                    startActivity(it);
                    finish();
                }
            }
        });
        spinnerFundo.setOnItemSelectedListener(this);
    }

    public void salvaGasto(View v) {
        BancoController crud = new BancoController(getBaseContext(), "gasto", 1);
        String descricao = inputDescricao.getText().toString();
        float valor = Float.parseFloat(inputValor.getText().toString());
        if (dataStr == null || descricao == "" || valor == 0.0) {
            Snackbar.make(v, "Preencha os campos corretamente!", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        } else {
            //Valor de todas as despesas relacionadas a determinado fundo
            float valorAllRest = 0;
            for (int i = 0; i < crud.getAllDespesaFundo(idFundo).size(); i++) {
                valorAllRest += crud.getAllDespesaFundo(idFundo).get(i).getValor();
            }
            //Criando despesa com valores inseridos por usuário
            DespesaModel despesa = new DespesaModel(0, dataStr, descricao, valor, 0, idFundo);
            //variável que guarda o Valor restante de determinado fundo
            float valorRest = crud.getValorRestFundoId(idFundo);
            //verifica se o valor da nova despesa somado ao valor de todas as outras despesas relacionadas ao mesmo fundo
            //uçtrapassam ao valor restante do fundo.
            if (valor + valorAllRest <= valorRest) {
                if (crud.insereGasto(despesa) == true) {
                    Snackbar.make(v, "Despesa Inserida", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                    finish();
                } else {
                    Snackbar.make(v, "Houve um problema..", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                }
            } else {
                Snackbar.make(v, "O fundo selecionado não possui saldo sulficiente.", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
        }
    }

    public boolean carregaSpinner(int numMes, int numAno) {
        BancoController bd = new BancoController(getBaseContext(), "gasto", 1);

        //Objeto Spinner, preenchendo e métodos de seleção
        List<String> listStrFundos = new ArrayList();
        ListIdFundo = new ArrayList<>();

        for (int i = 0; i < bd.getFundos(numMes, numAno).size(); i++) {
            ListIdFundo.add(bd.getFundos(numMes, numAno).get(i).getId());
            listStrFundos.add(bd.getFundos(numMes, numAno).get(i).getNome());
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, listStrFundos);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinnerFundo.setAdapter(arrayAdapter);
        if (listStrFundos.size() >= 1) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        idFundo = ListIdFundo.get(position);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }
}