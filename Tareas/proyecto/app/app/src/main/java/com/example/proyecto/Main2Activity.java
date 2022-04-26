package com.example.proyecto;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Main2Activity extends AppCompatActivity {


    BluetoothJhr blue;
    Button envia;
    Boolean offHilo = false;
    Boolean initConexion = false;
    TextView pantallaRx;
    String mensaje;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        pantallaRx = findViewById(R.id.pantallaRx)
    }
}
