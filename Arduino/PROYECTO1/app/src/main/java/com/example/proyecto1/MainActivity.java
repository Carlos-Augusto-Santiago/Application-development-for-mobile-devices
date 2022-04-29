package com.example.proyecto1;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.view.View.*;
import android.content.Intent;
import android.widget.*;

public class MainActivity extends Activity {
    Button iniciar, presentacion, funcionamiento;
    Intent itn1, itn2, itn3;
    public void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activity_main);
        iniciar = (Button) findViewById(R.id.iniciar);
        presentacion = (Button) findViewById(R.id.presentacion);
        funcionamiento = (Button) findViewById(R.id.funcionamiento);
        iniciar.setOnClickListener(new OnClickListener() {
            public void onClick(View arg0) {
                itn1 = new Intent(MainActivity.this, VistaSensores.class);
                startActivity(itn1);
            }
        });
        presentacion.setOnClickListener(new OnClickListener() {
            public void onClick(View arg0) {
                itn2 = new Intent(MainActivity.this, Presentacion.class);
                startActivity(itn2);
            }
        });
        funcionamiento.setOnClickListener(new OnClickListener() {
            public void onClick(View arg0) {
                itn3 = new Intent(MainActivity.this, Funcionamiento.class);
                startActivity(itn3);
            }
        });
    }
}