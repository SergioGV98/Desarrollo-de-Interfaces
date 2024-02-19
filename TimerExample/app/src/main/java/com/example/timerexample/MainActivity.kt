package com.example.timerexample

import android.app.AlarmManager
import android.app.PendingIntent
import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TimePicker
import java.util.Calendar

class MainActivity : AppCompatActivity() {

    private lateinit var timePicker: TimePicker

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.btnInitJob).setOnClickListener {
            if(Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP){
                initAlarmManager()
            } else {
                scheduleJob()
            }
        }
        timePicker = findViewById(R.id.timePicker)
        timePicker.setIs24HourView(true)
    }

    private fun initAlarmManager() {

        val intent = Intent("com.example.timer_intent")
        val pendingIntent = PendingIntent.getBroadcast(this, 123, intent, PendingIntent.FLAG_IMMUTABLE)

        //A recoger el momento de la alarma que se ha establecido en el TimePicker
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, timePicker.hour)
        calendar.set(Calendar.MINUTE, timePicker.minute)
        calendar.set(Calendar.SECOND, 0)
        val alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager
        alarmManager.set(AlarmManager.RTC, calendar.timeInMillis, pendingIntent)
    }

    private fun scheduleJob() {
        //1. Recoger la instancia del JobScheduler (Servicio del sistema)
        val jobScheduler = getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler

        //2. ComponentName: definir el trabajo (servicio) que se quiere ejecutar
        val componentName = ComponentName (this, TimerJobService::class.java)

        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, timePicker.hour)
        calendar.set(Calendar.MINUTE, timePicker.minute)
        calendar.set(Calendar.SECOND, 0)

        val now = Calendar.getInstance()
        now.timeInMillis = calendar.timeInMillis-now.timeInMillis

        val jobInfo = JobInfo.Builder(JOB_ID, componentName)
            .setMinimumLatency(now.timeInMillis)
            .setOverrideDeadline(now.timeInMillis)
            .build()
        jobScheduler.schedule(jobInfo)

    }

    companion object {
        const val JOB_ID  = 123
        const val TAG = "JobSchedulerExample"
    }
}