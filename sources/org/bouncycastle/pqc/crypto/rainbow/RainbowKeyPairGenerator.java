package org.bouncycastle.pqc.crypto.rainbow;

import java.lang.reflect.Array;
import java.security.SecureRandom;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.AsymmetricCipherKeyPairGenerator;
import org.bouncycastle.crypto.CryptoServicesRegistrar;
import org.bouncycastle.crypto.KeyGenerationParameters;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.pqc.crypto.rainbow.util.ComputeInField;
import org.bouncycastle.pqc.crypto.rainbow.util.GF2Field;

/* loaded from: classes3.dex */
public class RainbowKeyPairGenerator implements AsymmetricCipherKeyPairGenerator {

    /* renamed from: A1 */
    private short[][] f2487A1;
    private short[][] A1inv;

    /* renamed from: A2 */
    private short[][] f2488A2;
    private short[][] A2inv;

    /* renamed from: b1 */
    private short[] f2489b1;

    /* renamed from: b2 */
    private short[] f2490b2;
    private boolean initialized = false;
    private Layer[] layers;
    private int numOfLayers;
    private short[][] pub_quadratic;
    private short[] pub_scalar;
    private short[][] pub_singular;
    private RainbowKeyGenerationParameters rainbowParams;

    /* renamed from: sr */
    private SecureRandom f2491sr;

    /* renamed from: vi */
    private int[] f2492vi;

    private void compactPublicKey(short[][][] sArr) {
        int length = sArr.length;
        int length2 = sArr[0].length;
        this.pub_quadratic = (short[][]) Array.newInstance(short.class, length, ((length2 + 1) * length2) / 2);
        for (int r3 = 0; r3 < length; r3++) {
            int r5 = 0;
            for (int r4 = 0; r4 < length2; r4++) {
                for (int r6 = r4; r6 < length2; r6++) {
                    short[][] sArr2 = this.pub_quadratic;
                    if (r6 == r4) {
                        sArr2[r3][r5] = sArr[r3][r4][r6];
                    } else {
                        sArr2[r3][r5] = GF2Field.addElem(sArr[r3][r4][r6], sArr[r3][r6][r4]);
                    }
                    r5++;
                }
            }
        }
    }

    private void computePublicKey() {
        Class<short> cls;
        Class<short> cls2 = short.class;
        ComputeInField computeInField = new ComputeInField();
        int[] r3 = this.f2492vi;
        int r6 = 0;
        int r4 = r3[r3.length - 1] - r3[0];
        int r32 = r3[r3.length - 1];
        short[][][] sArr = (short[][][]) Array.newInstance((Class<?>) cls2, r4, r32, r32);
        this.pub_singular = (short[][]) Array.newInstance((Class<?>) cls2, r4, r32);
        this.pub_scalar = new short[r4];
        short[] sArr2 = new short[r32];
        int r10 = 0;
        int r11 = 0;
        while (true) {
            Layer[] layerArr = this.layers;
            if (r10 >= layerArr.length) {
                break;
            }
            short[][][] coeffAlpha = layerArr[r10].getCoeffAlpha();
            short[][][] coeffBeta = this.layers[r10].getCoeffBeta();
            short[][] coeffGamma = this.layers[r10].getCoeffGamma();
            short[] coeffEta = this.layers[r10].getCoeffEta();
            int length = coeffAlpha[r6].length;
            int length2 = coeffBeta[r6].length;
            while (r6 < length) {
                int r7 = 0;
                while (true) {
                    cls = cls2;
                    if (r7 >= length) {
                        break;
                    }
                    int r1 = 0;
                    while (r1 < length2) {
                        int r18 = r4;
                        int r17 = r32;
                        int r19 = r7 + length2;
                        short[] multVect = computeInField.multVect(coeffAlpha[r6][r7][r1], this.f2488A2[r19]);
                        int r42 = r11 + r6;
                        int r20 = r10;
                        sArr[r42] = computeInField.addSquareMatrix(sArr[r42], computeInField.multVects(multVect, this.f2488A2[r1]));
                        short[] multVect2 = computeInField.multVect(this.f2490b2[r1], multVect);
                        short[][] sArr3 = this.pub_singular;
                        sArr3[r42] = computeInField.addVect(multVect2, sArr3[r42]);
                        short[] multVect3 = computeInField.multVect(this.f2490b2[r19], computeInField.multVect(coeffAlpha[r6][r7][r1], this.f2488A2[r1]));
                        short[][] sArr4 = this.pub_singular;
                        sArr4[r42] = computeInField.addVect(multVect3, sArr4[r42]);
                        short multElem = GF2Field.multElem(coeffAlpha[r6][r7][r1], this.f2490b2[r19]);
                        short[] sArr5 = this.pub_scalar;
                        sArr5[r42] = GF2Field.addElem(sArr5[r42], GF2Field.multElem(multElem, this.f2490b2[r1]));
                        r1++;
                        r32 = r17;
                        r4 = r18;
                        coeffAlpha = coeffAlpha;
                        r10 = r20;
                        coeffEta = coeffEta;
                    }
                    r7++;
                    cls2 = cls;
                }
                int r172 = r32;
                int r182 = r4;
                int r202 = r10;
                short[][][] sArr6 = coeffAlpha;
                short[] sArr7 = coeffEta;
                for (int r12 = 0; r12 < length2; r12++) {
                    for (int r33 = 0; r33 < length2; r33++) {
                        short[] multVect4 = computeInField.multVect(coeffBeta[r6][r12][r33], this.f2488A2[r12]);
                        int r72 = r11 + r6;
                        sArr[r72] = computeInField.addSquareMatrix(sArr[r72], computeInField.multVects(multVect4, this.f2488A2[r33]));
                        short[] multVect5 = computeInField.multVect(this.f2490b2[r33], multVect4);
                        short[][] sArr8 = this.pub_singular;
                        sArr8[r72] = computeInField.addVect(multVect5, sArr8[r72]);
                        short[] multVect6 = computeInField.multVect(this.f2490b2[r12], computeInField.multVect(coeffBeta[r6][r12][r33], this.f2488A2[r33]));
                        short[][] sArr9 = this.pub_singular;
                        sArr9[r72] = computeInField.addVect(multVect6, sArr9[r72]);
                        short multElem2 = GF2Field.multElem(coeffBeta[r6][r12][r33], this.f2490b2[r12]);
                        short[] sArr10 = this.pub_scalar;
                        sArr10[r72] = GF2Field.addElem(sArr10[r72], GF2Field.multElem(multElem2, this.f2490b2[r33]));
                    }
                }
                for (int r13 = 0; r13 < length2 + length; r13++) {
                    short[] multVect7 = computeInField.multVect(coeffGamma[r6][r13], this.f2488A2[r13]);
                    short[][] sArr11 = this.pub_singular;
                    int r73 = r11 + r6;
                    sArr11[r73] = computeInField.addVect(multVect7, sArr11[r73]);
                    short[] sArr12 = this.pub_scalar;
                    sArr12[r73] = GF2Field.addElem(sArr12[r73], GF2Field.multElem(coeffGamma[r6][r13], this.f2490b2[r13]));
                }
                short[] sArr13 = this.pub_scalar;
                int r34 = r11 + r6;
                sArr13[r34] = GF2Field.addElem(sArr13[r34], sArr7[r6]);
                r6++;
                cls2 = cls;
                r32 = r172;
                r4 = r182;
                coeffAlpha = sArr6;
                r10 = r202;
                coeffEta = sArr7;
            }
            r11 += length;
            r10++;
            r6 = 0;
        }
        Class<short> cls3 = cls2;
        int r173 = r32;
        int r183 = r4;
        short[][][] sArr14 = (short[][][]) Array.newInstance((Class<?>) cls3, r183, r173, r173);
        short[][] sArr15 = (short[][]) Array.newInstance((Class<?>) cls3, r183, r173);
        short[] sArr16 = new short[r183];
        for (int r74 = 0; r74 < r183; r74++) {
            int r9 = 0;
            while (true) {
                short[][] sArr17 = this.f2487A1;
                if (r9 < sArr17.length) {
                    sArr14[r74] = computeInField.addSquareMatrix(sArr14[r74], computeInField.multMatrix(sArr17[r74][r9], sArr[r9]));
                    sArr15[r74] = computeInField.addVect(sArr15[r74], computeInField.multVect(this.f2487A1[r74][r9], this.pub_singular[r9]));
                    sArr16[r74] = GF2Field.addElem(sArr16[r74], GF2Field.multElem(this.f2487A1[r74][r9], this.pub_scalar[r9]));
                    r9++;
                }
            }
            sArr16[r74] = GF2Field.addElem(sArr16[r74], this.f2489b1[r74]);
        }
        this.pub_singular = sArr15;
        this.pub_scalar = sArr16;
        compactPublicKey(sArr14);
    }

    private void generateF() {
        this.layers = new Layer[this.numOfLayers];
        int r0 = 0;
        while (r0 < this.numOfLayers) {
            Layer[] layerArr = this.layers;
            int[] r3 = this.f2492vi;
            int r5 = r0 + 1;
            layerArr[r0] = new Layer(r3[r0], r3[r5], this.f2491sr);
            r0 = r5;
        }
    }

    private void generateL1() {
        int[] r0 = this.f2492vi;
        int r1 = r0[r0.length - 1] - r0[0];
        this.f2487A1 = (short[][]) Array.newInstance(short.class, r1, r1);
        this.A1inv = null;
        ComputeInField computeInField = new ComputeInField();
        while (this.A1inv == null) {
            for (int r2 = 0; r2 < r1; r2++) {
                for (int r4 = 0; r4 < r1; r4++) {
                    this.f2487A1[r2][r4] = (short) (this.f2491sr.nextInt() & 255);
                }
            }
            this.A1inv = computeInField.inverse(this.f2487A1);
        }
        this.f2489b1 = new short[r1];
        for (int r3 = 0; r3 < r1; r3++) {
            this.f2489b1[r3] = (short) (this.f2491sr.nextInt() & 255);
        }
    }

    private void generateL2() {
        int[] r0 = this.f2492vi;
        int r02 = r0[r0.length - 1];
        this.f2488A2 = (short[][]) Array.newInstance(short.class, r02, r02);
        this.A2inv = null;
        ComputeInField computeInField = new ComputeInField();
        while (this.A2inv == null) {
            for (int r3 = 0; r3 < r02; r3++) {
                for (int r4 = 0; r4 < r02; r4++) {
                    this.f2488A2[r3][r4] = (short) (this.f2491sr.nextInt() & 255);
                }
            }
            this.A2inv = computeInField.inverse(this.f2488A2);
        }
        this.f2490b2 = new short[r02];
        for (int r2 = 0; r2 < r02; r2++) {
            this.f2490b2[r2] = (short) (this.f2491sr.nextInt() & 255);
        }
    }

    private void initializeDefault() {
        initialize(new RainbowKeyGenerationParameters(CryptoServicesRegistrar.getSecureRandom(), new RainbowParameters()));
    }

    private void keygen() {
        generateL1();
        generateL2();
        generateF();
        computePublicKey();
    }

    public AsymmetricCipherKeyPair genKeyPair() {
        if (!this.initialized) {
            initializeDefault();
        }
        keygen();
        RainbowPrivateKeyParameters rainbowPrivateKeyParameters = new RainbowPrivateKeyParameters(this.A1inv, this.f2489b1, this.A2inv, this.f2490b2, this.f2492vi, this.layers);
        int[] r2 = this.f2492vi;
        return new AsymmetricCipherKeyPair((AsymmetricKeyParameter) new RainbowPublicKeyParameters(r2[r2.length - 1] - r2[0], this.pub_quadratic, this.pub_singular, this.pub_scalar), (AsymmetricKeyParameter) rainbowPrivateKeyParameters);
    }

    @Override // org.bouncycastle.crypto.AsymmetricCipherKeyPairGenerator
    public AsymmetricCipherKeyPair generateKeyPair() {
        return genKeyPair();
    }

    @Override // org.bouncycastle.crypto.AsymmetricCipherKeyPairGenerator
    public void init(KeyGenerationParameters keyGenerationParameters) {
        initialize(keyGenerationParameters);
    }

    public void initialize(KeyGenerationParameters keyGenerationParameters) {
        RainbowKeyGenerationParameters rainbowKeyGenerationParameters = (RainbowKeyGenerationParameters) keyGenerationParameters;
        this.rainbowParams = rainbowKeyGenerationParameters;
        this.f2491sr = rainbowKeyGenerationParameters.getRandom();
        this.f2492vi = this.rainbowParams.getParameters().getVi();
        this.numOfLayers = this.rainbowParams.getParameters().getNumOfLayers();
        this.initialized = true;
    }
}
