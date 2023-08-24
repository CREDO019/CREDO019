package org.bouncycastle.jcajce.spec;

import java.security.spec.AlgorithmParameterSpec;
import java.util.Objects;
import org.bouncycastle.util.Arrays;

/* loaded from: classes5.dex */
public class SM2ParameterSpec implements AlgorithmParameterSpec {

    /* renamed from: id */
    private byte[] f2217id;

    public SM2ParameterSpec(byte[] bArr) {
        Objects.requireNonNull(bArr, "id string cannot be null");
        this.f2217id = Arrays.clone(bArr);
    }

    public byte[] getID() {
        return Arrays.clone(this.f2217id);
    }
}
