package com.example.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.*;
import android.widget.*;
public class MainActivity extends Activity implements OnClickListener{
    Button jbn1, jbn2, jbn3, jbn4;
    TextView jtv1;
    WebSettings ws;
    WebView wv;
    EditText jet;
    String s="https://www.google.com/";
    public void onCreate(Bundle b){
        super.onCreate(b); setContentView(R.layout.activity_main);
        jbn1 = (Button) findViewById(R.id.xbn1);
        jbn1.setOnClickListener(this);
        jbn2 = (Button) findViewById(R.id.xbn2);
        jbn2.setOnClickListener(this);
        jbn3 = (Button) findViewById(R.id.xbn3);
        jbn3.setOnClickListener(this);
        jbn4 = (Button) findViewById(R.id.xbn4);
        jbn4.setOnClickListener(this);
        jet = (EditText) findViewById(R.id.xet);
        wv = (WebView) findViewById(R.id.xwv);
        jtv1 = (TextView) findViewById(R.id.xtv1);
        wv.setWebViewClient(new Cliente());
        ws = wv.getSettings();
        ws.setBuiltInZoomControls(true);
        ws.setJavaScriptEnabled(true);
        ws.setUseWideViewPort(true);
    }
    class Cliente extends WebViewClient{
        public boolean shouldOverrideUrlLoading(WebView view, String url){
            return false;
        }
        public void onPageFinished(WebView view, String url){
            jet.setText(url);
            jtv1.append(url + "\n");
        }
    }
    public void onClick(View v){
        int id = v.getId(); switch(id){
            case R.id.xbn1: wv.goBack(); break;
            case R.id.xbn2: wv.loadUrl(s); break;
            case R.id.xbn3: wv.goForward(); break;
            case R.id.xbn4: wv.loadUrl(jet.getText() + ""); break;
        }
    }
}
