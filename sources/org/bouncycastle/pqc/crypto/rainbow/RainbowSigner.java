package org.bouncycastle.pqc.crypto.rainbow;

import java.security.SecureRandom;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.CryptoServicesRegistrar;
import org.bouncycastle.crypto.params.ParametersWithRandom;
import org.bouncycastle.pqc.crypto.MessageSigner;
import org.bouncycastle.pqc.crypto.rainbow.util.ComputeInField;
import org.bouncycastle.pqc.crypto.rainbow.util.GF2Field;

/* loaded from: classes3.dex */
public class RainbowSigner implements MessageSigner {
    private static final int MAXITS = 65536;

    /* renamed from: cf */
    private ComputeInField f2497cf = new ComputeInField();
    RainbowKeyParameters key;
    private SecureRandom random;
    int signableDocumentLength;

    /* renamed from: x */
    private short[] f2498x;

    private short[] initSign(Layer[] layerArr, short[] sArr) {
        short[] sArr2 = new short[sArr.length];
        short[] multiplyMatrix = this.f2497cf.multiplyMatrix(((RainbowPrivateKeyParameters) this.key).getInvA1(), this.f2497cf.addVect(((RainbowPrivateKeyParameters) this.key).getB1(), sArr));
        for (int r1 = 0; r1 < layerArr[0].getVi(); r1++) {
            this.f2498x[r1] = (short) this.random.nextInt();
            short[] sArr3 = this.f2498x;
            sArr3[r1] = (short) (sArr3[r1] & 255);
        }
        return multiplyMatrix;
    }

    private short[] makeMessageRepresentative(byte[] bArr) {
        int r0 = this.signableDocumentLength;
        short[] sArr = new short[r0];
        int r2 = 0;
        int r3 = 0;
        while (r2 < bArr.length) {
            sArr[r2] = bArr[r3];
            sArr[r2] = (short) (sArr[r2] & 255);
            r3++;
            r2++;
            if (r2 >= r0) {
                break;
            }
        }
        return sArr;
    }

    private short[] verifySignatureIntern(short[] sArr) {
        short[][] coeffQuadratic = ((RainbowPublicKeyParameters) this.key).getCoeffQuadratic();
        short[][] coeffSingular = ((RainbowPublicKeyParameters) this.key).getCoeffSingular();
        short[] coeffScalar = ((RainbowPublicKeyParameters) this.key).getCoeffScalar();
        short[] sArr2 = new short[coeffQuadratic.length];
        int length = coeffSingular[0].length;
        for (int r6 = 0; r6 < coeffQuadratic.length; r6++) {
            int r8 = 0;
            for (int r7 = 0; r7 < length; r7++) {
                for (int r9 = r7; r9 < length; r9++) {
                    sArr2[r6] = GF2Field.addElem(sArr2[r6], GF2Field.multElem(coeffQuadratic[r6][r8], GF2Field.multElem(sArr[r7], sArr[r9])));
                    r8++;
                }
                sArr2[r6] = GF2Field.addElem(sArr2[r6], GF2Field.multElem(coeffSingular[r6][r7], sArr[r7]));
            }
            sArr2[r6] = GF2Field.addElem(sArr2[r6], coeffScalar[r6]);
        }
        return sArr2;
    }

    @Override // org.bouncycastle.pqc.crypto.MessageSigner
    public byte[] generateSignature(byte[] bArr) {
        boolean z;
        Layer[] layers = ((RainbowPrivateKeyParameters) this.key).getLayers();
        int length = layers.length;
        this.f2498x = new short[((RainbowPrivateKeyParameters) this.key).getInvA2().length];
        int viNext = layers[length - 1].getViNext();
        byte[] bArr2 = new byte[viNext];
        short[] makeMessageRepresentative = makeMessageRepresentative(bArr);
        int r5 = 0;
        do {
            try {
                short[] initSign = initSign(layers, makeMessageRepresentative);
                int r8 = 0;
                for (int r7 = 0; r7 < length; r7++) {
                    short[] sArr = new short[layers[r7].getOi()];
                    short[] sArr2 = new short[layers[r7].getOi()];
                    for (int r10 = 0; r10 < layers[r7].getOi(); r10++) {
                        sArr[r10] = initSign[r8];
                        r8++;
                    }
                    short[] solveEquation = this.f2497cf.solveEquation(layers[r7].plugInVinegars(this.f2498x), sArr);
                    if (solveEquation == null) {
                        throw new Exception("LES is not solveable!");
                        break;
                    }
                    for (int r102 = 0; r102 < solveEquation.length; r102++) {
                        this.f2498x[layers[r7].getVi() + r102] = solveEquation[r102];
                    }
                }
                short[] multiplyMatrix = this.f2497cf.multiplyMatrix(((RainbowPrivateKeyParameters) this.key).getInvA2(), this.f2497cf.addVect(((RainbowPrivateKeyParameters) this.key).getB2(), this.f2498x));
                for (int r72 = 0; r72 < viNext; r72++) {
                    bArr2[r72] = (byte) multiplyMatrix[r72];
                }
                z = true;
            } catch (Exception unused) {
                z = false;
            }
            if (z) {
                break;
            }
            r5++;
        } while (r5 < 65536);
        if (r5 != 65536) {
            return bArr2;
        }
        throw new IllegalStateException("unable to generate signature - LES not solvable");
    }

    @Override // org.bouncycastle.pqc.crypto.MessageSigner
    public void init(boolean z, CipherParameters cipherParameters) {
        RainbowKeyParameters rainbowKeyParameters;
        if (!z) {
            rainbowKeyParameters = (RainbowPublicKeyParameters) cipherParameters;
        } else if (cipherParameters instanceof ParametersWithRandom) {
            ParametersWithRandom parametersWithRandom = (ParametersWithRandom) cipherParameters;
            this.random = parametersWithRandom.getRandom();
            this.key = (RainbowPrivateKeyParameters) parametersWithRandom.getParameters();
            this.signableDocumentLength = this.key.getDocLength();
        } else {
            this.random = CryptoServicesRegistrar.getSecureRandom();
            rainbowKeyParameters = (RainbowPrivateKeyParameters) cipherParameters;
        }
        this.key = rainbowKeyParameters;
        this.signableDocumentLength = this.key.getDocLength();
    }

    @Override // org.bouncycastle.pqc.crypto.MessageSigner
    public boolean verifySignature(byte[] bArr, byte[] bArr2) {
        short[] sArr = new short[bArr2.length];
        for (int r2 = 0; r2 < bArr2.length; r2++) {
            sArr[r2] = (short) (bArr2[r2] & 255);
        }
        short[] makeMessageRepresentative = makeMessageRepresentative(bArr);
        short[] verifySignatureIntern = verifySignatureIntern(sArr);
        if (makeMessageRepresentative.length != verifySignatureIntern.length) {
            return false;
        }
        boolean z = true;
        for (int r22 = 0; r22 < makeMessageRepresentative.length; r22++) {
            z = z && makeMessageRepresentative[r22] == verifySignatureIntern[r22];
        }
        return z;
    }
}
