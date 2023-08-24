package com.google.android.gms.internal.ads;

import com.google.android.exoplayer2.C1856C;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzahk implements zzagz {
    private final zzaia zza;
    private String zzb;
    private zzaam zzc;
    private zzahj zzd;
    private boolean zze;
    private long zzl;
    private final boolean[] zzf = new boolean[3];
    private final zzaho zzg = new zzaho(32, 128);
    private final zzaho zzh = new zzaho(33, 128);
    private final zzaho zzi = new zzaho(34, 128);
    private final zzaho zzj = new zzaho(39, 128);
    private final zzaho zzk = new zzaho(40, 128);
    private long zzm = C1856C.TIME_UNSET;
    private final zzed zzn = new zzed();

    public zzahk(zzaia zzaiaVar) {
        this.zza = zzaiaVar;
    }

    @RequiresNonNull({"sampleReader"})
    private final void zzf(byte[] bArr, int r3, int r4) {
        this.zzd.zzb(bArr, r3, r4);
        if (!this.zze) {
            this.zzg.zza(bArr, r3, r4);
            this.zzh.zza(bArr, r3, r4);
            this.zzi.zza(bArr, r3, r4);
        }
        this.zzj.zza(bArr, r3, r4);
        this.zzk.zza(bArr, r3, r4);
    }

    /* JADX WARN: Removed duplicated region for block: B:156:0x034f  */
    /* JADX WARN: Removed duplicated region for block: B:157:0x0372  */
    /* JADX WARN: Removed duplicated region for block: B:160:0x037c  */
    /* JADX WARN: Removed duplicated region for block: B:163:0x03b1  */
    /* JADX WARN: Removed duplicated region for block: B:164:0x03c3  */
    @Override // com.google.android.gms.internal.ads.zzagz
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void zza(com.google.android.gms.internal.ads.zzed r38) {
        /*
            Method dump skipped, instructions count: 989
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzahk.zza(com.google.android.gms.internal.ads.zzed):void");
    }

    @Override // com.google.android.gms.internal.ads.zzagz
    public final void zzb(zzzi zzziVar, zzail zzailVar) {
        zzailVar.zzc();
        this.zzb = zzailVar.zzb();
        zzaam zzv = zzziVar.zzv(zzailVar.zza(), 2);
        this.zzc = zzv;
        this.zzd = new zzahj(zzv);
        this.zza.zzb(zzziVar, zzailVar);
    }

    @Override // com.google.android.gms.internal.ads.zzagz
    public final void zzc() {
    }

    @Override // com.google.android.gms.internal.ads.zzagz
    public final void zzd(long j, int r5) {
        if (j != C1856C.TIME_UNSET) {
            this.zzm = j;
        }
    }

    @Override // com.google.android.gms.internal.ads.zzagz
    public final void zze() {
        this.zzl = 0L;
        this.zzm = C1856C.TIME_UNSET;
        zzaac.zze(this.zzf);
        this.zzg.zzb();
        this.zzh.zzb();
        this.zzi.zzb();
        this.zzj.zzb();
        this.zzk.zzb();
        zzahj zzahjVar = this.zzd;
        if (zzahjVar != null) {
            zzahjVar.zzc();
        }
    }
}
