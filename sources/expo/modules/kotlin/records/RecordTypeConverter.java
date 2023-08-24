package expo.modules.kotlin.records;

import com.facebook.react.bridge.Dynamic;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableType;
import com.google.android.exoplayer2.source.rtsp.SessionDescription;
import expo.modules.core.errors.CodedException;
import expo.modules.kotlin.allocators.ObjectConstructor;
import expo.modules.kotlin.allocators.ObjectConstructorFactory;
import expo.modules.kotlin.exception.FieldCastException;
import expo.modules.kotlin.exception.FieldRequiredException;
import expo.modules.kotlin.exception.RecordCastException;
import expo.modules.kotlin.exception.UnexpectedException;
import expo.modules.kotlin.jni.CppType;
import expo.modules.kotlin.jni.ExpectedType;
import expo.modules.kotlin.records.Record;
import expo.modules.kotlin.types.DynamicAwareTypeConverters;
import expo.modules.kotlin.types.TypeConverter;
import expo.modules.kotlin.types.TypeConverterProvider;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import kotlin.Metadata;
import kotlin.Tuples;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.JvmClassMapping;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import kotlin.reflect.KClassifier;
import kotlin.reflect.KProperty1;
import kotlin.reflect.KType;
import kotlin.reflect.full.KClasses;
import kotlin.reflect.jvm.ReflectJvmMapping;
import kotlin.text.StringsKt;

/* compiled from: RecordTypeConverter.kt */
@Metadata(m184d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003:\u0001&B\u0015\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0015\u0010\u0012\u001a\u00028\u00002\u0006\u0010\u0013\u001a\u00020\u000eH\u0016¢\u0006\u0002\u0010\u0014J\u0015\u0010\u0015\u001a\u00028\u00002\u0006\u0010\u0013\u001a\u00020\u0016H\u0016¢\u0006\u0002\u0010\u0017J\u0015\u0010\u0018\u001a\u00028\u00002\u0006\u0010\u0019\u001a\u00020\u001aH\u0002¢\u0006\u0002\u0010\u001bJ\b\u0010\u001c\u001a\u00020\u001dH\u0016J\"\u0010\u001e\u001a\b\u0012\u0004\u0012\u0002H\u00010\u001f\"\u0004\b\u0001\u0010\u00012\f\u0010 \u001a\b\u0012\u0004\u0012\u0002H\u00010!H\u0002J&\u0010\"\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030$0#2\u0012\u0010%\u001a\u000e\u0012\u0006\b\u0001\u0012\u00020\u000e\u0012\u0002\b\u00030\rH\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R&\u0010\u000b\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0006\b\u0001\u0012\u00020\u000e\u0012\u0002\b\u00030\r\u0012\u0004\u0012\u00020\u000f0\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006'"}, m183d2 = {"Lexpo/modules/kotlin/records/RecordTypeConverter;", "T", "Lexpo/modules/kotlin/records/Record;", "Lexpo/modules/kotlin/types/DynamicAwareTypeConverters;", "converterProvider", "Lexpo/modules/kotlin/types/TypeConverterProvider;", SessionDescription.ATTR_TYPE, "Lkotlin/reflect/KType;", "(Lexpo/modules/kotlin/types/TypeConverterProvider;Lkotlin/reflect/KType;)V", "objectConstructorFactory", "Lexpo/modules/kotlin/allocators/ObjectConstructorFactory;", "propertyDescriptors", "", "Lkotlin/reflect/KProperty1;", "", "Lexpo/modules/kotlin/records/RecordTypeConverter$PropertyDescriptor;", "getType", "()Lkotlin/reflect/KType;", "convertFromAny", "value", "(Ljava/lang/Object;)Lexpo/modules/kotlin/records/Record;", "convertFromDynamic", "Lcom/facebook/react/bridge/Dynamic;", "(Lcom/facebook/react/bridge/Dynamic;)Lexpo/modules/kotlin/records/Record;", "convertFromReadableMap", "jsMap", "Lcom/facebook/react/bridge/ReadableMap;", "(Lcom/facebook/react/bridge/ReadableMap;)Lexpo/modules/kotlin/records/Record;", "getCppRequiredTypes", "Lexpo/modules/kotlin/jni/ExpectedType;", "getObjectConstructor", "Lexpo/modules/kotlin/allocators/ObjectConstructor;", "clazz", "Ljava/lang/Class;", "getValidators", "", "Lexpo/modules/kotlin/records/FieldValidator;", "property", "PropertyDescriptor", "expo-modules-core_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes4.dex */
public final class RecordTypeConverter<T extends Record> extends DynamicAwareTypeConverters<T> {
    private final TypeConverterProvider converterProvider;
    private final ObjectConstructorFactory objectConstructorFactory;
    private final Map<KProperty1<? extends Object, ?>, PropertyDescriptor> propertyDescriptors;
    private final KType type;

    public final KType getType() {
        return this.type;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public RecordTypeConverter(TypeConverterProvider converterProvider, KType type) {
        super(type.isMarkedNullable());
        Tuples tuples;
        Object obj;
        Intrinsics.checkNotNullParameter(converterProvider, "converterProvider");
        Intrinsics.checkNotNullParameter(type, "type");
        this.converterProvider = converterProvider;
        this.type = type;
        this.objectConstructorFactory = new ObjectConstructorFactory();
        KClassifier classifier = type.getClassifier();
        Objects.requireNonNull(classifier, "null cannot be cast to non-null type kotlin.reflect.KClass<*>");
        Collection<KProperty1<? extends Object, ?>> memberProperties = KClasses.getMemberProperties((KClass) classifier);
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(memberProperties, 10));
        for (KProperty1<? extends Object, ?> kProperty1 : memberProperties) {
            KProperty1<? extends Object, ?> kProperty12 = kProperty1;
            Iterator<T> it = kProperty12.getAnnotations().iterator();
            while (true) {
                tuples = null;
                if (!it.hasNext()) {
                    obj = null;
                    break;
                }
                obj = it.next();
                if (((Annotation) obj) instanceof Field) {
                    break;
                }
            }
            Field field = (Field) obj;
            if (field != null) {
                TypeConverter<?> obtainTypeConverter = this.converterProvider.obtainTypeConverter(kProperty1.getReturnType());
                Iterator<T> it2 = kProperty12.getAnnotations().iterator();
                while (true) {
                    if (!it2.hasNext()) {
                        break;
                    }
                    Object next = it2.next();
                    if (((Annotation) next) instanceof Required) {
                        tuples = next;
                        break;
                    }
                }
                tuples = TuplesKt.m176to(kProperty1, new PropertyDescriptor(obtainTypeConverter, field, ((Required) tuples) != null, getValidators(kProperty1)));
            }
            arrayList.add(tuples);
        }
        this.propertyDescriptors = MapsKt.toMap(CollectionsKt.filterNotNull(arrayList));
    }

    @Override // expo.modules.kotlin.types.DynamicAwareTypeConverters
    public T convertFromDynamic(Dynamic value) {
        Intrinsics.checkNotNullParameter(value, "value");
        try {
            ReadableMap jsMap = value.asMap();
            Intrinsics.checkNotNullExpressionValue(jsMap, "jsMap");
            return convertFromReadableMap(jsMap);
        } catch (CodedException e) {
            String code = e.getCode();
            Intrinsics.checkNotNullExpressionValue(code, "e.code");
            throw new RecordCastException(getType(), new expo.modules.kotlin.exception.CodedException(code, e.getMessage(), e.getCause()));
        } catch (expo.modules.kotlin.exception.CodedException e2) {
            throw new RecordCastException(getType(), e2);
        } catch (Throwable th) {
            throw new RecordCastException(getType(), new UnexpectedException(th));
        }
    }

    @Override // expo.modules.kotlin.types.DynamicAwareTypeConverters
    public T convertFromAny(Object value) {
        Intrinsics.checkNotNullParameter(value, "value");
        if (value instanceof ReadableMap) {
            return convertFromReadableMap((ReadableMap) value);
        }
        return (T) value;
    }

    @Override // expo.modules.kotlin.types.TypeConverter
    public ExpectedType getCppRequiredTypes() {
        return new ExpectedType(CppType.READABLE_MAP);
    }

    private final T convertFromReadableMap(ReadableMap readableMap) {
        KClassifier classifier = this.type.getClassifier();
        Objects.requireNonNull(classifier, "null cannot be cast to non-null type kotlin.reflect.KClass<*>");
        T construct = getObjectConstructor(JvmClassMapping.getJavaClass((KClass) classifier)).construct();
        for (Map.Entry<KProperty1<? extends Object, ?>, PropertyDescriptor> entry : this.propertyDescriptors.entrySet()) {
            KProperty1<? extends Object, ?> key = entry.getKey();
            PropertyDescriptor value = entry.getValue();
            String key2 = value.getFieldAnnotation().key();
            if (StringsKt.isBlank(key2)) {
                key2 = null;
            }
            if (key2 == null) {
                key2 = key.getName();
            }
            if (!readableMap.hasKey(key2)) {
                if (value.isRequired()) {
                    throw new FieldRequiredException(key);
                }
            } else {
                Dynamic dynamic = readableMap.getDynamic(key2);
                Intrinsics.checkNotNullExpressionValue(dynamic, "jsMap.getDynamic(jsKey)");
                try {
                    java.lang.reflect.Field javaField = ReflectJvmMapping.getJavaField(key);
                    Intrinsics.checkNotNull(javaField);
                    try {
                        Object convert = value.getTypeConverter().convert(dynamic);
                        if (convert != null) {
                            Iterator<T> it = value.getValidators().iterator();
                            while (it.hasNext()) {
                                ((FieldValidator) it.next()).validate(convert);
                            }
                        }
                        javaField.setAccessible(true);
                        javaField.set(construct, convert);
                        Unit unit = Unit.INSTANCE;
                    } catch (CodedException e) {
                        String code = e.getCode();
                        Intrinsics.checkNotNullExpressionValue(code, "e.code");
                        expo.modules.kotlin.exception.CodedException codedException = new expo.modules.kotlin.exception.CodedException(code, e.getMessage(), e.getCause());
                        String name = key.getName();
                        KType returnType = key.getReturnType();
                        ReadableType type = dynamic.getType();
                        Intrinsics.checkNotNullExpressionValue(type, "type");
                        throw new FieldCastException(name, returnType, type, codedException);
                    } catch (expo.modules.kotlin.exception.CodedException e2) {
                        String name2 = key.getName();
                        KType returnType2 = key.getReturnType();
                        ReadableType type2 = dynamic.getType();
                        Intrinsics.checkNotNullExpressionValue(type2, "type");
                        throw new FieldCastException(name2, returnType2, type2, e2);
                    }
                } finally {
                    dynamic.recycle();
                }
            }
        }
        return construct;
    }

    private final <T> ObjectConstructor<T> getObjectConstructor(Class<T> cls) {
        return this.objectConstructorFactory.get(cls);
    }

    private final List<FieldValidator<?>> getValidators(KProperty1<? extends Object, ?> kProperty1) {
        Tuples tuples;
        Object obj;
        List<Annotation> annotations = kProperty1.getAnnotations();
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(annotations, 10));
        for (Annotation annotation : annotations) {
            Iterator<T> it = JvmClassMapping.getAnnotationClass(annotation).getAnnotations().iterator();
            while (true) {
                tuples = null;
                if (!it.hasNext()) {
                    obj = null;
                    break;
                }
                obj = it.next();
                if (((Annotation) obj) instanceof BindUsing) {
                    break;
                }
            }
            BindUsing bindUsing = (BindUsing) obj;
            if (bindUsing != null) {
                tuples = TuplesKt.m176to(annotation, bindUsing);
            }
            arrayList.add(tuples);
        }
        List<Tuples> filterNotNull = CollectionsKt.filterNotNull(arrayList);
        ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(filterNotNull, 10));
        for (Tuples tuples2 : filterNotNull) {
            arrayList2.add(((ValidationBinder) KClasses.createInstance(Reflection.getOrCreateKotlinClass(((BindUsing) tuples2.component2()).binder()))).bind((Annotation) tuples2.component1(), kProperty1.getReturnType()));
        }
        return arrayList2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: RecordTypeConverter.kt */
    @Metadata(m184d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0082\b\u0018\u00002\u00020\u0001B3\u0012\n\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0010\u0010\b\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\n0\t¢\u0006\u0002\u0010\u000bJ\r\u0010\u0013\u001a\u0006\u0012\u0002\b\u00030\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0007HÆ\u0003J\u0013\u0010\u0016\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\n0\tHÆ\u0003J?\u0010\u0017\u001a\u00020\u00002\f\b\u0002\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\u0012\b\u0002\u0010\b\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\n0\tHÆ\u0001J\u0013\u0010\u0018\u001a\u00020\u00072\b\u0010\u0019\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001a\u001a\u00020\u001bHÖ\u0001J\t\u0010\u001c\u001a\u00020\u001dHÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u000eR\u0015\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u001b\u0010\b\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\n0\t¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u001e"}, m183d2 = {"Lexpo/modules/kotlin/records/RecordTypeConverter$PropertyDescriptor;", "", "typeConverter", "Lexpo/modules/kotlin/types/TypeConverter;", "fieldAnnotation", "Lexpo/modules/kotlin/records/Field;", "isRequired", "", "validators", "", "Lexpo/modules/kotlin/records/FieldValidator;", "(Lexpo/modules/kotlin/types/TypeConverter;Lexpo/modules/kotlin/records/Field;ZLjava/util/List;)V", "getFieldAnnotation", "()Lexpo/modules/kotlin/records/Field;", "()Z", "getTypeConverter", "()Lexpo/modules/kotlin/types/TypeConverter;", "getValidators", "()Ljava/util/List;", "component1", "component2", "component3", "component4", "copy", "equals", "other", "hashCode", "", "toString", "", "expo-modules-core_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
    /* loaded from: classes4.dex */
    public static final class PropertyDescriptor {
        private final Field fieldAnnotation;
        private final boolean isRequired;
        private final TypeConverter<?> typeConverter;
        private final List<FieldValidator<?>> validators;

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ PropertyDescriptor copy$default(PropertyDescriptor propertyDescriptor, TypeConverter typeConverter, Field field, boolean z, List list, int r5, Object obj) {
            if ((r5 & 1) != 0) {
                typeConverter = propertyDescriptor.typeConverter;
            }
            if ((r5 & 2) != 0) {
                field = propertyDescriptor.fieldAnnotation;
            }
            if ((r5 & 4) != 0) {
                z = propertyDescriptor.isRequired;
            }
            if ((r5 & 8) != 0) {
                list = propertyDescriptor.validators;
            }
            return propertyDescriptor.copy(typeConverter, field, z, list);
        }

        public final TypeConverter<?> component1() {
            return this.typeConverter;
        }

        public final Field component2() {
            return this.fieldAnnotation;
        }

        public final boolean component3() {
            return this.isRequired;
        }

        public final List<FieldValidator<?>> component4() {
            return this.validators;
        }

        public final PropertyDescriptor copy(TypeConverter<?> typeConverter, Field fieldAnnotation, boolean z, List<? extends FieldValidator<?>> validators) {
            Intrinsics.checkNotNullParameter(typeConverter, "typeConverter");
            Intrinsics.checkNotNullParameter(fieldAnnotation, "fieldAnnotation");
            Intrinsics.checkNotNullParameter(validators, "validators");
            return new PropertyDescriptor(typeConverter, fieldAnnotation, z, validators);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof PropertyDescriptor) {
                PropertyDescriptor propertyDescriptor = (PropertyDescriptor) obj;
                return Intrinsics.areEqual(this.typeConverter, propertyDescriptor.typeConverter) && Intrinsics.areEqual(this.fieldAnnotation, propertyDescriptor.fieldAnnotation) && this.isRequired == propertyDescriptor.isRequired && Intrinsics.areEqual(this.validators, propertyDescriptor.validators);
            }
            return false;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public int hashCode() {
            int hashCode = ((this.typeConverter.hashCode() * 31) + this.fieldAnnotation.hashCode()) * 31;
            boolean z = this.isRequired;
            int r1 = z;
            if (z != 0) {
                r1 = 1;
            }
            return ((hashCode + r1) * 31) + this.validators.hashCode();
        }

        public String toString() {
            TypeConverter<?> typeConverter = this.typeConverter;
            Field field = this.fieldAnnotation;
            boolean z = this.isRequired;
            List<FieldValidator<?>> list = this.validators;
            return "PropertyDescriptor(typeConverter=" + typeConverter + ", fieldAnnotation=" + field + ", isRequired=" + z + ", validators=" + list + ")";
        }

        /* JADX WARN: Multi-variable type inference failed */
        public PropertyDescriptor(TypeConverter<?> typeConverter, Field fieldAnnotation, boolean z, List<? extends FieldValidator<?>> validators) {
            Intrinsics.checkNotNullParameter(typeConverter, "typeConverter");
            Intrinsics.checkNotNullParameter(fieldAnnotation, "fieldAnnotation");
            Intrinsics.checkNotNullParameter(validators, "validators");
            this.typeConverter = typeConverter;
            this.fieldAnnotation = fieldAnnotation;
            this.isRequired = z;
            this.validators = validators;
        }

        public final TypeConverter<?> getTypeConverter() {
            return this.typeConverter;
        }

        public final Field getFieldAnnotation() {
            return this.fieldAnnotation;
        }

        public final boolean isRequired() {
            return this.isRequired;
        }

        public final List<FieldValidator<?>> getValidators() {
            return this.validators;
        }
    }
}
