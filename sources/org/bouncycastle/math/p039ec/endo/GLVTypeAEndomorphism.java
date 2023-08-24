package org.bouncycastle.math.p039ec.endo;

import java.math.BigInteger;
import org.bouncycastle.math.p039ec.ECCurve;
import org.bouncycastle.math.p039ec.ECPointMap;
import org.bouncycastle.math.p039ec.ScaleYNegateXPointMap;

/* renamed from: org.bouncycastle.math.ec.endo.GLVTypeAEndomorphism */
/* loaded from: classes5.dex */
public class GLVTypeAEndomorphism implements GLVEndomorphism {
    protected final GLVTypeAParameters parameters;
    protected final ECPointMap pointMap;

    public GLVTypeAEndomorphism(ECCurve eCCurve, GLVTypeAParameters gLVTypeAParameters) {
        this.parameters = gLVTypeAParameters;
        this.pointMap = new ScaleYNegateXPointMap(eCCurve.fromBigInteger(gLVTypeAParameters.getI()));
    }

    @Override // org.bouncycastle.math.p039ec.endo.GLVEndomorphism
    public BigInteger[] decomposeScalar(BigInteger bigInteger) {
        return EndoUtil.decomposeScalar(this.parameters.getSplitParams(), bigInteger);
    }

    @Override // org.bouncycastle.math.p039ec.endo.ECEndomorphism
    public ECPointMap getPointMap() {
        return this.pointMap;
    }

    @Override // org.bouncycastle.math.p039ec.endo.ECEndomorphism
    public boolean hasEfficientPointMap() {
        return true;
    }
}
