package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    TextView jtv1;
    EditText jet1;
    Button jbtn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        jtv1 = findViewById(R.id.xtv1);
        jet1 = findViewById(R.id.xet1);
        jbtn1 = findViewById(R.id.xbtn1);

        jtv1.setVisibility(View.GONE);

        jbtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(jet1.length() < 3 || jet1.length() > 4){
                    Toast.makeText(getApplicationContext(),"La cantidad mínima de caracteres es 3 y la máxima 4", Toast.LENGTH_LONG).show();
                }
                else{
                    String menor = public
                    jtv1.append();
                    jtv1.append("\r\n");


                    jtv1.append(mayor);

                    jtv1.setVisibility(View.VISIBLE);
                }
            }
        });
    }
    public String menor(String numero){
        //ordenando numero de menor a mayor
        numero = jet1.getText().toString();
        char[] cadena = numero.toCharArray();
        Arrays.sort(cadena);
        String min = new String(cadena);
        return min;
    }
    public String mayor(String numero){
        String mayor = numero;
        StringBuilder may = new StringBuilder(mayor);
        mayor = may.reverse().toString();
        return mayor;
    }
    public void Numero(String num1, String num2){

    }
}