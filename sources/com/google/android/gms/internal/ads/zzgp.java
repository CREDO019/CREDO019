package com.google.android.gms.internal.ads;

import java.io.IOException;
import java.util.Objects;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public abstract class zzgp implements zzjy, zzjz {
    private final int zza;
    private zzka zzc;
    private int zzd;
    private zzmz zze;
    private int zzf;
    private zztw zzg;
    private zzaf[] zzh;
    private long zzi;
    private boolean zzk;
    private boolean zzl;
    private final zzje zzb = new zzje();
    private long zzj = Long.MIN_VALUE;

    public zzgp(int r3) {
        this.zza = r3;
    }

    private final void zzP(long j, boolean z) throws zzgy {
        this.zzk = false;
        this.zzj = j;
        zzu(j, z);
    }

    @Override // com.google.android.gms.internal.ads.zzjy
    public final void zzA() {
        zzdd.zzf(this.zzf == 0);
        zzje zzjeVar = this.zzb;
        zzjeVar.zzb = null;
        zzjeVar.zza = null;
        zzv();
    }

    @Override // com.google.android.gms.internal.ads.zzjy
    public final void zzB(long j) throws zzgy {
        zzP(j, false);
    }

    @Override // com.google.android.gms.internal.ads.zzjy
    public final void zzC() {
        this.zzk = true;
    }

    @Override // com.google.android.gms.internal.ads.zzjy
    public /* synthetic */ void zzD(float f, float f2) {
    }

    @Override // com.google.android.gms.internal.ads.zzjy
    public final void zzE() throws zzgy {
        zzdd.zzf(this.zzf == 1);
        this.zzf = 2;
        zzw();
    }

    @Override // com.google.android.gms.internal.ads.zzjy
    public final void zzF() {
        zzdd.zzf(this.zzf == 2);
        this.zzf = 1;
        zzx();
    }

    @Override // com.google.android.gms.internal.ads.zzjy
    public final boolean zzG() {
        return this.zzj == Long.MIN_VALUE;
    }

    @Override // com.google.android.gms.internal.ads.zzjy
    public final boolean zzH() {
        return this.zzk;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final zzaf[] zzJ() {
        zzaf[] zzafVarArr = this.zzh;
        Objects.requireNonNull(zzafVarArr);
        return zzafVarArr;
    }

    @Override // com.google.android.gms.internal.ads.zzjy, com.google.android.gms.internal.ads.zzjz
    public final int zzb() {
        return this.zza;
    }

    @Override // com.google.android.gms.internal.ads.zzjy
    public final int zzbe() {
        return this.zzf;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final int zzbf(zzje zzjeVar, zzgg zzggVar, int r9) {
        zztw zztwVar = this.zzg;
        Objects.requireNonNull(zztwVar);
        int zza = zztwVar.zza(zzjeVar, zzggVar, r9);
        if (zza == -4) {
            if (zzggVar.zzg()) {
                this.zzj = Long.MIN_VALUE;
                return this.zzk ? -4 : -3;
            }
            long j = zzggVar.zzd + this.zzi;
            zzggVar.zzd = j;
            this.zzj = Math.max(this.zzj, j);
        } else if (zza == -5) {
            zzaf zzafVar = zzjeVar.zza;
            Objects.requireNonNull(zzafVar);
            long j2 = zzafVar.zzq;
            if (j2 != Long.MAX_VALUE) {
                zzad zzb = zzafVar.zzb();
                zzb.zzW(j2 + this.zzi);
                zzjeVar.zza = zzb.zzY();
                return -5;
            }
        }
        return zza;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final zzgy zzbg(Throwable th, zzaf zzafVar, boolean z, int r13) {
        int r6;
        if (zzafVar != null && !this.zzl) {
            this.zzl = true;
            try {
                int zzO = zzO(zzafVar) & 7;
                this.zzl = false;
                r6 = zzO;
            } catch (zzgy unused) {
                this.zzl = false;
            } catch (Throwable th2) {
                this.zzl = false;
                throw th2;
            }
            return zzgy.zzb(th, zzK(), this.zzd, zzafVar, r6, z, r13);
        }
        r6 = 4;
        return zzgy.zzb(th, zzK(), this.zzd, zzafVar, r6, z, r13);
    }

    @Override // com.google.android.gms.internal.ads.zzjz
    public int zze() throws zzgy {
        return 0;
    }

    @Override // com.google.android.gms.internal.ads.zzjy
    public final long zzf() {
        return this.zzj;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final zzje zzh() {
        zzje zzjeVar = this.zzb;
        zzjeVar.zzb = null;
        zzjeVar.zza = null;
        return zzjeVar;
    }

    @Override // com.google.android.gms.internal.ads.zzjy
    public zzjg zzi() {
        return null;
    }

    @Override // com.google.android.gms.internal.ads.zzjy
    public final zzjz zzj() {
        return this;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final zzka zzk() {
        zzka zzkaVar = this.zzc;
        Objects.requireNonNull(zzkaVar);
        return zzkaVar;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final zzmz zzl() {
        zzmz zzmzVar = this.zze;
        Objects.requireNonNull(zzmzVar);
        return zzmzVar;
    }

    @Override // com.google.android.gms.internal.ads.zzjy
    public final zztw zzm() {
        return this.zzg;
    }

    @Override // com.google.android.gms.internal.ads.zzjy
    public final void zzn() {
        zzdd.zzf(this.zzf == 1);
        zzje zzjeVar = this.zzb;
        zzjeVar.zzb = null;
        zzjeVar.zza = null;
        this.zzf = 0;
        this.zzg = null;
        this.zzh = null;
        this.zzk = false;
        zzs();
    }

    @Override // com.google.android.gms.internal.ads.zzjy
    public final void zzo(zzka zzkaVar, zzaf[] zzafVarArr, zztw zztwVar, long j, boolean z, boolean z2, long j2, long j3) throws zzgy {
        zzdd.zzf(this.zzf == 0);
        this.zzc = zzkaVar;
        this.zzf = 1;
        zzt(z, z2);
        zzz(zzafVarArr, zztwVar, j2, j3);
        zzP(j, z);
    }

    @Override // com.google.android.gms.internal.ads.zzju
    public void zzp(int r1, Object obj) throws zzgy {
    }

    @Override // com.google.android.gms.internal.ads.zzjy
    public final void zzq(int r1, zzmz zzmzVar) {
        this.zzd = r1;
        this.zze = zzmzVar;
    }

    protected void zzs() {
        throw null;
    }

    protected void zzt(boolean z, boolean z2) throws zzgy {
    }

    protected void zzu(long j, boolean z) throws zzgy {
        throw null;
    }

    protected void zzv() {
    }

    protected void zzw() throws zzgy {
    }

    protected void zzx() {
    }

    protected void zzy(zzaf[] zzafVarArr, long j, long j2) throws zzgy {
        throw null;
    }

    @Override // com.google.android.gms.internal.ads.zzjy
    public final void zzz(zzaf[] zzafVarArr, zztw zztwVar, long j, long j2) throws zzgy {
        zzdd.zzf(!this.zzk);
        this.zzg = zztwVar;
        if (this.zzj == Long.MIN_VALUE) {
            this.zzj = j;
        }
        this.zzh = zzafVarArr;
        this.zzi = j2;
        zzy(zzafVarArr, j, j2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final boolean zzI() {
        if (zzG()) {
            return this.zzk;
        }
        zztw zztwVar = this.zzg;
        Objects.requireNonNull(zztwVar);
        return zztwVar.zze();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final int zzd(long j) {
        zztw zztwVar = this.zzg;
        Objects.requireNonNull(zztwVar);
        return zztwVar.zzb(j - this.zzi);
    }

    @Override // com.google.android.gms.internal.ads.zzjy
    public final void zzr() throws IOException {
        zztw zztwVar = this.zzg;
        Objects.requireNonNull(zztwVar);
        zztwVar.zzd();
    }
}
