package com.example.hp.mydiary;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.IOException;

public class editing extends AppCompatActivity {
    int i;
    Intent intent;
    EditText editText;
    public void work(View view){
        if(intent.getIntExtra("flag",0)==2){
            //Editing
            MainActivity.arrayList.set(i,editText.getText().toString());
            MainActivity.arrayAdapter.notifyDataSetChanged();
            SharedPreferences sharedPreferences=this.getSharedPreferences("com.example.hp.mydiary", Context.MODE_PRIVATE);
            try {
                sharedPreferences.edit().putString("list",ObjectSerializer.serialize(MainActivity.arrayList)).apply();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Toast.makeText(this, "Changes saved successfully", Toast.LENGTH_SHORT).show();
            finish();
        }else{
            //Writing
            MainActivity.arrayList.add(editText.getText().toString());
            MainActivity.arrayAdapter.notifyDataSetChanged();
            SharedPreferences sharedPreferences=this.getSharedPreferences("com.example.hp.mydiary", Context.MODE_PRIVATE);
            try {
                sharedPreferences.edit().putString("list",ObjectSerializer.serialize(MainActivity.arrayList)).apply();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Toast.makeText(this, "Added new Diary Successfully", Toast.LENGTH_SHORT).show();
            finish();
        }

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editing);
         intent=getIntent();
        editText=(EditText) findViewById(R.id.editText2);
        if(intent.getIntExtra("flag",0)==2){
            //Editing
            i=intent.getIntExtra("index",0);
            editText.setText(MainActivity.arrayList.get(i));
        }



    }
}
