package com.shawn.plugin.lib.hook;

import com.shawn.plugin.lib.reflect.RefInvoke;

public class HookActivityManager {

    private static final String CLASS_ActivityManager = "android.app.ActivityManager";

    private static final String FILED_IActivityManagerSingleton = "IActivityManagerSingleton";

    public Object getIActivityManagerSingleton() {
        return RefInvoke.getStaticFieldObject(CLASS_ActivityManager, FILED_IActivityManagerSingleton);
    }

    public void setIActivityManagerSingleton(Object instance, Object filed) {
        new HookInstance().setInstance(instance, filed);
    }
}
