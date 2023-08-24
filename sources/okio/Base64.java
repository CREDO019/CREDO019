package okio;

import com.google.common.base.Ascii;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: -Base64.kt */
@Metadata(m185bv = {1, 0, 3}, m184d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0003\u001a\u000e\u0010\u0006\u001a\u0004\u0018\u00010\u0001*\u00020\u0007H\u0000\u001a\u0016\u0010\b\u001a\u00020\u0007*\u00020\u00012\b\b\u0002\u0010\t\u001a\u00020\u0001H\u0000\"\u0014\u0010\u0000\u001a\u00020\u0001X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0003\"\u0014\u0010\u0004\u001a\u00020\u0001X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0003¨\u0006\n"}, m183d2 = {"BASE64", "", "getBASE64", "()[B", "BASE64_URL_SAFE", "getBASE64_URL_SAFE", "decodeBase64ToArray", "", "encodeBase64", "map", "okio"}, m182k = 2, m181mv = {1, 4, 0})
/* renamed from: okio.-Base64  reason: invalid class name */
/* loaded from: classes5.dex */
public final class Base64 {
    private static final byte[] BASE64 = ByteString.Companion.encodeUtf8("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/").getData$okio();
    private static final byte[] BASE64_URL_SAFE = ByteString.Companion.encodeUtf8("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-_").getData$okio();

    public static final byte[] getBASE64() {
        return BASE64;
    }

    public static final byte[] getBASE64_URL_SAFE() {
        return BASE64_URL_SAFE;
    }

    public static final byte[] decodeBase64ToArray(String decodeBase64ToArray) {
        int r12;
        char charAt;
        Intrinsics.checkNotNullParameter(decodeBase64ToArray, "$this$decodeBase64ToArray");
        int length = decodeBase64ToArray.length();
        while (length > 0 && ((charAt = decodeBase64ToArray.charAt(length - 1)) == '=' || charAt == '\n' || charAt == '\r' || charAt == ' ' || charAt == '\t')) {
            length--;
        }
        int r6 = (int) ((length * 6) / 8);
        byte[] bArr = new byte[r6];
        int r8 = 0;
        int r9 = 0;
        int r10 = 0;
        for (int r7 = 0; r7 < length; r7++) {
            char charAt2 = decodeBase64ToArray.charAt(r7);
            if ('A' <= charAt2 && 'Z' >= charAt2) {
                r12 = charAt2 - 'A';
            } else if ('a' <= charAt2 && 'z' >= charAt2) {
                r12 = charAt2 - 'G';
            } else if ('0' <= charAt2 && '9' >= charAt2) {
                r12 = charAt2 + 4;
            } else if (charAt2 == '+' || charAt2 == '-') {
                r12 = 62;
            } else if (charAt2 == '/' || charAt2 == '_') {
                r12 = 63;
            } else {
                if (charAt2 != '\n' && charAt2 != '\r' && charAt2 != ' ' && charAt2 != '\t') {
                    return null;
                }
            }
            r9 = (r9 << 6) | r12;
            r8++;
            if (r8 % 4 == 0) {
                int r11 = r10 + 1;
                bArr[r10] = (byte) (r9 >> 16);
                int r102 = r11 + 1;
                bArr[r11] = (byte) (r9 >> 8);
                bArr[r102] = (byte) r9;
                r10 = r102 + 1;
            }
        }
        int r82 = r8 % 4;
        if (r82 != 1) {
            if (r82 == 2) {
                bArr[r10] = (byte) ((r9 << 12) >> 16);
                r10++;
            } else if (r82 == 3) {
                int r15 = r9 << 6;
                int r0 = r10 + 1;
                bArr[r10] = (byte) (r15 >> 16);
                r10 = r0 + 1;
                bArr[r0] = (byte) (r15 >> 8);
            }
            if (r10 == r6) {
                return bArr;
            }
            byte[] copyOf = Arrays.copyOf(bArr, r10);
            Intrinsics.checkNotNullExpressionValue(copyOf, "java.util.Arrays.copyOf(this, newSize)");
            return copyOf;
        }
        return null;
    }

    public static /* synthetic */ String encodeBase64$default(byte[] bArr, byte[] bArr2, int r2, Object obj) {
        if ((r2 & 1) != 0) {
            bArr2 = BASE64;
        }
        return encodeBase64(bArr, bArr2);
    }

    public static final String encodeBase64(byte[] encodeBase64, byte[] map) {
        Intrinsics.checkNotNullParameter(encodeBase64, "$this$encodeBase64");
        Intrinsics.checkNotNullParameter(map, "map");
        byte[] bArr = new byte[((encodeBase64.length + 2) / 3) * 4];
        int length = encodeBase64.length - (encodeBase64.length % 3);
        int r3 = 0;
        int r4 = 0;
        while (r3 < length) {
            int r5 = r3 + 1;
            byte b = encodeBase64[r3];
            int r6 = r5 + 1;
            byte b2 = encodeBase64[r5];
            int r7 = r6 + 1;
            byte b3 = encodeBase64[r6];
            int r8 = r4 + 1;
            bArr[r4] = map[(b & 255) >> 2];
            int r42 = r8 + 1;
            bArr[r8] = map[((b & 3) << 4) | ((b2 & 255) >> 4)];
            int r32 = r42 + 1;
            bArr[r42] = map[((b2 & Ascii.f1128SI) << 2) | ((b3 & 255) >> 6)];
            r4 = r32 + 1;
            bArr[r32] = map[b3 & Utf8.REPLACEMENT_BYTE];
            r3 = r7;
        }
        int length2 = encodeBase64.length - length;
        if (length2 == 1) {
            byte b4 = encodeBase64[r3];
            int r33 = r4 + 1;
            bArr[r4] = map[(b4 & 255) >> 2];
            int r1 = r33 + 1;
            bArr[r33] = map[(b4 & 3) << 4];
            byte b5 = (byte) 61;
            bArr[r1] = b5;
            bArr[r1 + 1] = b5;
        } else if (length2 == 2) {
            int r52 = r3 + 1;
            byte b6 = encodeBase64[r3];
            byte b7 = encodeBase64[r52];
            int r53 = r4 + 1;
            bArr[r4] = map[(b6 & 255) >> 2];
            int r43 = r53 + 1;
            bArr[r53] = map[((b6 & 3) << 4) | ((b7 & 255) >> 4)];
            bArr[r43] = map[(b7 & Ascii.f1128SI) << 2];
            bArr[r43 + 1] = (byte) 61;
        }
        return Platform.toUtf8String(bArr);
    }
}
