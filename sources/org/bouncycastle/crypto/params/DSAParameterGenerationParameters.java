package org.bouncycastle.crypto.params;

import java.security.SecureRandom;

/* loaded from: classes5.dex */
public class DSAParameterGenerationParameters {
    public static final int DIGITAL_SIGNATURE_USAGE = 1;
    public static final int KEY_ESTABLISHMENT_USAGE = 2;
    private final int certainty;

    /* renamed from: l */
    private final int f2113l;

    /* renamed from: n */
    private final int f2114n;
    private final SecureRandom random;
    private final int usageIndex;

    public DSAParameterGenerationParameters(int r7, int r8, int r9, SecureRandom secureRandom) {
        this(r7, r8, r9, secureRandom, -1);
    }

    public DSAParameterGenerationParameters(int r1, int r2, int r3, SecureRandom secureRandom, int r5) {
        this.f2113l = r1;
        this.f2114n = r2;
        this.certainty = r3;
        this.usageIndex = r5;
        this.random = secureRandom;
    }

    public int getCertainty() {
        return this.certainty;
    }

    public int getL() {
        return this.f2113l;
    }

    public int getN() {
        return this.f2114n;
    }

    public SecureRandom getRandom() {
        return this.random;
    }

    public int getUsageIndex() {
        return this.usageIndex;
    }
}
