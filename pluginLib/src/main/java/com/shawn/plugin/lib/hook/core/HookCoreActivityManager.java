package com.shawn.plugin.lib.hook.core;

import android.annotation.SuppressLint;

import com.shawn.plugin.lib.hook.HookActivityManager;
import com.shawn.plugin.lib.hook.HookActivityManagerNative;
import com.shawn.plugin.lib.proxy.ProxyIActivityManager;
import com.shawn.plugin.lib.util.LogUtil;

import java.lang.reflect.Proxy;

public class HookCoreActivityManager {
    private static final String TAG = "HookCoreActivityManager";
    public void hook() {
        try {
            HookActivityManagerNative hookActivityManagerNative = new HookActivityManagerNative();
            Object iActivityManager = hookActivityManagerNative.getDefault();

            @SuppressLint("PrivateApi") Class<?> classB2Interface = Class.forName("android.app.IActivityManager");

            Object proxy = Proxy.newProxyInstance(getClass().getClassLoader(), new Class[]{classB2Interface}, new ProxyIActivityManager(iActivityManager));

            HookActivityManager hookActivityManager = new HookActivityManager();
            hookActivityManager.setIActivityManagerSingleton(hookActivityManager.getIActivityManagerSingleton(), proxy);

            LogUtil.i(TAG, "getDefault -> " + hookActivityManagerNative.getDefault());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
