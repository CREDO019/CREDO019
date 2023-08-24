package com.facebook.react.modules.permissions;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Process;
import android.util.SparseArray;
import com.facebook.fbreact.specs.NativePermissionsAndroidSpec;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.modules.core.PermissionAwareActivity;
import com.facebook.react.modules.core.PermissionListener;
import expo.modules.interfaces.permissions.PermissionsResponse;
import java.util.ArrayList;

@ReactModule(name = PermissionsModule.NAME)
/* loaded from: classes.dex */
public class PermissionsModule extends NativePermissionsAndroidSpec implements PermissionListener {
    private static final String ERROR_INVALID_ACTIVITY = "E_INVALID_ACTIVITY";
    public static final String NAME = "PermissionsAndroid";
    private final String DENIED;
    private final String GRANTED;
    private final String NEVER_ASK_AGAIN;
    private final SparseArray<Callback> mCallbacks;
    private int mRequestCode;

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return NAME;
    }

    public PermissionsModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        this.mRequestCode = 0;
        this.GRANTED = PermissionsResponse.GRANTED_KEY;
        this.DENIED = "denied";
        this.NEVER_ASK_AGAIN = "never_ask_again";
        this.mCallbacks = new SparseArray<>();
    }

    @Override // com.facebook.fbreact.specs.NativePermissionsAndroidSpec
    public void checkPermission(String str, Promise promise) {
        Context baseContext = getReactApplicationContext().getBaseContext();
        if (Build.VERSION.SDK_INT < 23) {
            promise.resolve(Boolean.valueOf(baseContext.checkPermission(str, Process.myPid(), Process.myUid()) == 0));
        } else {
            promise.resolve(Boolean.valueOf(baseContext.checkSelfPermission(str) == 0));
        }
    }

    @Override // com.facebook.fbreact.specs.NativePermissionsAndroidSpec
    public void shouldShowRequestPermissionRationale(String str, Promise promise) {
        if (Build.VERSION.SDK_INT < 23) {
            promise.resolve(false);
            return;
        }
        try {
            promise.resolve(Boolean.valueOf(getPermissionAwareActivity().shouldShowRequestPermissionRationale(str)));
        } catch (IllegalStateException e) {
            promise.reject(ERROR_INVALID_ACTIVITY, e);
        }
    }

    @Override // com.facebook.fbreact.specs.NativePermissionsAndroidSpec
    public void requestPermission(final String str, final Promise promise) {
        Context baseContext = getReactApplicationContext().getBaseContext();
        int r1 = Build.VERSION.SDK_INT;
        String str2 = PermissionsResponse.GRANTED_KEY;
        if (r1 < 23) {
            if (baseContext.checkPermission(str, Process.myPid(), Process.myUid()) != 0) {
                str2 = "denied";
            }
            promise.resolve(str2);
        } else if (baseContext.checkSelfPermission(str) == 0) {
            promise.resolve(PermissionsResponse.GRANTED_KEY);
        } else {
            try {
                PermissionAwareActivity permissionAwareActivity = getPermissionAwareActivity();
                this.mCallbacks.put(this.mRequestCode, new Callback() { // from class: com.facebook.react.modules.permissions.PermissionsModule.1
                    @Override // com.facebook.react.bridge.Callback
                    public void invoke(Object... objArr) {
                        int[] r12 = (int[]) objArr[0];
                        if (r12.length > 0 && r12[0] == 0) {
                            promise.resolve(PermissionsResponse.GRANTED_KEY);
                        } else if (((PermissionAwareActivity) objArr[1]).shouldShowRequestPermissionRationale(str)) {
                            promise.resolve("denied");
                        } else {
                            promise.resolve("never_ask_again");
                        }
                    }
                });
                permissionAwareActivity.requestPermissions(new String[]{str}, this.mRequestCode, this);
                this.mRequestCode++;
            } catch (IllegalStateException e) {
                promise.reject(ERROR_INVALID_ACTIVITY, e);
            }
        }
    }

    @Override // com.facebook.fbreact.specs.NativePermissionsAndroidSpec
    public void requestMultiplePermissions(ReadableArray readableArray, final Promise promise) {
        final WritableNativeMap writableNativeMap = new WritableNativeMap();
        final ArrayList arrayList = new ArrayList();
        Context baseContext = getReactApplicationContext().getBaseContext();
        int r5 = 0;
        for (int r4 = 0; r4 < readableArray.size(); r4++) {
            String string = readableArray.getString(r4);
            int r7 = Build.VERSION.SDK_INT;
            String str = PermissionsResponse.GRANTED_KEY;
            if (r7 < 23) {
                if (baseContext.checkPermission(string, Process.myPid(), Process.myUid()) != 0) {
                    str = "denied";
                }
                writableNativeMap.putString(string, str);
            } else if (baseContext.checkSelfPermission(string) == 0) {
                writableNativeMap.putString(string, PermissionsResponse.GRANTED_KEY);
            } else {
                arrayList.add(string);
            }
            r5++;
        }
        if (readableArray.size() == r5) {
            promise.resolve(writableNativeMap);
            return;
        }
        try {
            PermissionAwareActivity permissionAwareActivity = getPermissionAwareActivity();
            this.mCallbacks.put(this.mRequestCode, new Callback() { // from class: com.facebook.react.modules.permissions.PermissionsModule.2
                @Override // com.facebook.react.bridge.Callback
                public void invoke(Object... objArr) {
                    int[] r1 = (int[]) objArr[0];
                    PermissionAwareActivity permissionAwareActivity2 = (PermissionAwareActivity) objArr[1];
                    for (int r0 = 0; r0 < arrayList.size(); r0++) {
                        String str2 = (String) arrayList.get(r0);
                        if (r1.length > 0 && r1[r0] == 0) {
                            writableNativeMap.putString(str2, PermissionsResponse.GRANTED_KEY);
                        } else if (permissionAwareActivity2.shouldShowRequestPermissionRationale(str2)) {
                            writableNativeMap.putString(str2, "denied");
                        } else {
                            writableNativeMap.putString(str2, "never_ask_again");
                        }
                    }
                    promise.resolve(writableNativeMap);
                }
            });
            permissionAwareActivity.requestPermissions((String[]) arrayList.toArray(new String[0]), this.mRequestCode, this);
            this.mRequestCode++;
        } catch (IllegalStateException e) {
            promise.reject(ERROR_INVALID_ACTIVITY, e);
        }
    }

    @Override // com.facebook.react.modules.core.PermissionListener
    public boolean onRequestPermissionsResult(int r4, String[] strArr, int[] r6) {
        this.mCallbacks.get(r4).invoke(r6, getPermissionAwareActivity());
        this.mCallbacks.remove(r4);
        return this.mCallbacks.size() == 0;
    }

    private PermissionAwareActivity getPermissionAwareActivity() {
        Activity currentActivity = getCurrentActivity();
        if (currentActivity == null) {
            throw new IllegalStateException("Tried to use permissions API while not attached to an Activity.");
        }
        if (!(currentActivity instanceof PermissionAwareActivity)) {
            throw new IllegalStateException("Tried to use permissions API but the host Activity doesn't implement PermissionAwareActivity.");
        }
        return (PermissionAwareActivity) currentActivity;
    }
}
