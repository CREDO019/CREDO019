package org.bouncycastle.jce.spec;

import java.security.spec.AlgorithmParameterSpec;

/* loaded from: classes5.dex */
public class ElGamalGenParameterSpec implements AlgorithmParameterSpec {
    private int primeSize;

    public ElGamalGenParameterSpec(int r1) {
        this.primeSize = r1;
    }

    public int getPrimeSize() {
        return this.primeSize;
    }
}
