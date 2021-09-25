package com.shawn.plugin.lib.core;

import android.content.Context;

import com.shawn.plugin.lib.hook.core.HookCoreActivityManager;
import com.shawn.plugin.lib.hook.core.HookCoreActivityThread;

public class PluginCore {

    public static void init(Context application) {
        PluginConfig.setHostApplication(application);
        new HookCoreActivityManager().hook();
        new HookCoreActivityThread().hook();
    }
}
