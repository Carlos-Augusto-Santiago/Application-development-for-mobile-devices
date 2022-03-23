package com.example.myapplication;

import android.app.*;
import android.net.*;
import android.os.*;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.*;
import android.widget.*;

public class MainActivity extends Activity implements OnClickListener {
    Button jbn;
    VideoView vvw1, vvw2;
    Uri uri;
    MediaController mcr;
    DisplayMetrics displayMetrics;
    int x;

    @Override
    public void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activity_main);
        displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        x = displayMetrics.widthPixels;

        jbn = (Button) findViewById(R.id.xbn);
        jbn.setOnClickListener(this);
    }

    public void onClick(View v) {
        vvw1 = (VideoView) findViewById(R.id.xvv1);
        vvw2 = (VideoView) findViewById(R.id.xvv2);
        uri = Uri.parse("android.resource://com.example.myapplication/" + R.raw.this_is_love);
        mcr = new MediaController(this);
        vvw1.setMediaController(mcr);
        vvw1.setVideoURI(uri);
        vvw1.start();

        vvw2.setMediaController(mcr);
        vvw2.setVideoURI(uri);
        vvw2.start();
    }
}