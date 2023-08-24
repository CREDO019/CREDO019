package org.apache.commons.codec.binary;

import java.io.InputStream;

/* loaded from: classes5.dex */
public class Base32InputStream extends BaseNCodecInputStream {
    public Base32InputStream(InputStream inputStream) {
        this(inputStream, false);
    }

    public Base32InputStream(InputStream inputStream, boolean z) {
        super(inputStream, new Base32(false), z);
    }

    public Base32InputStream(InputStream inputStream, boolean z, int r4, byte[] bArr) {
        super(inputStream, new Base32(r4, bArr), z);
    }
}
