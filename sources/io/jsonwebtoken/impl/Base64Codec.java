package io.jsonwebtoken.impl;

import javax.xml.bind.DatatypeConverter;

/* loaded from: classes5.dex */
public class Base64Codec extends AbstractTextCodec {
    @Override // io.jsonwebtoken.impl.TextCodec
    public String encode(byte[] bArr) {
        return DatatypeConverter.printBase64Binary(bArr);
    }

    @Override // io.jsonwebtoken.impl.TextCodec
    public byte[] decode(String str) {
        return DatatypeConverter.parseBase64Binary(str);
    }
}
