package com.example.adailson.confii.daos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.example.adailson.confii.database.CamadaBanco;
import com.example.adailson.confii.model.Despesa;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class BancoController {

    private SQLiteDatabase db;
    private CamadaBanco banco;

    public BancoController(Context context, String name, int version) {
        banco = new CamadaBanco(context, name, version);
    }

    public void insereGasto(String data, String descricao, float valor) {
        db = banco.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("data", data);
        contentValues.put("descricao", descricao);
        contentValues.put("valor", valor);
        contentValues.put("pg", 0);

        db.insert("gastos", null, contentValues);
        Toast.makeText(CamadaBanco.context, "Despesa inserida", Toast.LENGTH_SHORT).show();

    }

    public ArrayList<Despesa> getGastos(int mes) {
        ArrayList<Despesa> despesas = new ArrayList<>();

        db = banco.getReadableDatabase();
        Cursor cursor = db.query("despesas", new String[]{"id", "data", "descricao", "valor", "pg"}, null, null, null, null, null);

        while (cursor.moveToNext()) {
            // despesas.add(new Despesa(cursor.getInt(0),cursor.getInt(1), cursor.getInt(2),cursor.getString(3),cursor.getFloat(4)));
            if (cursor.getInt(2) == mes) {
                despesas.add(new Despesa(cursor.getString(1), cursor.getString(2), cursor.getFloat(3), cursor.getInt(4)));
            }
        }
        return despesas;
    }

    public ArrayList getMeses() {
        ArrayList<String> meses = new ArrayList();
        db = banco.getReadableDatabase();

        Cursor cursor = db.query("gastos", new String[]{"id", "data", "descricao", "valor", "pg"}, null, null, null, null, null);

        while (cursor.moveToNext()) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            char[] dataFormatada = cursor.getString(1).toCharArray();
            Calendar c = Calendar.getInstance();
            String dia = dataFormatada[0] + "" + dataFormatada[1];
            String mes = dataFormatada[3] + "" + dataFormatada[4];
            String ano = dataFormatada[6] + "" + dataFormatada[7] + "" + dataFormatada[8] + "" + dataFormatada[9];
            c.set(Integer.parseInt(ano), Integer.parseInt(mes), Integer.parseInt(dia));
            if (!meses.contains(c.get(Calendar.MONTH))) {
                String mesForm = null;

                if (c.get(Calendar.MONTH) == 1) {
                    mesForm = "Janeiro/" + c.get(Calendar.YEAR);
                }
                if (c.get(Calendar.MONTH) == 2) {
                    mesForm = "Fevereiro/" + c.get(Calendar.YEAR);
                }
                if (c.get(Calendar.MONTH) == 3) {
                    mesForm = "Março/" + c.get(Calendar.YEAR);
                }
                if (c.get(Calendar.MONTH) == 4) {
                    mesForm = "Abril/" + c.get(Calendar.YEAR);
                }
                if (c.get(Calendar.MONTH) == 5) {
                    mesForm = "Maio/" + c.get(Calendar.YEAR);
                }
                if (c.get(Calendar.MONTH) == 6) {
                    mesForm = "Junho/" + c.get(Calendar.YEAR);
                }
                if (c.get(Calendar.MONTH) == 7) {
                    mesForm = "Julho/" + c.get(Calendar.YEAR);
                }
                if (c.get(Calendar.MONTH) == 8) {
                    mesForm = "Agosto/" + c.get(Calendar.YEAR);
                }
                if (c.get(Calendar.MONTH) == 9) {
                    mesForm = "Setembbro/" + c.get(Calendar.YEAR);
                }
                if (c.get(Calendar.MONTH) == 10) {
                    mesForm = "Outubro/" + c.get(Calendar.YEAR);
                }
                if (c.get(Calendar.MONTH) == 11) {
                    mesForm = "Novembro/" + c.get(Calendar.YEAR);
                }
                if (c.get(Calendar.MONTH) == 12) {
                    mesForm = "Dezembro/" + c.get(Calendar.YEAR);
                }

                meses.add(mesForm);
            }
        }
        return meses;
    }
}