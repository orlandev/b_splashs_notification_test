package com.inmersoft.googlesplashscreen

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_GoogleSplashScreen)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var btn = findViewById<Button>(R.id.show_notification)
        btn.setOnClickListener {
            SmartNotification.set(this, 1000 * 3, CHANNEL_ID, CHANNEL_NAME)
        }
    }


}