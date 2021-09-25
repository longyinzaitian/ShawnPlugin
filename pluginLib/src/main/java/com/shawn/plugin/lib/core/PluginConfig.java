package com.shawn.plugin.lib.core;

import android.content.Context;

public class PluginConfig {
    private static Context hostApplication;

    public static void setHostApplication(Context hostApplication) {
        PluginConfig.hostApplication = hostApplication;
    }

    public static Context getHostContext() {
        return hostApplication;
    }
}
