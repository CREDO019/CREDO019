package org.bouncycastle.math.p039ec.endo;

import org.bouncycastle.math.p039ec.ECPointMap;

/* renamed from: org.bouncycastle.math.ec.endo.ECEndomorphism */
/* loaded from: classes5.dex */
public interface ECEndomorphism {
    ECPointMap getPointMap();

    boolean hasEfficientPointMap();
}
