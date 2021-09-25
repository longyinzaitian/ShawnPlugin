package com.shawn.plugin.lib.hook;

import com.shawn.plugin.lib.reflect.RefInvoke;

public class HookActivityManagerNative {

    private static final String CLASS_ACTIVITY_MANAGER_NATIVE = "android.app.ActivityManagerNative";

    private static final String METHOD_getDefault = "getDefault";

    public Object getDefault() {
        return RefInvoke.invokeStaticMethod(CLASS_ACTIVITY_MANAGER_NATIVE, METHOD_getDefault);
    }
}
