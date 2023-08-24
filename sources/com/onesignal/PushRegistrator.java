package com.onesignal;

import android.content.Context;

/* loaded from: classes3.dex */
public interface PushRegistrator {

    /* loaded from: classes3.dex */
    public interface RegisteredHandler {
        void complete(String str, int r2);
    }

    void registerForPush(Context context, String str, RegisteredHandler registeredHandler);
}
