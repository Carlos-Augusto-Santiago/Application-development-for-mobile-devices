package com.example.proyecto1;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

public class Funcionamiento extends AppCompatActivity {

    Button iniciar, presentacion;
    Intent itn1, itn2;
    public void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.funcionamiento);
        iniciar = (Button) findViewById(R.id.iniciar);
        presentacion = (Button) findViewById(R.id.presentacion);
        iniciar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                itn1 = new Intent(Funcionamiento.this, VistaSensores.class);
                startActivity(itn1);
            }
        });
        presentacion.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                itn2 = new Intent(Funcionamiento.this, Presentacion.class);
                startActivity(itn2);
            }
        });
    }
}