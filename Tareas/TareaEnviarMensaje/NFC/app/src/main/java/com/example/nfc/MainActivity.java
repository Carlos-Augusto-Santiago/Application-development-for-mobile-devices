package com.example.nfc;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.NfcAdapter;
import android.nfc.NfcManager;
import android.nfc.Tag;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private static final int DIALOG_WRITE_URL = 1;
    private EditText mMyUrl;
    private Button mMyWriteUrlButton;
    private boolean mWriteUrl = false;
    private static final int PENDING_INTENT_TECH_DISCOVERED = 1;
    private NfcAdapter mNfcAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMyUrl = findViewById(R.id.myUrl);
        mMyWriteUrlButton = findViewById(R.id.myWriteUrlButton);

        mMyWriteUrlButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mWriteUrl = true;
                MainActivity.this.showDialog(DIALOG_WRITE_URL);
            }
        });
    }

    @Override
    public void onResume(){
        super.onResume();
        NfcManager nfcManager = (NfcManager) this.getSystemService(Context.NFC_SERVICE);
        mNfcAdapter = nfcManager.getDefaultAdapter();

        PendingIntent pi = createPendingResult(PENDING_INTENT_TECH_DISCOVERED, new Intent(), 0);

        mNfcAdapter.enableForegroundDispatch(this, pi,
                new IntentFilter[]{ new IntentFilter(NfcAdapter.ACTION_TECH_DISCOVERED)},
                new String[][]{
                        new String[]{"android.nfc.tech.NdefFormatable"},
                        new String[] {"android.nfc.tech.Ndef"}
                });
    }

    @Override
    public void onPause(){
        super.onPause();
        mNfcAdapter.disableForegroundDispatch(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case PENDING_INTENT_TECH_DISCOVERED:
                resolveIntent(data, true);
                break;
        }
    }

    private void resolveIntent(Intent data, boolean foregroundDispatch){
        String action = data.getAction();

        if (NfcAdapter.ACTION_TECH_DISCOVERED.equals(action)){
            Tag tag = data.getParcelableExtra(NfcAdapter.EXTRA_TAG);

            if (foregroundDispatch && mWriteUrl){
                mWriteUrl = false;

                dismissDialog(DIALOG_WRITE_URL);
            }
        }
    }

    protected Dialog onCreateDialog(int id, Bundle args){
        switch (id){
            case DIALOG_WRITE_URL:
                return new AlertDialog.Builder(this)
                        .setTitle("Write URL to tag...")
                        .setMessage("Touch tag to start writing.")
                        .setCancelable(true)
                        .setNeutralButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        })
                        .setOnCancelListener(new DialogInterface.OnCancelListener() {
                            @Override
                            public void onCancel(DialogInterface dialogInterface) {
                                mWriteUrl = false;
                            }
                        }).create();
        }
        return null;
    }
}