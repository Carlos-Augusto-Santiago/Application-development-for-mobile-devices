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
    Button jbnA, jbnL, jbnC, jbtnA, jbnD;
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
        jbtnA = findViewById(R.id.xbtnA);
        jbnD = findViewById(R.id.xbnD);

        jbnC.setVisibility(View.GONE);
        jbtnA.setVisibility(View.GONE);
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
                if (id.equals("") || nombre.equals("")) {
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                } else {
                    ContentValues cv = new ContentValues();
                    cv.put("id", id);
                    cv.put("nombre", nombre);

                    sqld.insert("Contactos", null, cv);
                    jetI.setText("");
                    jetN.setText("");
                    Toast ts = Toast.makeText(context, "Contacto guardado", duration);
                    ts.show();
                }
                int profile_counts = dsqlh.getProfilesCount();
                if (profile_counts != 0) {
                    jbnC.setVisibility(View.VISIBLE);
                    jbnD.setVisibility(View.VISIBLE);
                }
            }
        });
        jbnL.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                int profile_counts = dsqlh.getProfilesCount();
                if (profile_counts != 0) {
                    jbnC.setVisibility(View.VISIBLE);
                    jbnD.setVisibility(View.VISIBLE);
                }
                String id, nombre;
                Cursor c = sqld.rawQuery("SELECT id,nombre FROM Contactos", null);
                jtvL.setText("");
                if (c.moveToFirst()) {
                    do {
                        id = c.getString(0);
                        nombre = c.getString(1);
                        jtvL.append(" " + id + "\t" + nombre + "\n");
                    } while (c.moveToNext());
                }
                c.close();
            }
        });
        jbnC.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                String i = jetI.getText().toString();

                if (!i.equals("")) {
                    int id = Integer.parseInt(jetI.getText().toString());
                    Cursor c;
                    c = sqld.rawQuery("SELECT nombre FROM Contactos WHERE id = " + id, null);
                    String ids;
                    if (c.moveToFirst()) {
                        jbtnA.setVisibility(View.VISIBLE);
                        ids = c.getString(0);
                        jetN.append(ids);
                    } else {
                        Toast toast = Toast.makeText(context, "Error", duration);
                        toast.show();
                    }
                } else {
                    Toast toast = Toast.makeText(context, "No se puede checar ese valor", duration);
                    toast.show();
                }
            }
        });

        jbtnA.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                String name = jetN.getText().toString();
                int id = Integer.parseInt(jetI.getText().toString());
                if (!name.equals("")) {
                    sqld.execSQL("UPDATE Contactos SET nombre = '" + name + "' WHERE id = '" + id + "'");
                    Toast toast = Toast.makeText(context, "Valor modificado", duration);
                    toast.show();
                    jetI.setText("");
                    jetN.setText("");
                } else {
                    Toast toast = Toast.makeText(context, "No se puede guardar ese valor", duration);
                    toast.show();
                }
            }
        });

        jbnD.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                int id = Integer.parseInt(jetI.getText().toString());
                sqld.execSQL("DELETE FROM Contactos WHERE id = " + id);
                jetI.setText("");
                Toast toast = Toast.makeText(context, "Usuario borrado " + id, duration);
                toast.show();
            }
        });
    }
}