package expo.modules.kotlin.objects;

import expo.modules.kotlin.functions.SyncFunctionComponent;
import expo.modules.kotlin.jni.CppType;
import expo.modules.kotlin.jni.ExpectedType;
import expo.modules.kotlin.jni.JNIFunctionBody;
import expo.modules.kotlin.jni.JavaScriptModuleObject;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PropertyComponent.kt */
@Metadata(m184d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0007J\u000e\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\t¨\u0006\u0011"}, m183d2 = {"Lexpo/modules/kotlin/objects/PropertyComponent;", "", "name", "", "getter", "Lexpo/modules/kotlin/functions/SyncFunctionComponent;", "setter", "(Ljava/lang/String;Lexpo/modules/kotlin/functions/SyncFunctionComponent;Lexpo/modules/kotlin/functions/SyncFunctionComponent;)V", "getGetter", "()Lexpo/modules/kotlin/functions/SyncFunctionComponent;", "getName", "()Ljava/lang/String;", "getSetter", "attachToJSObject", "", "jsObject", "Lexpo/modules/kotlin/jni/JavaScriptModuleObject;", "expo-modules-core_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes4.dex */
public final class PropertyComponent {
    private final SyncFunctionComponent getter;
    private final String name;
    private final SyncFunctionComponent setter;

    public PropertyComponent(String name, SyncFunctionComponent syncFunctionComponent, SyncFunctionComponent syncFunctionComponent2) {
        Intrinsics.checkNotNullParameter(name, "name");
        this.name = name;
        this.getter = syncFunctionComponent;
        this.setter = syncFunctionComponent2;
    }

    public /* synthetic */ PropertyComponent(String str, SyncFunctionComponent syncFunctionComponent, SyncFunctionComponent syncFunctionComponent2, int r5, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (r5 & 2) != 0 ? null : syncFunctionComponent, (r5 & 4) != 0 ? null : syncFunctionComponent2);
    }

    public final String getName() {
        return this.name;
    }

    public final SyncFunctionComponent getGetter() {
        return this.getter;
    }

    public final SyncFunctionComponent getSetter() {
        return this.setter;
    }

    public final void attachToJSObject(JavaScriptModuleObject jsObject) {
        List<ExpectedType> cppRequiredTypes;
        Intrinsics.checkNotNullParameter(jsObject, "jsObject");
        ExpectedType expectedType = null;
        JNIFunctionBody jNIFunctionBody = this.getter != null ? new JNIFunctionBody() { // from class: expo.modules.kotlin.objects.PropertyComponent$$ExternalSyntheticLambda0
            @Override // expo.modules.kotlin.jni.JNIFunctionBody
            public final Object invoke(Object[] objArr) {
                Object m1675attachToJSObject$lambda0;
                m1675attachToJSObject$lambda0 = PropertyComponent.m1675attachToJSObject$lambda0(PropertyComponent.this, objArr);
                return m1675attachToJSObject$lambda0;
            }
        } : null;
        JNIFunctionBody jNIFunctionBody2 = this.setter != null ? new JNIFunctionBody() { // from class: expo.modules.kotlin.objects.PropertyComponent$$ExternalSyntheticLambda1
            @Override // expo.modules.kotlin.jni.JNIFunctionBody
            public final Object invoke(Object[] objArr) {
                Object m1676attachToJSObject$lambda1;
                m1676attachToJSObject$lambda1 = PropertyComponent.m1676attachToJSObject$lambda1(PropertyComponent.this, objArr);
                return m1676attachToJSObject$lambda1;
            }
        } : null;
        String str = this.name;
        SyncFunctionComponent syncFunctionComponent = this.setter;
        if (syncFunctionComponent != null && (cppRequiredTypes = syncFunctionComponent.getCppRequiredTypes()) != null) {
            expectedType = (ExpectedType) CollectionsKt.first((List<? extends Object>) cppRequiredTypes);
        }
        if (expectedType == null) {
            expectedType = new ExpectedType(CppType.NONE);
        }
        jsObject.registerProperty(str, expectedType, jNIFunctionBody, jNIFunctionBody2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: attachToJSObject$lambda-0  reason: not valid java name */
    public static final Object m1675attachToJSObject$lambda0(PropertyComponent this$0, Object[] it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(it, "it");
        return this$0.getter.call(new Object[0]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: attachToJSObject$lambda-1  reason: not valid java name */
    public static final Object m1676attachToJSObject$lambda1(PropertyComponent this$0, Object[] args) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(args, "args");
        this$0.setter.call(args);
        return null;
    }
}
