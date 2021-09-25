package com.shawn.plugin.lib.hook;

import com.shawn.plugin.lib.reflect.RefInvoke;

public class HookActivityThread {
    private static final String CLASS_ACTIVITY_THREAD = "android.app.ActivityThread";

    private static final String FILED_mH = "mH";
    private static final String FILED_sCurrentActivityThread = "sCurrentActivityThread";

    public Object getCurrentActivityThread() {
        return RefInvoke.getStaticFieldObject(CLASS_ACTIVITY_THREAD, FILED_sCurrentActivityThread);
    }

    public Object getH(Object instance) {
        return RefInvoke.getFieldObject(CLASS_ACTIVITY_THREAD, instance, FILED_mH);
    }
}
