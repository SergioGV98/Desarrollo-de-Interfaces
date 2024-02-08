package com.hanmajid.android.tiramisu.notificationruntimepermission

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.provider.Settings
import androidx.activity.ComponentActivity
import androidx.activity.result.ActivityResultLauncher
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class NotificationPermissionRequester(private val activity: ComponentActivity) {

    private lateinit var requestLauncher: ActivityResultLauncher<String>

    private  fun showSettingDialog() {
        MaterialAlertDialogBuilder(
            activity,
            R.style.Theme_NotificationRuntimePermission
        )
            .setTitle("Notification Permission")
            .setMessage("Notification permission is required, Please allow notification permission from setting")
            .setPositiveButton("Ok") { _, _ ->
                val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                intent.data = Uri.parse("package:${activity.packageName}")
                activity.startActivity(intent)
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    private fun showNotificationPermissionRationale() {
        MaterialAlertDialogBuilder(
            activity,
            R.style.Theme_NotificationRuntimePermission
        )
            .setTitle("Alert")
            .setMessage("Notification permission is required, to show notification")
            .setPositiveButton("Ok") { _, _ ->
                if (Build.VERSION.SDK_INT >= 33) {
                    requestLauncher?.launch(android.Manifest.permission.POST_NOTIFICATIONS)
                }
            }
            //Si cancela en nuestro código se mostrará siempre showSettingDialog
            .setNegativeButton("Cancel", null)
            .show()
    }
}