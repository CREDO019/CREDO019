package org.bouncycastle.util;

/* loaded from: classes4.dex */
public class StreamParsingException extends Exception {

    /* renamed from: _e */
    Throwable f2538_e;

    public StreamParsingException(String str, Throwable th) {
        super(str);
        this.f2538_e = th;
    }

    @Override // java.lang.Throwable
    public Throwable getCause() {
        return this.f2538_e;
    }
}
