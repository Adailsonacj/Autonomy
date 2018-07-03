package com.example.adailson.confii;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.adailson.confii.model.FundoModel;

import java.util.ArrayList;

public class FundosAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<FundoModel> lista;

    public FundosAdapter(Context context, ArrayList<FundoModel> lista) {
        this.context = context;
        this.lista = lista;
    }

    @Override
    public int getCount() {
        return lista.size();
    }

    @Override
    public FundoModel getItem(int position) {
        return lista.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        FundoModel fundo = lista.get(position);
        View layout;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            layout = inflater.inflate(R.layout.celula_fundos, null);
        } else {
            layout = convertView;
        }

        TextView descricao = (TextView) layout.findViewById(R.id.twDescricao);
        descricao.setText(fundo.getNome());

        TextView mes = (TextView) layout.findViewById(R.id.twMes);
        mes.setText(fundo.getData());

        TextView valor = (TextView) layout.findViewById(R.id.twValorEntra);
        valor.setText("R$" + fundo.getValorEntra());

        TextView valorRest = (TextView) layout.findViewById(R.id.twValorRest);
        valorRest.setText("R$" + fundo.getValorRest());

        return layout;
    }
}
