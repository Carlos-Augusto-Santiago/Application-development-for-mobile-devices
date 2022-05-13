package com.example.mapas;

import androidx.fragment.app.FragmentActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class Mapa extends FragmentActivity implements OnMapReadyCallback {
    private GoogleMap mMap;
    private Button btnVer;
    SQLiteDatabase sqld;
    Bundle bdl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa);
// Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        btnVer = findViewById(R.id.btnVer);
        bdl = getIntent().getExtras();

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
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        btnVer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                LatLng paisObtenido = new LatLng(Float.parseFloat(bdl.getString("Latitud")), Float.parseFloat(bdl.getString("Longitud")));
                mMap.addMarker(new MarkerOptions().position(paisObtenido).title("Marker in "+ bdl.getString("Pais")));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(paisObtenido));
                mMap.animateCamera( CameraUpdateFactory.zoomTo( 10.0f ) );
            }
        });
    }
}