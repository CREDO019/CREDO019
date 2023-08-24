package expo.modules.kotlin.jni;

import com.facebook.jni.HybridData;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: JavaScriptObject.kt */
@Metadata(m184d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0001\n\u0002\b\u0004\n\u0002\u0010\u0011\n\u0002\b\u000b\b\u0017\u0018\u00002\u00020\u0001:\u0001%B\u000f\b\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J!\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0082 J!\u0010\r\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u000e2\u0006\u0010\u000b\u001a\u00020\fH\u0082 J#\u0010\u000f\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\u00002\u0006\u0010\u000b\u001a\u00020\fH\u0082 J#\u0010\u0010\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u000b\u001a\u00020\fH\u0082 J(\u0010\u0012\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\u00002\u000e\b\u0002\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00140\u0013J(\u0010\u0012\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\u00112\u000e\b\u0002\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00140\u0013J&\u0010\u0012\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u000e\b\u0002\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00140\u0013J&\u0010\u0012\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u000e2\u000e\b\u0002\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00140\u0013J&\u0010\u0012\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\f2\u000e\b\u0002\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00140\u0013J(\u0010\u0012\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\b\u0010\u0015\u001a\u0004\u0018\u00010\u00162\u000e\b\u0002\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00140\u0013J(\u0010\u0012\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\b2\u000e\b\u0002\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00140\u0013J#\u0010\u0017\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\b2\u0006\u0010\u000b\u001a\u00020\fH\u0082 J\b\u0010\u0018\u001a\u00020\u0006H\u0004J\u0011\u0010\u0019\u001a\u00020\u00112\u0006\u0010\u0007\u001a\u00020\bH\u0086 J\u0014\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\b0\u001bH\u0086 ¢\u0006\u0002\u0010\u001cJ\u0011\u0010\u001d\u001a\u00020\n2\u0006\u0010\u0007\u001a\u00020\bH\u0086 J\u0019\u0010\u001e\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0082 J\u0019\u0010\u001f\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u000eH\u0082 J\u001b\u0010 \u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\u0000H\u0082 J\u001b\u0010!\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\u0011H\u0082 J\u0018\u0010\"\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\u0000J\u0018\u0010\"\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\u0011J\u0016\u0010\"\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nJ\u0016\u0010\"\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u000eJ\u0016\u0010\"\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\fJ\u0018\u0010\"\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016J\u0018\u0010\"\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\bJ\u001b\u0010#\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\bH\u0082 J\u0011\u0010$\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0082 R\u0010\u0010\u0002\u001a\u00020\u00038\u0002X\u0083\u0004¢\u0006\u0002\n\u0000¨\u0006&"}, m183d2 = {"Lexpo/modules/kotlin/jni/JavaScriptObject;", "", "mHybridData", "Lcom/facebook/jni/HybridData;", "(Lcom/facebook/jni/HybridData;)V", "defineBoolProperty", "", "name", "", "value", "", "options", "", "defineDoubleProperty", "", "defineJSObjectProperty", "defineJSValueProperty", "Lexpo/modules/kotlin/jni/JavaScriptValue;", "defineProperty", "", "Lexpo/modules/kotlin/jni/JavaScriptObject$PropertyDescriptor;", "null", "", "defineStringProperty", "finalize", "getProperty", "getPropertyNames", "", "()[Ljava/lang/String;", "hasProperty", "setBoolProperty", "setDoubleProperty", "setJSObjectProperty", "setJSValueProperty", "setProperty", "setStringProperty", "unsetProperty", "PropertyDescriptor", "expo-modules-core_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes4.dex */
public class JavaScriptObject {
    private final HybridData mHybridData;

    private final native void defineBoolProperty(String str, boolean z, int r3);

    private final native void defineDoubleProperty(String str, double d, int r4);

    private final native void defineJSObjectProperty(String str, JavaScriptObject javaScriptObject, int r3);

    private final native void defineJSValueProperty(String str, JavaScriptValue javaScriptValue, int r3);

    private final native void defineStringProperty(String str, String str2, int r3);

    private final native void setBoolProperty(String str, boolean z);

    private final native void setDoubleProperty(String str, double d);

    private final native void setJSObjectProperty(String str, JavaScriptObject javaScriptObject);

    private final native void setJSValueProperty(String str, JavaScriptValue javaScriptValue);

    private final native void setStringProperty(String str, String str2);

    private final native void unsetProperty(String str);

    public final native JavaScriptValue getProperty(String str);

    public final native String[] getPropertyNames();

    public final native boolean hasProperty(String str);

    public JavaScriptObject(HybridData mHybridData) {
        Intrinsics.checkNotNullParameter(mHybridData, "mHybridData");
        this.mHybridData = mHybridData;
    }

    /* compiled from: JavaScriptObject.kt */
    @Metadata(m184d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\t¨\u0006\n"}, m183d2 = {"Lexpo/modules/kotlin/jni/JavaScriptObject$PropertyDescriptor;", "", "value", "", "(Ljava/lang/String;II)V", "getValue", "()I", "Configurable", "Enumerable", "Writable", "expo-modules-core_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
    /* loaded from: classes4.dex */
    public enum PropertyDescriptor {
        Configurable(1),
        Enumerable(2),
        Writable(4);
        
        private final int value;

        PropertyDescriptor(int r3) {
            this.value = r3;
        }

        public final int getValue() {
            return this.value;
        }
    }

    public final void setProperty(String name, boolean z) {
        Intrinsics.checkNotNullParameter(name, "name");
        setBoolProperty(name, z);
    }

    public final void setProperty(String name, int r4) {
        Intrinsics.checkNotNullParameter(name, "name");
        setDoubleProperty(name, r4);
    }

    public final void setProperty(String name, double d) {
        Intrinsics.checkNotNullParameter(name, "name");
        setDoubleProperty(name, d);
    }

    public final void setProperty(String name, String str) {
        Intrinsics.checkNotNullParameter(name, "name");
        setStringProperty(name, str);
    }

    public final void setProperty(String name, JavaScriptValue javaScriptValue) {
        Intrinsics.checkNotNullParameter(name, "name");
        setJSValueProperty(name, javaScriptValue);
    }

    public final void setProperty(String name, JavaScriptObject javaScriptObject) {
        Intrinsics.checkNotNullParameter(name, "name");
        setJSObjectProperty(name, javaScriptObject);
    }

    public final void setProperty(String name, Void r2) {
        Intrinsics.checkNotNullParameter(name, "name");
        unsetProperty(name);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void defineProperty$default(JavaScriptObject javaScriptObject, String str, boolean z, List list, int r4, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: defineProperty");
        }
        if ((r4 & 4) != 0) {
            list = CollectionsKt.emptyList();
        }
        javaScriptObject.defineProperty(str, z, list);
    }

    public final void defineProperty(String name, boolean z, List<? extends PropertyDescriptor> options) {
        int cppOptions;
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(options, "options");
        cppOptions = JavaScriptObjectKt.toCppOptions(options);
        defineBoolProperty(name, z, cppOptions);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void defineProperty$default(JavaScriptObject javaScriptObject, String str, int r2, List list, int r4, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: defineProperty");
        }
        if ((r4 & 4) != 0) {
            list = CollectionsKt.emptyList();
        }
        javaScriptObject.defineProperty(str, r2, (List<? extends PropertyDescriptor>) list);
    }

    public final void defineProperty(String name, int r4, List<? extends PropertyDescriptor> options) {
        int cppOptions;
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(options, "options");
        double d = r4;
        cppOptions = JavaScriptObjectKt.toCppOptions(options);
        defineDoubleProperty(name, d, cppOptions);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void defineProperty$default(JavaScriptObject javaScriptObject, String str, double d, List list, int r5, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: defineProperty");
        }
        if ((r5 & 4) != 0) {
            list = CollectionsKt.emptyList();
        }
        javaScriptObject.defineProperty(str, d, list);
    }

    public final void defineProperty(String name, double d, List<? extends PropertyDescriptor> options) {
        int cppOptions;
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(options, "options");
        cppOptions = JavaScriptObjectKt.toCppOptions(options);
        defineDoubleProperty(name, d, cppOptions);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void defineProperty$default(JavaScriptObject javaScriptObject, String str, String str2, List list, int r4, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: defineProperty");
        }
        if ((r4 & 4) != 0) {
            list = CollectionsKt.emptyList();
        }
        javaScriptObject.defineProperty(str, str2, list);
    }

    public final void defineProperty(String name, String str, List<? extends PropertyDescriptor> options) {
        int cppOptions;
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(options, "options");
        cppOptions = JavaScriptObjectKt.toCppOptions(options);
        defineStringProperty(name, str, cppOptions);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void defineProperty$default(JavaScriptObject javaScriptObject, String str, JavaScriptValue javaScriptValue, List list, int r4, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: defineProperty");
        }
        if ((r4 & 4) != 0) {
            list = CollectionsKt.emptyList();
        }
        javaScriptObject.defineProperty(str, javaScriptValue, list);
    }

    public final void defineProperty(String name, JavaScriptValue javaScriptValue, List<? extends PropertyDescriptor> options) {
        int cppOptions;
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(options, "options");
        cppOptions = JavaScriptObjectKt.toCppOptions(options);
        defineJSValueProperty(name, javaScriptValue, cppOptions);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void defineProperty$default(JavaScriptObject javaScriptObject, String str, JavaScriptObject javaScriptObject2, List list, int r4, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: defineProperty");
        }
        if ((r4 & 4) != 0) {
            list = CollectionsKt.emptyList();
        }
        javaScriptObject.defineProperty(str, javaScriptObject2, list);
    }

    public final void defineProperty(String name, JavaScriptObject javaScriptObject, List<? extends PropertyDescriptor> options) {
        int cppOptions;
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(options, "options");
        cppOptions = JavaScriptObjectKt.toCppOptions(options);
        defineJSObjectProperty(name, javaScriptObject, cppOptions);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void defineProperty$default(JavaScriptObject javaScriptObject, String str, Void r2, List list, int r4, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: defineProperty");
        }
        if ((r4 & 4) != 0) {
            list = CollectionsKt.emptyList();
        }
        javaScriptObject.defineProperty(str, r2, list);
    }

    public final void defineProperty(String name, Void r2, List<? extends PropertyDescriptor> options) {
        int cppOptions;
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(options, "options");
        cppOptions = JavaScriptObjectKt.toCppOptions(options);
        defineJSObjectProperty(name, null, cppOptions);
    }

    protected final void finalize() throws Throwable {
        this.mHybridData.resetNative();
    }
}
