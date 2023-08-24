package expo.modules.kotlin.objects;

import com.google.android.exoplayer2.text.ttml.TtmlNode;
import expo.modules.kotlin.functions.SyncFunctionComponent;
import expo.modules.kotlin.types.AnyType;
import expo.modules.kotlin.types.AnyTypeKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Functions;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PropertyComponentBuilder.kt */
@Metadata(m184d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\u0010\u001a\u00020\u0011J\"\u0010\u0012\u001a\u00020\u0000\"\u0004\b\u0000\u0010\u00132\u000e\b\u0004\u0010\u0014\u001a\b\u0012\u0004\u0012\u0002H\u00130\u0015H\u0086\bø\u0001\u0000J9\u0010\u0016\u001a\u00020\u0000\"\u0006\b\u0000\u0010\u0013\u0018\u00012#\b\u0004\u0010\u0014\u001a\u001d\u0012\u0013\u0012\u0011H\u0013¢\u0006\f\b\u0018\u0012\b\b\u0002\u0012\u0004\b\b(\u0019\u0012\u0004\u0012\u00020\u001a0\u0017H\u0086\bø\u0001\u0000R\u001c\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u001c\u0010\r\u001a\u0004\u0018\u00010\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\b\"\u0004\b\u000f\u0010\n\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\u001b"}, m183d2 = {"Lexpo/modules/kotlin/objects/PropertyComponentBuilder;", "", "name", "", "(Ljava/lang/String;)V", "getter", "Lexpo/modules/kotlin/functions/SyncFunctionComponent;", "getGetter", "()Lexpo/modules/kotlin/functions/SyncFunctionComponent;", "setGetter", "(Lexpo/modules/kotlin/functions/SyncFunctionComponent;)V", "getName", "()Ljava/lang/String;", "setter", "getSetter", "setSetter", "build", "Lexpo/modules/kotlin/objects/PropertyComponent;", "get", "T", TtmlNode.TAG_BODY, "Lkotlin/Function0;", "set", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "newValue", "", "expo-modules-core_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes4.dex */
public final class PropertyComponentBuilder {
    private SyncFunctionComponent getter;
    private final String name;
    private SyncFunctionComponent setter;

    public PropertyComponentBuilder(String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        this.name = name;
    }

    public final String getName() {
        return this.name;
    }

    public final SyncFunctionComponent getGetter() {
        return this.getter;
    }

    public final void setGetter(SyncFunctionComponent syncFunctionComponent) {
        this.getter = syncFunctionComponent;
    }

    public final SyncFunctionComponent getSetter() {
        return this.setter;
    }

    public final void setSetter(SyncFunctionComponent syncFunctionComponent) {
        this.setter = syncFunctionComponent;
    }

    public final <T> PropertyComponentBuilder get(Functions<? extends T> body) {
        Intrinsics.checkNotNullParameter(body, "body");
        setGetter(new SyncFunctionComponent("get", new AnyType[0], new PropertyComponentBuilder$get$1$1(body)));
        return this;
    }

    public final /* synthetic */ <T> PropertyComponentBuilder set(final Function1<? super T, Unit> body) {
        Intrinsics.checkNotNullParameter(body, "body");
        Intrinsics.reifiedOperationMarker(6, "T");
        AnyType[] anyTypeArr = {AnyTypeKt.toAnyType(null)};
        Intrinsics.needClassReification();
        setSetter(new SyncFunctionComponent("set", anyTypeArr, new Function1<Object[], Unit>() { // from class: expo.modules.kotlin.objects.PropertyComponentBuilder$set$1$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Object[] objArr) {
                invoke2(objArr);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(Object[] it) {
                Intrinsics.checkNotNullParameter(it, "it");
                Function1<T, Unit> function1 = body;
                Object obj = it[0];
                Intrinsics.reifiedOperationMarker(1, "T");
                function1.invoke(obj);
            }
        }));
        return this;
    }

    public final PropertyComponent build() {
        return new PropertyComponent(this.name, this.getter, this.setter);
    }
}
