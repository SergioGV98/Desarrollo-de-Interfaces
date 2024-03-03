package com.example.intentserviceexample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.btStartIntentService).setOnClickListener {
            val intent = Intent(this, IntentServiceDownload::class.java)
            intent.putExtra("urlPath", "https://unsplash.com/photos/A-NVHPka9Rk/download?force=true")
            startService(intent)
        }

        findViewById<Button>(R.id.btStartService).setOnClickListener {
            val intent = Intent(this, ServiceDownload::class.java)
            intent.putExtra("urlPath", "https://unsplash.com/photos/A-NVHPka9Rk/download?force=true")
            startService(intent)
        }

        findViewById<Button>(R.id.btCancelService).setOnClickListener {
            val intent = Intent(this, ServiceDownload::class.java)
            stopService(intent)
        }


    }
}