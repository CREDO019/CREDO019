package org.bouncycastle.crypto.engines;

import androidx.core.view.MotionEventCompat;
import androidx.core.view.ViewCompat;
import com.google.common.base.Ascii;
import java.util.Enumeration;
import java.util.Hashtable;
import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithSBox;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Strings;

/* loaded from: classes5.dex */
public class GOST28147Engine implements BlockCipher {
    protected static final int BLOCK_SIZE = 8;
    private boolean forEncryption;
    private static byte[] Sbox_Default = {4, 10, 9, 2, 13, 8, 0, Ascii.f1129SO, 6, Ascii.f1132VT, 1, Ascii.f1121FF, 7, Ascii.f1128SI, 5, 3, Ascii.f1129SO, Ascii.f1132VT, 4, Ascii.f1121FF, 6, 13, Ascii.f1128SI, 10, 2, 3, 8, 1, 0, 7, 5, 9, 5, 8, 1, 13, 10, 3, 4, 2, Ascii.f1129SO, Ascii.f1128SI, Ascii.f1121FF, 7, 6, 0, 9, Ascii.f1132VT, 7, 13, 10, 1, 0, 8, 9, Ascii.f1128SI, Ascii.f1129SO, 4, 6, Ascii.f1121FF, Ascii.f1132VT, 2, 5, 3, 6, Ascii.f1121FF, 7, 1, 5, Ascii.f1128SI, 13, 8, 4, 10, 9, Ascii.f1129SO, 0, 3, Ascii.f1132VT, 2, 4, Ascii.f1132VT, 10, 0, 7, 2, 1, 13, 3, 6, 8, 5, 9, Ascii.f1121FF, Ascii.f1128SI, Ascii.f1129SO, 13, Ascii.f1132VT, 4, 1, 3, Ascii.f1128SI, 5, 9, 0, 10, Ascii.f1129SO, 7, 6, 8, 2, Ascii.f1121FF, 1, Ascii.f1128SI, 13, 0, 5, 7, 10, 4, 9, 2, 3, Ascii.f1129SO, 6, Ascii.f1132VT, 8, Ascii.f1121FF};
    private static byte[] ESbox_Test = {4, 2, Ascii.f1128SI, 5, 9, 1, 0, 8, Ascii.f1129SO, 3, Ascii.f1132VT, Ascii.f1121FF, 13, 7, 10, 6, Ascii.f1121FF, 9, Ascii.f1128SI, Ascii.f1129SO, 8, 1, 3, 10, 2, 7, 4, 13, 6, 0, Ascii.f1132VT, 5, 13, 8, Ascii.f1129SO, Ascii.f1121FF, 7, 3, 9, 10, 1, 5, 2, 4, 6, Ascii.f1128SI, 0, Ascii.f1132VT, Ascii.f1129SO, 9, Ascii.f1132VT, 2, 5, Ascii.f1128SI, 7, 1, 0, 13, Ascii.f1121FF, 6, 10, 4, 3, 8, 3, Ascii.f1129SO, 5, 9, 6, 8, 0, 13, 10, Ascii.f1132VT, 7, Ascii.f1121FF, 2, 1, Ascii.f1128SI, 4, 8, Ascii.f1128SI, 6, Ascii.f1132VT, 1, 9, Ascii.f1121FF, 5, 13, 3, 7, 10, 0, Ascii.f1129SO, 2, 4, 9, Ascii.f1132VT, Ascii.f1121FF, 0, 3, 6, 7, 5, 4, 8, Ascii.f1129SO, Ascii.f1128SI, 1, 10, 2, 13, Ascii.f1121FF, 6, 5, 2, Ascii.f1132VT, 0, 9, 13, 3, Ascii.f1129SO, 7, 10, Ascii.f1128SI, 4, 1, 8};
    private static byte[] ESbox_A = {9, 6, 3, 2, 8, Ascii.f1132VT, 1, 7, 10, 4, Ascii.f1129SO, Ascii.f1128SI, Ascii.f1121FF, 0, 13, 5, 3, 7, Ascii.f1129SO, 9, 8, 10, Ascii.f1128SI, 0, 5, 2, 6, Ascii.f1121FF, Ascii.f1132VT, 4, 13, 1, Ascii.f1129SO, 4, 6, 2, Ascii.f1132VT, 3, 13, 8, Ascii.f1121FF, Ascii.f1128SI, 5, 10, 0, 7, 1, 9, Ascii.f1129SO, 7, 10, Ascii.f1121FF, 13, 1, 3, 9, 0, 2, Ascii.f1132VT, 4, Ascii.f1128SI, 8, 5, 6, Ascii.f1132VT, 5, 1, 9, 8, 13, Ascii.f1128SI, 0, Ascii.f1129SO, 4, 2, 3, Ascii.f1121FF, 7, 10, 6, 3, 10, 13, Ascii.f1121FF, 1, 2, 0, Ascii.f1132VT, 7, 5, 9, 4, 8, Ascii.f1128SI, Ascii.f1129SO, 6, 1, 13, 2, 9, 7, 10, 6, 0, 8, Ascii.f1121FF, 4, 5, Ascii.f1128SI, 3, Ascii.f1132VT, Ascii.f1129SO, Ascii.f1132VT, 10, Ascii.f1128SI, 5, 0, Ascii.f1121FF, Ascii.f1129SO, 8, 6, 2, 3, 9, 1, 7, 13, 4};
    private static byte[] ESbox_B = {8, 4, Ascii.f1132VT, 1, 3, 5, 0, 9, 2, Ascii.f1129SO, 10, Ascii.f1121FF, 13, 6, 7, Ascii.f1128SI, 0, 1, 2, 10, 4, 13, 5, Ascii.f1121FF, 9, 7, 3, Ascii.f1128SI, Ascii.f1132VT, 8, 6, Ascii.f1129SO, Ascii.f1129SO, Ascii.f1121FF, 0, 10, 9, 2, 13, Ascii.f1132VT, 7, 5, 8, Ascii.f1128SI, 3, 6, 1, 4, 7, 5, 0, 13, Ascii.f1132VT, 6, 1, 2, 3, 10, Ascii.f1121FF, Ascii.f1128SI, 4, Ascii.f1129SO, 9, 8, 2, 7, Ascii.f1121FF, Ascii.f1128SI, 9, 5, 10, Ascii.f1132VT, 1, 4, 0, 13, 6, 8, Ascii.f1129SO, 3, 8, 3, 2, 6, 4, 13, Ascii.f1129SO, Ascii.f1132VT, Ascii.f1121FF, 1, 7, Ascii.f1128SI, 10, 0, 9, 5, 5, 2, 10, Ascii.f1132VT, 9, 1, Ascii.f1121FF, 3, 7, 4, 13, 0, 6, Ascii.f1128SI, 8, Ascii.f1129SO, 0, 4, Ascii.f1132VT, Ascii.f1129SO, 8, 3, 7, 1, 10, 2, 9, 6, Ascii.f1128SI, 13, 5, Ascii.f1121FF};
    private static byte[] ESbox_C = {1, Ascii.f1132VT, Ascii.f1121FF, 2, 9, 13, 0, Ascii.f1128SI, 4, 5, 8, Ascii.f1129SO, 10, 7, 6, 3, 0, 1, 7, 13, Ascii.f1132VT, 4, 5, 2, 8, Ascii.f1129SO, Ascii.f1128SI, Ascii.f1121FF, 9, 10, 6, 3, 8, 2, 5, 0, 4, 9, Ascii.f1128SI, 10, 3, 7, Ascii.f1121FF, 13, 6, Ascii.f1129SO, 1, Ascii.f1132VT, 3, 6, 0, 1, 5, 13, 10, 8, Ascii.f1132VT, 2, 9, 7, Ascii.f1129SO, Ascii.f1128SI, Ascii.f1121FF, 4, 8, 13, Ascii.f1132VT, 0, 4, 5, 1, 2, 9, 3, Ascii.f1121FF, Ascii.f1129SO, 6, Ascii.f1128SI, 10, 7, Ascii.f1121FF, 9, Ascii.f1132VT, 1, 8, Ascii.f1129SO, 2, 4, 7, 3, 6, 5, 10, 0, Ascii.f1128SI, 13, 10, 9, 6, 8, 13, Ascii.f1129SO, 2, 0, Ascii.f1128SI, 3, 5, Ascii.f1132VT, 4, 1, Ascii.f1121FF, 7, 7, 4, 0, 5, 10, 2, Ascii.f1128SI, Ascii.f1129SO, Ascii.f1121FF, 6, 1, Ascii.f1132VT, 13, 9, 3, 8};
    private static byte[] ESbox_D = {Ascii.f1128SI, Ascii.f1121FF, 2, 10, 6, 4, 5, 0, 7, 9, Ascii.f1129SO, 13, 1, Ascii.f1132VT, 8, 3, Ascii.f1132VT, 6, 3, 4, Ascii.f1121FF, Ascii.f1128SI, Ascii.f1129SO, 2, 7, 13, 8, 0, 5, 10, 9, 1, 1, Ascii.f1121FF, Ascii.f1132VT, 0, Ascii.f1128SI, Ascii.f1129SO, 6, 5, 10, 13, 4, 8, 9, 3, 7, 2, 1, 5, Ascii.f1129SO, Ascii.f1121FF, 10, 7, 0, 13, 6, 2, Ascii.f1132VT, 4, 9, 3, Ascii.f1128SI, 8, 0, Ascii.f1121FF, 8, 9, 13, 2, 10, Ascii.f1132VT, 7, 3, 6, 5, 4, Ascii.f1129SO, Ascii.f1128SI, 1, 8, 0, Ascii.f1128SI, 3, 2, 5, Ascii.f1129SO, Ascii.f1132VT, 1, 10, 4, 7, Ascii.f1121FF, 9, 13, 6, 3, 0, 6, Ascii.f1128SI, 1, Ascii.f1129SO, 9, 2, 13, 8, Ascii.f1121FF, 4, Ascii.f1132VT, 10, 5, 7, 1, 10, 6, 8, Ascii.f1128SI, Ascii.f1132VT, 0, 4, Ascii.f1121FF, 3, 5, 9, 7, 13, 2, Ascii.f1129SO};
    private static byte[] Param_Z = {Ascii.f1121FF, 4, 6, 2, 10, 5, Ascii.f1132VT, 9, Ascii.f1129SO, 8, 13, 7, 0, 3, Ascii.f1128SI, 1, 6, 8, 2, 3, 9, 10, 5, Ascii.f1121FF, 1, Ascii.f1129SO, 4, 7, Ascii.f1132VT, 13, 0, Ascii.f1128SI, Ascii.f1132VT, 3, 5, 8, 2, Ascii.f1128SI, 10, 13, Ascii.f1129SO, 1, 7, 4, Ascii.f1121FF, 9, 6, 0, Ascii.f1121FF, 8, 2, 1, 13, 4, Ascii.f1128SI, 6, 7, 0, 10, 5, 3, Ascii.f1129SO, 9, Ascii.f1132VT, 7, Ascii.f1128SI, 5, 10, 8, 1, 6, 13, 0, 9, 3, Ascii.f1129SO, Ascii.f1132VT, 4, 2, Ascii.f1121FF, 5, 13, Ascii.f1128SI, 6, 9, 2, Ascii.f1121FF, 10, Ascii.f1132VT, 7, 8, 1, 4, 3, Ascii.f1129SO, 0, 8, Ascii.f1129SO, 2, 5, 6, 9, 1, Ascii.f1121FF, Ascii.f1128SI, 4, Ascii.f1132VT, 0, 13, 10, 3, 7, 1, 7, Ascii.f1129SO, 13, 0, 5, 8, 3, 4, Ascii.f1128SI, 10, 6, 9, Ascii.f1121FF, Ascii.f1132VT, 2};
    private static byte[] DSbox_Test = {4, 10, 9, 2, 13, 8, 0, Ascii.f1129SO, 6, Ascii.f1132VT, 1, Ascii.f1121FF, 7, Ascii.f1128SI, 5, 3, Ascii.f1129SO, Ascii.f1132VT, 4, Ascii.f1121FF, 6, 13, Ascii.f1128SI, 10, 2, 3, 8, 1, 0, 7, 5, 9, 5, 8, 1, 13, 10, 3, 4, 2, Ascii.f1129SO, Ascii.f1128SI, Ascii.f1121FF, 7, 6, 0, 9, Ascii.f1132VT, 7, 13, 10, 1, 0, 8, 9, Ascii.f1128SI, Ascii.f1129SO, 4, 6, Ascii.f1121FF, Ascii.f1132VT, 2, 5, 3, 6, Ascii.f1121FF, 7, 1, 5, Ascii.f1128SI, 13, 8, 4, 10, 9, Ascii.f1129SO, 0, 3, Ascii.f1132VT, 2, 4, Ascii.f1132VT, 10, 0, 7, 2, 1, 13, 3, 6, 8, 5, 9, Ascii.f1121FF, Ascii.f1128SI, Ascii.f1129SO, 13, Ascii.f1132VT, 4, 1, 3, Ascii.f1128SI, 5, 9, 0, 10, Ascii.f1129SO, 7, 6, 8, 2, Ascii.f1121FF, 1, Ascii.f1128SI, 13, 0, 5, 7, 10, 4, 9, 2, 3, Ascii.f1129SO, 6, Ascii.f1132VT, 8, Ascii.f1121FF};
    private static byte[] DSbox_A = {10, 4, 5, 6, 8, 1, 3, 7, 13, Ascii.f1121FF, Ascii.f1129SO, 0, 9, 2, Ascii.f1132VT, Ascii.f1128SI, 5, Ascii.f1128SI, 4, 0, 2, 13, Ascii.f1132VT, 9, 1, 7, 6, 3, Ascii.f1121FF, Ascii.f1129SO, 10, 8, 7, Ascii.f1128SI, Ascii.f1121FF, Ascii.f1129SO, 9, 4, 1, 0, 3, Ascii.f1132VT, 5, 2, 6, 10, 8, 13, 4, 10, 7, Ascii.f1121FF, 0, Ascii.f1128SI, 2, 8, Ascii.f1129SO, 1, 6, 5, 13, Ascii.f1132VT, 9, 3, 7, 6, 4, Ascii.f1132VT, 9, Ascii.f1121FF, 2, 10, 1, 8, 0, Ascii.f1129SO, Ascii.f1128SI, 13, 3, 5, 7, 6, 2, 4, 13, 9, Ascii.f1128SI, 0, 10, 1, 5, Ascii.f1132VT, 8, Ascii.f1129SO, Ascii.f1121FF, 3, 13, Ascii.f1129SO, 4, 1, 7, 0, 5, 10, 3, Ascii.f1121FF, 8, Ascii.f1128SI, 6, 2, 9, Ascii.f1132VT, 1, 3, 10, 9, 5, Ascii.f1132VT, 4, Ascii.f1128SI, 8, 6, 7, Ascii.f1129SO, 13, 0, 2, Ascii.f1121FF};
    private static Hashtable sBoxes = new Hashtable();
    private int[] workingKey = null;

    /* renamed from: S */
    private byte[] f1928S = Sbox_Default;

    static {
        addSBox("Default", Sbox_Default);
        addSBox("E-TEST", ESbox_Test);
        addSBox("E-A", ESbox_A);
        addSBox("E-B", ESbox_B);
        addSBox("E-C", ESbox_C);
        addSBox("E-D", ESbox_D);
        addSBox("Param-Z", Param_Z);
        addSBox("D-TEST", DSbox_Test);
        addSBox("D-A", DSbox_A);
    }

    private void GOST28147Func(int[] r9, byte[] bArr, int r11, byte[] bArr2, int r13) {
        int r112;
        int r10;
        int bytesToint = bytesToint(bArr, r11);
        int bytesToint2 = bytesToint(bArr, r11 + 4);
        int r1 = 7;
        if (this.forEncryption) {
            for (int r113 = 0; r113 < 3; r113++) {
                int r5 = 0;
                while (r5 < 8) {
                    r5++;
                    int r7 = bytesToint;
                    bytesToint = bytesToint2 ^ GOST28147_mainStep(bytesToint, r9[r5]);
                    bytesToint2 = r7;
                }
            }
            r112 = bytesToint2;
            r10 = bytesToint;
            while (r1 > 0) {
                int GOST28147_mainStep = r112 ^ GOST28147_mainStep(r10, r9[r1]);
                r1--;
                r112 = r10;
                r10 = GOST28147_mainStep;
            }
        } else {
            int r114 = 0;
            while (r114 < 8) {
                r114++;
                int r72 = bytesToint;
                bytesToint = bytesToint2 ^ GOST28147_mainStep(bytesToint, r9[r114]);
                bytesToint2 = r72;
            }
            r112 = bytesToint2;
            r10 = bytesToint;
            for (int r0 = 0; r0 < 3; r0++) {
                int r3 = 7;
                while (r3 >= 0 && (r0 != 2 || r3 != 0)) {
                    int GOST28147_mainStep2 = r112 ^ GOST28147_mainStep(r10, r9[r3]);
                    r3--;
                    r112 = r10;
                    r10 = GOST28147_mainStep2;
                }
            }
        }
        intTobytes(r10, bArr2, r13);
        intTobytes(GOST28147_mainStep(r10, r9[0]) ^ r112, bArr2, r13 + 4);
    }

    private int GOST28147_mainStep(int r3, int r4) {
        int r42 = r4 + r3;
        byte[] bArr = this.f1928S;
        int r0 = (bArr[((r42 >> 0) & 15) + 0] << 0) + (bArr[((r42 >> 4) & 15) + 16] << 4) + (bArr[((r42 >> 8) & 15) + 32] << 8) + (bArr[((r42 >> 12) & 15) + 48] << Ascii.f1121FF) + (bArr[((r42 >> 16) & 15) + 64] << 16) + (bArr[((r42 >> 20) & 15) + 80] << Ascii.DC4) + (bArr[((r42 >> 24) & 15) + 96] << Ascii.CAN) + (bArr[((r42 >> 28) & 15) + 112] << Ascii.f1122FS);
        return (r0 << 11) | (r0 >>> 21);
    }

    private static void addSBox(String str, byte[] bArr) {
        sBoxes.put(Strings.toUpperCase(str), bArr);
    }

    private int bytesToint(byte[] bArr, int r5) {
        return ((bArr[r5 + 3] << Ascii.CAN) & ViewCompat.MEASURED_STATE_MASK) + ((bArr[r5 + 2] << 16) & 16711680) + ((bArr[r5 + 1] << 8) & MotionEventCompat.ACTION_POINTER_INDEX_MASK) + (bArr[r5] & 255);
    }

    private int[] generateWorkingKey(boolean z, byte[] bArr) {
        this.forEncryption = z;
        if (bArr.length == 32) {
            int[] r0 = new int[8];
            for (int r1 = 0; r1 != 8; r1++) {
                r0[r1] = bytesToint(bArr, r1 * 4);
            }
            return r0;
        }
        throw new IllegalArgumentException("Key length invalid. Key needs to be 32 byte - 256 bit!!!");
    }

    public static byte[] getSBox(String str) {
        byte[] bArr = (byte[]) sBoxes.get(Strings.toUpperCase(str));
        if (bArr != null) {
            return Arrays.clone(bArr);
        }
        throw new IllegalArgumentException("Unknown S-Box - possible types: \"Default\", \"E-Test\", \"E-A\", \"E-B\", \"E-C\", \"E-D\", \"Param-Z\", \"D-Test\", \"D-A\".");
    }

    public static String getSBoxName(byte[] bArr) {
        Enumeration keys = sBoxes.keys();
        while (keys.hasMoreElements()) {
            String str = (String) keys.nextElement();
            if (Arrays.areEqual((byte[]) sBoxes.get(str), bArr)) {
                return str;
            }
        }
        throw new IllegalArgumentException("SBOX provided did not map to a known one");
    }

    private void intTobytes(int r3, byte[] bArr, int r5) {
        bArr[r5 + 3] = (byte) (r3 >>> 24);
        bArr[r5 + 2] = (byte) (r3 >>> 16);
        bArr[r5 + 1] = (byte) (r3 >>> 8);
        bArr[r5] = (byte) r3;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public String getAlgorithmName() {
        return "GOST28147";
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int getBlockSize() {
        return 8;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void init(boolean z, CipherParameters cipherParameters) {
        if (cipherParameters instanceof ParametersWithSBox) {
            ParametersWithSBox parametersWithSBox = (ParametersWithSBox) cipherParameters;
            byte[] sBox = parametersWithSBox.getSBox();
            if (sBox.length != Sbox_Default.length) {
                throw new IllegalArgumentException("invalid S-box passed to GOST28147 init");
            }
            this.f1928S = Arrays.clone(sBox);
            if (parametersWithSBox.getParameters() != null) {
                this.workingKey = generateWorkingKey(z, ((KeyParameter) parametersWithSBox.getParameters()).getKey());
            }
        } else if (cipherParameters instanceof KeyParameter) {
            this.workingKey = generateWorkingKey(z, ((KeyParameter) cipherParameters).getKey());
        } else if (cipherParameters == null) {
        } else {
            throw new IllegalArgumentException("invalid parameter passed to GOST28147 init - " + cipherParameters.getClass().getName());
        }
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int processBlock(byte[] bArr, int r8, byte[] bArr2, int r10) {
        int[] r1 = this.workingKey;
        if (r1 != null) {
            if (r8 + 8 <= bArr.length) {
                if (r10 + 8 <= bArr2.length) {
                    GOST28147Func(r1, bArr, r8, bArr2, r10);
                    return 8;
                }
                throw new OutputLengthException("output buffer too short");
            }
            throw new DataLengthException("input buffer too short");
        }
        throw new IllegalStateException("GOST28147 engine not initialised");
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void reset() {
    }
}
