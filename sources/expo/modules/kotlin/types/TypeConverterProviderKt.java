package expo.modules.kotlin.types;

import com.facebook.react.bridge.Dynamic;
import com.google.android.exoplayer2.source.rtsp.SessionDescription;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KType;

/* compiled from: TypeConverterProvider.kt */
@Metadata(m184d1 = {"\u0000\u001e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u001e\u0010\u0000\u001a\u0002H\u0001\"\u0006\b\u0000\u0010\u0001\u0018\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0086\b¢\u0006\u0002\u0010\u0004\u001a\u0018\u0010\u0000\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0007\u001a \u0010\u0000\u001a\u0002H\u0001\"\u0006\b\u0000\u0010\u0001\u0018\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0005H\u0086\b¢\u0006\u0002\u0010\b\u001a\u001b\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\u00010\n\"\n\b\u0000\u0010\u0001\u0018\u0001*\u00020\u0005H\u0086\b¨\u0006\u000b"}, m183d2 = {"convert", "T", "value", "Lcom/facebook/react/bridge/Dynamic;", "(Lcom/facebook/react/bridge/Dynamic;)Ljava/lang/Object;", "", SessionDescription.ATTR_TYPE, "Lkotlin/reflect/KType;", "(Ljava/lang/Object;)Ljava/lang/Object;", "obtainTypeConverter", "Lexpo/modules/kotlin/types/TypeConverter;", "expo-modules-core_release"}, m182k = 2, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes4.dex */
public final class TypeConverterProviderKt {
    public static final /* synthetic */ <T> TypeConverter<T> obtainTypeConverter() {
        TypeConverterProviderImpl typeConverterProviderImpl = TypeConverterProviderImpl.INSTANCE;
        Intrinsics.reifiedOperationMarker(6, "T");
        return typeConverterProviderImpl.obtainTypeConverter(null);
    }

    public static final /* synthetic */ <T> T convert(Dynamic value) {
        Intrinsics.checkNotNullParameter(value, "value");
        TypeConverterProviderImpl typeConverterProviderImpl = TypeConverterProviderImpl.INSTANCE;
        Intrinsics.reifiedOperationMarker(6, "T");
        T t = (T) typeConverterProviderImpl.obtainTypeConverter(null).convert(value);
        Intrinsics.reifiedOperationMarker(1, "T");
        return t;
    }

    public static final /* synthetic */ <T> T convert(Object obj) {
        TypeConverterProviderImpl typeConverterProviderImpl = TypeConverterProviderImpl.INSTANCE;
        Intrinsics.reifiedOperationMarker(6, "T");
        T t = (T) typeConverterProviderImpl.obtainTypeConverter(null).convert(obj);
        Intrinsics.reifiedOperationMarker(1, "T");
        return t;
    }

    public static final Object convert(Dynamic value, KType type) {
        Intrinsics.checkNotNullParameter(value, "value");
        Intrinsics.checkNotNullParameter(type, "type");
        return TypeConverterProviderImpl.INSTANCE.obtainTypeConverter(type).convert(value);
    }
}
