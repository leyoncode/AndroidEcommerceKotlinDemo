package com.leyon.androidecommercekotlindemo.view

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.leyon.androidecommercekotlindemo.R
import com.leyon.androidecommercekotlindemo.model.repository.NotificationLogRepository
import com.leyon.androidecommercekotlindemo.model.storage.entity.NotificationLog

fun createNotificationChannel(context: Context) {
    // Create the NotificationChannel, but only on API 26+ because
    // the NotificationChannel class is new and not in the support library
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val name = context.getString(R.string.channel_name)
        val descriptionText = context.getString(R.string.channel_description)
        val CHANNEL_ID = context.getString(R.string.channel_id)
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
            description = descriptionText
        }
        // Register the channel with the system
        val notificationManager: NotificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }
}

fun sendNotification(context: Context, textTitle : String, textContent : String) {
    val CHANNEL_ID = context.getString(R.string.channel_id)
    val builder = NotificationCompat.Builder(context, CHANNEL_ID)
        .setSmallIcon(R.drawable.ic_baseline_notification_icon_24)
        .setContentTitle(textTitle)
        .setContentText(textContent)
        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
        .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
        .setStyle(NotificationCompat.BigTextStyle().bigText(textContent))
        .setAutoCancel(true)

    with(NotificationManagerCompat.from(context)) {
        // notificationId is a unique int for each notification that you must define
        notify(0, builder.build())
    }

    //log notification
    val newNotificationLog = NotificationLog(
        NotificationLogRepository.getDateTime().toString(),
        "$textTitle - $textContent"
    )

    NotificationLogRepository.addNotificationLog(newNotificationLog)
}