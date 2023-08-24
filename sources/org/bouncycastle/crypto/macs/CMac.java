package org.bouncycastle.crypto.macs;

import ai.api.util.VoiceActivityDetector;
import com.facebook.imagepipeline.memory.BitmapCounterConfig;
import com.google.android.exoplayer2.extractor.p011ts.TsExtractor;
import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.Mac;
import org.bouncycastle.crypto.modes.CBCBlockCipher;
import org.bouncycastle.crypto.paddings.ISO7816d4Padding;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.util.Pack;

/* loaded from: classes5.dex */
public class CMac implements Mac {

    /* renamed from: Lu */
    private byte[] f2004Lu;
    private byte[] Lu2;
    private byte[] ZEROES;
    private byte[] buf;
    private int bufOff;
    private BlockCipher cipher;
    private byte[] mac;
    private int macSize;
    private byte[] poly;

    public CMac(BlockCipher blockCipher) {
        this(blockCipher, blockCipher.getBlockSize() * 8);
    }

    public CMac(BlockCipher blockCipher, int r4) {
        if (r4 % 8 != 0) {
            throw new IllegalArgumentException("MAC size must be multiple of 8");
        }
        if (r4 > blockCipher.getBlockSize() * 8) {
            throw new IllegalArgumentException("MAC size must be less or equal to " + (blockCipher.getBlockSize() * 8));
        }
        this.cipher = new CBCBlockCipher(blockCipher);
        this.macSize = r4 / 8;
        this.poly = lookupPoly(blockCipher.getBlockSize());
        this.mac = new byte[blockCipher.getBlockSize()];
        this.buf = new byte[blockCipher.getBlockSize()];
        this.ZEROES = new byte[blockCipher.getBlockSize()];
        this.bufOff = 0;
    }

    private byte[] doubleLu(byte[] bArr) {
        byte[] bArr2 = new byte[bArr.length];
        int r1 = (-shiftLeft(bArr, bArr2)) & 255;
        int length = bArr.length - 3;
        byte b = bArr2[length];
        byte[] bArr3 = this.poly;
        bArr2[length] = (byte) (b ^ (bArr3[1] & r1));
        int length2 = bArr.length - 2;
        bArr2[length2] = (byte) ((bArr3[2] & r1) ^ bArr2[length2]);
        int length3 = bArr.length - 1;
        bArr2[length3] = (byte) ((r1 & bArr3[3]) ^ bArr2[length3]);
        return bArr2;
    }

    private static byte[] lookupPoly(int r3) {
        int r32 = r3 * 8;
        int r0 = TsExtractor.TS_STREAM_TYPE_E_AC3;
        switch (r32) {
            case 64:
            case VoiceActivityDetector.FRAME_SIZE_IN_BYTES /* 320 */:
                r0 = 27;
                break;
            case 128:
            case 192:
                break;
            case 160:
                r0 = 45;
                break;
            case 224:
                r0 = 777;
                break;
            case 256:
                r0 = 1061;
                break;
            case BitmapCounterConfig.DEFAULT_MAX_BITMAP_COUNT /* 384 */:
                r0 = 4109;
                break;
            case 448:
                r0 = 2129;
                break;
            case 512:
                r0 = 293;
                break;
            case 768:
                r0 = 655377;
                break;
            case 1024:
                r0 = 524355;
                break;
            case 2048:
                r0 = 548865;
                break;
            default:
                throw new IllegalArgumentException("Unknown block size for CMAC: " + r32);
        }
        return Pack.intToBigEndian(r0);
    }

    private static int shiftLeft(byte[] bArr, byte[] bArr2) {
        int length = bArr.length;
        int r1 = 0;
        while (true) {
            length--;
            if (length < 0) {
                return r1;
            }
            int r2 = bArr[length] & 255;
            bArr2[length] = (byte) (r1 | (r2 << 1));
            r1 = (r2 >>> 7) & 1;
        }
    }

    @Override // org.bouncycastle.crypto.Mac
    public int doFinal(byte[] bArr, int r8) {
        byte[] bArr2;
        if (this.bufOff == this.cipher.getBlockSize()) {
            bArr2 = this.f2004Lu;
        } else {
            new ISO7816d4Padding().addPadding(this.buf, this.bufOff);
            bArr2 = this.Lu2;
        }
        int r2 = 0;
        while (true) {
            byte[] bArr3 = this.mac;
            if (r2 >= bArr3.length) {
                this.cipher.processBlock(this.buf, 0, bArr3, 0);
                System.arraycopy(this.mac, 0, bArr, r8, this.macSize);
                reset();
                return this.macSize;
            }
            byte[] bArr4 = this.buf;
            bArr4[r2] = (byte) (bArr4[r2] ^ bArr2[r2]);
            r2++;
        }
    }

    @Override // org.bouncycastle.crypto.Mac
    public String getAlgorithmName() {
        return this.cipher.getAlgorithmName();
    }

    @Override // org.bouncycastle.crypto.Mac
    public int getMacSize() {
        return this.macSize;
    }

    @Override // org.bouncycastle.crypto.Mac
    public void init(CipherParameters cipherParameters) {
        validate(cipherParameters);
        this.cipher.init(true, cipherParameters);
        byte[] bArr = this.ZEROES;
        byte[] bArr2 = new byte[bArr.length];
        this.cipher.processBlock(bArr, 0, bArr2, 0);
        byte[] doubleLu = doubleLu(bArr2);
        this.f2004Lu = doubleLu;
        this.Lu2 = doubleLu(doubleLu);
        reset();
    }

    @Override // org.bouncycastle.crypto.Mac
    public void reset() {
        int r1 = 0;
        while (true) {
            byte[] bArr = this.buf;
            if (r1 >= bArr.length) {
                this.bufOff = 0;
                this.cipher.reset();
                return;
            }
            bArr[r1] = 0;
            r1++;
        }
    }

    @Override // org.bouncycastle.crypto.Mac
    public void update(byte b) {
        int r0 = this.bufOff;
        byte[] bArr = this.buf;
        if (r0 == bArr.length) {
            this.cipher.processBlock(bArr, 0, this.mac, 0);
            this.bufOff = 0;
        }
        byte[] bArr2 = this.buf;
        int r1 = this.bufOff;
        this.bufOff = r1 + 1;
        bArr2[r1] = b;
    }

    @Override // org.bouncycastle.crypto.Mac
    public void update(byte[] bArr, int r8, int r9) {
        if (r9 < 0) {
            throw new IllegalArgumentException("Can't have a negative input length!");
        }
        int blockSize = this.cipher.getBlockSize();
        int r1 = this.bufOff;
        int r2 = blockSize - r1;
        if (r9 > r2) {
            System.arraycopy(bArr, r8, this.buf, r1, r2);
            this.cipher.processBlock(this.buf, 0, this.mac, 0);
            this.bufOff = 0;
            r9 -= r2;
            r8 += r2;
            while (r9 > blockSize) {
                this.cipher.processBlock(bArr, r8, this.mac, 0);
                r9 -= blockSize;
                r8 += blockSize;
            }
        }
        System.arraycopy(bArr, r8, this.buf, this.bufOff, r9);
        this.bufOff += r9;
    }

    void validate(CipherParameters cipherParameters) {
        if (cipherParameters != null && !(cipherParameters instanceof KeyParameter)) {
            throw new IllegalArgumentException("CMac mode only permits key to be set.");
        }
    }
}
