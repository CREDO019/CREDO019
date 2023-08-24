package com.google.android.gms.internal.ads;

import androidx.collection.ArrayMap;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzdqk implements zzdds {
    private final zzdoo zza;
    private final zzdot zzb;

    public zzdqk(zzdoo zzdooVar, zzdot zzdotVar) {
        this.zza = zzdooVar;
        this.zzb = zzdotVar;
    }

    @Override // com.google.android.gms.internal.ads.zzdds
    public final void zzl() {
        zzdoo zzdooVar = this.zza;
        if (zzdooVar.zzu() == null) {
            return;
        }
        zzcmn zzq = zzdooVar.zzq();
        zzcmn zzr = zzdooVar.zzr();
        if (zzq == null) {
            zzq = zzr == null ? null : zzr;
        }
        if (!this.zzb.zzd() || zzq == null) {
            return;
        }
        zzq.zzd("onSdkImpression", new ArrayMap());
    }
}
