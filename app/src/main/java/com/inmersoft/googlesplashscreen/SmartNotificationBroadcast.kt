package com.inmersoft.googlesplashscreen

import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class SmartNotificationBroadcast : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        val notification =
            NotificationCompat.Builder(context!!, CHANNEL_ID).setContentTitle("TITULO")
                .setContentText("DESCRIPCION sdf sjkdf jkwe wef")
                .setLargeIcon(
                    BitmapFactory.decodeResource(
                        context.resources,
                        R.drawable.large_icon
                    )
                )
                .setContentIntent()
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT).build()

        val notificationManager = NotificationManagerCompat.from(context)
        notificationManager.notify(SMART_NOTIFICATION_ID, notification)
    }
}