package com.example.myapplication;

import android.os.Bundle;
import android.app.Activity;
import android.widget.*;
public class SegundaActivity extends Activity{
    TextView jtv;
    Bundle bdl;
    public void onCreate(Bundle b){
        super.onCreate(b);
        setContentView(R.layout.activity_segunda);
        jtv = (TextView) findViewById(R.id.xtv2);
        bdl = getIntent().getExtras();
        jtv.append(bdl.getString("RESULTADO"));
    }
}
