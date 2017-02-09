package com.definityfirst.jesusgonzalez.conciertosapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jesus.gonzalez on 09/02/2017.
 */

public class DBHandler extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "ConciertosApp";
    // Contacts table name
    private static final String TABLE_NAME = "artists";
    // Shops Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_IMG_URL = "image_url";
    private static final String KEY_THUMB_URL = "thumb_url";
    private static final String KEY_FB_TOURURL = "facebook_tour_dates_url";
    private static final String KEY_FB_PAGEURL = "facebok_page_url";
    private static final String KEY_MBID = "mbid";
    private static final String KEY_TRACKER = "tracker_count";
    private static final String KEY_UPCOMING_EVENTS = "upcoming_event_count";
    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"+ KEY_IMG_URL + " TEXT,"
                + KEY_THUMB_URL + " TEXT,"+ KEY_FB_TOURURL + " TEXT,"
                + KEY_FB_PAGEURL + " TEXT,"+ KEY_MBID + " TEXT,"+ KEY_TRACKER + " INTEGER,"
                + KEY_UPCOMING_EVENTS + " INTEGER" + ")";
        System.out.println(CREATE_CONTACTS_TABLE);
        db.execSQL(CREATE_CONTACTS_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
// Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
// Creating tables again
        onCreate(db);
    }

    // Adding new artist
    public void addArtist(Artist artist) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, artist.getName()); // Shop Name
        values.put(KEY_IMG_URL, artist.getImageUrl()); // Shop Phone Number
        values.put(KEY_THUMB_URL, artist.getThumbUrl());
        values.put(KEY_FB_TOURURL, "placeholder");
        values.put(KEY_FB_PAGEURL, "placeholder");
        values.put(KEY_MBID, artist.getMbid());
        values.put(KEY_TRACKER, artist.getTrackerCount());
        values.put(KEY_UPCOMING_EVENTS, artist.getUpcomingEventCount());

        db.insert(TABLE_NAME, null, values);
        db.close(); // Closing database connection
    }

    // Getting artist
    public Artist getArtist(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, new String[] { KEY_ID,
                        KEY_NAME, KEY_IMG_URL, KEY_THUMB_URL, KEY_FB_TOURURL, KEY_FB_PAGEURL, KEY_MBID, KEY_TRACKER, KEY_UPCOMING_EVENTS }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        Artist artist = new Artist();
        artist.setName(cursor.getString(1));
        artist.setImageUrl(cursor.getString(2));
        artist.setThumbUrl(cursor.getString(3));
        artist.setFacebookTourDatesUrl(cursor.getString(4));
        artist.setFacebookPageUrl(cursor.getString(5));
        artist.setMbid(cursor.getString(6));
        artist.setTrackerCount(Integer.parseInt(cursor.getString(7)));
        artist.setUpcomingEventCount(Integer.parseInt(cursor.getString(8)));
        return artist;
    }

    // Getting All Artists
    public List<Artist> getAllArtist() {
        List<Artist> artistList = new ArrayList<Artist>();

        String selectQuery = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Artist artist = new Artist();
                artist.setName(cursor.getString(1));
                artist.setImageUrl(cursor.getString(2));
                artist.setThumbUrl(cursor.getString(3));
                artist.setFacebookTourDatesUrl(cursor.getString(4));
                artist.setFacebookPageUrl(cursor.getString(5));
                artist.setMbid(cursor.getString(6));
                artist.setTrackerCount(Integer.parseInt(cursor.getString(7)));
                artist.setUpcomingEventCount(Integer.parseInt(cursor.getString(8)));

                artistList.add(artist);
            } while (cursor.moveToNext());
        }

        return artistList;
    }

    //Getting Artist Count
    public int getArtistCount() {
        String countQuery = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();
// return count
        return cursor.getCount();
    }


    // Deleting artist
    public void deleteArtist(Artist artist) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, KEY_NAME + " = ?",
                new String[] { String.valueOf(artist.getName()) });
        db.close();
    }

}