package expo.modules.kotlin.types;

import com.facebook.react.bridge.Dynamic;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableType;
import expo.modules.core.errors.CodedException;
import expo.modules.kotlin.exception.CollectionElementCastException;
import expo.modules.kotlin.exception.UnexpectedException;
import expo.modules.kotlin.jni.ExpectedType;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KType;
import kotlin.reflect.KTypeProjection;

/* compiled from: ListTypeConverter.kt */
@Metadata(m184d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\u0018\u00002\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00020\u0001B\u0015\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\u0014\u0010\n\u001a\u0006\u0012\u0002\b\u00030\u00022\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u0014\u0010\r\u001a\u0006\u0012\u0002\b\u00030\u00022\u0006\u0010\u000b\u001a\u00020\u000eH\u0016J\u0014\u0010\u000f\u001a\u0006\u0012\u0002\b\u00030\u00022\u0006\u0010\u0010\u001a\u00020\u0011H\u0002J\b\u0010\u0012\u001a\u00020\u0013H\u0016J\b\u0010\u0014\u001a\u00020\u0015H\u0016R\u0012\u0010\b\u001a\u0006\u0012\u0002\b\u00030\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0016"}, m183d2 = {"Lexpo/modules/kotlin/types/ListTypeConverter;", "Lexpo/modules/kotlin/types/DynamicAwareTypeConverters;", "", "converterProvider", "Lexpo/modules/kotlin/types/TypeConverterProvider;", "listType", "Lkotlin/reflect/KType;", "(Lexpo/modules/kotlin/types/TypeConverterProvider;Lkotlin/reflect/KType;)V", "elementConverter", "Lexpo/modules/kotlin/types/TypeConverter;", "convertFromAny", "value", "", "convertFromDynamic", "Lcom/facebook/react/bridge/Dynamic;", "convertFromReadableArray", "jsArray", "Lcom/facebook/react/bridge/ReadableArray;", "getCppRequiredTypes", "Lexpo/modules/kotlin/jni/ExpectedType;", "isTrivial", "", "expo-modules-core_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes4.dex */
public final class ListTypeConverter extends DynamicAwareTypeConverters<List<?>> {
    private final TypeConverter<?> elementConverter;
    private final KType listType;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ListTypeConverter(TypeConverterProvider converterProvider, KType listType) {
        super(listType.isMarkedNullable());
        Intrinsics.checkNotNullParameter(converterProvider, "converterProvider");
        Intrinsics.checkNotNullParameter(listType, "listType");
        this.listType = listType;
        KType type = ((KTypeProjection) CollectionsKt.first((List<? extends Object>) listType.getArguments())).getType();
        if (type != null) {
            this.elementConverter = converterProvider.obtainTypeConverter(type);
            return;
        }
        throw new IllegalArgumentException("The list type should contain the type of elements.".toString());
    }

    @Override // expo.modules.kotlin.types.DynamicAwareTypeConverters
    public List<?> convertFromDynamic(Dynamic value) {
        Intrinsics.checkNotNullParameter(value, "value");
        ReadableArray jsArray = value.asArray();
        Intrinsics.checkNotNullExpressionValue(jsArray, "jsArray");
        return convertFromReadableArray(jsArray);
    }

    @Override // expo.modules.kotlin.types.DynamicAwareTypeConverters
    public List<?> convertFromAny(Object value) {
        Intrinsics.checkNotNullParameter(value, "value");
        if (this.elementConverter.isTrivial()) {
            return (List) value;
        }
        List list = (List) value;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        for (Object obj : list) {
            try {
                arrayList.add(this.elementConverter.convert(obj));
            } catch (CodedException e) {
                String code = e.getCode();
                Intrinsics.checkNotNullExpressionValue(code, "e.code");
                expo.modules.kotlin.exception.CodedException codedException = new expo.modules.kotlin.exception.CodedException(code, e.getMessage(), e.getCause());
                KType kType = this.listType;
                KType type = ((KTypeProjection) CollectionsKt.first((List<? extends Object>) kType.getArguments())).getType();
                Intrinsics.checkNotNull(type);
                Intrinsics.checkNotNull(obj);
                throw new CollectionElementCastException(kType, type, Reflection.getOrCreateKotlinClass(obj.getClass()), codedException);
            } catch (expo.modules.kotlin.exception.CodedException e2) {
                KType kType2 = this.listType;
                KType type2 = ((KTypeProjection) CollectionsKt.first((List<? extends Object>) kType2.getArguments())).getType();
                Intrinsics.checkNotNull(type2);
                Intrinsics.checkNotNull(obj);
                throw new CollectionElementCastException(kType2, type2, Reflection.getOrCreateKotlinClass(obj.getClass()), e2);
            } catch (Throwable th) {
                KType kType3 = this.listType;
                KType type3 = ((KTypeProjection) CollectionsKt.first((List<? extends Object>) kType3.getArguments())).getType();
                Intrinsics.checkNotNull(type3);
                Intrinsics.checkNotNull(obj);
                throw new CollectionElementCastException(kType3, type3, Reflection.getOrCreateKotlinClass(obj.getClass()), new UnexpectedException(th));
            }
        }
        return arrayList;
    }

    private final List<?> convertFromReadableArray(ReadableArray readableArray) {
        int size = readableArray.size();
        ArrayList arrayList = new ArrayList(size);
        int r3 = 0;
        while (r3 < size) {
            int r4 = r3 + 1;
            Dynamic dynamic = readableArray.getDynamic(r3);
            Intrinsics.checkNotNullExpressionValue(dynamic, "jsArray.getDynamic(index)");
            try {
                try {
                    try {
                        Object convert = this.elementConverter.convert(dynamic);
                        dynamic.recycle();
                        arrayList.add(convert);
                        r3 = r4;
                    } catch (expo.modules.kotlin.exception.CodedException e) {
                        KType kType = this.listType;
                        KType type = ((KTypeProjection) CollectionsKt.first((List<? extends Object>) kType.getArguments())).getType();
                        Intrinsics.checkNotNull(type);
                        ReadableType type2 = dynamic.getType();
                        Intrinsics.checkNotNullExpressionValue(type2, "type");
                        throw new CollectionElementCastException(kType, type, type2, e);
                    } catch (Throwable th) {
                        KType kType2 = this.listType;
                        KType type3 = ((KTypeProjection) CollectionsKt.first((List<? extends Object>) kType2.getArguments())).getType();
                        Intrinsics.checkNotNull(type3);
                        ReadableType type4 = dynamic.getType();
                        Intrinsics.checkNotNullExpressionValue(type4, "type");
                        throw new CollectionElementCastException(kType2, type3, type4, new UnexpectedException(th));
                    }
                } catch (CodedException e2) {
                    String code = e2.getCode();
                    Intrinsics.checkNotNullExpressionValue(code, "e.code");
                    expo.modules.kotlin.exception.CodedException codedException = new expo.modules.kotlin.exception.CodedException(code, e2.getMessage(), e2.getCause());
                    KType kType3 = this.listType;
                    KType type5 = ((KTypeProjection) CollectionsKt.first((List<? extends Object>) kType3.getArguments())).getType();
                    Intrinsics.checkNotNull(type5);
                    ReadableType type6 = dynamic.getType();
                    Intrinsics.checkNotNullExpressionValue(type6, "type");
                    throw new CollectionElementCastException(kType3, type5, type6, codedException);
                }
            } catch (Throwable th2) {
                dynamic.recycle();
                throw th2;
            }
        }
        return arrayList;
    }

    @Override // expo.modules.kotlin.types.TypeConverter
    public ExpectedType getCppRequiredTypes() {
        return ExpectedType.Companion.forList(this.elementConverter.getCppRequiredTypes());
    }

    @Override // expo.modules.kotlin.types.TypeConverter
    public boolean isTrivial() {
        return this.elementConverter.isTrivial();
    }
}
