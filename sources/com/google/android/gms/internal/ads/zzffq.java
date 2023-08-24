package com.google.android.gms.internal.ads;

import java.util.ArrayDeque;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzffq implements zzfyk {
    final /* synthetic */ zzfft zza;
    final /* synthetic */ zzffu zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzffq(zzffu zzffuVar, zzfft zzfftVar) {
        this.zzb = zzffuVar;
        this.zza = zzfftVar;
    }

    @Override // com.google.android.gms.internal.ads.zzfyk
    public final void zza(Throwable th) {
        synchronized (this.zzb) {
            this.zzb.zze = null;
        }
    }

    @Override // com.google.android.gms.internal.ads.zzfyk
    public final /* bridge */ /* synthetic */ void zzb(Object obj) {
        ArrayDeque arrayDeque;
        int r1;
        Void r4 = (Void) obj;
        synchronized (this.zzb) {
            this.zzb.zze = null;
            arrayDeque = this.zzb.zzd;
            arrayDeque.addFirst(this.zza);
            zzffu zzffuVar = this.zzb;
            r1 = zzffuVar.zzf;
            if (r1 == 1) {
                zzffuVar.zzh();
            }
        }
    }
}
