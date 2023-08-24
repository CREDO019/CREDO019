package com.onesignal;

/* loaded from: classes3.dex */
public interface OneSignalApiResponseHandler {
    void onFailure(int r1, String str, Throwable th);

    void onSuccess(String str);
}
