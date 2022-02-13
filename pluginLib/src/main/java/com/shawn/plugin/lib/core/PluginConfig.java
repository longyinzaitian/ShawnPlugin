package com.shawn.plugin.lib.core;

import android.content.Context;

import com.shawn.plugin.lib.util.SpUtil;

public class PluginConfig {
    private static Context hostApplication;
    public static String pluginPath;
    private static boolean isDebug;

    public static void setHostApplication(Context hostApplication) {
        PluginConfig.hostApplication = hostApplication;
        SpUtil.INSTANCE.init();
    }

    public static Context getHostContext() {
        return hostApplication;
    }

    public static void setDebug(boolean isDebug) {
        PluginConfig.isDebug = isDebug;
    }

    public static boolean isDebug() {
        return isDebug;
    }
}
