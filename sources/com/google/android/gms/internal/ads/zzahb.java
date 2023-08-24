package com.google.android.gms.internal.ads;

import com.google.android.exoplayer2.C1856C;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzahb implements zzagz {
    private static final double[] zza = {23.976023976023978d, 24.0d, 25.0d, 29.97002997002997d, 30.0d, 50.0d, 59.94005994005994d, 60.0d};
    private String zzb;
    private zzaam zzc;
    private final zzaio zzd;
    private final zzed zze;
    private final zzaho zzf;
    private final boolean[] zzg;
    private final zzaha zzh;
    private long zzi;
    private boolean zzj;
    private boolean zzk;
    private long zzl;
    private long zzm;
    private long zzn;
    private long zzo;
    private boolean zzp;
    private boolean zzq;

    public zzahb() {
        this(null);
    }

    /* JADX WARN: Removed duplicated region for block: B:46:0x0138  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x017c  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x0194  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x019e  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x01b3  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x01c8  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x01df  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x01e1  */
    @Override // com.google.android.gms.internal.ads.zzagz
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void zza(com.google.android.gms.internal.ads.zzed r20) {
        /*
            Method dump skipped, instructions count: 490
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzahb.zza(com.google.android.gms.internal.ads.zzed):void");
    }

    @Override // com.google.android.gms.internal.ads.zzagz
    public final void zzb(zzzi zzziVar, zzail zzailVar) {
        zzailVar.zzc();
        this.zzb = zzailVar.zzb();
        this.zzc = zzziVar.zzv(zzailVar.zza(), 2);
        zzaio zzaioVar = this.zzd;
        if (zzaioVar != null) {
            zzaioVar.zzb(zzziVar, zzailVar);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzagz
    public final void zzc() {
    }

    @Override // com.google.android.gms.internal.ads.zzagz
    public final void zzd(long j, int r3) {
        this.zzm = j;
    }

    @Override // com.google.android.gms.internal.ads.zzagz
    public final void zze() {
        zzaac.zze(this.zzg);
        this.zzh.zzb();
        zzaho zzahoVar = this.zzf;
        if (zzahoVar != null) {
            zzahoVar.zzb();
        }
        this.zzi = 0L;
        this.zzj = false;
        this.zzm = C1856C.TIME_UNSET;
        this.zzo = C1856C.TIME_UNSET;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzahb(zzaio zzaioVar) {
        zzed zzedVar;
        this.zzd = zzaioVar;
        this.zzg = new boolean[4];
        this.zzh = new zzaha(128);
        if (zzaioVar != null) {
            this.zzf = new zzaho(178, 128);
            zzedVar = new zzed();
        } else {
            zzedVar = null;
            this.zzf = null;
        }
        this.zze = zzedVar;
        this.zzm = C1856C.TIME_UNSET;
        this.zzo = C1856C.TIME_UNSET;
    }
}
