package com.example.jobscheluderexample

import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.ComponentName
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.btnInitJob).setOnClickListener {
            scheduleJob()
        }

        findViewById<Button>(R.id.btnCancellJob).setOnClickListener {
            cancelJob()
        }
    }

    /**
     * Inicia los parametros/condiciones que queremos que existan para la
     * ejecucion del trabajo.
     */
    private fun scheduleJob() {
        //1. Recoger la instancia del JobScheduler (Servicio del sistema)
        val jobScheduler = getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler

        //2. ComponentName: definir el trabajo (servicio) que se quiere ejecutar
        val componentName = ComponentName (this, ExampleJobService::class.java)

        val jobInfo = JobInfo.Builder(JOB_ID, componentName)
            .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
            .setRequiresDeviceIdle(false)
            .setPersisted(true)
            .setOverrideDeadline(3*1000)
            .build()

        val resultCode = jobScheduler.schedule(jobInfo)
        val isJobScheduledSuccess = resultCode == JobScheduler.RESULT_SUCCESS
        Log.d(TAG, "Job Scheduled ${if (isJobScheduledSuccess) SUCCESS else FAILED}")
    }

    private fun cancelJob() {
        //1. Recoger la instancia del JobScheduler (Servicio del sistema)
        val jobScheduler = getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler

        //2. Cancelar el trabajo con id JOB_ID
        jobScheduler.cancel(JOB_ID)
        Log.d(TAG, "Job Canceled")
    }


    companion object {
        const val JOB_ID  = 123
        const val TAG = "JobSchedulerExample"
        const val SUCCESS = "Success"
        const val FAILED = "Failed"
    }
}