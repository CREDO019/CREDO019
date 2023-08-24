package expo.modules.kotlin.jni;

import com.facebook.jni.HybridData;
import kotlin.Metadata;

/* compiled from: JavaScriptValue.kt */
@Metadata(m184d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0003\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0005\u001a\u00020\u0006H\u0004J\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00000\bH\u0086 ¢\u0006\u0002\u0010\tJ\t\u0010\n\u001a\u00020\u000bH\u0086 J\t\u0010\f\u001a\u00020\rH\u0086 J\t\u0010\u000e\u001a\u00020\u000fH\u0086 J\t\u0010\u0010\u001a\u00020\u0011H\u0086 J\t\u0010\u0012\u001a\u00020\u0013H\u0086 J\t\u0010\u0014\u001a\u00020\u000bH\u0086 J\t\u0010\u0015\u001a\u00020\u000bH\u0086 J\t\u0010\u0016\u001a\u00020\u000bH\u0086 J\t\u0010\u0017\u001a\u00020\u000bH\u0086 J\t\u0010\u0018\u001a\u00020\u000bH\u0086 J\t\u0010\u0019\u001a\u00020\u000bH\u0086 J\t\u0010\u001a\u001a\u00020\u000bH\u0086 J\t\u0010\u001b\u001a\u00020\u000bH\u0086 J\t\u0010\u001c\u001a\u00020\u000bH\u0086 J\t\u0010\u001d\u001a\u00020\u000bH\u0086 J\t\u0010\u001e\u001a\u00020\u0011H\u0086 R\u0010\u0010\u0002\u001a\u00020\u00038\u0002X\u0083\u0004¢\u0006\u0002\n\u0000¨\u0006\u001f"}, m183d2 = {"Lexpo/modules/kotlin/jni/JavaScriptValue;", "", "mHybridData", "Lcom/facebook/jni/HybridData;", "(Lcom/facebook/jni/HybridData;)V", "finalize", "", "getArray", "", "()[Lexpo/modules/kotlin/jni/JavaScriptValue;", "getBool", "", "getDouble", "", "getObject", "Lexpo/modules/kotlin/jni/JavaScriptObject;", "getString", "", "getTypedArray", "Lexpo/modules/kotlin/jni/JavaScriptTypedArray;", "isArray", "isBool", "isFunction", "isNull", "isNumber", "isObject", "isString", "isSymbol", "isTypedArray", "isUndefined", "kind", "expo-modules-core_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes4.dex */
public final class JavaScriptValue {
    private final HybridData mHybridData;

    public final native JavaScriptValue[] getArray();

    public final native boolean getBool();

    public final native double getDouble();

    public final native JavaScriptObject getObject();

    public final native String getString();

    public final native JavaScriptTypedArray getTypedArray();

    public final native boolean isArray();

    public final native boolean isBool();

    public final native boolean isFunction();

    public final native boolean isNull();

    public final native boolean isNumber();

    public final native boolean isObject();

    public final native boolean isString();

    public final native boolean isSymbol();

    public final native boolean isTypedArray();

    public final native boolean isUndefined();

    public final native String kind();

    private JavaScriptValue(HybridData hybridData) {
        this.mHybridData = hybridData;
    }

    protected final void finalize() throws Throwable {
        this.mHybridData.resetNative();
    }
}
