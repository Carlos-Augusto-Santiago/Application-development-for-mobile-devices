package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
public class MainActivity extends Activity { // ServiceTimerActivity
    private TextView jtv;
    private Button jbn, jbn2;
    @Override
    public void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activity_main);
        jtv = (TextView) findViewById(R.id.xtvT);
        jbn = (Button) findViewById(R.id.xbnI);
        jbn2 = (Button) findViewById(R.id.xbnT);

        jbn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                initCrono();
            }
        });
        jbn2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                refreshCrono(0);
            }
        });
        if(jbn.getText() == "Pausar"){
            jbn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    stopCrono();
                }
            });
        }
        MiCrono.setUpdateListener(this);
    }
    @Override
    protected void onDestroy() {
        stopCrono();
        super.onDestroy();
    }
    private void initCrono() {
        jbn.setText("Pausar");
        jbn2.setText("Continuar");
        Intent in = new Intent(this, MiCrono.class);
        startService(in);
    }
    private void stopCrono() {
        Intent in = new Intent(this, MiCrono.class);
        stopService(in);
    }
    public void refreshCrono(double t) {
        jtv.setText(String.format("%.2f", t) + " segs");
    }
}