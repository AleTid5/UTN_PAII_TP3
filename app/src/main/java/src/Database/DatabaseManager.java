package src.Database;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import src.Database.Tables.ParkingTable;
import src.Database.Tables.UserTable;
import src.Interfaces.Entity;
import src.Services.ContextManagerService;

public class DatabaseManager extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "G1_TP3.db";

    public DatabaseManager() {
        super(ContextManagerService.getContext(), DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + UserTable.Entry.TABLE_NAME + " ("
                + UserTable.Entry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + UserTable.Entry.NAME + " TEXT NOT NULL,"
                + UserTable.Entry.EMAIL + " TEXT NOT NULL,"
                + UserTable.Entry.PASSWORD + " TEXT NOT NULL,"
                + "UNIQUE (" + UserTable.Entry.EMAIL + "))");

        sqLiteDatabase.execSQL("CREATE TABLE " + ParkingTable.Entry.TABLE_NAME + " ("
                + ParkingTable.Entry.USER_ID + " INTEGER,"
                + ParkingTable.Entry.CAR_REGISTER + " TEXT NOT NULL,"
                + ParkingTable.Entry.TIME + " TEXT NOT NULL," +
                " PRIMARY KEY(user_id, car_register), " +
                " FOREIGN KEY (user_id) REFERENCES users(_id) " +
                ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}

    public long save(String tableName, Entity entity) {
        return this.getWritableDatabase().insert(
                tableName,
                null,
                entity.toContentValues());
    }

    public Cursor find(String query) {
        return this.getReadableDatabase().rawQuery(query, null);
    }

    public void remove(String table, String where, String[] values) {
        this.getWritableDatabase().delete(table, where, values);
    }
}
