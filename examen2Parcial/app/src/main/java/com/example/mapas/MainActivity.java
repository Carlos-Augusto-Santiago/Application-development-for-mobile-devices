package com.example.mapas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText pais, longitud, latitud;
    Button btn_guardarDatos, btn_verMapa;
    SQLiteDatabase sqld;
    Intent itn;
    Bundle bdl;
    TextView datosAlmacenados;
    String paisGuardado, longitudGuardado, latitudGuardado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pais = findViewById(R.id.pais);
        longitud = findViewById(R.id.lon);
        latitud = findViewById(R.id.la);
        btn_guardarDatos = findViewById(R.id.btn);
        btn_verMapa = findViewById(R.id.btnMap);
        datosAlmacenados = findViewById(R.id.Valores);
        DbmsSQLiteHelper dsqlh = new DbmsSQLiteHelper(this, "DBPaises", null, 1);
        sqld = dsqlh.getWritableDatabase();

        btn_guardarDatos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre = pais.getText().toString();
                String lat = latitud.getText().toString();
                String lon = longitud.getText().toString();
                datosAlmacenados.setText("");

                ContentValues cv = new ContentValues();
                cv.put("nombre", nombre);
                cv.put("latitud", lat);
                cv.put("longitud", lon);
                sqld.insert("Paises", null, cv);
                pais.setText("");
                longitud.setText("");
                latitud.setText("");

                Toast toast = Toast.makeText(getApplicationContext(), "Datos Guardados", Toast.LENGTH_SHORT);
                toast.show();
                Cursor c = sqld.rawQuery("SELECT * FROM Paises", null);
                if (c.moveToFirst()) {
                    do {
                        paisGuardado = c.getString(0);
                        latitudGuardado = c.getString(1);
                        longitudGuardado = c.getString(2);
                        datosAlmacenados.append("Pais:" + paisGuardado + "\tLatitud: " + latitudGuardado + "\tLongitud: " + longitudGuardado + "\n");
                    } while (c.moveToNext());
                }
            }
        });

        btn_verMapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itn = new Intent(MainActivity.this, Mapa.class);
                bdl = new Bundle();
                bdl.putString("Pais", paisGuardado);
                bdl.putString("Latitud", latitudGuardado);
                bdl.putString("Longitud", longitudGuardado);
                itn.putExtras(bdl);
                startActivity(itn);
            }
        });


    }
}