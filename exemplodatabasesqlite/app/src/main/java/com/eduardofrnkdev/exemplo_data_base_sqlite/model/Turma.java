package com.eduardofrnkdev.exemplo_data_base_sqlite.model;

public class Turma {
    private int idTurma ;
    private String curso ;
    private int anoInicio ;
    private int anoFim ;

    public Turma(int idTurma, String curso, int anoInicio, int anoFim) {
        this.idTurma = idTurma;
        this.curso = curso;
        this.anoInicio = anoInicio;
        this.anoFim = anoFim;
    }

    public Turma() {

    }

    public int getIdTurma() {
        return idTurma;
    }

    public void setIdTurma(int idTurma) {
        this.idTurma = idTurma;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public int getAnoInicio() {
        return anoInicio;
    }

    public void setAnoInicio(int anoInicio) {
        this.anoInicio = anoInicio;
    }

    public int getAnoFim() {
        return anoFim;
    }

    public void setAnoFim(int anoFim) {
        this.anoFim = anoFim;
    }
}
