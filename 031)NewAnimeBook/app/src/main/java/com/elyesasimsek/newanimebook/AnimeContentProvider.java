package com.elyesasimsek.newanimebook;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.HashMap;

public class AnimeContentProvider extends ContentProvider {

    static final String PROVIDER_NAME = "com.elyesasimsek.newanimebook.AnimeContentProvider";
    static final String URL = "content://" + PROVIDER_NAME + "animes";
    static final Uri CONTENT_URI = Uri.parse(URL);

    static final String NAME = "name";
    static final String IMAGE = "image";

    static final int ANIMES = 1;
    static final UriMatcher uriMatcher;


    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(PROVIDER_NAME, "animes", ANIMES);
    }

    private static HashMap<String, String> ANIME_PROJECTION_MAP;

    //Database
    private SQLiteDatabase db;
    static final String DATABASE_NAME = "Animes";
    static final String TABLE_NAME = "animes";
    static final int DATABASE_VERSION = 1;
    static final String CREATE_DATABASE_TABLE = "CREATE TABLE " + TABLE_NAME + "(name TEXT NOT NULL, " + "image BLOB NOT NULL);";


    private static class DatabaseHelper extends SQLiteOpenHelper{

        public DatabaseHelper(@Nullable Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_DATABASE_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            onCreate(db);
        }
    }

    @Override
    public boolean onCreate() {
        Context context = getContext();
        DatabaseHelper helper = new DatabaseHelper(context);
        db = helper.getWritableDatabase();
        return (db != null);
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        SQLiteQueryBuilder sqLiteQueryBuilder = new SQLiteQueryBuilder();
        sqLiteQueryBuilder.setTables(TABLE_NAME);

        switch (uriMatcher.match(uri)){
            case ANIMES:
                sqLiteQueryBuilder.setProjectionMap(ANIME_PROJECTION_MAP);
                break;
            default:
        }

        if (sortOrder == null || sortOrder.matches("")) {
            sortOrder = NAME;
        }
        Cursor cursor = sqLiteQueryBuilder.query(db, projection, selection, selectionArgs, null, null, sortOrder);
        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return "";
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        long rowID = db.insert(TABLE_NAME,"",values);

        if (rowID > 0) {
            Uri newUri = ContentUris.withAppendedId(CONTENT_URI,rowID);
            getContext().getContentResolver().notifyChange(newUri,null);
            return newUri;
        }

        throw new SQLException("Error!");
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        int rowCount = 0;
        switch (uriMatcher.match(uri)){
            case ANIMES:
                rowCount =  db.delete(TABLE_NAME, selection, selectionArgs);
                break;
            default:
                throw  new IllegalArgumentException("Failed URI");
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return rowCount;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        int rowCount = 0;
        switch (uriMatcher.match(uri)){
            case ANIMES:
                rowCount = db.update(TABLE_NAME, values,selection, selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Failed URI");
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return rowCount;
    }
}
