package org.bouncycastle.pqc.crypto.sphincsplus;

import org.bouncycastle.util.Arrays;

/* loaded from: classes3.dex */
public class SPHINCSPlusPrivateKeyParameters extends SPHINCSPlusKeyParameters {

    /* renamed from: pk */
    final C5394PK f2509pk;

    /* renamed from: sk */
    final C5395SK f2510sk;

    /* JADX INFO: Access modifiers changed from: package-private */
    public SPHINCSPlusPrivateKeyParameters(SPHINCSPlusParameters sPHINCSPlusParameters, C5395SK c5395sk, C5394PK c5394pk) {
        super(true, sPHINCSPlusParameters);
        this.f2510sk = c5395sk;
        this.f2509pk = c5394pk;
    }

    public SPHINCSPlusPrivateKeyParameters(SPHINCSPlusParameters sPHINCSPlusParameters, byte[] bArr) {
        super(true, sPHINCSPlusParameters);
        int r6 = sPHINCSPlusParameters.getEngine().f2507N;
        int r1 = r6 * 4;
        if (bArr.length != r1) {
            throw new IllegalArgumentException("private key encoding does not match parameters");
        }
        int r3 = r6 * 2;
        this.f2510sk = new C5395SK(Arrays.copyOfRange(bArr, 0, r6), Arrays.copyOfRange(bArr, r6, r3));
        int r62 = r6 * 3;
        this.f2509pk = new C5394PK(Arrays.copyOfRange(bArr, r3, r62), Arrays.copyOfRange(bArr, r62, r1));
    }

    public byte[] getEncoded() {
        return Arrays.concatenate(this.f2510sk.seed, this.f2510sk.prf, this.f2509pk.seed, this.f2509pk.root);
    }

    public byte[] getPrf() {
        return Arrays.clone(this.f2510sk.prf);
    }

    public byte[] getPublicKey() {
        return Arrays.concatenate(this.f2509pk.seed, this.f2509pk.root);
    }

    public byte[] getPublicSeed() {
        return Arrays.clone(this.f2509pk.seed);
    }

    public byte[] getSeed() {
        return Arrays.clone(this.f2510sk.seed);
    }
}
