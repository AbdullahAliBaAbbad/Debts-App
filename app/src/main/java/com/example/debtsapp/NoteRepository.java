package com.example.debtsapp;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class NoteRepository {
    private NoteDao noteDao;
    private LiveData<List<Note>> allNotes;

    public NoteRepository(Application application) {
        NoteDataBase dataBase = NoteDataBase.getInstance(application);
        noteDao = dataBase.noteDao();
        allNotes = noteDao.getAllNotes();
    }

    public void insert(Note note) {
        new InsertNoteAysncTask(noteDao).execute(note);
    }
    public void update(Note note) {
        new UpdateNoteAysncTask(noteDao).execute(note);
    }
    public void delete(Note note) {
        new DeleteNoteAysncTask(noteDao).execute(note);
    }

    public void deleteAllNotes() {
        new DeleteAllNotesAysncTask(noteDao).execute();
    }

    public LiveData<List<Note>> getAllNotes() {
        return allNotes;
    }

    public static class InsertNoteAysncTask extends AsyncTask<Note, Void, Void> {
        NoteDao noteDao;
        public InsertNoteAysncTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.insert(notes[0]);
            return null;
        }
    }

    public static class UpdateNoteAysncTask extends AsyncTask<Note, Void, Void> {
        NoteDao noteDao;
        public UpdateNoteAysncTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.update(notes[0]);
            return null;
        }
    }
    public static class DeleteNoteAysncTask extends AsyncTask<Note, Void, Void> {
        NoteDao noteDao;
        public DeleteNoteAysncTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.delete(notes[0]);
            return null;
        }
    }
    public static class DeleteAllNotesAysncTask extends AsyncTask<Void, Void, Void> {
        NoteDao noteDao;
        public DeleteAllNotesAysncTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            noteDao.deleteAllNotes();
            return null;
        }
    }
}