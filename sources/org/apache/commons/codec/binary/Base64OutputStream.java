package org.apache.commons.codec.binary;

import java.io.OutputStream;

/* loaded from: classes5.dex */
public class Base64OutputStream extends BaseNCodecOutputStream {
    public Base64OutputStream(OutputStream outputStream) {
        this(outputStream, true);
    }

    public Base64OutputStream(OutputStream outputStream, boolean z) {
        super(outputStream, new Base64(false), z);
    }

    public Base64OutputStream(OutputStream outputStream, boolean z, int r4, byte[] bArr) {
        super(outputStream, new Base64(r4, bArr), z);
    }
}
