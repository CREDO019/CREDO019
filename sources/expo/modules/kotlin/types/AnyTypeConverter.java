package expo.modules.kotlin.types;

import com.facebook.react.bridge.Dynamic;
import com.facebook.react.bridge.ReadableType;
import expo.modules.kotlin.jni.ExpectedType;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AnyTypeConverter.kt */
@Metadata(m184d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0010\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0002H\u0016J\u0010\u0010\b\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\tH\u0016J\b\u0010\n\u001a\u00020\u000bH\u0016¨\u0006\f"}, m183d2 = {"Lexpo/modules/kotlin/types/AnyTypeConverter;", "Lexpo/modules/kotlin/types/DynamicAwareTypeConverters;", "", "isOptional", "", "(Z)V", "convertFromAny", "value", "convertFromDynamic", "Lcom/facebook/react/bridge/Dynamic;", "getCppRequiredTypes", "Lexpo/modules/kotlin/jni/ExpectedType;", "expo-modules-core_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes4.dex */
public final class AnyTypeConverter extends DynamicAwareTypeConverters<Object> {

    /* compiled from: AnyTypeConverter.kt */
    @Metadata(m182k = 3, m181mv = {1, 6, 0}, m179xi = 48)
    /* loaded from: classes4.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] r0 = new int[ReadableType.values().length];
            r0[ReadableType.Boolean.ordinal()] = 1;
            r0[ReadableType.Number.ordinal()] = 2;
            r0[ReadableType.String.ordinal()] = 3;
            r0[ReadableType.Map.ordinal()] = 4;
            r0[ReadableType.Array.ordinal()] = 5;
            $EnumSwitchMapping$0 = r0;
        }
    }

    @Override // expo.modules.kotlin.types.DynamicAwareTypeConverters
    public Object convertFromAny(Object value) {
        Intrinsics.checkNotNullParameter(value, "value");
        return value;
    }

    public AnyTypeConverter(boolean z) {
        super(z);
    }

    @Override // expo.modules.kotlin.types.DynamicAwareTypeConverters
    public Object convertFromDynamic(Dynamic value) {
        Object valueOf;
        Intrinsics.checkNotNullParameter(value, "value");
        ReadableType type = value.getType();
        int r0 = type == null ? -1 : WhenMappings.$EnumSwitchMapping$0[type.ordinal()];
        if (r0 == 1) {
            valueOf = Boolean.valueOf(value.asBoolean());
        } else if (r0 == 2) {
            valueOf = Double.valueOf(value.asDouble());
        } else if (r0 == 3) {
            valueOf = value.asString();
        } else if (r0 == 4) {
            valueOf = value.asMap();
        } else if (r0 == 5) {
            valueOf = value.asArray();
        } else {
            ReadableType type2 = value.getType();
            throw new IllegalStateException(("Unknown dynamic type: " + type2).toString());
        }
        Intrinsics.checkNotNullExpressionValue(valueOf, "when (value.type) {\n    …pe: ${value.type}\")\n    }");
        return valueOf;
    }

    @Override // expo.modules.kotlin.types.TypeConverter
    public ExpectedType getCppRequiredTypes() {
        return ExpectedType.Companion.forAny();
    }
}
