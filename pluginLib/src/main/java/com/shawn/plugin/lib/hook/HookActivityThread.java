package com.shawn.plugin.lib.hook;

import com.shawn.plugin.lib.reflect.RefInvoke;

public class HookActivityThread {
    private static final String CLASS_ACTIVITY_THREAD = "android.app.ActivityThread";

    private static final String FILED_mH = "mH";
    private static final String FILED_sCurrentActivityThread = "sCurrentActivityThread";
    private static final String FILED_mPackages = "mPackages";
    private static final String FILED_mInstrumentation = "mInstrumentation";
    private static final String FILED_sPackageManager = "sPackageManager";

    public Object getCurrentActivityThread() {
        return RefInvoke.getStaticFieldObject(CLASS_ACTIVITY_THREAD, FILED_sCurrentActivityThread);
    }

    public Object getH(Object instance) {
        return RefInvoke.getFieldObject(CLASS_ACTIVITY_THREAD, instance, FILED_mH);
    }

    public Object getPackages(Object instance) {
        return RefInvoke.getFieldObject(CLASS_ACTIVITY_THREAD, instance, FILED_mPackages);
    }

    public Object getInstrumentation(Object instance) {
        return RefInvoke.getFieldObject(CLASS_ACTIVITY_THREAD, instance, FILED_mInstrumentation);
    }

    public void setInstrumentation(Object instance, Object filed) {
        RefInvoke.setFieldObject(CLASS_ACTIVITY_THREAD, instance, FILED_mInstrumentation, filed);
    }

    public Object getPackageManager() {
        return RefInvoke.getStaticFieldObject(CLASS_ACTIVITY_THREAD, FILED_sPackageManager);
    }

    public void setPackageManager(Object filed) {
        RefInvoke.setStaticFieldObject(CLASS_ACTIVITY_THREAD, FILED_sPackageManager, filed);
    }
}
