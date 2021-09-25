package com.shawn.plugin.lib.hook;

import com.shawn.plugin.lib.reflect.RefInvoke;

public class HookHandlerCallback {

    private static final String CLASS_Handler = "android.os.Handler";
    private static final String FILED_mCallBack = "mCallback";

    public void setCallback(Object instance, Object filed) {
        RefInvoke.setFieldObject(CLASS_Handler, instance, FILED_mCallBack, filed);
    }
}
