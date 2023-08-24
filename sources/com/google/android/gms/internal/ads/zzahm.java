package com.google.android.gms.internal.ads;

import com.google.android.exoplayer2.C1856C;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzahm implements zzagz {
    private final String zza;
    private final zzed zzb;
    private final zzec zzc;
    private zzaam zzd;
    private String zze;
    private zzaf zzf;
    private int zzg;
    private int zzh;
    private int zzi;
    private int zzj;
    private long zzk;
    private boolean zzl;
    private int zzm;
    private int zzn;
    private int zzo;
    private boolean zzp;
    private long zzq;
    private int zzr;
    private long zzs;
    private int zzt;
    private String zzu;

    public zzahm(String str) {
        this.zza = str;
        zzed zzedVar = new zzed(1024);
        this.zzb = zzedVar;
        byte[] zzH = zzedVar.zzH();
        this.zzc = new zzec(zzH, zzH.length);
        this.zzk = C1856C.TIME_UNSET;
    }

    private final int zzf(zzec zzecVar) throws zzbu {
        int zza = zzecVar.zza();
        zzyc zzb = zzyd.zzb(zzecVar, true);
        this.zzu = zzb.zzc;
        this.zzr = zzb.zza;
        this.zzt = zzb.zzb;
        return zza - zzecVar.zza();
    }

    private static long zzg(zzec zzecVar) {
        return zzecVar.zzc((zzecVar.zzc(2) + 1) * 8);
    }

    /* JADX WARN: Code restructure failed: missing block: B:67:0x0154, code lost:
        if (r14.zzl == false) goto L105;
     */
    @Override // com.google.android.gms.internal.ads.zzagz
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void zza(com.google.android.gms.internal.ads.zzed r15) throws com.google.android.gms.internal.ads.zzbu {
        /*
            Method dump skipped, instructions count: 537
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzahm.zza(com.google.android.gms.internal.ads.zzed):void");
    }

    @Override // com.google.android.gms.internal.ads.zzagz
    public final void zzb(zzzi zzziVar, zzail zzailVar) {
        zzailVar.zzc();
        this.zzd = zzziVar.zzv(zzailVar.zza(), 1);
        this.zze = zzailVar.zzb();
    }

    @Override // com.google.android.gms.internal.ads.zzagz
    public final void zzc() {
    }

    @Override // com.google.android.gms.internal.ads.zzagz
    public final void zzd(long j, int r5) {
        if (j != C1856C.TIME_UNSET) {
            this.zzk = j;
        }
    }

    @Override // com.google.android.gms.internal.ads.zzagz
    public final void zze() {
        this.zzg = 0;
        this.zzk = C1856C.TIME_UNSET;
        this.zzl = false;
    }
}
