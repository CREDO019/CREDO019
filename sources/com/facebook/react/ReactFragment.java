package com.facebook.react;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import com.facebook.react.modules.core.PermissionAwareActivity;
import com.facebook.react.modules.core.PermissionListener;

/* loaded from: classes.dex */
public class ReactFragment extends Fragment implements PermissionAwareActivity {
    protected static final String ARG_COMPONENT_NAME = "arg_component_name";
    protected static final String ARG_LAUNCH_OPTIONS = "arg_launch_options";
    private PermissionListener mPermissionListener;
    private ReactDelegate mReactDelegate;

    /* JADX INFO: Access modifiers changed from: private */
    public static ReactFragment newInstance(String str, Bundle bundle) {
        ReactFragment reactFragment = new ReactFragment();
        Bundle bundle2 = new Bundle();
        bundle2.putString(ARG_COMPONENT_NAME, str);
        bundle2.putBundle(ARG_LAUNCH_OPTIONS, bundle);
        reactFragment.setArguments(bundle2);
        return reactFragment;
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        Bundle bundle2;
        super.onCreate(bundle);
        String str = null;
        if (getArguments() != null) {
            str = getArguments().getString(ARG_COMPONENT_NAME);
            bundle2 = getArguments().getBundle(ARG_LAUNCH_OPTIONS);
        } else {
            bundle2 = null;
        }
        if (str == null) {
            throw new IllegalStateException("Cannot loadApp if component name is null");
        }
        this.mReactDelegate = new ReactDelegate(getActivity(), getReactNativeHost(), str, bundle2);
    }

    protected ReactNativeHost getReactNativeHost() {
        return ((ReactApplication) getActivity().getApplication()).getReactNativeHost();
    }

    protected ReactDelegate getReactDelegate() {
        return this.mReactDelegate;
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.mReactDelegate.loadApp();
        return this.mReactDelegate.getReactRootView();
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        this.mReactDelegate.onHostResume();
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        this.mReactDelegate.onHostPause();
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        this.mReactDelegate.onHostDestroy();
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityResult(int r3, int r4, Intent intent) {
        super.onActivityResult(r3, r4, intent);
        this.mReactDelegate.onActivityResult(r3, r4, intent, false);
    }

    public boolean onBackPressed() {
        return this.mReactDelegate.onBackPressed();
    }

    public boolean onKeyUp(int r2, KeyEvent keyEvent) {
        return this.mReactDelegate.shouldShowDevMenuOrReload(r2, keyEvent);
    }

    @Override // androidx.fragment.app.Fragment
    public void onRequestPermissionsResult(int r2, String[] strArr, int[] r4) {
        super.onRequestPermissionsResult(r2, strArr, r4);
        PermissionListener permissionListener = this.mPermissionListener;
        if (permissionListener == null || !permissionListener.onRequestPermissionsResult(r2, strArr, r4)) {
            return;
        }
        this.mPermissionListener = null;
    }

    @Override // com.facebook.react.modules.core.PermissionAwareActivity
    public int checkPermission(String str, int r3, int r4) {
        return getActivity().checkPermission(str, r3, r4);
    }

    @Override // com.facebook.react.modules.core.PermissionAwareActivity
    public int checkSelfPermission(String str) {
        return getActivity().checkSelfPermission(str);
    }

    @Override // com.facebook.react.modules.core.PermissionAwareActivity
    public void requestPermissions(String[] strArr, int r2, PermissionListener permissionListener) {
        this.mPermissionListener = permissionListener;
        requestPermissions(strArr, r2);
    }

    /* loaded from: classes.dex */
    public static class Builder {
        String mComponentName = null;
        Bundle mLaunchOptions = null;

        public Builder setComponentName(String str) {
            this.mComponentName = str;
            return this;
        }

        public Builder setLaunchOptions(Bundle bundle) {
            this.mLaunchOptions = bundle;
            return this;
        }

        public ReactFragment build() {
            return ReactFragment.newInstance(this.mComponentName, this.mLaunchOptions);
        }
    }
}
