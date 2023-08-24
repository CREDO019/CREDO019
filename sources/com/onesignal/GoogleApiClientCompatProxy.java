package com.onesignal;

import com.google.android.gms.common.api.GoogleApiClient;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public class GoogleApiClientCompatProxy {
    private final GoogleApiClient googleApiClient;
    private final Class googleApiClientListenerClass;

    /* JADX INFO: Access modifiers changed from: package-private */
    public GoogleApiClientCompatProxy(GoogleApiClient googleApiClient) {
        this.googleApiClient = googleApiClient;
        this.googleApiClientListenerClass = googleApiClient.getClass();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void connect() {
        try {
            this.googleApiClientListenerClass.getMethod("connect", new Class[0]).invoke(this.googleApiClient, new Object[0]);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void disconnect() {
        try {
            this.googleApiClientListenerClass.getMethod("disconnect", new Class[0]).invoke(this.googleApiClient, new Object[0]);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public GoogleApiClient realInstance() {
        return this.googleApiClient;
    }
}
