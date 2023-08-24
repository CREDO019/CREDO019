package com.google.android.exoplayer2.extractor;

import com.google.android.exoplayer2.ParserException;
import java.io.EOFException;
import java.io.IOException;
import org.checkerframework.dataflow.qual.Pure;

/* loaded from: classes2.dex */
public final class ExtractorUtil {
    @Pure
    public static void checkContainerInput(boolean z, String str) throws ParserException {
        if (!z) {
            throw ParserException.createForMalformedContainer(str, null);
        }
    }

    public static int peekToLength(ExtractorInput extractorInput, byte[] bArr, int r5, int r6) throws IOException {
        int r0 = 0;
        while (r0 < r6) {
            int peek = extractorInput.peek(bArr, r5 + r0, r6 - r0);
            if (peek == -1) {
                break;
            }
            r0 += peek;
        }
        return r0;
    }

    public static boolean readFullyQuietly(ExtractorInput extractorInput, byte[] bArr, int r2, int r3) throws IOException {
        try {
            extractorInput.readFully(bArr, r2, r3);
            return true;
        } catch (EOFException unused) {
            return false;
        }
    }

    public static boolean skipFullyQuietly(ExtractorInput extractorInput, int r1) throws IOException {
        try {
            extractorInput.skipFully(r1);
            return true;
        } catch (EOFException unused) {
            return false;
        }
    }

    public static boolean peekFullyQuietly(ExtractorInput extractorInput, byte[] bArr, int r2, int r3, boolean z) throws IOException {
        try {
            return extractorInput.peekFully(bArr, r2, r3, z);
        } catch (EOFException e) {
            if (z) {
                return false;
            }
            throw e;
        }
    }

    private ExtractorUtil() {
    }
}
