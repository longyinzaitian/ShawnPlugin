package com.shawn.plugin.lib.hook;

import com.shawn.plugin.lib.reflect.RefInvoke;

public class HookInstance {
    private static final String CLASS_Singleton = "android.util.Singleton";

    private static final String FILED_mInstance = "mInstance";

    public Object getInstance(Object instance) {
        return RefInvoke.getFieldObject(CLASS_Singleton, instance, FILED_mInstance);
    }

    public void setInstance(Object instance, Object filed) {
        RefInvoke.setFieldObject(CLASS_Singleton, instance, FILED_mInstance, filed);
    }
}
