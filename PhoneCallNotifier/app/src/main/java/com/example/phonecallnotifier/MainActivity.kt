package com.example.phonecallnotifier

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telephony.TelephonyManager
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat


class MainActivity : AppCompatActivity() {

    private val READ_PHONE_STATE_PERMISSION_REQUEST_CODE = 1001
    private val POST_NOTIFICATION_PERMISSION_REQUEST_CODE = 1002
    private val READ_PHONE_NUMBERS_PERMISSION_REQUEST_CODE = 1003

    private lateinit var telephonyManager: TelephonyManager
    private lateinit var phoneStateListener: MyPhoneStateListener

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        requestPermissions()

        telephonyManager = getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager

        phoneStateListener = MyPhoneStateListener(this)

        // Register the PhoneStateListener
        telephonyManager.registerTelephonyCallback(
            applicationContext.mainExecutor,
            phoneStateListener
        )
    }

    private fun requestPermissions() {
        // Request READ_PHONE_STATE permission
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_PHONE_STATE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this, arrayOf(Manifest.permission.READ_PHONE_STATE),
                READ_PHONE_STATE_PERMISSION_REQUEST_CODE
            )
        }

        // Request POST_NOTIFICATION permission
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this, arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                POST_NOTIFICATION_PERMISSION_REQUEST_CODE
            )
        }

        // Request READ_PHONE_NUMBERS permission
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S &&
            ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_PHONE_NUMBERS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this, arrayOf(Manifest.permission.READ_PHONE_NUMBERS),
                READ_PHONE_NUMBERS_PERMISSION_REQUEST_CODE
            )
        }
    }

    // Handle permission request results
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            READ_PHONE_STATE_PERMISSION_REQUEST_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // READ_PHONE_STATE permission granted
                } else {
                    // Handle permission denial
                }
            }

            POST_NOTIFICATION_PERMISSION_REQUEST_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // POST_NOTIFICATION permission granted
                } else {
                    // Handle permission denial
                }
            }

            READ_PHONE_NUMBERS_PERMISSION_REQUEST_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // READ_PHONE_NUMBERS permission granted
                } else {
                    // Handle permission denial
                }
            }

            else -> {
                // Handle other permission request codes if needed
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.S)
    override fun onDestroy() {
        super.onDestroy()
        telephonyManager.unregisterTelephonyCallback(phoneStateListener)
    }
}
