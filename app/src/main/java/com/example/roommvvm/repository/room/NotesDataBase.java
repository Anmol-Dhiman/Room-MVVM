package com.example.roommvvm.repository.room;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;


@Database(entities = {Note.class}, version = 1)
public abstract class NotesDataBase extends RoomDatabase {

    private static NotesDataBase instance;


    //    we have build the NotesDataBase instance with the help of builder so Room will auto generate the code for the abstract method
    public abstract RoomDAO roomDAO();


    public static synchronized NotesDataBase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext()
                            , NotesDataBase.class, "note_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }

        return instance;
    }




}





