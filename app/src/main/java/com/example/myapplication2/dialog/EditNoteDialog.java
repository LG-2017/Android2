package com.example.myapplication2.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.myapplication2.R;
import com.example.myapplication2.adapter.NoteAdapter;
import com.example.myapplication2.model.Note;

import java.util.ArrayList;
import java.util.List;

public class EditNoteDialog extends Dialog {
    private onChangeClickListener listener = null;
    private onShowClickDialog onShowClickDialogListener = null;
    private Note note;
    private static List<Note> notesList = new ArrayList<>();
    private NoteAdapter noteAdapter;
    private NoteAdapter.NoteViewHolder noteViewHolder;

    public EditNoteDialog(Context context, NoteAdapter noteAdapter) {

        super(context);
        this.noteAdapter = noteAdapter;
    }
    public EditText updateTitleText;
    public EditText updateBodyText;
    private Button updateButton;
    private Button cancelButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_note_dialog);
        init();
        SetListeners();
    }
    private void init(){
        updateTitleText = findViewById(R.id.updateTitleEditText);
        updateBodyText = findViewById(R.id.updatebodyEdittext);
        updateButton = findViewById(R.id.updateButton);
        cancelButton = findViewById(R.id.cancelButton);
    }
    private void SetListeners(){

        updateButton.setOnClickListener(view -> {

            String title = updateTitleText.getText().toString();
            String body = updateBodyText.getText().toString();
            if (listener != null){
                listener.updateNote(new Note(title,body));
            }
            updateTitleText.setText("");
            updateBodyText.setText("");
            dismiss();
        });

        cancelButton.setOnClickListener(view -> {
            dismiss();
        });
    }
    public interface onChangeClickListener{
        void updateNote(Note note);
    }
    public void setOnChangeClickListener(onChangeClickListener listener){
        this.listener = listener;
    }
    public interface onShowClickDialog{
        void showClickDialog(int position);
    }

}