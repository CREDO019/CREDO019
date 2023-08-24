package org.apache.commons.codec.binary;

import java.io.OutputStream;

/* loaded from: classes5.dex */
public class Base32OutputStream extends BaseNCodecOutputStream {
    public Base32OutputStream(OutputStream outputStream) {
        this(outputStream, true);
    }

    public Base32OutputStream(OutputStream outputStream, boolean z) {
        super(outputStream, new Base32(false), z);
    }

    public Base32OutputStream(OutputStream outputStream, boolean z, int r4, byte[] bArr) {
        super(outputStream, new Base32(r4, bArr), z);
    }
}
