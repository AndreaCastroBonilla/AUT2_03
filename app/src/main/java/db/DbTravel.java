package db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

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
}
