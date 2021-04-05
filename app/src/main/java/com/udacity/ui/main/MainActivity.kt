package com.udacity.ui.main

import android.app.DownloadManager
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import com.udacity.R
import com.udacity.utils.createLoadAppNotificationChannel
import com.udacity.utils.sendNotification
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*


class MainActivity : AppCompatActivity() {

    private var downloadID: Long = 0

    private lateinit var notificationManager: NotificationManager
    private lateinit var pendingIntent: PendingIntent
    private lateinit var action: NotificationCompat.Action

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        registerReceiver(receiver, IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE))

        val notificationManager = getSystemService(NotificationManager::class.java)

        notificationManager.createLoadAppNotificationChannel(
            getString(R.string.channel_id),
            getString(R.string.channel_name)
        )
        notificationManager.sendNotification(application)

        custom_button.setOnClickListener {
            actOnSelectedRadioOption()
        }
    }

    private val receiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            val id = intent?.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1)
        }
    }

    private fun download(radioOption: RadioOption) {
        val request =
            DownloadManager.Request(Uri.parse(URL))
                .setTitle(getString(R.string.app_name))
                .setDescription(getString(R.string.app_description))
                .setRequiresCharging(false)
                .setAllowedOverMetered(true)
                .setAllowedOverRoaming(true)

        val downloadManager = getSystemService(DOWNLOAD_SERVICE) as DownloadManager
        downloadID =
            downloadManager.enqueue(request)// enqueue puts the download request in the queue.
    }

    private fun actOnSelectedRadioOption() {
        when (main_radio_group.checkedRadioButtonId) {
            R.id.main_radio_group_glide -> download(RadioOption.GLIDE)
            R.id.main_radio_group_retrofit -> download(RadioOption.RETROFIT)
            R.id.main_radio_group_udacity -> download(RadioOption.UDACITY)
            else -> Toast.makeText(this, "Please select radio option", Toast.LENGTH_SHORT).show()
        }
    }


    companion object {
        private const val URL =
            "https://github.com/udacity/nd940-c3-advanced-android-programming-project-starter/archive/master.zip"
    }

    private enum class RadioOption {
        GLIDE,
        RETROFIT,
        UDACITY
    }

}
