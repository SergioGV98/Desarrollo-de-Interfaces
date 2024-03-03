package com.example.intentserviceexample

import android.annotation.SuppressLint
import android.app.IntentService
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.os.Build
import android.provider.MediaStore
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import java.io.IOException
import java.net.URL

class IntentServiceDownload : IntentService("IntentServiceDownload") {

    override fun onHandleIntent(intent: Intent?) {
        Log.d(TAG, "Servicio iniciado. Procesando descarga...")
        val urlPath = intent?.getStringExtra("urlPath")
        if (urlPath != null) {
            val fileName = "image_${System.currentTimeMillis()}.jpg"
            val result = ImageDownloadHelper.downloadFromUrlToGallery(this, urlPath, fileName)
            if (result) {
                Log.d(TAG, "Imagen descargada correctamente: $fileName")
                showNotification("Imagen descargada", "La imagen se ha descargado correctamente: $fileName")
            } else {
                Log.e(TAG, "Error al descargar la imagen")
            }
        } else {
            Log.e(TAG, "URL de la imagen no proporcionada o es incorrecta.")
        }
    }

    @SuppressLint("MissingPermission")
    private fun showNotification(title: String, message: String) {
        val channelId = "download_channel"
        createNotificationChannel(channelId)

        val notificationBuilder = NotificationCompat.Builder(this, channelId)
            .setContentTitle(title)
            .setContentText(message)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .build()

        with(NotificationManagerCompat.from(this)) {
            notify(System.currentTimeMillis().toInt(), notificationBuilder)
        }
    }

    private fun createNotificationChannel(channelId: String) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Download"
            val descriptionText = "Canal de notificaciones de descarga"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(channelId, name, importance).apply {
                description = descriptionText
            }

            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    companion object {
        private const val TAG = "IntentServiceDownload"
    }
}
