package com.example.intentserviceexample

import android.app.IntentService
import android.content.Intent
import android.util.Log

/**
 * Este servicio se ejecuta en un hilo diferente al Main Thread y es finito
 */
class IntentServiceDownload() : IntentService("IntentServiceDownload") {
    override fun onHandleIntent(intent: Intent?) {
        Log.d(TAG, "IntentService Iniciado")
        ImageDownloadHelper.downloadFromUrl(intent?.getStringExtra("urlPath"), "image.png")
    }

    companion object{
        const val TAG = "IntentServiceDownload"
    }
}
