package com.shawn.plugin.lib.hook.core;

import android.os.Handler;

import com.shawn.plugin.lib.hook.HookActivityThread;
import com.shawn.plugin.lib.hook.HookHandlerCallback;
import com.shawn.plugin.lib.proxy.ProxyHandlerCallback;

public class HookCoreActivityThread {

    public void hook() {
        try {
            HookActivityThread hookActivityThread = new HookActivityThread();
            Object activityThread = hookActivityThread.getCurrentActivityThread();

            Object mH = hookActivityThread.getH(activityThread);

            HookHandlerCallback hookHandlerCallback = new HookHandlerCallback();

            if (mH instanceof Handler) {
                ProxyHandlerCallback proxyHandlerCallback = new ProxyHandlerCallback((Handler) mH);
                hookHandlerCallback.setCallback(mH, proxyHandlerCallback);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
