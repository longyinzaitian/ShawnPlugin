package com.shawn.plugin.lib.manager

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.shawn.plugin.lib.core.PluginConfig
import com.shawn.plugin.lib.install.ScanRunnable
import com.shawn.plugin.lib.plugin.Plugin
import com.shawn.plugin.lib.util.LogUtil
import java.util.*
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.CopyOnWriteArrayList
import java.util.concurrent.locks.ReentrantLock

object PluginManager {
    private const val TAG = "PluginManager"

    @Volatile
    private var isConfigInit: Boolean = false

    @Volatile
    private var pluginMap: ConcurrentHashMap<String, Plugin>? = null

    private val initLock = ReentrantLock()
    private var initCondition = initLock.newCondition()

    fun init() {
        if (isConfigInit) {
            return
        }
        PluginThreadManager.post {
            initLock.lock()
            val inStream = PluginConfig.getHostContext().assets.open("plugin.json")
            val str = String(inStream.readBytes())

            val pluginList =
                Gson().fromJson<CopyOnWriteArrayList<Plugin>>(
                    str,
                    object : TypeToken<CopyOnWriteArrayList<Plugin>>() {}.type
                )

            if (PluginConfig.isDebug()) {
                LogUtil.i(TAG, "pluginList-> ${Arrays.toString(pluginList?.toArray())}")
            }

            if (pluginMap == null) {
                pluginMap = ConcurrentHashMap(8)
            }
            pluginList?.forEach {
                if (it.isEnable) {
                    it.pkgName?.let { pkg ->
                        it.initPlugin()
                        pluginMap?.put(pkg, it)
                    }
                }
            }

            isConfigInit = true
            initCondition.signalAll()
            initLock.unlock()

            ScanRunnable().run()
        }
    }

    private fun needInitWait() {
        if (!isConfigInit) {
            initLock.lock()
            initCondition.await()
            initLock.unlock()
        }
    }

    fun pluginList(): List<Plugin>? {
        needInitWait()
        if (pluginMap.isNullOrEmpty()) {
            return null
        }
        val list =  mutableListOf<Plugin>()
        list.addAll(pluginMap!!.values)
        return list
    }

    fun getPlugin(pkg: String?): Plugin? {
        needInitWait()
        if (pkg.isNullOrEmpty()) {
            return null
        }

        return pluginMap?.get(pkg)
    }
}