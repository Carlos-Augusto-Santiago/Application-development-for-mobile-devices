package com.example.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.*;
import android.widget.*;
public class MainActivity extends Activity{
    WebSettings ws;
    WebView wv;
    EditText jet;
    String s="file:///android_asset/index.html";
    public void onCreate(Bundle b){
        super.onCreate(b); setContentView(R.layout.activity_main);

        wv = (WebView) findViewById(R.id.xwv);
        wv.loadUrl(s);
        wv.setWebViewClient(new Cliente());
        wv.getSettings().setJavaScriptEnabled(true);
        wv.getSettings().setBuiltInZoomControls(true);
    }
    class Cliente extends WebViewClient{
        public boolean shouldOverrideUrlLoading(WebView view, String url){
            return false;
        }
    }

}
