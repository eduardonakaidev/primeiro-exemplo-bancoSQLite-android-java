package com.eduardofrnkdev.exemplo_data_base_sqlite.dao;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.SimpleCursorAdapter;

import com.eduardofrnkdev.exemplo_data_base_sqlite.helper.SQLiteDataHelper;
import com.eduardofrnkdev.exemplo_data_base_sqlite.model.Aluno;

import java.util.ArrayList;

public class AlunoDAO implements IGenericDao<Aluno> {

    // Variavel responsavel por abrir conexão
    private SQLiteOpenHelper openHelper;

    // Database
    private SQLiteDatabase baseDados;

    //nome das colunas da tabela ;
    private String[]colunas = {"RA","NOME"};

    //nome da tabela
    private String tabela = "ALUNO";

    //Contexto(view)
    private Context context;

    private static AlunoDAO instancia ;

    public  static AlunoDAO getInstance(Context context){
        if(instancia == null){
            return instancia = new AlunoDAO(context);
        }else {
            return  instancia;
        }
    }
    private AlunoDAO(Context context){
        this.context = context;

        // Abrir a conxão com o dataBase
        openHelper = new SQLiteDataHelper(this.context,
                "UNIPAR TOLEDO",null,1);

        baseDados = openHelper.getWritableDatabase();
    }


    @Override
    public long insert(Aluno obj) {
        try {
            ContentValues valores = new ContentValues();
            valores.put(colunas[0],obj.getRa());
            valores.put(colunas[1],obj.getNome());
            baseDados.insert(tabela,null,valores);
        }catch (SQLException ex){
            Log.e("UNIPAR","ERRO:AlunoDao.insert()"+ex.getMessage());
        }
        return 0;
    }

    @Override
    public long update(Aluno obj) {

        try {
            ContentValues values = new ContentValues();
            values.put(colunas[1],obj.getNome());
            values.put(colunas[1],obj.getRa());
            String[]identificador = {String.valueOf(obj.getRa())};
           return baseDados.update(tabela,values,colunas[0]+"= ?",identificador);
        }catch (SQLException ex){
            Log.e("UNIPAR","ERRO:AlunoDao.update()"+ex.getMessage());
        }
        return 0;
    }

    @Override
    public long delete(Aluno obj) {
        try {

            String[]identificador = {String.valueOf(obj.getRa())};
            return baseDados.delete(tabela,colunas[0]+"= ?",identificador);
        }catch (SQLException ex){
            Log.e("UNIPAR","ERRO:AlunoDao.delete()"+ex.getMessage());
        }
        return 0;
    }

    @Override
    public ArrayList<Aluno> getAll() {
        ArrayList<Aluno> lista = new ArrayList<>();
        try {
            Cursor cursor = baseDados.query(tabela,colunas,null,null,null,null,colunas[0]+"asc");
            if(cursor.moveToFirst()){
                do{
                  Aluno aluno = new Aluno();
                  aluno.setRa(cursor.getInt(0));
                  aluno.setNome(cursor.getString(1));

                  lista.add(aluno);
                }while (cursor.moveToNext());
                return lista;
            }
        }catch (SQLException ex){
            Log.e("UNIPAR","ERRO:AlunoDao.getAll()"+ex.getMessage());
        }
        return null;
    }
    @Override
    public Aluno getById(int id) {
        try {
            String[]identificador = {String.valueOf(id)};
            Cursor cursor = baseDados.query(tabela,colunas,colunas[0]+="= ?",identificador,null,null,null);
            if(cursor.moveToFirst()){
                Aluno aluno = new Aluno();
                aluno.setRa(cursor.getInt(0));
                aluno.setNome(cursor.getString(1));
                return aluno;

            }
        }catch (SQLException ex){
            Log.e("UNIPAR","ERRO:AlunoDao.getById()"+ex.getMessage());
        }
     return  null;
    }
}
