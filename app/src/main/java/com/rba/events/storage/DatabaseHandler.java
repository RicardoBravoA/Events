package com.rba.events.storage;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Ricardo Bravo on 4/03/18.
 */

class DatabaseHandler extends SQLiteOpenHelper {


    private static DatabaseHandler mInstance = null;

    DatabaseHandler(Context context){
        super(context, ConstantDB.DATABASE_NAME, null, ConstantDB.DATABASE_VERSION);
    }


    static DatabaseHandler getInstance(Context context){
        if (mInstance == null) {
            mInstance = new DatabaseHandler(context.getApplicationContext());
        }
        return mInstance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_USER_TABLE= "CREATE TABLE " + ConstantDB.TABLE_USER + "("
                + ConstantDB.KEY_UID + " TEXT, "+ ConstantDB.KEY_EMAIL+" TEXT)";
        db.execSQL(CREATE_USER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

}