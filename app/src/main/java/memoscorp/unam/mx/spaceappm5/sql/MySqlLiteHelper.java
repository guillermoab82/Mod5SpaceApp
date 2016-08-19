package memoscorp.unam.mx.spaceappm5.sql;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

/**
 * Created by GuillermoAB on 16/08/2016.
 */
public class MySqlLiteHelper extends SQLiteOpenHelper {
    private final static String DATABASE_NAME = "app_favorites_db";
    private static final int DATABASE_VERSION = 1;

    //Table to store favorites
    public static final String TABLE_FAV_NAME="favorites_table";
    public static final String COLUMN_FAV_ID= BaseColumns._ID;
    public static final String COLUMN_FAV_IMAGEURI= "cImage_URI";
    public static final String COLUMN_FAV_FULLNAME= "cFullName";
    public static final String COLUMN_FAV_EARTHDATE= "cEarth_Date";
    public static final String COLUMN_FAV_ROVERNAME= "cRover_Name";
    public static final String COLUMN_FAV_TOTALPHOTO="nTotal_Photo";

    public static final String CREATE_FAV_TABLE = "create table "+TABLE_FAV_NAME+
            "("+COLUMN_FAV_ID+" integer primary key autoincrement,"+
            COLUMN_FAV_IMAGEURI+ " text null,"+
            COLUMN_FAV_FULLNAME+" text null,"+
            COLUMN_FAV_EARTHDATE+ " text null,"+
            COLUMN_FAV_ROVERNAME+ " text null,"+
            COLUMN_FAV_TOTALPHOTO+ " integer null)";



    public MySqlLiteHelper(Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_FAV_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
