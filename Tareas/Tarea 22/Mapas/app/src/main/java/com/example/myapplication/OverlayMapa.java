package com.example.myapplication;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;

import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.Projection;

public class OverlayMapa extends Overlay {
    private Double latitud = 37.40 * 1E6;
    private Double longitud = -5.99 * 1E6;

    public void draw(Canvas canvas, MapView mapView, boolean shadow) {
        Projection projection = mapView.getProjection();
        GeoPoint geoPoint = new GeoPoint(latitud.intValue(), longitud.intValue());
        if (shadow == false) {
            Point centro = new Point();
            projection.toPixels(geoPoint, centro);
            Paint p = new Paint();
            p.setColor(Color.BLUE);
            canvas.drawCircle(centro.x, centro.y, 5, p);
            canvas.drawText("Sevilla", centro.x + 10, centro.y + 5, p);
        }
    }
}