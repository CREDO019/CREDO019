package org.bouncycastle.util;

/* loaded from: classes4.dex */
public class StoreException extends RuntimeException {

    /* renamed from: _e */
    private Throwable f2537_e;

    public StoreException(String str, Throwable th) {
        super(str);
        this.f2537_e = th;
    }

    @Override // java.lang.Throwable
    public Throwable getCause() {
        return this.f2537_e;
    }
}
