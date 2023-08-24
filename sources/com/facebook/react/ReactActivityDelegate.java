package com.facebook.react;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.KeyEvent;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.bridge.Callback;
import com.facebook.react.modules.core.PermissionListener;

/* loaded from: classes.dex */
public class ReactActivityDelegate {
    private final Activity mActivity;
    private boolean mConcurrentRootEnabled;
    private final String mMainComponentName;
    private PermissionListener mPermissionListener;
    private Callback mPermissionsCallback;
    private ReactDelegate mReactDelegate;

    protected Bundle getLaunchOptions() {
        return null;
    }

    protected boolean isConcurrentRootEnabled() {
        return false;
    }

    @Deprecated
    public ReactActivityDelegate(Activity activity, String str) {
        this.mActivity = activity;
        this.mMainComponentName = str;
    }

    public ReactActivityDelegate(ReactActivity reactActivity, String str) {
        this.mActivity = reactActivity;
        this.mMainComponentName = str;
    }

    private Bundle composeLaunchOptions() {
        Bundle launchOptions = getLaunchOptions();
        if (isConcurrentRootEnabled()) {
            if (launchOptions == null) {
                launchOptions = new Bundle();
            }
            launchOptions.putBoolean("concurrentRoot", true);
        }
        return launchOptions;
    }

    protected ReactRootView createRootView() {
        return new ReactRootView(getContext());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public ReactNativeHost getReactNativeHost() {
        return ((ReactApplication) getPlainActivity().getApplication()).getReactNativeHost();
    }

    public ReactInstanceManager getReactInstanceManager() {
        return this.mReactDelegate.getReactInstanceManager();
    }

    public String getMainComponentName() {
        return this.mMainComponentName;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        String mainComponentName = getMainComponentName();
        this.mReactDelegate = new ReactDelegate(getPlainActivity(), getReactNativeHost(), mainComponentName, composeLaunchOptions()) { // from class: com.facebook.react.ReactActivityDelegate.1
            @Override // com.facebook.react.ReactDelegate
            protected ReactRootView createRootView() {
                return ReactActivityDelegate.this.createRootView();
            }
        };
        if (mainComponentName != null) {
            loadApp(mainComponentName);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void loadApp(String str) {
        this.mReactDelegate.loadApp(str);
        getPlainActivity().setContentView(this.mReactDelegate.getReactRootView());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void onPause() {
        this.mReactDelegate.onHostPause();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void onResume() {
        this.mReactDelegate.onHostResume();
        Callback callback = this.mPermissionsCallback;
        if (callback != null) {
            callback.invoke(new Object[0]);
            this.mPermissionsCallback = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void onDestroy() {
        this.mReactDelegate.onHostDestroy();
    }

    public void onActivityResult(int r3, int r4, Intent intent) {
        this.mReactDelegate.onActivityResult(r3, r4, intent, true);
    }

    public boolean onKeyDown(int r2, KeyEvent keyEvent) {
        if (getReactNativeHost().hasInstance() && getReactNativeHost().getUseDeveloperSupport() && r2 == 90) {
            keyEvent.startTracking();
            return true;
        }
        return false;
    }

    public boolean onKeyUp(int r2, KeyEvent keyEvent) {
        return this.mReactDelegate.shouldShowDevMenuOrReload(r2, keyEvent);
    }

    public boolean onKeyLongPress(int r1, KeyEvent keyEvent) {
        if (getReactNativeHost().hasInstance() && getReactNativeHost().getUseDeveloperSupport() && r1 == 90) {
            getReactNativeHost().getReactInstanceManager().showDevOptionsDialog();
            return true;
        }
        return false;
    }

    public boolean onBackPressed() {
        return this.mReactDelegate.onBackPressed();
    }

    public boolean onNewIntent(Intent intent) {
        if (getReactNativeHost().hasInstance()) {
            getReactNativeHost().getReactInstanceManager().onNewIntent(intent);
            return true;
        }
        return false;
    }

    public void onWindowFocusChanged(boolean z) {
        if (getReactNativeHost().hasInstance()) {
            getReactNativeHost().getReactInstanceManager().onWindowFocusChange(z);
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        if (getReactNativeHost().hasInstance()) {
            getReactInstanceManager().onConfigurationChanged(getContext(), configuration);
        }
    }

    public void requestPermissions(String[] strArr, int r2, PermissionListener permissionListener) {
        this.mPermissionListener = permissionListener;
        getPlainActivity().requestPermissions(strArr, r2);
    }

    public void onRequestPermissionsResult(final int r2, final String[] strArr, final int[] r4) {
        this.mPermissionsCallback = new Callback() { // from class: com.facebook.react.ReactActivityDelegate.2
            @Override // com.facebook.react.bridge.Callback
            public void invoke(Object... objArr) {
                if (ReactActivityDelegate.this.mPermissionListener == null || !ReactActivityDelegate.this.mPermissionListener.onRequestPermissionsResult(r2, strArr, r4)) {
                    return;
                }
                ReactActivityDelegate.this.mPermissionListener = null;
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Context getContext() {
        return (Context) Assertions.assertNotNull(this.mActivity);
    }

    protected Activity getPlainActivity() {
        return (Activity) getContext();
    }
}
