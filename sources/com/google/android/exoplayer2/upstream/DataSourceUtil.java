package com.google.android.exoplayer2.upstream;

import java.io.IOException;
import java.util.Arrays;

/* loaded from: classes2.dex */
public final class DataSourceUtil {
    private DataSourceUtil() {
    }

    public static byte[] readToEnd(DataSource dataSource) throws IOException {
        byte[] bArr = new byte[1024];
        int r1 = 0;
        int r2 = 0;
        while (r1 != -1) {
            if (r2 == bArr.length) {
                bArr = Arrays.copyOf(bArr, bArr.length * 2);
            }
            r1 = dataSource.read(bArr, r2, bArr.length - r2);
            if (r1 != -1) {
                r2 += r1;
            }
        }
        return Arrays.copyOf(bArr, r2);
    }

    public static byte[] readExactly(DataSource dataSource, int r5) throws IOException {
        byte[] bArr = new byte[r5];
        int r1 = 0;
        while (r1 < r5) {
            int read = dataSource.read(bArr, r1, r5 - r1);
            if (read == -1) {
                throw new IllegalStateException("Not enough data could be read: " + r1 + " < " + r5);
            }
            r1 += read;
        }
        return bArr;
    }

    public static void closeQuietly(DataSource dataSource) {
        if (dataSource != null) {
            try {
                dataSource.close();
            } catch (IOException unused) {
            }
        }
    }
}
