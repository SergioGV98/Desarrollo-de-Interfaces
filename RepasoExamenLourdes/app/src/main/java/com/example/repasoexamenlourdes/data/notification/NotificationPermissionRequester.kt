package com.example.repasoexamenlourdes.data.notification

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.provider.Settings
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.example.repasoexamenlourdes.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class NotificationPermissionRequester(private val activity: ComponentActivity) {

    private lateinit var requestLauncher: ActivityResultLauncher<String>
    private lateinit var requestPermissionLauncher: ActivityResultLauncher<String>

    private  fun showSettingDialog() {
        MaterialAlertDialogBuilder(
            activity,
            R.style.Theme_RepasoExamenLourdes
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
            R.style.Theme_RepasoExamenLourdes
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

    /**
     * Funcion que solicita el permiso para notificaciones
     */
    fun tryRequest() {
        // Sets up permissions request launcher.
        requestPermissionLauncher = activity.registerForActivityResult(ActivityResultContracts.RequestPermission()) {isGranted ->
            if (!isGranted) {
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                    if(Build.VERSION.SDK_INT >= 33){
                        // Caso de uso o apartado 5a del diagrama Android Developer. Comprobar que se muestra la opcion PermissionRationale
                        if(activity.shouldShowRequestPermissionRationale(Manifest.permission.POST_NOTIFICATIONS)) //5b explicamos al usuario
                            showNotificationPermissionRationale()
                        else
                            showSettingDialog() //Caso de uso o apartado 6 del diagrama Android Developer
                    }
            }
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
        }
    }
}