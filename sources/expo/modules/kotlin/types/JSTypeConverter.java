package expo.modules.kotlin.types;

import android.net.Uri;
import android.os.Bundle;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import expo.modules.kotlin.records.Record;
import java.io.File;
import java.net.URI;
import java.net.URL;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Tuples;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: JSTypeConverter.kt */
@Metadata(m184d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001:\u0002\u0007\bB\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u00012\b\u0010\u0004\u001a\u0004\u0018\u00010\u00012\b\b\u0002\u0010\u0005\u001a\u00020\u0006¨\u0006\t"}, m183d2 = {"Lexpo/modules/kotlin/types/JSTypeConverter;", "", "()V", "convertToJSValue", "value", "containerProvider", "Lexpo/modules/kotlin/types/JSTypeConverter$ContainerProvider;", "ContainerProvider", "DefaultContainerProvider", "expo-modules-core_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes4.dex */
public final class JSTypeConverter {
    public static final JSTypeConverter INSTANCE = new JSTypeConverter();

    /* compiled from: JSTypeConverter.kt */
    @Metadata(m184d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006"}, m183d2 = {"Lexpo/modules/kotlin/types/JSTypeConverter$ContainerProvider;", "", "createArray", "Lcom/facebook/react/bridge/WritableArray;", "createMap", "Lcom/facebook/react/bridge/WritableMap;", "expo-modules-core_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
    /* loaded from: classes4.dex */
    public interface ContainerProvider {
        WritableArray createArray();

        WritableMap createMap();
    }

    private JSTypeConverter() {
    }

    /* compiled from: JSTypeConverter.kt */
    @Metadata(m184d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016J\b\u0010\u0005\u001a\u00020\u0006H\u0016¨\u0006\u0007"}, m183d2 = {"Lexpo/modules/kotlin/types/JSTypeConverter$DefaultContainerProvider;", "Lexpo/modules/kotlin/types/JSTypeConverter$ContainerProvider;", "()V", "createArray", "Lcom/facebook/react/bridge/WritableArray;", "createMap", "Lcom/facebook/react/bridge/WritableMap;", "expo-modules-core_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
    /* loaded from: classes4.dex */
    public static final class DefaultContainerProvider implements ContainerProvider {
        public static final DefaultContainerProvider INSTANCE = new DefaultContainerProvider();

        private DefaultContainerProvider() {
        }

        @Override // expo.modules.kotlin.types.JSTypeConverter.ContainerProvider
        public WritableMap createMap() {
            WritableMap createMap = Arguments.createMap();
            Intrinsics.checkNotNullExpressionValue(createMap, "createMap()");
            return createMap;
        }

        @Override // expo.modules.kotlin.types.JSTypeConverter.ContainerProvider
        public WritableArray createArray() {
            WritableArray createArray = Arguments.createArray();
            Intrinsics.checkNotNullExpressionValue(createArray, "createArray()");
            return createArray;
        }
    }

    public static /* synthetic */ Object convertToJSValue$default(JSTypeConverter jSTypeConverter, Object obj, ContainerProvider containerProvider, int r3, Object obj2) {
        if ((r3 & 2) != 0) {
            containerProvider = DefaultContainerProvider.INSTANCE;
        }
        return jSTypeConverter.convertToJSValue(obj, containerProvider);
    }

    public final Object convertToJSValue(Object obj, ContainerProvider containerProvider) {
        Intrinsics.checkNotNullParameter(containerProvider, "containerProvider");
        if (obj == null ? true : obj instanceof Unit) {
            return null;
        }
        return obj instanceof Bundle ? JSTypeConverterHelper.toJSValue((Bundle) obj, containerProvider) : obj instanceof Iterable ? JSTypeConverterHelper.toJSValue((Iterable) obj, containerProvider) : obj instanceof Object[] ? JSTypeConverterHelper.toJSValue((Object[]) obj, containerProvider) : obj instanceof int[] ? JSTypeConverterHelper.toJSValue((int[]) obj, containerProvider) : obj instanceof float[] ? JSTypeConverterHelper.toJSValue((float[]) obj, containerProvider) : obj instanceof double[] ? JSTypeConverterHelper.toJSValue((double[]) obj, containerProvider) : obj instanceof boolean[] ? JSTypeConverterHelper.toJSValue((boolean[]) obj, containerProvider) : obj instanceof Map ? JSTypeConverterHelper.toJSValue((Map) obj, containerProvider) : obj instanceof Enum ? JSTypeConverterHelper.toJSValue((Enum) obj) : obj instanceof Record ? JSTypeConverterHelper.toJSValue((Record) obj, containerProvider) : obj instanceof URI ? JSTypeConverterHelper.toJSValue((URI) obj) : obj instanceof URL ? JSTypeConverterHelper.toJSValue((URL) obj) : obj instanceof Uri ? JSTypeConverterHelper.toJSValue((Uri) obj) : obj instanceof File ? JSTypeConverterHelper.toJSValue((File) obj) : obj instanceof Tuples ? JSTypeConverterHelper.toJSValue((Tuples) obj, containerProvider) : obj;
    }
}
