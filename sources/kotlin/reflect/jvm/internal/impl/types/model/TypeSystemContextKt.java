package kotlin.reflect.jvm.internal.impl.types.model;

import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.types.Variance;

/* compiled from: TypeSystemContext.kt */
/* loaded from: classes5.dex */
public final class TypeSystemContextKt {

    /* compiled from: TypeSystemContext.kt */
    /* loaded from: classes5.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] r0 = new int[Variance.values().length];
            r0[Variance.INVARIANT.ordinal()] = 1;
            r0[Variance.IN_VARIANCE.ordinal()] = 2;
            r0[Variance.OUT_VARIANCE.ordinal()] = 3;
            $EnumSwitchMapping$0 = r0;
        }
    }

    public static final TypeVariance convertVariance(Variance variance) {
        Intrinsics.checkNotNullParameter(variance, "<this>");
        int r1 = WhenMappings.$EnumSwitchMapping$0[variance.ordinal()];
        if (r1 != 1) {
            if (r1 != 2) {
                if (r1 == 3) {
                    return TypeVariance.OUT;
                }
                throw new NoWhenBranchMatchedException();
            }
            return TypeVariance.IN;
        }
        return TypeVariance.INV;
    }
}
