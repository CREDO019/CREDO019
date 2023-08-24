package org.bouncycastle.jcajce.provider.symmetric.util;

import java.security.AlgorithmParameters;
import java.security.spec.AlgorithmParameterSpec;

/* loaded from: classes5.dex */
class SpecUtil {
    SpecUtil() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static AlgorithmParameterSpec extractSpec(AlgorithmParameters algorithmParameters, Class[] clsArr) {
        try {
            return algorithmParameters.getParameterSpec(AlgorithmParameterSpec.class);
        } catch (Exception unused) {
            for (int r0 = 0; r0 != clsArr.length; r0++) {
                if (clsArr[r0] != null) {
                    try {
                        return algorithmParameters.getParameterSpec(clsArr[r0]);
                    } catch (Exception unused2) {
                    }
                }
            }
            return null;
        }
    }
}
