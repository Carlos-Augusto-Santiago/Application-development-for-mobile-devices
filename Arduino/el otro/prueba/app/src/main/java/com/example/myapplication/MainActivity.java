package com.example.myapplication;

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
    public boolean activar;
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
                myConexionBT.read();
                //TODO para ver el resultado de implementar este read() abre el Logcat y escribe en la barra de busqueda "BT"
                //con eso te va a filtrar las salidas del Logcat con los logs que yo puse en el codigo para seguir el progreso de el sistema
                //este llamado a el .read() deberia imprimir aprox 26 caracteres que se obtienen desde el input de la conexion
                //si llega hasta esta parte del read y te imprime valores en el Logcat habrá que ver a qué pertenecen esos valores y si podemos obtener los datos de los sensores
                //desde ahí.
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
        if (activar) {
            BluetoothDevice device = btAdapter.getRemoteDevice(address);

            try {
                btSocket = createBluetoothSocket(device);
                myConexionBT = new ConnectedThread(btSocket);
            } catch (IOException e) {
                Toast.makeText(getBaseContext(), "La creacción del Socket fallo", Toast.LENGTH_LONG).show();
            }
            // Establece la conexión con el socket Bluetooth.
            try {
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    btSocket.connect();
                    return;
                }
                else{
                    btSocket.connect();
                }
            } catch (IOException e) {
                try {
                    btSocket.close();
                } catch (IOException e2) {

                }
            }
            myConexionBT = new ConnectedThread(btSocket);
            myConexionBT.start();
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

        //Prueba de recibo de trama
        public void read(){
            try {
                for (int i = 0; i < 26; i++) {
                    byte b = (byte) mmInStream.read();
                    Log.d("BT Input", String.valueOf(b));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}