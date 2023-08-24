package com.airbnb.lottie.parser.moshi;

/* loaded from: classes.dex */
final class JsonScope {
    static final int CLOSED = 8;
    static final int DANGLING_NAME = 4;
    static final int EMPTY_ARRAY = 1;
    static final int EMPTY_DOCUMENT = 6;
    static final int EMPTY_OBJECT = 3;
    static final int NONEMPTY_ARRAY = 2;
    static final int NONEMPTY_DOCUMENT = 7;
    static final int NONEMPTY_OBJECT = 5;

    private JsonScope() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String getPath(int r4, int[] r5, String[] strArr, int[] r7) {
        StringBuilder sb = new StringBuilder();
        sb.append('$');
        for (int r1 = 0; r1 < r4; r1++) {
            int r2 = r5[r1];
            if (r2 == 1 || r2 == 2) {
                sb.append('[');
                sb.append(r7[r1]);
                sb.append(']');
            } else if (r2 == 3 || r2 == 4 || r2 == 5) {
                sb.append('.');
                if (strArr[r1] != null) {
                    sb.append(strArr[r1]);
                }
            }
        }
        return sb.toString();
    }
}
