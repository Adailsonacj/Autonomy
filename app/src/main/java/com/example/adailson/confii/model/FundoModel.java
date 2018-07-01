package com.example.adailson.confii.model;

public class FundoModel {
    private int id;
    private String nome;
    private String data;
    private float valorEntra;
    private float valorRest;

    public FundoModel(int id,  String nome, String data, float valorEntra, float valorRest) {
        this.id = id;
        this.nome = nome;
        this.data = data;
        this.valorEntra = valorEntra;
        this.valorRest = valorRest;
    }

    public float getValorEntra() {
        return valorEntra;
    }

    public void setValorEntra(float valorEntra) {
        this.valorEntra = valorEntra;
    }

    public float getValorRest() {
        return valorRest;
    }

    public void setValorRest(float valorRest) {
        this.valorRest = valorRest;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
