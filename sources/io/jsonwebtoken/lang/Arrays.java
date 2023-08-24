package io.jsonwebtoken.lang;

/* loaded from: classes5.dex */
public final class Arrays {
    private static final Arrays INSTANCE = new Arrays();

    private Arrays() {
    }

    public static int length(byte[] bArr) {
        if (bArr != null) {
            return bArr.length;
        }
        return 0;
    }

    public static byte[] clean(byte[] bArr) {
        if (length(bArr) > 0) {
            return bArr;
        }
        return null;
    }
}
