package com.example.loisgussenhoven.puppyplay.Datalayer;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.location.Location;

import com.example.loisgussenhoven.puppyplay.Entity.Dog;
import com.example.loisgussenhoven.puppyplay.Entity.FriendSession;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nick van Endhoven, 2119719 on 30-Dec-17.
 */

public class DAODog {

    public static final String DICTIONARY_TABLE_NAME = "Dogs";

    public static final String COLUMN_UUID = "UUID";
    public static final String COLUMN_NAME = "NAME";
    public static final String COLUMN_OWNER = "OWNER";
    public static final String COLUMN_GENDER = "GENDER";
    public static final String COLUMN_COLOR = "COLOR";

    public static final String DICTIONARY_TABLE_CREATE =
            "CREATE TABLE " + DICTIONARY_TABLE_NAME + " (" +
                    COLUMN_UUID + " VARCHAR(40) NOT NULL PRIMARY KEY," +
                    COLUMN_NAME + " VARCHAR(250) NOT NULL," +
                    COLUMN_OWNER + " VARCHAR(255) NOT NULL, " +
                    COLUMN_GENDER + " VARCHAR(15) NOT NULL, " +
                    COLUMN_COLOR + " INT NOT NULL" +
                    ");";

    DBObject db;

    public DAODog(DBObject db){
        this.db = db;
    }

    public void create(SQLiteDatabase database){
        database.execSQL(DICTIONARY_TABLE_CREATE);
    }

    public void addDog(Dog d) {
        String sql = "INSERT INTO " + DICTIONARY_TABLE_NAME  + " VALUES ('" +
                d.getUuid() + "', '" +
                d.getName() + "', '" +
                d.getNameOwner()  + "', '" +
                d.getGender()  + "', '" +
                d.getColour()  +
                "')";

        SQLiteDatabase database = db.getWritableDatabase();
        database.execSQL(sql);
    }

    public void deleteDog(Dog d) {
        SQLiteDatabase database = db.getWritableDatabase();
        String sql = "DELETE FROM " + DICTIONARY_TABLE_NAME + " WHERE " + COLUMN_UUID +" = '" + d.getUuid() +  "';";
        database.execSQL(sql);
    }

    public void deleteAllDogs() {
        db.getWritableDatabase().execSQL("DELETE FROM " + DICTIONARY_TABLE_NAME);
    }

    public Dog getDogById(String uuid){
        SQLiteDatabase database = db.getReadableDatabase();
        Cursor cursor = database.query(DICTIONARY_TABLE_NAME, new String[]{COLUMN_UUID, COLUMN_NAME, COLUMN_OWNER, COLUMN_GENDER, COLUMN_COLOR}, COLUMN_UUID + " = '" + uuid + "'", null, null, null, null);
        if(cursor.moveToFirst()) {
            do {

                String duuid = cursor.getString(cursor.getColumnIndex(COLUMN_UUID));
                String name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));
                String owner = cursor.getString(cursor.getColumnIndex(COLUMN_OWNER));
                String gender = cursor.getString(cursor.getColumnIndex(COLUMN_GENDER));
                int color = cursor.getInt(cursor.getColumnIndex(COLUMN_COLOR));

                return new Dog(duuid, name, owner, gender, color);
            } while(cursor.moveToNext());
        }

        return null;
    }
}
