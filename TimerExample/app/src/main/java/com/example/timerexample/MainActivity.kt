package com.example.timerexample


import android.app.AlarmManager
import android.app.PendingIntent
import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.ComponentName
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TimePicker
import java.text.SimpleDateFormat
import java.util.Calendar

class MainActivity : AppCompatActivity() {

    private lateinit var timePicker: TimePicker
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.btnInitJob).setOnClickListener{
            if(Build.VERSION.SDK_INT<Build.VERSION_CODES.LOLLIPOP)
                initAlarmManager()
            else
                scheduleJob()
        }
        timePicker=findViewById(R.id.timePicker)
        timePicker.setIs24HourView(true)
    }
    private fun initAlarmManager(){
        Log.d(TAG, "Se inicia el componente AlarmManger")
        val intent= Intent("com.example.timer_intent")
        val pendingIntent=PendingIntent.getBroadcast(this, JOB_ID,intent,PendingIntent.FLAG_IMMUTABLE)
        //Iniciar el componente TimePicker con la hora actual
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY,timePicker.hour)
        calendar.set(Calendar.MINUTE, timePicker.minute)
        calendar.set(Calendar.SECOND, 0)
        val alarmManager=getSystemService(ALARM_SERVICE) as AlarmManager

        alarmManager.set(AlarmManager.RTC,calendar.timeInMillis, pendingIntent)
        finish() //Se finaliza la Activity
    }

    private fun scheduleJob(){
        val jobScheduler=getSystemService(JOB_SCHEDULER_SERVICE) as JobScheduler
        val componentName=ComponentName(this, TimerJobService::class.java)

        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY,timePicker.hour)
        calendar.set(Calendar.MINUTE, timePicker.minute)
        calendar.set(Calendar.SECOND, 0)

        val now=Calendar.getInstance()
        now.timeInMillis=(calendar.timeInMillis-now.timeInMillis)

        val simpleDataFormat = SimpleDateFormat("dd/MM/yyyy HH:mm:ss")
        Log.d(TAG, "Se inicia el componente ScheduleJob ${simpleDataFormat.format(now.timeInMillis)}")

        val jobInfo= JobInfo.Builder(JOB_ID, componentName)
            .setMinimumLatency(now.timeInMillis)
            .setOverrideDeadline(now.timeInMillis)
            .build()
        jobScheduler.schedule(jobInfo)
        finish() //Se finaliza la Activity
    }
    companion object{
        const val JOB_ID=234
        const val TAG = "JobSchedulerExample"
    }
}