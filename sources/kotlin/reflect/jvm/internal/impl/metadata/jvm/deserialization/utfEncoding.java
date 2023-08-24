package kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization;

import kotlin.jvm.internal.Intrinsics;

/* renamed from: kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.UtfEncodingKt */
/* loaded from: classes5.dex */
public final class utfEncoding {
    public static final byte[] stringsToBytes(String[] strings) {
        int r9;
        Intrinsics.checkNotNullParameter(strings, "strings");
        int length = strings.length;
        int r2 = 0;
        int r3 = 0;
        while (r2 < length) {
            String str = strings[r2];
            r2++;
            r3 += str.length();
        }
        byte[] bArr = new byte[r3];
        int length2 = strings.length;
        int r32 = 0;
        int r4 = 0;
        while (r32 < length2) {
            String str2 = strings[r32];
            r32++;
            int length3 = str2.length() - 1;
            if (length3 >= 0) {
                int r7 = 0;
                while (true) {
                    int r8 = r7 + 1;
                    r9 = r4 + 1;
                    bArr[r4] = (byte) str2.charAt(r7);
                    if (r7 == length3) {
                        break;
                    }
                    r7 = r8;
                    r4 = r9;
                }
                r4 = r9;
            }
        }
        return bArr;
    }
}
