package com.example.timerexample

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class TimerBroadCast : BroadcastReceiver() {

    @SuppressLint("MissingPermission")
    override fun onReceive(context: Context?, intent: Intent?) {
        context ?: return

        val title = intent?.getStringExtra(EXTRA_TITLE)
        val content = intent?.getStringExtra(EXTRA_CONTENT)

        if (!title.isNullOrBlank() && !content.isNullOrBlank()) {
            val notification = NotificationCompat.Builder(context, MainActivity.CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle(title)
                .setContentText(content)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true)
                .build()

            val notificationManager = NotificationManagerCompat.from(context)
            notificationManager.notify(NOTIFICATION_ID, notification)
        } else {
            Log.e(TAG, "No se proporcionó título o contenido para la notificación")
        }
    }


    companion object {
        private const val TAG = "TimerBroadCast"
        private const val NOTIFICATION_ID = 123
        const val EXTRA_TITLE = "extra_title"
        const val EXTRA_CONTENT = "extra_content"
    }
}
