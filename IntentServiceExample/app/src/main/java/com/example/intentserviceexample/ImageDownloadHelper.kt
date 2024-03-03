package com.example.intentserviceexample

import android.content.ContentValues
import android.content.Context
import android.provider.MediaStore
import android.util.Log
import java.io.IOException
import java.net.URL

object ImageDownloadHelper {
    fun downloadFromUrlToGallery(context: Context, imageURL: String, fileName: String): Boolean {
        try {
            val url = URL(imageURL)
            val imageContentValues = ContentValues().apply {
                put(MediaStore.Images.Media.DISPLAY_NAME, fileName)
                put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
            }

            val resolver = context.contentResolver
            val uri = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, imageContentValues)
            uri?.let {
                resolver.openOutputStream(it).use { outputStream ->
                    val inputStream = URL(imageURL).openStream()
                    inputStream.use { input ->
                        input.copyTo(outputStream!!, bufferSize = 1024)
                    }
                    Log.d("ImageDownloadHelper", "Imagen guardada en la galería: $fileName")
                    return true
                }
            } ?: run {
                Log.e("ImageDownloadHelper", "Error al guardar imagen: no se pudo crear URI")
                return false
            }
        } catch (e: IOException) {
            Log.e("ImageDownloadHelper", "Error al guardar la imagen en la galería", e)
            return false
        }
    }
}
