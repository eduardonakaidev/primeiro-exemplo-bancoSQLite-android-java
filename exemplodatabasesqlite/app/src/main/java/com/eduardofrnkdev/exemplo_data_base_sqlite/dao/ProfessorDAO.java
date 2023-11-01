package com.eduardofrnkdev.exemplo_data_base_sqlite.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.eduardofrnkdev.exemplo_data_base_sqlite.helper.SQLiteDataHelper;
import com.eduardofrnkdev.exemplo_data_base_sqlite.model.Disciplina;
import com.eduardofrnkdev.exemplo_data_base_sqlite.model.Professor;

import java.util.ArrayList;

public class ProfessorDAO implements IGenericDao<Professor> {
    // Variavel responsavel por abrir conexão
    private SQLiteOpenHelper openHelper;

    // Database
    private SQLiteDatabase baseDados;

    //nome das colunas da tabela ;
    private String[]colunas = {"ID_PROFESSOR","MATRICULA","NOME"};

    //nome da tabela
    private String tabela = "PROFESSOR";

    //Contexto(view)
    private Context context;

    private static ProfessorDAO instancia ;

    public  static ProfessorDAO getInstance(Context context){
        if(instancia == null){
            return instancia = new ProfessorDAO(context);
        }else {
            return  instancia;
        }
    }
    private ProfessorDAO(Context context){
        this.context = context;

        // Abrir a conxão com o dataBase
        openHelper = new SQLiteDataHelper(this.context,
                "UNIPAR TOLEDO",null,1);

        baseDados = openHelper.getWritableDatabase();
    }
    @Override
    public long insert(Professor obj) {
        try {
            ContentValues valores = new ContentValues();
            valores.put(colunas[0],obj.getIdProfessor());
            valores.put(colunas[1],obj.getMatricula());
            valores.put(colunas[2],obj.getNome());

            baseDados.insert(tabela,null,valores);
        }catch (SQLException ex){
            Log.e("UNIPAR","ERRO:ProfessorDao.insert()"+ex.getMessage());
        }
        return 0;
    }

    @Override
    public long update(Professor obj) {

        try {
            ContentValues valores = new ContentValues();
            valores.put(colunas[0],obj.getIdProfessor());
            valores.put(colunas[1],obj.getMatricula());
            valores.put(colunas[2],obj.getNome());

            String[]identificador = {String.valueOf(obj.getIdProfessor())};
            return baseDados.update(tabela,valores,colunas[0]+"= ?",identificador);
        }catch (SQLException ex){
            Log.e("UNIPAR","ERRO:ProfessorDAO.update()"+ex.getMessage());
        }
        return 0;
    }

    @Override
    public long delete(Professor obj) {
        try {

            String[]identificador = {String.valueOf(obj.getIdProfessor())};
            return baseDados.delete(tabela,colunas[0]+"= ?",identificador);
        }catch (SQLException ex){
            Log.e("UNIPAR","ERRO:ProfessorDAO.delete()"+ex.getMessage());
        }
        return 0;
    }
    @Override
    public ArrayList<Professor> getAll() {
        ArrayList<Professor> lista = new ArrayList<>();
        try {
            Cursor cursor = baseDados.query(tabela,colunas,null,null,null,null,colunas[0]+"asc");
            if(cursor.moveToFirst()){
                do{
                    Professor professor = new Professor();
                    professor.setIdProfessor(cursor.getInt(0));
                    professor.setMatricula(cursor.getInt(1));
                    professor.setNome(cursor.getString(2));


                    lista.add(professor);
                }while (cursor.moveToNext());
                return lista;
            }
        }catch (SQLException ex){
            Log.e("UNIPAR","ERRO:ProfessorDao.getAll()"+ex.getMessage());
        }
        return null;
    }

    @Override
    public Professor getById(int id) {
        try {
            String[]identificador = {String.valueOf(id)};
            Cursor cursor = baseDados.query(tabela,colunas,colunas[0]+="= ?",identificador,null,null,null);
            if(cursor.moveToFirst()){

                Professor professor = new Professor();
                professor.setIdProfessor(cursor.getInt(0));
                professor.setMatricula(cursor.getInt(1));
                professor.setNome(cursor.getString(2));

                return professor;

            }
        }catch (SQLException ex){
            Log.e("UNIPAR","ERRO:DisciplinaDao.getById()"+ex.getMessage());
        }
        return  null;
    }
}
