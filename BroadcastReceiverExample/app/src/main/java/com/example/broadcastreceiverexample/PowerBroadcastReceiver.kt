package com.example.broadcastreceiverexample

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.widget.Toast

class PowerBroadcastReceiver: BroadcastReceiver() {

    private var isAirplaneModeEnabled: Boolean = false

    override fun onReceive(context: Context?, intent: Intent?) {
        if (connectivityReceiverListener != null) {
            connectivityReceiverListener!!.onNetworkConnectionChanged(
                isConnectedOrConnecting(
                    context!!
                )
            )
        }

        //El estado en modo avion se recoge como BooleanExtra con KEY=state
        isAirplaneModeEnabled = intent?.getBooleanExtra("state", false) ?: return
        //El nivel de la bateria se recibe como IntExtra con KEY=level
        val batteryLevel: Int = intent.getIntExtra("level", 0)
        //Gestion del ACTION del Intent qeu recibimos
        val intentAction = intent.action

        if (intentAction != null) {
            var toastMessage = ""
            when (intentAction) {
                Intent.ACTION_POWER_CONNECTED -> toastMessage = "Power is connected"
                Intent.ACTION_POWER_DISCONNECTED -> toastMessage = "Power is disconected"
                Intent.ACTION_BATTERY_CHANGED -> toastMessage = "{$batteryLevel %}"
                Intent.ACTION_AIRPLANE_MODE_CHANGED -> toastMessage = if (isAirplaneModeEnabled) "Flight Mode is On"
                else "Flight Mode is off"
            }
            Toast.makeText(context, toastMessage, Toast.LENGTH_SHORT).show()
        }

    }

    /**
     * Este metodo comprueba si esta activiada la Red de DATOS o WIFI y si es TRUE
     * en el codigo del IF llama al metodo onNetworkConnectionChanged del
     * ActivityMain
     */
    private fun isConnectedOrConnecting(context: Context): Boolean {
        val connMgr = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connMgr.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnectedOrConnecting
    }

    interface ConnectivityReceiverListener {
        fun onNetworkConnectionChanged(isConnected: Boolean)
    }

    companion object {
        var connectivityReceiverListener: ConnectivityReceiverListener? = null
    }

}
