package com.example.localizacion;

import android.app.*;
import android.content.*;
import android.location.*;
import android.os.Bundle;
import android.util.Log;
import android.view.*;
import android.view.View.*;
import android.widget.*;

public class MainActivity extends Activity {
    private Button btnActualizar, btnDesactivar;
    private TextView lblLatitud, lblLongitud, lblPrecision;
    private TextView lblEstado;
    private LocationManager locManager;
    private LocationListener locListener;
    @Override
    public void onCreate(Bundle b){
        super.onCreate(b);
        setContentView(R.layout.activity_main);
        btnActualizar = (Button)findViewById(R.id.BtnActualizar);
        btnDesactivar = (Button)findViewById(R.id.BtnDesactivar);
        lblLatitud = (TextView)findViewById(R.id.LblPosLatitud);
        lblLongitud = (TextView)findViewById(R.id.LblPosLongitud);
        lblPrecision = (TextView)findViewById(R.id.LblPosPrecision);
        lblEstado = (TextView)findViewById(R.id.LblEstado);
        btnActualizar.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                comenzarLocalizacion();
            }
        });
        btnDesactivar.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                locManager.removeUpdates(locListener);
            }
        });
    }
    private void comenzarLocalizacion()    {
        locManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        Location loc = locManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        mostrarPosicion(loc);
        locListener = new LocationListener() {
            public void onLocationChanged(Location location) {
                mostrarPosicion(location);
            }
            public void onProviderDisabled(String provider){
                lblEstado.setText("Provider OFF");
            }
            public void onProviderEnabled(String provider){
                lblEstado.setText("Provider ON ");
            }
            public void onStatusChanged(String provider, int status, Bundle extras){
                Log.i("", "Provider Status: " + status);
                lblEstado.setText("Provider Status: " + status);
            }
        };
        locManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 30000, 0, locListener);
    }
    private void mostrarPosicion(Location loc) {
        if(loc != null){
            lblLatitud.setText("Latitud: " + String.valueOf(loc.getLatitude()));
            lblLongitud.setText("Longitud: " + String.valueOf(loc.getLongitude()));
            lblPrecision.setText("Precision: " + String.valueOf(loc.getAccuracy()));
            Log.i("", String.valueOf(loc.getLatitude() + " - " + String.valueOf(loc.getLongitude())));
        }else{
            lblLatitud.setText("Latitud: (sin_datos)");
            lblLongitud.setText("Longitud: (sin_datos)");
            lblPrecision.setText("Precision: (sin_datos)");
        }
    }
}