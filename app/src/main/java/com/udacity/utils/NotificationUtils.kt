package com.udacity.utils

import android.app.Application
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import com.udacity.ui.details.DetailActivity

private val NOTIFICATION_ID = 0
private val REQUEST_CODE = 0
private val FLAGS = 0

fun NotificationManager.sendNotification(application: Application) {
    val intent = Intent(application, DetailActivity::class.java)
    val pendingIntent = PendingIntent.getActivity(
        application,
        NOTIFICATION_ID, intent, PendingIntent.FLAG_UPDATE_CURRENT
    )
}