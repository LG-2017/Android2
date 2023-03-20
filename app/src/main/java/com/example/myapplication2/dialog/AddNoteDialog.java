package com.example.myapplication2.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;

import com.example.myapplication2.MainActivity;
import com.example.myapplication2.R;
import com.example.myapplication2.model.Note;
import com.google.android.material.button.MaterialButton;

public class AddNoteDialog extends Dialog {
    private OnSaveClickListener listener = null;


    public AddNoteDialog(Context context) {
        super(context);

    }
    private EditText titleEdittext;
    private EditText bodyEdittext;
    private Button cancelButton;
    private Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_add_note);
        init();
        saveButton.setOnClickListener(view -> {
            String title = titleEdittext.getText().toString();
            String body = bodyEdittext.getText().toString();
            if (listener != null){
                listener.onClick(new Note(title,body));
            }
            titleEdittext.setText("");
            bodyEdittext.setText("");
            dismiss();
        });
        cancelButton.setOnClickListener(view -> {
            dismiss();
        });


    }

    private void init(){
        titleEdittext = findViewById(R.id.titleEdittext);
        bodyEdittext = findViewById(R.id.bodyEdittext);
        cancelButton = findViewById(R.id.cancelButton);
        saveButton = findViewById(R.id.saveButton);
    }

    public interface OnSaveClickListener {
        void onClick(Note note);
    }
    public void setOnSaveClickListener(OnSaveClickListener listener){
        this.listener = listener;
    }

}
