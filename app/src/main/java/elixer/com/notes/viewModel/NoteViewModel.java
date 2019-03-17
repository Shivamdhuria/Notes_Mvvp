package elixer.com.notes.viewModel;

import android.app.Application;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import elixer.com.notes.model.Note;
import elixer.com.notes.model.NoteRepository;

public class NoteViewModel extends AndroidViewModel {

    private NoteRepository mRepository;
    private LiveData<List<Note>> mAllNotes;

    public NoteViewModel(@NonNull Application application) {
        super(application);
        mRepository = new NoteRepository(application);
        mAllNotes = mRepository.getAllNotes();
    }

    public void insert(Note note) {
        mRepository.insert(note);
    }

    public void delete(Note note) {
        mRepository.insert(note);
    }

    public LiveData<List<Note>> getAllNotes() {
        return mAllNotes;
    }
}
