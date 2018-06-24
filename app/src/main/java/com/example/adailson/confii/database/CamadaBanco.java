package com.example.adailson.confii.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CamadaBanco extends SQLiteOpenHelper {

    String[] scripCriaBanco = {"create table gastos (id integer primary key autoincrement, data text not null, descricao text not null, valor real not null);"};
    //public final String apagaBancoSQL = "drop database if exists carro";
    public static Context context;

    public CamadaBanco(Context context, String name, int version) {
        super(context, name, null, version);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        for (int i = 0; i < scripCriaBanco.length;i++){
            db.execSQL(scripCriaBanco[i]);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}