package com.google.android.gms.internal.ads;

import java.util.Iterator;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzguh implements Iterator {
    int zza = 0;
    final /* synthetic */ zzgui zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzguh(zzgui zzguiVar) {
        this.zzb = zzguiVar;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.zza < this.zzb.zza.size() || this.zzb.zzb.hasNext();
    }

    @Override // java.util.Iterator
    public final Object next() {
        if (this.zza < this.zzb.zza.size()) {
            List list = this.zzb.zza;
            int r1 = this.zza;
            this.zza = r1 + 1;
            return list.get(r1);
        }
        zzgui zzguiVar = this.zzb;
        zzguiVar.zza.add(zzguiVar.zzb.next());
        return next();
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException();
    }
}
