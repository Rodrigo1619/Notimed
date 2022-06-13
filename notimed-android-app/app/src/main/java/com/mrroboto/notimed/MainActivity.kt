package com.mrroboto.notimed

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.splashScreen)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}