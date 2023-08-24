package com.google.android.gms.internal.vision;

import java.lang.reflect.Type;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.0 */
/* loaded from: classes3.dex */
public enum zzgn {
    DOUBLE(0, zzgp.SCALAR, zzhe.DOUBLE),
    FLOAT(1, zzgp.SCALAR, zzhe.FLOAT),
    INT64(2, zzgp.SCALAR, zzhe.LONG),
    UINT64(3, zzgp.SCALAR, zzhe.LONG),
    INT32(4, zzgp.SCALAR, zzhe.INT),
    FIXED64(5, zzgp.SCALAR, zzhe.LONG),
    FIXED32(6, zzgp.SCALAR, zzhe.INT),
    BOOL(7, zzgp.SCALAR, zzhe.BOOLEAN),
    STRING(8, zzgp.SCALAR, zzhe.STRING),
    MESSAGE(9, zzgp.SCALAR, zzhe.MESSAGE),
    BYTES(10, zzgp.SCALAR, zzhe.BYTE_STRING),
    UINT32(11, zzgp.SCALAR, zzhe.INT),
    ENUM(12, zzgp.SCALAR, zzhe.ENUM),
    SFIXED32(13, zzgp.SCALAR, zzhe.INT),
    SFIXED64(14, zzgp.SCALAR, zzhe.LONG),
    SINT32(15, zzgp.SCALAR, zzhe.INT),
    SINT64(16, zzgp.SCALAR, zzhe.LONG),
    GROUP(17, zzgp.SCALAR, zzhe.MESSAGE),
    DOUBLE_LIST(18, zzgp.VECTOR, zzhe.DOUBLE),
    FLOAT_LIST(19, zzgp.VECTOR, zzhe.FLOAT),
    INT64_LIST(20, zzgp.VECTOR, zzhe.LONG),
    UINT64_LIST(21, zzgp.VECTOR, zzhe.LONG),
    INT32_LIST(22, zzgp.VECTOR, zzhe.INT),
    FIXED64_LIST(23, zzgp.VECTOR, zzhe.LONG),
    FIXED32_LIST(24, zzgp.VECTOR, zzhe.INT),
    BOOL_LIST(25, zzgp.VECTOR, zzhe.BOOLEAN),
    STRING_LIST(26, zzgp.VECTOR, zzhe.STRING),
    MESSAGE_LIST(27, zzgp.VECTOR, zzhe.MESSAGE),
    BYTES_LIST(28, zzgp.VECTOR, zzhe.BYTE_STRING),
    UINT32_LIST(29, zzgp.VECTOR, zzhe.INT),
    ENUM_LIST(30, zzgp.VECTOR, zzhe.ENUM),
    SFIXED32_LIST(31, zzgp.VECTOR, zzhe.INT),
    SFIXED64_LIST(32, zzgp.VECTOR, zzhe.LONG),
    SINT32_LIST(33, zzgp.VECTOR, zzhe.INT),
    SINT64_LIST(34, zzgp.VECTOR, zzhe.LONG),
    DOUBLE_LIST_PACKED(35, zzgp.PACKED_VECTOR, zzhe.DOUBLE),
    FLOAT_LIST_PACKED(36, zzgp.PACKED_VECTOR, zzhe.FLOAT),
    INT64_LIST_PACKED(37, zzgp.PACKED_VECTOR, zzhe.LONG),
    UINT64_LIST_PACKED(38, zzgp.PACKED_VECTOR, zzhe.LONG),
    INT32_LIST_PACKED(39, zzgp.PACKED_VECTOR, zzhe.INT),
    FIXED64_LIST_PACKED(40, zzgp.PACKED_VECTOR, zzhe.LONG),
    FIXED32_LIST_PACKED(41, zzgp.PACKED_VECTOR, zzhe.INT),
    BOOL_LIST_PACKED(42, zzgp.PACKED_VECTOR, zzhe.BOOLEAN),
    UINT32_LIST_PACKED(43, zzgp.PACKED_VECTOR, zzhe.INT),
    ENUM_LIST_PACKED(44, zzgp.PACKED_VECTOR, zzhe.ENUM),
    SFIXED32_LIST_PACKED(45, zzgp.PACKED_VECTOR, zzhe.INT),
    SFIXED64_LIST_PACKED(46, zzgp.PACKED_VECTOR, zzhe.LONG),
    SINT32_LIST_PACKED(47, zzgp.PACKED_VECTOR, zzhe.INT),
    SINT64_LIST_PACKED(48, zzgp.PACKED_VECTOR, zzhe.LONG),
    GROUP_LIST(49, zzgp.VECTOR, zzhe.MESSAGE),
    MAP(50, zzgp.MAP, zzhe.VOID);
    
    private static final zzgn[] zzvn;
    private static final Type[] zzvo = new Type[0];

    /* renamed from: id */
    private final int f265id;
    private final zzhe zzvj;
    private final zzgp zzvk;
    private final Class<?> zzvl;
    private final boolean zzvm;

    zzgn(int r4, zzgp zzgpVar, zzhe zzheVar) {
        int r5;
        this.f265id = r4;
        this.zzvk = zzgpVar;
        this.zzvj = zzheVar;
        int r2 = zzgm.zzti[zzgpVar.ordinal()];
        boolean z = true;
        if (r2 == 1) {
            this.zzvl = zzheVar.zzgv();
        } else if (r2 == 2) {
            this.zzvl = zzheVar.zzgv();
        } else {
            this.zzvl = null;
        }
        this.zzvm = (zzgpVar != zzgp.SCALAR || (r5 = zzgm.zztj[zzheVar.ordinal()]) == 1 || r5 == 2 || r5 == 3) ? false : false;
    }

    /* renamed from: id */
    public final int m1085id() {
        return this.f265id;
    }

    static {
        zzgn[] values = values();
        zzvn = new zzgn[values.length];
        for (zzgn zzgnVar : values) {
            zzvn[zzgnVar.f265id] = zzgnVar;
        }
    }
}
