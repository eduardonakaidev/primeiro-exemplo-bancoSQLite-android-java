package com.eduardofrnkdev.exemplo_data_base_sqlite.model;

public class Professor {
    private int idProfessor;
    private int matricula ;
    private String nome ;

    public Professor(int idProfessor, int matricula, String nome) {
        this.idProfessor = idProfessor;
        this.matricula = matricula;
        this.nome = nome;
    }

    public Professor() {

    }

    public int getIdProfessor() {
        return idProfessor;
    }

    public void setIdProfessor(int idProfessor) {
        this.idProfessor = idProfessor;
    }

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
