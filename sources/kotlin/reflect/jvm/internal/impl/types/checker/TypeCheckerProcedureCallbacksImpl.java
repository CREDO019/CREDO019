package kotlin.reflect.jvm.internal.impl.types.checker;

import com.google.android.exoplayer2.source.rtsp.SessionDescription;
import com.onesignal.NotificationBundleProcessor;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructor;

/* loaded from: classes5.dex */
class TypeCheckerProcedureCallbacksImpl implements TypeCheckingProcedureCallbacks {
    private static /* synthetic */ void $$$reportNull$$$0(int r3) {
        Object[] objArr = new Object[3];
        switch (r3) {
            case 1:
            case 4:
                objArr[0] = "b";
                break;
            case 2:
            case 7:
                objArr[0] = "typeCheckingProcedure";
                break;
            case 3:
            default:
                objArr[0] = NotificationBundleProcessor.PUSH_ADDITIONAL_DATA_KEY;
                break;
            case 5:
            case 10:
                objArr[0] = "subtype";
                break;
            case 6:
            case 11:
                objArr[0] = "supertype";
                break;
            case 8:
                objArr[0] = SessionDescription.ATTR_TYPE;
                break;
            case 9:
                objArr[0] = "typeProjection";
                break;
        }
        objArr[1] = "kotlin/reflect/jvm/internal/impl/types/checker/TypeCheckerProcedureCallbacksImpl";
        switch (r3) {
            case 3:
            case 4:
                objArr[2] = "assertEqualTypeConstructors";
                break;
            case 5:
            case 6:
            case 7:
                objArr[2] = "assertSubtype";
                break;
            case 8:
            case 9:
                objArr[2] = "capture";
                break;
            case 10:
            case 11:
                objArr[2] = "noCorrespondingSupertype";
                break;
            default:
                objArr[2] = "assertEqualTypes";
                break;
        }
        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", objArr));
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.checker.TypeCheckingProcedureCallbacks
    public boolean assertEqualTypeConstructors(TypeConstructor typeConstructor, TypeConstructor typeConstructor2) {
        if (typeConstructor == null) {
            $$$reportNull$$$0(3);
        }
        if (typeConstructor2 == null) {
            $$$reportNull$$$0(4);
        }
        return typeConstructor.equals(typeConstructor2);
    }
}
