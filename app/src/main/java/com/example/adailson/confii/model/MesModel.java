package com.example.adailson.confii.model;

public class MesModel {
    private String nome;
    private int numeroMes;
    private int numeroAno;
    public MesModel(String nome, int numeroMes, int numeroAno){
        this.nome = nome;
        this.numeroMes = numeroMes;
        this.numeroAno = numeroAno;
    }

    public int getNumeroMes() {
        return numeroMes;
    }

    public int getNumeroAno() {
        return numeroAno;
    }

    public void setNumeroAno(int numeroAno) {
        this.numeroAno = numeroAno;
    }

    public void setNumeroMes(int numeroMes) {
        this.numeroMes = numeroMes;
    }

    public String getNome() {

        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
