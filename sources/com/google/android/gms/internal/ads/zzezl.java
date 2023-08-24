package com.google.android.gms.internal.ads;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzezl implements zzffs {
    private final zzfah zza;

    public zzezl(zzfah zzfahVar) {
        this.zza = zzfahVar;
    }

    @Override // com.google.android.gms.internal.ads.zzffs
    public final zzfyx zza(zzfft zzfftVar) {
        zzezm zzezmVar = (zzezm) zzfftVar;
        return ((zzezi) this.zza).zzb(zzezmVar.zzb, zzezmVar.zza, null);
    }

    @Override // com.google.android.gms.internal.ads.zzffs
    public final void zzb(zzffh zzffhVar) {
        zzffhVar.zza = ((zzezi) this.zza).zza();
    }
}
