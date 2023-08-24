package com.google.android.gms.internal.ads;

import java.util.ArrayDeque;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzffu {
    private final zzfey zza;
    private final zzffs zzb;
    private final zzfeu zzc;
    private zzfga zze;
    private int zzf = 1;
    private final ArrayDeque zzd = new ArrayDeque();

    public zzffu(zzfey zzfeyVar, zzfeu zzfeuVar, zzffs zzffsVar) {
        this.zza = zzfeyVar;
        this.zzc = zzfeuVar;
        this.zzb = zzffsVar;
        zzfeuVar.zzb(new zzffp(this));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final synchronized void zzh() {
        if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzfp)).booleanValue() && !com.google.android.gms.ads.internal.zzt.zzp().zzh().zzh().zzh()) {
            this.zzd.clear();
            return;
        }
        if (zzi()) {
            while (!this.zzd.isEmpty()) {
                zzfft zzfftVar = (zzfft) this.zzd.pollFirst();
                if (zzfftVar == null || (zzfftVar.zza() != null && this.zza.zze(zzfftVar.zza()))) {
                    zzfga zzfgaVar = new zzfga(this.zza, this.zzb, zzfftVar);
                    this.zze = zzfgaVar;
                    zzfgaVar.zzd(new zzffq(this, zzfftVar));
                    return;
                }
            }
        }
    }

    private final synchronized boolean zzi() {
        return this.zze == null;
    }

    public final synchronized zzfyx zza(zzfft zzfftVar) {
        this.zzf = 2;
        if (zzi()) {
            return null;
        }
        return this.zze.zza(zzfftVar);
    }

    public final synchronized void zze(zzfft zzfftVar) {
        this.zzd.add(zzfftVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void zzf() {
        synchronized (this) {
            this.zzf = 1;
            zzh();
        }
    }
}
