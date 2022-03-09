package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;

import java.util.Objects;

public class MainActivity extends Activity {
    EditText jetI, jetN;
    Button jbnA, jbnL, jbnC, jbnD;
    TextView jtvL;
    SQLiteDatabase sqld;

    Intent itn;

    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activity_main);
        jetI = findViewById(R.id.xetI);
        jetN = findViewById(R.id.xetN);
        jbnA = findViewById(R.id.xbnA);
        jbnL = findViewById(R.id.xbnL);
        jtvL = findViewById(R.id.xtvL);

        jbnC = findViewById(R.id.xbnC);
        jbnD = findViewById(R.id.xbnD);

        jbnC.setVisibility(View.GONE);
        jbnD.setVisibility(View.GONE);

        Context context = getApplicationContext();
        CharSequence text = "No se pueden guardar esos valores!";
        int duration = Toast.LENGTH_SHORT;


        DbmsSQLiteHelper dsqlh = new DbmsSQLiteHelper(this);
        sqld = dsqlh.getWritableDatabase();

        int profile_counts = dsqlh.getProfilesCount();
        if (profile_counts != 0) {
            jbnC.setVisibility(View.VISIBLE);
            jbnD.setVisibility(View.VISIBLE);
        }

        jbnA.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                String id = jetI.getText().toString();
                String nombre = jetN.getText().toString();
                if(id.equals("") || nombre.equals("")){
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
                else{
                    ContentValues cv = new ContentValues();
                    cv.put("id", id);
                    cv.put("nombre", nombre);


                    DbmsSQLiteHelper dsqlh = new DbmsSQLiteHelper(v.getContext());
                    sqld = dsqlh.getWritableDatabase();

                    sqld.insert("Contactos", null, cv);
                    jetI.setText("");
                    jetN.setText("");

                    sqld.close();

                    Toast ts = Toast.makeText(context, "Contacto guardado", duration);
                    ts.show();
                }
            }
        });
        jbnL.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                String id, nombre;

                DbmsSQLiteHelper dsqlh = new DbmsSQLiteHelper(v.getContext());
                sqld = dsqlh.getWritableDatabase();

                Cursor c = sqld.rawQuery("SELECT id,nombre FROM Contactos", null);
                jtvL.setText("");
                if (c.moveToFirst()) {
                    do {
                        id = c.getString(0);
                        nombre = c.getString(1);
                        jtvL.append(" " + id + "\t" + nombre + "\n");
                    } while (c.moveToNext());
                }

                sqld.close();
            }
        });
        jbnC.setOnClickListener(new OnClickListener() {
            public void onClick(View v){
                itn = new Intent(MainActivity.this, CambiarContactos.class);
                startActivity(itn);
            }
        });
        jbnD.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                itn = new Intent(MainActivity.this, EliminarContactos.class);
                startActivity(itn);
            }
        });
    }
}