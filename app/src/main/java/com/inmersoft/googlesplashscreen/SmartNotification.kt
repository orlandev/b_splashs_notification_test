package com.inmersoft.googlesplashscreen

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity

object SmartNotification {

    fun set(ctx: Context, milliSeconds: Long, channelID: String, channelName: String) {

        createSmartChannel(ctx, channelID, channelName)

        val intent = Intent(ctx, SmartNotificationBroadcast::class.java)
        val pendingIntent = PendingIntent.getBroadcast(ctx, 0, intent, 0)
        var smartNotificationAlarm =
            ctx.getSystemService(AppCompatActivity.ALARM_SERVICE) as AlarmManager
        val timeAtBtnClick = System.currentTimeMillis()
        smartNotificationAlarm.set(
            AlarmManager.RTC_WAKEUP,
            timeAtBtnClick + milliSeconds,
            pendingIntent
        )
    }

    private fun createSmartChannel(ctx: Context, channelID: String, channelName: String) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelID,
                channelName,
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                lightColor = Color.RED
                enableLights(true)

            }
            val manager = ctx.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(channel)
        }
    }
}