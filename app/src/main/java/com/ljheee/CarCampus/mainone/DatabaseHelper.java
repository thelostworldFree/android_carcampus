package com.ljheee.CarCampus.mainone;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by YuChenghao on 2017/12/18.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "mydata.db"; //数据库名称
    private static final int version = 1; //数据库版本

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE mytable (_id INTEGER PRIMARY KEY AUTOINCREMENT, startLocation TEXT, endLocation TEXT, startDate TEXT, startTime TEXT, dues TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
