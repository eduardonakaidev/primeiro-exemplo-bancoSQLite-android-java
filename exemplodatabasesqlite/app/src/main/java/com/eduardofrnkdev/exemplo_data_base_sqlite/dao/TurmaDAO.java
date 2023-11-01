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
import com.eduardofrnkdev.exemplo_data_base_sqlite.model.Turma;

import java.util.ArrayList;

public class TurmaDAO implements IGenericDao<Turma> {
    // Variavel responsavel por abrir conexão
    private SQLiteOpenHelper openHelper;

    // Database
    private SQLiteDatabase baseDados;

    //nome das colunas da tabela ;
    private String[]colunas = {"ID_TURMA","CURSO","ANO_INICIO","ANO_FIM"};

    //nome da tabela
    private String tabela = "TURMA";

    //Contexto(view)
    private Context context;

    private static TurmaDAO instancia ;

    public  static TurmaDAO getInstance(Context context){
        if(instancia == null){
            return instancia = new TurmaDAO(context);
        }else {
            return  instancia;
        }
    }
    private TurmaDAO(Context context){
        this.context = context;

        // Abrir a conxão com o dataBase
        openHelper = new SQLiteDataHelper(this.context,
                "UNIPAR TOLEDO",null,1);

        baseDados = openHelper.getWritableDatabase();
    }


    @Override
    public long insert(Turma obj) {
        try {
            ContentValues valores = new ContentValues();
            valores.put(colunas[0],obj.getIdTurma());
            valores.put(colunas[1],obj.getCurso());
            valores.put(colunas[2],obj.getAnoInicio());
            valores.put(colunas[3],obj.getAnoFim());
            baseDados.insert(tabela,null,valores);
        }catch (SQLException ex){
            Log.e("UNIPAR","ERRO:TurmaDao.insert()"+ex.getMessage());
        }
        return 0;
    }

    @Override
    public long update(Turma obj) {

        try {
            ContentValues values = new ContentValues();
            values.put(colunas[0],obj.getIdTurma());
            values.put(colunas[1],obj.getCurso());
            values.put(colunas[2],obj.getAnoInicio());
            values.put(colunas[3],obj.getAnoFim());
            String[]identificador = {String.valueOf(obj.getIdTurma())};
            return baseDados.update(tabela,values,colunas[0]+"= ?",identificador);
        }catch (SQLException ex){
            Log.e("UNIPAR","ERRO:TurmaDAO.update()"+ex.getMessage());
        }
        return 0;
    }

    @Override
    public long delete(Turma obj) {
        try {

            String[]identificador = {String.valueOf(obj.getIdTurma())};
            return baseDados.delete(tabela,colunas[0]+"= ?",identificador);
        }catch (SQLException ex){
            Log.e("UNIPAR","ERRO:TurmaDAO.delete()"+ex.getMessage());
        }
        return 0;
    }

    @Override
    public ArrayList<Turma> getAll() {
        ArrayList<Turma> lista = new ArrayList<>();
        try {
            Cursor cursor = baseDados.query(tabela,colunas,null,null,null,null,colunas[0]+"asc");
            if(cursor.moveToFirst()){
                do{
                   Turma turma = new Turma();
                    turma.setIdTurma(cursor.getInt(0));
                    turma.setCurso(cursor.getString(1));
                    turma.setAnoInicio(cursor.getInt(2));
                    turma.setAnoFim(cursor.getInt(3));

                    lista.add(turma);
                }while (cursor.moveToNext());
                return lista;
            }
        }catch (SQLException ex){
            Log.e("UNIPAR","ERRO:TurmaDao.getAll()"+ex.getMessage());
        }
        return null;
    }
    @Override
    public Turma getById(int id) {
        try {
            String[]identificador = {String.valueOf(id)};
            Cursor cursor = baseDados.query(tabela,colunas,colunas[0]+="= ?",identificador,null,null,null);
            if(cursor.moveToFirst()){
                Turma turma = new Turma();
                turma.setIdTurma(cursor.getInt(0));
                turma.setCurso(cursor.getString(1));
                turma.setAnoInicio(cursor.getInt(2));
                turma.setAnoFim(cursor.getInt(3));

                return turma;

            }
        }catch (SQLException ex){
            Log.e("UNIPAR","ERRO:TurmaDao.getById()"+ex.getMessage());
        }
        return  null;
    }

}
