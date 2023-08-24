package com.google.android.gms.common.api;

import com.google.android.gms.common.api.Result;

/* compiled from: com.google.android.gms:play-services-basement@@18.1.0 */
/* renamed from: com.google.android.gms.common.api.Response */
/* loaded from: classes2.dex */
public class C2134Response<T extends Result> {
    private Result zza;

    public C2134Response() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public C2134Response(T t) {
        this.zza = t;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public T getResult() {
        return (T) this.zza;
    }

    public void setResult(T t) {
        this.zza = t;
    }
}
