package com.shawn.plugin.lib.proxy;

import com.shawn.plugin.lib.util.LogUtil;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class ProxyIActivityManager implements InvocationHandler {
    private static final String TAG = "ProxyIActivityManager";

    private final Object mOrigin;

    public ProxyIActivityManager(Object origin) {
        mOrigin = origin;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        LogUtil.i(TAG, "invoke method -> method:" + method.getName());
        return method.invoke(mOrigin, args);
    }

}
