package com.google.android.gms.internal.ads;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzaot implements Runnable {
    final /* synthetic */ zzaou zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzaot(zzaou zzaouVar) {
        this.zza = zzaouVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        Object obj;
        boolean z;
        zzfmf zzfmfVar;
        Object obj2;
        obj = this.zza.zzm;
        synchronized (obj) {
            z = this.zza.zzn;
            if (z) {
                return;
            }
            this.zza.zzn = true;
            try {
                zzaou.zzj(this.zza);
            } catch (Exception e) {
                zzfmfVar = this.zza.zzh;
                zzfmfVar.zzc(2023, -1L, e);
            }
            obj2 = this.zza.zzm;
            synchronized (obj2) {
                this.zza.zzn = false;
            }
        }
    }
}
