package com.shawn.plugin.lib.hook;

import com.shawn.plugin.lib.reflect.RefInvoke;

public class HookContextWrapper {

    private static final String Class_ContextWrapper = "android.content.ContextWrapper";
    private static final String FIELD_Base = "mBase";

    public void setBase(Object instance, Object base) {
        RefInvoke.setFieldObject(Class_ContextWrapper, instance, FIELD_Base, base);
    }
}
