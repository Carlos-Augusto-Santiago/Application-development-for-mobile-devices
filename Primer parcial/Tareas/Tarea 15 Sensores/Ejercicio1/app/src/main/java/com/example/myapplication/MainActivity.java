package com.example.myapplication;

import android.content.Context;
import android.hardware.*;
import android.os.*;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    SensorManager sm;
    Sensor s;
    SensorEventListener se;
    int n;
    double x, y, z, a, m, g;
    TextView jtvX, jtvY, jtvZ, jtvA, jtvM, jtvG;
    public void onCreate(Bundle b){
        super.onCreate(b);
        setContentView(R.layout.activity_main);
        sm = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        s = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        x=0; y=0; z=0; a=0; m=0; n=0;
        g = SensorManager.STANDARD_GRAVITY;
        jtvX = findViewById(R.id.xtvX);
        jtvY = findViewById(R.id.xtvY);
        jtvZ = findViewById(R.id.xtvZ);
        jtvA = findViewById(R.id.xtvA);
        jtvM = findViewById(R.id.xtvM);
        jtvG = findViewById(R.id.xtvG);
        new MiAsincronia().execute();
        se = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                x = sensorEvent.values[0];
                y = sensorEvent.values[1];
                z = sensorEvent.values[2];
                a = Math.sqrt(x*x + y*y + z*z);
                if(a>m)
                    m = a;
            }
            @Override
            public void onAccuracyChanged(Sensor sensor, int i) { }
        };

    }
    class MiAsincronia extends AsyncTask<Void, Void, Void>{
        protected Void doInBackground(Void... x){
            while(true){
                try{
                    Thread.sleep(100); // 100 milisegundos
                } catch(InterruptedException e){
                    e.printStackTrace();
                }
                n++;
                publishProgress();
            }
        }
        protected void onProgressUpdate(Void... progress){
            jtvX.setText(" " + x + "\n");
            jtvY.setText(" " + y + "\n");
            jtvZ.setText(" " + z + "\n");
            jtvA.setText(" " + a + "\n");
            jtvM.setText(" " + m + "\n");
            jtvG.setText(" " + g + "\t\tActualización: " + n + "(ms)");
        }
    }
}