package org.bouncycastle.pqc.crypto.rainbow;

/* loaded from: classes3.dex */
public class RainbowPrivateKeyParameters extends RainbowKeyParameters {
    private short[][] A1inv;
    private short[][] A2inv;

    /* renamed from: b1 */
    private short[] f2494b1;

    /* renamed from: b2 */
    private short[] f2495b2;
    private Layer[] layers;

    /* renamed from: vi */
    private int[] f2496vi;

    public RainbowPrivateKeyParameters(short[][] sArr, short[] sArr2, short[][] sArr3, short[] sArr4, int[] r8, Layer[] layerArr) {
        super(true, r8[r8.length - 1] - r8[0]);
        this.A1inv = sArr;
        this.f2494b1 = sArr2;
        this.A2inv = sArr3;
        this.f2495b2 = sArr4;
        this.f2496vi = r8;
        this.layers = layerArr;
    }

    public short[] getB1() {
        return this.f2494b1;
    }

    public short[] getB2() {
        return this.f2495b2;
    }

    public short[][] getInvA1() {
        return this.A1inv;
    }

    public short[][] getInvA2() {
        return this.A2inv;
    }

    public Layer[] getLayers() {
        return this.layers;
    }

    public int[] getVi() {
        return this.f2496vi;
    }
}
