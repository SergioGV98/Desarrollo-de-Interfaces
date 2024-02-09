package com.hanmajid.android.tiramisu.notificationruntimepermission.utils

import android.app.Activity
import android.content.Context
import android.widget.Toast

fun Activity.showToast(context: Context, message: String){
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}