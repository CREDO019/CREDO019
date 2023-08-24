package kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReference;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KDeclarationContainer;
import kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.signatureEnhancement;
import kotlin.reflect.jvm.internal.impl.types.UnwrappedType;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: signatureEnhancement.kt */
/* loaded from: classes5.dex */
public /* synthetic */ class SignatureEnhancement$SignatureParts$enhance$containsFunctionN$1 extends FunctionReference implements Function1<UnwrappedType, Boolean> {
    public static final SignatureEnhancement$SignatureParts$enhance$containsFunctionN$1 INSTANCE = new SignatureEnhancement$SignatureParts$enhance$containsFunctionN$1();

    SignatureEnhancement$SignatureParts$enhance$containsFunctionN$1() {
        super(1);
    }

    @Override // kotlin.jvm.internal.CallableReference, kotlin.reflect.KCallable
    public final String getName() {
        return "containsFunctionN";
    }

    @Override // kotlin.jvm.internal.CallableReference
    public final String getSignature() {
        return "enhance$containsFunctionN(Lorg/jetbrains/kotlin/types/UnwrappedType;)Z";
    }

    @Override // kotlin.jvm.internal.CallableReference
    public final KDeclarationContainer getOwner() {
        return Reflection.getOrCreateKotlinClass(Intrinsics.Kotlin.class);
    }

    @Override // kotlin.jvm.functions.Function1
    public final Boolean invoke(UnwrappedType p0) {
        boolean enhance$containsFunctionN;
        Intrinsics.checkNotNullParameter(p0, "p0");
        enhance$containsFunctionN = signatureEnhancement.SignatureParts.enhance$containsFunctionN(p0);
        return Boolean.valueOf(enhance$containsFunctionN);
    }
}
