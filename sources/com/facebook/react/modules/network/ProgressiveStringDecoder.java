package com.facebook.react.modules.network;

import com.facebook.common.logging.FLog;
import com.facebook.react.common.ReactConstants;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

/* loaded from: classes.dex */
public class ProgressiveStringDecoder {
    private static final String EMPTY_STRING = "";
    private final CharsetDecoder mDecoder;
    private byte[] remainder = null;

    public ProgressiveStringDecoder(Charset charset) {
        this.mDecoder = charset.newDecoder();
    }

    public String decodeNext(byte[] bArr, int r10) {
        byte[] bArr2 = this.remainder;
        if (bArr2 != null) {
            byte[] bArr3 = new byte[bArr2.length + r10];
            System.arraycopy(bArr2, 0, bArr3, 0, bArr2.length);
            System.arraycopy(bArr, 0, bArr3, this.remainder.length, r10);
            r10 += this.remainder.length;
            bArr = bArr3;
        }
        ByteBuffer wrap = ByteBuffer.wrap(bArr, 0, r10);
        boolean z = true;
        CharBuffer charBuffer = null;
        boolean z2 = false;
        int r5 = 0;
        while (!z2 && r5 < 4) {
            try {
                charBuffer = this.mDecoder.decode(wrap);
                z2 = true;
            } catch (CharacterCodingException unused) {
                r5++;
                wrap = ByteBuffer.wrap(bArr, 0, r10 - r5);
            }
        }
        if ((!z2 || r5 <= 0) ? false : false) {
            byte[] bArr4 = new byte[r5];
            this.remainder = bArr4;
            System.arraycopy(bArr, r10 - r5, bArr4, 0, r5);
        } else {
            this.remainder = null;
        }
        if (!z2) {
            FLog.m1288w(ReactConstants.TAG, "failed to decode string from byte array");
            return "";
        }
        return new String(charBuffer.array(), 0, charBuffer.length());
    }
}
