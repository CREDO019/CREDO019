package org.bouncycastle.pqc.crypto.sphincs;

import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.params.ParametersWithRandom;
import org.bouncycastle.pqc.crypto.MessageSigner;
import org.bouncycastle.pqc.crypto.sphincs.Tree;
import org.bouncycastle.util.Pack;

/* loaded from: classes3.dex */
public class SPHINCS256Signer implements MessageSigner {
    private final HashFunctions hashFunctions;
    private byte[] keyData;

    public SPHINCS256Signer(Digest digest, Digest digest2) {
        if (digest.getDigestSize() != 32) {
            throw new IllegalArgumentException("n-digest needs to produce 32 bytes of output");
        }
        if (digest2.getDigestSize() != 64) {
            throw new IllegalArgumentException("2n-digest needs to produce 64 bytes of output");
        }
        this.hashFunctions = new HashFunctions(digest, digest2);
    }

    static void compute_authpath_wots(HashFunctions hashFunctions, byte[] bArr, byte[] bArr2, int r25, Tree.leafaddr leafaddrVar, byte[] bArr3, byte[] bArr4, int r29) {
        Tree.leafaddr leafaddrVar2 = new Tree.leafaddr(leafaddrVar);
        byte[] bArr5 = new byte[2048];
        byte[] bArr6 = new byte[1024];
        byte[] bArr7 = new byte[68608];
        leafaddrVar2.subleaf = 0L;
        while (leafaddrVar2.subleaf < 32) {
            Seed.get_seed(hashFunctions, bArr6, (int) (leafaddrVar2.subleaf * 32), bArr3, leafaddrVar2);
            leafaddrVar2.subleaf++;
        }
        Wots wots = new Wots();
        leafaddrVar2.subleaf = 0L;
        while (leafaddrVar2.subleaf < 32) {
            wots.wots_pkgen(hashFunctions, bArr7, (int) (leafaddrVar2.subleaf * 67 * 32), bArr6, (int) (leafaddrVar2.subleaf * 32), bArr4, 0);
            leafaddrVar2.subleaf++;
        }
        leafaddrVar2.subleaf = 0L;
        while (leafaddrVar2.subleaf < 32) {
            Tree.l_tree(hashFunctions, bArr5, (int) ((leafaddrVar2.subleaf * 32) + 1024), bArr7, (int) (leafaddrVar2.subleaf * 67 * 32), bArr4, 0);
            leafaddrVar2.subleaf++;
        }
        int r12 = 0;
        for (int r11 = 32; r11 > 0; r11 >>>= 1) {
            for (int r13 = 0; r13 < r11; r13 += 2) {
                hashFunctions.hash_2n_n_mask(bArr5, ((r11 >>> 1) * 32) + ((r13 >>> 1) * 32), bArr5, (r11 * 32) + (r13 * 32), bArr4, (r12 + 7) * 2 * 32);
            }
            r12++;
        }
        int r0 = (int) leafaddrVar.subleaf;
        for (int r4 = 0; r4 < r29; r4++) {
            System.arraycopy(bArr5, ((32 >>> r4) * 32) + (((r0 >>> r4) ^ 1) * 32), bArr2, r25 + (r4 * 32), 32);
        }
        System.arraycopy(bArr5, 32, bArr, 0, 32);
    }

    static void validate_authpath(HashFunctions hashFunctions, byte[] bArr, byte[] bArr2, int r16, byte[] bArr3, int r18, byte[] bArr4, int r20) {
        byte[] bArr5 = new byte[64];
        if ((r16 & 1) != 0) {
            for (int r1 = 0; r1 < 32; r1++) {
                bArr5[r1 + 32] = bArr2[r1];
            }
            for (int r12 = 0; r12 < 32; r12++) {
                bArr5[r12] = bArr3[r18 + r12];
            }
        } else {
            for (int r13 = 0; r13 < 32; r13++) {
                bArr5[r13] = bArr2[r13];
            }
            for (int r14 = 0; r14 < 32; r14++) {
                bArr5[r14 + 32] = bArr3[r18 + r14];
            }
        }
        int r10 = r18 + 32;
        int r11 = 0;
        int r15 = r16;
        while (r11 < r20 - 1) {
            int r122 = r15 >>> 1;
            if ((r122 & 1) != 0) {
                hashFunctions.hash_2n_n_mask(bArr5, 32, bArr5, 0, bArr4, (r11 + 7) * 2 * 32);
                for (int r17 = 0; r17 < 32; r17++) {
                    bArr5[r17] = bArr3[r10 + r17];
                }
            } else {
                hashFunctions.hash_2n_n_mask(bArr5, 0, bArr5, 0, bArr4, (r11 + 7) * 2 * 32);
                for (int r19 = 0; r19 < 32; r19++) {
                    bArr5[r19 + 32] = bArr3[r10 + r19];
                }
            }
            r10 += 32;
            r11++;
            r15 = r122;
        }
        hashFunctions.hash_2n_n_mask(bArr, 0, bArr5, 0, bArr4, ((r20 + 7) - 1) * 2 * 32);
    }

    private void zerobytes(byte[] bArr, int r5, int r6) {
        for (int r1 = 0; r1 != r6; r1++) {
            bArr[r5 + r1] = 0;
        }
    }

    byte[] crypto_sign(HashFunctions hashFunctions, byte[] bArr, byte[] bArr2) {
        byte[] bArr3 = new byte[41000];
        byte[] bArr4 = new byte[32];
        byte[] bArr5 = new byte[64];
        long[] jArr = new long[8];
        byte[] bArr6 = new byte[32];
        byte[] bArr7 = new byte[32];
        byte[] bArr8 = new byte[1024];
        byte[] bArr9 = new byte[1088];
        for (int r6 = 0; r6 < 1088; r6++) {
            bArr9[r6] = bArr2[r6];
        }
        System.arraycopy(bArr9, 1056, bArr3, 40968, 32);
        Digest messageHash = hashFunctions.getMessageHash();
        byte[] bArr10 = new byte[messageHash.getDigestSize()];
        messageHash.update(bArr3, 40968, 32);
        messageHash.update(bArr, 0, bArr.length);
        messageHash.doFinal(bArr10, 0);
        zerobytes(bArr3, 40968, 32);
        for (int r4 = 0; r4 != 8; r4++) {
            jArr[r4] = Pack.littleEndianToLong(bArr10, r4 * 8);
        }
        long j = jArr[0] & 1152921504606846975L;
        System.arraycopy(bArr10, 16, bArr4, 0, 32);
        System.arraycopy(bArr4, 0, bArr3, 39912, 32);
        Tree.leafaddr leafaddrVar = new Tree.leafaddr();
        leafaddrVar.level = 11;
        leafaddrVar.subtree = 0L;
        leafaddrVar.subleaf = 0L;
        System.arraycopy(bArr9, 32, bArr3, 39944, 1024);
        Tree.treehash(hashFunctions, bArr3, 40968, 5, bArr9, leafaddrVar, bArr3, 39944);
        Digest messageHash2 = hashFunctions.getMessageHash();
        messageHash2.update(bArr3, 39912, 1088);
        messageHash2.update(bArr, 0, bArr.length);
        messageHash2.doFinal(bArr5, 0);
        Tree.leafaddr leafaddrVar2 = new Tree.leafaddr();
        leafaddrVar2.level = 12;
        leafaddrVar2.subleaf = (int) (j & 31);
        leafaddrVar2.subtree = j >>> 5;
        for (int r2 = 0; r2 < 32; r2++) {
            bArr3[r2] = bArr4[r2];
        }
        byte[] bArr11 = bArr8;
        System.arraycopy(bArr9, 32, bArr11, 0, 1024);
        for (int r22 = 0; r22 < 8; r22++) {
            bArr3[32 + r22] = (byte) ((j >>> (r22 * 8)) & 255);
        }
        Seed.get_seed(hashFunctions, bArr7, 0, bArr9, leafaddrVar2);
        new Horst();
        byte[] bArr12 = bArr9;
        Wots wots = new Wots();
        int horst_sign = 40 + Horst.horst_sign(hashFunctions, bArr3, 40, bArr6, bArr7, bArr11, bArr5);
        int r13 = 0;
        for (int r15 = 12; r13 < r15; r15 = 12) {
            leafaddrVar2.level = r13;
            byte[] bArr13 = bArr12;
            Seed.get_seed(hashFunctions, bArr7, 0, bArr13, leafaddrVar2);
            int r42 = horst_sign;
            int r152 = horst_sign;
            byte[] bArr14 = bArr11;
            wots.wots_sign(hashFunctions, bArr3, r42, bArr6, bArr7, bArr14);
            int r153 = r152 + 2144;
            compute_authpath_wots(hashFunctions, bArr6, bArr3, r153, leafaddrVar2, bArr13, bArr14, 5);
            horst_sign = r153 + 160;
            leafaddrVar2.subleaf = (int) (leafaddrVar2.subtree & 31);
            leafaddrVar2.subtree >>>= 5;
            r13++;
            bArr12 = bArr13;
            bArr11 = bArr11;
        }
        zerobytes(bArr12, 0, 1088);
        return bArr3;
    }

    @Override // org.bouncycastle.pqc.crypto.MessageSigner
    public byte[] generateSignature(byte[] bArr) {
        return crypto_sign(this.hashFunctions, bArr, this.keyData);
    }

    @Override // org.bouncycastle.pqc.crypto.MessageSigner
    public void init(boolean z, CipherParameters cipherParameters) {
        if (!z) {
            this.keyData = ((SPHINCSPublicKeyParameters) cipherParameters).getKeyData();
        } else if (cipherParameters instanceof ParametersWithRandom) {
            this.keyData = ((SPHINCSPrivateKeyParameters) ((ParametersWithRandom) cipherParameters).getParameters()).getKeyData();
        } else {
            this.keyData = ((SPHINCSPrivateKeyParameters) cipherParameters).getKeyData();
        }
    }

    boolean verify(HashFunctions hashFunctions, byte[] bArr, byte[] bArr2, byte[] bArr3) {
        byte[] bArr4 = new byte[2144];
        byte[] bArr5 = new byte[32];
        byte[] bArr6 = new byte[32];
        byte[] bArr7 = new byte[41000];
        byte[] bArr8 = new byte[1056];
        if (bArr2.length == 41000) {
            byte[] bArr9 = new byte[64];
            for (int r6 = 0; r6 < 1056; r6++) {
                bArr8[r6] = bArr3[r6];
            }
            byte[] bArr10 = new byte[32];
            for (int r7 = 0; r7 < 32; r7++) {
                bArr10[r7] = bArr2[r7];
            }
            System.arraycopy(bArr2, 0, bArr7, 0, 41000);
            Digest messageHash = hashFunctions.getMessageHash();
            messageHash.update(bArr10, 0, 32);
            messageHash.update(bArr8, 0, 1056);
            messageHash.update(bArr, 0, bArr.length);
            messageHash.doFinal(bArr9, 0);
            long j = 0;
            for (int r4 = 0; r4 < 8; r4++) {
                j ^= (bArr7[32 + r4] & 255) << (r4 * 8);
            }
            new Horst();
            Horst.horst_verify(hashFunctions, bArr6, bArr7, 40, bArr8, bArr9);
            Wots wots = new Wots();
            int r8 = 0;
            int r9 = 13352;
            while (r8 < 12) {
                byte[] bArr11 = bArr8;
                wots.wots_verify(hashFunctions, bArr4, bArr7, r9, bArr6, bArr8);
                int r2 = r9 + 2144;
                Tree.l_tree(hashFunctions, bArr5, 0, bArr4, 0, bArr11, 0);
                byte[] bArr12 = bArr7;
                validate_authpath(hashFunctions, bArr6, bArr5, (int) (31 & j), bArr12, r2, bArr11, 5);
                j >>= 5;
                r9 = r2 + 160;
                r8++;
                bArr7 = bArr12;
                bArr8 = bArr11;
            }
            byte[] bArr13 = bArr8;
            boolean z = true;
            for (int r1 = 0; r1 < 32; r1++) {
                if (bArr6[r1] != bArr13[r1 + 1024]) {
                    z = false;
                }
            }
            return z;
        }
        throw new IllegalArgumentException("signature wrong size");
    }

    @Override // org.bouncycastle.pqc.crypto.MessageSigner
    public boolean verifySignature(byte[] bArr, byte[] bArr2) {
        return verify(this.hashFunctions, bArr, bArr2, this.keyData);
    }
}
