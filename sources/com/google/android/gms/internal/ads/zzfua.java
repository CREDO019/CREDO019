package com.google.android.gms.internal.ads;

import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Map;
import javax.annotation.CheckForNull;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzfua extends AbstractSet {
    final /* synthetic */ zzfuf zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzfua(zzfuf zzfufVar) {
        this.zza = zzfufVar;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final void clear() {
        this.zza.clear();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean contains(@CheckForNull Object obj) {
        int zzv;
        Map zzl = this.zza.zzl();
        if (zzl != null) {
            return zzl.entrySet().contains(obj);
        }
        if (obj instanceof Map.Entry) {
            Map.Entry entry = (Map.Entry) obj;
            zzv = this.zza.zzv(entry.getKey());
            if (zzv != -1 && zzfsa.zza(zzfuf.zzj(this.zza, zzv), entry.getValue())) {
                return true;
            }
        }
        return false;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public final Iterator iterator() {
        zzfuf zzfufVar = this.zza;
        Map zzl = zzfufVar.zzl();
        if (zzl != null) {
            return zzl.entrySet().iterator();
        }
        return new zzfty(zzfufVar);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean remove(@CheckForNull Object obj) {
        int zzu;
        int[] zzz;
        Object[] zzA;
        Object[] zzB;
        Map zzl = this.zza.zzl();
        if (zzl != null) {
            return zzl.entrySet().remove(obj);
        }
        if (obj instanceof Map.Entry) {
            Map.Entry entry = (Map.Entry) obj;
            zzfuf zzfufVar = this.zza;
            if (zzfufVar.zzq()) {
                return false;
            }
            zzu = zzfufVar.zzu();
            Object key = entry.getKey();
            Object value = entry.getValue();
            Object zzk = zzfuf.zzk(this.zza);
            zzz = this.zza.zzz();
            zzA = this.zza.zzA();
            zzB = this.zza.zzB();
            int zzb = zzfug.zzb(key, value, zzu, zzk, zzz, zzA, zzB);
            if (zzb == -1) {
                return false;
            }
            this.zza.zzp(zzb, zzu);
            zzfuf.zzb(this.zza);
            this.zza.zzn();
            return true;
        }
        return false;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        return this.zza.size();
    }
}
