package com.google.android.gms.internal.ads;

import android.util.Pair;
import java.util.Objects;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public abstract class zzgh extends zzcn {
    private final int zzc;
    private final zztz zzd;

    public zzgh(boolean z, zztz zztzVar, byte[] bArr) {
        this.zzd = zztzVar;
        this.zzc = zztzVar.zzc();
    }

    private final int zzw(int r2, boolean z) {
        if (z) {
            return this.zzd.zzd(r2);
        }
        if (r2 >= this.zzc - 1) {
            return -1;
        }
        return r2 + 1;
    }

    private final int zzx(int r2, boolean z) {
        if (z) {
            return this.zzd.zze(r2);
        }
        if (r2 <= 0) {
            return -1;
        }
        return r2 - 1;
    }

    @Override // com.google.android.gms.internal.ads.zzcn
    public final int zza(Object obj) {
        int zza;
        if (obj instanceof Pair) {
            Pair pair = (Pair) obj;
            Object obj2 = pair.first;
            Object obj3 = pair.second;
            int zzp = zzp(obj2);
            if (zzp == -1 || (zza = zzu(zzp).zza(obj3)) == -1) {
                return -1;
            }
            return zzs(zzp) + zza;
        }
        return -1;
    }

    @Override // com.google.android.gms.internal.ads.zzcn
    public final zzck zzd(int r5, zzck zzckVar, boolean z) {
        int zzq = zzq(r5);
        int zzt = zzt(zzq);
        zzu(zzq).zzd(r5 - zzs(zzq), zzckVar, z);
        zzckVar.zzd += zzt;
        if (z) {
            Object zzv = zzv(zzq);
            Object obj = zzckVar.zzc;
            Objects.requireNonNull(obj);
            zzckVar.zzc = Pair.create(zzv, obj);
        }
        return zzckVar;
    }

    @Override // com.google.android.gms.internal.ads.zzcn
    public final zzcm zze(int r5, zzcm zzcmVar, long j) {
        int zzr = zzr(r5);
        int zzt = zzt(zzr);
        int zzs = zzs(zzr);
        zzu(zzr).zze(r5 - zzt, zzcmVar, j);
        Object zzv = zzv(zzr);
        if (!zzcm.zza.equals(zzcmVar.zzc)) {
            zzv = Pair.create(zzv, zzcmVar.zzc);
        }
        zzcmVar.zzc = zzv;
        zzcmVar.zzo += zzs;
        zzcmVar.zzp += zzs;
        return zzcmVar;
    }

    @Override // com.google.android.gms.internal.ads.zzcn
    public final Object zzf(int r4) {
        int zzq = zzq(r4);
        return Pair.create(zzv(zzq), zzu(zzq).zzf(r4 - zzs(zzq)));
    }

    @Override // com.google.android.gms.internal.ads.zzcn
    public final int zzg(boolean z) {
        if (this.zzc == 0) {
            return -1;
        }
        int zza = z ? this.zzd.zza() : 0;
        while (zzu(zza).zzo()) {
            zza = zzw(zza, z);
            if (zza == -1) {
                return -1;
            }
        }
        return zzt(zza) + zzu(zza).zzg(z);
    }

    @Override // com.google.android.gms.internal.ads.zzcn
    public final int zzh(boolean z) {
        int r0 = this.zzc;
        if (r0 == 0) {
            return -1;
        }
        int zzb = z ? this.zzd.zzb() : r0 - 1;
        while (zzu(zzb).zzo()) {
            zzb = zzx(zzb, z);
            if (zzb == -1) {
                return -1;
            }
        }
        return zzt(zzb) + zzu(zzb).zzh(z);
    }

    @Override // com.google.android.gms.internal.ads.zzcn
    public final int zzj(int r6, int r7, boolean z) {
        int zzr = zzr(r6);
        int zzt = zzt(zzr);
        int zzj = zzu(zzr).zzj(r6 - zzt, r7 == 2 ? 0 : r7, z);
        if (zzj != -1) {
            return zzt + zzj;
        }
        int zzw = zzw(zzr, z);
        while (zzw != -1 && zzu(zzw).zzo()) {
            zzw = zzw(zzw, z);
        }
        if (zzw != -1) {
            return zzt(zzw) + zzu(zzw).zzg(z);
        }
        if (r7 == 2) {
            return zzg(z);
        }
        return -1;
    }

    @Override // com.google.android.gms.internal.ads.zzcn
    public final int zzk(int r3, int r4, boolean z) {
        int zzr = zzr(r3);
        int zzt = zzt(zzr);
        int zzk = zzu(zzr).zzk(r3 - zzt, 0, false);
        if (zzk != -1) {
            return zzt + zzk;
        }
        int zzx = zzx(zzr, false);
        while (zzx != -1 && zzu(zzx).zzo()) {
            zzx = zzx(zzx, false);
        }
        if (zzx != -1) {
            return zzt(zzx) + zzu(zzx).zzh(false);
        }
        return -1;
    }

    @Override // com.google.android.gms.internal.ads.zzcn
    public final zzck zzn(Object obj, zzck zzckVar) {
        Pair pair = (Pair) obj;
        Object obj2 = pair.first;
        Object obj3 = pair.second;
        int zzp = zzp(obj2);
        int zzt = zzt(zzp);
        zzu(zzp).zzn(obj3, zzckVar);
        zzckVar.zzd += zzt;
        zzckVar.zzc = obj;
        return zzckVar;
    }

    protected abstract int zzp(Object obj);

    protected abstract int zzq(int r1);

    protected abstract int zzr(int r1);

    protected abstract int zzs(int r1);

    protected abstract int zzt(int r1);

    protected abstract zzcn zzu(int r1);

    protected abstract Object zzv(int r1);
}
