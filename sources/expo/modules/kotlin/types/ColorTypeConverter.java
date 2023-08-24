package expo.modules.kotlin.types;

import android.graphics.Color;
import com.facebook.react.bridge.Dynamic;
import com.facebook.react.bridge.ReadableType;
import expo.modules.kotlin.exception.UnexpectedException;
import expo.modules.kotlin.jni.CppType;
import expo.modules.kotlin.jni.ExpectedType;
import expo.modules.kotlin.jni.SingleType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;

/* compiled from: ColorTypeConverter.kt */
@Metadata(m184d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0013\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0010\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\bH\u0002J\u0010\u0010\t\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\nH\u0002J\u0010\u0010\u000b\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\fH\u0002J\u0010\u0010\r\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u000eH\u0016J\u0010\u0010\u000f\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0010H\u0016J\b\u0010\u0011\u001a\u00020\u0012H\u0016J\b\u0010\u0013\u001a\u00020\u0004H\u0016¨\u0006\u0014"}, m183d2 = {"Lexpo/modules/kotlin/types/ColorTypeConverter;", "Lexpo/modules/kotlin/types/DynamicAwareTypeConverters;", "Landroid/graphics/Color;", "isOptional", "", "(Z)V", "colorFromDoubleArray", "value", "", "colorFromInt", "", "colorFromString", "", "convertFromAny", "", "convertFromDynamic", "Lcom/facebook/react/bridge/Dynamic;", "getCppRequiredTypes", "Lexpo/modules/kotlin/jni/ExpectedType;", "isTrivial", "expo-modules-core_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes4.dex */
public final class ColorTypeConverter extends DynamicAwareTypeConverters<Color> {

    /* compiled from: ColorTypeConverter.kt */
    @Metadata(m182k = 3, m181mv = {1, 6, 0}, m179xi = 48)
    /* loaded from: classes4.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] r0 = new int[ReadableType.values().length];
            r0[ReadableType.Number.ordinal()] = 1;
            r0[ReadableType.String.ordinal()] = 2;
            r0[ReadableType.Array.ordinal()] = 3;
            $EnumSwitchMapping$0 = r0;
        }
    }

    @Override // expo.modules.kotlin.types.TypeConverter
    public boolean isTrivial() {
        return false;
    }

    public ColorTypeConverter(boolean z) {
        super(z);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // expo.modules.kotlin.types.DynamicAwareTypeConverters
    public Color convertFromDynamic(Dynamic value) {
        Intrinsics.checkNotNullParameter(value, "value");
        ReadableType type = value.getType();
        int r0 = type == null ? -1 : WhenMappings.$EnumSwitchMapping$0[type.ordinal()];
        if (r0 != 1) {
            if (r0 == 2) {
                String asString = value.asString();
                Intrinsics.checkNotNullExpressionValue(asString, "value.asString()");
                return colorFromString(asString);
            } else if (r0 == 3) {
                ArrayList<Object> arrayList = value.asArray().toArrayList();
                Intrinsics.checkNotNullExpressionValue(arrayList, "value.asArray().toArrayList()");
                ArrayList<Object> arrayList2 = arrayList;
                ArrayList arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList2, 10));
                for (Object obj : arrayList2) {
                    Objects.requireNonNull(obj, "null cannot be cast to non-null type kotlin.Double");
                    arrayList3.add(Double.valueOf(((Double) obj).doubleValue()));
                }
                return colorFromDoubleArray(CollectionsKt.toDoubleArray(arrayList3));
            } else {
                ReadableType type2 = value.getType();
                throw new UnexpectedException("Unknown argument type: " + type2);
            }
        }
        return colorFromInt((int) value.asDouble());
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // expo.modules.kotlin.types.DynamicAwareTypeConverters
    public Color convertFromAny(Object value) {
        Intrinsics.checkNotNullParameter(value, "value");
        if (value instanceof Integer) {
            return colorFromInt(((Number) value).intValue());
        }
        if (value instanceof String) {
            return colorFromString((String) value);
        }
        if (value instanceof double[]) {
            return colorFromDoubleArray((double[]) value);
        }
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(value.getClass());
        throw new UnexpectedException("Unknown argument type: " + orCreateKotlinClass);
    }

    private final Color colorFromDoubleArray(double[] dArr) {
        Double orNull = ArraysKt.getOrNull(dArr, 3);
        Color valueOf = Color.valueOf((float) dArr[0], (float) dArr[1], (float) dArr[2], (float) (orNull == null ? 1.0d : orNull.doubleValue()));
        Intrinsics.checkNotNullExpressionValue(valueOf, "valueOf(value[0].toFloat…Float(), alpha.toFloat())");
        return valueOf;
    }

    private final Color colorFromInt(int r2) {
        Color valueOf = Color.valueOf(r2);
        Intrinsics.checkNotNullExpressionValue(valueOf, "valueOf(value)");
        return valueOf;
    }

    private final Color colorFromString(String str) {
        Map map;
        map = ColorTypeConverterKt.namedColors;
        List list = (List) map.get(str);
        if (list != null) {
            Color valueOf = Color.valueOf(((Number) list.get(0)).floatValue(), ((Number) list.get(1)).floatValue(), ((Number) list.get(2)).floatValue(), ((Number) list.get(3)).floatValue());
            Intrinsics.checkNotNullExpressionValue(valueOf, "valueOf(\n        colorFr…olorFromString[3]\n      )");
            return valueOf;
        }
        Color valueOf2 = Color.valueOf(Color.parseColor(str));
        Intrinsics.checkNotNullExpressionValue(valueOf2, "valueOf(Color.parseColor(value))");
        return valueOf2;
    }

    @Override // expo.modules.kotlin.types.TypeConverter
    public ExpectedType getCppRequiredTypes() {
        return new ExpectedType(new SingleType(CppType.INT, null, 2, null), new SingleType(CppType.STRING, null, 2, null), new SingleType(CppType.PRIMITIVE_ARRAY, new ExpectedType[]{new ExpectedType(CppType.DOUBLE)}));
    }
}
