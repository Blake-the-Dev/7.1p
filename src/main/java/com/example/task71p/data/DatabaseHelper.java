package com.example.task71p.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.task71p.model.Post;
import com.example.task71p.util.Util;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper(@Nullable Context context) {
        super(context, Util.DATABASE_NAME, null, Util.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_POST_TABLE = "CREATE TABLE " + Util.TABLE_NAME + "(" + Util.POST_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + Util.POST_TYPE + " TEXT, " + Util.POST_NAME + " TEXT, " + Util.POST_PHONE + " TEXT, " + Util.POST_DESCRIPTION + " TEXT, " +
                Util.POST_DATE + " TEXT, " + Util.POST_LOCATION + " TEXT)";

        sqLiteDatabase.execSQL(CREATE_POST_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        String DROP_POST_TABLE = "DROP TABLE IF EXISTS" + sqLiteDatabase;
        sqLiteDatabase.execSQL(DROP_POST_TABLE, new String[]{Util.TABLE_NAME});

        onCreate(sqLiteDatabase);
    }

    public long insertPost(Post post)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Util.POST_TYPE, post.getPost_type());
        contentValues.put(Util.POST_NAME, post.getPost_name());
        contentValues.put(Util.POST_PHONE, post.getPost_phone());
        contentValues.put(Util.POST_DESCRIPTION, post.getPost_description());
        contentValues.put(Util.POST_DATE, post.getPost_date());
        contentValues.put(Util.POST_LOCATION, post.getPost_location());

        long newRowID = db.insert(Util.TABLE_NAME, null, contentValues);
        db.close();
        return newRowID;
    }

    public boolean fetchPost(String post_type, String post_name, String post_phone,
                             String post_description, String post_date, String post_location)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(Util.TABLE_NAME, new String[]{Util.POST_ID}, Util.POST_TYPE + "=? and " +
                Util.POST_NAME + "=? and " + Util.POST_DESCRIPTION + "=? and " + Util.POST_DATE + "=? and " + Util.POST_LOCATION + "=?",
                new String[]{post_type, post_name, post_phone, post_description, post_date, post_location},
                null, null, null);

        int numberOfRows = cursor.getCount();
        db.close();

        if(numberOfRows > 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public Cursor fetchPostDetails()
    {
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.query(Util.TABLE_NAME, new String[] {Util.POST_ID, Util.POST_NAME, Util.POST_DATE, Util.POST_DESCRIPTION},
                null, null, null, null, null);

        return cursor;
    }

    public void deleteRow(int id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
//        db.execSQL("DELETE FROM " + Util.TABLE_NAME + " WHERE " + Util.POST_NAME + " = " + name);
            db.delete(Util.TABLE_NAME, Util.POST_ID + " = " + id, null);
    }




    public List<Post> showAllPosts (){
        List<Post> postList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String selectAll = " SELECT * FROM " + Util.TABLE_NAME;
        Cursor cursor = db.rawQuery(selectAll, null);

        if (cursor.moveToFirst()) {
            do {
                Post post = new Post();
                post.setPost_id(cursor.getInt(0));
                post.setPost_type(cursor.getString(1));
                post.setPost_name(cursor.getString(2));
                post.setPost_phone(cursor.getString(3));
                post.setPost_description(cursor.getString(4));
                post.setPost_date(cursor.getString(5));
                post.setPost_location(cursor.getString(6));

                postList.add(post);

            } while (cursor.moveToNext());

        }

        return postList;
    }
}
