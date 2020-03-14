package com.android.train.task.json;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.StreamHandler;

public class TestDataBase extends SQLiteOpenHelper {

    String TABLE_NAME = "Film";

    public TestDataBase(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREAT_TABLE_QUERY = "CREATE TABLE " + TABLE_NAME + "(" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "title TEXT," +
                "year TEXT," +
                "dirctor TEXT," +
                "gener TEXT," +
                "country TEXT," +
                "language TEXT," +
                "actors TEXT" +
                ")";

        db.execSQL(CREAT_TABLE_QUERY);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insertFilm(String title,String year,String dirctor,String gener,String country,
                           String language,String actots){

        String INSERT_FILM_QUERY = "INSERT INTO " + TABLE_NAME +
                "(title,year,dirctor,gener,country,language,actors) VALUES("
                + "'" +title+"'" + ","
                + "'" +year+"'" + ","
                + "'" +dirctor+"'" + ","
                + "'" +gener+"'" + ","
                + "'" +country+"'" + ","
                + "'" +language+"'" + ","
                + "'" +actots+"'"
                +")";

        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(INSERT_FILM_QUERY);
        db.close();
    }

    public List<String> GetFilmName (){

        String Get_All_Film = "SELECT title from "+ TABLE_NAME ;
        List<String> names = new ArrayList<String>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(Get_All_Film,null);
        int cunter = 0;

        while (c.moveToNext()){
            names.add(cunter,c.getString(0));
            cunter = cunter + 1;
        }
        db.close();
        return names;
    }

}
