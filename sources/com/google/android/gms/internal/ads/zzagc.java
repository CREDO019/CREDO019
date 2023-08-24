package com.google.android.gms.internal.ads;

import android.net.Uri;
import java.io.IOException;
import java.util.Map;
import org.checkerframework.checker.nullness.qual.EnsuresNonNullIf;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzagc implements zzzf {
    public static final zzzm zza = new zzzm() { // from class: com.google.android.gms.internal.ads.zzagb
        @Override // com.google.android.gms.internal.ads.zzzm
        public final zzzf[] zza() {
            zzzm zzzmVar = zzagc.zza;
            return new zzzf[]{new zzagc()};
        }

        @Override // com.google.android.gms.internal.ads.zzzm
        public final /* synthetic */ zzzf[] zzb(Uri uri, Map map) {
            return zzzl.zza(this, uri, map);
        }
    };
    private zzzi zzb;
    private zzagk zzc;
    private boolean zzd;

    @EnsuresNonNullIf(expression = {"streamReader"}, result = true)
    private final boolean zze(zzzg zzzgVar) throws IOException {
        zzage zzageVar = new zzage();
        if (zzageVar.zzb(zzzgVar, true) && (zzageVar.zza & 2) == 2) {
            int min = Math.min(zzageVar.zze, 8);
            zzed zzedVar = new zzed(min);
            ((zzyv) zzzgVar).zzm(zzedVar.zzH(), 0, min, false);
            zzedVar.zzF(0);
            if (zzedVar.zza() < 5 || zzedVar.zzk() != 127 || zzedVar.zzs() != 1179402563) {
                zzedVar.zzF(0);
                try {
                    if (zzaas.zzd(1, zzedVar, true)) {
                        this.zzc = new zzagm();
                    }
                } catch (zzbu unused) {
                }
                zzedVar.zzF(0);
                if (zzagg.zzd(zzedVar)) {
                    this.zzc = new zzagg();
                }
            } else {
                this.zzc = new zzaga();
            }
            return true;
        }
        return false;
    }

    @Override // com.google.android.gms.internal.ads.zzzf
    public final int zza(zzzg zzzgVar, zzaaf zzaafVar) throws IOException {
        zzdd.zzb(this.zzb);
        if (this.zzc == null) {
            if (!zze(zzzgVar)) {
                throw zzbu.zza("Failed to determine bitstream type", null);
            }
            zzzgVar.zzj();
        }
        if (!this.zzd) {
            zzaam zzv = this.zzb.zzv(0, 1);
            this.zzb.zzB();
            this.zzc.zzh(this.zzb, zzv);
            this.zzd = true;
        }
        return this.zzc.zze(zzzgVar, zzaafVar);
    }

    @Override // com.google.android.gms.internal.ads.zzzf
    public final void zzb(zzzi zzziVar) {
        this.zzb = zzziVar;
    }

    @Override // com.google.android.gms.internal.ads.zzzf
    public final void zzc(long j, long j2) {
        zzagk zzagkVar = this.zzc;
        if (zzagkVar != null) {
            zzagkVar.zzj(j, j2);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzzf
    public final boolean zzd(zzzg zzzgVar) throws IOException {
        try {
            return zze(zzzgVar);
        } catch (zzbu unused) {
            return false;
        }
    }
}
