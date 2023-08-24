package okio;

import com.facebook.imagepipeline.producers.DecodeProducer;
import com.google.android.exoplayer2.source.rtsp.SessionDescription;
import com.google.common.base.Ascii;
import com.onesignal.NotificationBundleProcessor;
import kotlin.Metadata;
import kotlin.UShort;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: -Util.kt */
@Metadata(m185bv = {1, 0, 3}, m184d1 = {"\u0000:\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u0005\n\u0002\b\u0004\n\u0002\u0010\n\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\u001a0\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u0005H\u0000\u001a \u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\b\u001a\u00020\fH\u0000\u001a\u0019\u0010\u000e\u001a\u00020\f2\u0006\u0010\u0002\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\fH\u0080\b\u001a\u0019\u0010\u000e\u001a\u00020\f2\u0006\u0010\u0002\u001a\u00020\f2\u0006\u0010\u0006\u001a\u00020\u0005H\u0080\b\u001a\u0015\u0010\u000f\u001a\u00020\u0005*\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0005H\u0080\f\u001a\u0015\u0010\u000f\u001a\u00020\f*\u00020\u00102\u0006\u0010\u0011\u001a\u00020\fH\u0080\f\u001a\u0015\u0010\u000f\u001a\u00020\f*\u00020\u00052\u0006\u0010\u0011\u001a\u00020\fH\u0080\f\u001a\u0015\u0010\u0012\u001a\u00020\u0005*\u00020\u00052\u0006\u0010\u0013\u001a\u00020\u0005H\u0080\f\u001a\f\u0010\u0014\u001a\u00020\u0005*\u00020\u0005H\u0000\u001a\f\u0010\u0014\u001a\u00020\f*\u00020\fH\u0000\u001a\f\u0010\u0014\u001a\u00020\u0015*\u00020\u0015H\u0000\u001a\u0015\u0010\u0016\u001a\u00020\f*\u00020\f2\u0006\u0010\u0013\u001a\u00020\u0005H\u0080\f\u001a\u0015\u0010\u0017\u001a\u00020\u0005*\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0005H\u0080\f\u001a\u0015\u0010\u0018\u001a\u00020\u0005*\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0005H\u0080\f\u001a\f\u0010\u0019\u001a\u00020\u001a*\u00020\u0010H\u0000\u001a\f\u0010\u0019\u001a\u00020\u001a*\u00020\u0005H\u0000\u001a\f\u0010\u0019\u001a\u00020\u001a*\u00020\fH\u0000¨\u0006\u001b"}, m183d2 = {"arrayRangeEquals", "", NotificationBundleProcessor.PUSH_ADDITIONAL_DATA_KEY, "", "aOffset", "", "b", "bOffset", DecodeProducer.EXTRA_BITMAP_BYTES, "checkOffsetAndCount", "", "size", "", "offset", "minOf", "and", "", "other", "leftRotate", "bitCount", "reverseBytes", "", "rightRotate", "shl", "shr", "toHexString", "", "okio"}, m182k = 2, m181mv = {1, 4, 0})
/* renamed from: okio.-Util  reason: invalid class name */
/* loaded from: classes5.dex */
public final class Util {
    public static final int and(byte b, int r1) {
        return b & r1;
    }

    public static final long and(byte b, long j) {
        return b & j;
    }

    public static final long and(int r2, long j) {
        return r2 & j;
    }

    public static final int leftRotate(int r1, int r2) {
        return (r1 >>> (32 - r2)) | (r1 << r2);
    }

    public static final int reverseBytes(int r2) {
        return ((r2 & 255) << 24) | (((-16777216) & r2) >>> 24) | ((16711680 & r2) >>> 8) | ((65280 & r2) << 8);
    }

    public static final long reverseBytes(long j) {
        return ((j & 255) << 56) | (((-72057594037927936L) & j) >>> 56) | ((71776119061217280L & j) >>> 40) | ((280375465082880L & j) >>> 24) | ((1095216660480L & j) >>> 8) | ((4278190080L & j) << 8) | ((16711680 & j) << 24) | ((65280 & j) << 40);
    }

    public static final short reverseBytes(short s) {
        int r1 = s & UShort.MAX_VALUE;
        return (short) (((r1 & 255) << 8) | ((65280 & r1) >>> 8));
    }

    public static final long rightRotate(long j, int r4) {
        return (j << (64 - r4)) | (j >>> r4);
    }

    public static final int shl(byte b, int r1) {
        return b << r1;
    }

    public static final int shr(byte b, int r1) {
        return b >> r1;
    }

    public static final void checkOffsetAndCount(long j, long j2, long j3) {
        if ((j2 | j3) < 0 || j2 > j || j - j2 < j3) {
            throw new ArrayIndexOutOfBoundsException("size=" + j + " offset=" + j2 + " byteCount=" + j3);
        }
    }

    public static final long minOf(long j, int r4) {
        return Math.min(j, r4);
    }

    public static final long minOf(int r2, long j) {
        return Math.min(r2, j);
    }

    public static final boolean arrayRangeEquals(byte[] a, int r5, byte[] b, int r7, int r8) {
        Intrinsics.checkNotNullParameter(a, "a");
        Intrinsics.checkNotNullParameter(b, "b");
        for (int r1 = 0; r1 < r8; r1++) {
            if (a[r1 + r5] != b[r1 + r7]) {
                return false;
            }
        }
        return true;
    }

    public static final String toHexString(byte b) {
        return new String(new char[]{okio.internal.ByteString.getHEX_DIGIT_CHARS()[(b >> 4) & 15], okio.internal.ByteString.getHEX_DIGIT_CHARS()[b & Ascii.f1128SI]});
    }

    public static final String toHexString(int r6) {
        if (r6 == 0) {
            return SessionDescription.SUPPORTED_SDP_VERSION;
        }
        int r3 = 0;
        char[] cArr = {okio.internal.ByteString.getHEX_DIGIT_CHARS()[(r6 >> 28) & 15], okio.internal.ByteString.getHEX_DIGIT_CHARS()[(r6 >> 24) & 15], okio.internal.ByteString.getHEX_DIGIT_CHARS()[(r6 >> 20) & 15], okio.internal.ByteString.getHEX_DIGIT_CHARS()[(r6 >> 16) & 15], okio.internal.ByteString.getHEX_DIGIT_CHARS()[(r6 >> 12) & 15], okio.internal.ByteString.getHEX_DIGIT_CHARS()[(r6 >> 8) & 15], okio.internal.ByteString.getHEX_DIGIT_CHARS()[(r6 >> 4) & 15], okio.internal.ByteString.getHEX_DIGIT_CHARS()[r6 & 15]};
        while (r3 < 8 && cArr[r3] == '0') {
            r3++;
        }
        return new String(cArr, r3, 8 - r3);
    }

    public static final String toHexString(long j) {
        if (j == 0) {
            return SessionDescription.SUPPORTED_SDP_VERSION;
        }
        int r3 = 0;
        char[] cArr = {okio.internal.ByteString.getHEX_DIGIT_CHARS()[(int) ((j >> 60) & 15)], okio.internal.ByteString.getHEX_DIGIT_CHARS()[(int) ((j >> 56) & 15)], okio.internal.ByteString.getHEX_DIGIT_CHARS()[(int) ((j >> 52) & 15)], okio.internal.ByteString.getHEX_DIGIT_CHARS()[(int) ((j >> 48) & 15)], okio.internal.ByteString.getHEX_DIGIT_CHARS()[(int) ((j >> 44) & 15)], okio.internal.ByteString.getHEX_DIGIT_CHARS()[(int) ((j >> 40) & 15)], okio.internal.ByteString.getHEX_DIGIT_CHARS()[(int) ((j >> 36) & 15)], okio.internal.ByteString.getHEX_DIGIT_CHARS()[(int) ((j >> 32) & 15)], okio.internal.ByteString.getHEX_DIGIT_CHARS()[(int) ((j >> 28) & 15)], okio.internal.ByteString.getHEX_DIGIT_CHARS()[(int) ((j >> 24) & 15)], okio.internal.ByteString.getHEX_DIGIT_CHARS()[(int) ((j >> 20) & 15)], okio.internal.ByteString.getHEX_DIGIT_CHARS()[(int) ((j >> 16) & 15)], okio.internal.ByteString.getHEX_DIGIT_CHARS()[(int) ((j >> 12) & 15)], okio.internal.ByteString.getHEX_DIGIT_CHARS()[(int) ((j >> 8) & 15)], okio.internal.ByteString.getHEX_DIGIT_CHARS()[(int) ((j >> 4) & 15)], okio.internal.ByteString.getHEX_DIGIT_CHARS()[(int) (j & 15)]};
        while (r3 < 16 && cArr[r3] == '0') {
            r3++;
        }
        return new String(cArr, r3, 16 - r3);
    }
}
