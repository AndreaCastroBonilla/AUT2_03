package db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import java.util.ArrayList;

import db.entidades.Travel;

public class DbTravel extends DbHelper{

    private Context context;

    public DbTravel(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public long insertar(String id, String nombre, String capital, String idioma){
        long res = 0;
        try{
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues contentValues = new ContentValues();
            contentValues.put("id",id);
            contentValues.put("nombre",nombre);
            contentValues.put("capital",capital);
            contentValues.put("idioma",idioma);

            res = db.insert(TABLE_TRAVEL,null, contentValues);
        }catch(Exception exception){
            exception.toString();
        }

        return res;
    }

    public ArrayList<Travel> mostrarDatos(){
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<Travel> listaTravel = new ArrayList<Travel>();
        Travel travel = null;
        Cursor cursorTravel = null;

        cursorTravel = db.rawQuery("SELECT * FROM " + TABLE_TRAVEL,null);

        if(cursorTravel.moveToFirst()){
            do{
                travel = new Travel();
                travel.setId(cursorTravel.getString(0));
                travel.setNombre(cursorTravel.getString(1));
                travel.setCapital(cursorTravel.getString(2));
                travel.setIdioma(cursorTravel.getString(3));
                listaTravel.add(travel);
            }while(cursorTravel.moveToNext());
        }
        cursorTravel.close();
        return listaTravel;
    }

    public Travel verDato(int id){
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Travel travel = null;
        Cursor cursorTravel = null;

        cursorTravel = db.rawQuery("SELECT * FROM " + TABLE_TRAVEL + " WHERE id = " + id + "LIMIT 1",null);

        if(cursorTravel.moveToFirst()){
            travel = new Travel();
            travel.setId(cursorTravel.getString(0));
            travel.setNombre(cursorTravel.getString(1));
            travel.setCapital(cursorTravel.getString(2));
            travel.setIdioma(cursorTravel.getString(3));
        }
        cursorTravel.close();
        return travel;
    }

    public boolean modificarDato(int i, String id, String nombre, String capital, String idioma){
        boolean correcto = false;

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try{
            db.execSQL("UPDATE " + TABLE_TRAVEL + "SET id = '"+id+"',nombre = '"+nombre+"',capital = '"+capital+"',idioma = '"+idioma+"' WHERE i = '"+i+"'");
            correcto = true;
        }catch(Exception exception){
            exception.toString();
            correcto = false;
        } finally {
            db.close();
        }

        return correcto;
    }
}
