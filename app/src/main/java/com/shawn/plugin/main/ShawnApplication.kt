package com.shawn.plugin.main

import android.app.Application
import android.content.Context
import com.shawn.plugin.lib.core.PluginCore

class ShawnApplication: Application() {

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        PluginCore.init(base)
    }

    override fun onCreate() {
        super.onCreate()
    }
}