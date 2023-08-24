package com.onesignal;

import android.content.Context;

/* loaded from: classes3.dex */
class DelayedConsentInitializationParameters {
    private final String appId;
    private final Context context;

    /* JADX INFO: Access modifiers changed from: package-private */
    public DelayedConsentInitializationParameters(Context context, String str) {
        this.context = context;
        this.appId = str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Context getContext() {
        return this.context;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String getAppId() {
        return this.appId;
    }
}
