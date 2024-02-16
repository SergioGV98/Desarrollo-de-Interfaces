package com.example.broadcastreceiverexample

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.telephony.PhoneStateListener
import android.telephony.TelephonyManager
import android.widget.Toast


class PhoneReceiver: BroadcastReceiver() {

    companion object{
        private var c: Context? = null
    }
    override fun onReceive(context: Context, intent: Intent?) {
        c = context
        try {
            val tmgr = context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
            val phoneListener = MyPhoneStateListener()
            tmgr.listen(phoneListener, PhoneStateListener.LISTEN_CALL_STATE)
        } catch (e: Exception) {
            Toast.makeText(context, "oops!", Toast.LENGTH_SHORT).show()
        }
    }

    private class MyPhoneStateListener : PhoneStateListener() {
        override fun onCallStateChanged(state: Int, incomingNumber: String) {
            if (state == 1) {
                val msg = "New Phone Call Event. Incoming Number: $incomingNumber"
                val duration = Toast.LENGTH_LONG
                val toast = Toast.makeText(c, msg, duration)
                toast.show()
            }
        }
    }
}