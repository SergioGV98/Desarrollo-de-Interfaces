package com.example.broadcastreceiverexample

import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import android.Manifest
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar


class MainActivity : AppCompatActivity(), PowerBroadcastReceiver.ConnectivityReceiverListener {

    private val powerReceiver = PowerBroadcastReceiver()
    private var phoneReceiver = PhoneReceiver()
    private lateinit var imageView: ImageView

    companion object {
        private const val PHONE_PERMISSION_REQUEST_CODE = 1
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        imageView = findViewById(R.id.imageView)

        checkPhoneStatePermission()

        phoneReceiver = PhoneReceiver()
        val intentFilter = IntentFilter().apply {
            addAction(Intent.ACTION_CALL)
        }
        registerReceiver(phoneReceiver, intentFilter)


        /*

        // Recoger el Intent cuando se ha inicializado a traves de otra aplicacion con intent.action.SEND

        //Se recoge el intent que ha recibido esta Activity. Solo hay dos opciones:
        //LAUNCHER o SEND
        val startIntent = intent
        val action = startIntent.action //Accion del intent
        val typeData = startIntent.type //Tipo de dato o mimeType

        if(action == Intent.ACTION_SEND && typeData != null){
            imageView.setImageURI(startIntent.getParcelableExtra(Intent.EXTRA_STREAM))
        }

        //Se registra el conjunto de ACTION que nuestro BroadCastReceiver escuchara/responde
        val filter = IntentFilter()
        filter.addAction(Intent.ACTION_POWER_CONNECTED)
        filter.addAction(Intent.ACTION_POWER_DISCONNECTED)
        filter.addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED)
        filter.addAction(Intent.ACTION_BATTERY_CHANGED)
        filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION)

        //Registrar de forma dinamica el BroadCastReceiver. Esta vinciulado al contexto
        //de la Actividad.
        filter.also {
            registerReceiver(powerReceiver, it)
        }
*/
    }

    private fun checkPhoneStatePermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_PHONE_STATE), PHONE_PERMISSION_REQUEST_CODE)
        } else {
            Toast.makeText(this, "Permiso de llamada telefónica concedido", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            PHONE_PERMISSION_REQUEST_CODE -> {
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    Toast.makeText(this, "Permiso de llamada telefónica concedido", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Permiso de llamada telefónica denegado", Toast.LENGTH_SHORT).show()
                }
                return
            }
            // Puedes chequear otros permisos en caso de que tu aplicación los requiera
        }
    }

    /**
     * Esta funcion se ejecuta desde el BroadCastReceiver cuando hay un cambio en la red.
     * Ejemplo -> Se activa/desactiva el modo avion.
     */
    override fun onNetworkConnectionChanged(isConnected: Boolean) {
        showNetworkMessage(isConnected)
    }

    private fun showNetworkMessage(isConnected: Boolean) {
        val parentLayout = findViewById<View>(android.R.id.content)
        if (!isConnected) {
            Snackbar.make(parentLayout, "You are offline", Snackbar.LENGTH_LONG)
                .show() //Assume "rootLayout" as the root layout of every activity.
        } else {
            Snackbar.make(parentLayout, "You are Online", Snackbar.LENGTH_LONG)
                .show() //Assume "rootLayout" as the root layout of every activity.

        }
    }

    override fun onResume() {
        super.onResume()
        PowerBroadcastReceiver.connectivityReceiverListener = this
    }

    override fun onStop() {
        super.onStop()
        unregisterReceiver(powerReceiver)
    }


}