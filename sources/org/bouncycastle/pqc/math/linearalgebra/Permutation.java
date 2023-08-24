package org.bouncycastle.pqc.math.linearalgebra;

import java.security.SecureRandom;
import org.bouncycastle.util.Arrays;

/* loaded from: classes4.dex */
public class Permutation {
    private int[] perm;

    public Permutation(int r2) {
        if (r2 <= 0) {
            throw new IllegalArgumentException("invalid length");
        }
        this.perm = new int[r2];
        for (int r22 = r2 - 1; r22 >= 0; r22--) {
            this.perm[r22] = r22;
        }
    }

    public Permutation(int r7, SecureRandom secureRandom) {
        if (r7 <= 0) {
            throw new IllegalArgumentException("invalid length");
        }
        this.perm = new int[r7];
        int[] r0 = new int[r7];
        for (int r2 = 0; r2 < r7; r2++) {
            r0[r2] = r2;
        }
        int r22 = r7;
        for (int r1 = 0; r1 < r7; r1++) {
            int nextInt = RandUtils.nextInt(secureRandom, r22);
            r22--;
            this.perm[r1] = r0[nextInt];
            r0[nextInt] = r0[r22];
        }
    }

    public Permutation(byte[] bArr) {
        if (bArr.length <= 4) {
            throw new IllegalArgumentException("invalid encoding");
        }
        int OS2IP = LittleEndianConversions.OS2IP(bArr, 0);
        int ceilLog256 = IntegerFunctions.ceilLog256(OS2IP - 1);
        if (bArr.length != (OS2IP * ceilLog256) + 4) {
            throw new IllegalArgumentException("invalid encoding");
        }
        this.perm = new int[OS2IP];
        for (int r0 = 0; r0 < OS2IP; r0++) {
            this.perm[r0] = LittleEndianConversions.OS2IP(bArr, (r0 * ceilLog256) + 4, ceilLog256);
        }
        if (!isPermutation(this.perm)) {
            throw new IllegalArgumentException("invalid encoding");
        }
    }

    public Permutation(int[] r2) {
        if (!isPermutation(r2)) {
            throw new IllegalArgumentException("array is not a permutation vector");
        }
        this.perm = IntUtils.clone(r2);
    }

    private boolean isPermutation(int[] r7) {
        int length = r7.length;
        boolean[] zArr = new boolean[length];
        for (int r3 = 0; r3 < length; r3++) {
            if (r7[r3] < 0 || r7[r3] >= length || zArr[r7[r3]]) {
                return false;
            }
            zArr[r7[r3]] = true;
        }
        return true;
    }

    public Permutation computeInverse() {
        Permutation permutation = new Permutation(this.perm.length);
        for (int length = this.perm.length - 1; length >= 0; length--) {
            permutation.perm[this.perm[length]] = length;
        }
        return permutation;
    }

    public boolean equals(Object obj) {
        if (obj instanceof Permutation) {
            return IntUtils.equals(this.perm, ((Permutation) obj).perm);
        }
        return false;
    }

    public byte[] getEncoded() {
        int length = this.perm.length;
        int ceilLog256 = IntegerFunctions.ceilLog256(length - 1);
        byte[] bArr = new byte[(length * ceilLog256) + 4];
        LittleEndianConversions.I2OSP(length, bArr, 0);
        for (int r3 = 0; r3 < length; r3++) {
            LittleEndianConversions.I2OSP(this.perm[r3], bArr, (r3 * ceilLog256) + 4, ceilLog256);
        }
        return bArr;
    }

    public int[] getVector() {
        return IntUtils.clone(this.perm);
    }

    public int hashCode() {
        return Arrays.hashCode(this.perm);
    }

    public Permutation rightMultiply(Permutation permutation) {
        int length = permutation.perm.length;
        int[] r1 = this.perm;
        if (length == r1.length) {
            Permutation permutation2 = new Permutation(r1.length);
            for (int length2 = this.perm.length - 1; length2 >= 0; length2--) {
                permutation2.perm[length2] = this.perm[permutation.perm[length2]];
            }
            return permutation2;
        }
        throw new IllegalArgumentException("length mismatch");
    }

    public String toString() {
        String str = "[" + this.perm[0];
        for (int r1 = 1; r1 < this.perm.length; r1++) {
            str = str + ", " + this.perm[r1];
        }
        return str + "]";
    }
}
