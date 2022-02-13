package com.shawn.plugin.lib.util

import android.content.Context
import android.content.SharedPreferences
import com.shawn.plugin.lib.core.PluginConfig

object SpUtil {
    private lateinit var sp: SharedPreferences

    fun init() {
        sp = PluginConfig.getHostContext().getSharedPreferences("PluginSp", Context.MODE_PRIVATE)
    }

    fun getPluginInstalled(pkg: String, versionCode: Long): Boolean {
        return sp.getBoolean(pkg + versionCode, false)
    }

    fun setPluginInstalled(pkg: String, versionCode: Long) {
        sp.edit().putBoolean(pkg + versionCode, true).apply()
    }


}