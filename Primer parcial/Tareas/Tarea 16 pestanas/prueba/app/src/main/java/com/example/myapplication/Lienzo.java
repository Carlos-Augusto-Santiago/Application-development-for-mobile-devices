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
        p.setStrokeWidth(30F);
        p.setColor(Color.WHITE); // Fondo blanco
        c.drawPaint(p);
        p.setColor(Color.BLACK); // Texto negro
        p.setTextSize(20);
        p.setAntiAlias(true);
        p.setDither(true);
        p.setStyle(Paint.Style.STROKE);
        int pos = 200;

        // circulo
        p.setColor(Color.argb(100, 200, 100, 100));
        c.drawCircle(x/2, y/2, 250, p);
        // Rectangulo
        p.setColor(Color.argb(211, 40, 124, 230));
        c.drawRect(x/2-pos-50, y/2+pos+50, x/2+pos+50, y/2-pos-50, p);

        // circulo
        p.setColor(Color.argb(100, 200, 100, 100));
        c.drawCircle(x/2, y/2, 300, p);
        // Rectangulo
        p.setColor(Color.argb(211, 40, 124, 230));
        c.drawRect(x/2-pos-100, y/2+pos+100, x/2+pos+100, y/2-pos-100, p);

        // circulo
        p.setColor(Color.argb(100, 200, 100, 100));
        c.drawCircle(x/2, y/2, 350, p);
        // Rectangulo
        p.setColor(Color.argb(211, 40, 124, 230));
        c.drawRect(x/2-pos-150, y/2+pos+150, x/2+pos+150, y/2-pos-150, p);
    }
}