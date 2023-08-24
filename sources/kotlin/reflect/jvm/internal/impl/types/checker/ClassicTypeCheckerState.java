package kotlin.reflect.jvm.internal.impl.types.checker;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.types.TypeCheckerState;
import kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypePreparator;
import kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeRefiner;

/* renamed from: kotlin.reflect.jvm.internal.impl.types.checker.ClassicTypeCheckerStateKt */
/* loaded from: classes5.dex */
public final class ClassicTypeCheckerState {
    public static /* synthetic */ TypeCheckerState createClassicTypeCheckerState$default(boolean z, boolean z2, ClassicTypeSystemContext classicTypeSystemContext, KotlinTypePreparator kotlinTypePreparator, KotlinTypeRefiner kotlinTypeRefiner, int r5, Object obj) {
        if ((r5 & 2) != 0) {
            z2 = true;
        }
        if ((r5 & 4) != 0) {
            classicTypeSystemContext = SimpleClassicTypeSystemContext.INSTANCE;
        }
        if ((r5 & 8) != 0) {
            kotlinTypePreparator = KotlinTypePreparator.Default.INSTANCE;
        }
        if ((r5 & 16) != 0) {
            kotlinTypeRefiner = KotlinTypeRefiner.Default.INSTANCE;
        }
        return createClassicTypeCheckerState(z, z2, classicTypeSystemContext, kotlinTypePreparator, kotlinTypeRefiner);
    }

    public static final TypeCheckerState createClassicTypeCheckerState(boolean z, boolean z2, ClassicTypeSystemContext typeSystemContext, KotlinTypePreparator kotlinTypePreparator, KotlinTypeRefiner kotlinTypeRefiner) {
        Intrinsics.checkNotNullParameter(typeSystemContext, "typeSystemContext");
        Intrinsics.checkNotNullParameter(kotlinTypePreparator, "kotlinTypePreparator");
        Intrinsics.checkNotNullParameter(kotlinTypeRefiner, "kotlinTypeRefiner");
        return new TypeCheckerState(z, z2, true, typeSystemContext, kotlinTypePreparator, kotlinTypeRefiner);
    }
}
