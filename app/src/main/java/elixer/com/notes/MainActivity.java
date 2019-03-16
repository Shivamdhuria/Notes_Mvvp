package elixer.com.notes;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import elixer.com.notes.Models.Note;
import elixer.com.notes.Models.NoteViewModel;

import android.preference.PreferenceManager;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private NoteViewModel noteViewModel;
    public static final int ADD_NOTE_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab_add);
        fab.setOnClickListener(view -> {
            Intent addNoteIntent = new Intent(MainActivity.this, AddNoteActivity.class);
            startActivityForResult(addNoteIntent, ADD_NOTE_REQUEST_CODE);
        });

        RecyclerView recyclerView = findViewById(R.id.recycylerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        final NoteAdapter noteAdapter = new NoteAdapter();
        recyclerView.setAdapter(noteAdapter);


        noteViewModel = ViewModelProviders.of(this).get(NoteViewModel.class);
        noteViewModel.getAllNotes().observe(this, notes -> {
            //Recycler update
            noteAdapter.setNotes(notes);
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_NOTE_REQUEST_CODE && resultCode == RESULT_OK) {

            String title = getIntent().getStringExtra(AddNoteActivity.EXTRA_TITLE);
            String description = getIntent().getStringExtra(AddNoteActivity.EXTRA_DESCRIPTION);
            int priority = getIntent().getIntExtra(AddNoteActivity.EXTRA_PRIORITY, 0);

            Note newNote = new Note(title, description, priority);
            noteViewModel.insert(newNote);
            Toast.makeText(getApplicationContext(), "Done", Toast.LENGTH_SHORT).show();


        }
    }
}
