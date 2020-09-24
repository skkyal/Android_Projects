package com.example.notes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashSet;

public class MainActivity extends AppCompatActivity {
    static ArrayList<String> note;
    ListView noteList;
    static ArrayAdapter<String> arrayAdapter;
    static SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPreferences= getApplicationContext().getSharedPreferences("com.example.notes", Context.MODE_PRIVATE);

        HashSet<String> data=(HashSet<String>)sharedPreferences.getStringSet("notes",null);
        if(data== null)
            note=new ArrayList<String>();
        else
            note=new ArrayList<String>(data);
        noteList= findViewById(R.id.notesList);
        arrayAdapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,note);
        noteList.setAdapter(arrayAdapter);

        noteList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent= new Intent(getApplicationContext(),NotesEditorActivity.class);
                intent.putExtra("notesId",i);
                startActivity(intent);

               /* if(note.get(i).equals("")) {
                    note.remove(i);
                    arrayAdapter.notifyDataSetChanged();

                    HashSet<String> set= new HashSet<>(note);
                    sharedPreferences.edit().putStringSet("notes",set).apply();
                }*/

            }
        });

        noteList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                final int x=i;
                new AlertDialog.Builder(MainActivity.this)
                        .setIcon(android.R.drawable.ic_dialog_info)
                        .setTitle("Are You Sure?")
                        .setMessage("Do you want to delete the Note?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                note.remove(x);
                                arrayAdapter.notifyDataSetChanged();
                                HashSet<String> set= new HashSet<>(note);
                                sharedPreferences.edit().putStringSet("notes",set).apply();
                            }
                        })
                        .setNegativeButton("No",null)
                        .show();


                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater= getMenuInflater();
        inflater.inflate(R.menu.add_note,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);
        if(item.getItemId()==R.id.addNote){
            Intent intent= new Intent(getApplicationContext(),NotesEditorActivity.class);
            startActivity(intent);
            if(note.get(note.size()-1).equals("")) {
                    note.remove(note.size()-1);
                    arrayAdapter.notifyDataSetChanged();

                    HashSet<String> set= new HashSet<>(note);
                    sharedPreferences.edit().putStringSet("notes",set).apply();
                    return false;
             }
            return true;
        }
        return false;
    }
}
