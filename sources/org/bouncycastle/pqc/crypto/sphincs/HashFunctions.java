package org.bouncycastle.pqc.crypto.sphincs;

import org.bouncycastle.crypto.Digest;
import org.bouncycastle.util.Strings;

/* loaded from: classes3.dex */
class HashFunctions {
    private static final byte[] hashc = Strings.toByteArray("expand 32-byte to 64-byte state!");
    private final Digest dig256;
    private final Digest dig512;
    private final Permute perm;

    /* JADX INFO: Access modifiers changed from: package-private */
    public HashFunctions(Digest digest) {
        this(digest, null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public HashFunctions(Digest digest, Digest digest2) {
        this.perm = new Permute();
        this.dig256 = digest;
        this.dig512 = digest2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Digest getMessageHash() {
        return this.dig512;
    }

    int hash_2n_n(byte[] bArr, int r8, byte[] bArr2, int r10) {
        byte[] bArr3 = new byte[64];
        for (int r2 = 0; r2 < 32; r2++) {
            bArr3[r2] = bArr2[r10 + r2];
            bArr3[r2 + 32] = hashc[r2];
        }
        this.perm.chacha_permute(bArr3, bArr3);
        for (int r22 = 0; r22 < 32; r22++) {
            bArr3[r22] = (byte) (bArr3[r22] ^ bArr2[(r10 + r22) + 32]);
        }
        this.perm.chacha_permute(bArr3, bArr3);
        for (int r9 = 0; r9 < 32; r9++) {
            bArr[r8 + r9] = bArr3[r9];
        }
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int hash_2n_n_mask(byte[] bArr, int r8, byte[] bArr2, int r10, byte[] bArr3, int r12) {
        byte[] bArr4 = new byte[64];
        for (int r3 = 0; r3 < 64; r3++) {
            bArr4[r3] = (byte) (bArr2[r10 + r3] ^ bArr3[r12 + r3]);
        }
        return hash_2n_n(bArr, r8, bArr4, 0);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int hash_n_n(byte[] bArr, int r7, byte[] bArr2, int r9) {
        byte[] bArr3 = new byte[64];
        for (int r2 = 0; r2 < 32; r2++) {
            bArr3[r2] = bArr2[r9 + r2];
            bArr3[r2 + 32] = hashc[r2];
        }
        this.perm.chacha_permute(bArr3, bArr3);
        for (int r8 = 0; r8 < 32; r8++) {
            bArr[r7 + r8] = bArr3[r8];
        }
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int hash_n_n_mask(byte[] bArr, int r8, byte[] bArr2, int r10, byte[] bArr3, int r12) {
        byte[] bArr4 = new byte[32];
        for (int r3 = 0; r3 < 32; r3++) {
            bArr4[r3] = (byte) (bArr2[r10 + r3] ^ bArr3[r12 + r3]);
        }
        return hash_n_n(bArr, r8, bArr4, 0);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int varlen_hash(byte[] bArr, int r4, byte[] bArr2, int r6) {
        this.dig256.update(bArr2, 0, r6);
        this.dig256.doFinal(bArr, r4);
        return 0;
    }
}
