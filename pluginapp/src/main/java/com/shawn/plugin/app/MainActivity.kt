package com.shawn.plugin.app

import android.app.Activity
import android.graphics.Color
import android.os.Bundle
import android.widget.TextView

class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
        val tv = TextView(this)
        tv.setText("ddddddd")
        tv.setTextSize(50F)
        tv.setTextColor(Color.BLACK)
        setContentView(tv)
    }
}