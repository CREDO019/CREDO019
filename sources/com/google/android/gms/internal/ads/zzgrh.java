package com.google.android.gms.internal.ads;

import java.io.IOException;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
abstract class zzgrh {
    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract int zza(Object obj);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract int zzb(Object obj);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract Object zzc(Object obj);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract Object zzd(Object obj);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract Object zze(Object obj, Object obj2);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract Object zzf();

    abstract Object zzg(Object obj);

    abstract void zzh(Object obj, int r2, int r3);

    abstract void zzi(Object obj, int r2, long j);

    abstract void zzj(Object obj, int r2, Object obj2);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void zzk(Object obj, int r2, zzgnf zzgnfVar);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void zzl(Object obj, int r2, long j);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void zzm(Object obj);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void zzn(Object obj, Object obj2);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void zzo(Object obj, Object obj2);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract boolean zzq(zzgqi zzgqiVar);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void zzr(Object obj, zzgnv zzgnvVar) throws IOException;

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean zzp(Object obj, zzgqi zzgqiVar) throws IOException {
        int zzd = zzgqiVar.zzd();
        int r1 = zzd >>> 3;
        int r0 = zzd & 7;
        if (r0 == 0) {
            zzl(obj, r1, zzgqiVar.zzl());
            return true;
        } else if (r0 == 1) {
            zzi(obj, r1, zzgqiVar.zzk());
            return true;
        } else if (r0 == 2) {
            zzk(obj, r1, zzgqiVar.zzp());
            return true;
        } else if (r0 != 3) {
            if (r0 != 4) {
                if (r0 == 5) {
                    zzh(obj, r1, zzgqiVar.zzf());
                    return true;
                }
                throw zzgoz.zza();
            }
            return false;
        } else {
            Object zzf = zzf();
            int r3 = 4 | (r1 << 3);
            while (zzgqiVar.zzc() != Integer.MAX_VALUE && zzp(zzf, zzgqiVar)) {
            }
            if (r3 != zzgqiVar.zzd()) {
                throw zzgoz.zzb();
            }
            zzg(zzf);
            zzj(obj, r1, zzf);
            return true;
        }
    }
}
