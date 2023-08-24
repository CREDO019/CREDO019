package expo.modules.kotlin.types;

import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import com.facebook.react.bridge.Dynamic;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.google.android.exoplayer2.source.rtsp.SessionDescription;
import expo.modules.kotlin.exception.MissingTypeConverter;
import expo.modules.kotlin.exception.UnsupportedClass;
import expo.modules.kotlin.jni.CppType;
import expo.modules.kotlin.jni.ExpectedType;
import expo.modules.kotlin.jni.JavaScriptObject;
import expo.modules.kotlin.jni.JavaScriptValue;
import expo.modules.kotlin.records.Record;
import expo.modules.kotlin.records.RecordTypeConverter;
import expo.modules.kotlin.typedarray.BigUint64Array;
import expo.modules.kotlin.typedarray.ConcreteTypedArrays;
import expo.modules.kotlin.typedarray.Float32Array;
import expo.modules.kotlin.typedarray.Float64Array;
import expo.modules.kotlin.typedarray.Int16Array;
import expo.modules.kotlin.typedarray.Int32Array;
import expo.modules.kotlin.typedarray.Int8Array;
import expo.modules.kotlin.typedarray.TypedArray;
import expo.modules.kotlin.typedarray.Uint16Array;
import expo.modules.kotlin.typedarray.Uint32Array;
import expo.modules.kotlin.typedarray.Uint8Array;
import expo.modules.kotlin.typedarray.Uint8ClampedArray;
import expo.modules.kotlin.types.net.JavaURITypeConverter;
import expo.modules.kotlin.types.net.URLTypConverter;
import expo.modules.kotlin.types.net.UriTypeConverter;
import expo.modules.kotlin.types.p020io.FileTypeConverter;
import expo.modules.kotlin.types.p020io.PathTypeConverter;
import java.io.File;
import java.net.URI;
import java.net.URL;
import java.nio.file.Path;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Tuples;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.JvmClassMapping;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import kotlin.reflect.KClassifier;
import kotlin.reflect.KType;
import kotlin.reflect.full.KClasses;
import kotlin.reflect.full.KClassifiers;

/* compiled from: TypeConverterProvider.kt */
@Metadata(m184d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J \u0010\n\u001a\u0012\u0012\u0004\u0012\u00020\u0005\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00060\u00042\u0006\u0010\u000b\u001a\u00020\fH\u0002J\"\u0010\r\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u00062\u0006\u0010\u000e\u001a\u00020\u00052\n\u0010\u000f\u001a\u0006\u0012\u0002\b\u00030\tH\u0002J\u0014\u0010\u0010\u001a\u0006\u0012\u0002\b\u00030\u00062\u0006\u0010\u000e\u001a\u00020\u0005H\u0016R\u001e\u0010\u0003\u001a\u0012\u0012\u0004\u0012\u00020\u0005\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00060\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\"\u0010\u0007\u001a\u0016\u0012\b\u0012\u0006\u0012\u0002\b\u00030\t\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00060\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, m183d2 = {"Lexpo/modules/kotlin/types/TypeConverterProviderImpl;", "Lexpo/modules/kotlin/types/TypeConverterProvider;", "()V", "cachedConverters", "", "Lkotlin/reflect/KType;", "Lexpo/modules/kotlin/types/TypeConverter;", "cachedRecordConverters", "", "Lkotlin/reflect/KClass;", "createCashedConverters", "isOptional", "", "handelEither", SessionDescription.ATTR_TYPE, "kClass", "obtainTypeConverter", "expo-modules-core_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes4.dex */
public final class TypeConverterProviderImpl implements TypeConverterProvider {
    public static final TypeConverterProviderImpl INSTANCE;
    private static final Map<KType, TypeConverter<?>> cachedConverters;
    private static final Map<KClass<?>, TypeConverter<?>> cachedRecordConverters;

    private TypeConverterProviderImpl() {
    }

    static {
        TypeConverterProviderImpl typeConverterProviderImpl = new TypeConverterProviderImpl();
        INSTANCE = typeConverterProviderImpl;
        cachedConverters = MapsKt.plus(typeConverterProviderImpl.createCashedConverters(false), typeConverterProviderImpl.createCashedConverters(true));
        cachedRecordConverters = new LinkedHashMap();
    }

    @Override // expo.modules.kotlin.types.TypeConverterProvider
    public TypeConverter<?> obtainTypeConverter(KType type) {
        Intrinsics.checkNotNullParameter(type, "type");
        TypeConverter<?> typeConverter = cachedConverters.get(type);
        if (typeConverter == null) {
            KClassifier classifier = type.getClassifier();
            KClass<?> kClass = classifier instanceof KClass ? (KClass) classifier : null;
            if (kClass == null) {
                throw new MissingTypeConverter(type);
            }
            if (JvmClassMapping.getJavaClass((KClass) kClass).isArray()) {
                return new ArrayTypeConverter(this, type);
            }
            if (KClasses.isSubclassOf(kClass, Reflection.getOrCreateKotlinClass(List.class))) {
                return new ListTypeConverter(this, type);
            }
            if (KClasses.isSubclassOf(kClass, Reflection.getOrCreateKotlinClass(Map.class))) {
                return new MapTypeConverter(this, type);
            }
            if (KClasses.isSubclassOf(kClass, Reflection.getOrCreateKotlinClass(Tuples.class))) {
                return new PairTypeConverter(this, type);
            }
            if (KClasses.isSubclassOf(kClass, Reflection.getOrCreateKotlinClass(Object[].class))) {
                return new ArrayTypeConverter(this, type);
            }
            if (JvmClassMapping.getJavaClass((KClass) kClass).isEnum()) {
                return new EnumTypeConverter(kClass, type.isMarkedNullable());
            }
            Map<KClass<?>, TypeConverter<?>> map = cachedRecordConverters;
            TypeConverter<?> typeConverter2 = map.get(kClass);
            if (typeConverter2 != null) {
                return typeConverter2;
            }
            if (KClasses.isSubclassOf(kClass, Reflection.getOrCreateKotlinClass(Record.class))) {
                RecordTypeConverter recordTypeConverter = new RecordTypeConverter(this, type);
                map.put(kClass, recordTypeConverter);
                return recordTypeConverter;
            }
            TypeConverter<?> handelEither = handelEither(type, kClass);
            if (handelEither != null) {
                return handelEither;
            }
            throw new MissingTypeConverter(type);
        }
        return typeConverter;
    }

    private final TypeConverter<?> handelEither(KType kType, KClass<?> kClass) {
        if (KClasses.isSubclassOf(kClass, Reflection.getOrCreateKotlinClass(Either.class))) {
            if (KClasses.isSubclassOf(kClass, Reflection.getOrCreateKotlinClass(EitherOfFour.class))) {
                return new EitherOfFourTypeConverter(this, kType);
            }
            if (KClasses.isSubclassOf(kClass, Reflection.getOrCreateKotlinClass(EitherOfThree.class))) {
                return new EitherOfThreeTypeConverter(this, kType);
            }
            return new EitherTypeConverter(this, kType);
        }
        return null;
    }

    private final Map<KType, TypeConverter<?>> createCashedConverters(final boolean z) {
        final ExpectedType expectedType = new ExpectedType(CppType.INT);
        DynamicAwareTypeConverters<Integer> dynamicAwareTypeConverters = new DynamicAwareTypeConverters<Integer>(z, expectedType) { // from class: expo.modules.kotlin.types.TypeConverterProviderImpl$createCashedConverters$$inlined$createTrivialTypeConverter$1
            final /* synthetic */ ExpectedType $cppRequireType;
            final /* synthetic */ boolean $isOptional;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(z);
                this.$isOptional = z;
                this.$cppRequireType = expectedType;
            }

            @Override // expo.modules.kotlin.types.TypeConverter
            public ExpectedType getCppRequiredTypes() {
                return this.$cppRequireType;
            }

            @Override // expo.modules.kotlin.types.DynamicAwareTypeConverters
            public Integer convertFromAny(Object value) {
                Intrinsics.checkNotNullParameter(value, "value");
                return (Integer) value;
            }

            @Override // expo.modules.kotlin.types.DynamicAwareTypeConverters
            public Integer convertFromDynamic(Dynamic value) {
                Intrinsics.checkNotNullParameter(value, "value");
                return Integer.valueOf((int) value.asDouble());
            }
        };
        final ExpectedType expectedType2 = new ExpectedType(CppType.DOUBLE);
        DynamicAwareTypeConverters<Double> dynamicAwareTypeConverters2 = new DynamicAwareTypeConverters<Double>(z, expectedType2) { // from class: expo.modules.kotlin.types.TypeConverterProviderImpl$createCashedConverters$$inlined$createTrivialTypeConverter$2
            final /* synthetic */ ExpectedType $cppRequireType;
            final /* synthetic */ boolean $isOptional;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(z);
                this.$isOptional = z;
                this.$cppRequireType = expectedType2;
            }

            @Override // expo.modules.kotlin.types.TypeConverter
            public ExpectedType getCppRequiredTypes() {
                return this.$cppRequireType;
            }

            @Override // expo.modules.kotlin.types.DynamicAwareTypeConverters
            public Double convertFromAny(Object value) {
                Intrinsics.checkNotNullParameter(value, "value");
                return (Double) value;
            }

            @Override // expo.modules.kotlin.types.DynamicAwareTypeConverters
            public Double convertFromDynamic(Dynamic value) {
                Intrinsics.checkNotNullParameter(value, "value");
                return Double.valueOf(value.asDouble());
            }
        };
        final ExpectedType expectedType3 = new ExpectedType(CppType.FLOAT);
        DynamicAwareTypeConverters<Float> dynamicAwareTypeConverters3 = new DynamicAwareTypeConverters<Float>(z, expectedType3) { // from class: expo.modules.kotlin.types.TypeConverterProviderImpl$createCashedConverters$$inlined$createTrivialTypeConverter$3
            final /* synthetic */ ExpectedType $cppRequireType;
            final /* synthetic */ boolean $isOptional;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(z);
                this.$isOptional = z;
                this.$cppRequireType = expectedType3;
            }

            @Override // expo.modules.kotlin.types.TypeConverter
            public ExpectedType getCppRequiredTypes() {
                return this.$cppRequireType;
            }

            @Override // expo.modules.kotlin.types.DynamicAwareTypeConverters
            public Float convertFromAny(Object value) {
                Intrinsics.checkNotNullParameter(value, "value");
                return (Float) value;
            }

            @Override // expo.modules.kotlin.types.DynamicAwareTypeConverters
            public Float convertFromDynamic(Dynamic value) {
                Intrinsics.checkNotNullParameter(value, "value");
                return Float.valueOf((float) value.asDouble());
            }
        };
        final ExpectedType expectedType4 = new ExpectedType(CppType.BOOLEAN);
        DynamicAwareTypeConverters<Boolean> dynamicAwareTypeConverters4 = new DynamicAwareTypeConverters<Boolean>(z, expectedType4) { // from class: expo.modules.kotlin.types.TypeConverterProviderImpl$createCashedConverters$$inlined$createTrivialTypeConverter$4
            final /* synthetic */ ExpectedType $cppRequireType;
            final /* synthetic */ boolean $isOptional;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(z);
                this.$isOptional = z;
                this.$cppRequireType = expectedType4;
            }

            @Override // expo.modules.kotlin.types.TypeConverter
            public ExpectedType getCppRequiredTypes() {
                return this.$cppRequireType;
            }

            @Override // expo.modules.kotlin.types.DynamicAwareTypeConverters
            public Boolean convertFromAny(Object value) {
                Intrinsics.checkNotNullParameter(value, "value");
                return (Boolean) value;
            }

            @Override // expo.modules.kotlin.types.DynamicAwareTypeConverters
            public Boolean convertFromDynamic(Dynamic value) {
                Intrinsics.checkNotNullParameter(value, "value");
                return Boolean.valueOf(value.asBoolean());
            }
        };
        KType createType$default = KClassifiers.createType$default(Reflection.getOrCreateKotlinClass(String.class), null, z, null, 5, null);
        final ExpectedType expectedType5 = new ExpectedType(CppType.STRING);
        KType createType$default2 = KClassifiers.createType$default(Reflection.getOrCreateKotlinClass(ReadableArray.class), null, z, null, 5, null);
        final ExpectedType expectedType6 = new ExpectedType(CppType.READABLE_ARRAY);
        KType createType$default3 = KClassifiers.createType$default(Reflection.getOrCreateKotlinClass(ReadableMap.class), null, z, null, 5, null);
        final ExpectedType expectedType7 = new ExpectedType(CppType.READABLE_MAP);
        KType createType$default4 = KClassifiers.createType$default(Reflection.getOrCreateKotlinClass(int[].class), null, z, null, 5, null);
        final ExpectedType forPrimitiveArray = ExpectedType.Companion.forPrimitiveArray(CppType.INT);
        KType createType$default5 = KClassifiers.createType$default(Reflection.getOrCreateKotlinClass(double[].class), null, z, null, 5, null);
        final ExpectedType forPrimitiveArray2 = ExpectedType.Companion.forPrimitiveArray(CppType.DOUBLE);
        KType createType$default6 = KClassifiers.createType$default(Reflection.getOrCreateKotlinClass(float[].class), null, z, null, 5, null);
        final ExpectedType forPrimitiveArray3 = ExpectedType.Companion.forPrimitiveArray(CppType.FLOAT);
        KType createType$default7 = KClassifiers.createType$default(Reflection.getOrCreateKotlinClass(boolean[].class), null, z, null, 5, null);
        final ExpectedType forPrimitiveArray4 = ExpectedType.Companion.forPrimitiveArray(CppType.BOOLEAN);
        KType createType$default8 = KClassifiers.createType$default(Reflection.getOrCreateKotlinClass(JavaScriptValue.class), null, z, null, 5, null);
        final ExpectedType expectedType8 = new ExpectedType(CppType.JS_VALUE);
        KType createType$default9 = KClassifiers.createType$default(Reflection.getOrCreateKotlinClass(JavaScriptObject.class), null, z, null, 5, null);
        final ExpectedType expectedType9 = new ExpectedType(CppType.JS_OBJECT);
        Map<KType, TypeConverter<?>> mapOf = MapsKt.mapOf(TuplesKt.m176to(KClassifiers.createType$default(Reflection.getOrCreateKotlinClass(Integer.TYPE), null, z, null, 5, null), dynamicAwareTypeConverters), TuplesKt.m176to(KClassifiers.createType$default(Reflection.getOrCreateKotlinClass(Integer.class), null, z, null, 5, null), dynamicAwareTypeConverters), TuplesKt.m176to(KClassifiers.createType$default(Reflection.getOrCreateKotlinClass(Double.TYPE), null, z, null, 5, null), dynamicAwareTypeConverters2), TuplesKt.m176to(KClassifiers.createType$default(Reflection.getOrCreateKotlinClass(Double.class), null, z, null, 5, null), dynamicAwareTypeConverters2), TuplesKt.m176to(KClassifiers.createType$default(Reflection.getOrCreateKotlinClass(Float.TYPE), null, z, null, 5, null), dynamicAwareTypeConverters3), TuplesKt.m176to(KClassifiers.createType$default(Reflection.getOrCreateKotlinClass(Float.class), null, z, null, 5, null), dynamicAwareTypeConverters3), TuplesKt.m176to(KClassifiers.createType$default(Reflection.getOrCreateKotlinClass(Boolean.TYPE), null, z, null, 5, null), dynamicAwareTypeConverters4), TuplesKt.m176to(KClassifiers.createType$default(Reflection.getOrCreateKotlinClass(Boolean.class), null, z, null, 5, null), dynamicAwareTypeConverters4), TuplesKt.m176to(createType$default, new DynamicAwareTypeConverters<String>(z, expectedType5) { // from class: expo.modules.kotlin.types.TypeConverterProviderImpl$createCashedConverters$$inlined$createTrivialTypeConverter$5
            final /* synthetic */ ExpectedType $cppRequireType;
            final /* synthetic */ boolean $isOptional;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(z);
                this.$isOptional = z;
                this.$cppRequireType = expectedType5;
            }

            @Override // expo.modules.kotlin.types.TypeConverter
            public ExpectedType getCppRequiredTypes() {
                return this.$cppRequireType;
            }

            @Override // expo.modules.kotlin.types.DynamicAwareTypeConverters
            public String convertFromAny(Object value) {
                Intrinsics.checkNotNullParameter(value, "value");
                return (String) value;
            }

            @Override // expo.modules.kotlin.types.DynamicAwareTypeConverters
            public String convertFromDynamic(Dynamic value) {
                Intrinsics.checkNotNullParameter(value, "value");
                return value.asString();
            }
        }), TuplesKt.m176to(createType$default2, new DynamicAwareTypeConverters<ReadableArray>(z, expectedType6) { // from class: expo.modules.kotlin.types.TypeConverterProviderImpl$createCashedConverters$$inlined$createTrivialTypeConverter$6
            final /* synthetic */ ExpectedType $cppRequireType;
            final /* synthetic */ boolean $isOptional;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(z);
                this.$isOptional = z;
                this.$cppRequireType = expectedType6;
            }

            @Override // expo.modules.kotlin.types.TypeConverter
            public ExpectedType getCppRequiredTypes() {
                return this.$cppRequireType;
            }

            @Override // expo.modules.kotlin.types.DynamicAwareTypeConverters
            public ReadableArray convertFromAny(Object value) {
                Intrinsics.checkNotNullParameter(value, "value");
                return (ReadableArray) value;
            }

            @Override // expo.modules.kotlin.types.DynamicAwareTypeConverters
            public ReadableArray convertFromDynamic(Dynamic value) {
                Intrinsics.checkNotNullParameter(value, "value");
                return value.asArray();
            }
        }), TuplesKt.m176to(createType$default3, new DynamicAwareTypeConverters<ReadableMap>(z, expectedType7) { // from class: expo.modules.kotlin.types.TypeConverterProviderImpl$createCashedConverters$$inlined$createTrivialTypeConverter$7
            final /* synthetic */ ExpectedType $cppRequireType;
            final /* synthetic */ boolean $isOptional;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(z);
                this.$isOptional = z;
                this.$cppRequireType = expectedType7;
            }

            @Override // expo.modules.kotlin.types.TypeConverter
            public ExpectedType getCppRequiredTypes() {
                return this.$cppRequireType;
            }

            @Override // expo.modules.kotlin.types.DynamicAwareTypeConverters
            public ReadableMap convertFromAny(Object value) {
                Intrinsics.checkNotNullParameter(value, "value");
                return (ReadableMap) value;
            }

            @Override // expo.modules.kotlin.types.DynamicAwareTypeConverters
            public ReadableMap convertFromDynamic(Dynamic value) {
                Intrinsics.checkNotNullParameter(value, "value");
                return value.asMap();
            }
        }), TuplesKt.m176to(createType$default4, new DynamicAwareTypeConverters<int[]>(z, forPrimitiveArray) { // from class: expo.modules.kotlin.types.TypeConverterProviderImpl$createCashedConverters$$inlined$createTrivialTypeConverter$8
            final /* synthetic */ ExpectedType $cppRequireType;
            final /* synthetic */ boolean $isOptional;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(z);
                this.$isOptional = z;
                this.$cppRequireType = forPrimitiveArray;
            }

            @Override // expo.modules.kotlin.types.TypeConverter
            public ExpectedType getCppRequiredTypes() {
                return this.$cppRequireType;
            }

            @Override // expo.modules.kotlin.types.DynamicAwareTypeConverters
            public int[] convertFromAny(Object value) {
                Intrinsics.checkNotNullParameter(value, "value");
                return (int[]) value;
            }

            @Override // expo.modules.kotlin.types.DynamicAwareTypeConverters
            public int[] convertFromDynamic(Dynamic value) {
                Intrinsics.checkNotNullParameter(value, "value");
                ReadableArray asArray = value.asArray();
                int size = asArray.size();
                int[] r1 = new int[size];
                for (int r2 = 0; r2 < size; r2++) {
                    r1[r2] = asArray.getInt(r2);
                }
                return r1;
            }
        }), TuplesKt.m176to(createType$default5, new DynamicAwareTypeConverters<double[]>(z, forPrimitiveArray2) { // from class: expo.modules.kotlin.types.TypeConverterProviderImpl$createCashedConverters$$inlined$createTrivialTypeConverter$9
            final /* synthetic */ ExpectedType $cppRequireType;
            final /* synthetic */ boolean $isOptional;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(z);
                this.$isOptional = z;
                this.$cppRequireType = forPrimitiveArray2;
            }

            @Override // expo.modules.kotlin.types.TypeConverter
            public ExpectedType getCppRequiredTypes() {
                return this.$cppRequireType;
            }

            @Override // expo.modules.kotlin.types.DynamicAwareTypeConverters
            public double[] convertFromAny(Object value) {
                Intrinsics.checkNotNullParameter(value, "value");
                return (double[]) value;
            }

            @Override // expo.modules.kotlin.types.DynamicAwareTypeConverters
            public double[] convertFromDynamic(Dynamic value) {
                Intrinsics.checkNotNullParameter(value, "value");
                ReadableArray asArray = value.asArray();
                int size = asArray.size();
                double[] dArr = new double[size];
                for (int r2 = 0; r2 < size; r2++) {
                    dArr[r2] = asArray.getDouble(r2);
                }
                return dArr;
            }
        }), TuplesKt.m176to(createType$default6, new DynamicAwareTypeConverters<float[]>(z, forPrimitiveArray3) { // from class: expo.modules.kotlin.types.TypeConverterProviderImpl$createCashedConverters$$inlined$createTrivialTypeConverter$10
            final /* synthetic */ ExpectedType $cppRequireType;
            final /* synthetic */ boolean $isOptional;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(z);
                this.$isOptional = z;
                this.$cppRequireType = forPrimitiveArray3;
            }

            @Override // expo.modules.kotlin.types.TypeConverter
            public ExpectedType getCppRequiredTypes() {
                return this.$cppRequireType;
            }

            @Override // expo.modules.kotlin.types.DynamicAwareTypeConverters
            public float[] convertFromAny(Object value) {
                Intrinsics.checkNotNullParameter(value, "value");
                return (float[]) value;
            }

            @Override // expo.modules.kotlin.types.DynamicAwareTypeConverters
            public float[] convertFromDynamic(Dynamic value) {
                Intrinsics.checkNotNullParameter(value, "value");
                ReadableArray asArray = value.asArray();
                int size = asArray.size();
                float[] fArr = new float[size];
                for (int r2 = 0; r2 < size; r2++) {
                    fArr[r2] = (float) asArray.getDouble(r2);
                }
                return fArr;
            }
        }), TuplesKt.m176to(createType$default7, new DynamicAwareTypeConverters<boolean[]>(z, forPrimitiveArray4) { // from class: expo.modules.kotlin.types.TypeConverterProviderImpl$createCashedConverters$$inlined$createTrivialTypeConverter$11
            final /* synthetic */ ExpectedType $cppRequireType;
            final /* synthetic */ boolean $isOptional;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(z);
                this.$isOptional = z;
                this.$cppRequireType = forPrimitiveArray4;
            }

            @Override // expo.modules.kotlin.types.TypeConverter
            public ExpectedType getCppRequiredTypes() {
                return this.$cppRequireType;
            }

            @Override // expo.modules.kotlin.types.DynamicAwareTypeConverters
            public boolean[] convertFromAny(Object value) {
                Intrinsics.checkNotNullParameter(value, "value");
                return (boolean[]) value;
            }

            @Override // expo.modules.kotlin.types.DynamicAwareTypeConverters
            public boolean[] convertFromDynamic(Dynamic value) {
                Intrinsics.checkNotNullParameter(value, "value");
                ReadableArray asArray = value.asArray();
                int size = asArray.size();
                boolean[] zArr = new boolean[size];
                for (int r2 = 0; r2 < size; r2++) {
                    zArr[r2] = asArray.getBoolean(r2);
                }
                return zArr;
            }
        }), TuplesKt.m176to(createType$default8, new DynamicAwareTypeConverters<Object>(z, expectedType8) { // from class: expo.modules.kotlin.types.TypeConverterProviderImpl$createCashedConverters$$inlined$createTrivialTypeConverter$default$1
            final /* synthetic */ ExpectedType $cppRequireType;
            final /* synthetic */ boolean $isOptional;

            @Override // expo.modules.kotlin.types.DynamicAwareTypeConverters
            public Object convertFromAny(Object value) {
                Intrinsics.checkNotNullParameter(value, "value");
                return value;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(z);
                this.$isOptional = z;
                this.$cppRequireType = expectedType8;
            }

            @Override // expo.modules.kotlin.types.TypeConverter
            public ExpectedType getCppRequiredTypes() {
                return this.$cppRequireType;
            }

            @Override // expo.modules.kotlin.types.DynamicAwareTypeConverters
            public Object convertFromDynamic(Dynamic value) {
                Intrinsics.checkNotNullParameter(value, "value");
                throw new UnsupportedClass(Reflection.getOrCreateKotlinClass(Object.class));
            }
        }), TuplesKt.m176to(createType$default9, new DynamicAwareTypeConverters<Object>(z, expectedType9) { // from class: expo.modules.kotlin.types.TypeConverterProviderImpl$createCashedConverters$$inlined$createTrivialTypeConverter$default$2
            final /* synthetic */ ExpectedType $cppRequireType;
            final /* synthetic */ boolean $isOptional;

            @Override // expo.modules.kotlin.types.DynamicAwareTypeConverters
            public Object convertFromAny(Object value) {
                Intrinsics.checkNotNullParameter(value, "value");
                return value;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(z);
                this.$isOptional = z;
                this.$cppRequireType = expectedType9;
            }

            @Override // expo.modules.kotlin.types.TypeConverter
            public ExpectedType getCppRequiredTypes() {
                return this.$cppRequireType;
            }

            @Override // expo.modules.kotlin.types.DynamicAwareTypeConverters
            public Object convertFromDynamic(Dynamic value) {
                Intrinsics.checkNotNullParameter(value, "value");
                throw new UnsupportedClass(Reflection.getOrCreateKotlinClass(Object.class));
            }
        }), TuplesKt.m176to(KClassifiers.createType$default(Reflection.getOrCreateKotlinClass(Int8Array.class), null, z, null, 5, null), new Int8ArrayTypeConverter(z)), TuplesKt.m176to(KClassifiers.createType$default(Reflection.getOrCreateKotlinClass(Int16Array.class), null, z, null, 5, null), new Int16ArrayTypeConverter(z)), TuplesKt.m176to(KClassifiers.createType$default(Reflection.getOrCreateKotlinClass(Int32Array.class), null, z, null, 5, null), new Int32ArrayTypeConverter(z)), TuplesKt.m176to(KClassifiers.createType$default(Reflection.getOrCreateKotlinClass(Uint8Array.class), null, z, null, 5, null), new Uint8ArrayTypeConverter(z)), TuplesKt.m176to(KClassifiers.createType$default(Reflection.getOrCreateKotlinClass(Uint8ClampedArray.class), null, z, null, 5, null), new Uint8ClampedArrayTypeConverter(z)), TuplesKt.m176to(KClassifiers.createType$default(Reflection.getOrCreateKotlinClass(Uint16Array.class), null, z, null, 5, null), new Uint16ArrayTypeConverter(z)), TuplesKt.m176to(KClassifiers.createType$default(Reflection.getOrCreateKotlinClass(Uint32Array.class), null, z, null, 5, null), new Uint32ArrayTypeConverter(z)), TuplesKt.m176to(KClassifiers.createType$default(Reflection.getOrCreateKotlinClass(Float32Array.class), null, z, null, 5, null), new Float32ArrayTypeConverter(z)), TuplesKt.m176to(KClassifiers.createType$default(Reflection.getOrCreateKotlinClass(Float64Array.class), null, z, null, 5, null), new Float64ArrayTypeConverter(z)), TuplesKt.m176to(KClassifiers.createType$default(Reflection.getOrCreateKotlinClass(ConcreteTypedArrays.class), null, z, null, 5, null), new BigInt64ArrayTypeConverter(z)), TuplesKt.m176to(KClassifiers.createType$default(Reflection.getOrCreateKotlinClass(BigUint64Array.class), null, z, null, 5, null), new BigUint64ArrayTypeConverter(z)), TuplesKt.m176to(KClassifiers.createType$default(Reflection.getOrCreateKotlinClass(TypedArray.class), null, z, null, 5, null), new TypedArrayTypeConverter(z)), TuplesKt.m176to(KClassifiers.createType$default(Reflection.getOrCreateKotlinClass(Color.class), null, z, null, 5, null), new ColorTypeConverter(z)), TuplesKt.m176to(KClassifiers.createType$default(Reflection.getOrCreateKotlinClass(URL.class), null, z, null, 5, null), new URLTypConverter(z)), TuplesKt.m176to(KClassifiers.createType$default(Reflection.getOrCreateKotlinClass(Uri.class), null, z, null, 5, null), new UriTypeConverter(z)), TuplesKt.m176to(KClassifiers.createType$default(Reflection.getOrCreateKotlinClass(URI.class), null, z, null, 5, null), new JavaURITypeConverter(z)), TuplesKt.m176to(KClassifiers.createType$default(Reflection.getOrCreateKotlinClass(File.class), null, z, null, 5, null), new FileTypeConverter(z)), TuplesKt.m176to(KClassifiers.createType$default(Reflection.getOrCreateKotlinClass(Object.class), null, z, null, 5, null), new AnyTypeConverter(z)));
        return Build.VERSION.SDK_INT >= 26 ? MapsKt.plus(mapOf, MapsKt.mapOf(TuplesKt.m176to(KClassifiers.createType$default(Reflection.getOrCreateKotlinClass(Path.class), null, z, null, 5, null), new PathTypeConverter(z)))) : mapOf;
    }
}
