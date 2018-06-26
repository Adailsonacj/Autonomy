package com.example.adailson.confii;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.adailson.confii.model.MesModel;

import java.util.ArrayList;

public class MesAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<MesModel> lista;

    public MesAdapter(Context context, ArrayList<MesModel> lista) {
        this.context = context;
        this.lista = lista;
    }

    @Override
    public int getCount() {
        return lista.size();
    }

    @Override
    public MesModel getItem(int position) {
        return lista.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MesModel mes = lista.get(position);
        View layout;

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            layout = inflater.inflate(R.layout.celula_meses, null);
        } else {
            layout = convertView;
        }

        TextView descricao = (TextView) layout.findViewById(R.id.twMesAno);
        descricao.setText(mes.getNome());
        return layout;
    }
}
