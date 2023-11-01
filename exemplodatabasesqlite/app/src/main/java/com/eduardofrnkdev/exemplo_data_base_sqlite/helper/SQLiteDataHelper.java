package com.eduardofrnkdev.exemplo_data_base_sqlite.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class SQLiteDataHelper extends SQLiteOpenHelper {

    public SQLiteDataHelper(@Nullable Context context,
                            @Nullable String name ,
                            @Nullable SQLiteDatabase.CursorFactory factory , int version) {
        super(context, name, factory, version);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase){
        sqLiteDatabase.execSQL("CREATE TABLE ALUNO(RA INTEGER,NOME VARCHAR(100))");
        sqLiteDatabase.execSQL("CREATE TABLE DISCIPLINA(ID_DISCIPLINA INTEGER , DESCRICAO VARCHAR(100),PERIODO INTEGER,CARGA_HORARIA NUMERIC ,ID_PROFESSOR INTEGER )");
        sqLiteDatabase.execSQL("CREATE TABLE PROFESSOR(ID_PROFESSOR INTEGER , MATRICULA INTEGER , NOME VARCHAR(100))");
        sqLiteDatabase.execSQL("CREATE TABLE TURMA(ID_TURMA INTEGER , CURSO VARCHAR(100),ANO_INICIO INTEGER , ANO_FIM INTEGER)");
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

}
