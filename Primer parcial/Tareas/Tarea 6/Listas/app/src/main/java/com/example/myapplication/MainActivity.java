package com.example.myapplication;

import java.util.ArrayList;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView.OnItemClickListener; import android.widget.*;
public class MainActivity extends Activity {
    private ListView lv;

    protected void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.listado);
        ArrayList<ListaEntrada> al = new ArrayList<ListaEntrada>();
        al.add(new ListaEntrada(R.drawable.buho,
                "Mercurio", "mer"));
        al.add(new ListaEntrada(R.drawable.colibri,
                "Venus", "ven"));
        al.add(new ListaEntrada(R.drawable.cuervo,
                "Tierra", "tierra"));
        al.add(new ListaEntrada(R.drawable.flamenco,
                "Marte", "mars"));
        al.add(new ListaEntrada(R.drawable.kiwi,
                "Jupiter", "jup"));
        al.add(new ListaEntrada(R.drawable.loro,
                "Saturno", "satur"));
        al.add(new ListaEntrada(R.drawable.pavo,
                "Urano", "uranius"));
        al.add(new ListaEntrada(R.drawable.pinguino,
                "Neptuno", "npth"));
        lv = (ListView) findViewById(R.id.xlv_listado);
        lv.setAdapter(new ListaAdapter(this, R.layout.activity_main, al) {
            public void onEntrada(Object o, View v) {
                if (o != null) {
                    TextView texto_superior_entrada = (TextView) v.findViewById(R.id.textView_superior);
                    if (texto_superior_entrada != null)
                        texto_superior_entrada.setText(((ListaEntrada) o).get_textoEncima());
                    TextView texto_inferior_entrada = (TextView) v.findViewById(R.id.textView_inferior);
                    if (texto_inferior_entrada != null)
                        texto_inferior_entrada.setText(((ListaEntrada) o).get_textoDebajo());
                    ImageView imagen_entrada = (ImageView) v.findViewById(R.id.imageView_imagen);
                    if (imagen_entrada != null)
                        imagen_entrada.setImageResource(((ListaEntrada) o).get_idImagen());
                }
            }
        });
        lv.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> av, View view, int i, long l) {
                ListaEntrada le = (ListaEntrada) av.getItemAtPosition(i);
                CharSequence cs = "Seleccionado: " + le.get_textoDebajo();
                Toast t = Toast.makeText(MainActivity.this, cs, Toast.LENGTH_SHORT);
                t.show();
            }
        });
    }
}