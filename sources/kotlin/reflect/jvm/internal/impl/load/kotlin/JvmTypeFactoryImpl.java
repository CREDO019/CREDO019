package kotlin.reflect.jvm.internal.impl.load.kotlin;

import androidx.exifinterface.media.ExifInterface;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.PrimitiveType;
import kotlin.reflect.jvm.internal.impl.load.kotlin.methodSignatureMapping;
import kotlin.reflect.jvm.internal.impl.resolve.jvm.JvmClassName;
import kotlin.reflect.jvm.internal.impl.resolve.jvm.JvmPrimitiveType;
import kotlin.text.StringsKt;
import org.bouncycastle.pqc.math.linearalgebra.Matrix;

/* compiled from: methodSignatureMapping.kt */
/* loaded from: classes5.dex */
final class JvmTypeFactoryImpl implements JvmTypeFactory<methodSignatureMapping> {
    public static final JvmTypeFactoryImpl INSTANCE = new JvmTypeFactoryImpl();

    /* compiled from: methodSignatureMapping.kt */
    /* loaded from: classes5.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] r0 = new int[PrimitiveType.values().length];
            r0[PrimitiveType.BOOLEAN.ordinal()] = 1;
            r0[PrimitiveType.CHAR.ordinal()] = 2;
            r0[PrimitiveType.BYTE.ordinal()] = 3;
            r0[PrimitiveType.SHORT.ordinal()] = 4;
            r0[PrimitiveType.INT.ordinal()] = 5;
            r0[PrimitiveType.FLOAT.ordinal()] = 6;
            r0[PrimitiveType.LONG.ordinal()] = 7;
            r0[PrimitiveType.DOUBLE.ordinal()] = 8;
            $EnumSwitchMapping$0 = r0;
        }
    }

    private JvmTypeFactoryImpl() {
    }

    @Override // kotlin.reflect.jvm.internal.impl.load.kotlin.JvmTypeFactory
    public methodSignatureMapping boxType(methodSignatureMapping possiblyPrimitiveType) {
        Intrinsics.checkNotNullParameter(possiblyPrimitiveType, "possiblyPrimitiveType");
        if (possiblyPrimitiveType instanceof methodSignatureMapping.Primitive) {
            methodSignatureMapping.Primitive primitive = (methodSignatureMapping.Primitive) possiblyPrimitiveType;
            if (primitive.getJvmPrimitiveType() != null) {
                String internalName = JvmClassName.byFqNameWithoutInnerClasses(primitive.getJvmPrimitiveType().getWrapperFqName()).getInternalName();
                Intrinsics.checkNotNullExpressionValue(internalName, "byFqNameWithoutInnerClas…apperFqName).internalName");
                return createObjectType(internalName);
            }
            return possiblyPrimitiveType;
        }
        return possiblyPrimitiveType;
    }

    @Override // kotlin.reflect.jvm.internal.impl.load.kotlin.JvmTypeFactory
    public methodSignatureMapping createFromString(String representation) {
        JvmPrimitiveType jvmPrimitiveType;
        boolean z;
        Intrinsics.checkNotNullParameter(representation, "representation");
        String str = representation;
        str.length();
        char charAt = representation.charAt(0);
        JvmPrimitiveType[] values = JvmPrimitiveType.values();
        int length = values.length;
        int r5 = 0;
        while (true) {
            if (r5 >= length) {
                jvmPrimitiveType = null;
                break;
            }
            jvmPrimitiveType = values[r5];
            r5++;
            if (jvmPrimitiveType.getDesc().charAt(0) == charAt) {
                z = true;
                continue;
            } else {
                z = false;
                continue;
            }
            if (z) {
                break;
            }
        }
        if (jvmPrimitiveType == null) {
            if (charAt == 'V') {
                return new methodSignatureMapping.Primitive(null);
            }
            if (charAt == '[') {
                String substring = representation.substring(1);
                Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String).substring(startIndex)");
                return new methodSignatureMapping.Array(createFromString(substring));
            }
            if (charAt == 'L') {
                StringsKt.endsWith$default((CharSequence) str, ';', false, 2, (Object) null);
            }
            String substring2 = representation.substring(1, representation.length() - 1);
            Intrinsics.checkNotNullExpressionValue(substring2, "this as java.lang.String…ing(startIndex, endIndex)");
            return new methodSignatureMapping.Object(substring2);
        }
        return new methodSignatureMapping.Primitive(jvmPrimitiveType);
    }

    @Override // kotlin.reflect.jvm.internal.impl.load.kotlin.JvmTypeFactory
    public methodSignatureMapping createPrimitiveType(PrimitiveType primitiveType) {
        Intrinsics.checkNotNullParameter(primitiveType, "primitiveType");
        switch (WhenMappings.$EnumSwitchMapping$0[primitiveType.ordinal()]) {
            case 1:
                return methodSignatureMapping.Companion.getBOOLEAN$descriptors_jvm();
            case 2:
                return methodSignatureMapping.Companion.getCHAR$descriptors_jvm();
            case 3:
                return methodSignatureMapping.Companion.getBYTE$descriptors_jvm();
            case 4:
                return methodSignatureMapping.Companion.getSHORT$descriptors_jvm();
            case 5:
                return methodSignatureMapping.Companion.getINT$descriptors_jvm();
            case 6:
                return methodSignatureMapping.Companion.getFLOAT$descriptors_jvm();
            case 7:
                return methodSignatureMapping.Companion.getLONG$descriptors_jvm();
            case 8:
                return methodSignatureMapping.Companion.getDOUBLE$descriptors_jvm();
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    @Override // kotlin.reflect.jvm.internal.impl.load.kotlin.JvmTypeFactory
    public methodSignatureMapping createObjectType(String internalName) {
        Intrinsics.checkNotNullParameter(internalName, "internalName");
        return new methodSignatureMapping.Object(internalName);
    }

    @Override // kotlin.reflect.jvm.internal.impl.load.kotlin.JvmTypeFactory
    public String toString(methodSignatureMapping type) {
        Intrinsics.checkNotNullParameter(type, "type");
        if (type instanceof methodSignatureMapping.Array) {
            return Intrinsics.stringPlus("[", toString(((methodSignatureMapping.Array) type).getElementType()));
        }
        if (type instanceof methodSignatureMapping.Primitive) {
            JvmPrimitiveType jvmPrimitiveType = ((methodSignatureMapping.Primitive) type).getJvmPrimitiveType();
            String desc = jvmPrimitiveType == null ? ExifInterface.GPS_MEASUREMENT_INTERRUPTED : jvmPrimitiveType.getDesc();
            Intrinsics.checkNotNullExpressionValue(desc, "type.jvmPrimitiveType?.desc ?: \"V\"");
            return desc;
        } else if (type instanceof methodSignatureMapping.Object) {
            return Matrix.MATRIX_TYPE_RANDOM_LT + ((methodSignatureMapping.Object) type).getInternalName() + ';';
        } else {
            throw new NoWhenBranchMatchedException();
        }
    }

    @Override // kotlin.reflect.jvm.internal.impl.load.kotlin.JvmTypeFactory
    public methodSignatureMapping getJavaLangClassType() {
        return createObjectType("java/lang/Class");
    }
}
