package com.example.debtsapp;

import android.content.Context;
import android.os.AsyncTask;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;


@Database(entities = Note.class,version = 1)

public abstract class NoteDataBase extends RoomDatabase {

    private static NoteDataBase instance;
    public abstract NoteDao noteDao();

    public static synchronized NoteDataBase getInstance(Context context) {
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    NoteDataBase.class,"note_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallBack)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallBack = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAysncTask(instance).execute();
        }
    };


    private static class PopulateDbAysncTask extends AsyncTask<Void, Void, Void> {
        NoteDao noteDao;

        public PopulateDbAysncTask(NoteDataBase db)  {
            noteDao = db.noteDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            noteDao.insert(new Note("Name 1" , 100 , "Description 1"));
            noteDao.insert(new Note("Name 2" , 200 , "Description 2"));
            noteDao.insert(new Note("Name 3" , 300 , "Description 3 "));
            return null;
        }
    }

}
