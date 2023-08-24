package okio.internal;

import com.fasterxml.jackson.core.base.GeneratorBase;
import com.google.android.exoplayer2.extractor.p011ts.PsExtractor;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okio.Utf8;

/* compiled from: -Utf8.kt */
@Metadata(m185bv = {1, 0, 3}, m184d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0012\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0002\u001a\u001e\u0010\u0003\u001a\u00020\u0002*\u00020\u00012\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0005¨\u0006\u0007"}, m183d2 = {"commonAsUtf8ToByteArray", "", "", "commonToUtf8String", "beginIndex", "", "endIndex", "okio"}, m182k = 2, m181mv = {1, 4, 0})
/* loaded from: classes5.dex */
public final class _Utf8Kt {
    public static /* synthetic */ String commonToUtf8String$default(byte[] bArr, int r1, int r2, int r3, Object obj) {
        if ((r3 & 1) != 0) {
            r1 = 0;
        }
        if ((r3 & 2) != 0) {
            r2 = bArr.length;
        }
        return commonToUtf8String(bArr, r1, r2);
    }

    /* JADX WARN: Code restructure failed: missing block: B:47:0x009d, code lost:
        if (((r16[r5] & 192) == 128) == false) goto L78;
     */
    /* JADX WARN: Code restructure failed: missing block: B:89:0x011e, code lost:
        if (((r16[r5] & 192) == 128) == false) goto L37;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final java.lang.String commonToUtf8String(byte[] r16, int r17, int r18) {
        /*
            Method dump skipped, instructions count: 492
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: okio.internal._Utf8Kt.commonToUtf8String(byte[], int, int):java.lang.String");
    }

    public static final byte[] commonAsUtf8ToByteArray(String commonAsUtf8ToByteArray) {
        int r6;
        int r9;
        char charAt;
        Intrinsics.checkNotNullParameter(commonAsUtf8ToByteArray, "$this$commonAsUtf8ToByteArray");
        byte[] bArr = new byte[commonAsUtf8ToByteArray.length() * 4];
        int length = commonAsUtf8ToByteArray.length();
        int r2 = 0;
        while (r2 < length) {
            char charAt2 = commonAsUtf8ToByteArray.charAt(r2);
            if (Intrinsics.compare((int) charAt2, 128) >= 0) {
                int length2 = commonAsUtf8ToByteArray.length();
                int r4 = r2;
                while (r2 < length2) {
                    char charAt3 = commonAsUtf8ToByteArray.charAt(r2);
                    if (Intrinsics.compare((int) charAt3, 128) < 0) {
                        int r7 = r4 + 1;
                        bArr[r4] = (byte) charAt3;
                        r2++;
                        while (r2 < length2 && Intrinsics.compare((int) commonAsUtf8ToByteArray.charAt(r2), 128) < 0) {
                            bArr[r7] = (byte) commonAsUtf8ToByteArray.charAt(r2);
                            r2++;
                            r7++;
                        }
                        r4 = r7;
                    } else {
                        if (Intrinsics.compare((int) charAt3, 2048) < 0) {
                            int r8 = r4 + 1;
                            bArr[r4] = (byte) ((charAt3 >> 6) | 192);
                            r6 = r8 + 1;
                            bArr[r8] = (byte) ((charAt3 & '?') | 128);
                        } else if (55296 > charAt3 || 57343 < charAt3) {
                            int r92 = r4 + 1;
                            bArr[r4] = (byte) ((charAt3 >> '\f') | 224);
                            int r72 = r92 + 1;
                            bArr[r92] = (byte) (((charAt3 >> 6) & 63) | 128);
                            r6 = r72 + 1;
                            bArr[r72] = (byte) ((charAt3 & '?') | 128);
                        } else if (Intrinsics.compare((int) charAt3, (int) GeneratorBase.SURR1_LAST) > 0 || length2 <= (r9 = r2 + 1) || 56320 > (charAt = commonAsUtf8ToByteArray.charAt(r9)) || 57343 < charAt) {
                            r6 = r4 + 1;
                            bArr[r4] = Utf8.REPLACEMENT_BYTE;
                        } else {
                            int charAt4 = ((charAt3 << '\n') + commonAsUtf8ToByteArray.charAt(r9)) - 56613888;
                            int r93 = r4 + 1;
                            bArr[r4] = (byte) ((charAt4 >> 18) | PsExtractor.VIDEO_STREAM_MASK);
                            int r73 = r93 + 1;
                            bArr[r93] = (byte) (((charAt4 >> 12) & 63) | 128);
                            int r94 = r73 + 1;
                            bArr[r73] = (byte) (((charAt4 >> 6) & 63) | 128);
                            r6 = r94 + 1;
                            bArr[r94] = (byte) ((charAt4 & 63) | 128);
                            r2 += 2;
                            r4 = r6;
                        }
                        r2++;
                        r4 = r6;
                    }
                }
                byte[] copyOf = Arrays.copyOf(bArr, r4);
                Intrinsics.checkNotNullExpressionValue(copyOf, "java.util.Arrays.copyOf(this, newSize)");
                return copyOf;
            }
            bArr[r2] = (byte) charAt2;
            r2++;
        }
        byte[] copyOf2 = Arrays.copyOf(bArr, commonAsUtf8ToByteArray.length());
        Intrinsics.checkNotNullExpressionValue(copyOf2, "java.util.Arrays.copyOf(this, newSize)");
        return copyOf2;
    }
}
