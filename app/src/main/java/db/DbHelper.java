package db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1; //versión
    private static final String DATABASE_NAME = "travelPlanner.bd"; //nombre
    public static final String TABLE_TRAVEL = "t_travel"; //nombre de la tabla

    public DbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_TRAVEL + "(" +
                "id TEXT PRIMARY KEY," +
                "nombre TEXT NOT NULL," +
                "capital TEXT NOT NULL," +
                "idioma TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE " + TABLE_TRAVEL);
        onCreate(sqLiteDatabase);
    }
}
