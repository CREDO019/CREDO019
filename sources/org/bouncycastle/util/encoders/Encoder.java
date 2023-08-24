package org.bouncycastle.util.encoders;

import java.io.IOException;
import java.io.OutputStream;

/* loaded from: classes4.dex */
public interface Encoder {
    int decode(String str, OutputStream outputStream) throws IOException;

    int decode(byte[] bArr, int r2, int r3, OutputStream outputStream) throws IOException;

    int encode(byte[] bArr, int r2, int r3, OutputStream outputStream) throws IOException;

    int getEncodedLength(int r1);

    int getMaxDecodedLength(int r1);
}
