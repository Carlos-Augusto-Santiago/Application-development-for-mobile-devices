package com.example.sos;


import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.sql.Time;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    Thread thread;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = (Button) findViewById(R.id.button);
        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        CameraManager cameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
        final String[] cameraId = {null};
        final boolean[] encendido = {false};

        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    cameraId[0] = cameraManager.getCameraIdList()[0];
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        int i = 0;
                        while(true){
                            if (encendido[0]){
                                cameraManager.setTorchMode(cameraId[0], true);//el true hace que nuestra linterna se encienda
                                imageView.setImageResource(R.drawable.off);
                                if (i < 3){
                                    thread.sleep(1000);
                                    cameraManager.setTorchMode(cameraId[0], false);//el false hace que nuestra linterna se apague
                                    thread.sleep(500);
                                }
                                else if(i < 6){
                                    thread.sleep(2000);
                                    cameraManager.setTorchMode(cameraId[0], false);//el false hace que nuestra linterna se apague
                                    thread.sleep(500);
                                }
                                else{
                                    i = -1;
                                }
                                i++;
                            }
                            else{
                                cameraManager.setTorchMode(cameraId[0], false);//el false hace que nuestra linterna se apague
                                imageView.setImageResource(R.drawable.on);
                                i = 0;
                            }
                        }
                    }
                } catch (CameraAccessException | InterruptedException e){

                }
            }
        });
        thread.start();

        btn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                //todo lo que pongas aquí va a hacerse cuando presiones el botón
                encendido[0] = !encendido[0];

            }
        });
    }
}