package com.google.android.gms.internal.ads;

import android.view.Surface;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
@Deprecated
/* loaded from: classes2.dex */
public final class zzkd extends zzm implements zzhj {
    private final zzir zzb;
    private final zzdg zzc;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzkd(zzhi zzhiVar) {
        zzdg zzdgVar = new zzdg(zzde.zza);
        this.zzc = zzdgVar;
        try {
            this.zzb = new zzir(zzhiVar, this);
            zzdgVar.zze();
        } catch (Throwable th) {
            this.zzc.zze();
            throw th;
        }
    }

    public final void zzA(zzkp zzkpVar) {
        this.zzc.zzb();
        this.zzb.zzY(zzkpVar);
    }

    public final void zzB(zzsi zzsiVar) {
        this.zzc.zzb();
        this.zzb.zzZ(zzsiVar);
    }

    public final void zzC(boolean z) {
        this.zzc.zzb();
        this.zzb.zzaa(z);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zzD(boolean z) {
        this.zzc.zzb();
        this.zzb.zzab(false);
    }

    public final void zzE(Surface surface) {
        this.zzc.zzb();
        this.zzb.zzac(surface);
    }

    public final void zzF(float f) {
        this.zzc.zzb();
        this.zzb.zzad(f);
    }

    public final void zzG() {
        this.zzc.zzb();
        this.zzb.zzae();
    }

    @Override // com.google.android.gms.internal.ads.zzcg
    public final int zzd() {
        this.zzc.zzb();
        return this.zzb.zzd();
    }

    @Override // com.google.android.gms.internal.ads.zzcg
    public final int zze() {
        this.zzc.zzb();
        return this.zzb.zze();
    }

    @Override // com.google.android.gms.internal.ads.zzcg
    public final int zzf() {
        this.zzc.zzb();
        return this.zzb.zzf();
    }

    @Override // com.google.android.gms.internal.ads.zzcg
    public final int zzg() {
        this.zzc.zzb();
        return this.zzb.zzg();
    }

    @Override // com.google.android.gms.internal.ads.zzcg
    public final int zzh() {
        this.zzc.zzb();
        return this.zzb.zzh();
    }

    @Override // com.google.android.gms.internal.ads.zzcg
    public final int zzi() {
        this.zzc.zzb();
        return this.zzb.zzi();
    }

    @Override // com.google.android.gms.internal.ads.zzcg
    public final int zzj() {
        this.zzc.zzb();
        this.zzb.zzj();
        return 0;
    }

    @Override // com.google.android.gms.internal.ads.zzcg
    public final long zzk() {
        this.zzc.zzb();
        return this.zzb.zzk();
    }

    @Override // com.google.android.gms.internal.ads.zzcg
    public final long zzl() {
        this.zzc.zzb();
        return this.zzb.zzl();
    }

    @Override // com.google.android.gms.internal.ads.zzcg
    public final long zzm() {
        this.zzc.zzb();
        return this.zzb.zzm();
    }

    @Override // com.google.android.gms.internal.ads.zzcg
    public final zzcn zzn() {
        this.zzc.zzb();
        return this.zzb.zzn();
    }

    @Override // com.google.android.gms.internal.ads.zzcg
    public final zzcy zzo() {
        this.zzc.zzb();
        return this.zzb.zzo();
    }

    @Override // com.google.android.gms.internal.ads.zzcg
    public final void zzp(int r2, long j) {
        this.zzc.zzb();
        this.zzb.zzp(r2, j);
    }

    @Override // com.google.android.gms.internal.ads.zzcg
    public final boolean zzq() {
        this.zzc.zzb();
        return this.zzb.zzq();
    }

    @Override // com.google.android.gms.internal.ads.zzcg
    public final boolean zzr() {
        this.zzc.zzb();
        this.zzb.zzr();
        return false;
    }

    @Override // com.google.android.gms.internal.ads.zzcg
    public final boolean zzs() {
        this.zzc.zzb();
        return this.zzb.zzs();
    }

    public final int zzt() {
        this.zzc.zzb();
        this.zzb.zzu();
        return 2;
    }

    public final long zzu() {
        this.zzc.zzb();
        return this.zzb.zzv();
    }

    public final long zzv() {
        this.zzc.zzb();
        return this.zzb.zzw();
    }

    public final zzgy zzw() {
        this.zzc.zzb();
        return this.zzb.zzA();
    }

    public final void zzx(zzkp zzkpVar) {
        this.zzc.zzb();
        this.zzb.zzR(zzkpVar);
    }

    public final void zzy() {
        this.zzc.zzb();
        this.zzb.zzW();
    }

    public final void zzz() {
        this.zzc.zzb();
        this.zzb.zzX();
    }
}
