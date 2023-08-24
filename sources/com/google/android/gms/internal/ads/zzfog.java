package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import android.util.Log;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzfog {
    final /* synthetic */ zzfoh zza;
    private final byte[] zzb;
    private int zzc;
    private int zzd;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzfog(zzfoh zzfohVar, byte[] bArr, zzfof zzfofVar) {
        this.zza = zzfohVar;
        this.zzb = bArr;
    }

    public final zzfog zza(int r1) {
        this.zzd = r1;
        return this;
    }

    public final zzfog zzb(int r1) {
        this.zzc = r1;
        return this;
    }

    public final synchronized void zzc() {
        try {
            zzfoh zzfohVar = this.zza;
            if (zzfohVar.zzb) {
                zzfohVar.zza.zzj(this.zzb);
                this.zza.zza.zzi(this.zzc);
                this.zza.zza.zzg(this.zzd);
                this.zza.zza.zzh(null);
                this.zza.zza.zzf();
            }
        } catch (RemoteException e) {
            Log.d("GASS", "Clearcut log failed", e);
        }
    }
}
