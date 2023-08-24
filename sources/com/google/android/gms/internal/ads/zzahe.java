package com.google.android.gms.internal.ads;

import com.google.android.exoplayer2.C1856C;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzahe implements zzagz {
    private static final float[] zza = {1.0f, 1.0f, 1.0909091f, 0.90909094f, 1.4545455f, 1.2121212f, 1.0f};
    private final zzaio zzb;
    private final zzed zzc;
    private final boolean[] zzd;
    private final zzahc zze;
    private final zzaho zzf;
    private zzahd zzg;
    private long zzh;
    private String zzi;
    private zzaam zzj;
    private boolean zzk;
    private long zzl;

    public zzahe() {
        this(null);
    }

    /* JADX WARN: Removed duplicated region for block: B:54:0x018c  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x0191  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x019a  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x01bc  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x01ce A[SYNTHETIC] */
    @Override // com.google.android.gms.internal.ads.zzagz
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void zza(com.google.android.gms.internal.ads.zzed r19) {
        /*
            Method dump skipped, instructions count: 486
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzahe.zza(com.google.android.gms.internal.ads.zzed):void");
    }

    @Override // com.google.android.gms.internal.ads.zzagz
    public final void zzb(zzzi zzziVar, zzail zzailVar) {
        zzailVar.zzc();
        this.zzi = zzailVar.zzb();
        zzaam zzv = zzziVar.zzv(zzailVar.zza(), 2);
        this.zzj = zzv;
        this.zzg = new zzahd(zzv);
        this.zzb.zzb(zzziVar, zzailVar);
    }

    @Override // com.google.android.gms.internal.ads.zzagz
    public final void zzc() {
    }

    @Override // com.google.android.gms.internal.ads.zzagz
    public final void zzd(long j, int r5) {
        if (j != C1856C.TIME_UNSET) {
            this.zzl = j;
        }
    }

    @Override // com.google.android.gms.internal.ads.zzagz
    public final void zze() {
        zzaac.zze(this.zzd);
        this.zze.zzb();
        zzahd zzahdVar = this.zzg;
        if (zzahdVar != null) {
            zzahdVar.zzd();
        }
        this.zzf.zzb();
        this.zzh = 0L;
        this.zzl = C1856C.TIME_UNSET;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzahe(zzaio zzaioVar) {
        this.zzb = zzaioVar;
        this.zzd = new boolean[4];
        this.zze = new zzahc(128);
        this.zzl = C1856C.TIME_UNSET;
        this.zzf = new zzaho(178, 128);
        this.zzc = new zzed();
    }
}
