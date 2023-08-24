package com.google.android.gms.internal.ads;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzgph extends zzgpl {
    private static final Class zza = Collections.unmodifiableList(Collections.emptyList()).getClass();

    private zzgph() {
        super(null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzgph(zzgpg zzgpgVar) {
        super(null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static List zzf(Object obj, long j, int r6) {
        zzgpe zzgpeVar;
        List arrayList;
        List list = (List) zzgrr.zzh(obj, j);
        if (list.isEmpty()) {
            if (list instanceof zzgpf) {
                arrayList = new zzgpe(r6);
            } else if (!(list instanceof zzgqe) || !(list instanceof zzgow)) {
                arrayList = new ArrayList(r6);
            } else {
                arrayList = ((zzgow) list).zzd(r6);
            }
            zzgrr.zzv(obj, j, arrayList);
            return arrayList;
        }
        if (zza.isAssignableFrom(list.getClass())) {
            ArrayList arrayList2 = new ArrayList(list.size() + r6);
            arrayList2.addAll(list);
            zzgrr.zzv(obj, j, arrayList2);
            zzgpeVar = arrayList2;
        } else if (list instanceof zzgrm) {
            zzgpe zzgpeVar2 = new zzgpe(list.size() + r6);
            zzgpeVar2.addAll(zzgpeVar2.size(), (zzgrm) list);
            zzgrr.zzv(obj, j, zzgpeVar2);
            zzgpeVar = zzgpeVar2;
        } else if ((list instanceof zzgqe) && (list instanceof zzgow)) {
            zzgow zzgowVar = (zzgow) list;
            if (zzgowVar.zzc()) {
                return list;
            }
            zzgow zzd = zzgowVar.zzd(list.size() + r6);
            zzgrr.zzv(obj, j, zzd);
            return zzd;
        } else {
            return list;
        }
        return zzgpeVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.ads.zzgpl
    public final List zza(Object obj, long j) {
        return zzf(obj, j, 10);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.ads.zzgpl
    public final void zzb(Object obj, long j) {
        Object unmodifiableList;
        List list = (List) zzgrr.zzh(obj, j);
        if (list instanceof zzgpf) {
            unmodifiableList = ((zzgpf) list).zze();
        } else if (zza.isAssignableFrom(list.getClass())) {
            return;
        } else {
            if (!(list instanceof zzgqe) || !(list instanceof zzgow)) {
                unmodifiableList = Collections.unmodifiableList(list);
            } else {
                zzgow zzgowVar = (zzgow) list;
                if (zzgowVar.zzc()) {
                    zzgowVar.zzb();
                    return;
                }
                return;
            }
        }
        zzgrr.zzv(obj, j, unmodifiableList);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.ads.zzgpl
    public final void zzc(Object obj, Object obj2, long j) {
        List list = (List) zzgrr.zzh(obj2, j);
        List zzf = zzf(obj, j, list.size());
        int size = zzf.size();
        int size2 = list.size();
        if (size > 0 && size2 > 0) {
            zzf.addAll(list);
        }
        if (size > 0) {
            list = zzf;
        }
        zzgrr.zzv(obj, j, list);
    }
}
