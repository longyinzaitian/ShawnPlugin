package com.shawn.plugin.main

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.shawn.plugin.common.ui.ToastUtil
import com.shawn.plugin.lib.core.PluginCore
import com.shawn.plugin.lib.hook.HookPackageManager
import com.shawn.plugin.lib.hook.core.HookCoreActivityThread
import com.shawn.plugin.lib.util.FileUtil
import com.shawn.plugin.main.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(newBase)
        FileUtil.extractAssets(MainActivity@ this, "plugin1.apk")

        ToastUtil.showMsg(MainActivity@ this, "Asset Finish")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

//        binding.fab.setOnClickListener {
//            ThreadManager.getInstance().postOnUi {
//                FileUtil.extractAssets(MainActivity@ this, "plugin1.apk")
//                ThreadManager.getInstance().postOnUi {
//                    HookPackageManager.hookLoadedApkInActivityThread(getFileStreamPath("plugin1.apk"))
////                    PluginCore.init(this)
//                    ToastUtil.showMsg(MainActivity@ this, "Asset Finish")
//                }
//            }
//        }

        binding.include.buttonFirst.setOnClickListener {
//            startActivity(Intent(this, SecondActivity::class.java))

            HookPackageManager.hookLoadedApkInActivityThread(getFileStreamPath("plugin1.apk"))
//            PluginCore.init(this)
            try {
                val t = Intent()
                t.component = ComponentName(
                    "com.shawn.plugin.app",
                    "com.shawn.plugin.app.MainActivity"
                )
                startActivity(t)
            } catch (e: Throwable) {
                e.printStackTrace()
            }
        }
    }
}