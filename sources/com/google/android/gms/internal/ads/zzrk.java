package com.google.android.gms.internal.ads;

import java.io.IOException;
import java.util.Objects;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzrk implements zztw {
    public final zztw zza;
    final /* synthetic */ zzrl zzb;
    private boolean zzc;

    public zzrk(zzrl zzrlVar, zztw zztwVar) {
        this.zzb = zzrlVar;
        this.zza = zztwVar;
    }

    @Override // com.google.android.gms.internal.ads.zztw
    public final int zza(zzje zzjeVar, zzgg zzggVar, int r13) {
        if (this.zzb.zzq()) {
            return -3;
        }
        if (this.zzc) {
            zzggVar.zzc(4);
            return -4;
        }
        int zza = this.zza.zza(zzjeVar, zzggVar, r13);
        if (zza == -5) {
            zzaf zzafVar = zzjeVar.zza;
            Objects.requireNonNull(zzafVar);
            int r132 = zzafVar.zzC;
            if (r132 == 0) {
                if (zzafVar.zzD != 0) {
                    r132 = 0;
                }
                return -5;
            }
            int r1 = this.zzb.zzb == Long.MIN_VALUE ? zzafVar.zzD : 0;
            zzad zzb = zzafVar.zzb();
            zzb.zzC(r132);
            zzb.zzD(r1);
            zzjeVar.zza = zzb.zzY();
            return -5;
        }
        zzrl zzrlVar = this.zzb;
        long j = zzrlVar.zzb;
        if (j == Long.MIN_VALUE || ((zza != -4 || zzggVar.zzd < j) && !(zza == -3 && zzrlVar.zzb() == Long.MIN_VALUE && !zzggVar.zzc))) {
            return zza;
        }
        zzggVar.zzb();
        zzggVar.zzc(4);
        this.zzc = true;
        return -4;
    }

    @Override // com.google.android.gms.internal.ads.zztw
    public final int zzb(long j) {
        if (this.zzb.zzq()) {
            return -3;
        }
        return this.zza.zzb(j);
    }

    public final void zzc() {
        this.zzc = false;
    }

    @Override // com.google.android.gms.internal.ads.zztw
    public final void zzd() throws IOException {
        this.zza.zzd();
    }

    @Override // com.google.android.gms.internal.ads.zztw
    public final boolean zze() {
        return !this.zzb.zzq() && this.zza.zze();
    }
}
