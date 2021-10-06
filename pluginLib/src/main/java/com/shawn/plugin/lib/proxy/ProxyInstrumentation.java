package com.shawn.plugin.lib.proxy;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.app.Fragment;
import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.os.PersistableBundle;
import android.os.UserHandle;

import com.shawn.plugin.lib.core.PluginConfig;
import com.shawn.plugin.lib.hook.HookContextThemeWrapper;
import com.shawn.plugin.lib.hook.HookContextWrapper;
import com.shawn.plugin.lib.hook.HookPackageManager;
import com.shawn.plugin.lib.loader.PluginClassLoader;
import com.shawn.plugin.lib.plugin.PluginContext;
import com.shawn.plugin.lib.reflect.RefInvoke;
import com.shawn.plugin.lib.util.LogUtil;
import com.shawn.plugin.lib.util.ProcessUtil;

public class ProxyInstrumentation extends Instrumentation {
    private static final String TAG = "ProxyInstrumentation";

    private final Instrumentation mOrigin;

    public ProxyInstrumentation(Instrumentation origin) {
        mOrigin = origin;
    }

    @Override
    public Activity newActivity(Class<?> clazz, Context context, IBinder token, Application application, Intent intent, ActivityInfo info, CharSequence title, Activity parent, String id, Object lastNonConfigurationInstance) throws IllegalAccessException, InstantiationException {
        LogUtil.i(TAG, "newActivity -> clazz:" + clazz);
        return mOrigin.newActivity(clazz, context, token, application, intent, info, title, parent, id, lastNonConfigurationInstance);
    }

    @Override
    public Activity newActivity(ClassLoader cl, String className, Intent intent) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        String originClass = intent.getStringExtra("_origin");
        LogUtil.i(TAG, "origin class:" + originClass);
        if ("1".equals(intent.getStringExtra("_origin"))) {
            intent.setPackage("com.shawn.plugin.app");
            className = "com.shawn.plugin.app.MainActivity";
            intent.setClassName("com.shawn.plugin.app", "com.shawn.plugin.app.MainActivity");
            LogUtil.i(TAG, "HookPackageManager.sLoadedApk->" + HookPackageManager.sLoadedApk.toString());
            Object loadedApk = HookPackageManager.sLoadedApk.get("com.shawn.plugin.app");
            LogUtil.i(TAG, "loadedApk:" + loadedApk);
            cl = (PluginClassLoader) RefInvoke.getFieldObject("android.app.LoadedApk", loadedApk, "mClassLoader");
            intent.setExtrasClassLoader(cl);
            return mOrigin.newActivity(cl, className, intent);
        }
        LogUtil.i(TAG, "newActivity -> cl:" + cl);
        return mOrigin.newActivity(cl, className, intent);
    }

    public ActivityResult execStartActivity(Context who, IBinder contextThread, IBinder token, Activity target,
                                            Intent intent, int requestCode, Bundle options) {

        LogUtil.i(TAG, "execStartActivity 1->");
        if (intent.getComponent().getPackageName().equals("com.shawn.plugin.app")) {
            intent.putExtra("_origin", "1");
            intent.setClassName("com.shawn.plugin.main", "com.shawn.plugin.lib.stub.StubActivity");
        }
        return (ActivityResult) RefInvoke.invokeInstanceMethod(mOrigin, "execStartActivity",
                new Class[]{Context.class, IBinder.class, IBinder.class,
                        Activity.class, Intent.class, int.class, Bundle.class},
                new Object[]{who, contextThread, token, target, intent, requestCode, options});
    }

    public ActivityResult execStartActivity(Context who, IBinder contextThread, IBinder token,
                                            Fragment target, Intent intent, int requestCode, Bundle options) {

        LogUtil.i(TAG, "execStartActivity 2->");
        return (ActivityResult) RefInvoke.invokeInstanceMethod(mOrigin, "execStartActivity",
                new Class[]{Context.class, IBinder.class, IBinder.class,
                        Fragment.class, Intent.class, int.class, Bundle.class},
                new Object[]{who, contextThread, token, target, intent, requestCode, options});
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    public ActivityResult execStartActivity(Context who, IBinder contextThread, IBinder token, Activity target,
                                            Intent intent, int requestCode, Bundle options, UserHandle user) {

        LogUtil.i(TAG, "execStartActivity 3->");
        return (ActivityResult) RefInvoke.invokeInstanceMethod(mOrigin, "execStartActivity",
                new Class[]{Context.class, IBinder.class, IBinder.class,
                        Activity.class, Intent.class, int.class, Bundle.class, UserHandle.class},
                new Object[]{who, contextThread, token, target, intent, requestCode, options, user});
    }

    @Override
    public void callActivityOnCreate(Activity activity, Bundle icicle) {
        LogUtil.i(TAG, "callActivityOnCreate act:" + activity);
        if (ProcessUtil.isPluginProcess()) {
            try {
                AssetManager assetManager = AssetManager.class.newInstance();
                RefInvoke.invokeInstanceMethod("android.content.res.AssetManager",
                        assetManager, "addAssetPath",
                        new Class[]{String.class},
                        new Object[]{activity.getFileStreamPath("plugin1.apk").getAbsolutePath()});

                Resources resources = new Resources(assetManager,
                        PluginConfig.getHostContext().getResources().getDisplayMetrics(),
                        PluginConfig.getHostContext().getResources().getConfiguration());

                PluginContext pluginContext = new PluginContext(activity.getApplicationContext(), resources);

                HookContextWrapper hookContextWrapper = new HookContextWrapper();
                hookContextWrapper.setBase(activity, null);

                HookContextThemeWrapper hookContextThemeWrapper = new HookContextThemeWrapper();
                hookContextThemeWrapper.attachBaseContext(activity, pluginContext);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        RefInvoke.invokeInstanceMethod(mOrigin, "callActivityOnCreate",
                new Class[]{Activity.class, Bundle.class},
                new Object[]{activity, icicle});
    }

    @Override
    public void callActivityOnCreate(Activity activity, Bundle icicle, PersistableBundle persistentState) {
        LogUtil.i(TAG, "callActivityOnCreate act:" + activity + ",,");
        RefInvoke.invokeInstanceMethod(mOrigin, "callActivityOnCreate",
                new Class[]{Activity.class, Bundle.class, PersistableBundle.class},
                new Object[]{activity, icicle, persistentState});
    }
}
