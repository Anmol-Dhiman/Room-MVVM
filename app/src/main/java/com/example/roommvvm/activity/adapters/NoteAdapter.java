package com.example.roommvvm.activity.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.roommvvm.R;
import com.example.roommvvm.repository.room.Note;


import java.util.ArrayList;
import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder> {


    private List<Note> noteArrayList = new ArrayList<>();

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
        holder.description.setText(note.getDesc());
        holder.priority.setText(String.valueOf(note.getPriority()));
    }

    public Note getNote(int position) {
        return noteArrayList.get(position);
    }

    @Override
    public int getItemCount() {
        return noteArrayList.size();
    }

    public void setNoteArrayList(List<Note> noteArrayList) {
        this.noteArrayList = noteArrayList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView title, description, priority;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.title);
            description = itemView.findViewById(R.id.description);
            priority = itemView.findViewById(R.id.priority);
        }
    }
}
