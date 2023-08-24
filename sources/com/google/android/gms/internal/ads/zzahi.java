package com.google.android.gms.internal.ads;

import com.google.android.exoplayer2.C1856C;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzahi implements zzagz {
    private final zzaia zza;
    private long zze;
    private String zzg;
    private zzaam zzh;
    private zzahh zzi;
    private boolean zzj;
    private boolean zzl;
    private final boolean[] zzf = new boolean[3];
    private final zzaho zzb = new zzaho(7, 128);
    private final zzaho zzc = new zzaho(8, 128);
    private final zzaho zzd = new zzaho(6, 128);
    private long zzk = C1856C.TIME_UNSET;
    private final zzed zzm = new zzed();

    public zzahi(zzaia zzaiaVar, boolean z, boolean z2) {
        this.zza = zzaiaVar;
    }

    @RequiresNonNull({"sampleReader"})
    private final void zzf(byte[] bArr, int r3, int r4) {
        if (!this.zzj) {
            this.zzb.zza(bArr, r3, r4);
            this.zzc.zza(bArr, r3, r4);
        }
        this.zzd.zza(bArr, r3, r4);
    }

    /* JADX WARN: Removed duplicated region for block: B:30:0x013f  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x016e  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0177  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0181 A[SYNTHETIC] */
    @Override // com.google.android.gms.internal.ads.zzagz
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void zza(com.google.android.gms.internal.ads.zzed r20) {
        /*
            Method dump skipped, instructions count: 405
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzahi.zza(com.google.android.gms.internal.ads.zzed):void");
    }

    @Override // com.google.android.gms.internal.ads.zzagz
    public final void zzb(zzzi zzziVar, zzail zzailVar) {
        zzailVar.zzc();
        this.zzg = zzailVar.zzb();
        zzaam zzv = zzziVar.zzv(zzailVar.zza(), 2);
        this.zzh = zzv;
        this.zzi = new zzahh(zzv, false, false);
        this.zza.zzb(zzziVar, zzailVar);
    }

    @Override // com.google.android.gms.internal.ads.zzagz
    public final void zzc() {
    }

    @Override // com.google.android.gms.internal.ads.zzagz
    public final void zzd(long j, int r6) {
        if (j != C1856C.TIME_UNSET) {
            this.zzk = j;
        }
        this.zzl |= (r6 & 2) != 0;
    }

    @Override // com.google.android.gms.internal.ads.zzagz
    public final void zze() {
        this.zze = 0L;
        this.zzl = false;
        this.zzk = C1856C.TIME_UNSET;
        zzaac.zze(this.zzf);
        this.zzb.zzb();
        this.zzc.zzb();
        this.zzd.zzb();
        zzahh zzahhVar = this.zzi;
        if (zzahhVar != null) {
            zzahhVar.zzc();
        }
    }
}
