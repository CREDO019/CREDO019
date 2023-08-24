package com.google.android.gms.internal.ads;

import java.io.IOException;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public abstract class zzasc implements zzasx, zzasy {
    private final int zza;
    private zzasz zzb;
    private int zzc;
    private int zzd;
    private zzayk zze;
    private long zzf;
    private boolean zzg = true;
    private boolean zzh;

    public zzasc(int r1) {
        this.zza = r1;
    }

    @Override // com.google.android.gms.internal.ads.zzasx
    public final boolean zzA() {
        return this.zzg;
    }

    @Override // com.google.android.gms.internal.ads.zzasx
    public final boolean zzB() {
        return this.zzh;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final boolean zzC() {
        return this.zzg ? this.zzh : this.zze.zze();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final int zza() {
        return this.zzc;
    }

    @Override // com.google.android.gms.internal.ads.zzasx
    public final int zzb() {
        return this.zzd;
    }

    @Override // com.google.android.gms.internal.ads.zzasx, com.google.android.gms.internal.ads.zzasy
    public final int zzc() {
        return this.zza;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final int zzd(zzast zzastVar, zzaun zzaunVar, boolean z) {
        int zzb = this.zze.zzb(zzastVar, zzaunVar, z);
        if (zzb == -4) {
            if (zzaunVar.zzf()) {
                this.zzg = true;
                return this.zzh ? -4 : -3;
            }
            zzaunVar.zzc += this.zzf;
        } else if (zzb == -5) {
            zzass zzassVar = zzastVar.zza;
            long j = zzassVar.zzw;
            if (j != Long.MAX_VALUE) {
                zzastVar.zza = new zzass(zzassVar.zza, zzassVar.zze, zzassVar.zzf, zzassVar.zzc, zzassVar.zzb, zzassVar.zzg, zzassVar.zzj, zzassVar.zzk, zzassVar.zzl, zzassVar.zzm, zzassVar.zzn, zzassVar.zzp, zzassVar.zzo, zzassVar.zzq, zzassVar.zzr, zzassVar.zzs, zzassVar.zzt, zzassVar.zzu, zzassVar.zzv, zzassVar.zzx, zzassVar.zzy, zzassVar.zzz, j + this.zzf, zzassVar.zzh, zzassVar.zzi, zzassVar.zzd);
                return -5;
            }
        }
        return zzb;
    }

    @Override // com.google.android.gms.internal.ads.zzasy
    public int zze() throws zzase {
        throw null;
    }

    @Override // com.google.android.gms.internal.ads.zzasx
    public final zzasy zzf() {
        return this;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final zzasz zzg() {
        return this.zzb;
    }

    @Override // com.google.android.gms.internal.ads.zzasx
    public final zzayk zzh() {
        return this.zze;
    }

    @Override // com.google.android.gms.internal.ads.zzasx
    public zzbac zzi() {
        return null;
    }

    @Override // com.google.android.gms.internal.ads.zzasx
    public final void zzj() {
        zzazy.zze(this.zzd == 1);
        this.zzd = 0;
        this.zze = null;
        this.zzh = false;
        zzn();
    }

    @Override // com.google.android.gms.internal.ads.zzasx
    public final void zzk(zzasz zzaszVar, zzass[] zzassVarArr, zzayk zzaykVar, long j, boolean z, long j2) throws zzase {
        zzazy.zze(this.zzd == 0);
        this.zzb = zzaszVar;
        this.zzd = 1;
        zzo(z);
        zzt(zzassVarArr, zzaykVar, j2);
        zzp(j, z);
    }

    @Override // com.google.android.gms.internal.ads.zzasg
    public void zzl(int r1, Object obj) throws zzase {
    }

    @Override // com.google.android.gms.internal.ads.zzasx
    public final void zzm() throws IOException {
        this.zze.zzc();
    }

    protected void zzn() {
        throw null;
    }

    protected void zzo(boolean z) throws zzase {
    }

    protected void zzp(long j, boolean z) throws zzase {
        throw null;
    }

    protected void zzq() throws zzase {
    }

    protected void zzr() throws zzase {
    }

    protected void zzs(zzass[] zzassVarArr, long j) throws zzase {
    }

    @Override // com.google.android.gms.internal.ads.zzasx
    public final void zzt(zzass[] zzassVarArr, zzayk zzaykVar, long j) throws zzase {
        zzazy.zze(!this.zzh);
        this.zze = zzaykVar;
        this.zzg = false;
        this.zzf = j;
        zzs(zzassVarArr, j);
    }

    @Override // com.google.android.gms.internal.ads.zzasx
    public final void zzu(long j) throws zzase {
        this.zzh = false;
        this.zzg = false;
        zzp(j, false);
    }

    @Override // com.google.android.gms.internal.ads.zzasx
    public final void zzv() {
        this.zzh = true;
    }

    @Override // com.google.android.gms.internal.ads.zzasx
    public final void zzw(int r1) {
        this.zzc = r1;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void zzx(long j) {
        this.zze.zzd(j - this.zzf);
    }

    @Override // com.google.android.gms.internal.ads.zzasx
    public final void zzy() throws zzase {
        zzazy.zze(this.zzd == 1);
        this.zzd = 2;
        zzq();
    }

    @Override // com.google.android.gms.internal.ads.zzasx
    public final void zzz() throws zzase {
        zzazy.zze(this.zzd == 2);
        this.zzd = 1;
        zzr();
    }
}
