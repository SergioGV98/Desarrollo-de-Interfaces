package com.hanmajid.android.tiramisu.notificationruntimepermission

import android.Manifest
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.navigation.NavDeepLinkBuilder
import com.hanmajid.android.tiramisu.notificationruntimepermission.MainActivity.Companion.notificationPermissionRequest
import com.hanmajid.android.tiramisu.notificationruntimepermission.databinding.Fragment4Binding

@RequiresApi(Build.VERSION_CODES.O)
class Fragment4 : Fragment() {

    private var _binding: Fragment4Binding? = null
    private val binding get() = _binding!!

    private val notificationManager: NotificationManager by lazy {
        requireContext().getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = Fragment4Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.floatingActionButton.setOnClickListener {
            showNotification()
        }
    }

    /**
     * Muestra las notificaciones
     * NotificationHelper: contiene las funciones necesarias para crear una notificacion
     * NotificationPermissionRequester: contiene las funciones necesarias para solicitar los permisos
     */

    private fun showNotification() {
        //1. Comprobar si se tiene el permiso IsGranted
        //1.1 True -> Se muestra la notificacion
        //1.2 False -> Se debe solicitar de nuevo el permiso

        when (PackageManager.PERMISSION_GRANTED) {
            ContextCompat.checkSelfPermission(
                requireActivity(),
                Manifest.permission.POST_NOTIFICATIONS
            ) -> {
                refreshUI()
                val pendingIntent = NavDeepLinkBuilder(requireContext())
                    .setComponentName(MainActivity::class.java)
                    .setGraph(R.navigation.nav_graph)
                    .setDestination(R.id.fragment3)
                    .createPendingIntent()
                sendNotification(
                    requireActivity(),
                    pendingIntent,
                    "Titutlo solucion",
                    "Contenido de la solucion"
                )
            }
            else -> notificationPermissionRequest!!.tryRequest()
        }
    }

    private fun refreshUI() {
       if (notificationManager.areNotificationsEnabled()) "TRUE" else "FALSE"
    }

    private val requestPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                Log.i("Permission: ", "Granted")
            } else {
                Log.i("Permission: ", "Denied")
            }
        }

}