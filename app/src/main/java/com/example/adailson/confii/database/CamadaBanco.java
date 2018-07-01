package com.example.adailson.confii.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CamadaBanco extends SQLiteOpenHelper {
    //String[] scripCriaBanco = {"create table gastos (id integer primary key autoincrement, data text not null, descricao text not null, valor real not null,  pg integer not null);"};

    private static final String[] tabelaGastos = {"create table gastos (id integer primary key autoincrement, data text not null, descricao text not null, valor real not null, pg integer not null, idFundo integer);"};
    private static final String[] tabelaFundos = {"create table fundos (id integer primary key autoincrement, data text not null, nome text not null, valorEntra real not null, valorRest real);"};

    public static Context context;

    public CamadaBanco(Context context, String name, int version) {
        super(context, name, null, version);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        for (int i = 0; i < tabelaGastos.length; i++) {
            db.execSQL(tabelaGastos[i]);
        }
        for (int i = 0; i < tabelaFundos.length; i++) {
            db.execSQL(tabelaFundos[i]);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}