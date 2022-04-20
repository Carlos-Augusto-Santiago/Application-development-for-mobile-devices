package com.example.myapplication;

import androidx.fragment.app.FragmentActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.location.Geocoder;
import android.location.GpsSatellite;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
    private GoogleMap mMap;
    private Button btnS, btnC, btnA, btnM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
// Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        btnS = (Button) findViewById(R.id.xbtnS);
        btnC = (Button) findViewById(R.id.xbtnC);
        btnA = (Button) findViewById(R.id.xbtnA);
        btnM = (Button) findViewById(R.id.xbtnM);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case, * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to
     * install
     * it inside the SupportMapFragment. This method will only be triggered once the user has * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        // Add a marker in Sydney and move the camera
        LatLng mexico = new LatLng(19.42847, -99.12766);
        mMap.addMarker(new MarkerOptions().position(mexico).title("Marker in Mexico"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(mexico));

        btnS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mMap.getMapType() == GoogleMap.MAP_TYPE_SATELLITE){
                    mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                }
                else{
                    mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                }
            }
        });

        btnC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LatLng sevilla = new LatLng(37.3826, -5.99629);
                mMap.addMarker(new MarkerOptions().position(sevilla).title("Marker in Sevilla"));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(sevilla));
                mMap.animateCamera( CameraUpdateFactory.zoomTo( 10.0f ) );
            }
        });

        btnA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LatLng sevilla = new LatLng(37.3826, -5.99629);
                mMap.addMarker(new MarkerOptions().position(sevilla).title("Marker in Sevilla"));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(sevilla));
                float zoom = mMap.getCameraPosition().zoom;
                for(float i=zoom; i<15.0; i++) mMap.animateCamera( CameraUpdateFactory.zoomTo( i ) );
            }
        });

        btnM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mMap.moveCamera(CameraUpdateFactory.scrollBy(40,40));
            }
        });
    }
}