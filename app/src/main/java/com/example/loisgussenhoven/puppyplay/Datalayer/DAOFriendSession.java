package com.example.loisgussenhoven.puppyplay.datalayer;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.location.Location;

import com.example.loisgussenhoven.puppyplay.entity.FriendSession;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nick van Endhoven, 2119719 on 30-Dec-17.
 */

public class DAOFriendSession {

    public static final String DICTIONARY_TABLE_NAME = "Sessions";

    public static final String COLUMN_UUID = "UUID";
    public static final String COLUMN_DATE = "DATE";
    public static final String COLUMN_TIME = "TIME";
    public static final String COLUMN_DURATION = "DURATION";
    public static final String COLUMN_LONG = "LONGTITUDE";
    public static final String COLUMN_LAT = "LATITUDE";
    public static final String COLUMN_DOG = "DOG";

    public static final String DICTIONARY_TABLE_CREATE =
            "CREATE TABLE " + DICTIONARY_TABLE_NAME + " (" +
                    COLUMN_UUID + " VARCHAR(40) NOT NULL PRIMARY KEY," +
                    COLUMN_DATE + " VARCHAR(15) NOT NULL, " +
                    COLUMN_TIME + " VARCHAR(15) NOT NULL, " +
                    COLUMN_DURATION + " VARCHAR(15) NOT NULL," +
                    COLUMN_LONG + "  DECIMAL(3, 10) NOT NULL," +
                    COLUMN_LAT + "  DECIMAL(3, 10) NOT NULL," +
                    COLUMN_DOG + " VARCHAR(40) NOT NULL, " +
                    " FOREIGN KEY ("+ COLUMN_DOG + ") REFERENCES "+ DAODog.DICTIONARY_TABLE_NAME + "(" + DAODog.COLUMN_UUID + ")" +
                    ");";

    DBObject db;

    public DAOFriendSession(DBObject db){
        this.db = db;
    }

    public void create(SQLiteDatabase database){
        database.execSQL(DICTIONARY_TABLE_CREATE);
    }

    public void addSession(FriendSession f) {
        SimpleDateFormat df = new SimpleDateFormat("dd-mm-yyyy");
        String sql = "INSERT INTO " + DICTIONARY_TABLE_NAME  + " VALUES ('" +
                f.getUuid() + "', '" +
                df.format(f.getDate()) + "', '" +
                f.getTime()  + "', '" +
                f.getDuration()  + "', '" +
                f.getLocation().getLongitude()  + "', '" +
                f.getLocation().getLatitude()  + "', '" +
                f.getOther().getUuid() +
                "')";

        SQLiteDatabase database = db.getWritableDatabase();
        database.execSQL(sql);
    }

    public void deleteSession(FriendSession f) {
        SQLiteDatabase database = db.getWritableDatabase();
        String sql = "DELETE FROM " + DICTIONARY_TABLE_NAME + " WHERE " + COLUMN_UUID +" = '" + f.getUuid() +  "';";
        database.execSQL(sql);
    }

    public void deleteAllSessions() {
        db.getWritableDatabase().execSQL("DELETE FROM " + DICTIONARY_TABLE_NAME);
    }

    public List<FriendSession> getAllSessions() {
        List<FriendSession> sessions = new ArrayList<>();
        SQLiteDatabase database = db.getReadableDatabase();
        Cursor cursor = database.query(DICTIONARY_TABLE_NAME, new String[]{COLUMN_UUID, COLUMN_DATE, COLUMN_TIME, COLUMN_DURATION, COLUMN_LONG, COLUMN_LAT, COLUMN_DOG}, null, null, null, null, COLUMN_DATE + " DESC");
        if(cursor.moveToFirst()) {
            do {
                String uuid = cursor.getString(cursor.getColumnIndex(COLUMN_UUID));
                String date = cursor.getString(cursor.getColumnIndex(COLUMN_DATE));
                String time = cursor.getString(cursor.getColumnIndex(COLUMN_TIME));
                String duration = cursor.getString(cursor.getColumnIndex(COLUMN_DURATION));
                float longitude = cursor.getFloat(cursor.getColumnIndex(COLUMN_LONG));
                float latitude = cursor.getFloat(cursor.getColumnIndex(COLUMN_LAT));
                String dogUuid = cursor.getString(cursor.getColumnIndex(COLUMN_DOG));

                SimpleDateFormat df = new SimpleDateFormat("dd-mm-yyyy");

                try {
                    Location loc = new Location("");
                    loc.setLongitude(longitude);
                    loc.setLatitude(latitude);
                    sessions.add(new FriendSession(uuid, df.parse(date), time, duration, db.getDog().getDogById(dogUuid), loc));
                } catch (ParseException e) {
                    e.printStackTrace();
                }

            } while(cursor.moveToNext());
        }
        return sessions;
    }

    public FriendSession getSessionById(String uuid) {
//        SQLiteDatabase database = db.getReadableDatabase();
//        Cursor cursor = database.query(DICTIONARY_TABLE_NAME, new String[]{COLUMN_NAME_EN, COLUMN_DESCRIPTION_EN, COLUMN_NAME_NL, COLUMN_DESCRIPTION_NL}, COLUMN_NAME_EN + " = '" + route + "'", null, null, null, COLUMN_NAME_EN + " DESC");
//        FriendSession toReturn = null;
//        if(cursor.moveToFirst()) {
//            do {
////                String nameEN = cursor.getString(cursor.getColumnIndex(COLUMN_NAME_EN));
////                String descriptionEN = cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION_EN));
////                String nameNL = cursor.getString(cursor.getColumnIndex(COLUMN_NAME_NL));
////                String descriptionNL = cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION_NL));
////                if(Objects.equals(nameEN, "BlindWall Route")) {
////                    toReturn = new BlindRoute(nameEN, descriptionEN, nameNL, descriptionNL);
////                } else if(Objects.equals(nameEN, "Historic mile")) {
////                    toReturn = new HistoricRoute(nameEN, descriptionEN, nameNL, descriptionNL);
////                }
//            } while(cursor.moveToNext());
//        }
//        toReturn.setAllPoints(DB.getInstanceOf().getPoi().getAllPoisFromRoute(route));
        return null;
    }
}
