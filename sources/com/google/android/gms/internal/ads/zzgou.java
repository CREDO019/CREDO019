package com.google.android.gms.internal.ads;

import java.util.AbstractList;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzgou extends AbstractList {
    private final List zza;
    private final zzgot zzb;

    public zzgou(List list, zzgot zzgotVar) {
        this.zza = list;
        this.zzb = zzgotVar;
    }

    @Override // java.util.AbstractList, java.util.List
    public final Object get(int r2) {
        zzbfj zzb = zzbfj.zzb(((Integer) this.zza.get(r2)).intValue());
        return zzb == null ? zzbfj.AD_FORMAT_TYPE_UNSPECIFIED : zzb;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.zza.size();
    }
}
