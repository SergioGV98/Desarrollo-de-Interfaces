package com.example.timerexample

import android.Manifest
import android.app.*
import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.TimePicker
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import java.util.*



class MainActivity : AppCompatActivity() {

    private lateinit var timePicker: TimePicker

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inicializar el timePicker y configurar el botón
        timePicker = findViewById(R.id.timePicker)
        timePicker.setIs24HourView(true)

        findViewById<Button>(R.id.btnInitJob).setOnClickListener {
            if (arePermissionsGranted()) {
                if(Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP){
                    initAlarmManager()
                } else {
                    scheduleJob()
                }
            } else {
                requestPermissions()
            }
        }
    }

    private fun arePermissionsGranted(): Boolean {
        return ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.RECEIVE_BOOT_COMPLETED
        ) == PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.WAKE_LOCK
                ) == PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.POST_NOTIFICATIONS
                ) == PackageManager.PERMISSION_GRANTED
    }

    private fun requestPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(
                    Manifest.permission.RECEIVE_BOOT_COMPLETED,
                    Manifest.permission.WAKE_LOCK,
                    Manifest.permission.POST_NOTIFICATIONS
                ),
                PERMISSION_REQUEST_CODE
            )
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults.all { it == PackageManager.PERMISSION_GRANTED }) {
                if(Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP){
                    initAlarmManager()
                } else {
                    scheduleJob()
                }
            } else {
                Toast.makeText(this, "Los permisos son necesarios para enviar notificaciones.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun initAlarmManager() {
        val intent = Intent("com.example.timer_intent")
        val pendingIntent = PendingIntent.getBroadcast(this, 123, intent, PendingIntent.FLAG_IMMUTABLE)

        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, timePicker.hour)
        calendar.set(Calendar.MINUTE, timePicker.minute)
        calendar.set(Calendar.SECOND, 0)
        val alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager
        alarmManager.set(AlarmManager.RTC, calendar.timeInMillis, pendingIntent)
    }

    private fun scheduleJob() {
        val jobScheduler = getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler
        val componentName = ComponentName(this, TimerJobService::class.java)

        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, timePicker.hour)
        calendar.set(Calendar.MINUTE, timePicker.minute)
        calendar.set(Calendar.SECOND, 0)

        val now = Calendar.getInstance()
        now.timeInMillis = calendar.timeInMillis - now.timeInMillis

        val jobInfo = JobInfo.Builder(JOB_ID, componentName)
            .setMinimumLatency(now.timeInMillis)
            .setOverrideDeadline(now.timeInMillis)
            .build()
        jobScheduler.schedule(jobInfo)
    }

    companion object {
        const val CHANNEL_ID = "timer_channel"
        const val JOB_ID  = 123
        const val TAG = "JobSchedulerExample"
        private const val PERMISSION_REQUEST_CODE = 123
    }
}
