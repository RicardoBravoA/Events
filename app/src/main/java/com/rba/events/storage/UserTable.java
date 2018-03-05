package com.rba.events.storage;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import com.rba.events.model.entity.UserEntity;

/**
 * Created by Ricardo Bravo on 4/03/18.
 */

public class UserTable extends DatabaseHandler {

    private DatabaseHandler databaseHelper;

    public UserTable(Context context) {
        super(context);
        databaseHelper = getInstance(context);
    }

    public void addUser(UserEntity userEntity) {
        deleteUser();
        String sql = "INSERT INTO " + ConstantDB.TABLE_USER + "(" + ConstantDB.KEY_UID + ", " +
                ConstantDB.KEY_EMAIL + ") VALUES (?,?)";
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        SQLiteStatement statement = db.compileStatement(sql);
        db.beginTransaction();
        statement.clearBindings();
        statement.bindString(1, userEntity.getUid());
        statement.bindString(2, userEntity.getEmail());
        statement.execute();
        db.setTransactionSuccessful();
        db.endTransaction();
    }

    public boolean validUser(String uid) {

        boolean isSession = false;

        String selectQuery = "SELECT " + ConstantDB.KEY_UID + ", " + ConstantDB.KEY_EMAIL + " FROM " +
                ConstantDB.TABLE_USER + " WHERE " + ConstantDB.KEY_UID + " = '" + uid + "'";

        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        try {
            if (cursor.moveToFirst()) {
                do {
                    isSession = true;
                } while (cursor.moveToNext());
            }
        } finally {
            cursor.close();
        }

        return isSession;
    }

    public void deleteUser() {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        String query = "DELETE FROM " + ConstantDB.TABLE_USER;
        db.execSQL(query);
    }


}