package expo.modules.kotlin.types;

import com.facebook.react.bridge.Dynamic;
import com.facebook.react.bridge.ReadableType;
import expo.modules.core.logging.Logger;
import expo.modules.kotlin.CoreLogger;
import expo.modules.kotlin.ReadableTypeExtensions;
import expo.modules.kotlin.exception.EnumNoSuchValueException;
import expo.modules.kotlin.exception.IncompatibleArgTypeException;
import expo.modules.kotlin.jni.ExpectedType;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmClassMapping;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import kotlin.reflect.KClassifier;
import kotlin.reflect.KFunction;
import kotlin.reflect.KParameter;
import kotlin.reflect.KProperty1;
import kotlin.reflect.full.KClasses;
import kotlin.reflect.full.KClassifiers;

/* compiled from: EnumTypeConverter.kt */
@Metadata(m184d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00020\u0001B\u001f\u0012\u0010\u0010\u0003\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J5\u0010\u000e\u001a\u0006\u0012\u0002\b\u00030\u00022\u0006\u0010\u000f\u001a\u00020\u00102\u0012\u0010\b\u001a\u000e\u0012\n\b\u0001\u0012\u0006\u0012\u0002\b\u00030\u00020\t2\u0006\u0010\u0011\u001a\u00020\u0012H\u0002¢\u0006\u0002\u0010\u0013J-\u0010\u0014\u001a\u0006\u0012\u0002\b\u00030\u00022\u0006\u0010\u0015\u001a\u00020\u00122\u0012\u0010\b\u001a\u000e\u0012\n\b\u0001\u0012\u0006\u0012\u0002\b\u00030\u00020\tH\u0002¢\u0006\u0002\u0010\u0016J\u0014\u0010\u0017\u001a\u0006\u0012\u0002\b\u00030\u00022\u0006\u0010\u0018\u001a\u00020\u0010H\u0016J\u0014\u0010\u0019\u001a\u0006\u0012\u0002\b\u00030\u00022\u0006\u0010\u0018\u001a\u00020\u001aH\u0016J\b\u0010\u001b\u001a\u00020\u001cH\u0016J\b\u0010\u001d\u001a\u00020\u0006H\u0016R\u0018\u0010\u0003\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000RD\u0010\b\u001a6\u0012\u0014\u0012\u0012\u0012\u0002\b\u0003 \n*\b\u0012\u0002\b\u0003\u0018\u00010\u00020\u0002 \n*\u001a\u0012\u0016\b\u0001\u0012\u0012\u0012\u0002\b\u0003 \n*\b\u0012\u0002\b\u0003\u0018\u00010\u00020\u00020\t0\tX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u000bR\u0018\u0010\f\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001e"}, m183d2 = {"Lexpo/modules/kotlin/types/EnumTypeConverter;", "Lexpo/modules/kotlin/types/DynamicAwareTypeConverters;", "", "enumClass", "Lkotlin/reflect/KClass;", "isOptional", "", "(Lkotlin/reflect/KClass;Z)V", "enumConstants", "", "kotlin.jvm.PlatformType", "[Ljava/lang/Enum;", "primaryConstructor", "Lkotlin/reflect/KFunction;", "convertEnumWithParameter", "jsValue", "", "parameterName", "", "(Ljava/lang/Object;[Ljava/lang/Enum;Ljava/lang/String;)Ljava/lang/Enum;", "convertEnumWithoutParameter", "stringRepresentation", "(Ljava/lang/String;[Ljava/lang/Enum;)Ljava/lang/Enum;", "convertFromAny", "value", "convertFromDynamic", "Lcom/facebook/react/bridge/Dynamic;", "getCppRequiredTypes", "Lexpo/modules/kotlin/jni/ExpectedType;", "isTrivial", "expo-modules-core_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes4.dex */
public final class EnumTypeConverter extends DynamicAwareTypeConverters<Enum<?>> {
    private final KClass<Enum<?>> enumClass;
    private final Enum<?>[] enumConstants;
    private final KFunction<Enum<?>> primaryConstructor;

    @Override // expo.modules.kotlin.types.TypeConverter
    public boolean isTrivial() {
        return false;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public EnumTypeConverter(KClass<Enum<?>> enumClass, boolean z) {
        super(z);
        Intrinsics.checkNotNullParameter(enumClass, "enumClass");
        this.enumClass = enumClass;
        Object[] enumConstants = JvmClassMapping.getJavaClass((KClass) enumClass).getEnumConstants();
        if (enumConstants == null) {
            throw new IllegalArgumentException("Passed type is not an enum type".toString());
        }
        Enum<?>[] enumArr = (Enum[]) enumConstants;
        if (!(enumArr.length == 0)) {
            this.enumConstants = enumArr;
            KFunction<Enum<?>> primaryConstructor = KClasses.getPrimaryConstructor(enumClass);
            if (primaryConstructor == null) {
                throw new IllegalArgumentException("Cannot convert js value to enum without the primary constructor".toString());
            }
            this.primaryConstructor = primaryConstructor;
            if (KClasses.isSubclassOf(enumClass, Reflection.getOrCreateKotlinClass(Enumerable.class))) {
                return;
            }
            Logger logger = CoreLogger.getLogger();
            KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(Enumerable.class);
            Logger.warn$default(logger, "Enum '" + enumClass + "' should inherit from " + orCreateKotlinClass + ".", null, 2, null);
            return;
        }
        throw new IllegalArgumentException("Passed enum type is empty".toString());
    }

    @Override // expo.modules.kotlin.types.TypeConverter
    public ExpectedType getCppRequiredTypes() {
        return ExpectedType.Companion.forEnum();
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // expo.modules.kotlin.types.DynamicAwareTypeConverters
    public Enum<?> convertFromDynamic(Dynamic value) {
        Intrinsics.checkNotNullParameter(value, "value");
        if (this.primaryConstructor.getParameters().isEmpty()) {
            String asString = value.asString();
            Intrinsics.checkNotNullExpressionValue(asString, "value.asString()");
            return convertEnumWithoutParameter(asString, this.enumConstants);
        } else if (this.primaryConstructor.getParameters().size() == 1) {
            Enum<?>[] enumArr = this.enumConstants;
            String name = ((KParameter) CollectionsKt.first((List<? extends Object>) this.primaryConstructor.getParameters())).getName();
            Intrinsics.checkNotNull(name);
            return convertEnumWithParameter(value, enumArr, name);
        } else {
            ReadableType type = value.getType();
            Intrinsics.checkNotNullExpressionValue(type, "value.type");
            throw new IncompatibleArgTypeException(ReadableTypeExtensions.toKType(type), KClassifiers.createType$default(this.enumClass, null, false, null, 7, null), null, 4, null);
        }
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // expo.modules.kotlin.types.DynamicAwareTypeConverters
    public Enum<?> convertFromAny(Object value) {
        Intrinsics.checkNotNullParameter(value, "value");
        if (this.primaryConstructor.getParameters().isEmpty()) {
            return convertEnumWithoutParameter((String) value, this.enumConstants);
        }
        if (this.primaryConstructor.getParameters().size() == 1) {
            Enum<?>[] enumArr = this.enumConstants;
            String name = ((KParameter) CollectionsKt.first((List<? extends Object>) this.primaryConstructor.getParameters())).getName();
            Intrinsics.checkNotNull(name);
            return convertEnumWithParameter(value, enumArr, name);
        }
        throw new IncompatibleArgTypeException(KClassifiers.createType$default(Reflection.getOrCreateKotlinClass(value.getClass()), null, false, null, 7, null), KClassifiers.createType$default(this.enumClass, null, false, null, 7, null), null, 4, null);
    }

    private final Enum<?> convertEnumWithoutParameter(String str, Enum<?>[] enumArr) {
        Enum<?> r2;
        int length = enumArr.length;
        int r1 = 0;
        while (true) {
            if (r1 >= length) {
                r2 = null;
                break;
            }
            r2 = enumArr[r1];
            r1++;
            if (Intrinsics.areEqual(r2.name(), str)) {
                break;
            }
        }
        if (r2 != null) {
            return r2;
        }
        throw new EnumNoSuchValueException(this.enumClass, enumArr, str);
    }

    private final Enum<?> convertEnumWithParameter(Object obj, Enum<?>[] enumArr, String str) {
        Enum<?> r2;
        Object obj2;
        int intValue;
        Object valueOf;
        Iterator it = KClasses.getDeclaredMemberProperties(this.enumClass).iterator();
        while (true) {
            r2 = null;
            if (!it.hasNext()) {
                obj2 = null;
                break;
            }
            obj2 = it.next();
            if (Intrinsics.areEqual(((KProperty1) obj2).getName(), str)) {
                break;
            }
        }
        KProperty1 kProperty1 = (KProperty1) obj2;
        if (kProperty1 == null) {
            throw new IllegalArgumentException(("Cannot find a property for " + str + " parameter").toString());
        }
        KClassifier classifier = kProperty1.getReturnType().getClassifier();
        if (obj instanceof Dynamic) {
            if (Intrinsics.areEqual(classifier, Reflection.getOrCreateKotlinClass(String.class))) {
                valueOf = ((Dynamic) obj).asString();
            } else {
                valueOf = Integer.valueOf(((Dynamic) obj).asInt());
            }
        } else if (Intrinsics.areEqual(classifier, Reflection.getOrCreateKotlinClass(String.class))) {
            valueOf = (String) obj;
        } else {
            if (obj instanceof Double) {
                intValue = (int) ((Number) obj).doubleValue();
            } else {
                intValue = ((Integer) obj).intValue();
            }
            valueOf = Integer.valueOf(intValue);
        }
        int r3 = 0;
        int length = enumArr.length;
        while (true) {
            if (r3 >= length) {
                break;
            }
            Enum<?> r5 = enumArr[r3];
            r3++;
            if (Intrinsics.areEqual(kProperty1.get(r5), valueOf)) {
                r2 = r5;
                break;
            }
        }
        if (r2 != null) {
            return r2;
        }
        throw new IllegalArgumentException(("Couldn't convert '" + obj + "' to " + this.enumClass.getSimpleName() + " where " + str + " is the enum parameter").toString());
    }
}
