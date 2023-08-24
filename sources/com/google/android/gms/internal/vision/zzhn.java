package com.google.android.gms.internal.vision;

import java.util.List;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.0 */
/* loaded from: classes3.dex */
final class zzhn extends zzhm {
    private zzhn() {
        super();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.vision.zzhm
    public final <L> List<L> zza(Object obj, long j) {
        zzgz zzc = zzc(obj, j);
        if (zzc.zzdo()) {
            return zzc;
        }
        int size = zzc.size();
        zzgz zzag = zzc.zzag(size == 0 ? 10 : size << 1);
        zzjp.zza(obj, j, zzag);
        return zzag;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.vision.zzhm
    public final void zzb(Object obj, long j) {
        zzc(obj, j).zzdp();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v2, types: [com.google.android.gms.internal.vision.zzgz] */
    @Override // com.google.android.gms.internal.vision.zzhm
    public final <E> void zza(Object obj, Object obj2, long j) {
        zzgz<E> zzc = zzc(obj, j);
        zzgz<E> zzc2 = zzc(obj2, j);
        int size = zzc.size();
        int size2 = zzc2.size();
        zzgz<E> zzgzVar = zzc;
        zzgzVar = zzc;
        if (size > 0 && size2 > 0) {
            boolean zzdo = zzc.zzdo();
            zzgz<E> zzgzVar2 = zzc;
            if (!zzdo) {
                zzgzVar2 = zzc.zzag(size2 + size);
            }
            zzgzVar2.addAll(zzc2);
            zzgzVar = zzgzVar2;
        }
        if (size > 0) {
            zzc2 = zzgzVar;
        }
        zzjp.zza(obj, j, zzc2);
    }

    private static <E> zzgz<E> zzc(Object obj, long j) {
        return (zzgz) zzjp.zzp(obj, j);
    }
}
