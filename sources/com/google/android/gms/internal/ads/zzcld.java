package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzcld extends zzckz {
    public zzcld(zzciw zzciwVar) {
        super(zzciwVar);
    }

    @Override // com.google.android.gms.internal.ads.zzckz
    public final void zzb() {
    }

    @Override // com.google.android.gms.internal.ads.zzckz
    public final boolean zzq(String str) {
        String zze = zzcgg.zze(str);
        zzciw zzciwVar = (zzciw) this.zzc.get();
        if (zzciwVar != null && zze != null) {
            zzciwVar.zzv(zze, this);
        }
        com.google.android.gms.ads.internal.util.zze.zzj("VideoStreamNoopCache is doing nothing.");
        zzc(str, zze, "noop", "Noop cache is a noop.");
        return false;
    }
}
