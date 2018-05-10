package com.example.adailson.confi.model;

public class Gasto {
    private int dia;
    private int mes;
    private int ano;
    private String descricao;
    private float valor;
    ;

    public Gasto(int dia, int mes, int ano, String descricao, float valor){
        this.dia=dia;
        this.mes=mes;
        this.ano=ano;
        this.descricao=descricao;
        this.valor=valor;
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
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
