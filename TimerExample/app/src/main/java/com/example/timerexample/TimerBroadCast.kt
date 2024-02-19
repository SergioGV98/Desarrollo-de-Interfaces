package com.example.timerexample

import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import com.example.timerexample.MainActivity.Companion.JOB_ID


class TimerBroadCast : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val notificationManager =
            context?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val builder = NotificationCompat.Builder(context, "channelId")
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setContentTitle("Alarma")
            .setContentText("¡Es la hora!")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setAutoCancel(true)

        notificationManager.notify(JOB_ID, builder.build())
    }
}
