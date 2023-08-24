package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public enum zzgrx {
    DOUBLE(zzgry.DOUBLE, 1),
    FLOAT(zzgry.FLOAT, 5),
    INT64(zzgry.LONG, 0),
    UINT64(zzgry.LONG, 0),
    INT32(zzgry.INT, 0),
    FIXED64(zzgry.LONG, 1),
    FIXED32(zzgry.INT, 5),
    BOOL(zzgry.BOOLEAN, 0),
    STRING(zzgry.STRING, 2),
    GROUP(zzgry.MESSAGE, 3),
    MESSAGE(zzgry.MESSAGE, 2),
    BYTES(zzgry.BYTE_STRING, 2),
    UINT32(zzgry.INT, 0),
    ENUM(zzgry.ENUM, 0),
    SFIXED32(zzgry.INT, 5),
    SFIXED64(zzgry.LONG, 1),
    SINT32(zzgry.INT, 0),
    SINT64(zzgry.LONG, 0);
    
    private final zzgry zzt;

    zzgrx(zzgry zzgryVar, int r4) {
        this.zzt = zzgryVar;
    }

    public final zzgry zza() {
        return this.zzt;
    }
}
