package com.example.easykhatayt;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.net.ContentHandler;

public class KhaataDB {

    private final String DATABASE_NAME = "KhaataDB";
    private final String DATABASE_TABLE = "KhaataTable";
    private final int DATABASE_VERSION = 1;

    public static final String ROW_ID = "_id";
    public static final String ROW_TITLE = "_title";
    public static final String ROW_DESC = "_desc";
    public static final String ROW_DATE = "_date";
    public static final String ROW_PRICE = "_price";

    public Context ourContext;
    private DBHelper ourHelper;
    private SQLiteDatabase ourDatabase;

    public KhaataDB(Context context)
    {
        ourContext = context;
    }

    public String getAllKhaatas() {
        String []columns = new String[]{ROW_ID, ROW_TITLE,
        ROW_DESC, ROW_DATE, ROW_PRICE};

        Cursor c = ourDatabase.query(DATABASE_TABLE, columns, null, null, null, null, null);
        int indexID = c.getColumnIndex(ROW_ID);
        int indexTitle = c.getColumnIndex(ROW_TITLE);
        int indexDesc = c.getColumnIndex(ROW_DESC);
        int indexDate = c.getColumnIndex(ROW_DATE);
        int indexPrice = c.getColumnIndex(ROW_PRICE);
        String result = "";
        for(c.moveToFirst();!c.isAfterLast();c.moveToNext())
        {
            result = result + c.getString(indexID) + "\n"
                    +c.getString(indexTitle) + "\n"
                    +c.getString(indexDesc) + "\n"
                    +c.getString(indexDate) + "\n"
                    +c.getString(indexPrice) + "\n\n\n";
        }
        c.close();

        return result;

    }

    private class DBHelper extends SQLiteOpenHelper
    {
        public DBHelper(Context context)
        {
            super(context, DATABASE_NAME, null, DATABASE_VERSION, null);
        }
        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {

            /*
            CREATE TABLE KhaataDB(
               _id INTEGER PRIMARY KEY AUTOINCREMENT,
               _title TEXT NOT NULL,
               _desc TEXT NOT NULL,
               _date TEXT NOT NULL,
               _price INTEGER NOT NULL);
             */

            String query = "CREATE TABLE "+DATABASE_TABLE+"("
                    +ROW_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    +ROW_TITLE + " TEXT NOT NULL,"
                    +ROW_DESC+ " TEXT NOT NULL,"
                    +ROW_DATE + " TEXT NOT NULL,"
                    +ROW_PRICE+ " INTEGER NOT NULL);";
            sqLiteDatabase.execSQL(query);
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
            // back-up code should be here
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+DATABASE_TABLE);
            onCreate(sqLiteDatabase);
        }
    }

    public void open() throws SQLException
    {
        ourHelper = new DBHelper(ourContext);
        ourDatabase = ourHelper.getWritableDatabase();
    }
    public void close()
    {
        ourHelper.close();
    }

    public long addNewKhata(String title,
                       String desc,
                       String date,
                       String price)
    {
        ContentValues cv = new ContentValues();
        cv.put(ROW_TITLE, title);
        cv.put(ROW_DESC, desc);
        cv.put(ROW_DATE, date);
        cv.put(ROW_PRICE, price);
        return ourDatabase.insert(DATABASE_TABLE, null, cv);
    }

    public int removeKhaata(String rowID)
    {
       return ourDatabase.delete(DATABASE_TABLE, ROW_ID+"=?", new String[]{rowID});
    }

    public int updatKhaata(String rowID,
                       String title,
                       String desc,
                       String date,
                       String price)
    {
        ContentValues cv = new ContentValues();
        cv.put(ROW_TITLE, title);
        cv.put(ROW_DESC, desc);
        cv.put(ROW_DATE, date);
        cv.put(ROW_PRICE, price);
        return ourDatabase.update(DATABASE_TABLE, cv, ROW_ID+"=?", new String[]{rowID});
    }

}
