package com.shawn.plugin.lib.proxy;

import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;

import com.shawn.plugin.lib.util.LogUtil;

public class ProxyHandlerCallback implements Handler.Callback {
    private static final String TAG = "ProxyHandlerCallback";
    private final Handler mHandler;

    public ProxyHandlerCallback(Handler handler) {
        mHandler = handler;
    }

    @Override
    public boolean handleMessage(@NonNull Message msg) {
        LogUtil.i(TAG, "handler message -> " + msg);
        if (mHandler != null) {
            mHandler.handleMessage(msg);
        }
        return true;
    }

}
