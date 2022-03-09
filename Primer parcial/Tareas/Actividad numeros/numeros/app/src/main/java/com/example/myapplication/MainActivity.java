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
                    String men = menor(jet1.getText().toString());
                    String may = mayor(men);

                    jtv1.append("  " + may);
                    jtv1.append("\r\n");
                    jtv1.append("- " + men);
                    jtv1.append("\r\n");
                    jtv1.append("----------");
                    jtv1.append("\r\n");
                    int resultado = resta(Integer.parseInt(may),Integer.parseInt(men));
                    jtv1.append("  " + resultado);
                    jtv1.append("\r\n");
                    int menor = Integer.parseInt(men);
                    while(menor != resultado){
                        jtv1.append("Aqui bien");
                        jtv1.append("\r\n");
                        men = menor(Integer.toString(resultado));
                        may = mayor(Integer.toString(resultado));
                        jtv1.append("  " + may);
                        jtv1.append("\r\n");
                        jtv1.append("- " + men);
                        jtv1.append("\r\n");
                        jtv1.append("----------");
                        jtv1.append("\r\n");
                        resultado = resta(Integer.parseInt(may),Integer.parseInt(men));
                        jtv1.append("  " + resultado);
                        jtv1.append("\r\n");
                    }
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
        // ordenando numero de mayor a menor
        String mayor = numero;
        StringBuilder may = new StringBuilder(mayor);
        mayor = may.reverse().toString();
        return mayor;
    }
    public int resta(int num1, int num2){
        // restandole al mayor el menor
        int res = num1 - num2;
        return res;
    }
}