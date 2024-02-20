package com.example.timerexample

import android.app.job.JobParameters
import android.app.job.JobService
import android.content.Intent
import android.util.Log
import com.example.timerexample.MainActivity.Companion.TAG

class TimerJobService: JobService() {
    override fun onStartJob(p0: JobParameters?): Boolean {
        Log.d(TAG,"Se ha inicializado TimerJobService")
        //Lanza un intent personalizado
        val intent= Intent("com.example.timer_intent")
        //Lanza el intent como broadCast
        sendBroadcast(intent)
        return true
    }

    override fun onStopJob(p0: JobParameters?): Boolean {
        return true
    }
    companion object{
        const val TAG="JobSchedulerExample"
    }

}
