package com.google.android.gms.internal.ads;

import android.util.SparseArray;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzaxp implements Runnable {
    final /* synthetic */ zzaxs zza;
    final /* synthetic */ zzaxu zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzaxp(zzaxu zzaxuVar, zzaxs zzaxsVar) {
        this.zzb = zzaxuVar;
        this.zza = zzaxsVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        SparseArray sparseArray;
        SparseArray sparseArray2;
        this.zza.zza();
        sparseArray = this.zzb.zzn;
        int size = sparseArray.size();
        for (int r1 = 0; r1 < size; r1++) {
            sparseArray2 = this.zzb.zzn;
            ((zzayj) sparseArray2.valueAt(r1)).zzi();
        }
    }
}
