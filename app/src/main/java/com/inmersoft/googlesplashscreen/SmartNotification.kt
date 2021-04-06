package com.inmersoft.googlesplashscreen

import android.app.*
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import android.widget.RemoteViews
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat

object SmartNotification {
    const val CHANNEL_ID = "SMART_NOTIFICATIONS_CHANNEL"
    const val CHANNEL_NAME = "SMART_NOTIFICATIONS"
    const val SMART_NOTIFICATION_ID = 2545

    fun set(ctx: Context, milliSeconds: Long) {

        createSmartChannel(ctx)

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

    private fun createSmartChannel(ctx: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT
            )
            val manager = ctx.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(channel)
        }
    }

    fun build(
        ctx: Context,
        contentTitle: String,
        contentText: String,
        pendingIntent: PendingIntent,
        smallIcon: Int,
        largeIcon: Int
    ): Notification? {

        val notificationLayout =
            RemoteViews("com.inmersoft.googlesplashscreen", R.layout.custom_notification)
        val notificationLayoutExpanded =
            RemoteViews("com.inmersoft.googlesplashscreen", R.layout.expanded_notification)


        /**
         * Verificamos la compativilidad de las Notificaciones
         * dependiendo de la version del sistema
         */
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            return NotificationCompat.Builder(ctx, CHANNEL_ID)
                .setContentTitle(contentTitle)
                .setContentText(contentText)
                .setBadgeIconType(NotificationCompat.BADGE_ICON_SMALL)
                .setStyle(NotificationCompat.DecoratedCustomViewStyle())
                .setCustomBigContentView(notificationLayoutExpanded)
                .setCustomContentView(notificationLayout)
                .setContentIntent(pendingIntent)
                .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                .setSmallIcon(smallIcon)
                .setLargeIcon(BitmapFactory.decodeResource(ctx.resources, largeIcon))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT).build()

        } else {
            return Notification.Builder(ctx)
                .setContentTitle(contentTitle)
                .setContentText(contentText)
                .setContentIntent(pendingIntent).build()
        }
    }

}