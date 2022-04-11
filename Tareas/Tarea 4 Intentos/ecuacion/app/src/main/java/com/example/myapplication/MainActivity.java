package com.example.myapplication;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.view.View.*;
import android.content.Intent;
import android.widget.*;

public class MainActivity extends Activity{
    EditText jet1;
    EditText jet2;
    EditText jet3;
    Button jbn;
    Bundle bdl;
    Intent itn;
    //      Variables a,b,c
    double ja,jb,jc;
    //      Varibles x1 y x2 "raices"
    double x1, x2;
    //      String en donde guardar el resultado
    String resultado;
    public void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activity_main);
        jet1 = (EditText) findViewById(R.id.xet1);
        jet2 = (EditText) findViewById(R.id.xet2);
        jet3 = (EditText) findViewById(R.id.xet3);
        jbn = (Button) findViewById(R.id.xbn);
        jbn.setOnClickListener(new OnClickListener() {
            public void onClick(View arg0) {
//              Guardamos el valor de cada EditText en su variable correspondiente
                ja = Double.parseDouble(jet1.getText().toString());
                jb = Double.parseDouble(jet2.getText().toString());
                jc = Double.parseDouble(jet3.getText().toString());
//              Obtenemos las raices
                x1 = (-jb + Math.sqrt(jb*jb - (4*ja*jc)))/(2*ja);
                x2 = (-jb - Math.sqrt(jb*jb - (4*ja*jc)))/(2*ja);
//              Colocamos los resultados en un solo string
                resultado = "x1 = " + String.format("%.4f",x1) + ", x2 = " + String.format("%.4f",x2);
                itn = new Intent(MainActivity.this, SegundaActivity.class);
                bdl = new Bundle();
                bdl.putString("RESULTADO", resultado);
                itn.putExtras(bdl);
                startActivity(itn);
            }
        });
    }
}