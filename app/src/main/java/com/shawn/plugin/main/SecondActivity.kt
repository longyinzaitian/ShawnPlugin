package com.shawn.plugin.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.shawn.plugin.main.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {

    private lateinit var activitySecondBinding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activitySecondBinding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(activitySecondBinding.root)
    }
}