package com.example.intentserviceexample

import android.app.Service
import android.content.Intent
import android.os.AsyncTask
import android.os.IBinder
import android.util.Log

/**
 * Se ejecutan en el MainThread y hay que crear un objeto AsyncTask
 * donde hay metodos quese ejecutan en el MainThread y otros en un hilo
 * diferente -> doInBackground()
 */
class ServiceDownload: Service() {

    private lateinit var imageDownloadAsyncTask: ImageDownloadAsyncTask

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    /**
     * Clase que realiza una tarea de forma asincrona
     */
    inner class ImageDownloadAsyncTask: AsyncTask<String, Unit, Unit>(){
        override fun doInBackground(vararg url: String?) {
            Log.d(TAG, "Se inicia la tarea ImageDownloadAsyncTask")
            ImageDownloadHelper.downloadFromUrl(url[0], "prueba.png")
        }

        /**
         * Este metodo se ejecuta en el hilo principal de la Activity/fragment
         */
        override fun onPostExecute(result: Unit?) {
            super.onPreExecute()
            Log.d(TAG, "Finaliza el tarea ImageDownloadAsyncTask")
            //Cuando finaliza la tarea -> se finaliza el servicio
            stopSelf()
        }

        override fun onCancelled(result: Unit?) {
            super.onCancelled(result)
            Log.d(TAG, "Se cancela el tarea ImageDownloadAsyncTask")
        }
    }

    /**
     * Este es el metodo que se ejecuta cuando se inicia el servicio con startservice desde una activity/fragment
     * Se devuelve un int que indica como reiniciar el servicio
     */
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        super.onStartCommand(intent, flags, startId)
        Log.d(TAG, "Se inicia el servicio")
        imageDownloadAsyncTask = ImageDownloadAsyncTask()
        imageDownloadAsyncTask.execute(intent?.getStringExtra("urlPath"))
        return START_STICKY
    }

    companion object {
        const val TAG = "ServiceDownload"
    }
}
















