package com.shawn.plugin.main

import android.app.Application
import android.content.Context
import com.shawn.plugin.lib.core.PluginConfig
import com.shawn.plugin.lib.core.PluginCore

class ShawnApplication : Application() {

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        getSystemService(Context.ACTIVITY_SERVICE)
        PluginConfig.setHostApplication(this)
        PluginConfig.setDebug(true)
        PluginCore.init(this)
    }

    override fun onCreate() {
        super.onCreate()
    }
}