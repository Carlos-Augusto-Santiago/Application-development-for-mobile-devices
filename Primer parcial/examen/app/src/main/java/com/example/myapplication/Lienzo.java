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
        super.onDraw(c);
        p = new Paint();
        x = c.getWidth();
        y = c.getHeight();
        p.setColor(Color.WHITE);
        c.drawPaint(p);
        p.setColor(Color.BLACK);
        p.setTextSize(20);
        p.setColor(Color.rgb(0, 0, 255));

        c.drawCircle(x/3,y/3, 20, p);
        c.drawCircle(x/2,y/2, 20, p);
        c.drawCircle(x/3+350,y/3, 20, p);

        c.drawCircle(x+200,y+200, 20, p);



    }
}