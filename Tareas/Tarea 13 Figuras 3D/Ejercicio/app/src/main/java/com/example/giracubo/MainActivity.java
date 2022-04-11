package com.example.giracubo;

import android.content.Context;
import android.os.*;
import android.app.*;
import android.view.*;
import android.graphics.*;

public class MainActivity extends Activity {
    Lienzo l;
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        l = new Lienzo(this);
        setContentView(l);
    }
    class Lienzo extends View{
        Path pt;
        Paint p;
        float x,y;

        public Lienzo (Context c){
            super(c);
            pt = new Path();
        }
        public void onDraw (Canvas c){
            p = new Paint();
            p.setStyle(Paint.Style.STROKE);
            p.setStrokeWidth(3);
            p.setColor(Color.BLACK);
            c.drawColor(Color.rgb(250,250,100));
        }
    }
}