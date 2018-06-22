package com.example.adailson.confi;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.example.adailson.confi.model.Despesa;

import java.util.ArrayList;

import static com.example.adailson.confi.database.CamadaBanco.context;


public class DespesasAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Despesa> lista;

    public DespesasAdapter(Context context, ArrayList<Despesa> lista) {
        this.context = context;
        this.lista = lista;
    }

    @Override
    public int getCount() {
        return lista.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Despesa despesa = lista.get(position);
        View layout;

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            layout = inflater.inflate(R.layout.activity_celula_despesa, null);
        } else {
            layout = convertView;
        }

        TextView descricao = (TextView) layout.findViewById(R.id.twDescricao);
        descricao.setText(despesa.getDescricao());

        TextView data = (TextView) layout.findViewById(R.id.twData);
        data.setText(despesa.getData());

        TextView valor = (TextView) layout.findViewById(R.id.twValor);
        valor.setText(despesa.getData());

        if (position % 2 == 0) {
            layout.setBackgroundColor(Color.YELLOW);
        }
        return layout;
    }
}
