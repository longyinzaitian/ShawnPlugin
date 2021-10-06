package com.shawn.plugin.lib.hook.core;

import android.app.Instrumentation;
import android.content.Context;
import android.os.Handler;

import com.shawn.plugin.lib.hook.HookActivityThread;
import com.shawn.plugin.lib.hook.HookHandlerCallback;
import com.shawn.plugin.lib.proxy.ProxyHandlerCallback;
import com.shawn.plugin.lib.proxy.ProxyIPackageManager;
import com.shawn.plugin.lib.proxy.ProxyInstrumentation;

import java.lang.reflect.Proxy;

public class HookCoreActivityThread {

    public void hook(Context context) {
        try {
            HookActivityThread hookActivityThread = new HookActivityThread();
            Object activityThread = hookActivityThread.getCurrentActivityThread();

            Object mH = hookActivityThread.getH(activityThread);

            HookHandlerCallback hookHandlerCallback = new HookHandlerCallback();

            if (mH instanceof Handler) {
                ProxyHandlerCallback proxyHandlerCallback = new ProxyHandlerCallback((Handler) mH);
                hookHandlerCallback.setCallback(mH, proxyHandlerCallback);
            }

            Object instrumentation = hookActivityThread.getInstrumentation(activityThread);
            if (instrumentation instanceof Instrumentation) {
                ProxyInstrumentation proxyInstrumentation = new ProxyInstrumentation((Instrumentation) instrumentation);
                hookActivityThread.setInstrumentation(activityThread, proxyInstrumentation);
            }

            Object packageManager = hookActivityThread.getPackageManager();
            hookActivityThread.setPackageManager(Proxy.newProxyInstance(getClass().getClassLoader(),
                    new Class[]{Class.forName("android.content.pm.IPackageManager")},
                    new ProxyIPackageManager(packageManager)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
