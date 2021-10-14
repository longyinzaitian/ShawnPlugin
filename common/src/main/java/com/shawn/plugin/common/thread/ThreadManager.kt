package com.shawn.plugin.common.thread

import android.os.Handler
import android.os.Looper
import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.ThreadFactory
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit

class ThreadManager {

    companion object {
        private class Holder {
            companion object {
                val instance = ThreadManager()
            }
        }

        fun getInstance(): ThreadManager {
            return Holder.instance
        }
    }

    private val threadExecute: ThreadPoolExecutor = ThreadPoolExecutor(
        Runtime.getRuntime().availableProcessors(),
        Runtime.getRuntime().availableProcessors() * 2 + 1,
        0,
        TimeUnit.MILLISECONDS,
        LinkedBlockingQueue(125),
        ThreadFactory {
            Thread(it, "ThreadManager")
        })

    private val mHandler = Handler(Looper.getMainLooper())

    fun postOnUi(runnable: Runnable?) {
        runnable?.let {
            mHandler.post(it)
        }
    }

    fun postThread(runnable: Runnable?) {
        runnable?.let {
            threadExecute.execute(it)
        }
    }
}