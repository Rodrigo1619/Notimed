package com.mrroboto.notimed.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mrroboto.notimed.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.splashScreen)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}