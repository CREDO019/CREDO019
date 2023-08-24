package com.google.android.gms.internal.ads;

import java.util.Collection;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;
import java.util.SortedSet;
import javax.annotation.CheckForNull;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzfwq {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zza(Set set) {
        Iterator it = set.iterator();
        int r1 = 0;
        while (it.hasNext()) {
            Object next = it.next();
            r1 += next != null ? next.hashCode() : 0;
        }
        return r1;
    }

    /* JADX WARN: Type inference failed for: r1v2, types: [java.util.Collection, java.util.Set] */
    public static Set zzb(Set set, zzfsg zzfsgVar) {
        if (set instanceof SortedSet) {
            SortedSet sortedSet = (SortedSet) set;
            if (sortedSet instanceof zzfwn) {
                zzfwn zzfwnVar = (zzfwn) sortedSet;
                return new zzfwo((SortedSet) zzfwnVar.zza, zzfsj.zza(zzfwnVar.zzb, zzfsgVar));
            }
            Objects.requireNonNull(sortedSet);
            Objects.requireNonNull(zzfsgVar);
            return new zzfwo(sortedSet, zzfsgVar);
        } else if (set instanceof zzfwn) {
            zzfwn zzfwnVar2 = (zzfwn) set;
            return new zzfwn(zzfwnVar2.zza, zzfsj.zza(zzfwnVar2.zzb, zzfsgVar));
        } else {
            Objects.requireNonNull(set);
            Objects.requireNonNull(zzfsgVar);
            return new zzfwn(set, zzfsgVar);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean zzc(Set set, @CheckForNull Object obj) {
        if (set == obj) {
            return true;
        }
        if (obj instanceof Set) {
            Set set2 = (Set) obj;
            try {
                if (set.size() == set2.size()) {
                    if (set.containsAll(set2)) {
                        return true;
                    }
                }
            } catch (ClassCastException | NullPointerException unused) {
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean zze(Set set, Iterator it) {
        boolean z = false;
        while (it.hasNext()) {
            z |= set.remove(it.next());
        }
        return z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean zzd(Set set, Collection collection) {
        Objects.requireNonNull(collection);
        if (collection instanceof zzfwa) {
            collection = ((zzfwa) collection).zza();
        }
        if (!(collection instanceof Set) || collection.size() <= set.size()) {
            return zze(set, collection.iterator());
        }
        Iterator it = set.iterator();
        Objects.requireNonNull(collection);
        boolean z = false;
        while (it.hasNext()) {
            if (collection.contains(it.next())) {
                it.remove();
                z = true;
            }
        }
        return z;
    }
}
