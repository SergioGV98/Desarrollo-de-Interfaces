package com.example.timerexample

import android.app.job.JobParameters
import android.app.job.JobService
import android.content.Intent
import android.util.Log
import com.example.timerexample.MainActivity.Companion.TAG

class TimerJobService: JobService() {
    override fun onStartJob(params: JobParameters?): Boolean {
        Log.d(TAG, "Se ha inicializado TimerJobService")

        val intent = Intent(this, TimerBroadCast::class.java).apply {
            putExtra(TimerBroadCast.EXTRA_TITLE, "Título de la notificación")
            putExtra(TimerBroadCast.EXTRA_CONTENT, "Contenido de la notificación")
        }

        sendBroadcast(intent)
        return false
    }

    override fun onStopJob(params: JobParameters?): Boolean {
        return true
    }

}
