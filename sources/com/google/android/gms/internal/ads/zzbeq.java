package com.google.android.gms.internal.ads;

import android.os.RemoteException;

/* compiled from: com.google.android.gms:play-services-ads-lite@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzbeq {
    final /* synthetic */ zzber zza;
    private final byte[] zzb;
    private int zzc;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzbeq(zzber zzberVar, byte[] bArr, zzbep zzbepVar) {
        this.zza = zzberVar;
        this.zzb = bArr;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final synchronized void zzd() {
        try {
            zzber zzberVar = this.zza;
            if (zzberVar.zzb) {
                zzberVar.zza.zzj(this.zzb);
                this.zza.zza.zzi(0);
                this.zza.zza.zzg(this.zzc);
                this.zza.zza.zzh(null);
                this.zza.zza.zzf();
            }
        } catch (RemoteException e) {
            zzcgn.zzf("Clearcut log failed", e);
        }
    }

    public final zzbeq zza(int r1) {
        this.zzc = r1;
        return this;
    }

    public final synchronized void zzc() {
        if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zziA)).booleanValue()) {
            zzber.zza(this.zza).execute(new Runnable() { // from class: com.google.android.gms.internal.ads.zzbeo
                @Override // java.lang.Runnable
                public final void run() {
                    zzbeq.this.zzd();
                }
            });
        } else {
            zzd();
        }
    }
}
