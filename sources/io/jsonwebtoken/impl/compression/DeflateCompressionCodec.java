package io.jsonwebtoken.impl.compression;

import io.jsonwebtoken.lang.Objects;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.InflaterOutputStream;

/* loaded from: classes5.dex */
public class DeflateCompressionCodec extends AbstractCompressionCodec {
    private static final String DEFLATE = "DEF";

    @Override // io.jsonwebtoken.CompressionCodec
    public String getAlgorithmName() {
        return DEFLATE;
    }

    @Override // io.jsonwebtoken.impl.compression.AbstractCompressionCodec
    public byte[] doCompress(byte[] bArr) throws IOException {
        DeflaterOutputStream deflaterOutputStream;
        Deflater deflater = new Deflater(9);
        ByteArrayOutputStream byteArrayOutputStream = null;
        try {
            ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
            try {
                deflaterOutputStream = new DeflaterOutputStream((OutputStream) byteArrayOutputStream2, deflater, true);
            } catch (Throwable th) {
                th = th;
                deflaterOutputStream = null;
            }
            try {
                deflaterOutputStream.write(bArr, 0, bArr.length);
                deflaterOutputStream.flush();
                byte[] byteArray = byteArrayOutputStream2.toByteArray();
                Objects.nullSafeClose(byteArrayOutputStream2, deflaterOutputStream);
                return byteArray;
            } catch (Throwable th2) {
                th = th2;
                byteArrayOutputStream = byteArrayOutputStream2;
                Objects.nullSafeClose(byteArrayOutputStream, deflaterOutputStream);
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            deflaterOutputStream = null;
        }
    }

    @Override // io.jsonwebtoken.impl.compression.AbstractCompressionCodec
    public byte[] doDecompress(byte[] bArr) throws IOException {
        InflaterOutputStream inflaterOutputStream;
        ByteArrayOutputStream byteArrayOutputStream;
        ByteArrayOutputStream byteArrayOutputStream2 = null;
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
            try {
                inflaterOutputStream = new InflaterOutputStream(byteArrayOutputStream);
            } catch (Throwable th) {
                th = th;
                inflaterOutputStream = null;
            }
        } catch (Throwable th2) {
            th = th2;
            inflaterOutputStream = null;
        }
        try {
            inflaterOutputStream.write(bArr);
            inflaterOutputStream.flush();
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            Objects.nullSafeClose(byteArrayOutputStream, inflaterOutputStream);
            return byteArray;
        } catch (Throwable th3) {
            th = th3;
            byteArrayOutputStream2 = byteArrayOutputStream;
            Objects.nullSafeClose(byteArrayOutputStream2, inflaterOutputStream);
            throw th;
        }
    }
}
