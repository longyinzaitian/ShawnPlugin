package com.shawn.plugin.lib.util;

import android.util.Log;

public class LogUtil {

    public static void i(String tag, String... msg) {
        StringBuilder sb = new StringBuilder();
        for (String s : msg) {
            sb.append(s).append(",");
        }
        Log.i(tag, sb.toString());
    }

    public static void e(String tag, String... msg) {
        StringBuilder sb = new StringBuilder();
        for (String s : msg) {
            sb.append(s).append(",");
        }
        Log.e(tag, sb.toString());
    }
}
