package com.example.phonecallnotifier

import android.Manifest
import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.telephony.TelephonyCallback
import android.telephony.TelephonyManager
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat


@RequiresApi(Build.VERSION_CODES.S)
class MyPhoneStateListener(private val context: Context) : TelephonyCallback(),
    TelephonyCallback.CallStateListener {

    private val CHANNEL_ID = "incoming_call_channel"
    private val NOTIFICATION_ID = 101

    init {
        createNotificationChannel()
    }

    override fun onCallStateChanged(state: Int) {
        when (state) {
            TelephonyManager.CALL_STATE_RINGING -> {
                val telephonyManager =
                    context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
                val incomingNumber = getIncomingNumber(telephonyManager)
                showNotification("Llamada entrante", "Número de teléfono: $incomingNumber")
            }
        }
    }

    @SuppressLint("MissingPermission")
    private fun getIncomingNumber(telephonyManager: TelephonyManager): String {
        return telephonyManager.line1Number ?: "Unknown"
    }

    @SuppressLint("MissingPermission")
    private fun showNotification(title: String, message: String) {
        val builder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setContentTitle(title)
            .setContentText(message)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        with(NotificationManagerCompat.from(context)) {
            notify(NOTIFICATION_ID, builder.build())
        }
    }

    private fun createNotificationChannel() {
        val name = "Incoming Call Channel"
        val descriptionText = "Channel for incoming call notifications"
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
            description = descriptionText
        }
        // Register the channel with the system
        val notificationManager: NotificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }
}
