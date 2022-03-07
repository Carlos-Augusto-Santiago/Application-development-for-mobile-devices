package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.*;
import android.app.*;
import android.view.View;
import android.widget.*;
public class MainActivity extends Activity{
    SharedPreferences sp;
    EditText jetn, jetx, jety;
    Button jbtn;
    Intent itn;
    Bundle bdl;
    float x, y;
    public void onCreate(Bundle b){
        super.onCreate(b);
        setContentView(R.layout.activity_main);
        jetx = findViewById(R.id.xetx);
        jety = findViewById(R.id.xety);
        jbtn = findViewById(R.id.xbtn);
        sp = getSharedPreferences("preferencias", Context.MODE_PRIVATE);
        x = sp.getFloat("x", 0);
        y = sp.getFloat("y", 0);
        jetx.setText("" + x);
        jety.setText("" + y);
        jbtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                itn = new Intent(MainActivity.this, SegundaActivity.class);
                bdl = new Bundle();
                bdl.putString("Latitud", jetx.getText().toString());
                bdl.putString("Longitud", jety.getText().toString());
                itn.putExtras(bdl);
                startActivity(itn);
            }
        });
    }
    protected void onPause(){
        super.onPause();
        x = Float.parseFloat(jetx.getText().toString());
        y = Float.parseFloat(jety.getText().toString());
        SharedPreferences.Editor miEditor = sp.edit();
        miEditor.putFloat("x", x);
        miEditor.putFloat("y", y);
        miEditor.commit();
        Toast.makeText(this, "Preferencias guardadas", Toast.LENGTH_LONG).show();
    }

}
