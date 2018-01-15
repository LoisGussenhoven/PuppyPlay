package com.example.loisgussenhoven.puppyplay.datalayer;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.loisgussenhoven.puppyplay.entity.Dog;

/**
 * Created by Nick van Endhoven, 2119719 on 30-Dec-17.
 */

public class DAODog {

    public static final String DICTIONARY_TABLE_NAME = "Dogs";

    public static final String COLUMN_UUID = "UUID";
    public static final String COLUMN_NAME = "NAME";
    public static final String COLUMN_OWNER = "OWNER";
    public static final String COLUMN_GENDER = "GENDER";
    public static final String COLUMN_COLOR1 = "COLOR1";
    public static final String COLUMN_COLOR2 = "COLOR2";

    public static final String DICTIONARY_TABLE_CREATE =
            "CREATE TABLE " + DICTIONARY_TABLE_NAME + " (" +
                    COLUMN_UUID + " VARCHAR(40) NOT NULL PRIMARY KEY," +
                    COLUMN_NAME + " VARCHAR(250) NOT NULL," +
                    COLUMN_OWNER + " VARCHAR(255) NOT NULL, " +
                    COLUMN_GENDER + " VARCHAR(15) NOT NULL, " +
                    COLUMN_COLOR1 + " VARCHAR(6) NOT NULL," +
                    COLUMN_COLOR2 + " VARCHAR(6) NOT NULL" +
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
                d.getColor1()  + "', '" +
                d.getColor2()  +
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
        Cursor cursor = database.query(DICTIONARY_TABLE_NAME, new String[]{COLUMN_UUID, COLUMN_NAME, COLUMN_OWNER, COLUMN_GENDER, COLUMN_COLOR1, COLUMN_COLOR2}, COLUMN_UUID + " = '" + uuid + "'", null, null, null, null);
        if(cursor.moveToFirst()) {
            do {

                String duuid = cursor.getString(cursor.getColumnIndex(COLUMN_UUID));
                String name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));
                String owner = cursor.getString(cursor.getColumnIndex(COLUMN_OWNER));
                String gender = cursor.getString(cursor.getColumnIndex(COLUMN_GENDER));
                String color1 = cursor.getString(cursor.getColumnIndex(COLUMN_COLOR1));
                String color2 = cursor.getString(cursor.getColumnIndex(COLUMN_COLOR2));

                return new Dog(duuid, name, owner, gender, color1, color2);
            } while(cursor.moveToNext());
        }

        return null;
    }
}
