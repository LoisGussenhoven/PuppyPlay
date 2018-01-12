package com.example.loisgussenhoven.puppyplay.Datalayer;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Nick van Endhoven, 2119719 on 30-Dec-17.
 */

public class DBObject extends SQLiteOpenHelper {

    private static DBObject instance;

    private static final String DATABASE_NAME = "PUPPYPLAY";
    private static final int DATABASE_VERSION = 3;

    DAODog dog;
    DAOFriendSession session;

    private DBObject(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        dog = new DAODog(this);
        session = new DAOFriendSession(this);
    }

    public static DBObject init(Context context) {
        if(instance == null) {
            instance = new DBObject(context);
        }
        return instance;
    }

    public static DBObject getInstanceOf() {
        if(instance == null) {
            Log.e("DB init", "Call init(Context context) first!" );
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        dog.create(sqLiteDatabase);
        session.create(sqLiteDatabase);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + dog.DICTIONARY_TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + session.DICTIONARY_TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public DAODog getDog() {
        return dog;
    }

    public DAOFriendSession getSession() {
        return session;
    }
}
