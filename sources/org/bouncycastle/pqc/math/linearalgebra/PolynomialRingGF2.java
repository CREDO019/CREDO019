package org.bouncycastle.pqc.math.linearalgebra;

import java.io.PrintStream;
import org.bouncycastle.asn1.cmc.BodyPartID;

/* loaded from: classes4.dex */
public final class PolynomialRingGF2 {
    private PolynomialRingGF2() {
    }

    public static int add(int r0, int r1) {
        return r0 ^ r1;
    }

    public static int degree(int r1) {
        int r0 = -1;
        while (r1 != 0) {
            r0++;
            r1 >>>= 1;
        }
        return r0;
    }

    public static int degree(long j) {
        int r0 = 0;
        while (j != 0) {
            r0++;
            j >>>= 1;
        }
        return r0 - 1;
    }

    public static int gcd(int r1, int r2) {
        while (true) {
            int r0 = r2;
            int r22 = r1;
            r1 = r0;
            if (r1 == 0) {
                return r22;
            }
            r2 = remainder(r22, r1);
        }
    }

    public static int getIrreduciblePolynomial(int r3) {
        PrintStream printStream;
        String str;
        if (r3 < 0) {
            printStream = System.err;
            str = "The Degree is negative";
        } else if (r3 <= 31) {
            if (r3 == 0) {
                return 1;
            }
            int r32 = 1 << (r3 + 1);
            for (int r2 = (1 << r3) + 1; r2 < r32; r2 += 2) {
                if (isIrreducible(r2)) {
                    return r2;
                }
            }
            return 0;
        } else {
            printStream = System.err;
            str = "The Degree is more then 31";
        }
        printStream.println(str);
        return 0;
    }

    public static boolean isIrreducible(int r6) {
        if (r6 == 0) {
            return false;
        }
        int degree = degree(r6) >>> 1;
        int r3 = 2;
        for (int r4 = 0; r4 < degree; r4++) {
            r3 = modMultiply(r3, r3, r6);
            if (gcd(r3 ^ 2, r6) != 1) {
                return false;
            }
        }
        return true;
    }

    public static int modMultiply(int r4, int r5, int r6) {
        int remainder = remainder(r4, r6);
        int remainder2 = remainder(r5, r6);
        int r0 = 0;
        if (remainder2 != 0) {
            int degree = 1 << degree(r6);
            while (remainder != 0) {
                if (((byte) (remainder & 1)) == 1) {
                    r0 ^= remainder2;
                }
                remainder >>>= 1;
                remainder2 <<= 1;
                if (remainder2 >= degree) {
                    remainder2 ^= r6;
                }
            }
        }
        return r0;
    }

    public static long multiply(int r6, int r7) {
        long j = 0;
        if (r7 != 0) {
            long j2 = r7 & BodyPartID.bodyIdMax;
            while (r6 != 0) {
                if (((byte) (r6 & 1)) == 1) {
                    j ^= j2;
                }
                r6 >>>= 1;
                j2 <<= 1;
            }
        }
        return j;
    }

    public static int remainder(int r2, int r3) {
        if (r3 == 0) {
            System.err.println("Error: to be divided by 0");
            return 0;
        }
        while (degree(r2) >= degree(r3)) {
            r2 ^= r3 << (degree(r2) - degree(r3));
        }
        return r2;
    }

    public static int rest(long j, int r9) {
        if (r9 == 0) {
            System.err.println("Error: to be divided by 0");
            return 0;
        }
        long j2 = r9 & BodyPartID.bodyIdMax;
        while ((j >>> 32) != 0) {
            j ^= j2 << (degree(j) - degree(j2));
        }
        int r8 = (int) (j & (-1));
        while (degree(r8) >= degree(r9)) {
            r8 ^= r9 << (degree(r8) - degree(r9));
        }
        return r8;
    }
}
