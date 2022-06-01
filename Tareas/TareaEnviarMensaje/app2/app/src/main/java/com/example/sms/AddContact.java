package com.example.sms;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddContact extends AppCompatActivity {

    EditText nombre, numero;
    Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        nombre = findViewById(R.id.xtETName);
        numero = findViewById(R.id.xtETNumber);
        btnAdd = findViewById(R.id.xtBtnAddContact);

        if(nombre.getText().toString().equals("") || numero.getText().toString().equals("")){
            Toast.makeText(getApplicationContext(), "No puedes guardar valores vacíos",
                    Toast.LENGTH_LONG).show();
        }
        else {
            btnAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
        }
    }
}
