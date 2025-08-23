package com.example.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import org.w3c.dom.Text;

import java.util.HashSet;

public class NotesEditorActivity extends AppCompatActivity {
    int notesId;
    EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_editor);
        editText=findViewById(R.id.editText);
        Intent intent=getIntent();
        notesId=intent.getIntExtra("notesId",-1);

        if(notesId!=-1){
            editText.setText(MainActivity.note.get(notesId));
        }
        else{
            MainActivity.note.add("");
            notesId=MainActivity.note.size()-1;
        }
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {


                    MainActivity.note.set(notesId,String.valueOf(charSequence));
                    MainActivity.arrayAdapter.notifyDataSetChanged();

                    HashSet<String> set= new HashSet<>(MainActivity.note);
                    MainActivity.sharedPreferences.edit().putStringSet("notes",set).apply();
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(editable.toString().equals("")){
                    MainActivity.note.remove(notesId);
                    MainActivity.arrayAdapter.notifyDataSetChanged();

                    HashSet<String> set= new HashSet<>(MainActivity.note);
                    MainActivity.sharedPreferences.edit().putStringSet("notes",set).apply();
                }
            }
        });

    }
}
