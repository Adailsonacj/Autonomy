package com.example.adailson.confi.daos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.example.adailson.confi.database.CamadaBanco;
import com.example.adailson.confi.model.Gasto;

import java.util.ArrayList;
import java.util.List;

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

        db.insert("gastos", null, contentValues);
        Toast.makeText(CamadaBanco.context, "Gasto inserido", Toast.LENGTH_SHORT).show();
    }

    public float getGastos(int mes) {
        float gasto = 0;
        ArrayList<Gasto> gastos = new ArrayList<>();

        db = banco.getReadableDatabase();
        Cursor cursor = db.query("gastos", new String[]{"id", "dia", "mes", "ano", "descricao", "valor"}, null, null, null, null, null);

        while (cursor.moveToNext()) {
            // gastos.add(new Gasto(cursor.getInt(0),cursor.getInt(1), cursor.getInt(2),cursor.getString(3),cursor.getFloat(4)));
            if (cursor.getInt(2) == mes) {
                gasto += cursor.getFloat(5);
            }
        }
        return gasto;
    }

    public ArrayList getMeses() {
        ArrayList<String> meses = new ArrayList();
        db = banco.getReadableDatabase();
        Cursor cursor = db.query("gastos", new String[]{"id", "data", "descricao", "valor"}, null, null, null, null, null);

        while (cursor.moveToNext()) {
            for(int i=0; i < cursor.getString(1).length(); i++){

            }
            if (!meses.contains(cursor.getString(1))) {
                meses.add(cursor.getString(1));
            }
        }
        return meses;
    }
}