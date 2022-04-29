package com.example.proyecto1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Set;
import java.util.UUID;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Set;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    private BluetoothAdapter btAdapter = null;
    private BluetoothSocket btSocket = null;
    public static String address = null;
    private static final UUID BTMODULEUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    public boolean activar = false;
    Handler bluetoothIn;
    final int handlerState = 0;
    Button temp, button, conectar, desconectar;
    private ConnectedThread myConexionBT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        temp = findViewById(R.id.xbtnT);
        conectar = findViewById(R.id.conectar);

        btAdapter = BluetoothAdapter.getDefaultAdapter();
        verificarBluetooth();

        //Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
        //startActivityForResult(intent, 1);

        conectar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                conectar();
            }
        });

        temp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myConexionBT.write("t");

            }
        });
    }


    private BluetoothSocket createBluetoothSocket(BluetoothDevice device) throws IOException {

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            Log.d("BT Socket","BT Socket Creado");
            return device.createRfcommSocketToServiceRecord(BTMODULEUUID);
        } else {
            Log.d("BT Socket","BT Socket Error al crear");
            return device.createRfcommSocketToServiceRecord(BTMODULEUUID);
        }
    }

    private void verificarBluetooth() {
        if (btAdapter.isEnabled()) {
            Log.d("BT Adapter","BT Adapter habilitado");
            activar = true;
        } else {
            Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                startActivityForResult(intent, 1);
            }
            else{
                startActivityForResult(intent, 1);
            }
        }

    }

    public void conectar() {
        Set<BluetoothDevice> pairedDeveicesList = btAdapter.getBondedDevices();
        if (activar) {
            for (BluetoothDevice pairedDevice : pairedDeveicesList) {
                Log.d("BT Devices",pairedDevice.getName());
                Log.d("BT Devices Address",pairedDevice.getAddress());
                if (pairedDevice.getName().equals("HC-05")) {

                    address = pairedDevice.getAddress();
                }
            }
            BluetoothDevice device = btAdapter.getRemoteDevice(address);

            try {
                btSocket = createBluetoothSocket(device);
                myConexionBT = new ConnectedThread(btSocket);
                Log.d("BT Socket","Socket creado");
            } catch (IOException e) {
                Log.d("BT Socket","Error al crear el socket");
                Toast.makeText(getBaseContext(), "La creacción del Socket fallo", Toast.LENGTH_LONG).show();
            }
            // Establece la conexión con el socket Bluetooth.
            try {
                btSocket.connect();
                Log.d("BT Socket","Socket conectado");
            } catch (IOException e) {
                try {
                    btSocket.close();
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
            }
            myConexionBT = new ConnectedThread(btSocket);
            myConexionBT.start();
            Log.d("BT Start","Conexion iniciada");
        }

    }

    private class ConnectedThread extends Thread {
        private final InputStream mmInStream;
        private final OutputStream mmOutStream;

        public ConnectedThread(BluetoothSocket socket) {

            InputStream tmpIn = null;
            OutputStream tmpOut = null;
            try {
                tmpIn = socket.getInputStream();
                tmpOut = socket.getOutputStream();
            } catch (IOException e) {

            }
            mmInStream = tmpIn;
            mmOutStream = tmpOut;
        }

        public void run() {
            byte[] buffer = new byte[256];
            int bytes;

            // Se mantiene en modo escucha para determinar el ingreso de datos
            while (true) {
                try {

                    bytes = mmInStream.read(buffer);
                    String readMessage = new String(buffer, 0, bytes);
                    // Envia los datos obtenidos hacia el evento via handler
                    bluetoothIn.obtainMessage(handlerState, bytes, -1, readMessage).sendToTarget();
                } catch (IOException e) {
                    break;
                }
            }
        }

        //Envio de trama
        public void write(String input) {
            try {
                mmOutStream.write(input.getBytes());
            } catch (IOException e) {
                //si no es posible enviar datos se cierra la conexión
                Toast.makeText(getBaseContext(), "La Conexión fallo", Toast.LENGTH_LONG).show();
                finish();
            }
        }
    }
}