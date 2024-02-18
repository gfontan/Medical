package com.example.aplicacionfinal;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbManager extends SQLiteOpenHelper {

        private static final String dbname="Medicamentos.db";
    public DbManager(Context context) {

        super(context, dbname, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String qyr = "create table tbl_medicamentos(Cod_Nacional varchar(10) primary key, nombre varchar(50), dosis integer, sintomas varchar(100), prospecto TEXT)";
        db.execSQL(qyr);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS tbl_medicamentos");
    }

    public Boolean insertUserData(String cod_nacional, String nombre, String dosis, String sintomas, String prospecto) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("Cod_Nacional", cod_nacional);
        cv.put("nombre", nombre);  // Cambiado a minúsculas
        cv.put("dosis", dosis);    // Cambiado a minúsculas
        cv.put("sintomas", sintomas);  // Cambiado a minúsculas
        cv.put("prospecto", prospecto);

        long res = db.insert("tbl_medicamentos", null, cv);

        if (res == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean insertarProspecto(String codNacional, String prospecto) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Cod_Nacional", codNacional);
        contentValues.put("prospecto", prospecto);
        long resultado = db.insert("tbl_medicamentos", null, contentValues);
        return resultado != -1;
    }

    @SuppressLint("Range")
    public String obtenerProspecto(String codNacional) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT prospecto FROM tbl_medicamentos WHERE Cod_Nacional = ?";
        Cursor cursor = db.rawQuery(query, new String[]{codNacional});
        String prospecto = null;
        if (cursor.moveToFirst()) {
            prospecto = cursor.getString(cursor.getColumnIndex("prospecto"));
        }
        cursor.close();
        return prospecto;
    }


    public Cursor getdata(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from tbl_medicamentos", null);
        return cursor;
    }
}
