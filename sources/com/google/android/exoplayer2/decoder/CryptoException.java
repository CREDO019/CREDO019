package com.google.android.exoplayer2.decoder;

/* loaded from: classes2.dex */
public class CryptoException extends Exception {
    public final int errorCode;

    public CryptoException(int r1, String str) {
        super(str);
        this.errorCode = r1;
    }
}