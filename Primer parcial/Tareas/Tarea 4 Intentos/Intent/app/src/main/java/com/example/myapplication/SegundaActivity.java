package com.example.myapplication;

import android.os.Bundle;
import android.app.Activity;
import android.widget.*;
public class SegundaActivity extends Activity{
    EditText jet;
    Bundle bdl;
    public void onCreate(Bundle b){
        super.onCreate(b);
        setContentView(R.layout.activity_segunda);
        jet = (EditText) findViewById(R.id.xet1);
        jet.setEnabled(false);
        bdl = getIntent().getExtras();
        jet.append("Hola " + bdl.getString("NOMBRE"));
    }
}

