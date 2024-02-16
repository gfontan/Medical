// DbHelperCitas.java
package com.example.aplicacionfinal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelperCitas extends SQLiteOpenHelper {

    private static final String dbname = "Citas.db";

    public DbHelperCitas(Context context) {
        super(context, dbname, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String qyr = "CREATE TABLE tbl_citas (DNI TEXT PRIMARY KEY, Nombre TEXT, Apellidos TEXT, Razon TEXT, Disponibilidad INTEGER, Medico TEXT, Historial TEXT)";
        db.execSQL(qyr);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS tbl_citas");
        onCreate(db);
    }

    public Boolean insertUserData(String DNI, String Nombre, String Apellidos, String Razon, String Disponibilidad, String Medico, String Historial) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("DNI", DNI);
        cv.put("Nombre", Nombre);
        cv.put("Apellidos", Apellidos);
        cv.put("Razon", Razon);
        cv.put("Disponibilidad", Disponibilidad);
        cv.put("Medico", Medico);
        cv.put("Historial", Historial);

        try {
            long res = db.insertOrThrow("tbl_citas", null, cv);
            return res != -1;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Cursor getdata() {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("SELECT * FROM tbl_citas", null);
    }

    public Cursor getCitaByDNI(String dni) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("SELECT * FROM tbl_citas WHERE DNI=?", new String[]{dni});
    }

    public boolean eliminarCita(String dni) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("tbl_citas", "DNI=?", new String[]{dni}) > 0;
    }

    public boolean marcarComoPendiente(String dni) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put("Disponibilidad", "Pendiente");
        return db.update("tbl_citas", valores, "DNI=?", new String[]{dni}) > 0;
    }
}
