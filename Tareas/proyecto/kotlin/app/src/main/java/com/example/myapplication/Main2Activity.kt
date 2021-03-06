package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main2.*
import kotlin.concurrent.thread

class Main2Activity : AppCompatActivity() {

    lateinit var blue :BluetoothJhr
    var initConexion = false
    var offHilo = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        blue = BluetoothJhr(this, MainActivity::class.java)
        //si se pierde conexion no sale sino que avisa con un mensaje  error
        blue.exitErrorOk(true)
        //mensaje conectado
        blue.mensajeConexion("Conectado jhr")
        //mensaje de error
        blue.mensajeErrorTx("algo salio mal")


        thread(start = true){
            while (!initConexion && !offHilo){
                Thread.sleep(500)
            }

            while (!offHilo){
                var mensaje = blue.mRx()
                if (mensaje!=""){
                    pantallaRx.post {
                        pantallaRx.text = mensaje
                    }
                }
                Thread.sleep(100)
            }
        }
        envia.setOnClickListener {
            blue.mTx("ola")
        }
        envia.setOnLongClickListener {
            blue.exitConexion()
            offHilo = true
            false
        }
    }

    override fun onResume() {
        initConexion =  blue.conectaBluetooth()
        super.onResume()
    }

    override fun onPause() {
        offHilo = true
        blue.exitConexion()
        super.onPause()
    }
}