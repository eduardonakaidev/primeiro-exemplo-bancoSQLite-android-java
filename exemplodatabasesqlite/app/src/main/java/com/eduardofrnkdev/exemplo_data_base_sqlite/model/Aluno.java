package com.eduardofrnkdev.exemplo_data_base_sqlite.model;

public class Aluno {
    private int ra;
    private String nome;
    public Aluno(){}

    public Aluno(int ra, String nome) {
        this.ra = ra;
        this.nome = nome;
    }

    public int getRa() {
        return ra;
    }

    public void setRa(int ra) {
        this.ra = ra;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
