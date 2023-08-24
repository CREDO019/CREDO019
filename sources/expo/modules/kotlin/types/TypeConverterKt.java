package expo.modules.kotlin.types;

import com.facebook.react.bridge.Dynamic;
import expo.modules.kotlin.exception.UnsupportedClass;
import expo.modules.kotlin.jni.ExpectedType;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

/* compiled from: TypeConverter.kt */
@Metadata(m184d1 = {"\u0000$\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001aD\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\n\b\u0000\u0010\u0002\u0018\u0001*\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0014\b\u0006\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u0002H\u00020\tH\u0086\bø\u0001\u0000\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\u000b"}, m183d2 = {"createTrivialTypeConverter", "Lexpo/modules/kotlin/types/TypeConverter;", "T", "", "isOptional", "", "cppRequireType", "Lexpo/modules/kotlin/jni/ExpectedType;", "dynamicFallback", "Lkotlin/Function1;", "Lcom/facebook/react/bridge/Dynamic;", "expo-modules-core_release"}, m182k = 2, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes4.dex */
public final class TypeConverterKt {
    public static /* synthetic */ TypeConverter createTrivialTypeConverter$default(boolean z, ExpectedType cppRequireType, Function1 dynamicFallback, int r3, Object obj) {
        if ((r3 & 4) != 0) {
            Intrinsics.needClassReification();
            dynamicFallback = new Function1() { // from class: expo.modules.kotlin.types.TypeConverterKt$createTrivialTypeConverter$1
                @Override // kotlin.jvm.functions.Function1
                public final Void invoke(Dynamic it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                    Intrinsics.reifiedOperationMarker(4, "T");
                    throw new UnsupportedClass(Reflection.getOrCreateKotlinClass(Object.class));
                }
            };
        }
        Intrinsics.checkNotNullParameter(cppRequireType, "cppRequireType");
        Intrinsics.checkNotNullParameter(dynamicFallback, "dynamicFallback");
        Intrinsics.needClassReification();
        return new TypeConverterKt$createTrivialTypeConverter$2(z, dynamicFallback, cppRequireType);
    }

    public static final /* synthetic */ <T> TypeConverter<T> createTrivialTypeConverter(boolean z, ExpectedType cppRequireType, Function1<? super Dynamic, ? extends T> dynamicFallback) {
        Intrinsics.checkNotNullParameter(cppRequireType, "cppRequireType");
        Intrinsics.checkNotNullParameter(dynamicFallback, "dynamicFallback");
        Intrinsics.needClassReification();
        return new TypeConverterKt$createTrivialTypeConverter$2(z, dynamicFallback, cppRequireType);
    }
}
