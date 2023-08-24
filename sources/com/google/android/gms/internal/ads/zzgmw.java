package com.google.android.gms.internal.ads;

import java.util.Comparator;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzgmw implements Comparator {
    @Override // java.util.Comparator
    public final /* synthetic */ int compare(Object obj, Object obj2) {
        zzgnf zzgnfVar = (zzgnf) obj;
        zzgnf zzgnfVar2 = (zzgnf) obj2;
        zzgmz it = zzgnfVar.iterator();
        zzgmz it2 = zzgnfVar2.iterator();
        while (it.hasNext() && it2.hasNext()) {
            int compareTo = Integer.valueOf(it.zza() & 255).compareTo(Integer.valueOf(it2.zza() & 255));
            if (compareTo != 0) {
                return compareTo;
            }
        }
        return Integer.valueOf(zzgnfVar.zzd()).compareTo(Integer.valueOf(zzgnfVar2.zzd()));
    }
}
