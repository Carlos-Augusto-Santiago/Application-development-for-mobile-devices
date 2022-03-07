package com.example.myapplication;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CambiarContactos extends Activity {
    EditText jetI, jetN;
    Button checar, cambiar;
    TextView jtvN;
    SQLiteDatabase sqld;

    public void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activity_cambiar);
        jetI = findViewById(R.id.id_change);
        jetN = findViewById(R.id.nombre_change);
        jtvN = findViewById(R.id.tvnombre);
        checar = findViewById(R.id.btn_check);
        cambiar = findViewById(R.id.btn_change);

        jtvN.setVisibility(View.GONE);
        jetN.setVisibility(View.GONE);
        cambiar.setVisibility(View.GONE);

        Context context = getApplicationContext();
        CharSequence text = "No se pueden guardar esos valores!";
        int duration = Toast.LENGTH_SHORT;

        DbmsSQLiteHelper dsqlh = new DbmsSQLiteHelper(this);
        sqld = dsqlh.getWritableDatabase();


        checar.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                int id = Integer.parseInt(jetI.getText().toString());
                Cursor c;
                c = sqld.rawQuery("SELECT id FROM Contactos WHERE id = " + id + "LIMIT 1", null);
                if(c != null){
                    jtvN.setVisibility(View.VISIBLE);
                    jetN.setVisibility(View.VISIBLE);
                    checar.setVisibility(View.GONE);
                }
            }
        });

        cambiar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                int id = Integer.parseInt(jetI.getText().toString());
                Cursor c = sqld.rawQuery("SELECT nombre FROM Contactos WHERE id = " + id, null);
                jtvN.setText(c.toString());
                String name = jtvN.getText().toString();
                if(!name.equals("")){
                    sqld.execSQL("UPDATE Contactos SET nombre = '" + name + "' WHERE id = '" + id + "'");
                }
                else{
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
            }
        });
    }
}
