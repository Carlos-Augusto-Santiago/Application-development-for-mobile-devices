package com.example.myapplication;

import android.content.Context;
import android.os.*;
import android.app.*;
import android.view.*;
import android.graphics.*;
public class MainActivity extends Activity{
    Lienzo l;
    public void onCreate(Bundle b){
        super.onCreate(b);
        l = new Lienzo(this);
        setContentView(l);
    }
    class Lienzo extends View{
        Path pt;
        Paint pn;
        String s;
        float x, y, x1, y1, x2, y2, x3, y3;
        public Lienzo(Context c){
            super(c);
            pt = new Path();
        }
        public void onDraw(Canvas c){
            x1 = 225;
            y1 = 325;

            x2 = 825;
            y2 = 725;

            x3 = 525;
            y3 = 1525;
            pn = new Paint();
            pn.setStyle(Paint.Style.STROKE);
            pn.setStrokeWidth(5);
            pn.setColor(Color.RED);
            c.drawColor(Color.rgb(250, 250, 100));
            c.drawRect(200,300,250,350,pn);
            c.drawCircle(225, 325, 20, pn);
            c.drawRect(800,700,850,750,pn);
            c.drawCircle(825, 725, 20, pn);
            c.drawRect(500, 1500,550, 1550,pn);
            c.drawCircle(525, 1525, 20, pn);

            pn.setStrokeWidth(20);
            pn.setColor(Color.BLACK);

            if(s == "00") pt.moveTo(x, y);
            if(s == "xy1") pt.lineTo(x1, y1);
            if(s == "xy2") pt.lineTo(x2, y2);
            if(s == "xy3") pt.lineTo(x3, y3);

            c.drawPath(pt, pn);
        }
        public boolean onTouchEvent(MotionEvent e){
            x = e.getX();
            y = e.getY();
            if(e.getAction() == MotionEvent.ACTION_DOWN) s = "00";
            if((x >= 200 && x <= 250) && (y >= 300  && y <= 350)){
                if(e.getAction() == MotionEvent.ACTION_MOVE) s = "xy1";
            }
            if((x >= 800 && x <= 850) && (y >= 700  && y <= 750)){
                if(e.getAction() == MotionEvent.ACTION_MOVE) s = "xy2";
            }
            if((x >= 500 && x <= 550) && (y >= 1500  && y <= 1550)){
                if(e.getAction() == MotionEvent.ACTION_MOVE) s = "xy3";
            }
            invalidate();
            return true;
        }

    }
}
