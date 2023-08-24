package org.bouncycastle.pqc.crypto.qtesla;

/* loaded from: classes3.dex */
public class QTESLASecurityCategory {
    public static final int PROVABLY_SECURE_I = 5;
    public static final int PROVABLY_SECURE_III = 6;

    private QTESLASecurityCategory() {
    }

    public static String getName(int r3) {
        if (r3 != 5) {
            if (r3 == 6) {
                return "qTESLA-p-III";
            }
            throw new IllegalArgumentException("unknown security category: " + r3);
        }
        return "qTESLA-p-I";
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int getPrivateSize(int r3) {
        if (r3 != 5) {
            if (r3 == 6) {
                return 12392;
            }
            throw new IllegalArgumentException("unknown security category: " + r3);
        }
        return 5224;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int getPublicSize(int r3) {
        if (r3 != 5) {
            if (r3 == 6) {
                return 38432;
            }
            throw new IllegalArgumentException("unknown security category: " + r3);
        }
        return 14880;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int getSignatureSize(int r3) {
        if (r3 != 5) {
            if (r3 == 6) {
                return 5664;
            }
            throw new IllegalArgumentException("unknown security category: " + r3);
        }
        return 2592;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void validate(int r3) {
        if (r3 == 5 || r3 == 6) {
            return;
        }
        throw new IllegalArgumentException("unknown security category: " + r3);
    }
}
