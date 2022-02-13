package com.shawn.plugin.lib.util;

import android.content.Context;
import android.content.res.AssetManager;

import com.shawn.plugin.lib.core.PluginConfig;

import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class FileUtil {
    private static final String TAG = "FileUtil";

    /**
     * 把Assets里面得文件复制到 /data/data/files 目录下
     */
    public static void extractAssets(Context context, String sourceName) {
        if (context == null) {
            return;
        }

        AssetManager am = context.getAssets();
        InputStream is = null;
        FileOutputStream fos = null;
        try {
            is = am.open(sourceName);
            File extractFile = context.getFileStreamPath(sourceName);
            fos = new FileOutputStream(extractFile);
            byte[] buffer = new byte[1024 * 500];
            int count;
            while ((count = is.read(buffer)) > 0) {
                fos.write(buffer, 0, count);
            }
            fos.flush();
            PluginConfig.pluginPath = extractFile.getAbsolutePath();
            LogUtil.i(TAG, "plugin path:" + PluginConfig.pluginPath);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            closeSilently(is, fos);
        }

    }

    /**
     * 获取下载目录
     */
    public static File getDownloadDirFile() {
        String downloadStr = PluginConfig.getHostContext().getFilesDir().getAbsolutePath() + File.separator + "PluginDownload";
        File downloadFile = new File(downloadStr);
        enforceDirExists(downloadFile);
        return downloadFile;
    }

    /**
     * 待加载插件经过opt优化之后存放odex得路径
     */
    public static File getPluginOptDexDir(String packageName) {
        return enforceDirExists(new File(getPluginBaseDir(packageName), "odex"));
    }

    /**
     * 插件得lib库路径, 这个demo里面没有用
     */
    public static File getPluginLibDir(String packageName) {
        return enforceDirExists(new File(getPluginBaseDir(packageName), "lib"));
    }

    private static File sBaseDir;

    // 需要加载得插件得基本目录 /data/data/<package>/files/plugin/<package>
    public static File getPluginBaseDir(String packageName) {
        if (sBaseDir == null) {
            sBaseDir = PluginConfig.getHostContext().getFileStreamPath("plugin");
            enforceDirExists(sBaseDir);
        }
        return enforceDirExists(new File(sBaseDir, packageName));
    }

    // 需要加载得插件得基本目录 /data/data/<package>/files/plugin/<package>/data/
    public static File getPluginDataDir(String packageName) {
        return enforceDirExists(new File(getPluginBaseDir(packageName), "data"));
    }

    // 需要加载得插件得基本目录 /data/data/<package>/files/plugin/<package>/version-000/
    public static File getPluginDirVersion(String packageName, long versionCode) {
        return enforceDirExists(new File(getPluginBaseDir(packageName), "version-" + versionCode));
    }

    // 需要加载得插件得基本目录 /data/data/<package>/files/plugin/<package>/version-code/apk/
    public static File getPluginSourceDir(String packageName, long versionCode) {
        return enforceDirExists(new File(getPluginDirVersion(packageName, versionCode), "apk"));
    }

    // 需要加载得插件得基本目录 /data/data/<package>/files/plugin/<package>/version-code/apk/base1.apk
    public static File getPluginSourcePath(String packageName, long versionCode) {
        File dir = enforceDirExists(new File(getPluginDirVersion(packageName, versionCode), "apk"));
        return new File(dir, "base1.apk");
    }

    // 需要加载得插件得基本目录 /data/data/<package>/files/plugin/<package>/version-code/lib/
    public static File getPluginLibDir(String packageName, long versionCode) {
        return enforceDirExists(new File(getPluginDirVersion(packageName, versionCode), "lib"));
    }

    // 需要加载得插件得基本目录 /data/data/<package>/files/plugin/<package>/version-code/dalvik-cache/
    public static File getPluginDalvikDir(String packageName, long versionCode) {
        return enforceDirExists(new File(getPluginDirVersion(packageName, versionCode), "dalvik-cache"));
    }

    private static File enforceDirExists(File sBaseDir) {
        if (!sBaseDir.exists()) {
            boolean ret = sBaseDir.mkdir();
            if (!ret) {
                throw new RuntimeException("create dir " + sBaseDir + "failed");
            }
        }
        return sBaseDir;
    }

    public static void closeSilently(Closeable... closeable) {
        if (closeable != null) {
            for (Closeable close : closeable) {
                closeSilently(close);
            }
        }
    }

    public static void closeSilently(Closeable closeable) {
        try {
            if (closeable != null) {
                closeable.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void deleteFile(File file) {
        if (file == null) {
            return;
        }
        file.deleteOnExit();
    }
}
