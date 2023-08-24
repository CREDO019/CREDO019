package com.google.android.gms.internal.ads;

import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.RandomAccess;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzfvb {
    public static boolean zza(Iterable iterable, zzfsg zzfsgVar) {
        if (!(iterable instanceof RandomAccess) || !(iterable instanceof List)) {
            Iterator it = iterable.iterator();
            Objects.requireNonNull(zzfsgVar);
            boolean z = false;
            while (it.hasNext()) {
                if (zzfsgVar.zza(it.next())) {
                    it.remove();
                    z = true;
                }
            }
            return z;
        }
        Objects.requireNonNull(zzfsgVar);
        return zzc((List) iterable, zzfsgVar);
    }

    private static void zzb(List list, zzfsg zzfsgVar, int r4, int r5) {
        int size = list.size();
        while (true) {
            size--;
            if (size <= r5) {
                break;
            } else if (zzfsgVar.zza(list.get(size))) {
                list.remove(size);
            }
        }
        while (true) {
            r5--;
            if (r5 < r4) {
                return;
            }
            list.remove(r5);
        }
    }

    private static boolean zzc(List list, zzfsg zzfsgVar) {
        int r1 = 0;
        int r2 = 0;
        while (r1 < list.size()) {
            Object obj = list.get(r1);
            if (!zzfsgVar.zza(obj)) {
                if (r1 > r2) {
                    try {
                        list.set(r2, obj);
                    } catch (IllegalArgumentException unused) {
                        zzb(list, zzfsgVar, r2, r1);
                        return true;
                    } catch (UnsupportedOperationException unused2) {
                        zzb(list, zzfsgVar, r2, r1);
                        return true;
                    }
                }
                r2++;
            }
            r1++;
        }
        list.subList(r2, list.size()).clear();
        return r1 != r2;
    }
}
