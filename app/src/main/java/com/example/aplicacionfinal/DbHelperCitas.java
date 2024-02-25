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
        String qyr = "CREATE TABLE tbl_citas (DNI TEXT PRIMARY KEY, Nombre TEXT, Apellidos TEXT, Razon TEXT, Disponibilidad INTEGER, Fecha TEXT, Cita TEXT, Médico TEXT, Historial TEXT, HorasOcupadas TEXT)";
        db.execSQL(qyr);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS tbl_citas");
        onCreate(db);
    }

    public Boolean insertUserData(String DNI, String Nombre, String Apellidos, String Razon, String Disponibilidad, String Fecha, String Cita, String Médico, String Historial, String HorasOcupadas) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("DNI", DNI);
        cv.put("Nombre", Nombre);
        cv.put("Apellidos", Apellidos);
        cv.put("Razon", Razon);
        cv.put("Disponibilidad", Disponibilidad);
        cv.put("Fecha", Fecha);
        cv.put("Cita", Cita);
        cv.put("Médico", Médico);
        cv.put("Historial", Historial);
        cv.put("HorasOcupadas", HorasOcupadas);

        try {
            // Verificar si la hora seleccionada ya está ocupada para el día correspondiente
            Cursor cursor = db.rawQuery("SELECT * FROM tbl_citas WHERE Fecha=? AND HorasOcupadas=?", new String[]{Fecha, HorasOcupadas});
            if (cursor != null && cursor.getCount() > 0) {
                // La hora ya está ocupada, no permitir la inserción y mostrar un mensaje de error
                cursor.close();
                return false;
            } else {
                // La hora está disponible, proceder con la inserción normalmente
                long res = db.insertOrThrow("tbl_citas", null, cv);
                return res != -1;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean checkHoraOcupada(String fecha, String hora) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {"DNI"};
        String selection = "Fecha=? AND Disponibilidad=?";
        String[] selectionArgs = {fecha, hora};
        Cursor cursor = db.query("tbl_citas", columns, selection, selectionArgs, null, null, null);
        int count = cursor.getCount();
        cursor.close();
        return count > 0;
    }

    public boolean verificarCitaExistente(String dni, String fecha) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {"DNI"};
        String selection = "DNI=? AND Fecha=?";
        String[] selectionArgs = {dni, fecha};
        Cursor cursor = db.query("tbl_citas", columns, selection, selectionArgs, null, null, null);
        int count = cursor.getCount();
        cursor.close();
        return count > 0;
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
        valores.put("Cita", "Pendiente");
        return db.update("tbl_citas", valores, "DNI=?", new String[]{dni}) > 0;
    }
}
