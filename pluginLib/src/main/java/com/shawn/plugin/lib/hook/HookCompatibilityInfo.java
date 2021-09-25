package com.shawn.plugin.lib.hook;

import com.shawn.plugin.lib.reflect.RefInvoke;

public class HookCompatibilityInfo {
    private static final String CLASS_CompatibilityInfo = "android.content.res.CompatibilityInfo";

    private static final String FILED_DEFAULT_COMPATIBILITY_INFO = "DEFAULT_COMPATIBILITY_INFO";

    public Object getDefaultCompatibilityInfo() {
        return RefInvoke.getStaticFieldObject(CLASS_CompatibilityInfo, FILED_DEFAULT_COMPATIBILITY_INFO);
    }
}
