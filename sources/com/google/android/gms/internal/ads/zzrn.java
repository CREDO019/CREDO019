package com.google.android.gms.internal.ads;

import java.io.IOException;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzrn implements zzsq, zzpj {
    final /* synthetic */ zzrp zza;
    private final Object zzb;
    private zzsp zzc;
    private zzpi zzd;

    public zzrn(zzrp zzrpVar, Object obj) {
        this.zza = zzrpVar;
        this.zzc = zzrpVar.zze(null);
        this.zzd = zzrpVar.zzc(null);
        this.zzb = obj;
    }

    private final boolean zzf(int r4, zzsg zzsgVar) {
        zzsg zzsgVar2;
        if (zzsgVar != null) {
            zzsgVar2 = this.zza.zzv(this.zzb, zzsgVar);
            if (zzsgVar2 == null) {
                return false;
            }
        } else {
            zzsgVar2 = null;
        }
        zzsp zzspVar = this.zzc;
        if (zzspVar.zza != r4 || !zzel.zzT(zzspVar.zzb, zzsgVar2)) {
            this.zzc = this.zza.zzf(r4, zzsgVar2, 0L);
        }
        zzpi zzpiVar = this.zzd;
        if (zzpiVar.zza == r4 && zzel.zzT(zzpiVar.zzb, zzsgVar2)) {
            return true;
        }
        this.zzd = this.zza.zzd(r4, zzsgVar2);
        return true;
    }

    @Override // com.google.android.gms.internal.ads.zzsq
    public final void zzaf(int r1, zzsg zzsgVar, zzsc zzscVar) {
        if (zzf(r1, zzsgVar)) {
            this.zzc.zzc(zzscVar);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzsq
    public final void zzag(int r1, zzsg zzsgVar, zzrx zzrxVar, zzsc zzscVar) {
        if (zzf(r1, zzsgVar)) {
            this.zzc.zze(zzrxVar, zzscVar);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzsq
    public final void zzah(int r1, zzsg zzsgVar, zzrx zzrxVar, zzsc zzscVar) {
        if (zzf(r1, zzsgVar)) {
            this.zzc.zzg(zzrxVar, zzscVar);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzsq
    public final void zzai(int r1, zzsg zzsgVar, zzrx zzrxVar, zzsc zzscVar, IOException iOException, boolean z) {
        if (zzf(r1, zzsgVar)) {
            this.zzc.zzi(zzrxVar, zzscVar, iOException, z);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzsq
    public final void zzaj(int r1, zzsg zzsgVar, zzrx zzrxVar, zzsc zzscVar) {
        if (zzf(r1, zzsgVar)) {
            this.zzc.zzk(zzrxVar, zzscVar);
        }
    }
}
