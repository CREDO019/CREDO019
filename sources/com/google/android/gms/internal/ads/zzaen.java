package com.google.android.gms.internal.ads;

import android.net.Uri;
import com.google.android.exoplayer2.C1856C;
import java.io.EOFException;
import java.io.IOException;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzaen implements zzzf {
    public static final zzzm zza = new zzzm() { // from class: com.google.android.gms.internal.ads.zzael
        @Override // com.google.android.gms.internal.ads.zzzm
        public final zzzf[] zza() {
            zzzm zzzmVar = zzaen.zza;
            return new zzzf[]{new zzaen(0)};
        }

        @Override // com.google.android.gms.internal.ads.zzzm
        public final /* synthetic */ zzzf[] zzb(Uri uri, Map map) {
            return zzzl.zza(this, uri, map);
        }
    };
    private static final zzacx zzb = new zzacx() { // from class: com.google.android.gms.internal.ads.zzaem
    };
    private final zzed zzc;
    private final zzzy zzd;
    private final zzzu zze;
    private final zzzw zzf;
    private final zzaam zzg;
    private zzzi zzh;
    private zzaam zzi;
    private zzaam zzj;
    private int zzk;
    private zzbq zzl;
    private long zzm;
    private long zzn;
    private long zzo;
    private int zzp;
    private zzaep zzq;
    private boolean zzr;

    public zzaen() {
        this(0);
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x0059  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0072  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0082 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:39:0x00b5  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x00f1  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x0107  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x0153  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x0159  */
    @org.checkerframework.checker.nullness.qual.RequiresNonNull({"extractorOutput", "realTrackOutput"})
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final int zzf(com.google.android.gms.internal.ads.zzzg r17) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 615
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzaen.zzf(com.google.android.gms.internal.ads.zzzg):int");
    }

    private final long zzg(long j) {
        return this.zzm + ((j * 1000000) / this.zzd.zzd);
    }

    private final zzaep zzh(zzzg zzzgVar, boolean z) throws IOException {
        ((zzyv) zzzgVar).zzm(this.zzc.zzH(), 0, 4, false);
        this.zzc.zzF(0);
        this.zzd.zza(this.zzc.zze());
        return new zzaei(zzzgVar.zzd(), zzzgVar.zzf(), this.zzd, false);
    }

    private static boolean zzi(int r4, long j) {
        return ((long) (r4 & (-128000))) == (j & (-128000));
    }

    private final boolean zzj(zzzg zzzgVar) throws IOException {
        zzaep zzaepVar = this.zzq;
        if (zzaepVar != null) {
            long zzb2 = zzaepVar.zzb();
            if (zzb2 != -1 && zzzgVar.zze() > zzb2 - 4) {
                return true;
            }
        }
        try {
            return !zzzgVar.zzm(this.zzc.zzH(), 0, 4, true);
        } catch (EOFException unused) {
            return true;
        }
    }

    private final boolean zzk(zzzg zzzgVar, boolean z) throws IOException {
        int r2;
        int r3;
        int zzb2;
        int r1 = true != z ? 131072 : 32768;
        zzzgVar.zzj();
        if (zzzgVar.zzf() == 0) {
            zzbq zza2 = this.zzf.zza(zzzgVar, null);
            this.zzl = zza2;
            if (zza2 != null) {
                this.zze.zzb(zza2);
            }
            r3 = (int) zzzgVar.zze();
            if (!z) {
                ((zzyv) zzzgVar).zzo(r3, false);
            }
            r2 = 0;
        } else {
            r2 = 0;
            r3 = 0;
        }
        int r4 = 0;
        int r5 = 0;
        while (true) {
            if (!zzj(zzzgVar)) {
                this.zzc.zzF(0);
                int zze = this.zzc.zze();
                if ((r2 == 0 || zzi(zze, r2)) && (zzb2 = zzzz.zzb(zze)) != -1) {
                    r4++;
                    if (r4 != 1) {
                        if (r4 == 4) {
                            break;
                        }
                    } else {
                        this.zzd.zza(zze);
                        r2 = zze;
                    }
                    ((zzyv) zzzgVar).zzl(zzb2 - 4, false);
                } else {
                    int r22 = r5 + 1;
                    if (r5 == r1) {
                        if (z) {
                            return false;
                        }
                        throw zzbu.zza("Searched too many bytes.", null);
                    }
                    if (!z) {
                        ((zzyv) zzzgVar).zzo(1, false);
                    } else {
                        zzzgVar.zzj();
                        ((zzyv) zzzgVar).zzl(r3 + r22, false);
                    }
                    r5 = r22;
                    r2 = 0;
                    r4 = 0;
                }
            } else if (r4 <= 0) {
                throw new EOFException();
            }
        }
        if (z) {
            ((zzyv) zzzgVar).zzo(r3 + r5, false);
        } else {
            zzzgVar.zzj();
        }
        this.zzk = r2;
        return true;
    }

    @Override // com.google.android.gms.internal.ads.zzzf
    public final int zza(zzzg zzzgVar, zzaaf zzaafVar) throws IOException {
        zzdd.zzb(this.zzi);
        int r6 = zzel.zza;
        int zzf = zzf(zzzgVar);
        if (zzf == -1 && (this.zzq instanceof zzaej)) {
            if (this.zzq.zze() != zzg(this.zzn)) {
                zzaej zzaejVar = (zzaej) this.zzq;
                throw null;
            }
        }
        return zzf;
    }

    @Override // com.google.android.gms.internal.ads.zzzf
    public final void zzb(zzzi zzziVar) {
        this.zzh = zzziVar;
        zzaam zzv = zzziVar.zzv(0, 1);
        this.zzi = zzv;
        this.zzj = zzv;
        this.zzh.zzB();
    }

    @Override // com.google.android.gms.internal.ads.zzzf
    public final void zzc(long j, long j2) {
        this.zzk = 0;
        this.zzm = C1856C.TIME_UNSET;
        this.zzn = 0L;
        this.zzp = 0;
        zzaep zzaepVar = this.zzq;
        if (zzaepVar instanceof zzaej) {
            zzaej zzaejVar = (zzaej) zzaepVar;
            throw null;
        }
    }

    @Override // com.google.android.gms.internal.ads.zzzf
    public final boolean zzd(zzzg zzzgVar) throws IOException {
        return zzk(zzzgVar, true);
    }

    public final void zze() {
        this.zzr = true;
    }

    public zzaen(int r3) {
        this.zzc = new zzed(10);
        this.zzd = new zzzy();
        this.zze = new zzzu();
        this.zzm = C1856C.TIME_UNSET;
        this.zzf = new zzzw();
        zzze zzzeVar = new zzze();
        this.zzg = zzzeVar;
        this.zzj = zzzeVar;
    }
}
