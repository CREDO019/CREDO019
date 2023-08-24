package androidx.emoji2.text.flatbuffer;

import androidx.emoji2.text.flatbuffer.Utf8;
import com.google.android.exoplayer2.extractor.p011ts.PsExtractor;
import java.nio.ByteBuffer;

/* loaded from: classes.dex */
public final class Utf8Safe extends Utf8 {
    private static int computeEncodedLength(CharSequence charSequence) {
        int length = charSequence.length();
        int r1 = 0;
        while (r1 < length && charSequence.charAt(r1) < 128) {
            r1++;
        }
        int r2 = length;
        while (true) {
            if (r1 < length) {
                char charAt = charSequence.charAt(r1);
                if (charAt >= 2048) {
                    r2 += encodedLengthGeneral(charSequence, r1);
                    break;
                }
                r2 += (127 - charAt) >>> 31;
                r1++;
            } else {
                break;
            }
        }
        if (r2 >= length) {
            return r2;
        }
        throw new IllegalArgumentException("UTF-8 length does not fit in int: " + (r2 + 4294967296L));
    }

    private static int encodedLengthGeneral(CharSequence charSequence, int r5) {
        int length = charSequence.length();
        int r1 = 0;
        while (r5 < length) {
            char charAt = charSequence.charAt(r5);
            if (charAt < 2048) {
                r1 += (127 - charAt) >>> 31;
            } else {
                r1 += 2;
                if (55296 <= charAt && charAt <= 57343) {
                    if (Character.codePointAt(charSequence, r5) < 65536) {
                        throw new UnpairedSurrogateException(r5, length);
                    }
                    r5++;
                }
            }
            r5++;
        }
        return r1;
    }

    public static String decodeUtf8Array(byte[] bArr, int r12, int r13) {
        if ((r12 | r13 | ((bArr.length - r12) - r13)) < 0) {
            throw new ArrayIndexOutOfBoundsException(String.format("buffer length=%d, index=%d, size=%d", Integer.valueOf(bArr.length), Integer.valueOf(r12), Integer.valueOf(r13)));
        }
        int r0 = r12 + r13;
        char[] cArr = new char[r13];
        int r3 = 0;
        while (r12 < r0) {
            byte b = bArr[r12];
            if (!Utf8.DecodeUtil.isOneByte(b)) {
                break;
            }
            r12++;
            Utf8.DecodeUtil.handleOneByte(b, cArr, r3);
            r3++;
        }
        int r8 = r3;
        while (r12 < r0) {
            int r32 = r12 + 1;
            byte b2 = bArr[r12];
            if (Utf8.DecodeUtil.isOneByte(b2)) {
                int r4 = r8 + 1;
                Utf8.DecodeUtil.handleOneByte(b2, cArr, r8);
                while (r32 < r0) {
                    byte b3 = bArr[r32];
                    if (!Utf8.DecodeUtil.isOneByte(b3)) {
                        break;
                    }
                    r32++;
                    Utf8.DecodeUtil.handleOneByte(b3, cArr, r4);
                    r4++;
                }
                r12 = r32;
                r8 = r4;
            } else if (Utf8.DecodeUtil.isTwoBytes(b2)) {
                if (r32 >= r0) {
                    throw new IllegalArgumentException("Invalid UTF-8");
                }
                Utf8.DecodeUtil.handleTwoBytes(b2, bArr[r32], cArr, r8);
                r12 = r32 + 1;
                r8++;
            } else if (Utf8.DecodeUtil.isThreeBytes(b2)) {
                if (r32 >= r0 - 1) {
                    throw new IllegalArgumentException("Invalid UTF-8");
                }
                int r42 = r32 + 1;
                Utf8.DecodeUtil.handleThreeBytes(b2, bArr[r32], bArr[r42], cArr, r8);
                r12 = r42 + 1;
                r8++;
            } else if (r32 >= r0 - 2) {
                throw new IllegalArgumentException("Invalid UTF-8");
            } else {
                int r43 = r32 + 1;
                byte b4 = bArr[r32];
                int r33 = r43 + 1;
                Utf8.DecodeUtil.handleFourBytes(b2, b4, bArr[r43], bArr[r33], cArr, r8);
                r12 = r33 + 1;
                r8 = r8 + 1 + 1;
            }
        }
        return new String(cArr, 0, r8);
    }

    public static String decodeUtf8Buffer(ByteBuffer byteBuffer, int r12, int r13) {
        if ((r12 | r13 | ((byteBuffer.limit() - r12) - r13)) < 0) {
            throw new ArrayIndexOutOfBoundsException(String.format("buffer limit=%d, index=%d, limit=%d", Integer.valueOf(byteBuffer.limit()), Integer.valueOf(r12), Integer.valueOf(r13)));
        }
        int r0 = r12 + r13;
        char[] cArr = new char[r13];
        int r3 = 0;
        while (r12 < r0) {
            byte b = byteBuffer.get(r12);
            if (!Utf8.DecodeUtil.isOneByte(b)) {
                break;
            }
            r12++;
            Utf8.DecodeUtil.handleOneByte(b, cArr, r3);
            r3++;
        }
        int r8 = r3;
        while (r12 < r0) {
            int r32 = r12 + 1;
            byte b2 = byteBuffer.get(r12);
            if (Utf8.DecodeUtil.isOneByte(b2)) {
                int r4 = r8 + 1;
                Utf8.DecodeUtil.handleOneByte(b2, cArr, r8);
                while (r32 < r0) {
                    byte b3 = byteBuffer.get(r32);
                    if (!Utf8.DecodeUtil.isOneByte(b3)) {
                        break;
                    }
                    r32++;
                    Utf8.DecodeUtil.handleOneByte(b3, cArr, r4);
                    r4++;
                }
                r12 = r32;
                r8 = r4;
            } else if (Utf8.DecodeUtil.isTwoBytes(b2)) {
                if (r32 >= r0) {
                    throw new IllegalArgumentException("Invalid UTF-8");
                }
                Utf8.DecodeUtil.handleTwoBytes(b2, byteBuffer.get(r32), cArr, r8);
                r12 = r32 + 1;
                r8++;
            } else if (Utf8.DecodeUtil.isThreeBytes(b2)) {
                if (r32 >= r0 - 1) {
                    throw new IllegalArgumentException("Invalid UTF-8");
                }
                int r42 = r32 + 1;
                Utf8.DecodeUtil.handleThreeBytes(b2, byteBuffer.get(r32), byteBuffer.get(r42), cArr, r8);
                r12 = r42 + 1;
                r8++;
            } else if (r32 >= r0 - 2) {
                throw new IllegalArgumentException("Invalid UTF-8");
            } else {
                int r43 = r32 + 1;
                byte b4 = byteBuffer.get(r32);
                int r33 = r43 + 1;
                Utf8.DecodeUtil.handleFourBytes(b2, b4, byteBuffer.get(r43), byteBuffer.get(r33), cArr, r8);
                r12 = r33 + 1;
                r8 = r8 + 1 + 1;
            }
        }
        return new String(cArr, 0, r8);
    }

    @Override // androidx.emoji2.text.flatbuffer.Utf8
    public int encodedLength(CharSequence charSequence) {
        return computeEncodedLength(charSequence);
    }

    @Override // androidx.emoji2.text.flatbuffer.Utf8
    public String decodeUtf8(ByteBuffer byteBuffer, int r3, int r4) throws IllegalArgumentException {
        if (byteBuffer.hasArray()) {
            return decodeUtf8Array(byteBuffer.array(), byteBuffer.arrayOffset() + r3, r4);
        }
        return decodeUtf8Buffer(byteBuffer, r3, r4);
    }

    private static void encodeUtf8Buffer(CharSequence charSequence, ByteBuffer byteBuffer) {
        int length = charSequence.length();
        int position = byteBuffer.position();
        int r2 = 0;
        while (r2 < length) {
            try {
                char charAt = charSequence.charAt(r2);
                if (charAt >= 128) {
                    break;
                }
                byteBuffer.put(position + r2, (byte) charAt);
                r2++;
            } catch (IndexOutOfBoundsException unused) {
                throw new ArrayIndexOutOfBoundsException("Failed writing " + charSequence.charAt(r2) + " at index " + (byteBuffer.position() + Math.max(r2, (position - byteBuffer.position()) + 1)));
            }
        }
        if (r2 == length) {
            byteBuffer.position(position + r2);
            return;
        }
        position += r2;
        while (r2 < length) {
            char charAt2 = charSequence.charAt(r2);
            if (charAt2 < 128) {
                byteBuffer.put(position, (byte) charAt2);
            } else if (charAt2 < 2048) {
                int r5 = position + 1;
                try {
                    byteBuffer.put(position, (byte) ((charAt2 >>> 6) | 192));
                    byteBuffer.put(r5, (byte) ((charAt2 & '?') | 128));
                    position = r5;
                } catch (IndexOutOfBoundsException unused2) {
                    position = r5;
                    throw new ArrayIndexOutOfBoundsException("Failed writing " + charSequence.charAt(r2) + " at index " + (byteBuffer.position() + Math.max(r2, (position - byteBuffer.position()) + 1)));
                }
            } else if (charAt2 < 55296 || 57343 < charAt2) {
                int r52 = position + 1;
                byteBuffer.put(position, (byte) ((charAt2 >>> '\f') | 224));
                position = r52 + 1;
                byteBuffer.put(r52, (byte) (((charAt2 >>> 6) & 63) | 128));
                byteBuffer.put(position, (byte) ((charAt2 & '?') | 128));
            } else {
                int r53 = r2 + 1;
                if (r53 != length) {
                    try {
                        char charAt3 = charSequence.charAt(r53);
                        if (Character.isSurrogatePair(charAt2, charAt3)) {
                            int codePoint = Character.toCodePoint(charAt2, charAt3);
                            int r4 = position + 1;
                            try {
                                byteBuffer.put(position, (byte) ((codePoint >>> 18) | PsExtractor.VIDEO_STREAM_MASK));
                                int r1 = r4 + 1;
                                byteBuffer.put(r4, (byte) (((codePoint >>> 12) & 63) | 128));
                                int r42 = r1 + 1;
                                byteBuffer.put(r1, (byte) (((codePoint >>> 6) & 63) | 128));
                                byteBuffer.put(r42, (byte) ((codePoint & 63) | 128));
                                position = r42;
                                r2 = r53;
                            } catch (IndexOutOfBoundsException unused3) {
                                position = r4;
                                r2 = r53;
                                throw new ArrayIndexOutOfBoundsException("Failed writing " + charSequence.charAt(r2) + " at index " + (byteBuffer.position() + Math.max(r2, (position - byteBuffer.position()) + 1)));
                            }
                        } else {
                            r2 = r53;
                        }
                    } catch (IndexOutOfBoundsException unused4) {
                    }
                }
                throw new UnpairedSurrogateException(r2, length);
            }
            r2++;
            position++;
        }
        byteBuffer.position(position);
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x001d, code lost:
        return r9 + r0;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static int encodeUtf8Array(java.lang.CharSequence r7, byte[] r8, int r9, int r10) {
        /*
            Method dump skipped, instructions count: 254
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.emoji2.text.flatbuffer.Utf8Safe.encodeUtf8Array(java.lang.CharSequence, byte[], int, int):int");
    }

    @Override // androidx.emoji2.text.flatbuffer.Utf8
    public void encodeUtf8(CharSequence charSequence, ByteBuffer byteBuffer) {
        if (byteBuffer.hasArray()) {
            int arrayOffset = byteBuffer.arrayOffset();
            byteBuffer.position(encodeUtf8Array(charSequence, byteBuffer.array(), byteBuffer.position() + arrayOffset, byteBuffer.remaining()) - arrayOffset);
            return;
        }
        encodeUtf8Buffer(charSequence, byteBuffer);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class UnpairedSurrogateException extends IllegalArgumentException {
        UnpairedSurrogateException(int r3, int r4) {
            super("Unpaired surrogate at index " + r3 + " of " + r4);
        }
    }
}
