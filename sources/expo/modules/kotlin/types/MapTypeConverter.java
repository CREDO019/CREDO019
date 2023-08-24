package expo.modules.kotlin.types;

import com.facebook.react.bridge.Dynamic;
import com.facebook.react.bridge.DynamicFromObject;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableType;
import expo.modules.core.errors.CodedException;
import expo.modules.kotlin.exception.CollectionElementCastException;
import expo.modules.kotlin.exception.UnexpectedException;
import expo.modules.kotlin.jni.ExpectedType;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KType;
import kotlin.reflect.KTypeProjection;

/* compiled from: MapTypeConverter.kt */
@Metadata(m184d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\u0018\u00002\u0010\u0012\f\u0012\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u00020\u0001B\u0015\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\u0018\u0010\n\u001a\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u00022\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u0018\u0010\r\u001a\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u00022\u0006\u0010\u000b\u001a\u00020\u000eH\u0016J\u0018\u0010\u000f\u001a\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u00022\u0006\u0010\u0010\u001a\u00020\u0011H\u0002J\b\u0010\u0012\u001a\u00020\u0013H\u0016J\b\u0010\u0014\u001a\u00020\u0015H\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\b\u001a\u0006\u0012\u0002\b\u00030\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0016"}, m183d2 = {"Lexpo/modules/kotlin/types/MapTypeConverter;", "Lexpo/modules/kotlin/types/DynamicAwareTypeConverters;", "", "converterProvider", "Lexpo/modules/kotlin/types/TypeConverterProvider;", "mapType", "Lkotlin/reflect/KType;", "(Lexpo/modules/kotlin/types/TypeConverterProvider;Lkotlin/reflect/KType;)V", "valueConverter", "Lexpo/modules/kotlin/types/TypeConverter;", "convertFromAny", "value", "", "convertFromDynamic", "Lcom/facebook/react/bridge/Dynamic;", "convertFromReadableMap", "jsMap", "Lcom/facebook/react/bridge/ReadableMap;", "getCppRequiredTypes", "Lexpo/modules/kotlin/jni/ExpectedType;", "isTrivial", "", "expo-modules-core_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes4.dex */
public final class MapTypeConverter extends DynamicAwareTypeConverters<Map<?, ?>> {
    private final KType mapType;
    private final TypeConverter<?> valueConverter;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MapTypeConverter(TypeConverterProvider converterProvider, KType mapType) {
        super(mapType.isMarkedNullable());
        Intrinsics.checkNotNullParameter(converterProvider, "converterProvider");
        Intrinsics.checkNotNullParameter(mapType, "mapType");
        this.mapType = mapType;
        if (!Intrinsics.areEqual(((KTypeProjection) CollectionsKt.first((List<? extends Object>) mapType.getArguments())).getType(), Reflection.typeOf(String.class))) {
            Object first = CollectionsKt.first((List<? extends Object>) mapType.getArguments());
            throw new IllegalArgumentException(("The map key type should be String, but received " + first + ".").toString());
        }
        KTypeProjection kTypeProjection = (KTypeProjection) CollectionsKt.getOrNull(mapType.getArguments(), 1);
        KType type = kTypeProjection == null ? null : kTypeProjection.getType();
        if (type != null) {
            this.valueConverter = converterProvider.obtainTypeConverter(type);
            return;
        }
        throw new IllegalArgumentException("The map type should contain the key type.".toString());
    }

    @Override // expo.modules.kotlin.types.DynamicAwareTypeConverters
    public Map<?, ?> convertFromDynamic(Dynamic value) {
        Intrinsics.checkNotNullParameter(value, "value");
        ReadableMap jsMap = value.asMap();
        Intrinsics.checkNotNullExpressionValue(jsMap, "jsMap");
        return convertFromReadableMap(jsMap);
    }

    @Override // expo.modules.kotlin.types.DynamicAwareTypeConverters
    public Map<?, ?> convertFromAny(Object value) {
        Intrinsics.checkNotNullParameter(value, "value");
        if (this.valueConverter.isTrivial()) {
            return (Map) value;
        }
        Map map = (Map) value;
        LinkedHashMap linkedHashMap = new LinkedHashMap(MapsKt.mapCapacity(map.size()));
        for (Map.Entry entry : map.entrySet()) {
            Object key = entry.getKey();
            Object value2 = entry.getValue();
            try {
                linkedHashMap.put(key, this.valueConverter.convert(value2));
            } catch (CodedException e) {
                String code = e.getCode();
                Intrinsics.checkNotNullExpressionValue(code, "e.code");
                expo.modules.kotlin.exception.CodedException codedException = new expo.modules.kotlin.exception.CodedException(code, e.getMessage(), e.getCause());
                KType kType = this.mapType;
                KType type = kType.getArguments().get(1).getType();
                Intrinsics.checkNotNull(type);
                Intrinsics.checkNotNull(value2);
                throw new CollectionElementCastException(kType, type, Reflection.getOrCreateKotlinClass(value2.getClass()), codedException);
            } catch (expo.modules.kotlin.exception.CodedException e2) {
                KType kType2 = this.mapType;
                KType type2 = kType2.getArguments().get(1).getType();
                Intrinsics.checkNotNull(type2);
                Intrinsics.checkNotNull(value2);
                throw new CollectionElementCastException(kType2, type2, Reflection.getOrCreateKotlinClass(value2.getClass()), e2);
            } catch (Throwable th) {
                KType kType3 = this.mapType;
                KType type3 = kType3.getArguments().get(1).getType();
                Intrinsics.checkNotNull(type3);
                Intrinsics.checkNotNull(value2);
                throw new CollectionElementCastException(kType3, type3, Reflection.getOrCreateKotlinClass(value2.getClass()), new UnexpectedException(th));
            }
        }
        return linkedHashMap;
    }

    private final Map<?, ?> convertFromReadableMap(ReadableMap readableMap) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        Iterator<Map.Entry<String, Object>> entryIterator = readableMap.getEntryIterator();
        Intrinsics.checkNotNullExpressionValue(entryIterator, "jsMap.entryIterator");
        while (entryIterator.hasNext()) {
            Map.Entry<String, Object> next = entryIterator.next();
            Intrinsics.checkNotNullExpressionValue(next, "(key, value)");
            String key = next.getKey();
            DynamicFromObject dynamicFromObject = new DynamicFromObject(next.getValue());
            try {
                try {
                    try {
                        Intrinsics.checkNotNullExpressionValue(key, "key");
                        linkedHashMap.put(key, this.valueConverter.convert(dynamicFromObject));
                        Unit unit = Unit.INSTANCE;
                        Unit unit2 = Unit.INSTANCE;
                    } finally {
                        dynamicFromObject.recycle();
                    }
                } catch (CodedException e) {
                    String code = e.getCode();
                    Intrinsics.checkNotNullExpressionValue(code, "e.code");
                    expo.modules.kotlin.exception.CodedException codedException = new expo.modules.kotlin.exception.CodedException(code, e.getMessage(), e.getCause());
                    KType kType = this.mapType;
                    KType type = kType.getArguments().get(1).getType();
                    Intrinsics.checkNotNull(type);
                    ReadableType type2 = dynamicFromObject.getType();
                    Intrinsics.checkNotNullExpressionValue(type2, "type");
                    throw new CollectionElementCastException(kType, type, type2, codedException);
                }
            } catch (expo.modules.kotlin.exception.CodedException e2) {
                KType kType2 = this.mapType;
                KType type3 = kType2.getArguments().get(1).getType();
                Intrinsics.checkNotNull(type3);
                ReadableType type4 = dynamicFromObject.getType();
                Intrinsics.checkNotNullExpressionValue(type4, "type");
                throw new CollectionElementCastException(kType2, type3, type4, e2);
            }
        }
        return linkedHashMap;
    }

    @Override // expo.modules.kotlin.types.TypeConverter
    public ExpectedType getCppRequiredTypes() {
        return ExpectedType.Companion.forMap(this.valueConverter.getCppRequiredTypes());
    }

    @Override // expo.modules.kotlin.types.TypeConverter
    public boolean isTrivial() {
        return this.valueConverter.isTrivial();
    }
}
