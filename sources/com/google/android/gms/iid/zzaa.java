package com.google.android.gms.iid;

/* loaded from: classes2.dex */
public final class zzaa extends Exception {
    private final int errorCode;

    public zzaa(int r1, String str) {
        super(str);
        this.errorCode = r1;
    }

    public final int getErrorCode() {
        return this.errorCode;
    }
}
