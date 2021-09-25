package com.shawn.plugin.lib.core;

import android.content.Context;
import android.os.Build;

import com.shawn.plugin.lib.hook.core.HookCoreActivityManager;
import com.shawn.plugin.lib.hook.core.HookCoreActivityThread;
import com.shawn.plugin.lib.reflect.FreeReflection;
import com.shawn.plugin.lib.util.LogUtil;

public class PluginCore {

    public static void init(Context application) {
//        PluginConfig.setHostApplication(application);
        if (Build.VERSION.SDK_INT >= 28) {
            boolean ret = FreeReflection.exemptAll(application);
            LogUtil.i("hidden api exempt " + ret);
        }
        new HookCoreActivityManager().hook();
        new HookCoreActivityThread().hook(application);
    }
}
