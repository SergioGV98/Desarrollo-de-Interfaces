package com.hanmajid.android.tiramisu.notificationruntimepermission

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.navigation.NavDeepLinkBuilder
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.hanmajid.android.tiramisu.notificationruntimepermission.databinding.Fragment1Binding

@RequiresApi(Build.VERSION_CODES.O)
class Fragment1 : Fragment() {
    private lateinit var requestPermissionLauncher: ActivityResultLauncher<String>
    private var _binding: Fragment1Binding? = null
    private val binding get() = _binding!!
    private val notificationManager: NotificationManager by lazy {
        requireContext().getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = Fragment1Binding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.ftbNextFragment.setOnClickListener {
            findNavController().navigate(R.id.action_fragment1_to_fragment2)
        }

        // Sets up permissions request launcher.
        requestPermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) {
            refreshUI()
            if (it) {
                showDummyNotification()
            } else {
                Snackbar.make(
                    binding.root,
                    "Please grant Notification permission from App Settings",
                    Snackbar.LENGTH_LONG
                ).show()
            }
        }

        // Sets up notification channel.
        createNotificationChannel()

        binding.buttonShowNotification.setOnClickListener {
            if (ContextCompat.checkSelfPermission(
                    requireContext(),
                    Manifest.permission.POST_NOTIFICATIONS,
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                showDummyNotification()
            } else {
                requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
            }
        }

        // Refresh UI.
        refreshUI()

    }


    /**
     * Refresh UI elements.
     */
    private fun refreshUI() {
        binding.textNotificationEnabled.text = if (notificationManager.areNotificationsEnabled()) "TRUE" else "FALSE"
    }

    /**
     * Creates Notification Channel (required for API level >= 26) before sending any notification.
     */
    private fun createNotificationChannel() {
        val channel = NotificationChannel(
            CHANNEL_ID,
            "Important Notification Channel",
            NotificationManager.IMPORTANCE_HIGH,
        ).apply {
            description = "This notification contains important announcement, etc."
        }
        notificationManager.createNotificationChannel(channel)
    }

    /**
     * Shows a notification to user.
     *
     * The notification won't appear if the user doesn't grant notification permission first.
     */
    private fun showDummyNotification() {

        /*
        val intent = Intent(requireContext(), MainActivity::class.java)
        val taskStackBuilder: TaskStackBuilder = TaskStackBuilder.create(requireActivity())
        taskStackBuilder.addNextIntentWithParentStack(intent)
        val pendingIntent: PendingIntent =
            taskStackBuilder.getPendingIntent(0, PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT)
        */
        val pendingIntent = NavDeepLinkBuilder(requireContext())
            .setComponentName(MainActivity::class.java)
            .setGraph(R.navigation.nav_graph)
            .setDestination(R.id.fragment2)
            .createPendingIntent()

        val builder = NotificationCompat.Builder(requireContext(), MainActivity.CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("Congratulations!")
            .setContentText("You have post a notification to Android 13!!!")
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)

        with(NotificationManagerCompat.from(requireContext())) {
            notify(1, builder.build())
        }
    }

    companion object {
        const val CHANNEL_ID = "dummy_channel"
    }

}