package com.shawn.plugin.app

import android.app.Activity
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.TextView

class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
        val tv = TextView(this)
        tv.text = "ddddddd"
        tv.textSize = 50F
        tv.setTextColor(Color.BLACK)
        setContentView(tv)
        Log.e("MainActivity","com.shawn.plugin.app.MainActivity")
    }
}