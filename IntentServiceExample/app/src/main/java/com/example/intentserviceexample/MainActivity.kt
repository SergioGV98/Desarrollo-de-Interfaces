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
            intent.putExtra("urlPath", "https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.vidaextra.com%2Fseries%2Funo-animes-inesperados-curiosos-ultimos-anos-anuncia-su-final-dr-stone-confirma-cuarta-ultima-temporada-serie&psig=AOvVaw3Q3pBQvedlJZKIQEdQm-ej&ust=1708506590895000&source=images&cd=vfe&opi=89978449&ved=0CBAQjRxqFwoTCNjGipfJuYQDFQAAAAAdAAAAABAD")
            startService(intent)
        }
    }
}