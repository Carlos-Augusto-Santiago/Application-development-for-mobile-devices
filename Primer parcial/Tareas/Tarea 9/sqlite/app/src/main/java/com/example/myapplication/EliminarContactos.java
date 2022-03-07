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

public class EliminarContactos extends Activity {
    EditText jetI;
    Button eliminar;
    SQLiteDatabase sqld;

    public void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activity_eliminar);
        jetI = findViewById(R.id.et_eliminar);
        eliminar = findViewById(R.id.btn_eliminar);

        Context context = getApplicationContext();
        CharSequence text = "No se pueden guardar esos valores!";
        int duration = Toast.LENGTH_SHORT;

        DbmsSQLiteHelper dsqlh = new DbmsSQLiteHelper(this);
        sqld = dsqlh.getWritableDatabase();

        eliminar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                int id = Integer.parseInt(jetI.getText().toString());
                sqld.rawQuery("DELETE FROM Contactos WHERE id = " + id + "LIMIT 1", null);
            }
        });
    }
}
