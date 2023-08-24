package expo.modules.kotlin.jni;

import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import expo.modules.kotlin.typedarray.TypedArray;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;

/* compiled from: CppType.kt */
@Metadata(m184d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0014\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u001d\b\u0002\u0012\n\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006R\u0015\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014j\u0002\b\u0015j\u0002\b\u0016j\u0002\b\u0017j\u0002\b\u0018¨\u0006\u0019"}, m183d2 = {"Lexpo/modules/kotlin/jni/CppType;", "", "clazz", "Lkotlin/reflect/KClass;", "value", "", "(Ljava/lang/String;ILkotlin/reflect/KClass;I)V", "getClazz", "()Lkotlin/reflect/KClass;", "getValue", "()I", "NONE", "DOUBLE", "INT", "FLOAT", "BOOLEAN", "STRING", "JS_OBJECT", "JS_VALUE", "READABLE_ARRAY", "READABLE_MAP", "TYPED_ARRAY", "PRIMITIVE_ARRAY", "LIST", "MAP", "expo-modules-core_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes4.dex */
public enum CppType {
    NONE(Reflection.getOrCreateKotlinClass(Void.class), 0),
    DOUBLE(Reflection.getOrCreateKotlinClass(Double.TYPE), 0, 2, null),
    INT(Reflection.getOrCreateKotlinClass(Integer.TYPE), 0, 2, null),
    FLOAT(Reflection.getOrCreateKotlinClass(Float.TYPE), 0, 2, null),
    BOOLEAN(Reflection.getOrCreateKotlinClass(Boolean.TYPE), 0, 2, null),
    STRING(Reflection.getOrCreateKotlinClass(String.class), 0, 2, null),
    JS_OBJECT(Reflection.getOrCreateKotlinClass(JavaScriptObject.class), 0, 2, null),
    JS_VALUE(Reflection.getOrCreateKotlinClass(JavaScriptValue.class), 0, 2, null),
    READABLE_ARRAY(Reflection.getOrCreateKotlinClass(ReadableArray.class), 0, 2, null),
    READABLE_MAP(Reflection.getOrCreateKotlinClass(ReadableMap.class), 0, 2, null),
    TYPED_ARRAY(Reflection.getOrCreateKotlinClass(TypedArray.class), 0, 2, null),
    PRIMITIVE_ARRAY(Reflection.getOrCreateKotlinClass(Object[].class), 0, 2, null),
    LIST(Reflection.getOrCreateKotlinClass(List.class), 0, 2, null),
    MAP(Reflection.getOrCreateKotlinClass(Map.class), 0, 2, null);
    
    private final KClass<?> clazz;
    private final int value;

    CppType(KClass kClass, int r4) {
        this.clazz = kClass;
        this.value = r4;
    }

    /* synthetic */ CppType(KClass kClass, int r4, int r5, DefaultConstructorMarker defaultConstructorMarker) {
        this(kClass, (r5 & 2) != 0 ? CppTypeKt.nextValue() : r4);
    }

    public final KClass<?> getClazz() {
        return this.clazz;
    }

    public final int getValue() {
        return this.value;
    }
}
