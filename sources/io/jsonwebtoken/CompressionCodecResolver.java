package io.jsonwebtoken;

/* loaded from: classes5.dex */
public interface CompressionCodecResolver {
    CompressionCodec resolveCompressionCodec(Header header) throws CompressionException;
}
