package com.google.android.gms.internal.ads;

import java.util.Map;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzdwv implements zzfhq {
    private final Map zza;
    private final zzbel zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzdwv(zzbel zzbelVar, Map map) {
        this.zza = map;
        this.zzb = zzbelVar;
    }

    @Override // com.google.android.gms.internal.ads.zzfhq
    public final void zzbF(zzfhj zzfhjVar, String str) {
    }

    @Override // com.google.android.gms.internal.ads.zzfhq
    public final void zzbG(zzfhj zzfhjVar, String str, Throwable th) {
        if (this.zza.containsKey(zzfhjVar)) {
            this.zzb.zzc(((zzdwu) this.zza.get(zzfhjVar)).zzc);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzfhq
    public final void zzc(zzfhj zzfhjVar, String str) {
        if (this.zza.containsKey(zzfhjVar)) {
            this.zzb.zzc(((zzdwu) this.zza.get(zzfhjVar)).zza);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzfhq
    public final void zzd(zzfhj zzfhjVar, String str) {
        if (this.zza.containsKey(zzfhjVar)) {
            this.zzb.zzc(((zzdwu) this.zza.get(zzfhjVar)).zzb);
        }
    }
}
