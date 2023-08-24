package com.google.android.gms.internal.ads;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzbtf implements Runnable {
    final /* synthetic */ zzbtq zza;
    final /* synthetic */ zzbsm zzb;
    final /* synthetic */ zzbtr zzc;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzbtf(zzbtr zzbtrVar, zzbtq zzbtqVar, zzbsm zzbsmVar) {
        this.zzc = zzbtrVar;
        this.zza = zzbtqVar;
        this.zzb = zzbsmVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        Object obj;
        obj = this.zzc.zza;
        synchronized (obj) {
            if (this.zza.zze() != -1 && this.zza.zze() != 1) {
                this.zza.zzg();
                zzfyy zzfyyVar = zzcha.zze;
                final zzbsm zzbsmVar = this.zzb;
                zzfyyVar.execute(new Runnable() { // from class: com.google.android.gms.internal.ads.zzbte
                    @Override // java.lang.Runnable
                    public final void run() {
                        zzbsm.this.zzc();
                    }
                });
                com.google.android.gms.ads.internal.util.zze.zza("Could not receive loaded message in a timely manner. Rejecting.");
            }
        }
    }
}
