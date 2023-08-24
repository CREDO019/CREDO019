package io.jsonwebtoken.impl.compression;

import io.jsonwebtoken.CompressionCodec;
import io.jsonwebtoken.CompressionCodecResolver;
import io.jsonwebtoken.CompressionException;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.lang.Assert;
import io.jsonwebtoken.lang.Strings;

/* loaded from: classes5.dex */
public class DefaultCompressionCodecResolver implements CompressionCodecResolver {
    @Override // io.jsonwebtoken.CompressionCodecResolver
    public CompressionCodec resolveCompressionCodec(Header header) {
        String algorithmFromHeader = getAlgorithmFromHeader(header);
        if (Strings.hasText(algorithmFromHeader)) {
            if (CompressionCodecs.DEFLATE.getAlgorithmName().equalsIgnoreCase(algorithmFromHeader)) {
                return CompressionCodecs.DEFLATE;
            }
            if (CompressionCodecs.GZIP.getAlgorithmName().equalsIgnoreCase(algorithmFromHeader)) {
                return CompressionCodecs.GZIP;
            }
            throw new CompressionException("Unsupported compression algorithm '" + algorithmFromHeader + "'");
        }
        return null;
    }

    private String getAlgorithmFromHeader(Header header) {
        Assert.notNull(header, "header cannot be null.");
        return header.getCompressionAlgorithm();
    }
}
