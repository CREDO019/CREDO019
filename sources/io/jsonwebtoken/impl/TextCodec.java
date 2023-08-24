package io.jsonwebtoken.impl;

/* loaded from: classes5.dex */
public interface TextCodec {
    public static final TextCodec BASE64 = new DefaultTextCodecFactory().getTextCodec();
    public static final TextCodec BASE64URL = new Base64UrlCodec();

    byte[] decode(String str);

    String decodeToString(String str);

    String encode(String str);

    String encode(byte[] bArr);
}
