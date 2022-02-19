package com.shawn.plugin.lib.plugin

import android.content.pm.PackageInfo
import android.os.Build
import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.WorkerThread
import com.google.gson.annotations.SerializedName
import com.shawn.plugin.lib.install.PluginInstall
import com.shawn.plugin.lib.util.FileUtil
import java.io.File
import java.io.Serializable
import java.util.concurrent.locks.ReentrantLock

class Plugin() : Parcelable, Serializable {

    @SerializedName("isEnable")
    @Volatile
    var isEnable: Boolean = false

    @SerializedName("pkg")
    @Volatile
    var pkgName: String? = ""

    @SerializedName("versionCode")
    @Volatile
    var versionCode: Long = 0L

    @SerializedName("minHostVersionCode")
    @Volatile
    var minHostVersionCode: Long = 0

    @SerializedName("maxHostVersionCode")
    @Volatile
    var maxHostVersionCode: Long = Long.MAX_VALUE

    @Volatile
    var isDelete: Boolean = false

    @Volatile
    var status: Int = Constant.STATUS_INIT

    @Volatile
    var installMaxVersionCode = 0L

    @Transient
    private val installLock = ReentrantLock()

    @Transient
    private val installCondition = installLock.newCondition()

    @Transient
    private val initLock = ReentrantLock()

    @Transient
    private val initCondition = installLock.newCondition()

    @Volatile
    private var isInit = false

    @Volatile
    private var mPackageDir: String? = ""

    @WorkerThread
    fun initPlugin() {
        initLock.lock()
        installMaxVersionCode = getInstalledMaxVersion()
        val valid = checkVersionValid(installMaxVersionCode)
        if (valid) {
            status = Constant.STATUS_INSTANT_SUC
        }
        isInit = true
        initCondition.signalAll()
        initLock.unlock()
    }

    private fun checkVersionValid(version: Long): Boolean {
        if (FileUtil.getPluginSourcePath(pkgName, version).exists()) {
            return true
        }
        return false
    }

    private fun needInitPluginWait() {
        if (!isInit) {
            initCondition.await()
        }
    }

    @WorkerThread
    fun install(file: File, packageInfo: PackageInfo?) {
        needInitPluginWait()
        if (packageInfo == null) {
            return
        }

        if (!file.exists()) {
            return
        }

        val pkgName = packageInfo.packageName
        val versionCode = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            packageInfo.longVersionCode
        } else {
            packageInfo.versionCode.toLong()
        }

        if (pkgName.isNullOrEmpty() || pkgName != this.pkgName) {
            return
        }
        installLock.lock()

        val valid = checkValid()
        if (valid) {
            val result = PluginInstall().install(this, file, packageInfo)
            if (result) {
                installMaxVersionCode = versionCode
                FileUtil.deleteFile(file)

                if (status != Constant.STATUS_ACTIVE) {
                    status = Constant.STATUS_INSTANT_SUC
                }
            }
        }
        installCondition.signalAll()
        installLock.unlock()

    }

    private fun checkValid(): Boolean {
        return true
    }

    private fun getInstalledMaxVersion(): Long {
        if (mPackageDir.isNullOrEmpty()) {
            mPackageDir = FileUtil.getPluginBaseDir(pkgName).absolutePath
        }

        val dirs = FileUtil.getPluginBaseDir(pkgName).listFiles { _, name ->
            name?.matches(Regex("^version-(\\d+)$")) ?: false
        }

        if (dirs == null || dirs.isEmpty()) {
            return 0L
        }
        var maxVersion = 0L
        dirs.forEach {
            val version = it.name.split("-")[1].toLong()
            if (version > maxVersion) {
                if (FileUtil.getPluginSourcePath(pkgName ?: "", version).exists()) {
                    maxVersion = version
                }
            }
        }
        return maxVersion
    }


    constructor(parcel: Parcel) : this() {
        isEnable = parcel.readByte() != 0.toByte()
        pkgName = parcel.readString()
        versionCode = parcel.readLong()
        minHostVersionCode = parcel.readLong()
        maxHostVersionCode = parcel.readLong()
        isDelete = parcel.readByte() != 0.toByte()
        status = parcel.readInt()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeByte(if (isEnable) 1 else 0)
        parcel.writeString(pkgName)
        parcel.writeLong(versionCode)
        parcel.writeLong(minHostVersionCode)
        parcel.writeLong(maxHostVersionCode)
        parcel.writeByte(if (isDelete) 1 else 0)
        parcel.writeInt(status)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Plugin> {
        override fun createFromParcel(parcel: Parcel): Plugin {
            return Plugin(parcel)
        }

        override fun newArray(size: Int): Array<Plugin?> {
            return arrayOfNulls(size)
        }
    }
}