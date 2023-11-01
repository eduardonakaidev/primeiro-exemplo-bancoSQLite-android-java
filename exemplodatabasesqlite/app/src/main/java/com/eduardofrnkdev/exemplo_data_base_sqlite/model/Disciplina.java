package com.eduardofrnkdev.exemplo_data_base_sqlite.model;

public class Disciplina {
    private int idDisciplina;
    private String descricao ;
    private int periodo ;
    private double cargaHoraria ;
    private int idProfessor ;

    public Disciplina(int idDisciplina, String descricao, int periodo, double cargaHoraria, int idProfessor) {
        this.idDisciplina = idDisciplina;
        this.descricao = descricao;
        this.periodo = periodo;
        this.cargaHoraria = cargaHoraria;
        this.idProfessor = idProfessor;
    }

    public Disciplina() {

    }


    public int getIdDisciplina() {
        return idDisciplina;
    }

    public void setIdDisciplina(int idDisciplina) {
        this.idDisciplina = idDisciplina;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getPeriodo() {
        return periodo;
    }

    public void setPeriodo(int periodo) {
        this.periodo = periodo;
    }

    public double getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(double cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    public int getIdProfessor() {
        return idProfessor;
    }

    public void setIdProfessor(int idProfessor) {
        this.idProfessor = idProfessor;
    }
}
