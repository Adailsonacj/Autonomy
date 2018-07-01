package com.example.adailson.confii.daos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.Snackbar;
import android.widget.Toast;

import com.example.adailson.confii.database.CamadaBanco;
import com.example.adailson.confii.model.DespesaModel;
import com.example.adailson.confii.model.FundoModel;
import com.example.adailson.confii.model.MesModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class BancoController {

    private SQLiteDatabase db;
    private CamadaBanco banco;

    public BancoController(Context context, String name, int version) {
        banco = new CamadaBanco(context, name, version);
    }

    public boolean insereFundo(FundoModel fundo) {
        try {
            db = banco.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put("data", fundo.getData());
            contentValues.put("nome", fundo.getNome());
            contentValues.put("valorEntra", fundo.getValorEntra());
            contentValues.put("valorRest", fundo.getValorEntra());

            db.insert("fundos", null, contentValues);
            db.close();
            return true;
        } catch (Exception e) {
            db.close();
            return false;
        }
    }

    public ArrayList<MesModel> getMesesFundos() {
        ArrayList<MesModel> meses = new ArrayList();
        ArrayList<Integer> validacao = new ArrayList();
        db = banco.getReadableDatabase();

        Cursor cursor = db.query("fundos", new String[]{"id", "data", "nome"}, null, null, null, null, null);

        while (cursor.moveToNext()) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            char[] dataFormatada = cursor.getString(1).toCharArray();
            Calendar c = Calendar.getInstance();
            String dia = dataFormatada[0] + "" + dataFormatada[1];
            String mes = dataFormatada[3] + "" + dataFormatada[4];
            String ano = dataFormatada[6] + "" + dataFormatada[7] + "" + dataFormatada[8] + "" + dataFormatada[9];
            c.set(Integer.parseInt(ano), Integer.parseInt(mes), Integer.parseInt(dia));

            String mesForm = null;
            if (!validacao.contains(c.get(Calendar.MONTH))) {
                validacao.add(c.get(Calendar.MONTH));
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
                    mesForm = "Setembro/" + c.get(Calendar.YEAR);
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

                meses.add(new MesModel(mesForm, c.get(Calendar.MONTH), c.get(Calendar.YEAR)));
            }
        }
        db.close();
        return meses;
    }

    public ArrayList<FundoModel> getFundos(int mes, int ano){
        ArrayList<FundoModel> fundos = new ArrayList<>();
        try {

            db = banco.getReadableDatabase();
            Cursor cursor = db.query("fundos", new String[]{"id", "data", "nome", "valorEntra", "valorRest"}, null, null, null, null, null);

            while (cursor.moveToNext()) {
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                char[] dataFormatada = cursor.getString(1).toCharArray();
                Calendar c = Calendar.getInstance();
                String strDia = dataFormatada[0] + "" + dataFormatada[1];
                String strMes = dataFormatada[3] + "" + dataFormatada[4];
                String strAno = dataFormatada[6] + "" + dataFormatada[7] + "" + dataFormatada[8] + "" + dataFormatada[9];
                c.set(Integer.parseInt(strAno), Integer.parseInt(strMes), Integer.parseInt(strDia));
                // fundos.add(new Despesa(cursor.getInt(0),cursor.getInt(1), cursor.getInt(2),cursor.getString(3),cursor.getFloat(4)));
                if (c.get(Calendar.MONTH) == mes && c.get(Calendar.YEAR) == ano) {
                    fundos.add(new FundoModel(cursor.getInt(0), cursor.getString(2), cursor.getString(1), cursor.getFloat(3), cursor.getFloat(4)));
                }
            }
            int ii = fundos.size();
        }catch(Exception e){

        }
        db.close();
        return fundos;
    }

    public boolean insereGasto(DespesaModel despesa) {
        try {
            db = banco.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put("data", despesa.getData());
            contentValues.put("descricao", despesa.getDescricao());
            contentValues.put("valor", despesa.getValor());
            contentValues.put("pg", despesa.getPg());
            db.insert("gastos", null, contentValues);
            db.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean atualizaDespesa(DespesaModel despesa) {
        try {
            ContentValues contentValues = new ContentValues();
            String where;
            db = banco.getWritableDatabase();
            where = "id=" + despesa.getId() + "";
            String desc = despesa.getDescricao();
            contentValues.put("descricao", despesa.getDescricao());
            contentValues.put("pg", despesa.getPg());
            contentValues.put("data", despesa.getData());
            db.update("gastos", contentValues, where, null);
            db.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public ArrayList<DespesaModel> getGastos(int mes, int ano) {
        ArrayList<DespesaModel> despesas = new ArrayList<>();

        db = banco.getReadableDatabase();
        Cursor cursor = db.query("gastos", new String[]{"id", "data", "descricao", "valor", "pg"}, null, null, null, null, null);

        while (cursor.moveToNext()) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            char[] dataFormatada = cursor.getString(1).toCharArray();
            Calendar c = Calendar.getInstance();
            String strDia = dataFormatada[0] + "" + dataFormatada[1];
            String strMes = dataFormatada[3] + "" + dataFormatada[4];
            String strAno = dataFormatada[6] + "" + dataFormatada[7] + "" + dataFormatada[8] + "" + dataFormatada[9];
            c.set(Integer.parseInt(strAno), Integer.parseInt(strMes), Integer.parseInt(strDia));
            // despesas.add(new Despesa(cursor.getInt(0),cursor.getInt(1), cursor.getInt(2),cursor.getString(3),cursor.getFloat(4)));
            if (c.get(Calendar.MONTH) == mes && c.get(Calendar.YEAR) == ano) {
                despesas.add(new DespesaModel(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getFloat(3), cursor.getInt(4)));
            }
        }
        db.close();
        return despesas;
    }

    public ArrayList<MesModel> getMeses() {
        ArrayList<MesModel> meses = new ArrayList();
        ArrayList<Integer> validacao = new ArrayList();
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

            String mesForm = null;
            if (!validacao.contains(c.get(Calendar.MONTH))) {
                validacao.add(c.get(Calendar.MONTH));
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
                    mesForm = "Setembro/" + c.get(Calendar.YEAR);
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

                meses.add(new MesModel(mesForm, c.get(Calendar.MONTH), c.get(Calendar.YEAR)));
            }
        }
        db.close();
        return meses;
    }
}