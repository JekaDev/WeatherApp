package com.example.ebobrovnichiy.weatherapp.ui.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity

class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)

        finish()
    }
}