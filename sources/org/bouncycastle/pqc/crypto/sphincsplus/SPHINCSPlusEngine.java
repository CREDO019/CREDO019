package org.bouncycastle.pqc.crypto.sphincsplus;

import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.Xof;
import org.bouncycastle.crypto.digests.SHA256Digest;
import org.bouncycastle.crypto.digests.SHA512Digest;
import org.bouncycastle.crypto.digests.SHAKEDigest;
import org.bouncycastle.crypto.generators.MGF1BytesGenerator;
import org.bouncycastle.crypto.macs.HMac;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.MGFParameters;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Pack;

/* loaded from: classes3.dex */
abstract class SPHINCSPlusEngine {

    /* renamed from: A */
    final int f2503A;

    /* renamed from: D */
    final int f2504D;

    /* renamed from: H */
    final int f2505H;
    final int H_PRIME;

    /* renamed from: K */
    final int f2506K;

    /* renamed from: N */
    final int f2507N;

    /* renamed from: T */
    final int f2508T;
    final int WOTS_LEN;
    final int WOTS_LEN1;
    final int WOTS_LEN2;
    final int WOTS_LOGW;
    final int WOTS_W;
    final boolean robust;

    /* loaded from: classes3.dex */
    static class Sha256Engine extends SPHINCSPlusEngine {
        private final byte[] digestBuf;
        private final byte[] hmacBuf;
        private final MGF1BytesGenerator mgf1;
        private final Digest msgDigest;
        private final byte[] padding;
        private final Digest treeDigest;
        private final HMac treeHMac;

        public Sha256Engine(boolean z, int r2, int r3, int r4, int r5, int r6, int r7) {
            super(z, r2, r3, r4, r5, r6, r7);
            MGF1BytesGenerator mGF1BytesGenerator;
            this.padding = new byte[64];
            SHA256Digest sHA256Digest = new SHA256Digest();
            this.treeDigest = sHA256Digest;
            if (r2 == 32) {
                this.msgDigest = new SHA512Digest();
                this.treeHMac = new HMac(new SHA512Digest());
                mGF1BytesGenerator = new MGF1BytesGenerator(new SHA512Digest());
            } else {
                this.msgDigest = new SHA256Digest();
                this.treeHMac = new HMac(new SHA256Digest());
                mGF1BytesGenerator = new MGF1BytesGenerator(new SHA256Digest());
            }
            this.mgf1 = mGF1BytesGenerator;
            this.digestBuf = new byte[sHA256Digest.getDigestSize()];
            this.hmacBuf = new byte[this.treeHMac.getMacSize()];
        }

        private byte[] compressedADRS(ADRS adrs) {
            byte[] bArr = new byte[22];
            System.arraycopy(adrs.value, 3, bArr, 0, 1);
            System.arraycopy(adrs.value, 8, bArr, 1, 8);
            System.arraycopy(adrs.value, 19, bArr, 9, 1);
            System.arraycopy(adrs.value, 20, bArr, 10, 12);
            return bArr;
        }

        @Override // org.bouncycastle.pqc.crypto.sphincsplus.SPHINCSPlusEngine
        /* renamed from: F */
        public byte[] mo8F(byte[] bArr, ADRS adrs, byte[] bArr2) {
            byte[] compressedADRS = compressedADRS(adrs);
            if (this.robust) {
                bArr2 = bitmask256(Arrays.concatenate(bArr, compressedADRS), bArr2);
            }
            this.treeDigest.update(bArr, 0, bArr.length);
            this.treeDigest.update(this.padding, 0, 64 - bArr.length);
            this.treeDigest.update(compressedADRS, 0, compressedADRS.length);
            this.treeDigest.update(bArr2, 0, bArr2.length);
            this.treeDigest.doFinal(this.digestBuf, 0);
            return Arrays.copyOfRange(this.digestBuf, 0, this.f2507N);
        }

        @Override // org.bouncycastle.pqc.crypto.sphincsplus.SPHINCSPlusEngine
        /* renamed from: H */
        public byte[] mo7H(byte[] bArr, ADRS adrs, byte[] bArr2, byte[] bArr3) {
            byte[] concatenate = Arrays.concatenate(bArr2, bArr3);
            byte[] compressedADRS = compressedADRS(adrs);
            if (this.robust) {
                concatenate = bitmask256(Arrays.concatenate(bArr, compressedADRS), concatenate);
            }
            this.treeDigest.update(bArr, 0, bArr.length);
            this.treeDigest.update(this.padding, 0, 64 - this.f2507N);
            this.treeDigest.update(compressedADRS, 0, compressedADRS.length);
            this.treeDigest.update(concatenate, 0, concatenate.length);
            this.treeDigest.doFinal(this.digestBuf, 0);
            return Arrays.copyOfRange(this.digestBuf, 0, this.f2507N);
        }

        @Override // org.bouncycastle.pqc.crypto.sphincsplus.SPHINCSPlusEngine
        IndexedDigest H_msg(byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4) {
            int r0 = ((this.f2503A * this.f2506K) + 7) / 8;
            int r2 = this.f2505H / this.f2504D;
            int r3 = this.f2505H - r2;
            int r4 = (r2 + 7) / 8;
            int r5 = (r3 + 7) / 8;
            byte[] bArr5 = new byte[this.msgDigest.getDigestSize()];
            this.msgDigest.update(bArr, 0, bArr.length);
            this.msgDigest.update(bArr2, 0, bArr2.length);
            this.msgDigest.update(bArr3, 0, bArr3.length);
            this.msgDigest.update(bArr4, 0, bArr4.length);
            this.msgDigest.doFinal(bArr5, 0);
            byte[] bitmask = bitmask(Arrays.concatenate(bArr, bArr2, bArr5), new byte[r0 + r4 + r5]);
            byte[] bArr6 = new byte[8];
            System.arraycopy(bitmask, r0, bArr6, 8 - r5, r5);
            byte[] bArr7 = new byte[4];
            System.arraycopy(bitmask, r5 + r0, bArr7, 4 - r4, r4);
            return new IndexedDigest(Pack.bigEndianToLong(bArr6, 0) & ((-1) >>> (64 - r3)), Pack.bigEndianToInt(bArr7, 0) & ((-1) >>> (32 - r2)), Arrays.copyOfRange(bitmask, 0, r0));
        }

        @Override // org.bouncycastle.pqc.crypto.sphincsplus.SPHINCSPlusEngine
        byte[] PRF(byte[] bArr, ADRS adrs) {
            int length = bArr.length;
            this.treeDigest.update(bArr, 0, bArr.length);
            byte[] compressedADRS = compressedADRS(adrs);
            this.treeDigest.update(compressedADRS, 0, compressedADRS.length);
            this.treeDigest.doFinal(this.digestBuf, 0);
            return Arrays.copyOfRange(this.digestBuf, 0, length);
        }

        @Override // org.bouncycastle.pqc.crypto.sphincsplus.SPHINCSPlusEngine
        public byte[] PRF_msg(byte[] bArr, byte[] bArr2, byte[] bArr3) {
            this.treeHMac.init(new KeyParameter(bArr));
            this.treeHMac.update(bArr2, 0, bArr2.length);
            this.treeHMac.update(bArr3, 0, bArr3.length);
            this.treeHMac.doFinal(this.hmacBuf, 0);
            return Arrays.copyOfRange(this.hmacBuf, 0, this.f2507N);
        }

        @Override // org.bouncycastle.pqc.crypto.sphincsplus.SPHINCSPlusEngine
        public byte[] T_l(byte[] bArr, ADRS adrs, byte[] bArr2) {
            byte[] compressedADRS = compressedADRS(adrs);
            if (this.robust) {
                bArr2 = bitmask256(Arrays.concatenate(bArr, compressedADRS), bArr2);
            }
            this.treeDigest.update(bArr, 0, bArr.length);
            this.treeDigest.update(this.padding, 0, 64 - this.f2507N);
            this.treeDigest.update(compressedADRS, 0, compressedADRS.length);
            this.treeDigest.update(bArr2, 0, bArr2.length);
            this.treeDigest.doFinal(this.digestBuf, 0);
            return Arrays.copyOfRange(this.digestBuf, 0, this.f2507N);
        }

        protected byte[] bitmask(byte[] bArr, byte[] bArr2) {
            int length = bArr2.length;
            byte[] bArr3 = new byte[length];
            this.mgf1.init(new MGFParameters(bArr));
            this.mgf1.generateBytes(bArr3, 0, length);
            for (int r2 = 0; r2 < bArr2.length; r2++) {
                bArr3[r2] = (byte) (bArr3[r2] ^ bArr2[r2]);
            }
            return bArr3;
        }

        protected byte[] bitmask256(byte[] bArr, byte[] bArr2) {
            int length = bArr2.length;
            byte[] bArr3 = new byte[length];
            MGF1BytesGenerator mGF1BytesGenerator = new MGF1BytesGenerator(new SHA256Digest());
            mGF1BytesGenerator.init(new MGFParameters(bArr));
            mGF1BytesGenerator.generateBytes(bArr3, 0, length);
            for (int r5 = 0; r5 < bArr2.length; r5++) {
                bArr3[r5] = (byte) (bArr3[r5] ^ bArr2[r5]);
            }
            return bArr3;
        }
    }

    /* loaded from: classes3.dex */
    static class Shake256Engine extends SPHINCSPlusEngine {
        private final Xof treeDigest;

        public Shake256Engine(boolean z, int r2, int r3, int r4, int r5, int r6, int r7) {
            super(z, r2, r3, r4, r5, r6, r7);
            this.treeDigest = new SHAKEDigest(256);
        }

        @Override // org.bouncycastle.pqc.crypto.sphincsplus.SPHINCSPlusEngine
        /* renamed from: F */
        byte[] mo8F(byte[] bArr, ADRS adrs, byte[] bArr2) {
            if (this.robust) {
                bArr2 = bitmask(bArr, adrs, bArr2);
            }
            int r0 = this.f2507N;
            byte[] bArr3 = new byte[r0];
            this.treeDigest.update(bArr, 0, bArr.length);
            this.treeDigest.update(adrs.value, 0, adrs.value.length);
            this.treeDigest.update(bArr2, 0, bArr2.length);
            this.treeDigest.doFinal(bArr3, 0, r0);
            return bArr3;
        }

        @Override // org.bouncycastle.pqc.crypto.sphincsplus.SPHINCSPlusEngine
        /* renamed from: H */
        byte[] mo7H(byte[] bArr, ADRS adrs, byte[] bArr2, byte[] bArr3) {
            byte[] concatenate = Arrays.concatenate(bArr2, bArr3);
            if (this.robust) {
                concatenate = bitmask(bArr, adrs, concatenate);
            }
            int r8 = this.f2507N;
            byte[] bArr4 = new byte[r8];
            this.treeDigest.update(bArr, 0, bArr.length);
            this.treeDigest.update(adrs.value, 0, adrs.value.length);
            this.treeDigest.update(concatenate, 0, concatenate.length);
            this.treeDigest.doFinal(bArr4, 0, r8);
            return bArr4;
        }

        @Override // org.bouncycastle.pqc.crypto.sphincsplus.SPHINCSPlusEngine
        IndexedDigest H_msg(byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4) {
            int r0 = ((this.f2503A * this.f2506K) + 7) / 8;
            int r2 = this.f2505H / this.f2504D;
            int r3 = this.f2505H - r2;
            int r4 = (r2 + 7) / 8;
            int r5 = (r3 + 7) / 8;
            int r6 = r0 + r4 + r5;
            byte[] bArr5 = new byte[r6];
            this.treeDigest.update(bArr, 0, bArr.length);
            this.treeDigest.update(bArr2, 0, bArr2.length);
            this.treeDigest.update(bArr3, 0, bArr3.length);
            this.treeDigest.update(bArr4, 0, bArr4.length);
            this.treeDigest.doFinal(bArr5, 0, r6);
            byte[] bArr6 = new byte[8];
            System.arraycopy(bArr5, r0, bArr6, 8 - r5, r5);
            long bigEndianToLong = Pack.bigEndianToLong(bArr6, 0) & ((-1) >>> (64 - r3));
            byte[] bArr7 = new byte[4];
            System.arraycopy(bArr5, r5 + r0, bArr7, 4 - r4, r4);
            return new IndexedDigest(bigEndianToLong, Pack.bigEndianToInt(bArr7, 0) & ((-1) >>> (32 - r2)), Arrays.copyOfRange(bArr5, 0, r0));
        }

        @Override // org.bouncycastle.pqc.crypto.sphincsplus.SPHINCSPlusEngine
        byte[] PRF(byte[] bArr, ADRS adrs) {
            this.treeDigest.update(bArr, 0, bArr.length);
            this.treeDigest.update(adrs.value, 0, adrs.value.length);
            byte[] bArr2 = new byte[this.f2507N];
            this.treeDigest.doFinal(bArr2, 0, this.f2507N);
            return bArr2;
        }

        @Override // org.bouncycastle.pqc.crypto.sphincsplus.SPHINCSPlusEngine
        public byte[] PRF_msg(byte[] bArr, byte[] bArr2, byte[] bArr3) {
            this.treeDigest.update(bArr, 0, bArr.length);
            this.treeDigest.update(bArr2, 0, bArr2.length);
            this.treeDigest.update(bArr3, 0, bArr3.length);
            int r4 = this.f2507N;
            byte[] bArr4 = new byte[r4];
            this.treeDigest.doFinal(bArr4, 0, r4);
            return bArr4;
        }

        @Override // org.bouncycastle.pqc.crypto.sphincsplus.SPHINCSPlusEngine
        byte[] T_l(byte[] bArr, ADRS adrs, byte[] bArr2) {
            if (this.robust) {
                bArr2 = bitmask(bArr, adrs, bArr2);
            }
            int r0 = this.f2507N;
            byte[] bArr3 = new byte[r0];
            this.treeDigest.update(bArr, 0, bArr.length);
            this.treeDigest.update(adrs.value, 0, adrs.value.length);
            this.treeDigest.update(bArr2, 0, bArr2.length);
            this.treeDigest.doFinal(bArr3, 0, r0);
            return bArr3;
        }

        protected byte[] bitmask(byte[] bArr, ADRS adrs, byte[] bArr2) {
            int length = bArr2.length;
            byte[] bArr3 = new byte[length];
            this.treeDigest.update(bArr, 0, bArr.length);
            this.treeDigest.update(adrs.value, 0, adrs.value.length);
            this.treeDigest.doFinal(bArr3, 0, length);
            for (int r4 = 0; r4 < bArr2.length; r4++) {
                bArr3[r4] = (byte) (bArr3[r4] ^ bArr2[r4]);
            }
            return bArr3;
        }
    }

    public SPHINCSPlusEngine(boolean z, int r9, int r10, int r11, int r12, int r13, int r14) {
        this.f2507N = r9;
        if (r10 == 16) {
            this.WOTS_LOGW = 4;
            this.WOTS_LEN1 = (r9 * 8) / 4;
            if (r9 > 8) {
                if (r9 <= 136) {
                    this.WOTS_LEN2 = 3;
                } else if (r9 > 256) {
                    throw new IllegalArgumentException("cannot precompute SPX_WOTS_LEN2 for n outside {2, .., 256}");
                } else {
                    this.WOTS_LEN2 = 4;
                }
                this.WOTS_W = r10;
                this.WOTS_LEN = this.WOTS_LEN1 + this.WOTS_LEN2;
                this.robust = z;
                this.f2504D = r11;
                this.f2503A = r12;
                this.f2506K = r13;
                this.f2505H = r14;
                this.H_PRIME = r14 / r11;
                this.f2508T = 1 << r12;
            }
            this.WOTS_LEN2 = 2;
            this.WOTS_W = r10;
            this.WOTS_LEN = this.WOTS_LEN1 + this.WOTS_LEN2;
            this.robust = z;
            this.f2504D = r11;
            this.f2503A = r12;
            this.f2506K = r13;
            this.f2505H = r14;
            this.H_PRIME = r14 / r11;
            this.f2508T = 1 << r12;
        } else if (r10 != 256) {
            throw new IllegalArgumentException("wots_w assumed 16 or 256");
        } else {
            this.WOTS_LOGW = 8;
            this.WOTS_LEN1 = (r9 * 8) / 8;
            if (r9 <= 1) {
                this.WOTS_LEN2 = 1;
                this.WOTS_W = r10;
                this.WOTS_LEN = this.WOTS_LEN1 + this.WOTS_LEN2;
                this.robust = z;
                this.f2504D = r11;
                this.f2503A = r12;
                this.f2506K = r13;
                this.f2505H = r14;
                this.H_PRIME = r14 / r11;
                this.f2508T = 1 << r12;
            }
            if (r9 > 256) {
                throw new IllegalArgumentException("cannot precompute SPX_WOTS_LEN2 for n outside {2, .., 256}");
            }
            this.WOTS_LEN2 = 2;
            this.WOTS_W = r10;
            this.WOTS_LEN = this.WOTS_LEN1 + this.WOTS_LEN2;
            this.robust = z;
            this.f2504D = r11;
            this.f2503A = r12;
            this.f2506K = r13;
            this.f2505H = r14;
            this.H_PRIME = r14 / r11;
            this.f2508T = 1 << r12;
        }
    }

    protected static byte[] xor(byte[] bArr, byte[] bArr2) {
        byte[] clone = Arrays.clone(bArr);
        for (int r1 = 0; r1 < bArr.length; r1++) {
            clone[r1] = (byte) (clone[r1] ^ bArr2[r1]);
        }
        return clone;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: F */
    public abstract byte[] mo8F(byte[] bArr, ADRS adrs, byte[] bArr2);

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: H */
    public abstract byte[] mo7H(byte[] bArr, ADRS adrs, byte[] bArr2, byte[] bArr3);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract IndexedDigest H_msg(byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract byte[] PRF(byte[] bArr, ADRS adrs);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract byte[] PRF_msg(byte[] bArr, byte[] bArr2, byte[] bArr3);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract byte[] T_l(byte[] bArr, ADRS adrs, byte[] bArr2);
}
