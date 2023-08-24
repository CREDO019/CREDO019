package com.facebook.jni;

/* loaded from: classes.dex */
public class CppSystemErrorException extends CppException {
    int errorCode;

    public CppSystemErrorException(String str, int r2) {
        super(str);
        this.errorCode = r2;
    }

    public int getErrorCode() {
        return this.errorCode;
    }
}
