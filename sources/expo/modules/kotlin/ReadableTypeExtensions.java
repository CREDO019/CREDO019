package expo.modules.kotlin;

import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableType;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KType;
import kotlin.reflect.full.KClassifiers;

@Metadata(m184d1 = {"\u0000\f\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0002¨\u0006\u0003"}, m183d2 = {"toKType", "Lkotlin/reflect/KType;", "Lcom/facebook/react/bridge/ReadableType;", "expo-modules-core_release"}, m182k = 2, m181mv = {1, 6, 0}, m179xi = 48)
/* renamed from: expo.modules.kotlin.ReadableTypeExtensionsKt */
/* loaded from: classes4.dex */
public final class ReadableTypeExtensions {

    /* compiled from: ReadableTypeExtensions.kt */
    @Metadata(m182k = 3, m181mv = {1, 6, 0}, m179xi = 48)
    /* renamed from: expo.modules.kotlin.ReadableTypeExtensionsKt$WhenMappings */
    /* loaded from: classes4.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] r0 = new int[ReadableType.values().length];
            r0[ReadableType.Null.ordinal()] = 1;
            r0[ReadableType.Boolean.ordinal()] = 2;
            r0[ReadableType.Number.ordinal()] = 3;
            r0[ReadableType.String.ordinal()] = 4;
            r0[ReadableType.Map.ordinal()] = 5;
            r0[ReadableType.Array.ordinal()] = 6;
            $EnumSwitchMapping$0 = r0;
        }
    }

    public static final KType toKType(ReadableType readableType) {
        Intrinsics.checkNotNullParameter(readableType, "<this>");
        switch (WhenMappings.$EnumSwitchMapping$0[readableType.ordinal()]) {
            case 1:
                return KClassifiers.createType$default(Reflection.getOrCreateKotlinClass(Object.class), null, true, null, 5, null);
            case 2:
                return KClassifiers.createType$default(Reflection.getOrCreateKotlinClass(Boolean.TYPE), null, false, null, 7, null);
            case 3:
                return KClassifiers.createType$default(Reflection.getOrCreateKotlinClass(Number.class), null, false, null, 7, null);
            case 4:
                return KClassifiers.createType$default(Reflection.getOrCreateKotlinClass(String.class), null, false, null, 7, null);
            case 5:
                return KClassifiers.createType$default(Reflection.getOrCreateKotlinClass(ReadableMap.class), null, false, null, 7, null);
            case 6:
                return KClassifiers.createType$default(Reflection.getOrCreateKotlinClass(ReadableArray.class), null, false, null, 7, null);
            default:
                throw new NoWhenBranchMatchedException();
        }
    }
}
