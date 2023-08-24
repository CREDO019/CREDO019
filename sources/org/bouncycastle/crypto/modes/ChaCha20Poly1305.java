package org.bouncycastle.crypto.modes;

import java.util.Objects;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.Mac;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.engines.ChaCha7539Engine;
import org.bouncycastle.crypto.macs.Poly1305;
import org.bouncycastle.crypto.params.AEADParameters;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithIV;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Pack;

/* loaded from: classes5.dex */
public class ChaCha20Poly1305 implements AEADCipher {
    private static final long AAD_LIMIT = -1;
    private static final int BUF_SIZE = 64;
    private static final long DATA_LIMIT = 274877906880L;
    private static final int KEY_SIZE = 32;
    private static final int MAC_SIZE = 16;
    private static final int NONCE_SIZE = 12;
    private static final byte[] ZEROES = new byte[15];
    private long aadCount;
    private final byte[] buf;
    private int bufPos;
    private final ChaCha7539Engine chacha20;
    private long dataCount;
    private byte[] initialAAD;
    private final byte[] key;
    private final byte[] mac;
    private final byte[] nonce;
    private final Mac poly1305;
    private int state;

    /* loaded from: classes5.dex */
    private static final class State {
        static final int DEC_AAD = 6;
        static final int DEC_DATA = 7;
        static final int DEC_FINAL = 8;
        static final int DEC_INIT = 5;
        static final int ENC_AAD = 2;
        static final int ENC_DATA = 3;
        static final int ENC_FINAL = 4;
        static final int ENC_INIT = 1;
        static final int UNINITIALIZED = 0;

        private State() {
        }
    }

    public ChaCha20Poly1305() {
        this(new Poly1305());
    }

    public ChaCha20Poly1305(Mac mac) {
        this.key = new byte[32];
        this.nonce = new byte[12];
        this.buf = new byte[80];
        this.mac = new byte[16];
        this.state = 0;
        Objects.requireNonNull(mac, "'poly1305' cannot be null");
        if (16 != mac.getMacSize()) {
            throw new IllegalArgumentException("'poly1305' must be a 128-bit MAC");
        }
        this.chacha20 = new ChaCha7539Engine();
        this.poly1305 = mac;
    }

    private void checkAAD() {
        int r0 = this.state;
        int r2 = 2;
        if (r0 != 1) {
            if (r0 == 2) {
                return;
            }
            if (r0 == 4) {
                throw new IllegalStateException("ChaCha20Poly1305 cannot be reused for encryption");
            }
            r2 = 6;
            if (r0 != 5) {
                if (r0 != 6) {
                    throw new IllegalStateException();
                }
                return;
            }
        }
        this.state = r2;
    }

    private void checkData() {
        int r0;
        switch (this.state) {
            case 1:
            case 2:
                r0 = 3;
                break;
            case 3:
            case 7:
                return;
            case 4:
                throw new IllegalStateException("ChaCha20Poly1305 cannot be reused for encryption");
            case 5:
            case 6:
                r0 = 7;
                break;
            default:
                throw new IllegalStateException();
        }
        finishAAD(r0);
    }

    private void finishAAD(int r3) {
        padMAC(this.aadCount);
        this.state = r3;
    }

    private void finishData(int r7) {
        padMAC(this.dataCount);
        byte[] bArr = new byte[16];
        Pack.longToLittleEndian(this.aadCount, bArr, 0);
        Pack.longToLittleEndian(this.dataCount, bArr, 8);
        this.poly1305.update(bArr, 0, 16);
        this.poly1305.doFinal(this.mac, 0);
        this.state = r7;
    }

    private long incrementCount(long j, int r9, long j2) {
        long j3 = r9;
        if (j - Long.MIN_VALUE <= (j2 - j3) - Long.MIN_VALUE) {
            return j + j3;
        }
        throw new IllegalStateException("Limit exceeded");
    }

    private void initMAC() {
        byte[] bArr = new byte[64];
        try {
            this.chacha20.processBytes(bArr, 0, 64, bArr, 0);
            this.poly1305.init(new KeyParameter(bArr, 0, 32));
        } finally {
            Arrays.clear(bArr);
        }
    }

    private void padMAC(long j) {
        int r3 = ((int) j) & 15;
        if (r3 != 0) {
            this.poly1305.update(ZEROES, 0, 16 - r3);
        }
    }

    private void processData(byte[] bArr, int r10, int r11, byte[] bArr2, int r13) {
        if (r13 > bArr2.length - r11) {
            throw new OutputLengthException("Output buffer too short");
        }
        this.chacha20.processBytes(bArr, r10, r11, bArr2, r13);
        this.dataCount = incrementCount(this.dataCount, r11, DATA_LIMIT);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private void reset(boolean z, boolean z2) {
        Arrays.clear(this.buf);
        if (z) {
            Arrays.clear(this.mac);
        }
        this.aadCount = 0L;
        this.dataCount = 0L;
        this.bufPos = 0;
        switch (this.state) {
            case 1:
            case 5:
                break;
            case 2:
            case 3:
            case 4:
                this.state = 4;
                return;
            case 6:
            case 7:
            case 8:
                this.state = 5;
                break;
            default:
                throw new IllegalStateException();
        }
        if (z2) {
            this.chacha20.reset();
        }
        initMAC();
        byte[] bArr = this.initialAAD;
        if (bArr != null) {
            processAADBytes(bArr, 0, bArr.length);
        }
    }

    @Override // org.bouncycastle.crypto.modes.AEADCipher
    public int doFinal(byte[] bArr, int r13) throws IllegalStateException, InvalidCipherTextException {
        int r0;
        Objects.requireNonNull(bArr, "'out' cannot be null");
        if (r13 >= 0) {
            checkData();
            Arrays.clear(this.mac);
            int r02 = this.state;
            if (r02 == 3) {
                int r8 = this.bufPos;
                r0 = r8 + 16;
                if (r13 > bArr.length - r0) {
                    throw new OutputLengthException("Output buffer too short");
                }
                if (r8 > 0) {
                    processData(this.buf, 0, r8, bArr, r13);
                    this.poly1305.update(bArr, r13, this.bufPos);
                }
                finishData(4);
                System.arraycopy(this.mac, 0, bArr, r13 + this.bufPos, 16);
            } else if (r02 != 7) {
                throw new IllegalStateException();
            } else {
                int r03 = this.bufPos;
                if (r03 < 16) {
                    throw new InvalidCipherTextException("data too short");
                }
                r0 = r03 - 16;
                if (r13 > bArr.length - r0) {
                    throw new OutputLengthException("Output buffer too short");
                }
                if (r0 > 0) {
                    this.poly1305.update(this.buf, 0, r0);
                    processData(this.buf, 0, r0, bArr, r13);
                }
                finishData(8);
                if (!Arrays.constantTimeAreEqual(16, this.mac, 0, this.buf, r0)) {
                    throw new InvalidCipherTextException("mac check in ChaCha20Poly1305 failed");
                }
            }
            reset(false, true);
            return r0;
        }
        throw new IllegalArgumentException("'outOff' cannot be negative");
    }

    @Override // org.bouncycastle.crypto.modes.AEADCipher
    public String getAlgorithmName() {
        return "ChaCha20Poly1305";
    }

    @Override // org.bouncycastle.crypto.modes.AEADCipher
    public byte[] getMac() {
        return Arrays.clone(this.mac);
    }

    @Override // org.bouncycastle.crypto.modes.AEADCipher
    public int getOutputSize(int r4) {
        int max = Math.max(0, r4) + this.bufPos;
        int r1 = this.state;
        if (r1 == 1 || r1 == 2 || r1 == 3) {
            return max + 16;
        }
        if (r1 == 5 || r1 == 6 || r1 == 7) {
            return Math.max(0, max - 16);
        }
        throw new IllegalStateException();
    }

    @Override // org.bouncycastle.crypto.modes.AEADCipher
    public int getUpdateOutputSize(int r4) {
        int max = Math.max(0, r4) + this.bufPos;
        int r1 = this.state;
        if (r1 != 1 && r1 != 2 && r1 != 3) {
            if (r1 != 5 && r1 != 6 && r1 != 7) {
                throw new IllegalStateException();
            }
            max = Math.max(0, max - 16);
        }
        return max - (max % 64);
    }

    @Override // org.bouncycastle.crypto.modes.AEADCipher
    public void init(boolean z, CipherParameters cipherParameters) throws IllegalArgumentException {
        ParametersWithIV parametersWithIV;
        KeyParameter keyParameter;
        byte[] bArr;
        if (cipherParameters instanceof AEADParameters) {
            AEADParameters aEADParameters = (AEADParameters) cipherParameters;
            int macSize = aEADParameters.getMacSize();
            if (128 != macSize) {
                throw new IllegalArgumentException("Invalid value for MAC size: " + macSize);
            }
            keyParameter = aEADParameters.getKey();
            bArr = aEADParameters.getNonce();
            parametersWithIV = new ParametersWithIV(keyParameter, bArr);
            this.initialAAD = aEADParameters.getAssociatedText();
        } else if (!(cipherParameters instanceof ParametersWithIV)) {
            throw new IllegalArgumentException("invalid parameters passed to ChaCha20Poly1305");
        } else {
            parametersWithIV = (ParametersWithIV) cipherParameters;
            keyParameter = (KeyParameter) parametersWithIV.getParameters();
            bArr = parametersWithIV.getIV();
            this.initialAAD = null;
        }
        if (keyParameter == null) {
            if (this.state == 0) {
                throw new IllegalArgumentException("Key must be specified in initial init");
            }
        } else if (32 != keyParameter.getKey().length) {
            throw new IllegalArgumentException("Key must be 256 bits");
        }
        if (bArr == null || 12 != bArr.length) {
            throw new IllegalArgumentException("Nonce must be 96 bits");
        }
        if (this.state != 0 && z && Arrays.areEqual(this.nonce, bArr) && (keyParameter == null || Arrays.areEqual(this.key, keyParameter.getKey()))) {
            throw new IllegalArgumentException("cannot reuse nonce for ChaCha20Poly1305 encryption");
        }
        if (keyParameter != null) {
            System.arraycopy(keyParameter.getKey(), 0, this.key, 0, 32);
        }
        System.arraycopy(bArr, 0, this.nonce, 0, 12);
        this.chacha20.init(true, parametersWithIV);
        this.state = z ? 1 : 5;
        reset(true, false);
    }

    @Override // org.bouncycastle.crypto.modes.AEADCipher
    public void processAADByte(byte b) {
        checkAAD();
        this.aadCount = incrementCount(this.aadCount, 1, -1L);
        this.poly1305.update(b);
    }

    @Override // org.bouncycastle.crypto.modes.AEADCipher
    public void processAADBytes(byte[] bArr, int r9, int r10) {
        Objects.requireNonNull(bArr, "'in' cannot be null");
        if (r9 < 0) {
            throw new IllegalArgumentException("'inOff' cannot be negative");
        }
        if (r10 < 0) {
            throw new IllegalArgumentException("'len' cannot be negative");
        }
        if (r9 > bArr.length - r10) {
            throw new DataLengthException("Input buffer too short");
        }
        checkAAD();
        if (r10 > 0) {
            this.aadCount = incrementCount(this.aadCount, r10, -1L);
            this.poly1305.update(bArr, r9, r10);
        }
    }

    @Override // org.bouncycastle.crypto.modes.AEADCipher
    public int processByte(byte b, byte[] bArr, int r13) throws DataLengthException {
        checkData();
        int r0 = this.state;
        if (r0 == 3) {
            byte[] bArr2 = this.buf;
            int r02 = this.bufPos;
            bArr2[r02] = b;
            int r03 = r02 + 1;
            this.bufPos = r03;
            if (r03 == 64) {
                processData(bArr2, 0, 64, bArr, r13);
                this.poly1305.update(bArr, r13, 64);
                this.bufPos = 0;
                return 64;
            }
            return 0;
        } else if (r0 == 7) {
            byte[] bArr3 = this.buf;
            int r1 = this.bufPos;
            bArr3[r1] = b;
            int r12 = r1 + 1;
            this.bufPos = r12;
            if (r12 == bArr3.length) {
                this.poly1305.update(bArr3, 0, 64);
                processData(this.buf, 0, 64, bArr, r13);
                byte[] bArr4 = this.buf;
                System.arraycopy(bArr4, 64, bArr4, 0, 16);
                this.bufPos = 16;
                return 64;
            }
            return 0;
        } else {
            throw new IllegalStateException();
        }
    }

    @Override // org.bouncycastle.crypto.modes.AEADCipher
    public int processBytes(byte[] bArr, int r18, int r19, byte[] bArr2, int r21) throws DataLengthException {
        int r15;
        int r8 = r18;
        int r9 = r19;
        Objects.requireNonNull(bArr, "'in' cannot be null");
        if (r8 >= 0) {
            if (r9 >= 0) {
                if (r8 <= bArr.length - r9) {
                    if (r21 >= 0) {
                        checkData();
                        int r0 = this.state;
                        if (r0 == 3) {
                            if (this.bufPos != 0) {
                                while (r9 > 0) {
                                    r9--;
                                    byte[] bArr3 = this.buf;
                                    int r02 = this.bufPos;
                                    int r14 = r8 + 1;
                                    bArr3[r02] = bArr[r8];
                                    int r03 = r02 + 1;
                                    this.bufPos = r03;
                                    if (r03 == 64) {
                                        processData(bArr3, 0, 64, bArr2, r21);
                                        this.poly1305.update(bArr2, r21, 64);
                                        this.bufPos = 0;
                                        r8 = r14;
                                        r15 = 64;
                                        break;
                                    }
                                    r8 = r14;
                                }
                            }
                            r15 = 0;
                            while (r9 >= 64) {
                                int r142 = r21 + r15;
                                processData(bArr, r8, 64, bArr2, r142);
                                this.poly1305.update(bArr2, r142, 64);
                                r8 += 64;
                                r9 -= 64;
                                r15 += 64;
                            }
                            if (r9 > 0) {
                                System.arraycopy(bArr, r8, this.buf, 0, r9);
                                this.bufPos = r9;
                            }
                        } else if (r0 != 7) {
                            throw new IllegalStateException();
                        } else {
                            r15 = 0;
                            for (int r143 = 0; r143 < r9; r143++) {
                                byte[] bArr4 = this.buf;
                                int r1 = this.bufPos;
                                bArr4[r1] = bArr[r8 + r143];
                                int r12 = r1 + 1;
                                this.bufPos = r12;
                                if (r12 == bArr4.length) {
                                    this.poly1305.update(bArr4, 0, 64);
                                    processData(this.buf, 0, 64, bArr2, r21 + r15);
                                    byte[] bArr5 = this.buf;
                                    System.arraycopy(bArr5, 64, bArr5, 0, 16);
                                    this.bufPos = 16;
                                    r15 += 64;
                                }
                            }
                        }
                        return r15;
                    }
                    throw new IllegalArgumentException("'outOff' cannot be negative");
                }
                throw new DataLengthException("Input buffer too short");
            }
            throw new IllegalArgumentException("'len' cannot be negative");
        }
        throw new IllegalArgumentException("'inOff' cannot be negative");
    }

    @Override // org.bouncycastle.crypto.modes.AEADCipher
    public void reset() {
        reset(true, true);
    }
}
