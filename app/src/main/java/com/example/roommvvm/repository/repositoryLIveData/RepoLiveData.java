package com.example.roommvvm.repository.repositoryLIveData;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.roommvvm.repository.room.Note;
import com.example.roommvvm.repository.room.NotesDataBase;
import com.example.roommvvm.repository.room.RoomDAO;

import java.util.List;

public class RepoLiveData {

    private RoomDAO roomDAO;
    private LiveData<List<Note>> listLiveData;

    public RepoLiveData(Application application) {
        NotesDataBase dataBase = NotesDataBase.getInstance(application);
        roomDAO = dataBase.roomDAO();
        listLiveData = roomDAO.getAllNotes();
    }

    public void insert(Note note) {
        new InsertNoteAsync(roomDAO).execute(note);
    }

    public void delete(Note note) {
        new DeleteNoteAsync(roomDAO).execute(note);
    }

    public void update(Note note) {
        new UpdateNoteAsync(roomDAO).execute(note);

    }

    public void deleteAll() {

        new DeleteAllNoteAsync(roomDAO).execute();
    }

    public LiveData<List<Note>> getAllNotes() {
        return listLiveData;
    }


    private static class InsertNoteAsync extends AsyncTask<Note, Void, Void> {

        private RoomDAO roomDAO;

        private InsertNoteAsync(RoomDAO roomDAO) {
            this.roomDAO = roomDAO;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            roomDAO.insert(notes[0]);
            return null;
        }
    }

    private static class DeleteAllNoteAsync extends AsyncTask<Note, Void, Void> {

        private RoomDAO roomDAO;

        private DeleteAllNoteAsync(RoomDAO roomDAO) {
            this.roomDAO = roomDAO;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            roomDAO.deleteAll();
            return null;
        }
    }

    private static class DeleteNoteAsync extends AsyncTask<Note, Void, Void> {

        private RoomDAO roomDAO;

        private DeleteNoteAsync(RoomDAO roomDAO) {
            this.roomDAO = roomDAO;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            roomDAO.delete(notes[0]);
            return null;
        }
    }

    private static class UpdateNoteAsync extends AsyncTask<Note, Void, Void> {

        private RoomDAO roomDAO;

        private UpdateNoteAsync(RoomDAO roomDAO) {
            this.roomDAO = roomDAO;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            roomDAO.update(notes[0]);
            return null;
        }
    }
}
