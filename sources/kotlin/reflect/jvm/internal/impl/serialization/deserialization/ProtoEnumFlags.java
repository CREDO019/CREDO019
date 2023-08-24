package kotlin.reflect.jvm.internal.impl.serialization.deserialization;

import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassKind;
import kotlin.reflect.jvm.internal.impl.descriptors.Modality;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf;
import kotlin.reflect.jvm.internal.impl.types.Variance;

/* compiled from: ProtoEnumFlags.kt */
/* loaded from: classes5.dex */
public final class ProtoEnumFlags {
    public static final ProtoEnumFlags INSTANCE = new ProtoEnumFlags();

    /* compiled from: ProtoEnumFlags.kt */
    /* loaded from: classes5.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;
        public static final /* synthetic */ int[] $EnumSwitchMapping$2;
        public static final /* synthetic */ int[] $EnumSwitchMapping$3;
        public static final /* synthetic */ int[] $EnumSwitchMapping$4;
        public static final /* synthetic */ int[] $EnumSwitchMapping$5;
        public static final /* synthetic */ int[] $EnumSwitchMapping$6;
        public static final /* synthetic */ int[] $EnumSwitchMapping$7;

        static {
            int[] r0 = new int[ProtoBuf.Modality.values().length];
            r0[ProtoBuf.Modality.FINAL.ordinal()] = 1;
            r0[ProtoBuf.Modality.OPEN.ordinal()] = 2;
            r0[ProtoBuf.Modality.ABSTRACT.ordinal()] = 3;
            r0[ProtoBuf.Modality.SEALED.ordinal()] = 4;
            $EnumSwitchMapping$0 = r0;
            int[] r02 = new int[Modality.values().length];
            r02[Modality.FINAL.ordinal()] = 1;
            r02[Modality.OPEN.ordinal()] = 2;
            r02[Modality.ABSTRACT.ordinal()] = 3;
            r02[Modality.SEALED.ordinal()] = 4;
            $EnumSwitchMapping$1 = r02;
            int[] r03 = new int[ProtoBuf.Visibility.values().length];
            r03[ProtoBuf.Visibility.INTERNAL.ordinal()] = 1;
            r03[ProtoBuf.Visibility.PRIVATE.ordinal()] = 2;
            r03[ProtoBuf.Visibility.PRIVATE_TO_THIS.ordinal()] = 3;
            r03[ProtoBuf.Visibility.PROTECTED.ordinal()] = 4;
            r03[ProtoBuf.Visibility.PUBLIC.ordinal()] = 5;
            r03[ProtoBuf.Visibility.LOCAL.ordinal()] = 6;
            $EnumSwitchMapping$2 = r03;
            int[] r04 = new int[ProtoBuf.Class.Kind.values().length];
            r04[ProtoBuf.Class.Kind.CLASS.ordinal()] = 1;
            r04[ProtoBuf.Class.Kind.INTERFACE.ordinal()] = 2;
            r04[ProtoBuf.Class.Kind.ENUM_CLASS.ordinal()] = 3;
            r04[ProtoBuf.Class.Kind.ENUM_ENTRY.ordinal()] = 4;
            r04[ProtoBuf.Class.Kind.ANNOTATION_CLASS.ordinal()] = 5;
            r04[ProtoBuf.Class.Kind.OBJECT.ordinal()] = 6;
            r04[ProtoBuf.Class.Kind.COMPANION_OBJECT.ordinal()] = 7;
            $EnumSwitchMapping$3 = r04;
            int[] r05 = new int[ClassKind.values().length];
            r05[ClassKind.CLASS.ordinal()] = 1;
            r05[ClassKind.INTERFACE.ordinal()] = 2;
            r05[ClassKind.ENUM_CLASS.ordinal()] = 3;
            r05[ClassKind.ENUM_ENTRY.ordinal()] = 4;
            r05[ClassKind.ANNOTATION_CLASS.ordinal()] = 5;
            r05[ClassKind.OBJECT.ordinal()] = 6;
            $EnumSwitchMapping$4 = r05;
            int[] r06 = new int[ProtoBuf.TypeParameter.Variance.values().length];
            r06[ProtoBuf.TypeParameter.Variance.IN.ordinal()] = 1;
            r06[ProtoBuf.TypeParameter.Variance.OUT.ordinal()] = 2;
            r06[ProtoBuf.TypeParameter.Variance.INV.ordinal()] = 3;
            $EnumSwitchMapping$5 = r06;
            int[] r07 = new int[ProtoBuf.Type.Argument.Projection.values().length];
            r07[ProtoBuf.Type.Argument.Projection.IN.ordinal()] = 1;
            r07[ProtoBuf.Type.Argument.Projection.OUT.ordinal()] = 2;
            r07[ProtoBuf.Type.Argument.Projection.INV.ordinal()] = 3;
            r07[ProtoBuf.Type.Argument.Projection.STAR.ordinal()] = 4;
            $EnumSwitchMapping$6 = r07;
            int[] r08 = new int[Variance.values().length];
            r08[Variance.IN_VARIANCE.ordinal()] = 1;
            r08[Variance.OUT_VARIANCE.ordinal()] = 2;
            r08[Variance.INVARIANT.ordinal()] = 3;
            $EnumSwitchMapping$7 = r08;
        }
    }

    private ProtoEnumFlags() {
    }

    public final Modality modality(ProtoBuf.Modality modality) {
        int r2 = modality == null ? -1 : WhenMappings.$EnumSwitchMapping$0[modality.ordinal()];
        if (r2 != 1) {
            if (r2 != 2) {
                if (r2 != 3) {
                    if (r2 == 4) {
                        return Modality.SEALED;
                    }
                    return Modality.FINAL;
                }
                return Modality.ABSTRACT;
            }
            return Modality.OPEN;
        }
        return Modality.FINAL;
    }

    public final ClassKind classKind(ProtoBuf.Class.Kind kind) {
        switch (kind == null ? -1 : WhenMappings.$EnumSwitchMapping$3[kind.ordinal()]) {
            case 1:
                return ClassKind.CLASS;
            case 2:
                return ClassKind.INTERFACE;
            case 3:
                return ClassKind.ENUM_CLASS;
            case 4:
                return ClassKind.ENUM_ENTRY;
            case 5:
                return ClassKind.ANNOTATION_CLASS;
            case 6:
            case 7:
                return ClassKind.OBJECT;
            default:
                return ClassKind.CLASS;
        }
    }

    public final Variance variance(ProtoBuf.TypeParameter.Variance variance) {
        Intrinsics.checkNotNullParameter(variance, "variance");
        int r2 = WhenMappings.$EnumSwitchMapping$5[variance.ordinal()];
        if (r2 != 1) {
            if (r2 != 2) {
                if (r2 == 3) {
                    return Variance.INVARIANT;
                }
                throw new NoWhenBranchMatchedException();
            }
            return Variance.OUT_VARIANCE;
        }
        return Variance.IN_VARIANCE;
    }

    public final Variance variance(ProtoBuf.Type.Argument.Projection projection) {
        Intrinsics.checkNotNullParameter(projection, "projection");
        int r0 = WhenMappings.$EnumSwitchMapping$6[projection.ordinal()];
        if (r0 != 1) {
            if (r0 != 2) {
                if (r0 != 3) {
                    if (r0 != 4) {
                        throw new NoWhenBranchMatchedException();
                    }
                    throw new IllegalArgumentException(Intrinsics.stringPlus("Only IN, OUT and INV are supported. Actual argument: ", projection));
                }
                return Variance.INVARIANT;
            }
            return Variance.OUT_VARIANCE;
        }
        return Variance.IN_VARIANCE;
    }
}
