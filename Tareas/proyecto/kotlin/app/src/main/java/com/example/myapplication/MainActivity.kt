package com.example.myapplication

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val blue = BluetoothJhr(this,disp, Main2Activity::class.java)
        //para listView normal solo accedemos a onBluetooth
        blue.onBluetooth()

        //para listView Custom return ArrayList<String>
        //val a = blue.dispEmparejados()

        //blue.bluetoothSeleccionAddres("Addres")

        disp.setOnItemClickListener { adapterView, view, i, l ->
            blue.bluetoothSeleccion(i)
        }
    }
}