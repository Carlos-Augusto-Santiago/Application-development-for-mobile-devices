package com.example.myapplication;

import android.content.*;
import android.graphics.*;
import android.view.View;
public class Lienzo extends View{
    Paint p;
    int x, y;
    public Lienzo(Context c){
        super(c);
    }
    protected void onDraw(Canvas c){
        super.onDraw(c); // Canvas pinta atributos
        p = new Paint(); // Paint asigna atributos
        x = c.getWidth();
        y = c.getHeight();

        p.setColor(Color.WHITE); // Fondo blanco
        c.drawPaint(p);
        p.setColor(Color.BLACK); // Texto negro
        p.setTextSize(20);
        int pos = 200;

        // Rectangulo
        p.setColor(Color.argb(211, 40, 124, 230));
        c.drawRect(x/2-pos-150, y/2+pos+150, x/2+pos+150, y/2-pos-150, p);
        // circulo
        p.setColor(getResources().getColor(R.color.white));
        c.drawCircle(x/2, y/2, 350, p);

        // Rectangulo
        p.setColor(Color.argb(211, 40, 124, 230));
        c.drawRect(x/2-pos-50, y/2+pos+50, x/2+pos+50, y/2-pos-50, p);
        // circulo
        p.setColor(getResources().getColor(R.color.white));
        c.drawCircle(x/2, y/2, 250, p);

        // Rectangulo
        p.setColor(Color.argb(211, 40, 124, 230));
        c.drawRect(x/2-pos+20, y/2+pos-20, x/2+pos-20, y/2-pos+20, p);
        // circulo
        p.setColor(getResources().getColor(R.color.white));
        c.drawCircle(x/2, y/2, 180, p);

        // Rectangulo
        p.setColor(Color.argb(211, 40, 124, 230));
        c.drawRect(x/2-pos+70, y/2+pos-70, x/2+pos-70, y/2-pos+70, p);
        // circulo
        p.setColor(getResources().getColor(R.color.white));
        c.drawCircle(x/2, y/2, 130, p);

        // Rectangulo
        p.setColor(Color.argb(211, 40, 124, 230));
        c.drawRect(x/2-pos+105, y/2+pos-105, x/2+pos-105, y/2-pos+105, p);
        // circulo
        p.setColor(getResources().getColor(R.color.white));
        c.drawCircle(x/2, y/2, 95, p);

    }
}