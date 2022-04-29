package com.example.proyecto1;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.view.View.*;
import android.content.Intent;
import android.widget.*;
public class Presentacion extends Activity{
    Button iniciar, funcionamiento;
    Intent itn1, itn2;
    public void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.presentacion);
        iniciar = (Button) findViewById(R.id.iniciar);
        funcionamiento = (Button) findViewById(R.id.funcionamiento);
        iniciar.setOnClickListener(new OnClickListener() {
            public void onClick(View arg0) {
                itn1 = new Intent(Presentacion.this, VistaSensores.class);
                startActivity(itn1);
            }
        });
        funcionamiento.setOnClickListener(new OnClickListener() {
            public void onClick(View arg0) {
                itn2 = new Intent(Presentacion.this, Funcionamiento.class);
                startActivity(itn2);
            }
        });
    }
}