package com.example.intentserviceexample

import android.os.Environment
import android.util.Log
import java.io.BufferedInputStream
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.net.URL


object ImageDownloadHelper {
    /**
     * Método estático que descarga una imagen
     * @param imageURL
     * @param fileName
     * @return
     */
    fun downloadFromUrl(imageURL: String?, fileName: String): Long {
        return try {
            val url = URL(imageURL)

            //Utilizamos el directorio por defecto de la Galería
            val path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM)
            val file = File(path, fileName)
            path.mkdirs()
            Log.d("ServiceImageDownload", "download begining")
            Log.d("ServiceImageDownload", "download url:$url")
            Log.d("ServiceImageDownload", "downloaded file name:$fileName")
            /* Se abre la conexión con la URL*/
            val ucon = url.openConnection()

            val `is` = ucon.getInputStream()
            val bis = BufferedInputStream(`is`)

            val baf = ByteArrayOutputStream(50)
            var current = 0
            while (bis.read().also { current = it } != -1) {
                baf.write(current.toByte().toInt())
            }

            val fos = FileOutputStream(file)
            fos.write(baf.toByteArray())
            fos.close()
            1
        } catch (e: IOException) {
            Log.d("ServiceImageDownload", "Error: $e")
            0
        }
    }
}