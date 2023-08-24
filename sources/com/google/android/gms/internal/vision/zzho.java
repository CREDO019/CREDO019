package com.google.android.gms.internal.vision;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.0 */
/* loaded from: classes3.dex */
final class zzho extends zzhm {
    private static final Class<?> zzyg = Collections.unmodifiableList(Collections.emptyList()).getClass();

    private zzho() {
        super();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.vision.zzhm
    public final <L> List<L> zza(Object obj, long j) {
        return zza(obj, j, 10);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.vision.zzhm
    public final void zzb(Object obj, long j) {
        Object unmodifiableList;
        List list = (List) zzjp.zzp(obj, j);
        if (list instanceof zzhj) {
            unmodifiableList = ((zzhj) list).zzgy();
        } else if (zzyg.isAssignableFrom(list.getClass())) {
            return;
        } else {
            if ((list instanceof zzio) && (list instanceof zzgz)) {
                zzgz zzgzVar = (zzgz) list;
                if (zzgzVar.zzdo()) {
                    zzgzVar.zzdp();
                    return;
                }
                return;
            }
            unmodifiableList = Collections.unmodifiableList(list);
        }
        zzjp.zza(obj, j, unmodifiableList);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static <L> List<L> zza(Object obj, long j, int r6) {
        zzhk zzhkVar;
        List<L> arrayList;
        List<L> zzd = zzd(obj, j);
        if (zzd.isEmpty()) {
            if (zzd instanceof zzhj) {
                arrayList = new zzhk(r6);
            } else if ((zzd instanceof zzio) && (zzd instanceof zzgz)) {
                arrayList = ((zzgz) zzd).zzag(r6);
            } else {
                arrayList = new ArrayList<>(r6);
            }
            zzjp.zza(obj, j, arrayList);
            return arrayList;
        }
        if (zzyg.isAssignableFrom(zzd.getClass())) {
            ArrayList arrayList2 = new ArrayList(zzd.size() + r6);
            arrayList2.addAll(zzd);
            zzjp.zza(obj, j, arrayList2);
            zzhkVar = arrayList2;
        } else if (zzd instanceof zzjo) {
            zzhk zzhkVar2 = new zzhk(zzd.size() + r6);
            zzhkVar2.addAll((zzjo) zzd);
            zzjp.zza(obj, j, zzhkVar2);
            zzhkVar = zzhkVar2;
        } else if ((zzd instanceof zzio) && (zzd instanceof zzgz)) {
            zzgz zzgzVar = (zzgz) zzd;
            if (zzgzVar.zzdo()) {
                return zzd;
            }
            zzgz zzag = zzgzVar.zzag(zzd.size() + r6);
            zzjp.zza(obj, j, zzag);
            return zzag;
        } else {
            return zzd;
        }
        return zzhkVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.vision.zzhm
    public final <E> void zza(Object obj, Object obj2, long j) {
        List zzd = zzd(obj2, j);
        List zza = zza(obj, j, zzd.size());
        int size = zza.size();
        int size2 = zzd.size();
        if (size > 0 && size2 > 0) {
            zza.addAll(zzd);
        }
        if (size > 0) {
            zzd = zza;
        }
        zzjp.zza(obj, j, zzd);
    }

    private static <E> List<E> zzd(Object obj, long j) {
        return (List) zzjp.zzp(obj, j);
    }
}
