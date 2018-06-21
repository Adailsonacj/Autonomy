package com.example.adailson.confi.model;

public class Gasto {
    private String data;
    private String descricao;
    private float valor;
    private int pg;

    public Gasto(String data, String descricao, float valor, int pg) {
        this.data = data;
        this.descricao = descricao;
        this.valor = valor;
        this.pg = pg;
    }

    public int getPg() {
        return pg;
    }

    public void setPg(int pg) {
        this.pg = pg;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }
}
