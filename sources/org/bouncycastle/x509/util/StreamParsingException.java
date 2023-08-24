package org.bouncycastle.x509.util;

/* loaded from: classes4.dex */
public class StreamParsingException extends Exception {

    /* renamed from: _e */
    Throwable f2540_e;

    public StreamParsingException(String str, Throwable th) {
        super(str);
        this.f2540_e = th;
    }

    @Override // java.lang.Throwable
    public Throwable getCause() {
        return this.f2540_e;
    }
}
