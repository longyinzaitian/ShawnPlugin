package com.shawn.plugin.lib.hook;

import com.shawn.plugin.lib.reflect.RefInvoke;

public class HackActivity {
    private static final String CLASS_Activity = "android.app.Activity";

    private static final String FIELD_Resources = "mResources";


    public void setResource(Object instance, Object resource) {
        RefInvoke.setFieldObject(CLASS_Activity, instance, FIELD_Resources, resource);
    }
}
