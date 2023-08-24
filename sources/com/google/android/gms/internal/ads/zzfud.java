package com.google.android.gms.internal.ads;

import java.util.Map;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzfud extends zzftr {
    final /* synthetic */ zzfuf zza;
    private final Object zzb;
    private int zzc;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzfud(zzfuf zzfufVar, int r2) {
        this.zza = zzfufVar;
        this.zzb = zzfuf.zzg(zzfufVar, r2);
        this.zzc = r2;
    }

    private final void zza() {
        int zzv;
        int r0 = this.zzc;
        if (r0 == -1 || r0 >= this.zza.size() || !zzfsa.zza(this.zzb, zzfuf.zzg(this.zza, this.zzc))) {
            zzv = this.zza.zzv(this.zzb);
            this.zzc = zzv;
        }
    }

    @Override // com.google.android.gms.internal.ads.zzftr, java.util.Map.Entry
    public final Object getKey() {
        return this.zzb;
    }

    @Override // com.google.android.gms.internal.ads.zzftr, java.util.Map.Entry
    public final Object getValue() {
        Map zzl = this.zza.zzl();
        if (zzl != null) {
            return zzl.get(this.zzb);
        }
        zza();
        int r0 = this.zzc;
        if (r0 == -1) {
            return null;
        }
        return zzfuf.zzj(this.zza, r0);
    }

    @Override // com.google.android.gms.internal.ads.zzftr, java.util.Map.Entry
    public final Object setValue(Object obj) {
        Map zzl = this.zza.zzl();
        if (zzl != null) {
            return zzl.put(this.zzb, obj);
        }
        zza();
        int r0 = this.zzc;
        if (r0 == -1) {
            this.zza.put(this.zzb, obj);
            return null;
        }
        Object zzj = zzfuf.zzj(this.zza, r0);
        zzfuf.zzm(this.zza, this.zzc, obj);
        return zzj;
    }
}
