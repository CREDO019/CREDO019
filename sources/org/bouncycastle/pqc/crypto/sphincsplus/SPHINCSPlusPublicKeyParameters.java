package org.bouncycastle.pqc.crypto.sphincsplus;

import org.bouncycastle.util.Arrays;

/* loaded from: classes3.dex */
public class SPHINCSPlusPublicKeyParameters extends SPHINCSPlusKeyParameters {

    /* renamed from: pk */
    private final C5394PK f2511pk;

    /* JADX INFO: Access modifiers changed from: package-private */
    public SPHINCSPlusPublicKeyParameters(SPHINCSPlusParameters sPHINCSPlusParameters, C5394PK c5394pk) {
        super(false, sPHINCSPlusParameters);
        this.f2511pk = c5394pk;
    }

    public SPHINCSPlusPublicKeyParameters(SPHINCSPlusParameters sPHINCSPlusParameters, byte[] bArr) {
        super(false, sPHINCSPlusParameters);
        int r4 = sPHINCSPlusParameters.getEngine().f2507N;
        int r2 = r4 * 2;
        if (bArr.length != r2) {
            throw new IllegalArgumentException("public key encoding does not match parameters");
        }
        this.f2511pk = new C5394PK(Arrays.copyOfRange(bArr, 0, r4), Arrays.copyOfRange(bArr, r4, r2));
    }

    public byte[] getEncoded() {
        return Arrays.concatenate(this.f2511pk.seed, this.f2511pk.root);
    }

    public byte[] getRoot() {
        return Arrays.clone(this.f2511pk.root);
    }

    public byte[] getSeed() {
        return Arrays.clone(this.f2511pk.seed);
    }
}
