package com.example.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class SegundaActivity extends Activity {
    Bundle bdl;
    TextView jtv1, jtv2;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.segunda_activity);
        jtv1 = (TextView) findViewById(R.id.xtv1);
        jtv2 = (TextView) findViewById(R.id.xtv2);
        bdl = getIntent().getExtras();
        jtv1.append("Color" + bdl.getString("Color"));
        jtv2.append("Pixel" + bdl.getString("Color Pixel"));
        Lienzo l = new Lienzo(this);
        setContentView(l);
    }
}
