package com.google.android.gms.internal.vision;

import java.io.IOException;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.0 */
/* loaded from: classes3.dex */
abstract class zzjj<T, B> {
    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void zza(B b, int r2, long j);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void zza(B b, int r2, zzfh zzfhVar);

    abstract void zza(B b, int r2, T t);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void zza(T t, zzkg zzkgVar) throws IOException;

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract boolean zza(zzis zzisVar);

    abstract void zzb(B b, int r2, long j);

    abstract void zzc(B b, int r2, int r3);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void zzc(T t, zzkg zzkgVar) throws IOException;

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void zzf(Object obj, T t);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void zzg(Object obj);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void zzg(Object obj, B b);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract T zzh(T t, T t2);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract B zzif();

    abstract T zzn(B b);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract int zzr(T t);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract T zzv(Object obj);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract B zzw(Object obj);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract int zzx(T t);

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean zza(B b, zzis zzisVar) throws IOException {
        int tag = zzisVar.getTag();
        int r1 = tag >>> 3;
        int r0 = tag & 7;
        if (r0 == 0) {
            zza((zzjj<T, B>) b, r1, zzisVar.zzdx());
            return true;
        } else if (r0 == 1) {
            zzb(b, r1, zzisVar.zzdz());
            return true;
        } else if (r0 == 2) {
            zza((zzjj<T, B>) b, r1, zzisVar.zzed());
            return true;
        } else if (r0 != 3) {
            if (r0 != 4) {
                if (r0 == 5) {
                    zzc(b, r1, zzisVar.zzea());
                    return true;
                }
                throw zzhc.zzgr();
            }
            return false;
        } else {
            B zzif = zzif();
            int r3 = 4 | (r1 << 3);
            while (zzisVar.zzdu() != Integer.MAX_VALUE && zza((zzjj<T, B>) zzif, zzisVar)) {
            }
            if (r3 != zzisVar.getTag()) {
                throw zzhc.zzgq();
            }
            zza((zzjj<T, B>) b, r1, (int) zzn(zzif));
            return true;
        }
    }
}
