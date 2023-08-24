package com.facebook.react.modules.core;

/* loaded from: classes.dex */
public interface PermissionAwareActivity {
    int checkPermission(String str, int r2, int r3);

    int checkSelfPermission(String str);

    void requestPermissions(String[] strArr, int r2, PermissionListener permissionListener);

    boolean shouldShowRequestPermissionRationale(String str);
}
