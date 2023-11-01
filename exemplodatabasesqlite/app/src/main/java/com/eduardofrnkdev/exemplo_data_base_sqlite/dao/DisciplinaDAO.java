package com.eduardofrnkdev.exemplo_data_base_sqlite.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.eduardofrnkdev.exemplo_data_base_sqlite.helper.SQLiteDataHelper;
import com.eduardofrnkdev.exemplo_data_base_sqlite.model.Aluno;
import com.eduardofrnkdev.exemplo_data_base_sqlite.model.Disciplina;

import java.util.ArrayList;

public class DisciplinaDAO implements IGenericDao<Disciplina>{
    // Variavel responsavel por abrir conexão
    private SQLiteOpenHelper openHelper;

    // Database
    private SQLiteDatabase baseDados;

    //nome das colunas da tabela ;
    private String[]colunas = {"ID_DISCIPLINA","DESCRICAO","PERIODO","CARGA_HORARIA","ID_PROFESSOR"};

    //nome da tabela
    private String tabela = "DISCIPLINA";

    //Contexto(view)
    private Context context;

    private static DisciplinaDAO instancia ;

    public  static DisciplinaDAO getInstance(Context context){
        if(instancia == null){
            return instancia = new DisciplinaDAO(context);
        }else {
            return  instancia;
        }
    }
    private DisciplinaDAO(Context context){
        this.context = context;

        // Abrir a conxão com o dataBase
        openHelper = new SQLiteDataHelper(this.context,
                "UNIPAR TOLEDO",null,1);

        baseDados = openHelper.getWritableDatabase();
    }
    @Override
    public long insert(Disciplina obj) {
        try {
            ContentValues valores = new ContentValues();
            valores.put(colunas[0],obj.getIdDisciplina());
            valores.put(colunas[1],obj.getDescricao());
            valores.put(colunas[2],obj.getPeriodo());
            valores.put(colunas[3],obj.getCargaHoraria());
            valores.put(colunas[4],obj.getIdProfessor());
            baseDados.insert(tabela,null,valores);
        }catch (SQLException ex){
            Log.e("UNIPAR","ERRO:DisciplinaDao.insert()"+ex.getMessage());
        }
        return 0;
    }

    @Override
    public long update(Disciplina obj) {

        try {
            ContentValues valores = new ContentValues();
            valores.put(colunas[0],obj.getIdDisciplina());
            valores.put(colunas[1],obj.getDescricao());
            valores.put(colunas[2],obj.getPeriodo());
            valores.put(colunas[3],obj.getCargaHoraria());
            valores.put(colunas[4],obj.getIdProfessor());
            String[]identificador = {String.valueOf(obj.getIdDisciplina())};
            return baseDados.update(tabela,valores,colunas[0]+"= ?",identificador);
        }catch (SQLException ex){
            Log.e("UNIPAR","ERRO:DisciplinaDAo.update()"+ex.getMessage());
        }
        return 0;
    }

    @Override
    public long delete(Disciplina obj) {
        try {

            String[]identificador = {String.valueOf(obj.getIdDisciplina())};
            return baseDados.delete(tabela,colunas[0]+"= ?",identificador);
        }catch (SQLException ex){
            Log.e("UNIPAR","ERRO:DisciplinaDAO.delete()"+ex.getMessage());
        }
        return 0;
    }
    @Override
    public ArrayList<Disciplina> getAll() {
        ArrayList<Disciplina> lista = new ArrayList<>();
        try {
            Cursor cursor = baseDados.query(tabela,colunas,null,null,null,null,colunas[0]+"asc");
            if(cursor.moveToFirst()){
                do{
                    Disciplina disciplina = new Disciplina();
                    disciplina.setIdDisciplina(cursor.getInt(0));
                    disciplina.setDescricao(cursor.getString(1));
                    disciplina.setPeriodo(cursor.getInt(2));
                    disciplina.setCargaHoraria(cursor.getDouble(3));
                    disciplina.setIdProfessor(cursor.getInt(4));

                    lista.add(disciplina);
                }while (cursor.moveToNext());
                return lista;
            }
        }catch (SQLException ex){
            Log.e("UNIPAR","ERRO:DisciplinaDao.getAll()"+ex.getMessage());
        }
        return null;
    }

    @Override
    public Disciplina getById(int id) {
        try {
            String[]identificador = {String.valueOf(id)};
            Cursor cursor = baseDados.query(tabela,colunas,colunas[0]+="= ?",identificador,null,null,null);
            if(cursor.moveToFirst()){

                Disciplina disciplina = new Disciplina();
                disciplina.setIdDisciplina(cursor.getInt(0));
                disciplina.setDescricao(cursor.getString(1));
                disciplina.setPeriodo(cursor.getInt(2));
                disciplina.setCargaHoraria(cursor.getDouble(3));
                disciplina.setIdProfessor(cursor.getInt(4));
                return disciplina;

            }
        }catch (SQLException ex){
            Log.e("UNIPAR","ERRO:DisciplinaDao.getById()"+ex.getMessage());
        }
        return  null;
    }
}
