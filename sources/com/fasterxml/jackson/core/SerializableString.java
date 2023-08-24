package com.fasterxml.jackson.core;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;

/* loaded from: classes.dex */
public interface SerializableString {
    int appendQuoted(char[] cArr, int r2);

    int appendQuotedUTF8(byte[] bArr, int r2);

    int appendUnquoted(char[] cArr, int r2);

    int appendUnquotedUTF8(byte[] bArr, int r2);

    char[] asQuotedChars();

    byte[] asQuotedUTF8();

    byte[] asUnquotedUTF8();

    int charLength();

    String getValue();

    int putQuotedUTF8(ByteBuffer byteBuffer) throws IOException;

    int putUnquotedUTF8(ByteBuffer byteBuffer) throws IOException;

    int writeQuotedUTF8(OutputStream outputStream) throws IOException;

    int writeUnquotedUTF8(OutputStream outputStream) throws IOException;
}
