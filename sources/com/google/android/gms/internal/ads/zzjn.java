package com.google.android.gms.internal.ads;

import android.util.Pair;
import java.io.IOException;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzjn implements zzsq, zzpj {
    final /* synthetic */ zzjr zza;
    private final zzjp zzb;
    private zzsp zzc;
    private zzpi zzd;

    public zzjn(zzjr zzjrVar, zzjp zzjpVar) {
        zzsp zzspVar;
        zzpi zzpiVar;
        this.zza = zzjrVar;
        zzspVar = zzjrVar.zzf;
        this.zzc = zzspVar;
        zzpiVar = zzjrVar.zzg;
        this.zzd = zzpiVar;
        this.zzb = zzjpVar;
    }

    private final boolean zzf(int r10, zzsg zzsgVar) {
        zzsp zzspVar;
        zzpi zzpiVar;
        zzsg zzsgVar2 = null;
        if (zzsgVar != null) {
            zzjp zzjpVar = this.zzb;
            int r3 = 0;
            while (true) {
                if (r3 >= zzjpVar.zzc.size()) {
                    break;
                } else if (((zzsg) zzjpVar.zzc.get(r3)).zzd == zzsgVar.zzd) {
                    zzsgVar2 = zzsgVar.zzc(Pair.create(zzjpVar.zzb, zzsgVar.zza));
                    break;
                } else {
                    r3++;
                }
            }
            if (zzsgVar2 == null) {
                return false;
            }
        }
        int r102 = r10 + this.zzb.zzd;
        zzsp zzspVar2 = this.zzc;
        if (zzspVar2.zza != r102 || !zzel.zzT(zzspVar2.zzb, zzsgVar2)) {
            zzspVar = this.zza.zzf;
            this.zzc = zzspVar.zza(r102, zzsgVar2, 0L);
        }
        zzpi zzpiVar2 = this.zzd;
        if (zzpiVar2.zza == r102 && zzel.zzT(zzpiVar2.zzb, zzsgVar2)) {
            return true;
        }
        zzpiVar = this.zza.zzg;
        this.zzd = zzpiVar.zza(r102, zzsgVar2);
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
