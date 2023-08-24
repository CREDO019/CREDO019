package kotlin.reflect.jvm.internal.impl.serialization.deserialization;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DescriptorVisibilities;
import kotlin.reflect.jvm.internal.impl.descriptors.DescriptorVisibility;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf;

/* renamed from: kotlin.reflect.jvm.internal.impl.serialization.deserialization.ProtoEnumFlagsUtilsKt */
/* loaded from: classes5.dex */
public final class ProtoEnumFlagsUtils {

    /* compiled from: ProtoEnumFlagsUtils.kt */
    /* renamed from: kotlin.reflect.jvm.internal.impl.serialization.deserialization.ProtoEnumFlagsUtilsKt$WhenMappings */
    /* loaded from: classes5.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;
        public static final /* synthetic */ int[] $EnumSwitchMapping$2;

        static {
            int[] r0 = new int[ProtoBuf.MemberKind.values().length];
            r0[ProtoBuf.MemberKind.DECLARATION.ordinal()] = 1;
            r0[ProtoBuf.MemberKind.FAKE_OVERRIDE.ordinal()] = 2;
            r0[ProtoBuf.MemberKind.DELEGATION.ordinal()] = 3;
            r0[ProtoBuf.MemberKind.SYNTHESIZED.ordinal()] = 4;
            $EnumSwitchMapping$0 = r0;
            int[] r02 = new int[CallableMemberDescriptor.Kind.values().length];
            r02[CallableMemberDescriptor.Kind.DECLARATION.ordinal()] = 1;
            r02[CallableMemberDescriptor.Kind.FAKE_OVERRIDE.ordinal()] = 2;
            r02[CallableMemberDescriptor.Kind.DELEGATION.ordinal()] = 3;
            r02[CallableMemberDescriptor.Kind.SYNTHESIZED.ordinal()] = 4;
            $EnumSwitchMapping$1 = r02;
            int[] r03 = new int[ProtoBuf.Visibility.values().length];
            r03[ProtoBuf.Visibility.INTERNAL.ordinal()] = 1;
            r03[ProtoBuf.Visibility.PRIVATE.ordinal()] = 2;
            r03[ProtoBuf.Visibility.PRIVATE_TO_THIS.ordinal()] = 3;
            r03[ProtoBuf.Visibility.PROTECTED.ordinal()] = 4;
            r03[ProtoBuf.Visibility.PUBLIC.ordinal()] = 5;
            r03[ProtoBuf.Visibility.LOCAL.ordinal()] = 6;
            $EnumSwitchMapping$2 = r03;
        }
    }

    public static final CallableMemberDescriptor.Kind memberKind(ProtoEnumFlags protoEnumFlags, ProtoBuf.MemberKind memberKind) {
        Intrinsics.checkNotNullParameter(protoEnumFlags, "<this>");
        int r1 = memberKind == null ? -1 : WhenMappings.$EnumSwitchMapping$0[memberKind.ordinal()];
        if (r1 != 1) {
            if (r1 != 2) {
                if (r1 != 3) {
                    if (r1 == 4) {
                        return CallableMemberDescriptor.Kind.SYNTHESIZED;
                    }
                    return CallableMemberDescriptor.Kind.DECLARATION;
                }
                return CallableMemberDescriptor.Kind.DELEGATION;
            }
            return CallableMemberDescriptor.Kind.FAKE_OVERRIDE;
        }
        return CallableMemberDescriptor.Kind.DECLARATION;
    }

    public static final DescriptorVisibility descriptorVisibility(ProtoEnumFlags protoEnumFlags, ProtoBuf.Visibility visibility) {
        Intrinsics.checkNotNullParameter(protoEnumFlags, "<this>");
        switch (visibility == null ? -1 : WhenMappings.$EnumSwitchMapping$2[visibility.ordinal()]) {
            case 1:
                DescriptorVisibility INTERNAL = DescriptorVisibilities.INTERNAL;
                Intrinsics.checkNotNullExpressionValue(INTERNAL, "INTERNAL");
                return INTERNAL;
            case 2:
                DescriptorVisibility PRIVATE = DescriptorVisibilities.PRIVATE;
                Intrinsics.checkNotNullExpressionValue(PRIVATE, "PRIVATE");
                return PRIVATE;
            case 3:
                DescriptorVisibility PRIVATE_TO_THIS = DescriptorVisibilities.PRIVATE_TO_THIS;
                Intrinsics.checkNotNullExpressionValue(PRIVATE_TO_THIS, "PRIVATE_TO_THIS");
                return PRIVATE_TO_THIS;
            case 4:
                DescriptorVisibility PROTECTED = DescriptorVisibilities.PROTECTED;
                Intrinsics.checkNotNullExpressionValue(PROTECTED, "PROTECTED");
                return PROTECTED;
            case 5:
                DescriptorVisibility PUBLIC = DescriptorVisibilities.PUBLIC;
                Intrinsics.checkNotNullExpressionValue(PUBLIC, "PUBLIC");
                return PUBLIC;
            case 6:
                DescriptorVisibility LOCAL = DescriptorVisibilities.LOCAL;
                Intrinsics.checkNotNullExpressionValue(LOCAL, "LOCAL");
                return LOCAL;
            default:
                DescriptorVisibility PRIVATE2 = DescriptorVisibilities.PRIVATE;
                Intrinsics.checkNotNullExpressionValue(PRIVATE2, "PRIVATE");
                return PRIVATE2;
        }
    }
}
