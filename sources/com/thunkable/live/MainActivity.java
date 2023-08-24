package com.thunkable.live;

import android.os.Bundle;
import com.facebook.react.ReactActivity;
import com.facebook.react.ReactActivityDelegate;
import com.facebook.react.ReactRootView;
import expo.modules.ReactActivityDelegateWrapper;

/* loaded from: classes4.dex */
public class MainActivity extends ReactActivity {
    @Override // com.facebook.react.ReactActivity
    protected String getMainComponentName() {
        return "main";
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.facebook.react.ReactActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        setTheme(com.gmail.sehiteminguner223.eneryacopy1.R.style.AppTheme);
        super.onCreate(bundle);
    }

    @Override // com.facebook.react.ReactActivity
    protected ReactActivityDelegate createReactActivityDelegate() {
        return new ReactActivityDelegateWrapper(this, new MainActivityDelegate(this, getMainComponentName()));
    }

    /* loaded from: classes4.dex */
    public static class MainActivityDelegate extends ReactActivityDelegate {
        @Override // com.facebook.react.ReactActivityDelegate
        protected boolean isConcurrentRootEnabled() {
            return false;
        }

        public MainActivityDelegate(ReactActivity reactActivity, String str) {
            super(reactActivity, str);
        }

        @Override // com.facebook.react.ReactActivityDelegate
        protected ReactRootView createRootView() {
            ReactRootView reactRootView = new ReactRootView(getContext());
            reactRootView.setIsFabric(false);
            return reactRootView;
        }
    }
}
