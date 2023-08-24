package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public enum zzgof {
    DOUBLE(0, 1, zzgpb.DOUBLE),
    FLOAT(1, 1, zzgpb.FLOAT),
    INT64(2, 1, zzgpb.LONG),
    UINT64(3, 1, zzgpb.LONG),
    INT32(4, 1, zzgpb.INT),
    FIXED64(5, 1, zzgpb.LONG),
    FIXED32(6, 1, zzgpb.INT),
    BOOL(7, 1, zzgpb.BOOLEAN),
    STRING(8, 1, zzgpb.STRING),
    MESSAGE(9, 1, zzgpb.MESSAGE),
    BYTES(10, 1, zzgpb.BYTE_STRING),
    UINT32(11, 1, zzgpb.INT),
    ENUM(12, 1, zzgpb.ENUM),
    SFIXED32(13, 1, zzgpb.INT),
    SFIXED64(14, 1, zzgpb.LONG),
    SINT32(15, 1, zzgpb.INT),
    SINT64(16, 1, zzgpb.LONG),
    GROUP(17, 1, zzgpb.MESSAGE),
    DOUBLE_LIST(18, 2, zzgpb.DOUBLE),
    FLOAT_LIST(19, 2, zzgpb.FLOAT),
    INT64_LIST(20, 2, zzgpb.LONG),
    UINT64_LIST(21, 2, zzgpb.LONG),
    INT32_LIST(22, 2, zzgpb.INT),
    FIXED64_LIST(23, 2, zzgpb.LONG),
    FIXED32_LIST(24, 2, zzgpb.INT),
    BOOL_LIST(25, 2, zzgpb.BOOLEAN),
    STRING_LIST(26, 2, zzgpb.STRING),
    MESSAGE_LIST(27, 2, zzgpb.MESSAGE),
    BYTES_LIST(28, 2, zzgpb.BYTE_STRING),
    UINT32_LIST(29, 2, zzgpb.INT),
    ENUM_LIST(30, 2, zzgpb.ENUM),
    SFIXED32_LIST(31, 2, zzgpb.INT),
    SFIXED64_LIST(32, 2, zzgpb.LONG),
    SINT32_LIST(33, 2, zzgpb.INT),
    SINT64_LIST(34, 2, zzgpb.LONG),
    DOUBLE_LIST_PACKED(35, 3, zzgpb.DOUBLE),
    FLOAT_LIST_PACKED(36, 3, zzgpb.FLOAT),
    INT64_LIST_PACKED(37, 3, zzgpb.LONG),
    UINT64_LIST_PACKED(38, 3, zzgpb.LONG),
    INT32_LIST_PACKED(39, 3, zzgpb.INT),
    FIXED64_LIST_PACKED(40, 3, zzgpb.LONG),
    FIXED32_LIST_PACKED(41, 3, zzgpb.INT),
    BOOL_LIST_PACKED(42, 3, zzgpb.BOOLEAN),
    UINT32_LIST_PACKED(43, 3, zzgpb.INT),
    ENUM_LIST_PACKED(44, 3, zzgpb.ENUM),
    SFIXED32_LIST_PACKED(45, 3, zzgpb.INT),
    SFIXED64_LIST_PACKED(46, 3, zzgpb.LONG),
    SINT32_LIST_PACKED(47, 3, zzgpb.INT),
    SINT64_LIST_PACKED(48, 3, zzgpb.LONG),
    GROUP_LIST(49, 2, zzgpb.MESSAGE),
    MAP(50, 4, zzgpb.VOID);
    
    private static final zzgof[] zzZ;
    private final zzgpb zzab;
    private final int zzac;
    private final Class zzad;

    static {
        zzgof[] values = values();
        zzZ = new zzgof[values.length];
        for (zzgof zzgofVar : values) {
            zzZ[zzgofVar.zzac] = zzgofVar;
        }
    }

    zzgof(int r3, int r4, zzgpb zzgpbVar) {
        this.zzac = r3;
        this.zzab = zzgpbVar;
        zzgpb zzgpbVar2 = zzgpb.VOID;
        int r1 = r4 - 1;
        if (r1 == 1) {
            this.zzad = zzgpbVar.zza();
        } else if (r1 != 3) {
            this.zzad = null;
        } else {
            this.zzad = zzgpbVar.zza();
        }
        if (r4 == 1) {
            zzgpbVar.ordinal();
        }
    }

    public final int zza() {
        return this.zzac;
    }
}
