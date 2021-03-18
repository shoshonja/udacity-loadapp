package com.udacity.utils

import android.app.Application
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import androidx.core.app.NotificationCompat
import com.udacity.R
import com.udacity.ui.details.DetailActivity
import com.udacity.ui.main.MainActivity

private val NOTIFICATION_ID = 0
private val REQUEST_CODE = 0
private val FLAGS = 0

fun NotificationManager.sendNotification(application: Application) {
    val intent = Intent(application, DetailActivity::class.java)
    val pendingIntent = PendingIntent.getActivity(
        application,
        NOTIFICATION_ID, intent, PendingIntent.FLAG_UPDATE_CURRENT
    )

    val builder =
        NotificationCompat.Builder(application, application.getString(R.string.channel_id))
            .setContentTitle(application.getString(R.string.notification_title))
            .setContentText(application.getString(R.string.notification_description))
            .setSmallIcon(R.drawable.ic_assistant_black_24dp)
            .setAutoCancel(true)
            .setContentIntent(pendingIntent)
//            .addAction(
//                R.mipmap.ic_launcher_round,
//                application.getString(R.string.notification_button),
//                pendingIntent
//            )

    notify(NOTIFICATION_ID, builder.build())
}