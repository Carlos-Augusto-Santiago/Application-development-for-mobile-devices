package com.example.sos;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
public class MainActivity extends Activity {
    private boolean encendida = false;
    private Camera camera;
    private Button button;
    @Override
    protected void onStop() {
        super.onStop();
        if (camera != null) camera.release();
    }
    @Override
    public void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activity_main);
        button = (Button) findViewById(R.id.LED);
        Context context = this;
        PackageManager pm = context.getPackageManager();
        if (!pm.hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
            Log.e("err", "El mÛvil no tiene c·mara!");
            return;
        }
        camera = Camera.open();
        final Parameters p = camera.getParameters();
        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                if (encendida) {
                    Log.i("info", "Linterna apagada!");
                    p.setFlashMode(Parameters.FLASH_MODE_TORCH);
                    camera.setParameters(p);
                    camera.stopPreview();
                    encendida = false;

                } else {
                    Log.i("info", "Linterna encendida!");
                    p.setFlashMode(Parameters.FLASH_MODE_TORCH);
                    camera.setParameters(p);
                    camera.startPreview();
                    encendida = true;
                }
            }
        });
    }
}