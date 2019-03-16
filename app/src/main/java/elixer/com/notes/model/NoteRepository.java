package elixer.com.notes.model;

import android.app.Application;
import android.os.AsyncTask;

import java.util.List;

import androidx.lifecycle.LiveData;

public class NoteRepository {

    private NoteDao mNoteDao;
    private LiveData<List<Note>> mAllNotes;

    public NoteRepository(Application application) {
        NoteDatabase database = NoteDatabase.getInstance(application);
        mNoteDao = database.noteDao();
        mAllNotes = mNoteDao.getAllNotes();
    }

    public void insert(Note note) {
        new InsertNoteAsync(mNoteDao).execute(note);

    }

    public void delete(Note note) {
        new DeleteNoteAsync(mNoteDao).execute(note);
    }

    public LiveData<List<Note>> getAllNotes() {
        return mAllNotes;
    }

    public static class InsertNoteAsync extends AsyncTask<Note, Void, Void> {
        private NoteDao mNoteDao;

        private InsertNoteAsync(NoteDao mNoteDao) {
            this.mNoteDao = mNoteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            mNoteDao.insert(notes[0]);
            return null;
        }
    }

    public static class DeleteNoteAsync extends AsyncTask<Note, Void, Void> {
        private NoteDao mNoteDao;

        private DeleteNoteAsync(NoteDao noteDao) {
            this.mNoteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            mNoteDao.delete(notes[0]);
            return null;
        }
    }
}
