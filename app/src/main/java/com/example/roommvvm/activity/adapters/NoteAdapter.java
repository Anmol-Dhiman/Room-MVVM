package com.example.roommvvm.activity.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.roommvvm.R;
import com.example.roommvvm.repository.room.Note;


import java.util.ArrayList;
import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder> {


    private List<Note> noteArrayList = new ArrayList<>();
    private onItemClickListener listener;

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
//        whenever we have a new input in the list then this function will tell that the previous list is invalid and it will drop the whole list and
//        make the view from scratch

//        or

//        when we make changes to 1 item then it will reload the whole list on the screen
//     to overcome this we have parameterized function which takes the int as input which is the index where the changes happen


//        we have DiffUtil class which compares 2 lists and give us the index where changes happen
        notifyDataSetChanged();


    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView title, description, priority;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.title);
            description = itemView.findViewById(R.id.description);
            priority = itemView.findViewById(R.id.priority);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null && getAdapterPosition() != RecyclerView.NO_POSITION)
                        listener.onItemClick(noteArrayList.get(getAdapterPosition()));
                }
            });
        }

    }


    public interface onItemClickListener {
        void onItemClick(Note note);
    }

    public void setOnItemClickListener(onItemClickListener listener) {
        this.listener = listener;
    }
}
