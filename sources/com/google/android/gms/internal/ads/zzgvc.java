package com.google.android.gms.internal.ads;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzgvc implements zzgur {
    private static final zzgur zza = zzgus.zza(Collections.emptySet());
    private final List zzb;
    private final List zzc;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzgvc(List list, List list2, zzgva zzgvaVar) {
        this.zzb = list;
        this.zzc = list2;
    }

    public static zzgvb zza(int r2, int r3) {
        return new zzgvb(r2, r3, null);
    }

    @Override // com.google.android.gms.internal.ads.zzgve
    /* renamed from: zzc */
    public final Set zzb() {
        int size = this.zzb.size();
        ArrayList arrayList = new ArrayList(this.zzc.size());
        int size2 = this.zzc.size();
        for (int r4 = 0; r4 < size2; r4++) {
            Collection collection = (Collection) ((zzgve) this.zzc.get(r4)).zzb();
            size += collection.size();
            arrayList.add(collection);
        }
        HashSet zza2 = zzguo.zza(size);
        int size3 = this.zzb.size();
        for (int r42 = 0; r42 < size3; r42++) {
            Object zzb = ((zzgve) this.zzb.get(r42)).zzb();
            Objects.requireNonNull(zzb);
            zza2.add(zzb);
        }
        int size4 = arrayList.size();
        for (int r3 = 0; r3 < size4; r3++) {
            for (Object obj : (Collection) arrayList.get(r3)) {
                Objects.requireNonNull(obj);
                zza2.add(obj);
            }
        }
        return Collections.unmodifiableSet(zza2);
    }
}
