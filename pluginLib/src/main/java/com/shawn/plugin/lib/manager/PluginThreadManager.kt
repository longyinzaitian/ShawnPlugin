package com.shawn.plugin.lib.manager

import java.util.concurrent.Executors
import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.ThreadFactory
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit

object PluginThreadManager {

    private val singleThreadExecute = Executors.newSingleThreadExecutor()

    private val pluginThreadExecute: ThreadPoolExecutor = ThreadPoolExecutor(
        Runtime.getRuntime().availableProcessors(),
        Runtime.getRuntime().availableProcessors() * 2 + 1,
        0,
        TimeUnit.MILLISECONDS,
        LinkedBlockingQueue(128),
        ThreadFactory {
            Thread(it, "PluginThreadManager")
        })

    fun postSingle(runnable: Runnable) {
        singleThreadExecute.execute(runnable)
    }

    fun post(runnable: Runnable) {
        pluginThreadExecute.execute(runnable)
    }
}