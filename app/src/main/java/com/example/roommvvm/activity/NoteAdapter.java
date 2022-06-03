package com.example.roommvvm.activity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.roommvvm.R;
import com.example.roommvvm.repository.room.Note;

import java.util.ArrayList;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder> {


    private ArrayList<Note> noteArrayList;

    @NonNull
    @Override
    public NoteAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull NoteAdapter.ViewHolder holder, int position) {
        Note note = noteArrayList.get(position);
        holder.title.setText(note.getTitle());
        holder.priority.setText(note.getPriority());
        holder.description.setText(note.getDesc());
    }

    @Override
    public int getItemCount() {
        return noteArrayList.size();
    }

    public void setNoteArrayList(ArrayList<Note> noteArrayList) {
        this.noteArrayList = noteArrayList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView title, description, priority;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.title);
            description = itemView.findViewById(R.id.description);
            priority = itemView.findViewById(R.id.priority);
        }
    }
}
