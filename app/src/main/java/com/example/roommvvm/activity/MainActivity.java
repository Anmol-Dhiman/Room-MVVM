package com.example.roommvvm.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.roommvvm.R;
import com.example.roommvvm.activity.adapters.NoteAdapter;
import com.example.roommvvm.databinding.ActivityMainBinding;
import com.example.roommvvm.repository.room.Note;
import com.example.roommvvm.viewModel.NoteViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private NoteViewModel noteViewModel;
    private ActivityMainBinding binding;
    public static final int INSERT_NOTE = 1;
    public static final int UPDATE_NOTE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        NoteAdapter adapter = new NoteAdapter();
        binding.recyclerView.setAdapter(adapter);
        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));


        noteViewModel = new ViewModelProvider(this).get(NoteViewModel.class);
        noteViewModel.getAllNotes().observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(List<Note> notes) {
                adapter.setNoteArrayList(notes);
            }
        });


        binding.floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent data = new Intent(MainActivity.this, NoteSection.class);
                startActivityForResult(data, INSERT_NOTE);
            }
        });


        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                noteViewModel.delete(adapter.getNote(viewHolder.getAdapterPosition()));
                Toast.makeText(MainActivity.this, "NOTE has been deleted", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(binding.recyclerView);


//        updating the note
        adapter.setOnItemClickListener(new NoteAdapter.onItemClickListener() {
            @Override
            public void onItemClick(Note note) {
                Intent intent = new Intent(MainActivity.this, NoteSection.class);
                intent.putExtra(NoteSection.ENTERED_TITLE, note.getTitle());
                intent.putExtra(NoteSection.ENTERED_DESCRIPTION, note.getDesc());
                intent.putExtra(NoteSection.ENTERED_PRIORITY, note.getPriority());
                intent.putExtra(NoteSection.NOTE_ID, note.getId());
                startActivityForResult(intent, UPDATE_NOTE);
            }
        });

    }


    //    the insert item code
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == INSERT_NOTE && resultCode == RESULT_OK) {
            String title = data.getStringExtra(NoteSection.ENTERED_TITLE);
            String description = data.getStringExtra(NoteSection.ENTERED_DESCRIPTION);
            int priority = data.getIntExtra(NoteSection.ENTERED_PRIORITY, 1);
            noteViewModel.insert(new Note(title, description, priority));
            Toast.makeText(this, "NOTE Saved Successfully", Toast.LENGTH_SHORT).show();


        } else if (requestCode == UPDATE_NOTE && resultCode == RESULT_OK) {
            int id = data.getIntExtra(NoteSection.NOTE_ID, 1);
            if (id == -1) {
                Toast.makeText(this, "NOTE could not update", Toast.LENGTH_SHORT).show();
                return;
            }

            String title = data.getStringExtra(NoteSection.ENTERED_TITLE);
            String description = data.getStringExtra(NoteSection.ENTERED_DESCRIPTION);
            int priority = data.getIntExtra(NoteSection.ENTERED_PRIORITY, 1);
            Note note = new Note(title, description, priority);
            note.setId(id);
            noteViewModel.update(note);
            Toast.makeText(this, "NOTE has been updated successfully", Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(this, "NOTE could not save", Toast.LENGTH_SHORT).show();
        }
    }


    //    the menu items code
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.recycler_activity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.deleteAll:
                noteViewModel.deleteAllNotes();
                Toast.makeText(this, "All NOTES DELETED SUCCESSFULLY", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}