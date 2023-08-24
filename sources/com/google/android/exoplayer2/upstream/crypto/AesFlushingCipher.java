package com.google.android.exoplayer2.upstream.crypto;

import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Util;
import java.nio.ByteBuffer;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.ShortBufferException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* loaded from: classes2.dex */
public final class AesFlushingCipher {
    private final int blockSize;
    private final Cipher cipher;
    private final byte[] flushedBlock;
    private int pendingXorBytes;
    private final byte[] zerosBlock;

    public AesFlushingCipher(int r8, byte[] bArr, String str, long j) {
        this(r8, bArr, getFNV64Hash(str), j);
    }

    public AesFlushingCipher(int r7, byte[] bArr, long j, long j2) {
        try {
            Cipher cipher = Cipher.getInstance("AES/CTR/NoPadding");
            this.cipher = cipher;
            int blockSize = cipher.getBlockSize();
            this.blockSize = blockSize;
            this.zerosBlock = new byte[blockSize];
            this.flushedBlock = new byte[blockSize];
            int r12 = (int) (j2 % blockSize);
            cipher.init(r7, new SecretKeySpec(bArr, Util.splitAtFirst(cipher.getAlgorithm(), "/")[0]), new IvParameterSpec(getInitializationVector(j, j2 / blockSize)));
            if (r12 != 0) {
                updateInPlace(new byte[r12], 0, r12);
            }
        } catch (InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateInPlace(byte[] bArr, int r8, int r9) {
        update(bArr, r8, r9, bArr, r8);
    }

    public void update(byte[] bArr, int r10, int r11, byte[] bArr2, int r13) {
        int r2 = r10;
        do {
            int r102 = this.pendingXorBytes;
            if (r102 > 0) {
                bArr2[r13] = (byte) (bArr[r2] ^ this.flushedBlock[this.blockSize - r102]);
                r13++;
                r2++;
                this.pendingXorBytes = r102 - 1;
                r11--;
            } else {
                int nonFlushingUpdate = nonFlushingUpdate(bArr, r2, r11, bArr2, r13);
                if (r11 == nonFlushingUpdate) {
                    return;
                }
                int r112 = r11 - nonFlushingUpdate;
                int r0 = 0;
                Assertions.checkState(r112 < this.blockSize);
                int r132 = r13 + nonFlushingUpdate;
                int r5 = this.blockSize - r112;
                this.pendingXorBytes = r5;
                Assertions.checkState(nonFlushingUpdate(this.zerosBlock, 0, r5, this.flushedBlock, 0) == this.blockSize);
                while (r0 < r112) {
                    bArr2[r132] = this.flushedBlock[r0];
                    r0++;
                    r132++;
                }
                return;
            }
        } while (r11 != 0);
    }

    private int nonFlushingUpdate(byte[] bArr, int r8, int r9, byte[] bArr2, int r11) {
        try {
            return this.cipher.update(bArr, r8, r9, bArr2, r11);
        } catch (ShortBufferException e) {
            throw new RuntimeException(e);
        }
    }

    private byte[] getInitializationVector(long j, long j2) {
        return ByteBuffer.allocate(16).putLong(j).putLong(j2).array();
    }

    private static long getFNV64Hash(String str) {
        long j = 0;
        if (str == null) {
            return 0L;
        }
        for (int r2 = 0; r2 < str.length(); r2++) {
            long charAt = j ^ str.charAt(r2);
            j = charAt + (charAt << 1) + (charAt << 4) + (charAt << 5) + (charAt << 7) + (charAt << 8) + (charAt << 40);
        }
        return j;
    }
}
