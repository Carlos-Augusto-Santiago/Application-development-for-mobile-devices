package com.example.myapplication;

import android.os.Bundle;
import android.app.Activity;
import android.widget.*;
public class SegundaActivity extends Activity{
    TextView jtv2, jtv3;
    Bundle bdl;
    public void onCreate(Bundle b){
        super.onCreate(b);
        setContentView(R.layout.activity_segunda);
        jtv2 = findViewById(R.id.xtv2);
        jtv3 = findViewById(R.id.xtv3);
        bdl = getIntent().getExtras();
        jtv2.append("La latitud ingresada fue " + bdl.getString("Latitud"));
        jtv3.append("La longitud ingresada fue " + bdl.getString("Longitud"));
    }
}

