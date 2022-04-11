package com.example.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
    EditText jet1, jet2, jet3;
    Button jbtn1, jbtn2;
    SharedPreferences sp;
    String txtColor, txtPixel;
    Intent itn;
    Bundle bdl;
    int n;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        jet1 = (EditText) findViewById(R.id.xet1);
        jet2 = (EditText) findViewById(R.id.xet2);
        jet3 = (EditText) findViewById(R.id.xet3);
        jbtn1 = (Button) findViewById(R.id.xbtn1);
        jbtn2 = (Button) findViewById(R.id.xbtn2);

        sp = getSharedPreferences("preferencias", Context.MODE_PRIVATE);
        txtColor = sp.getString("Color", "");
        txtPixel = sp.getString("Color Pixel", "");
        n = sp.getInt("n",0);

        jbtn1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if(Integer.parseInt(jet3.getText().toString()) < 10000) {
                    Toast toast1 = Toast.makeText(getApplicationContext(), "Número menor a 10000", Toast.LENGTH_SHORT);
                    toast1.show();
                }
                else{
                    txtColor = jet1.getText().toString();
                    txtPixel = jet2.getText().toString();
                    n = Integer.parseInt(jet3.getText().toString());
                    SharedPreferences.Editor miEditor = sp.edit();
                    miEditor.putString("Color",txtColor);
                    miEditor.putString("Color Pixel",txtPixel);
                    miEditor.putInt("n", n);
                    miEditor.commit();
                    Toast.makeText(getApplicationContext(), "Preferencias guardadas", Toast.LENGTH_LONG).show();
                }
            }
        });
        jbtn2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                itn = new Intent(MainActivity.this, SegundaActivity.class);
                bdl = new Bundle();
                bdl.putString("Color", jet1.getText().toString());
                bdl.putString("Pixel", jet2.getText().toString());
                itn.putExtras(bdl);
                startActivity(itn);
            }
        });

    }
}