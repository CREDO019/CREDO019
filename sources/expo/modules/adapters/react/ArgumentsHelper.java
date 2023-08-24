package expo.modules.adapters.react;

import com.facebook.react.bridge.Dynamic;
import com.facebook.react.bridge.ReadableType;
import expo.modules.core.arguments.MapArguments;
import expo.modules.core.arguments.ReadableArguments;

/* loaded from: classes4.dex */
public class ArgumentsHelper {

    /* renamed from: expo.modules.adapters.react.ArgumentsHelper$1 */
    /* loaded from: classes4.dex */
    static /* synthetic */ class C42721 {
        static final /* synthetic */ int[] $SwitchMap$com$facebook$react$bridge$ReadableType;

        static {
            int[] r0 = new int[ReadableType.values().length];
            $SwitchMap$com$facebook$react$bridge$ReadableType = r0;
            try {
                r0[ReadableType.String.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$facebook$react$bridge$ReadableType[ReadableType.Map.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$facebook$react$bridge$ReadableType[ReadableType.Array.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$facebook$react$bridge$ReadableType[ReadableType.Number.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$facebook$react$bridge$ReadableType[ReadableType.Boolean.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$facebook$react$bridge$ReadableType[ReadableType.Null.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    public static Object getNativeArgumentForExpectedClass(Dynamic dynamic, Class<?> cls) {
        switch (C42721.$SwitchMap$com$facebook$react$bridge$ReadableType[dynamic.getType().ordinal()]) {
            case 1:
                return dynamic.asString();
            case 2:
                if (cls.isAssignableFrom(ReadableArguments.class)) {
                    return new MapArguments(dynamic.asMap().toHashMap());
                }
                return dynamic.asMap().toHashMap();
            case 3:
                return dynamic.asArray().toArrayList();
            case 4:
                Double valueOf = Double.valueOf(dynamic.asDouble());
                if (cls == Byte.TYPE || cls == Byte.class) {
                    return Byte.valueOf(valueOf.byteValue());
                }
                if (cls == Short.TYPE || cls == Short.class) {
                    return Short.valueOf(valueOf.shortValue());
                }
                if (cls == Integer.TYPE || cls == Integer.class) {
                    return Integer.valueOf(valueOf.intValue());
                }
                if (cls == Float.TYPE || cls == Float.class) {
                    return Float.valueOf(valueOf.floatValue());
                }
                return (cls == Long.TYPE || cls == Long.class) ? Long.valueOf(valueOf.longValue()) : valueOf;
            case 5:
                return Boolean.valueOf(dynamic.asBoolean());
            case 6:
                return null;
            default:
                throw new RuntimeException("Don't know how to convert React Native argument of type " + dynamic.getType() + " to native.");
        }
    }
}
