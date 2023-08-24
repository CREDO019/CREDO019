package com.th3rdwave.safeareacontext;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SafeAreaProviderManager.kt */
@Metadata(m182k = 3, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes4.dex */
/* synthetic */ class SafeAreaProviderManager$addEventEmitters$1 extends FunctionReferenceImpl implements Function3<SafeAreaProvider, EdgeInsets, Rect, Unit> {
    public static final SafeAreaProviderManager$addEventEmitters$1 INSTANCE = new SafeAreaProviderManager$addEventEmitters$1();

    SafeAreaProviderManager$addEventEmitters$1() {
        super(3, SafeAreaProviderManagerKt.class, "handleOnInsetsChange", "handleOnInsetsChange(Lcom/th3rdwave/safeareacontext/SafeAreaProvider;Lcom/th3rdwave/safeareacontext/EdgeInsets;Lcom/th3rdwave/safeareacontext/Rect;)V", 1);
    }

    @Override // kotlin.jvm.functions.Function3
    public /* bridge */ /* synthetic */ Unit invoke(SafeAreaProvider safeAreaProvider, EdgeInsets edgeInsets, Rect rect) {
        invoke2(safeAreaProvider, edgeInsets, rect);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final void invoke2(SafeAreaProvider p0, EdgeInsets p1, Rect p2) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        Intrinsics.checkNotNullParameter(p1, "p1");
        Intrinsics.checkNotNullParameter(p2, "p2");
        SafeAreaProviderManagerKt.handleOnInsetsChange(p0, p1, p2);
    }
}
