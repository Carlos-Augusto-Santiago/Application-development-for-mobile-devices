package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText jet1;
    Button jbn1;
    TextView jtv2;
    int n;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //EditText donde se ingresa el numero
        jet1 = (EditText) findViewById(R.id.xet1);
        //Button de aceptar
        jbn1 = (Button) findViewById(R.id.xbn1);
        jbn1.setOnClickListener(this);
        //TextView donde se muestra los resultados
        jtv2 = (TextView) findViewById(R.id.xtv2);
    }

    @Override
    public void onClick(View v) {

        n = Integer.parseInt(jet1.getText().toString());
        //Numero marvilloso
        jtv2.append(esMaravilloso(n));

        //El numero es primo?
        if (esPrimo(n)){
            jtv2.append("Es primo\n");
        }
        else{
            jtv2.append("No es primo\n");
        }

        //Es de la serie Fibonacci?
        if (esFibo(n)){
            jtv2.append("Es fibonacci\n");
        }
        else{
            jtv2.append("No es de la serie Fibonacci\n");
        }
        jtv2.append("\n");
    }
    public static String esMaravilloso(int num){
        String maravilloso;
        if (num<0){
            maravilloso = "No es maravilloso\n";
        }
        else{
            maravilloso = "Es maravilloso\n";
        }
        return maravilloso;
    }
    public static boolean esPrimo(int num){
        int cont=2;
        boolean primo = true;
        while((primo) && (cont!=num)){
            if (num % cont == 0){
                primo = false;
            }
            cont++;
        }
        return primo;
    }
    public static boolean esFibo(int num){
        int cont = 1, cont1, cont2 = 0;
        boolean fibo = false;
        if (num > 0){
            do {
                cont1 = cont;
                cont = cont1 + cont2;
                cont2 = cont1;
                if (cont == num){
                    fibo = true;
                }
            }while(cont <= num);
        }
        return fibo;
    }
}