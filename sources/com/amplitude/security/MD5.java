package com.amplitude.security;

import ai.api.util.VoiceActivityDetector;
import com.facebook.imagepipeline.memory.BitmapCounterConfig;
import java.security.DigestException;
import java.security.MessageDigest;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;

/* loaded from: classes.dex */
public final class MD5 extends MessageDigest implements Cloneable {
    private long bytes;

    /* renamed from: hA */
    private int f85hA;

    /* renamed from: hB */
    private int f86hB;

    /* renamed from: hC */
    private int f87hC;

    /* renamed from: hD */
    private int f88hD;
    private byte[] pad;
    private int padded;

    @Override // java.security.MessageDigestSpi
    public int engineGetDigestLength() {
        return 16;
    }

    public MD5() {
        super(MessageDigestAlgorithms.MD5);
        this.pad = new byte[64];
        init();
    }

    @Override // java.security.MessageDigest, java.security.MessageDigestSpi
    public Object clone() throws CloneNotSupportedException {
        MD5 md5 = (MD5) super.clone();
        md5.pad = (byte[]) this.pad.clone();
        return md5;
    }

    @Override // java.security.MessageDigestSpi
    public void engineReset() {
        this.padded = 0;
        this.bytes = 0L;
        byte[] bArr = this.pad;
        int r2 = 60;
        do {
            bArr[r2 - 4] = 0;
            bArr[r2 - 3] = 0;
            bArr[r2 - 2] = 0;
            bArr[r2 - 1] = 0;
            bArr[r2] = 0;
            bArr[r2 + 1] = 0;
            bArr[r2 + 2] = 0;
            bArr[r2 + 3] = 0;
            r2 -= 8;
        } while (r2 >= 0);
        init();
    }

    protected void init() {
        this.f85hA = 1732584193;
        this.f86hB = -271733879;
        this.f87hC = -1732584194;
        this.f88hD = 271733878;
    }

    @Override // java.security.MessageDigestSpi
    public void engineUpdate(byte b) {
        this.bytes++;
        int r0 = this.padded;
        if (r0 < 63) {
            byte[] bArr = this.pad;
            this.padded = r0 + 1;
            bArr[r0] = b;
            return;
        }
        byte[] bArr2 = this.pad;
        bArr2[63] = b;
        engineUpdate(bArr2, r0);
        this.padded = 0;
    }

    @Override // java.security.MessageDigestSpi
    public void engineUpdate(byte[] bArr, int r6, int r7) {
        if (r6 >= 0 && r7 >= 0 && r6 + r7 <= bArr.length) {
            this.bytes += r7;
            int r0 = this.padded;
            if (r0 > 0 && r0 + r7 >= 64) {
                int r3 = 64 - r0;
                System.arraycopy(bArr, r6, this.pad, r0, r3);
                byte[] bArr2 = this.pad;
                this.padded = 0;
                engineUpdate(bArr2, 0);
                r6 += r3;
                r7 -= r3;
            }
            while (r7 >= 512) {
                engineUpdate(bArr, r6);
                engineUpdate(bArr, r6 + 64);
                engineUpdate(bArr, r6 + 128);
                engineUpdate(bArr, r6 + 192);
                engineUpdate(bArr, r6 + 256);
                engineUpdate(bArr, r6 + VoiceActivityDetector.FRAME_SIZE_IN_BYTES);
                engineUpdate(bArr, r6 + BitmapCounterConfig.DEFAULT_MAX_BITMAP_COUNT);
                engineUpdate(bArr, r6 + 448);
                r6 += 512;
                r7 -= 512;
            }
            while (r7 >= 64) {
                engineUpdate(bArr, r6);
                r6 += 64;
                r7 -= 64;
            }
            if (r7 > 0) {
                System.arraycopy(bArr, r6, this.pad, this.padded, r7);
                this.padded += r7;
                return;
            }
            return;
        }
        throw new ArrayIndexOutOfBoundsException(r6);
    }

    @Override // java.security.MessageDigestSpi
    public byte[] engineDigest() {
        try {
            byte[] bArr = new byte[16];
            engineDigest(bArr, 0, 16);
            return bArr;
        } catch (DigestException unused) {
            return null;
        }
    }

    @Override // java.security.MessageDigestSpi
    public int engineDigest(byte[] bArr, int r18, int r19) throws DigestException {
        if (r19 >= 16) {
            if (bArr.length - r18 >= 16) {
                byte[] bArr2 = this.pad;
                int r4 = this.padded;
                bArr2[r4] = Byte.MIN_VALUE;
                switch (r4) {
                    case 56:
                        bArr2[57] = 0;
                    case 57:
                        bArr2[58] = 0;
                    case 58:
                        bArr2[59] = 0;
                    case 59:
                        bArr2[60] = 0;
                    case 60:
                        bArr2[61] = 0;
                    case 61:
                        bArr2[62] = 0;
                    case 62:
                        bArr2[63] = 0;
                    case 63:
                        engineUpdate(bArr2, 0);
                        r4 = -1;
                        break;
                }
                switch (r4 & 7) {
                    case 0:
                        r4 += 4;
                        bArr2[r4 - 3] = 0;
                        bArr2[r4 - 2] = 0;
                        bArr2[r4 - 1] = 0;
                        bArr2[r4] = 0;
                        bArr2[r4 + 1] = 0;
                        bArr2[r4 + 2] = 0;
                        bArr2[r4 + 3] = 0;
                        break;
                    case 1:
                        r4 += 3;
                        bArr2[r4 - 2] = 0;
                        bArr2[r4 - 1] = 0;
                        bArr2[r4] = 0;
                        bArr2[r4 + 1] = 0;
                        bArr2[r4 + 2] = 0;
                        bArr2[r4 + 3] = 0;
                        break;
                    case 2:
                        r4 += 2;
                        bArr2[r4 - 1] = 0;
                        bArr2[r4] = 0;
                        bArr2[r4 + 1] = 0;
                        bArr2[r4 + 2] = 0;
                        bArr2[r4 + 3] = 0;
                        break;
                    case 3:
                        r4++;
                        bArr2[r4] = 0;
                        bArr2[r4 + 1] = 0;
                        bArr2[r4 + 2] = 0;
                        bArr2[r4 + 3] = 0;
                        break;
                    case 4:
                        bArr2[r4 + 1] = 0;
                        bArr2[r4 + 2] = 0;
                        bArr2[r4 + 3] = 0;
                        break;
                    case 5:
                        r4--;
                        bArr2[r4 + 2] = 0;
                        bArr2[r4 + 3] = 0;
                        break;
                    case 6:
                        r4 -= 2;
                        bArr2[r4 + 3] = 0;
                        break;
                    case 7:
                        r4 -= 3;
                        break;
                }
                while (true) {
                    r4 += 8;
                    if (r4 <= 52) {
                        bArr2[r4 - 4] = 0;
                        bArr2[r4 - 3] = 0;
                        bArr2[r4 - 2] = 0;
                        bArr2[r4 - 1] = 0;
                        bArr2[r4] = 0;
                        bArr2[r4 + 1] = 0;
                        bArr2[r4 + 2] = 0;
                        bArr2[r4 + 3] = 0;
                    } else {
                        long j = this.bytes;
                        int r15 = ((int) j) << 3;
                        bArr2[56] = (byte) r15;
                        bArr2[57] = (byte) (r15 >>> 8);
                        bArr2[58] = (byte) (r15 >>> 16);
                        bArr2[59] = (byte) (r15 >>> 24);
                        int r2 = (int) (j >>> 29);
                        bArr2[60] = (byte) r2;
                        bArr2[61] = (byte) (r2 >>> 8);
                        bArr2[62] = (byte) (r2 >>> 16);
                        bArr2[63] = (byte) (r2 >>> 24);
                        engineUpdate(bArr2, 0);
                        int r22 = this.f85hA;
                        bArr[r18] = (byte) r22;
                        bArr[r18 + 1] = (byte) (r22 >>> 8);
                        bArr[r18 + 2] = (byte) (r22 >>> 16);
                        bArr[r18 + 3] = (byte) (r22 >>> 24);
                        int r3 = this.f86hB;
                        bArr[r18 + 4] = (byte) r3;
                        bArr[r18 + 5] = (byte) (r3 >>> 8);
                        int r23 = r18 + 10;
                        bArr[r23 - 4] = (byte) (r3 >>> 16);
                        bArr[r23 - 3] = (byte) (r3 >>> 24);
                        int r42 = this.f87hC;
                        bArr[r23 - 2] = (byte) r42;
                        bArr[r23 - 1] = (byte) (r42 >>> 8);
                        bArr[r23] = (byte) (r42 >>> 16);
                        bArr[r23 + 1] = (byte) (r42 >>> 24);
                        int r43 = this.f88hD;
                        bArr[r23 + 2] = (byte) r43;
                        bArr[r23 + 3] = (byte) (r43 >>> 8);
                        bArr[r23 + 4] = (byte) (r43 >>> 16);
                        bArr[r23 + 5] = (byte) (r43 >>> 24);
                        engineReset();
                        return 16;
                    }
                }
            } else {
                throw new DigestException("insufficient space in output buffer to store the digest");
            }
        } else {
            throw new DigestException("partial digests not returned");
        }
    }

    /*  JADX ERROR: Type inference failed with exception
        jadx.core.utils.exceptions.JadxOverflowException: Type update terminated with stack overflow, arg: (r13v149 ?? I:??[int, boolean])
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:56)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:30)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:18)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:114)
        */
    private final void engineUpdate(byte[] r28, int r29) {
        /*
            Method dump skipped, instructions count: 1558
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amplitude.security.MD5.engineUpdate(byte[], int):void");
    }
}
