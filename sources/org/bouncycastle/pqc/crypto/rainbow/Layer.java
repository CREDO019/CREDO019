package org.bouncycastle.pqc.crypto.rainbow;

import java.lang.reflect.Array;
import java.security.SecureRandom;
import org.bouncycastle.pqc.crypto.rainbow.util.GF2Field;
import org.bouncycastle.pqc.crypto.rainbow.util.RainbowUtil;
import org.bouncycastle.util.Arrays;

/* loaded from: classes3.dex */
public class Layer {
    private short[][][] coeff_alpha;
    private short[][][] coeff_beta;
    private short[] coeff_eta;
    private short[][] coeff_gamma;

    /* renamed from: oi */
    private int f2485oi;

    /* renamed from: vi */
    private int f2486vi;
    private int viNext;

    public Layer(byte b, byte b2, short[][][] sArr, short[][][] sArr2, short[][] sArr3, short[] sArr4) {
        int r1 = b & 255;
        this.f2486vi = r1;
        int r2 = b2 & 255;
        this.viNext = r2;
        this.f2485oi = r2 - r1;
        this.coeff_alpha = sArr;
        this.coeff_beta = sArr2;
        this.coeff_gamma = sArr3;
        this.coeff_eta = sArr4;
    }

    public Layer(int r6, int r7, SecureRandom secureRandom) {
        this.f2486vi = r6;
        this.viNext = r7;
        int r72 = r7 - r6;
        this.f2485oi = r72;
        this.coeff_alpha = (short[][][]) Array.newInstance(short.class, r72, r72, r6);
        int r73 = this.f2485oi;
        int r2 = this.f2486vi;
        this.coeff_beta = (short[][][]) Array.newInstance(short.class, r73, r2, r2);
        this.coeff_gamma = (short[][]) Array.newInstance(short.class, this.f2485oi, this.viNext);
        int r62 = this.f2485oi;
        this.coeff_eta = new short[r62];
        for (int r74 = 0; r74 < r62; r74++) {
            for (int r0 = 0; r0 < this.f2485oi; r0++) {
                for (int r1 = 0; r1 < this.f2486vi; r1++) {
                    this.coeff_alpha[r74][r0][r1] = (short) (secureRandom.nextInt() & 255);
                }
            }
        }
        for (int r75 = 0; r75 < r62; r75++) {
            for (int r02 = 0; r02 < this.f2486vi; r02++) {
                for (int r12 = 0; r12 < this.f2486vi; r12++) {
                    this.coeff_beta[r75][r02][r12] = (short) (secureRandom.nextInt() & 255);
                }
            }
        }
        for (int r76 = 0; r76 < r62; r76++) {
            for (int r03 = 0; r03 < this.viNext; r03++) {
                this.coeff_gamma[r76][r03] = (short) (secureRandom.nextInt() & 255);
            }
        }
        for (int r4 = 0; r4 < r62; r4++) {
            this.coeff_eta[r4] = (short) (secureRandom.nextInt() & 255);
        }
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Layer)) {
            return false;
        }
        Layer layer = (Layer) obj;
        return this.f2486vi == layer.getVi() && this.viNext == layer.getViNext() && this.f2485oi == layer.getOi() && RainbowUtil.equals(this.coeff_alpha, layer.getCoeffAlpha()) && RainbowUtil.equals(this.coeff_beta, layer.getCoeffBeta()) && RainbowUtil.equals(this.coeff_gamma, layer.getCoeffGamma()) && RainbowUtil.equals(this.coeff_eta, layer.getCoeffEta());
    }

    public short[][][] getCoeffAlpha() {
        return this.coeff_alpha;
    }

    public short[][][] getCoeffBeta() {
        return this.coeff_beta;
    }

    public short[] getCoeffEta() {
        return this.coeff_eta;
    }

    public short[][] getCoeffGamma() {
        return this.coeff_gamma;
    }

    public int getOi() {
        return this.f2485oi;
    }

    public int getVi() {
        return this.f2486vi;
    }

    public int getViNext() {
        return this.viNext;
    }

    public int hashCode() {
        return (((((((((((this.f2486vi * 37) + this.viNext) * 37) + this.f2485oi) * 37) + Arrays.hashCode(this.coeff_alpha)) * 37) + Arrays.hashCode(this.coeff_beta)) * 37) + Arrays.hashCode(this.coeff_gamma)) * 37) + Arrays.hashCode(this.coeff_eta);
    }

    public short[][] plugInVinegars(short[] sArr) {
        int r0 = this.f2485oi;
        int r1 = 0;
        short[][] sArr2 = (short[][]) Array.newInstance(short.class, r0, r0 + 1);
        short[] sArr3 = new short[this.f2485oi];
        for (int r3 = 0; r3 < this.f2485oi; r3++) {
            for (int r4 = 0; r4 < this.f2486vi; r4++) {
                for (int r5 = 0; r5 < this.f2486vi; r5++) {
                    sArr3[r3] = GF2Field.addElem(sArr3[r3], GF2Field.multElem(GF2Field.multElem(this.coeff_beta[r3][r4][r5], sArr[r4]), sArr[r5]));
                }
            }
        }
        for (int r32 = 0; r32 < this.f2485oi; r32++) {
            for (int r42 = 0; r42 < this.f2485oi; r42++) {
                for (int r52 = 0; r52 < this.f2486vi; r52++) {
                    sArr2[r32][r42] = GF2Field.addElem(sArr2[r32][r42], GF2Field.multElem(this.coeff_alpha[r32][r42][r52], sArr[r52]));
                }
            }
        }
        for (int r33 = 0; r33 < this.f2485oi; r33++) {
            for (int r43 = 0; r43 < this.f2486vi; r43++) {
                sArr3[r33] = GF2Field.addElem(sArr3[r33], GF2Field.multElem(this.coeff_gamma[r33][r43], sArr[r43]));
            }
        }
        for (int r10 = 0; r10 < this.f2485oi; r10++) {
            for (int r34 = this.f2486vi; r34 < this.viNext; r34++) {
                short[] sArr4 = sArr2[r10];
                int r53 = this.f2486vi;
                sArr4[r34 - r53] = GF2Field.addElem(this.coeff_gamma[r10][r34], sArr2[r10][r34 - r53]);
            }
        }
        for (int r102 = 0; r102 < this.f2485oi; r102++) {
            sArr3[r102] = GF2Field.addElem(sArr3[r102], this.coeff_eta[r102]);
        }
        while (true) {
            int r103 = this.f2485oi;
            if (r1 >= r103) {
                return sArr2;
            }
            sArr2[r1][r103] = sArr3[r1];
            r1++;
        }
    }
}
