package com.shawn.plugin.lib.install

import com.shawn.plugin.lib.core.PluginConfig
import com.shawn.plugin.lib.manager.PluginManager
import com.shawn.plugin.lib.util.FileUtil
import com.shawn.plugin.lib.util.LogUtil

class ScanRunnable : Runnable {

    override fun run() {
        val downloadDir = FileUtil.getDownloadDirFile()
        val fileList = downloadDir.listFiles()
        if (fileList.isNullOrEmpty()) {
            return
        }

        fileList.filter {
            it.isFile && it.exists() && it.name.endsWith(".apk", ignoreCase = true)
        }.forEach { file ->
            val packageInfo = PluginConfig.getHostContext().packageManager
                .getPackageArchiveInfo(file.absolutePath, 0)
            LogUtil.i("ScanRunnable", "packageInfo-> $packageInfo")
            packageInfo?.let {
                PluginManager.getPlugin(packageInfo.packageName)?.install(file, packageInfo)
            }
        }
    }
}