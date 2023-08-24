package com.google.android.gms.internal.ads;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;
import java.util.RandomAccess;
import java.util.Set;
import java.util.SortedMap;
import javax.annotation.CheckForNull;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public abstract class zzftq extends zzftt implements Serializable {
    private final transient Map zza;
    private transient int zzb;

    /* JADX INFO: Access modifiers changed from: protected */
    public zzftq(Map map) {
        zzfsf.zze(map.isEmpty());
        this.zza = map;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ int zzd(zzftq zzftqVar) {
        int r0 = zzftqVar.zzb;
        zzftqVar.zzb = r0 + 1;
        return r0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ int zze(zzftq zzftqVar) {
        int r0 = zzftqVar.zzb;
        zzftqVar.zzb = r0 - 1;
        return r0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ int zzf(zzftq zzftqVar, int r2) {
        int r0 = zzftqVar.zzb + r2;
        zzftqVar.zzb = r0;
        return r0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ int zzg(zzftq zzftqVar, int r2) {
        int r0 = zzftqVar.zzb - r2;
        zzftqVar.zzb = r0;
        return r0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zzq(zzftq zzftqVar, Object obj) {
        Object obj2;
        try {
            obj2 = zzftqVar.zza.remove(obj);
        } catch (ClassCastException | NullPointerException unused) {
            obj2 = null;
        }
        Collection collection = (Collection) obj2;
        if (collection != null) {
            int size = collection.size();
            collection.clear();
            zzftqVar.zzb -= size;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract Collection zza();

    /* JADX INFO: Access modifiers changed from: package-private */
    public Collection zzb(Collection collection) {
        throw null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Collection zzc(Object obj, Collection collection) {
        throw null;
    }

    @Override // com.google.android.gms.internal.ads.zzfvr
    public final int zzh() {
        return this.zzb;
    }

    @Override // com.google.android.gms.internal.ads.zzftt
    final Collection zzi() {
        return new zzfts(this);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.ads.zzftt
    public final Iterator zzj() {
        return new zzfta(this);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final List zzk(Object obj, List list, @CheckForNull zzftn zzftnVar) {
        if (list instanceof RandomAccess) {
            return new zzftj(this, obj, list, zzftnVar);
        }
        return new zzftp(this, obj, list, zzftnVar);
    }

    @Override // com.google.android.gms.internal.ads.zzftt
    Map zzm() {
        throw null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Map zzn() {
        Map map = this.zza;
        if (map instanceof NavigableMap) {
            return new zzfth(this, (NavigableMap) map);
        }
        if (map instanceof SortedMap) {
            return new zzftk(this, (SortedMap) map);
        }
        return new zzftd(this, map);
    }

    @Override // com.google.android.gms.internal.ads.zzftt
    Set zzo() {
        throw null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Set zzp() {
        Map map = this.zza;
        if (map instanceof NavigableMap) {
            return new zzfti(this, (NavigableMap) map);
        }
        if (map instanceof SortedMap) {
            return new zzftl(this, (SortedMap) map);
        }
        return new zzftg(this, map);
    }

    @Override // com.google.android.gms.internal.ads.zzfvr
    public final void zzr() {
        for (Collection collection : this.zza.values()) {
            collection.clear();
        }
        this.zza.clear();
        this.zzb = 0;
    }

    @Override // com.google.android.gms.internal.ads.zzftt, com.google.android.gms.internal.ads.zzfvr
    public final boolean zzs(Object obj, Object obj2) {
        Collection collection = (Collection) this.zza.get(obj);
        if (collection == null) {
            Collection zza = zza();
            if (zza.add(obj2)) {
                this.zzb++;
                this.zza.put(obj, zza);
                return true;
            }
            throw new AssertionError("New Collection violated the Collection spec");
        } else if (collection.add(obj2)) {
            this.zzb++;
            return true;
        } else {
            return false;
        }
    }
}
