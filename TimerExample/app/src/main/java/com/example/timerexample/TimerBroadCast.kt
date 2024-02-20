package com.example.timerexample

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class TimerBroadCast : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        //No se puede iniciar una Activity desde el conteo del BroadCastReceiver
        //desde la version Android Q (API 29)
        Log.d(TAG, "Se recibe el intent personalizado")
        val intent = Intent(context, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        context?.startActivity(intent)
        //Lanza una notificacion
    }

    companion object{
        const val TAG="JobSchedulerExample"
    }

}
