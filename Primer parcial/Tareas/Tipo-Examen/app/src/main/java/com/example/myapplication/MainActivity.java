package com.example.myapplication;

import android.os.Bundle;
import android.app.Activity;
import android.view.*;
import android.widget.*;
import android.widget.AdapterView.*;
public class MainActivity extends Activity{
    Spinner s;
    EditText jet1 = findViewById(R.id.xet1);
    EditText jet2 = findViewById(R.id.xet2);
    EditText jet3 = findViewById(R.id.xet3);


    public void onCreate(Bundle b){
        super.onCreate(b);
        setContentView(R.layout.activity_main);
        s = (Spinner) findViewById(R.id.xsp);
        s.setOnItemSelectedListener(new OnItemSelectedListener(){
            public void onItemSelected(AdapterView<?> av, View v, int i, long l) {
                Toast.makeText(MainActivity.this, s.getSelectedItem().toString(),
                        Toast.LENGTH_LONG).show();
                int x = Integer.parseInt(s.toString());

            }
            public void onNothingSelected(AdapterView<?> arg0){ }
            });
    }
}