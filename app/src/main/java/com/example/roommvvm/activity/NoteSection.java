package com.example.roommvvm.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Switch;
import android.widget.Toast;

import com.example.roommvvm.R;
import com.example.roommvvm.databinding.ActivityNoteSectionBinding;

public class NoteSection extends AppCompatActivity {
    private ActivityNoteSectionBinding binding;
    public static final String ENTERED_TITLE = "title";
    public static final String ENTERED_DESCRIPTION = "description";
    public static final String ENTERED_PRIORITY = "priority";
    public static final String NOTE_ID = "noteID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNoteSectionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_close_24);

        Intent intent = getIntent();
        if (intent.hasExtra(NOTE_ID)) {
            setTitle("Edit Note");
            binding.titleEditText.setText(intent.getStringExtra(ENTERED_TITLE));
            binding.descriptionEditText.setText(intent.getStringExtra(ENTERED_DESCRIPTION));
            binding.numberPicker.setValue(intent.getIntExtra(ENTERED_PRIORITY, 1));
        } else {
            setTitle("Add Note");
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.note_activity, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_note:
                saveNote();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private void saveNote() {
        String title = binding.titleEditText.getText().toString().trim();
        String desc = binding.descriptionEditText.getText().toString().trim();
        int priority = binding.numberPicker.getValue();

        if (title.isEmpty() || desc.isEmpty()) {
            Toast.makeText(this, "Please Enter the Values", Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent(NoteSection.this, MainActivity.class);

            intent.putExtra(ENTERED_TITLE, title);
            intent.putExtra(ENTERED_DESCRIPTION, desc);
            intent.putExtra(ENTERED_PRIORITY, priority);
            int id = getIntent().getIntExtra(NOTE_ID, 1);
            if (id != -1) {
                intent.putExtra(NOTE_ID, id);
            }
            setResult(RESULT_OK, intent);
            finish();
        }
    }
}