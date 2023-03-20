package com.example.myapplication2;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.example.myapplication2.adapter.NoteAdapter;
import com.example.myapplication2.databinding.ActivityMainBinding;
import com.example.myapplication2.dialog.AddNoteDialog;
import com.example.myapplication2.dialog.EditNoteDialog;
import com.example.myapplication2.model.Note;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NoteAdapter.NoteViewHolder.OnNoteListener {

   private RecyclerView notesRecycleview;
    private AddNoteDialog dialog;
   private NoteAdapter noteAdapter;
   private List<Note> notesList = new ArrayList<>();
    private FloatingActionButton addButton;
    private EditNoteDialog editNoteDialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        SetListeners();

        notesRecycleview.setLayoutManager(new LinearLayoutManager(this));
        noteAdapter = new NoteAdapter(notesList,this);
        notesRecycleview.setAdapter(noteAdapter);


    }
    private void init(){
        editNoteDialog = new EditNoteDialog(this,noteAdapter);
        dialog = new AddNoteDialog(this);
        notesRecycleview = findViewById(R.id.notesRecyclerview);
        addButton =  findViewById(R.id.addButton);
        notesRecycleview.setAdapter(noteAdapter);
    }
    private void SetListeners(){
        addButton.setOnClickListener(view -> {
         dialog.show();

        });
         dialog.setOnSaveClickListener(note -> {
             addNote(note);
        });



    }

    public void addNote(Note note){
        notesList.add(note);
        noteAdapter.updateList(notesList);
    }

    public void updateNote (Note note,int position){
        notesList.set(position,note);
        noteAdapter.updateList(notesList);
    }

    @Override
    public void onNoteClick(int position) {
        editNoteDialog.show();
        editNoteDialog.setOnChangeClickListener(note -> {
            updateNote(note,position);
        });
    }
}