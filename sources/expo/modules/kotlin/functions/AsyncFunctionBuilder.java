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
@Metadata(m184d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J9\u0010\u0010\u001a\u00020\n\"\u0006\b\u0000\u0010\u0011\u0018\u00012\u001e\b\u0004\u0010\u0012\u001a\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00110\u0014\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0013H\u0086\bø\u0001\u0000¢\u0006\u0002\u0010\u0015JV\u0010\u0010\u001a\u00020\n\"\u0006\b\u0000\u0010\u0011\u0018\u0001\"\u0006\b\u0001\u0010\u0016\u0018\u000123\b\u0004\u0010\u0012\u001a-\b\u0001\u0012\u0013\u0012\u0011H\u0016¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b(\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00110\u0014\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0017H\u0086\bø\u0001\u0000¢\u0006\u0002\u0010\u001aJs\u0010\u0010\u001a\u00020\n\"\u0006\b\u0000\u0010\u0011\u0018\u0001\"\u0006\b\u0001\u0010\u0016\u0018\u0001\"\u0006\b\u0002\u0010\u001b\u0018\u00012H\b\u0004\u0010\u0012\u001aB\b\u0001\u0012\u0013\u0012\u0011H\u0016¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b(\u0019\u0012\u0013\u0012\u0011H\u001b¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b(\u001d\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00110\u0014\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u001cH\u0086\bø\u0001\u0000¢\u0006\u0002\u0010\u001eJ\u0090\u0001\u0010\u0010\u001a\u00020\n\"\u0006\b\u0000\u0010\u0011\u0018\u0001\"\u0006\b\u0001\u0010\u0016\u0018\u0001\"\u0006\b\u0002\u0010\u001b\u0018\u0001\"\u0006\b\u0003\u0010\u001f\u0018\u00012]\b\u0004\u0010\u0012\u001aW\b\u0001\u0012\u0013\u0012\u0011H\u0016¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b(\u0019\u0012\u0013\u0012\u0011H\u001b¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b(\u001d\u0012\u0013\u0012\u0011H\u001f¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b(!\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00110\u0014\u0012\u0006\u0012\u0004\u0018\u00010\u00010 H\u0086\bø\u0001\u0000¢\u0006\u0002\u0010\"J\u00ad\u0001\u0010\u0010\u001a\u00020\n\"\u0006\b\u0000\u0010\u0011\u0018\u0001\"\u0006\b\u0001\u0010\u0016\u0018\u0001\"\u0006\b\u0002\u0010\u001b\u0018\u0001\"\u0006\b\u0003\u0010\u001f\u0018\u0001\"\u0006\b\u0004\u0010#\u0018\u00012r\b\u0004\u0010\u0012\u001al\b\u0001\u0012\u0013\u0012\u0011H\u0016¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b(\u0019\u0012\u0013\u0012\u0011H\u001b¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b(\u001d\u0012\u0013\u0012\u0011H\u001f¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b(!\u0012\u0013\u0012\u0011H#¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b(%\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00110\u0014\u0012\u0006\u0012\u0004\u0018\u00010\u00010$H\u0086\bø\u0001\u0000¢\u0006\u0002\u0010&JÌ\u0001\u0010\u0010\u001a\u00020\n\"\u0006\b\u0000\u0010\u0011\u0018\u0001\"\u0006\b\u0001\u0010\u0016\u0018\u0001\"\u0006\b\u0002\u0010\u001b\u0018\u0001\"\u0006\b\u0003\u0010\u001f\u0018\u0001\"\u0006\b\u0004\u0010#\u0018\u0001\"\u0006\b\u0005\u0010'\u0018\u00012\u0088\u0001\b\u0004\u0010\u0012\u001a\u0081\u0001\b\u0001\u0012\u0013\u0012\u0011H\u0016¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b(\u0019\u0012\u0013\u0012\u0011H\u001b¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b(\u001d\u0012\u0013\u0012\u0011H\u001f¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b(!\u0012\u0013\u0012\u0011H#¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b(%\u0012\u0013\u0012\u0011H'¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b()\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00110\u0014\u0012\u0006\u0012\u0004\u0018\u00010\u00010(H\u0086\bø\u0001\u0000¢\u0006\u0002\u0010*Jé\u0001\u0010\u0010\u001a\u00020\n\"\u0006\b\u0000\u0010\u0011\u0018\u0001\"\u0006\b\u0001\u0010\u0016\u0018\u0001\"\u0006\b\u0002\u0010\u001b\u0018\u0001\"\u0006\b\u0003\u0010\u001f\u0018\u0001\"\u0006\b\u0004\u0010#\u0018\u0001\"\u0006\b\u0005\u0010'\u0018\u0001\"\u0006\b\u0006\u0010+\u0018\u00012\u009d\u0001\b\u0004\u0010\u0012\u001a\u0096\u0001\b\u0001\u0012\u0013\u0012\u0011H\u0016¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b(\u0019\u0012\u0013\u0012\u0011H\u001b¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b(\u001d\u0012\u0013\u0012\u0011H\u001f¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b(!\u0012\u0013\u0012\u0011H#¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b(%\u0012\u0013\u0012\u0011H'¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b()\u0012\u0013\u0012\u0011H+¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b(-\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00110\u0014\u0012\u0006\u0012\u0004\u0018\u00010\u00010,H\u0086\bø\u0001\u0000¢\u0006\u0002\u0010.J\u0086\u0002\u0010\u0010\u001a\u00020\n\"\u0006\b\u0000\u0010\u0011\u0018\u0001\"\u0006\b\u0001\u0010\u0016\u0018\u0001\"\u0006\b\u0002\u0010\u001b\u0018\u0001\"\u0006\b\u0003\u0010\u001f\u0018\u0001\"\u0006\b\u0004\u0010#\u0018\u0001\"\u0006\b\u0005\u0010'\u0018\u0001\"\u0006\b\u0006\u0010+\u0018\u0001\"\u0006\b\u0007\u0010/\u0018\u00012²\u0001\b\u0004\u0010\u0012\u001a«\u0001\b\u0001\u0012\u0013\u0012\u0011H\u0016¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b(\u0019\u0012\u0013\u0012\u0011H\u001b¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b(\u001d\u0012\u0013\u0012\u0011H\u001f¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b(!\u0012\u0013\u0012\u0011H#¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b(%\u0012\u0013\u0012\u0011H'¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b()\u0012\u0013\u0012\u0011H+¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b(-\u0012\u0013\u0012\u0011H/¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b(1\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00110\u0014\u0012\u0006\u0012\u0004\u0018\u00010\u000100H\u0086\bø\u0001\u0000¢\u0006\u0002\u00102J£\u0002\u0010\u0010\u001a\u00020\n\"\u0006\b\u0000\u0010\u0011\u0018\u0001\"\u0006\b\u0001\u0010\u0016\u0018\u0001\"\u0006\b\u0002\u0010\u001b\u0018\u0001\"\u0006\b\u0003\u0010\u001f\u0018\u0001\"\u0006\b\u0004\u0010#\u0018\u0001\"\u0006\b\u0005\u0010'\u0018\u0001\"\u0006\b\u0006\u0010+\u0018\u0001\"\u0006\b\u0007\u0010/\u0018\u0001\"\u0006\b\b\u00103\u0018\u00012Ç\u0001\b\u0004\u0010\u0012\u001aÀ\u0001\b\u0001\u0012\u0013\u0012\u0011H\u0016¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b(\u0019\u0012\u0013\u0012\u0011H\u001b¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b(\u001d\u0012\u0013\u0012\u0011H\u001f¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b(!\u0012\u0013\u0012\u0011H#¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b(%\u0012\u0013\u0012\u0011H'¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b()\u0012\u0013\u0012\u0011H+¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b(-\u0012\u0013\u0012\u0011H/¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b(1\u0012\u0013\u0012\u0011H3¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b(5\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00110\u0014\u0012\u0006\u0012\u0004\u0018\u00010\u000104H\u0086\bø\u0001\u0000¢\u0006\u0002\u00106J\r\u00107\u001a\u00020\nH\u0000¢\u0006\u0002\b8R\u001c\u0010\u0002\u001a\u00020\u00038\u0000X\u0081\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0005\u0010\u0006\u001a\u0004\b\u0007\u0010\bR&\u0010\t\u001a\u0004\u0018\u00010\n8\u0000@\u0000X\u0081\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\u000b\u0010\u0006\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000f\u0082\u0002\u0004\n\u0002\b\u0019¨\u00069"}, m183d2 = {"Lexpo/modules/kotlin/functions/AsyncFunctionBuilder;", "", "name", "", "(Ljava/lang/String;)V", "getName$annotations", "()V", "getName", "()Ljava/lang/String;", "suspendFunctionComponent", "Lexpo/modules/kotlin/functions/SuspendFunctionComponent;", "getSuspendFunctionComponent$annotations", "getSuspendFunctionComponent", "()Lexpo/modules/kotlin/functions/SuspendFunctionComponent;", "setSuspendFunctionComponent", "(Lexpo/modules/kotlin/functions/SuspendFunctionComponent;)V", "SuspendBody", "R", "block", "Lkotlin/Function1;", "Lkotlin/coroutines/Continuation;", "(Lkotlin/jvm/functions/Function1;)Lexpo/modules/kotlin/functions/SuspendFunctionComponent;", "P0", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "p0", "(Lkotlin/jvm/functions/Function2;)Lexpo/modules/kotlin/functions/SuspendFunctionComponent;", "P1", "Lkotlin/Function3;", "p1", "(Lkotlin/jvm/functions/Function3;)Lexpo/modules/kotlin/functions/SuspendFunctionComponent;", "P2", "Lkotlin/Function4;", "p2", "(Lkotlin/jvm/functions/Function4;)Lexpo/modules/kotlin/functions/SuspendFunctionComponent;", "P3", "Lkotlin/Function5;", "p3", "(Lkotlin/jvm/functions/Function5;)Lexpo/modules/kotlin/functions/SuspendFunctionComponent;", "P4", "Lkotlin/Function6;", "p4", "(Lkotlin/jvm/functions/Function6;)Lexpo/modules/kotlin/functions/SuspendFunctionComponent;", "P5", "Lkotlin/Function7;", "p5", "(Lkotlin/jvm/functions/Function7;)Lexpo/modules/kotlin/functions/SuspendFunctionComponent;", "P6", "Lkotlin/Function8;", "p6", "(Lkotlin/jvm/functions/Function8;)Lexpo/modules/kotlin/functions/SuspendFunctionComponent;", "P7", "Lkotlin/Function9;", "p7", "(Lkotlin/jvm/functions/Function9;)Lexpo/modules/kotlin/functions/SuspendFunctionComponent;", "build", "build$expo_modules_core_release", "expo-modules-core_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes4.dex */
public final class AsyncFunctionBuilder {
    private final String name;
    private SuspendFunctionComponent suspendFunctionComponent;

    public static /* synthetic */ void getName$annotations() {
    }

    public static /* synthetic */ void getSuspendFunctionComponent$annotations() {
    }

    public AsyncFunctionBuilder(String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        this.name = name;
    }

    public final String getName() {
        return this.name;
    }

    public final SuspendFunctionComponent getSuspendFunctionComponent() {
        return this.suspendFunctionComponent;
    }

    public final void setSuspendFunctionComponent(SuspendFunctionComponent suspendFunctionComponent) {
        this.suspendFunctionComponent = suspendFunctionComponent;
    }

    public final /* synthetic */ <R> SuspendFunctionComponent SuspendBody(Function1<? super Continuation<? super R>, ? extends Object> block) {
        Intrinsics.checkNotNullParameter(block, "block");
        SuspendFunctionComponent suspendFunctionComponent = new SuspendFunctionComponent(getName(), new AnyType[0], new AsyncFunctionBuilder$SuspendBody$1(block, null));
        setSuspendFunctionComponent(suspendFunctionComponent);
        return suspendFunctionComponent;
    }

    public final /* synthetic */ <R, P0> SuspendFunctionComponent SuspendBody(Function2<? super P0, ? super Continuation<? super R>, ? extends Object> block) {
        Intrinsics.checkNotNullParameter(block, "block");
        String name = getName();
        Intrinsics.reifiedOperationMarker(6, "P0");
        AnyType[] anyTypeArr = {AnyTypeKt.toAnyType(null)};
        Intrinsics.needClassReification();
        SuspendFunctionComponent suspendFunctionComponent = new SuspendFunctionComponent(name, anyTypeArr, new AsyncFunctionBuilder$SuspendBody$3(block, null));
        setSuspendFunctionComponent(suspendFunctionComponent);
        return suspendFunctionComponent;
    }

    public final /* synthetic */ <R, P0, P1> SuspendFunctionComponent SuspendBody(Function3<? super P0, ? super P1, ? super Continuation<? super R>, ? extends Object> block) {
        Intrinsics.checkNotNullParameter(block, "block");
        String name = getName();
        Intrinsics.reifiedOperationMarker(6, "P0");
        AnyType[] anyTypeArr = {AnyTypeKt.toAnyType(null)};
        Intrinsics.needClassReification();
        SuspendFunctionComponent suspendFunctionComponent = new SuspendFunctionComponent(name, anyTypeArr, new AsyncFunctionBuilder$SuspendBody$5(block, null));
        setSuspendFunctionComponent(suspendFunctionComponent);
        return suspendFunctionComponent;
    }

    public final /* synthetic */ <R, P0, P1, P2> SuspendFunctionComponent SuspendBody(Function4<? super P0, ? super P1, ? super P2, ? super Continuation<? super R>, ? extends Object> block) {
        Intrinsics.checkNotNullParameter(block, "block");
        String name = getName();
        Intrinsics.reifiedOperationMarker(6, "P0");
        AnyType[] anyTypeArr = {AnyTypeKt.toAnyType(null)};
        Intrinsics.needClassReification();
        SuspendFunctionComponent suspendFunctionComponent = new SuspendFunctionComponent(name, anyTypeArr, new AsyncFunctionBuilder$SuspendBody$7(block, null));
        setSuspendFunctionComponent(suspendFunctionComponent);
        return suspendFunctionComponent;
    }

    public final /* synthetic */ <R, P0, P1, P2, P3> SuspendFunctionComponent SuspendBody(Function5<? super P0, ? super P1, ? super P2, ? super P3, ? super Continuation<? super R>, ? extends Object> block) {
        Intrinsics.checkNotNullParameter(block, "block");
        String name = getName();
        Intrinsics.reifiedOperationMarker(6, "P0");
        AnyType[] anyTypeArr = {AnyTypeKt.toAnyType(null)};
        Intrinsics.needClassReification();
        SuspendFunctionComponent suspendFunctionComponent = new SuspendFunctionComponent(name, anyTypeArr, new AsyncFunctionBuilder$SuspendBody$9(block, null));
        setSuspendFunctionComponent(suspendFunctionComponent);
        return suspendFunctionComponent;
    }

    public final /* synthetic */ <R, P0, P1, P2, P3, P4> SuspendFunctionComponent SuspendBody(Function6<? super P0, ? super P1, ? super P2, ? super P3, ? super P4, ? super Continuation<? super R>, ? extends Object> block) {
        Intrinsics.checkNotNullParameter(block, "block");
        String name = getName();
        Intrinsics.reifiedOperationMarker(6, "P0");
        AnyType[] anyTypeArr = {AnyTypeKt.toAnyType(null)};
        Intrinsics.needClassReification();
        SuspendFunctionComponent suspendFunctionComponent = new SuspendFunctionComponent(name, anyTypeArr, new AsyncFunctionBuilder$SuspendBody$11(block, null));
        setSuspendFunctionComponent(suspendFunctionComponent);
        return suspendFunctionComponent;
    }

    public final /* synthetic */ <R, P0, P1, P2, P3, P4, P5> SuspendFunctionComponent SuspendBody(Function7<? super P0, ? super P1, ? super P2, ? super P3, ? super P4, ? super P5, ? super Continuation<? super R>, ? extends Object> block) {
        Intrinsics.checkNotNullParameter(block, "block");
        String name = getName();
        Intrinsics.reifiedOperationMarker(6, "P0");
        AnyType[] anyTypeArr = {AnyTypeKt.toAnyType(null)};
        Intrinsics.needClassReification();
        SuspendFunctionComponent suspendFunctionComponent = new SuspendFunctionComponent(name, anyTypeArr, new AsyncFunctionBuilder$SuspendBody$13(block, null));
        setSuspendFunctionComponent(suspendFunctionComponent);
        return suspendFunctionComponent;
    }

    public final /* synthetic */ <R, P0, P1, P2, P3, P4, P5, P6> SuspendFunctionComponent SuspendBody(Function8<? super P0, ? super P1, ? super P2, ? super P3, ? super P4, ? super P5, ? super P6, ? super Continuation<? super R>, ? extends Object> block) {
        Intrinsics.checkNotNullParameter(block, "block");
        String name = getName();
        Intrinsics.reifiedOperationMarker(6, "P0");
        AnyType[] anyTypeArr = {AnyTypeKt.toAnyType(null)};
        Intrinsics.needClassReification();
        SuspendFunctionComponent suspendFunctionComponent = new SuspendFunctionComponent(name, anyTypeArr, new AsyncFunctionBuilder$SuspendBody$15(block, null));
        setSuspendFunctionComponent(suspendFunctionComponent);
        return suspendFunctionComponent;
    }

    public final /* synthetic */ <R, P0, P1, P2, P3, P4, P5, P6, P7> SuspendFunctionComponent SuspendBody(Function9<? super P0, ? super P1, ? super P2, ? super P3, ? super P4, ? super P5, ? super P6, ? super P7, ? super Continuation<? super R>, ? extends Object> block) {
        Intrinsics.checkNotNullParameter(block, "block");
        String name = getName();
        Intrinsics.reifiedOperationMarker(6, "P0");
        AnyType[] anyTypeArr = {AnyTypeKt.toAnyType(null)};
        Intrinsics.needClassReification();
        SuspendFunctionComponent suspendFunctionComponent = new SuspendFunctionComponent(name, anyTypeArr, new AsyncFunctionBuilder$SuspendBody$17(block, null));
        setSuspendFunctionComponent(suspendFunctionComponent);
        return suspendFunctionComponent;
    }

    public final SuspendFunctionComponent build$expo_modules_core_release() {
        SuspendFunctionComponent suspendFunctionComponent = this.suspendFunctionComponent;
        if (suspendFunctionComponent != null) {
            return suspendFunctionComponent;
        }
        throw new IllegalArgumentException("Required value was null.".toString());
    }
}
