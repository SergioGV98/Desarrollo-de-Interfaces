package com.example.intentserviceexample

import android.app.IntentService
import android.content.Intent
import android.util.Log

class IntentServiceDownload : IntentService("IntentServiceDownload") {
    override fun onHandleIntent(intent: Intent?) {
        Log.d(TAG, "Servicio iniciado. Procesando descarga...")
        val urlPath = intent?.getStringExtra("urlPath")
        if (urlPath != null) {
            ImageDownloadHelper.downloadFromUrlToGallery(this, urlPath, "image_${System.currentTimeMillis()}.jpg")
        } else {
            Log.e(TAG, "URL de la imagen no proporcionada o es incorrecta.")
        }
    }

    companion object {
        const val TAG = "IntentServiceDownload"
    }
}
