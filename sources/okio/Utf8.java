package okio;

import com.fasterxml.jackson.core.base.GeneratorBase;
import com.google.android.exoplayer2.extractor.p011ts.PsExtractor;
import com.google.common.base.Ascii;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Utf8.kt */
@Metadata(m185bv = {1, 0, 3}, m184d1 = {"\u0000D\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0005\n\u0000\n\u0002\u0010\f\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0012\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\u001a\u0011\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u0001H\u0080\b\u001a\u0011\u0010\u000e\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u0007H\u0080\b\u001a4\u0010\u0010\u001a\u00020\u0001*\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00012\u0006\u0010\u0013\u001a\u00020\u00012\u0012\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00160\u0015H\u0080\bø\u0001\u0000\u001a4\u0010\u0017\u001a\u00020\u0001*\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00012\u0006\u0010\u0013\u001a\u00020\u00012\u0012\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00160\u0015H\u0080\bø\u0001\u0000\u001a4\u0010\u0018\u001a\u00020\u0001*\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00012\u0006\u0010\u0013\u001a\u00020\u00012\u0012\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00160\u0015H\u0080\bø\u0001\u0000\u001a4\u0010\u0019\u001a\u00020\u0016*\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00012\u0006\u0010\u0013\u001a\u00020\u00012\u0012\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00160\u0015H\u0080\bø\u0001\u0000\u001a4\u0010\u001a\u001a\u00020\u0016*\u00020\u001b2\u0006\u0010\u0012\u001a\u00020\u00012\u0006\u0010\u0013\u001a\u00020\u00012\u0012\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00160\u0015H\u0080\bø\u0001\u0000\u001a4\u0010\u001c\u001a\u00020\u0016*\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00012\u0006\u0010\u0013\u001a\u00020\u00012\u0012\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00160\u0015H\u0080\bø\u0001\u0000\u001a%\u0010\u001d\u001a\u00020\u001e*\u00020\u001b2\b\b\u0002\u0010\u0012\u001a\u00020\u00012\b\b\u0002\u0010\u0013\u001a\u00020\u0001H\u0007¢\u0006\u0002\b\u001f\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0003\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0004\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0005\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0006\u001a\u00020\u0007X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\b\u001a\u00020\tX\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\n\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006 "}, m183d2 = {"HIGH_SURROGATE_HEADER", "", "LOG_SURROGATE_HEADER", "MASK_2BYTES", "MASK_3BYTES", "MASK_4BYTES", "REPLACEMENT_BYTE", "", "REPLACEMENT_CHARACTER", "", "REPLACEMENT_CODE_POINT", "isIsoControl", "", "codePoint", "isUtf8Continuation", "byte", "process2Utf8Bytes", "", "beginIndex", "endIndex", "yield", "Lkotlin/Function1;", "", "process3Utf8Bytes", "process4Utf8Bytes", "processUtf16Chars", "processUtf8Bytes", "", "processUtf8CodePoints", "utf8Size", "", "size", "okio"}, m182k = 2, m181mv = {1, 4, 0})
/* loaded from: classes5.dex */
public final class Utf8 {
    public static final int HIGH_SURROGATE_HEADER = 55232;
    public static final int LOG_SURROGATE_HEADER = 56320;
    public static final int MASK_2BYTES = 3968;
    public static final int MASK_3BYTES = -123008;
    public static final int MASK_4BYTES = 3678080;
    public static final byte REPLACEMENT_BYTE = 63;
    public static final char REPLACEMENT_CHARACTER = 65533;
    public static final int REPLACEMENT_CODE_POINT = 65533;

    public static final boolean isIsoControl(int r2) {
        return (r2 >= 0 && 31 >= r2) || (127 <= r2 && 159 >= r2);
    }

    public static final boolean isUtf8Continuation(byte b) {
        return (b & 192) == 128;
    }

    public static final long size(String str) {
        return size$default(str, 0, 0, 3, null);
    }

    public static final long size(String str, int r4) {
        return size$default(str, r4, 0, 2, null);
    }

    public static /* synthetic */ long size$default(String str, int r1, int r2, int r3, Object obj) {
        if ((r3 & 1) != 0) {
            r1 = 0;
        }
        if ((r3 & 2) != 0) {
            r2 = str.length();
        }
        return size(str, r1, r2);
    }

    public static final long size(String utf8Size, int r11, int r12) {
        int r3;
        Intrinsics.checkNotNullParameter(utf8Size, "$this$utf8Size");
        if (!(r11 >= 0)) {
            throw new IllegalArgumentException(("beginIndex < 0: " + r11).toString());
        }
        if (!(r12 >= r11)) {
            throw new IllegalArgumentException(("endIndex < beginIndex: " + r12 + " < " + r11).toString());
        }
        if (!(r12 <= utf8Size.length())) {
            throw new IllegalArgumentException(("endIndex > string.length: " + r12 + " > " + utf8Size.length()).toString());
        }
        long j = 0;
        while (r11 < r12) {
            char charAt = utf8Size.charAt(r11);
            if (charAt < 128) {
                j++;
            } else {
                if (charAt < 2048) {
                    r3 = 2;
                } else if (charAt < 55296 || charAt > 57343) {
                    r3 = 3;
                } else {
                    int r7 = r11 + 1;
                    char charAt2 = r7 < r12 ? utf8Size.charAt(r7) : (char) 0;
                    if (charAt > 56319 || charAt2 < 56320 || charAt2 > 57343) {
                        j++;
                        r11 = r7;
                    } else {
                        j += 4;
                        r11 += 2;
                    }
                }
                j += r3;
            }
            r11++;
        }
        return j;
    }

    public static final void processUtf8Bytes(String processUtf8Bytes, int r8, int r9, Function1<? super Byte, Unit> yield) {
        int r4;
        char charAt;
        Intrinsics.checkNotNullParameter(processUtf8Bytes, "$this$processUtf8Bytes");
        Intrinsics.checkNotNullParameter(yield, "yield");
        while (r8 < r9) {
            char charAt2 = processUtf8Bytes.charAt(r8);
            if (Intrinsics.compare((int) charAt2, 128) < 0) {
                yield.invoke(Byte.valueOf((byte) charAt2));
                r8++;
                while (r8 < r9 && Intrinsics.compare((int) processUtf8Bytes.charAt(r8), 128) < 0) {
                    yield.invoke(Byte.valueOf((byte) processUtf8Bytes.charAt(r8)));
                    r8++;
                }
            } else {
                if (Intrinsics.compare((int) charAt2, 2048) < 0) {
                    yield.invoke(Byte.valueOf((byte) ((charAt2 >> 6) | 192)));
                    yield.invoke(Byte.valueOf((byte) ((charAt2 & '?') | 128)));
                } else if (55296 > charAt2 || 57343 < charAt2) {
                    yield.invoke(Byte.valueOf((byte) ((charAt2 >> '\f') | 224)));
                    yield.invoke(Byte.valueOf((byte) (((charAt2 >> 6) & 63) | 128)));
                    yield.invoke(Byte.valueOf((byte) ((charAt2 & '?') | 128)));
                } else if (Intrinsics.compare((int) charAt2, (int) GeneratorBase.SURR1_LAST) > 0 || r9 <= (r4 = r8 + 1) || 56320 > (charAt = processUtf8Bytes.charAt(r4)) || 57343 < charAt) {
                    yield.invoke(Byte.valueOf((byte) REPLACEMENT_BYTE));
                } else {
                    int charAt3 = ((charAt2 << '\n') + processUtf8Bytes.charAt(r4)) - 56613888;
                    yield.invoke(Byte.valueOf((byte) ((charAt3 >> 18) | PsExtractor.VIDEO_STREAM_MASK)));
                    yield.invoke(Byte.valueOf((byte) (((charAt3 >> 12) & 63) | 128)));
                    yield.invoke(Byte.valueOf((byte) (((charAt3 >> 6) & 63) | 128)));
                    yield.invoke(Byte.valueOf((byte) ((charAt3 & 63) | 128)));
                    r8 += 2;
                }
                r8++;
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:38:0x0096, code lost:
        if (((r16[r4] & 192) == 128) == false) goto L21;
     */
    /* JADX WARN: Code restructure failed: missing block: B:78:0x0117, code lost:
        if (((r16[r4] & 192) == 128) == false) goto L30;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void processUtf8CodePoints(byte[] r16, int r17, int r18, kotlin.jvm.functions.Function1<? super java.lang.Integer, kotlin.Unit> r19) {
        /*
            Method dump skipped, instructions count: 411
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: okio.Utf8.processUtf8CodePoints(byte[], int, int, kotlin.jvm.functions.Function1):void");
    }

    /* JADX WARN: Code restructure failed: missing block: B:38:0x0098, code lost:
        if (((r16[r4] & 192) == 128) == false) goto L21;
     */
    /* JADX WARN: Code restructure failed: missing block: B:78:0x0119, code lost:
        if (((r16[r4] & 192) == 128) == false) goto L30;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void processUtf16Chars(byte[] r16, int r17, int r18, kotlin.jvm.functions.Function1<? super java.lang.Character, kotlin.Unit> r19) {
        /*
            Method dump skipped, instructions count: 439
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: okio.Utf8.processUtf16Chars(byte[], int, int, kotlin.jvm.functions.Function1):void");
    }

    public static final int process2Utf8Bytes(byte[] process2Utf8Bytes, int r4, int r5, Function1<? super Integer, Unit> yield) {
        Intrinsics.checkNotNullParameter(process2Utf8Bytes, "$this$process2Utf8Bytes");
        Intrinsics.checkNotNullParameter(yield, "yield");
        int r0 = r4 + 1;
        Integer valueOf = Integer.valueOf((int) REPLACEMENT_CODE_POINT);
        if (r5 <= r0) {
            yield.invoke(valueOf);
            return 1;
        }
        byte b = process2Utf8Bytes[r4];
        byte b2 = process2Utf8Bytes[r0];
        if (!((b2 & 192) == 128)) {
            yield.invoke(valueOf);
            return 1;
        }
        int r3 = (b2 ^ 3968) ^ (b << 6);
        if (r3 < 128) {
            yield.invoke(valueOf);
            return 2;
        }
        yield.invoke(Integer.valueOf(r3));
        return 2;
    }

    public static final int process3Utf8Bytes(byte[] process3Utf8Bytes, int r8, int r9, Function1<? super Integer, Unit> yield) {
        Intrinsics.checkNotNullParameter(process3Utf8Bytes, "$this$process3Utf8Bytes");
        Intrinsics.checkNotNullParameter(yield, "yield");
        int r0 = r8 + 2;
        Integer valueOf = Integer.valueOf((int) REPLACEMENT_CODE_POINT);
        if (r9 <= r0) {
            yield.invoke(valueOf);
            int r82 = r8 + 1;
            if (r9 > r82) {
                if ((process3Utf8Bytes[r82] & 192) == 128) {
                    return 2;
                }
            }
            return 1;
        }
        byte b = process3Utf8Bytes[r8];
        byte b2 = process3Utf8Bytes[r8 + 1];
        if (!((b2 & 192) == 128)) {
            yield.invoke(valueOf);
            return 1;
        }
        byte b3 = process3Utf8Bytes[r0];
        if (!((b3 & 192) == 128)) {
            yield.invoke(valueOf);
            return 2;
        }
        int r7 = ((b3 ^ (-123008)) ^ (b2 << 6)) ^ (b << Ascii.f1121FF);
        if (r7 < 2048) {
            yield.invoke(valueOf);
            return 3;
        } else if (55296 <= r7 && 57343 >= r7) {
            yield.invoke(valueOf);
            return 3;
        } else {
            yield.invoke(Integer.valueOf(r7));
            return 3;
        }
    }

    public static final int process4Utf8Bytes(byte[] process4Utf8Bytes, int r10, int r11, Function1<? super Integer, Unit> yield) {
        Intrinsics.checkNotNullParameter(process4Utf8Bytes, "$this$process4Utf8Bytes");
        Intrinsics.checkNotNullParameter(yield, "yield");
        int r0 = r10 + 3;
        Integer valueOf = Integer.valueOf((int) REPLACEMENT_CODE_POINT);
        if (r11 <= r0) {
            yield.invoke(valueOf);
            int r12 = r10 + 1;
            if (r11 > r12) {
                if ((process4Utf8Bytes[r12] & 192) == 128) {
                    int r102 = r10 + 2;
                    if (r11 > r102) {
                        if ((process4Utf8Bytes[r102] & 192) == 128) {
                            return 3;
                        }
                    }
                    return 2;
                }
            }
            return 1;
        }
        byte b = process4Utf8Bytes[r10];
        byte b2 = process4Utf8Bytes[r10 + 1];
        if (!((b2 & 192) == 128)) {
            yield.invoke(valueOf);
            return 1;
        }
        byte b3 = process4Utf8Bytes[r10 + 2];
        if (!((b3 & 192) == 128)) {
            yield.invoke(valueOf);
            return 2;
        }
        byte b4 = process4Utf8Bytes[r0];
        if (!((b4 & 192) == 128)) {
            yield.invoke(valueOf);
            return 3;
        }
        int r9 = (((b4 ^ 3678080) ^ (b3 << 6)) ^ (b2 << Ascii.f1121FF)) ^ (b << Ascii.DC2);
        if (r9 > 1114111) {
            yield.invoke(valueOf);
            return 4;
        } else if (55296 <= r9 && 57343 >= r9) {
            yield.invoke(valueOf);
            return 4;
        } else if (r9 < 65536) {
            yield.invoke(valueOf);
            return 4;
        } else {
            yield.invoke(Integer.valueOf(r9));
            return 4;
        }
    }
}
