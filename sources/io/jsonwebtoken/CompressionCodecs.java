package io.jsonwebtoken;

import io.jsonwebtoken.impl.compression.DeflateCompressionCodec;
import io.jsonwebtoken.impl.compression.GzipCompressionCodec;

/* loaded from: classes5.dex */
public final class CompressionCodecs {
    private static final CompressionCodecs INSTANCE = new CompressionCodecs();
    public static final CompressionCodec DEFLATE = new DeflateCompressionCodec();
    public static final CompressionCodec GZIP = new GzipCompressionCodec();

    private CompressionCodecs() {
    }
}
