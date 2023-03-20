package com.example.myapplication2.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;

import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication2.MainActivity;
import com.example.myapplication2.R;
import com.example.myapplication2.databinding.ActivityMainBinding;
import com.example.myapplication2.dialog.AddNoteDialog;
import com.example.myapplication2.dialog.EditNoteDialog;
import com.example.myapplication2.model.Note;


import java.util.ArrayList;
import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {
   public static List<Note> notesList = new ArrayList<>();

    private NoteViewHolder.OnNoteListener onNoteListener;

    public NoteAdapter (List<Note> notes, NoteViewHolder.OnNoteListener onNoteListener) {
        this.notesList = notes;
        this.onNoteListener = onNoteListener;
    }

    public void updateList(List<Note> newList) {
       notesList = newList;
       notifyDataSetChanged();
   }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_note,parent,false);
        return new NoteViewHolder(view,onNoteListener).linkAdapter(this);
    }


    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, @SuppressLint("RecyclerView") int position) {
       Note note = notesList.get(position);
       holder.titleTextview.setText(note.title);
       holder.bodyTextview.setText(note.body);
       holder.itemView.findViewById(R.id.changeButton).setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               onNoteListener.onNoteClick(position);
           }
       });

   }

    @Override
    public int getItemCount() {
        return notesList.size();
    }

      public static class  NoteViewHolder extends RecyclerView.ViewHolder {
       TextView titleTextview;
        TextView bodyTextview;

        private NoteAdapter noteAdapter;
        private OnNoteListener onNoteListener;


        public NoteViewHolder(View noteView,OnNoteListener onNoteListener){
            super(noteView);

            titleTextview = noteView.findViewById(R.id.Titletextview);
            bodyTextview = noteView.findViewById(R.id.bodytextview);
            this.onNoteListener = onNoteListener;


            itemView.findViewById(R.id.deleteButton).setOnClickListener(view -> {
                notesList.remove(getAdapterPosition());
               noteAdapter.notifyItemRemoved(getAdapterPosition());
            });



        }

        public NoteViewHolder linkAdapter(NoteAdapter adapter){
            this.noteAdapter = adapter;
            return this;
        }


        public interface OnNoteListener{
            void onNoteClick(int position);
        }

        }


    }
