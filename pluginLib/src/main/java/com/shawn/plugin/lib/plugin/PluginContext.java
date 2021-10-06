package com.shawn.plugin.lib.plugin;

import android.content.BroadcastReceiver;
import android.content.ComponentCallbacks;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentSender;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.UserHandle;
import android.view.Display;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.shawn.plugin.lib.util.LogUtil;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.Executor;

public class PluginContext extends ContextWrapper {
    private static final String TAG = "PluginContext";

    private final Resources mResources;

    public PluginContext(Context base, Resources resources) {
        super(base);
        mResources = resources;
        LogUtil.i(TAG, "PluginContext ->");
    }

    @Override
    protected void attachBaseContext(Context base) {
        LogUtil.i(TAG, "attachBaseContext");
        super.attachBaseContext(base);
    }

    @Override
    public Context getBaseContext() {
        LogUtil.i(TAG, "getBaseContext");
        return super.getBaseContext();
    }

    @Override
    public AssetManager getAssets() {
        LogUtil.i(TAG, "getAssets");
        return mResources.getAssets();
    }

    @Override
    public Resources getResources() {
        LogUtil.i(TAG, "getResources");
        return mResources;
    }

    @Override
    public PackageManager getPackageManager() {
        LogUtil.i(TAG, "getPackageManager");
        return super.getPackageManager();
    }

    @Override
    public ContentResolver getContentResolver() {
        LogUtil.i(TAG, "getContentResolver");
        return super.getContentResolver();
    }

    @Override
    public Looper getMainLooper() {
        LogUtil.i(TAG, "getMainLooper");
        return super.getMainLooper();
    }

    @Override
    public Executor getMainExecutor() {
        LogUtil.i(TAG, "getMainExecutor");
        return super.getMainExecutor();
    }

    @Override
    public Context getApplicationContext() {
        LogUtil.i(TAG, "getApplicationContext");
        return super.getApplicationContext();
    }

    @Override
    public void setTheme(int resid) {
        LogUtil.i(TAG, "setTheme");
        super.setTheme(resid);
    }

    @Override
    public Resources.Theme getTheme() {
        LogUtil.i(TAG, "getTheme");
        return super.getTheme();
    }

    @Override
    public ClassLoader getClassLoader() {
        LogUtil.i(TAG, "getClassLoader");
        return super.getClassLoader();
    }

    @Override
    public String getPackageName() {
        LogUtil.i(TAG, "getPackageName");
        return "com.shawn.plugin.app";
    }

    @Override
    public String getOpPackageName() {
        LogUtil.i(TAG, "getOpPackageName");
        return "com.shawn.plugin.app";
    }

    @Nullable
    @Override
    public String getAttributionTag() {
        LogUtil.i(TAG, "getAttributionTag");
        return super.getAttributionTag();
    }

    @Override
    public ApplicationInfo getApplicationInfo() {
        LogUtil.i(TAG, "getApplicationInfo");
        return super.getApplicationInfo();
    }

    @Override
    public String getPackageResourcePath() {
        LogUtil.i(TAG, "getPackageResourcePath");
        return super.getPackageResourcePath();
    }

    @Override
    public String getPackageCodePath() {
        LogUtil.i(TAG, "getPackageCodePath");
        return super.getPackageCodePath();
    }

    @Override
    public SharedPreferences getSharedPreferences(String name, int mode) {
        LogUtil.i(TAG, "getSharedPreferences");
        return super.getSharedPreferences(name, mode);
    }

    @Override
    public boolean moveSharedPreferencesFrom(Context sourceContext, String name) {
        LogUtil.i(TAG, "moveSharedPreferencesFrom");
        return super.moveSharedPreferencesFrom(sourceContext, name);
    }

    @Override
    public boolean deleteSharedPreferences(String name) {
        LogUtil.i(TAG, "deleteSharedPreferences");
        return super.deleteSharedPreferences(name);
    }

    @Override
    public FileInputStream openFileInput(String name) throws FileNotFoundException {
        LogUtil.i(TAG, "openFileInput");
        return super.openFileInput(name);
    }

    @Override
    public FileOutputStream openFileOutput(String name, int mode) throws FileNotFoundException {
        LogUtil.i(TAG, "openFileOutput");
        return super.openFileOutput(name, mode);
    }

    @Override
    public boolean deleteFile(String name) {
        LogUtil.i(TAG, "deleteFile");
        return super.deleteFile(name);
    }

    @Override
    public File getFileStreamPath(String name) {
        LogUtil.i(TAG, "getFileStreamPath");
        return super.getFileStreamPath(name);
    }

    @Override
    public String[] fileList() {
        LogUtil.i(TAG, "fileList");
        return super.fileList();
    }

    @Override
    public File getDataDir() {
        LogUtil.i(TAG, "getDataDir");
        return super.getDataDir();
    }

    @Override
    public File getFilesDir() {
        LogUtil.i(TAG, "getFilesDir");
        return super.getFilesDir();
    }

    @Override
    public File getNoBackupFilesDir() {
        LogUtil.i(TAG, "getNoBackupFilesDir");
        return super.getNoBackupFilesDir();
    }

    @Override
    public File getExternalFilesDir(String type) {
        LogUtil.i(TAG, "getExternalFilesDir");
        return super.getExternalFilesDir(type);
    }

    @Override
    public File[] getExternalFilesDirs(String type) {
        LogUtil.i(TAG, "getExternalFilesDirs");
        return super.getExternalFilesDirs(type);
    }

    @Override
    public File getObbDir() {
        LogUtil.i(TAG, "getObbDir");
        return super.getObbDir();
    }

    @Override
    public File[] getObbDirs() {
        LogUtil.i(TAG, "getObbDirs");
        return super.getObbDirs();
    }

    @Override
    public File getCacheDir() {
        LogUtil.i(TAG, "getCacheDir");
        return super.getCacheDir();
    }

    @Override
    public File getCodeCacheDir() {
        LogUtil.i(TAG, "getCodeCacheDir");
        return super.getCodeCacheDir();
    }

    @Override
    public File getExternalCacheDir() {
        LogUtil.i(TAG, "getExternalCacheDir");
        return super.getExternalCacheDir();
    }

    @Override
    public File[] getExternalCacheDirs() {
        LogUtil.i(TAG, "getExternalCacheDirs");
        return super.getExternalCacheDirs();
    }

    @Override
    public File[] getExternalMediaDirs() {
        LogUtil.i(TAG, "getExternalMediaDirs");
        return super.getExternalMediaDirs();
    }

    @Override
    public File getDir(String name, int mode) {
        LogUtil.i(TAG, "getDir");
        return super.getDir(name, mode);
    }

    @Override
    public SQLiteDatabase openOrCreateDatabase(String name, int mode, SQLiteDatabase.CursorFactory factory) {
        LogUtil.i(TAG, "openOrCreateDatabase");
        return super.openOrCreateDatabase(name, mode, factory);
    }

    @Override
    public SQLiteDatabase openOrCreateDatabase(String name, int mode, SQLiteDatabase.CursorFactory factory, DatabaseErrorHandler errorHandler) {
        LogUtil.i(TAG, "openOrCreateDatabase");
        return super.openOrCreateDatabase(name, mode, factory, errorHandler);
    }

    @Override
    public boolean moveDatabaseFrom(Context sourceContext, String name) {
        LogUtil.i(TAG, "moveDatabaseFrom");
        return super.moveDatabaseFrom(sourceContext, name);
    }

    @Override
    public boolean deleteDatabase(String name) {
        LogUtil.i(TAG, "deleteDatabase");
        return super.deleteDatabase(name);
    }

    @Override
    public File getDatabasePath(String name) {
        LogUtil.i(TAG, "getDatabasePath");
        return super.getDatabasePath(name);
    }

    @Override
    public String[] databaseList() {
        LogUtil.i(TAG, "databaseList");
        return super.databaseList();
    }

    @Override
    public Drawable getWallpaper() {
        LogUtil.i(TAG, "getWallpaper");
        return super.getWallpaper();
    }

    @Override
    public Drawable peekWallpaper() {
        LogUtil.i(TAG, "peekWallpaper");
        return super.peekWallpaper();
    }

    @Override
    public int getWallpaperDesiredMinimumWidth() {
        LogUtil.i(TAG, "getWallpaperDesiredMinimumWidth");
        return super.getWallpaperDesiredMinimumWidth();
    }

    @Override
    public int getWallpaperDesiredMinimumHeight() {
        LogUtil.i(TAG, "getWallpaperDesiredMinimumHeight");
        return super.getWallpaperDesiredMinimumHeight();
    }

    @Override
    public void setWallpaper(Bitmap bitmap) throws IOException {
        LogUtil.i(TAG, "setWallpaper");
        super.setWallpaper(bitmap);
    }

    @Override
    public void setWallpaper(InputStream data) throws IOException {
        LogUtil.i(TAG, "setWallpaper");
        super.setWallpaper(data);
    }

    @Override
    public void clearWallpaper() throws IOException {
        LogUtil.i(TAG, "clearWallpaper");
        super.clearWallpaper();
    }

    @Override
    public void startActivity(Intent intent) {
        LogUtil.i(TAG, "startActivity");
        super.startActivity(intent);
    }

    @Override
    public void startActivity(Intent intent, Bundle options) {
        LogUtil.i(TAG, "startActivity");
        super.startActivity(intent, options);
    }

    @Override
    public void startActivities(Intent[] intents) {
        LogUtil.i(TAG, "startActivities");
        super.startActivities(intents);
    }

    @Override
    public void startActivities(Intent[] intents, Bundle options) {
        LogUtil.i(TAG, "startActivities");
        super.startActivities(intents, options);
    }

    @Override
    public void startIntentSender(IntentSender intent, Intent fillInIntent, int flagsMask, int flagsValues, int extraFlags) throws IntentSender.SendIntentException {
        LogUtil.i(TAG, "startIntentSender");
        super.startIntentSender(intent, fillInIntent, flagsMask, flagsValues, extraFlags);
    }

    @Override
    public void startIntentSender(IntentSender intent, Intent fillInIntent, int flagsMask, int flagsValues, int extraFlags, Bundle options) throws IntentSender.SendIntentException {
        LogUtil.i(TAG, "startIntentSender");
        super.startIntentSender(intent, fillInIntent, flagsMask, flagsValues, extraFlags, options);
    }

    @Override
    public void sendBroadcast(Intent intent) {
        LogUtil.i(TAG, "sendBroadcast");
        super.sendBroadcast(intent);
    }

    @Override
    public void sendBroadcast(Intent intent, String receiverPermission) {
        LogUtil.i(TAG, "sendBroadcast");
        super.sendBroadcast(intent, receiverPermission);
    }

    @Override
    public void sendOrderedBroadcast(Intent intent, String receiverPermission) {
        LogUtil.i(TAG, "sendOrderedBroadcast");
        super.sendOrderedBroadcast(intent, receiverPermission);
    }

    @Override
    public void sendOrderedBroadcast(Intent intent, String receiverPermission, BroadcastReceiver resultReceiver, Handler scheduler, int initialCode, String initialData, Bundle initialExtras) {
        LogUtil.i(TAG, "sendOrderedBroadcast");
        super.sendOrderedBroadcast(intent, receiverPermission, resultReceiver, scheduler, initialCode, initialData, initialExtras);
    }

    @Override
    public void sendBroadcastAsUser(Intent intent, UserHandle user) {
        LogUtil.i(TAG, "sendBroadcastAsUser");
        super.sendBroadcastAsUser(intent, user);
    }

    @Override
    public void sendBroadcastAsUser(Intent intent, UserHandle user, String receiverPermission) {
        LogUtil.i(TAG, "sendBroadcastAsUser");
        super.sendBroadcastAsUser(intent, user, receiverPermission);
    }

    @Override
    public void sendOrderedBroadcastAsUser(Intent intent, UserHandle user, String receiverPermission, BroadcastReceiver resultReceiver, Handler scheduler, int initialCode, String initialData, Bundle initialExtras) {
        LogUtil.i(TAG, "sendOrderedBroadcastAsUser");
        super.sendOrderedBroadcastAsUser(intent, user, receiverPermission, resultReceiver, scheduler, initialCode, initialData, initialExtras);
    }

    @Override
    public void sendOrderedBroadcast(@NonNull Intent intent, @Nullable String receiverPermission, @Nullable String receiverAppOp, @Nullable BroadcastReceiver resultReceiver, @Nullable Handler scheduler, int initialCode, @Nullable String initialData, @Nullable Bundle initialExtras) {
        LogUtil.i(TAG, "sendOrderedBroadcast");
        super.sendOrderedBroadcast(intent, receiverPermission, receiverAppOp, resultReceiver, scheduler, initialCode, initialData, initialExtras);
    }

    @Override
    public void sendOrderedBroadcast(@NonNull Intent intent, int initialCode, @Nullable String receiverPermission, @Nullable String receiverAppOp, @Nullable BroadcastReceiver resultReceiver, @Nullable Handler scheduler, @Nullable String initialData, @Nullable Bundle initialExtras, @Nullable Bundle options) {
        LogUtil.i(TAG, "sendOrderedBroadcast");
        super.sendOrderedBroadcast(intent, initialCode, receiverPermission, receiverAppOp, resultReceiver, scheduler, initialData, initialExtras, options);
    }

    @Override
    public void sendStickyBroadcast(Intent intent) {
        LogUtil.i(TAG, "sendStickyBroadcast");
        super.sendStickyBroadcast(intent);
    }

    @Override
    public void sendStickyOrderedBroadcast(Intent intent, BroadcastReceiver resultReceiver, Handler scheduler, int initialCode, String initialData, Bundle initialExtras) {
        LogUtil.i(TAG, "sendStickyOrderedBroadcast");
        super.sendStickyOrderedBroadcast(intent, resultReceiver, scheduler, initialCode, initialData, initialExtras);
    }

    @Override
    public void removeStickyBroadcast(Intent intent) {
        LogUtil.i(TAG, "removeStickyBroadcast");
        super.removeStickyBroadcast(intent);
    }

    @Override
    public void sendStickyBroadcastAsUser(Intent intent, UserHandle user) {
        LogUtil.i(TAG, "sendStickyBroadcastAsUser");
        super.sendStickyBroadcastAsUser(intent, user);
    }

    @Override
    public void sendStickyOrderedBroadcastAsUser(Intent intent, UserHandle user, BroadcastReceiver resultReceiver, Handler scheduler, int initialCode, String initialData, Bundle initialExtras) {
        LogUtil.i(TAG, "sendStickyOrderedBroadcastAsUser");
        super.sendStickyOrderedBroadcastAsUser(intent, user, resultReceiver, scheduler, initialCode, initialData, initialExtras);
    }

    @Override
    public void removeStickyBroadcastAsUser(Intent intent, UserHandle user) {
        LogUtil.i(TAG, "removeStickyBroadcastAsUser");
        super.removeStickyBroadcastAsUser(intent, user);
    }

    @Override
    public Intent registerReceiver(BroadcastReceiver receiver, IntentFilter filter) {
        LogUtil.i(TAG, "registerReceiver");
        return super.registerReceiver(receiver, filter);
    }

    @Override
    public Intent registerReceiver(BroadcastReceiver receiver, IntentFilter filter, int flags) {
        LogUtil.i(TAG, "registerReceiver");
        return super.registerReceiver(receiver, filter, flags);
    }

    @Override
    public Intent registerReceiver(BroadcastReceiver receiver, IntentFilter filter, String broadcastPermission, Handler scheduler) {
        LogUtil.i(TAG, "registerReceiver");
        return super.registerReceiver(receiver, filter, broadcastPermission, scheduler);
    }

    @Override
    public Intent registerReceiver(BroadcastReceiver receiver, IntentFilter filter, String broadcastPermission, Handler scheduler, int flags) {
        LogUtil.i(TAG, "registerReceiver");
        return super.registerReceiver(receiver, filter, broadcastPermission, scheduler, flags);
    }

    @Override
    public void unregisterReceiver(BroadcastReceiver receiver) {
        LogUtil.i(TAG, "unregisterReceiver");
        super.unregisterReceiver(receiver);
    }

    @Override
    public ComponentName startService(Intent service) {
        LogUtil.i(TAG, "startService");
        return super.startService(service);
    }

    @Override
    public ComponentName startForegroundService(Intent service) {
        LogUtil.i(TAG, "startForegroundService");
        return super.startForegroundService(service);
    }

    @Override
    public boolean stopService(Intent name) {
        LogUtil.i(TAG, "stopService");
        return super.stopService(name);
    }

    @Override
    public boolean bindService(Intent service, ServiceConnection conn, int flags) {
        LogUtil.i(TAG, "bindService");
        return super.bindService(service, conn, flags);
    }

    @Override
    public boolean bindService(Intent service, int flags, Executor executor, ServiceConnection conn) {
        LogUtil.i(TAG, "bindService");
        return super.bindService(service, flags, executor, conn);
    }

    @Override
    public boolean bindIsolatedService(Intent service, int flags, String instanceName, Executor executor, ServiceConnection conn) {
        LogUtil.i(TAG, "bindIsolatedService");
        return super.bindIsolatedService(service, flags, instanceName, executor, conn);
    }

    @Override
    public boolean bindServiceAsUser(Intent service, ServiceConnection conn, int flags, UserHandle user) {
        LogUtil.i(TAG, "bindServiceAsUser");
        return super.bindServiceAsUser(service, conn, flags, user);
    }

    @Override
    public void updateServiceGroup(ServiceConnection conn, int group, int importance) {
        LogUtil.i(TAG, "updateServiceGroup");
        super.updateServiceGroup(conn, group, importance);
    }

    @Override
    public void unbindService(ServiceConnection conn) {
        LogUtil.i(TAG, "unbindService");
        super.unbindService(conn);
    }

    @Override
    public boolean startInstrumentation(ComponentName className, String profileFile, Bundle arguments) {
        LogUtil.i(TAG, "startInstrumentation");
        return super.startInstrumentation(className, profileFile, arguments);
    }

    @Override
    public Object getSystemService(String name) {
        LogUtil.i(TAG, "getSystemService");
        return super.getSystemService(name);
    }

    @Override
    public String getSystemServiceName(Class<?> serviceClass) {
        LogUtil.i(TAG, "getSystemServiceName");
        return super.getSystemServiceName(serviceClass);
    }

    @Override
    public int checkPermission(String permission, int pid, int uid) {
        LogUtil.i(TAG, "checkPermission");
        return super.checkPermission(permission, pid, uid);
    }

    @Override
    public int checkCallingPermission(String permission) {
        LogUtil.i(TAG, "checkCallingPermission");
        return super.checkCallingPermission(permission);
    }

    @Override
    public int checkCallingOrSelfPermission(String permission) {
        LogUtil.i(TAG, "checkCallingOrSelfPermission");
        return super.checkCallingOrSelfPermission(permission);
    }

    @Override
    public int checkSelfPermission(String permission) {
        LogUtil.i(TAG, "checkSelfPermission");
        return super.checkSelfPermission(permission);
    }

    @Override
    public void enforcePermission(String permission, int pid, int uid, String message) {
        LogUtil.i(TAG, "enforcePermission");
        super.enforcePermission(permission, pid, uid, message);
    }

    @Override
    public void enforceCallingPermission(String permission, String message) {
        LogUtil.i(TAG, "enforceCallingPermission");
        super.enforceCallingPermission(permission, message);
    }

    @Override
    public void enforceCallingOrSelfPermission(String permission, String message) {
        LogUtil.i(TAG, "enforceCallingOrSelfPermission");
        super.enforceCallingOrSelfPermission(permission, message);
    }

    @Override
    public void grantUriPermission(String toPackage, Uri uri, int modeFlags) {
        LogUtil.i(TAG, "grantUriPermission");
        super.grantUriPermission(toPackage, uri, modeFlags);
    }

    @Override
    public void revokeUriPermission(Uri uri, int modeFlags) {
        LogUtil.i(TAG, "revokeUriPermission");
        super.revokeUriPermission(uri, modeFlags);
    }

    @Override
    public void revokeUriPermission(String targetPackage, Uri uri, int modeFlags) {
        LogUtil.i(TAG, "revokeUriPermission");
        super.revokeUriPermission(targetPackage, uri, modeFlags);
    }

    @Override
    public int checkUriPermission(Uri uri, int pid, int uid, int modeFlags) {
        LogUtil.i(TAG, "checkUriPermission");
        return super.checkUriPermission(uri, pid, uid, modeFlags);
    }

    @Override
    public int checkCallingUriPermission(Uri uri, int modeFlags) {
        LogUtil.i(TAG, "checkCallingUriPermission");
        return super.checkCallingUriPermission(uri, modeFlags);
    }

    @Override
    public int checkCallingOrSelfUriPermission(Uri uri, int modeFlags) {
        LogUtil.i(TAG, "checkCallingOrSelfUriPermission");
        return super.checkCallingOrSelfUriPermission(uri, modeFlags);
    }

    @Override
    public int checkUriPermission(Uri uri, String readPermission, String writePermission, int pid, int uid, int modeFlags) {
        LogUtil.i(TAG, "checkUriPermission");
        return super.checkUriPermission(uri, readPermission, writePermission, pid, uid, modeFlags);
    }

    @Override
    public void enforceUriPermission(Uri uri, int pid, int uid, int modeFlags, String message) {
        LogUtil.i(TAG, "enforceUriPermission");
        super.enforceUriPermission(uri, pid, uid, modeFlags, message);
    }

    @Override
    public void enforceCallingUriPermission(Uri uri, int modeFlags, String message) {
        LogUtil.i(TAG, "enforceCallingUriPermission");
        super.enforceCallingUriPermission(uri, modeFlags, message);
    }

    @Override
    public void enforceCallingOrSelfUriPermission(Uri uri, int modeFlags, String message) {
        LogUtil.i(TAG, "enforceCallingOrSelfUriPermission");
        super.enforceCallingOrSelfUriPermission(uri, modeFlags, message);
    }

    @Override
    public void enforceUriPermission(Uri uri, String readPermission, String writePermission, int pid, int uid, int modeFlags, String message) {
        LogUtil.i(TAG, "enforceUriPermission");
        super.enforceUriPermission(uri, readPermission, writePermission, pid, uid, modeFlags, message);
    }

    @Override
    public Context createPackageContext(String packageName, int flags) throws PackageManager.NameNotFoundException {
        LogUtil.i(TAG, "createPackageContext-> pkg:" + packageName + ", flags:" + flags);
        return super.createPackageContext(packageName, flags);
    }

    @Override
    public Context createContextForSplit(String splitName) throws PackageManager.NameNotFoundException {
        LogUtil.i(TAG, "createContextForSplit");
        return super.createContextForSplit(splitName);
    }

    @Override
    public Context createConfigurationContext(Configuration overrideConfiguration) {
        LogUtil.i(TAG, "createConfigurationContext");
        return super.createConfigurationContext(overrideConfiguration);
    }

    @Override
    public Context createDisplayContext(Display display) {
        LogUtil.i(TAG, "createDisplayContext");
        return super.createDisplayContext(display);
    }

    @NonNull
    @Override
    public Context createWindowContext(int type, @Nullable Bundle options) {
        LogUtil.i(TAG, "createWindowContext");
        return super.createWindowContext(type, options);
    }

    @NonNull
    @Override
    public Context createAttributionContext(@Nullable String attributionTag) {
        LogUtil.i(TAG, "createAttributionContext");
        return super.createAttributionContext(attributionTag);
    }

    @Override
    public boolean isRestricted() {
        LogUtil.i(TAG, "isRestricted");
        return super.isRestricted();
    }

    @Nullable
    @Override
    public Display getDisplay() {
        LogUtil.i(TAG, "getDisplay");
        return super.getDisplay();
    }

    @Override
    public Context createDeviceProtectedStorageContext() {
        LogUtil.i(TAG, "createDeviceProtectedStorageContext");
        return super.createDeviceProtectedStorageContext();
    }

    @Override
    public boolean isDeviceProtectedStorage() {
        LogUtil.i(TAG, "isDeviceProtectedStorage");
        return super.isDeviceProtectedStorage();
    }

    @Override
    public void registerComponentCallbacks(ComponentCallbacks callback) {
        LogUtil.i(TAG, "registerComponentCallbacks");
        super.registerComponentCallbacks(callback);
    }

    @Override
    public void unregisterComponentCallbacks(ComponentCallbacks callback) {
        LogUtil.i(TAG, "unregisterComponentCallbacks");
        super.unregisterComponentCallbacks(callback);
    }

    @Override
    public void sendBroadcastWithMultiplePermissions(@NonNull Intent intent, @NonNull String[] receiverPermissions) {
        LogUtil.i(TAG, "sendBroadcastWithMultiplePermissions");
        super.sendBroadcastWithMultiplePermissions(intent, receiverPermissions);
    }
}
