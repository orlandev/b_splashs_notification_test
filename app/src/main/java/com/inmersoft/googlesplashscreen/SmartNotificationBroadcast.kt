package com.inmersoft.googlesplashscreen

import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationManagerCompat

class SmartNotificationBroadcast : BroadcastReceiver() {


    override fun onReceive(context: Context?, intent: Intent?) {
        val new_intent = Intent(context, MainActivity::class.java)
        val pendingIntent = TaskStackBuilder.create(context).run {
            addNextIntent(new_intent)
            getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT)
        }


        val notification = SmartNotification.build(
            context!!,
            "Titulo",
            "Descrescrescresc rescrescr sdfgsf wef wef lwkef lwkef wekf wklef escrescr sdfgsf wef wef lwkef lwkef wekf wklef escrescr sdfgsf wef wef lwkef lwkef wekf wklef escrescr sdfgsf wef wef lwkef lwkef wekf wklef escrescr sdfgsf wef wef lwkef lwkef wekf wklef escrescr sdfgsf wef wef lwkef lwkef wekf wklef escrescr sdfgsf wef wef lwkef lwkef wekf wklef   ipcion",
            pendingIntent,
            R.drawable.splash_logo,
            R.drawable.large_icon
        )

        val notificationManager = NotificationManagerCompat.from(context)
        notificationManager.notify(SmartNotification.SMART_NOTIFICATION_ID, notification!!)
    }
}