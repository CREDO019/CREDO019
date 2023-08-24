package io.jsonwebtoken.impl;

import android.util.Base64;

/* loaded from: classes5.dex */
public class AndroidBase64Codec extends AbstractTextCodec {
    @Override // io.jsonwebtoken.impl.TextCodec
    public String encode(byte[] bArr) {
        return Base64.encodeToString(bArr, 3);
    }

    @Override // io.jsonwebtoken.impl.TextCodec
    public byte[] decode(String str) {
        return Base64.decode(str, 0);
    }
}
