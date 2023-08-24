package com.google.android.gms.internal.ads;

import java.io.IOException;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzais implements zzair {
    private final zzzi zza;
    private final zzaam zzb;
    private final zzaiu zzc;
    private final zzaf zzd;
    private final int zze;
    private long zzf;
    private int zzg;
    private long zzh;

    public zzais(zzzi zzziVar, zzaam zzaamVar, zzaiu zzaiuVar, String str, int r6) throws zzbu {
        this.zza = zzziVar;
        this.zzb = zzaamVar;
        this.zzc = zzaiuVar;
        int r2 = (zzaiuVar.zzb * zzaiuVar.zze) / 8;
        int r3 = zzaiuVar.zzd;
        if (r3 != r2) {
            throw zzbu.zza("Expected block size: " + r2 + "; got: " + r3, null);
        }
        int r32 = zzaiuVar.zzc * r2;
        int r0 = r32 * 8;
        int max = Math.max(r2, r32 / 10);
        this.zze = max;
        zzad zzadVar = new zzad();
        zzadVar.zzS(str);
        zzadVar.zzv(r0);
        zzadVar.zzO(r0);
        zzadVar.zzL(max);
        zzadVar.zzw(zzaiuVar.zzb);
        zzadVar.zzT(zzaiuVar.zzc);
        zzadVar.zzN(r6);
        this.zzd = zzadVar.zzY();
    }

    @Override // com.google.android.gms.internal.ads.zzair
    public final void zza(int r10, long j) {
        this.zza.zzL(new zzaix(this.zzc, 1, r10, j));
        this.zzb.zzk(this.zzd);
    }

    @Override // com.google.android.gms.internal.ads.zzair
    public final void zzb(long j) {
        this.zzf = j;
        this.zzg = 0;
        this.zzh = 0L;
    }

    @Override // com.google.android.gms.internal.ads.zzair
    public final boolean zzc(zzzg zzzgVar, long j) throws IOException {
        int r6;
        zzaiu zzaiuVar;
        int r7;
        int r8;
        long j2 = j;
        while (true) {
            r6 = (j2 > 0L ? 1 : (j2 == 0L ? 0 : -1));
            if (r6 <= 0 || (r7 = this.zzg) >= (r8 = this.zze)) {
                break;
            }
            int zza = zzaak.zza(this.zzb, zzzgVar, (int) Math.min(r8 - r7, j2), true);
            if (zza == -1) {
                j2 = 0;
            } else {
                this.zzg += zza;
                j2 -= zza;
            }
        }
        int r2 = this.zzc.zzd;
        int r4 = this.zzg / r2;
        if (r4 > 0) {
            long j3 = this.zzf;
            long zzw = zzel.zzw(this.zzh, 1000000L, zzaiuVar.zzc);
            int r15 = r4 * r2;
            int r1 = this.zzg - r15;
            this.zzb.zzs(j3 + zzw, 1, r15, r1, null);
            this.zzh += r4;
            this.zzg = r1;
        }
        return r6 <= 0;
    }
}
