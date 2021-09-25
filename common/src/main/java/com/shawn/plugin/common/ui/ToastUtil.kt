package com.shawn.plugin.common.ui

import android.content.Context
import android.widget.Toast

object ToastUtil {

    fun showMsg(context: Context?, msg: String) {
        if (context == null) {
            return
        }
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
    }
}