package com.shawn.plugin.common.thread

import android.os.Handler
import android.os.Looper
import java.util.concurrent.*

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

    private val logicThreadExecute: ThreadPoolExecutor = ThreadPoolExecutor(
        Runtime.getRuntime().availableProcessors(),
        Runtime.getRuntime().availableProcessors() * 2 + 1,
        0,
        TimeUnit.MILLISECONDS,
        LinkedBlockingQueue(128),
        ThreadFactory {
            Thread(it, "ThreadManager")
        })

    private val mHandler = Handler(Looper.getMainLooper())

    fun postOnUi(runnable: Runnable?) {
        runnable?.let {
            if (Looper.myLooper() == Looper.getMainLooper()) {
                it.run()
            } else {
                mHandler.post(it)
            }
        }
    }

    fun postThread(runnable: Runnable?) {
        runnable?.let {
            logicThreadExecute.execute(it)
        }
    }
}