package com.example.adailson.confi.daos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.example.adailson.confi.database.CamadaBanco;
import com.example.adailson.confi.model.Gasto;

import java.util.ArrayList;

public class BancoController {

        private SQLiteDatabase db;
        private CamadaBanco banco;

        public BancoController(Context context, String name, int version){
            banco = new CamadaBanco(context, name, version);
        }

        public void insereGasto(int dia, int mes, int ano, String descricao, String valor){
            db = banco.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put("dia", dia);
            contentValues.put("mes", mes);
            contentValues.put("ano", ano);
            contentValues.put("descricao", descricao);
            contentValues.put("valor", valor);

            db.insert("gastos", null, contentValues);
            Toast.makeText(CamadaBanco.context, "Gasto inserido", Toast.LENGTH_SHORT).show();
        }
    }