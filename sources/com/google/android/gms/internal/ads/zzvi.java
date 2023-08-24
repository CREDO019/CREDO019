package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzvi extends zzvk implements Comparable {
    private final int zze;
    private final boolean zzf;
    private final boolean zzg;
    private final boolean zzh;
    private final int zzi;
    private final int zzj;
    private final int zzk;
    private final int zzl;
    private final boolean zzm;

    public zzvi(int r5, zzcp zzcpVar, int r7, zzvc zzvcVar, int r9, String str) {
        super(r5, zzcpVar, r7);
        zzfuv zzfuvVar;
        int r1;
        int r52 = 0;
        this.zzf = zzvo.zzm(r9, false);
        int r6 = this.zzd.zze;
        int r72 = zzvcVar.zzx;
        this.zzg = 1 == (r6 & 1);
        this.zzh = (r6 & 2) != 0;
        if (zzvcVar.zzv.isEmpty()) {
            zzfuvVar = zzfuv.zzp("");
        } else {
            zzfuvVar = zzvcVar.zzv;
        }
        int r73 = 0;
        while (true) {
            if (r73 >= zzfuvVar.size()) {
                r73 = Integer.MAX_VALUE;
                r1 = 0;
                break;
            }
            boolean z = zzvcVar.zzy;
            r1 = zzvo.zza(this.zzd, (String) zzfuvVar.get(r73), false);
            if (r1 > 0) {
                break;
            }
            r73++;
        }
        this.zzi = r73;
        this.zzj = r1;
        int r62 = this.zzd.zzf;
        int r63 = zzvcVar.zzw;
        int bitCount = Integer.bitCount(0);
        this.zzk = bitCount;
        int r74 = this.zzd.zzf;
        this.zzm = false;
        int zza = zzvo.zza(this.zzd, str, zzvo.zzf(str) == null);
        this.zzl = zza;
        boolean z2 = r1 > 0 || (zzvcVar.zzv.isEmpty() && bitCount > 0) || this.zzg || (this.zzh && zza > 0);
        if (zzvo.zzm(r9, zzvcVar.zzQ) && z2) {
            r52 = 1;
        }
        this.zze = r52;
    }

    @Override // java.lang.Comparable
    /* renamed from: zza */
    public final int compareTo(zzvi zzviVar) {
        zzfuk zzb = zzfuk.zzj().zzd(this.zzf, zzviVar.zzf).zzc(Integer.valueOf(this.zzi), Integer.valueOf(zzviVar.zzi), zzfwd.zzc().zza()).zzb(this.zzj, zzviVar.zzj).zzb(this.zzk, zzviVar.zzk).zzd(this.zzg, zzviVar.zzg).zzc(Boolean.valueOf(this.zzh), Boolean.valueOf(zzviVar.zzh), this.zzj == 0 ? zzfwd.zzc() : zzfwd.zzc().zza()).zzb(this.zzl, zzviVar.zzl);
        if (this.zzk == 0) {
            boolean z = zzviVar.zzm;
            zzb = zzb.zze(false, false);
        }
        return zzb.zza();
    }

    @Override // com.google.android.gms.internal.ads.zzvk
    public final int zzb() {
        return this.zze;
    }

    @Override // com.google.android.gms.internal.ads.zzvk
    public final /* bridge */ /* synthetic */ boolean zzc(zzvk zzvkVar) {
        zzvi zzviVar = (zzvi) zzvkVar;
        return false;
    }
}
