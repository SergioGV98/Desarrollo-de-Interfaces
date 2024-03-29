package com.example.jobscheluderexample

import android.app.job.JobParameters
import android.app.job.JobService
import android.util.Log

/**
 * Es un servicio que se ejecuta en segundo plano. Es asincrono y mediante la clase Thread se
 * ejecuta en un hilo diferente al Main Thread
 */
class ExampleJobService: JobService() {

    companion object {
        private const val TAG = "ExampleJobService"
        private const val TIME_SLEEP_MILLISECONDS: Long = 1000
    }

    private var jobCanceled : Boolean = false

    private fun doBackgroundWork(p0: JobParameters?) {
        Thread {
            // Un bloque de codigo que devuelve un resultado
            kotlin.run {
                for (i: Int in 0 until 9) {
                    Log.d(TAG, "run: $i")

                    if (jobCanceled) {
                        return@run
                    }

                    Thread.sleep(TIME_SLEEP_MILLISECONDS)
                }
                Log.d(TAG, "Job finish")
                jobFinished(p0, false)
            }
        }.start()
    }

    override fun onStartJob(p0: JobParameters?): Boolean {
        Log.d(TAG, "Job Started")
        doBackgroundWork(p0)
        return true
    }

    override fun onStopJob(p0: JobParameters?): Boolean {
        Log.d(TAG, "Job canceled before completion")
        jobCanceled = true
        return true
    }
}