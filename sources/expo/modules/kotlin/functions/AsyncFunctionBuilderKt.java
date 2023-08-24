package expo.modules.kotlin.functions;

import expo.modules.kotlin.types.AnyType;
import expo.modules.kotlin.types.AnyTypeKt;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.functions.Function5;
import kotlin.jvm.functions.Function6;
import kotlin.jvm.functions.Function7;
import kotlin.jvm.functions.Function8;
import kotlin.jvm.functions.Function9;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AsyncFunctionBuilder.kt */
@Metadata(m184d1 = {"\u0000^\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a=\u0010\u0000\u001a\u00020\u0001\"\u0006\b\u0000\u0010\u0002\u0018\u0001*\u00020\u00032\u001e\b\u0004\u0010\u0004\u001a\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0005H\u0086\fø\u0001\u0000¢\u0006\u0002\u0010\b\u001aK\u0010\u0000\u001a\u00020\u0001\"\u0006\b\u0000\u0010\u0002\u0018\u0001\"\u0006\b\u0001\u0010\t\u0018\u0001*\u00020\u00032$\b\u0004\u0010\u0004\u001a\u001e\b\u0001\u0012\u0004\u0012\u0002H\t\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00070\nH\u0086\fø\u0001\u0000¢\u0006\u0002\u0010\u000b\u001aY\u0010\u0000\u001a\u00020\u0001\"\u0006\b\u0000\u0010\u0002\u0018\u0001\"\u0006\b\u0001\u0010\t\u0018\u0001\"\u0006\b\u0002\u0010\f\u0018\u0001*\u00020\u00032*\b\u0004\u0010\u0004\u001a$\b\u0001\u0012\u0004\u0012\u0002H\t\u0012\u0004\u0012\u0002H\f\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00070\rH\u0086\fø\u0001\u0000¢\u0006\u0002\u0010\u000e\u001ag\u0010\u0000\u001a\u00020\u0001\"\u0006\b\u0000\u0010\u0002\u0018\u0001\"\u0006\b\u0001\u0010\t\u0018\u0001\"\u0006\b\u0002\u0010\f\u0018\u0001\"\u0006\b\u0003\u0010\u000f\u0018\u0001*\u00020\u000320\b\u0004\u0010\u0004\u001a*\b\u0001\u0012\u0004\u0012\u0002H\t\u0012\u0004\u0012\u0002H\f\u0012\u0004\u0012\u0002H\u000f\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0010H\u0086\fø\u0001\u0000¢\u0006\u0002\u0010\u0011\u001au\u0010\u0000\u001a\u00020\u0001\"\u0006\b\u0000\u0010\u0002\u0018\u0001\"\u0006\b\u0001\u0010\t\u0018\u0001\"\u0006\b\u0002\u0010\f\u0018\u0001\"\u0006\b\u0003\u0010\u000f\u0018\u0001\"\u0006\b\u0004\u0010\u0012\u0018\u0001*\u00020\u000326\b\u0004\u0010\u0004\u001a0\b\u0001\u0012\u0004\u0012\u0002H\t\u0012\u0004\u0012\u0002H\f\u0012\u0004\u0012\u0002H\u000f\u0012\u0004\u0012\u0002H\u0012\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0013H\u0086\fø\u0001\u0000¢\u0006\u0002\u0010\u0014\u001a\u0083\u0001\u0010\u0000\u001a\u00020\u0001\"\u0006\b\u0000\u0010\u0002\u0018\u0001\"\u0006\b\u0001\u0010\t\u0018\u0001\"\u0006\b\u0002\u0010\f\u0018\u0001\"\u0006\b\u0003\u0010\u000f\u0018\u0001\"\u0006\b\u0004\u0010\u0012\u0018\u0001\"\u0006\b\u0005\u0010\u0015\u0018\u0001*\u00020\u00032<\b\u0004\u0010\u0004\u001a6\b\u0001\u0012\u0004\u0012\u0002H\t\u0012\u0004\u0012\u0002H\f\u0012\u0004\u0012\u0002H\u000f\u0012\u0004\u0012\u0002H\u0012\u0012\u0004\u0012\u0002H\u0015\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0016H\u0086\fø\u0001\u0000¢\u0006\u0002\u0010\u0017\u001a\u0091\u0001\u0010\u0000\u001a\u00020\u0001\"\u0006\b\u0000\u0010\u0002\u0018\u0001\"\u0006\b\u0001\u0010\t\u0018\u0001\"\u0006\b\u0002\u0010\f\u0018\u0001\"\u0006\b\u0003\u0010\u000f\u0018\u0001\"\u0006\b\u0004\u0010\u0012\u0018\u0001\"\u0006\b\u0005\u0010\u0015\u0018\u0001\"\u0006\b\u0006\u0010\u0018\u0018\u0001*\u00020\u00032B\b\u0004\u0010\u0004\u001a<\b\u0001\u0012\u0004\u0012\u0002H\t\u0012\u0004\u0012\u0002H\f\u0012\u0004\u0012\u0002H\u000f\u0012\u0004\u0012\u0002H\u0012\u0012\u0004\u0012\u0002H\u0015\u0012\u0004\u0012\u0002H\u0018\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0019H\u0086\fø\u0001\u0000¢\u0006\u0002\u0010\u001a\u001a\u009f\u0001\u0010\u0000\u001a\u00020\u0001\"\u0006\b\u0000\u0010\u0002\u0018\u0001\"\u0006\b\u0001\u0010\t\u0018\u0001\"\u0006\b\u0002\u0010\f\u0018\u0001\"\u0006\b\u0003\u0010\u000f\u0018\u0001\"\u0006\b\u0004\u0010\u0012\u0018\u0001\"\u0006\b\u0005\u0010\u0015\u0018\u0001\"\u0006\b\u0006\u0010\u0018\u0018\u0001\"\u0006\b\u0007\u0010\u001b\u0018\u0001*\u00020\u00032H\b\u0004\u0010\u0004\u001aB\b\u0001\u0012\u0004\u0012\u0002H\t\u0012\u0004\u0012\u0002H\f\u0012\u0004\u0012\u0002H\u000f\u0012\u0004\u0012\u0002H\u0012\u0012\u0004\u0012\u0002H\u0015\u0012\u0004\u0012\u0002H\u0018\u0012\u0004\u0012\u0002H\u001b\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u001cH\u0086\fø\u0001\u0000¢\u0006\u0002\u0010\u001d\u001a\u00ad\u0001\u0010\u0000\u001a\u00020\u0001\"\u0006\b\u0000\u0010\u0002\u0018\u0001\"\u0006\b\u0001\u0010\t\u0018\u0001\"\u0006\b\u0002\u0010\f\u0018\u0001\"\u0006\b\u0003\u0010\u000f\u0018\u0001\"\u0006\b\u0004\u0010\u0012\u0018\u0001\"\u0006\b\u0005\u0010\u0015\u0018\u0001\"\u0006\b\u0006\u0010\u0018\u0018\u0001\"\u0006\b\u0007\u0010\u001b\u0018\u0001\"\u0006\b\b\u0010\u001e\u0018\u0001*\u00020\u00032N\b\u0004\u0010\u0004\u001aH\b\u0001\u0012\u0004\u0012\u0002H\t\u0012\u0004\u0012\u0002H\f\u0012\u0004\u0012\u0002H\u000f\u0012\u0004\u0012\u0002H\u0012\u0012\u0004\u0012\u0002H\u0015\u0012\u0004\u0012\u0002H\u0018\u0012\u0004\u0012\u0002H\u001b\u0012\u0004\u0012\u0002H\u001e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u001fH\u0086\fø\u0001\u0000¢\u0006\u0002\u0010 \u0082\u0002\u0004\n\u0002\b\u0019¨\u0006!"}, m183d2 = {"Coroutine", "Lexpo/modules/kotlin/functions/SuspendFunctionComponent;", "R", "Lexpo/modules/kotlin/functions/AsyncFunctionBuilder;", "block", "Lkotlin/Function1;", "Lkotlin/coroutines/Continuation;", "", "(Lexpo/modules/kotlin/functions/AsyncFunctionBuilder;Lkotlin/jvm/functions/Function1;)Lexpo/modules/kotlin/functions/SuspendFunctionComponent;", "P0", "Lkotlin/Function2;", "(Lexpo/modules/kotlin/functions/AsyncFunctionBuilder;Lkotlin/jvm/functions/Function2;)Lexpo/modules/kotlin/functions/SuspendFunctionComponent;", "P1", "Lkotlin/Function3;", "(Lexpo/modules/kotlin/functions/AsyncFunctionBuilder;Lkotlin/jvm/functions/Function3;)Lexpo/modules/kotlin/functions/SuspendFunctionComponent;", "P2", "Lkotlin/Function4;", "(Lexpo/modules/kotlin/functions/AsyncFunctionBuilder;Lkotlin/jvm/functions/Function4;)Lexpo/modules/kotlin/functions/SuspendFunctionComponent;", "P3", "Lkotlin/Function5;", "(Lexpo/modules/kotlin/functions/AsyncFunctionBuilder;Lkotlin/jvm/functions/Function5;)Lexpo/modules/kotlin/functions/SuspendFunctionComponent;", "P4", "Lkotlin/Function6;", "(Lexpo/modules/kotlin/functions/AsyncFunctionBuilder;Lkotlin/jvm/functions/Function6;)Lexpo/modules/kotlin/functions/SuspendFunctionComponent;", "P5", "Lkotlin/Function7;", "(Lexpo/modules/kotlin/functions/AsyncFunctionBuilder;Lkotlin/jvm/functions/Function7;)Lexpo/modules/kotlin/functions/SuspendFunctionComponent;", "P6", "Lkotlin/Function8;", "(Lexpo/modules/kotlin/functions/AsyncFunctionBuilder;Lkotlin/jvm/functions/Function8;)Lexpo/modules/kotlin/functions/SuspendFunctionComponent;", "P7", "Lkotlin/Function9;", "(Lexpo/modules/kotlin/functions/AsyncFunctionBuilder;Lkotlin/jvm/functions/Function9;)Lexpo/modules/kotlin/functions/SuspendFunctionComponent;", "expo-modules-core_release"}, m182k = 2, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes4.dex */
public final class AsyncFunctionBuilderKt {
    public static final /* synthetic */ <R> SuspendFunctionComponent Coroutine(AsyncFunctionBuilder asyncFunctionBuilder, Function1<? super Continuation<? super R>, ? extends Object> block) {
        Intrinsics.checkNotNullParameter(asyncFunctionBuilder, "<this>");
        Intrinsics.checkNotNullParameter(block, "block");
        SuspendFunctionComponent suspendFunctionComponent = new SuspendFunctionComponent(asyncFunctionBuilder.getName(), new AnyType[0], new AsyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$1(block, null));
        asyncFunctionBuilder.setSuspendFunctionComponent(suspendFunctionComponent);
        return suspendFunctionComponent;
    }

    public static final /* synthetic */ <R, P0> SuspendFunctionComponent Coroutine(AsyncFunctionBuilder asyncFunctionBuilder, Function2<? super P0, ? super Continuation<? super R>, ? extends Object> block) {
        Intrinsics.checkNotNullParameter(asyncFunctionBuilder, "<this>");
        Intrinsics.checkNotNullParameter(block, "block");
        String name = asyncFunctionBuilder.getName();
        Intrinsics.reifiedOperationMarker(6, "P0");
        AnyType[] anyTypeArr = {AnyTypeKt.toAnyType(null)};
        Intrinsics.needClassReification();
        SuspendFunctionComponent suspendFunctionComponent = new SuspendFunctionComponent(name, anyTypeArr, new AsyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$2(block, null));
        asyncFunctionBuilder.setSuspendFunctionComponent(suspendFunctionComponent);
        return suspendFunctionComponent;
    }

    public static final /* synthetic */ <R, P0, P1> SuspendFunctionComponent Coroutine(AsyncFunctionBuilder asyncFunctionBuilder, Function3<? super P0, ? super P1, ? super Continuation<? super R>, ? extends Object> block) {
        Intrinsics.checkNotNullParameter(asyncFunctionBuilder, "<this>");
        Intrinsics.checkNotNullParameter(block, "block");
        String name = asyncFunctionBuilder.getName();
        Intrinsics.reifiedOperationMarker(6, "P0");
        AnyType[] anyTypeArr = {AnyTypeKt.toAnyType(null)};
        Intrinsics.needClassReification();
        SuspendFunctionComponent suspendFunctionComponent = new SuspendFunctionComponent(name, anyTypeArr, new AsyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$3(block, null));
        asyncFunctionBuilder.setSuspendFunctionComponent(suspendFunctionComponent);
        return suspendFunctionComponent;
    }

    public static final /* synthetic */ <R, P0, P1, P2> SuspendFunctionComponent Coroutine(AsyncFunctionBuilder asyncFunctionBuilder, Function4<? super P0, ? super P1, ? super P2, ? super Continuation<? super R>, ? extends Object> block) {
        Intrinsics.checkNotNullParameter(asyncFunctionBuilder, "<this>");
        Intrinsics.checkNotNullParameter(block, "block");
        String name = asyncFunctionBuilder.getName();
        Intrinsics.reifiedOperationMarker(6, "P0");
        AnyType[] anyTypeArr = {AnyTypeKt.toAnyType(null)};
        Intrinsics.needClassReification();
        SuspendFunctionComponent suspendFunctionComponent = new SuspendFunctionComponent(name, anyTypeArr, new AsyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$4(block, null));
        asyncFunctionBuilder.setSuspendFunctionComponent(suspendFunctionComponent);
        return suspendFunctionComponent;
    }

    public static final /* synthetic */ <R, P0, P1, P2, P3> SuspendFunctionComponent Coroutine(AsyncFunctionBuilder asyncFunctionBuilder, Function5<? super P0, ? super P1, ? super P2, ? super P3, ? super Continuation<? super R>, ? extends Object> block) {
        Intrinsics.checkNotNullParameter(asyncFunctionBuilder, "<this>");
        Intrinsics.checkNotNullParameter(block, "block");
        String name = asyncFunctionBuilder.getName();
        Intrinsics.reifiedOperationMarker(6, "P0");
        AnyType[] anyTypeArr = {AnyTypeKt.toAnyType(null)};
        Intrinsics.needClassReification();
        SuspendFunctionComponent suspendFunctionComponent = new SuspendFunctionComponent(name, anyTypeArr, new AsyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$5(block, null));
        asyncFunctionBuilder.setSuspendFunctionComponent(suspendFunctionComponent);
        return suspendFunctionComponent;
    }

    public static final /* synthetic */ <R, P0, P1, P2, P3, P4> SuspendFunctionComponent Coroutine(AsyncFunctionBuilder asyncFunctionBuilder, Function6<? super P0, ? super P1, ? super P2, ? super P3, ? super P4, ? super Continuation<? super R>, ? extends Object> block) {
        Intrinsics.checkNotNullParameter(asyncFunctionBuilder, "<this>");
        Intrinsics.checkNotNullParameter(block, "block");
        String name = asyncFunctionBuilder.getName();
        Intrinsics.reifiedOperationMarker(6, "P0");
        AnyType[] anyTypeArr = {AnyTypeKt.toAnyType(null)};
        Intrinsics.needClassReification();
        SuspendFunctionComponent suspendFunctionComponent = new SuspendFunctionComponent(name, anyTypeArr, new AsyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$6(block, null));
        asyncFunctionBuilder.setSuspendFunctionComponent(suspendFunctionComponent);
        return suspendFunctionComponent;
    }

    public static final /* synthetic */ <R, P0, P1, P2, P3, P4, P5> SuspendFunctionComponent Coroutine(AsyncFunctionBuilder asyncFunctionBuilder, Function7<? super P0, ? super P1, ? super P2, ? super P3, ? super P4, ? super P5, ? super Continuation<? super R>, ? extends Object> block) {
        Intrinsics.checkNotNullParameter(asyncFunctionBuilder, "<this>");
        Intrinsics.checkNotNullParameter(block, "block");
        String name = asyncFunctionBuilder.getName();
        Intrinsics.reifiedOperationMarker(6, "P0");
        AnyType[] anyTypeArr = {AnyTypeKt.toAnyType(null)};
        Intrinsics.needClassReification();
        SuspendFunctionComponent suspendFunctionComponent = new SuspendFunctionComponent(name, anyTypeArr, new AsyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$7(block, null));
        asyncFunctionBuilder.setSuspendFunctionComponent(suspendFunctionComponent);
        return suspendFunctionComponent;
    }

    public static final /* synthetic */ <R, P0, P1, P2, P3, P4, P5, P6> SuspendFunctionComponent Coroutine(AsyncFunctionBuilder asyncFunctionBuilder, Function8<? super P0, ? super P1, ? super P2, ? super P3, ? super P4, ? super P5, ? super P6, ? super Continuation<? super R>, ? extends Object> block) {
        Intrinsics.checkNotNullParameter(asyncFunctionBuilder, "<this>");
        Intrinsics.checkNotNullParameter(block, "block");
        String name = asyncFunctionBuilder.getName();
        Intrinsics.reifiedOperationMarker(6, "P0");
        AnyType[] anyTypeArr = {AnyTypeKt.toAnyType(null)};
        Intrinsics.needClassReification();
        SuspendFunctionComponent suspendFunctionComponent = new SuspendFunctionComponent(name, anyTypeArr, new AsyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$8(block, null));
        asyncFunctionBuilder.setSuspendFunctionComponent(suspendFunctionComponent);
        return suspendFunctionComponent;
    }

    public static final /* synthetic */ <R, P0, P1, P2, P3, P4, P5, P6, P7> SuspendFunctionComponent Coroutine(AsyncFunctionBuilder asyncFunctionBuilder, Function9<? super P0, ? super P1, ? super P2, ? super P3, ? super P4, ? super P5, ? super P6, ? super P7, ? super Continuation<? super R>, ? extends Object> block) {
        Intrinsics.checkNotNullParameter(asyncFunctionBuilder, "<this>");
        Intrinsics.checkNotNullParameter(block, "block");
        String name = asyncFunctionBuilder.getName();
        Intrinsics.reifiedOperationMarker(6, "P0");
        AnyType[] anyTypeArr = {AnyTypeKt.toAnyType(null)};
        Intrinsics.needClassReification();
        SuspendFunctionComponent suspendFunctionComponent = new SuspendFunctionComponent(name, anyTypeArr, new AsyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$9(block, null));
        asyncFunctionBuilder.setSuspendFunctionComponent(suspendFunctionComponent);
        return suspendFunctionComponent;
    }
}
