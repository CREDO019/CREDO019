package com.google.android.play.core.internal;

import java.security.cert.CertificateEncodingException;
import java.security.cert.X509Certificate;

/* renamed from: com.google.android.play.core.internal.f */
/* loaded from: classes3.dex */
final class C2559f extends C2560g {

    /* renamed from: a */
    private final byte[] f863a;

    public C2559f(X509Certificate x509Certificate, byte[] bArr) {
        super(x509Certificate);
        this.f863a = bArr;
    }

    @Override // com.google.android.play.core.internal.C2560g, java.security.cert.Certificate
    public final byte[] getEncoded() throws CertificateEncodingException {
        return this.f863a;
    }
}
