package com.google.android.gms.internal.ads;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzcks implements Iterable {
    private final List zza = new ArrayList();

    @Override // java.lang.Iterable
    public final Iterator iterator() {
        return this.zza.iterator();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final zzckr zza(zzciw zzciwVar) {
        Iterator it = iterator();
        while (it.hasNext()) {
            zzckr zzckrVar = (zzckr) it.next();
            if (zzckrVar.zza == zzciwVar) {
                return zzckrVar;
            }
        }
        return null;
    }

    public final void zzb(zzckr zzckrVar) {
        this.zza.add(zzckrVar);
    }

    public final void zzc(zzckr zzckrVar) {
        this.zza.remove(zzckrVar);
    }

    public final boolean zzd(zzciw zzciwVar) {
        ArrayList<zzckr> arrayList = new ArrayList();
        Iterator it = iterator();
        while (it.hasNext()) {
            zzckr zzckrVar = (zzckr) it.next();
            if (zzckrVar.zza == zzciwVar) {
                arrayList.add(zzckrVar);
            }
        }
        if (arrayList.isEmpty()) {
            return false;
        }
        for (zzckr zzckrVar2 : arrayList) {
            zzckrVar2.zzb.zzb();
        }
        return true;
    }
}
