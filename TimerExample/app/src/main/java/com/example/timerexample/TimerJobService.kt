package com.example.timerexample

import android.app.job.JobParameters
import android.app.job.JobService
import android.content.Intent
import android.util.Log
import com.example.timerexample.MainActivity.Companion.TAG

class TimerJobService: JobService() {
    override fun onStartJob(params: JobParameters?): Boolean {
        Log.d(TAG, "Se ha inicializado TimerJobService")

        //Lanza un intent personalizado
        val intent = Intent("com.example.timer_intent")
        //Lanza el intent como broadCast
        sendBroadcast(intent)
        return false
    }

    override fun onStopJob(params: JobParameters?): Boolean {
        return true
    }

}
