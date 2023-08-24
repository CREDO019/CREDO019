package com.google.android.gms.internal.ads;

import java.util.Objects;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzgw implements zzjg {
    private final zzke zza;
    private final zzgv zzb;
    private zzjy zzc;
    private zzjg zzd;
    private boolean zze = true;
    private boolean zzf;

    public zzgw(zzgv zzgvVar, zzde zzdeVar) {
        this.zzb = zzgvVar;
        this.zza = new zzke(zzdeVar);
    }

    @Override // com.google.android.gms.internal.ads.zzjg
    public final long zza() {
        throw null;
    }

    public final long zzb(boolean z) {
        zzjy zzjyVar = this.zzc;
        if (zzjyVar == null || zzjyVar.zzM() || (!this.zzc.zzN() && (z || this.zzc.zzG()))) {
            this.zze = true;
            if (this.zzf) {
                this.zza.zzd();
            }
        } else {
            zzjg zzjgVar = this.zzd;
            Objects.requireNonNull(zzjgVar);
            long zza = zzjgVar.zza();
            if (this.zze) {
                if (zza < this.zza.zza()) {
                    this.zza.zze();
                } else {
                    this.zze = false;
                    if (this.zzf) {
                        this.zza.zzd();
                    }
                }
            }
            this.zza.zzb(zza);
            zzby zzc = zzjgVar.zzc();
            if (!zzc.equals(this.zza.zzc())) {
                this.zza.zzg(zzc);
                this.zzb.zza(zzc);
            }
        }
        if (this.zze) {
            return this.zza.zza();
        }
        zzjg zzjgVar2 = this.zzd;
        Objects.requireNonNull(zzjgVar2);
        return zzjgVar2.zza();
    }

    @Override // com.google.android.gms.internal.ads.zzjg
    public final zzby zzc() {
        zzjg zzjgVar = this.zzd;
        return zzjgVar != null ? zzjgVar.zzc() : this.zza.zzc();
    }

    public final void zzd(zzjy zzjyVar) {
        if (zzjyVar == this.zzc) {
            this.zzd = null;
            this.zzc = null;
            this.zze = true;
        }
    }

    public final void zze(zzjy zzjyVar) throws zzgy {
        zzjg zzjgVar;
        zzjg zzi = zzjyVar.zzi();
        if (zzi == null || zzi == (zzjgVar = this.zzd)) {
            return;
        }
        if (zzjgVar == null) {
            this.zzd = zzi;
            this.zzc = zzjyVar;
            zzi.zzg(this.zza.zzc());
            return;
        }
        throw zzgy.zzd(new IllegalStateException("Multiple renderer media clocks enabled."), 1000);
    }

    public final void zzf(long j) {
        this.zza.zzb(j);
    }

    @Override // com.google.android.gms.internal.ads.zzjg
    public final void zzg(zzby zzbyVar) {
        zzjg zzjgVar = this.zzd;
        if (zzjgVar != null) {
            zzjgVar.zzg(zzbyVar);
            zzbyVar = this.zzd.zzc();
        }
        this.zza.zzg(zzbyVar);
    }

    public final void zzh() {
        this.zzf = true;
        this.zza.zzd();
    }

    public final void zzi() {
        this.zzf = false;
        this.zza.zze();
    }
}
