package com.inmersoft.googlesplashscreen

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_GoogleSplashScreen)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        createSmartNotificationChannel()


        var btn = findViewById<Button>(R.id.show_notification)
        btn.setOnClickListener {
            val intent = Intent(this, SmartNotificationBroadcast::class.java)
            val pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0)
            var smartNotificationAlarm = getSystemService(ALARM_SERVICE) as AlarmManager

            var timeAtBtnClick = System.currentTimeMillis()
            var showNotyTenseconds = 1000 * 10

            smartNotificationAlarm.set(
                AlarmManager.RTC_WAKEUP,
                timeAtBtnClick + showNotyTenseconds,
                pendingIntent
            )
        }
    }


    fun createSmartNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                lightColor = Color.GREEN
                enableLights(true)
            }
            val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(channel)
        }
    }
}