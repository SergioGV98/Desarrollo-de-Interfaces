package com.example.intentserviceexample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

import android.util.Log

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.btStartIntentService).setOnClickListener {
            Log.d("MainActivity", "Iniciando el servicio de descarga...")
            val intent = Intent(this, IntentServiceDownload::class.java).apply {
                putExtra("urlPath", "https://unsplash.com/photos/A-NVHPka9Rk/download?force=true")
            }
            startService(intent)
        }
    }
}
