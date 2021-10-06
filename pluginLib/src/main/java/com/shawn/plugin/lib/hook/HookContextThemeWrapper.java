package com.shawn.plugin.lib.hook;

import android.content.Context;

import com.shawn.plugin.lib.reflect.RefInvoke;

public class HookContextThemeWrapper {

    private static final String Class_ContextThemeWrapper = "android.view.ContextThemeWrapper";

    private static final String METHOD_attachBaseContext = "attachBaseContext";

    public void attachBaseContext(Object instance, Object para) {
        RefInvoke.invokeInstanceMethod(Class_ContextThemeWrapper,
                instance, METHOD_attachBaseContext,
                new Class[]{Context.class}, new Object[]{para});
    }
}
