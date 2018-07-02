package com.example.adailson.confii.model;

public class DespesaModel {
    private int id;
    private String data;
    private String descricao;
    private float valor;
    private int pg;
    private int idFundo;

    public DespesaModel(int id, String data, String descricao, float valor, int pg, int idFundo) {
        this.idFundo = idFundo;
        this.id = id;
        this.data = data;
        this.descricao = descricao;
        this.valor = valor;
        this.pg = pg;
    }

    public int getIdFundo() {
        return idFundo;
    }

    public void setIdFundo(int idFundo) {
        this.idFundo = idFundo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
