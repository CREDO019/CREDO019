package com.facebook.react.bridge;

import android.app.Activity;
import android.content.Intent;

/* loaded from: classes.dex */
public class BaseActivityEventListener implements ActivityEventListener {
    @Deprecated
    public void onActivityResult(int r1, int r2, Intent intent) {
    }

    @Override // com.facebook.react.bridge.ActivityEventListener
    public void onActivityResult(Activity activity, int r2, int r3, Intent intent) {
    }

    @Override // com.facebook.react.bridge.ActivityEventListener
    public void onNewIntent(Intent intent) {
    }
}
