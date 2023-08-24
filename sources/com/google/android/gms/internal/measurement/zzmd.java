package com.google.android.gms.internal.measurement;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-measurement-base@@20.1.2 */
/* loaded from: classes3.dex */
final class zzmd implements Iterator {
    final /* synthetic */ zzmh zza;
    private int zzb = -1;
    private boolean zzc;
    private Iterator zzd;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzmd(zzmh zzmhVar, zzmc zzmcVar) {
        this.zza = zzmhVar;
    }

    private final Iterator zza() {
        Map map;
        if (this.zzd == null) {
            map = this.zza.zzc;
            this.zzd = map.entrySet().iterator();
        }
        return this.zzd;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        List list;
        Map map;
        int r0 = this.zzb + 1;
        list = this.zza.zzb;
        if (r0 >= list.size()) {
            map = this.zza.zzc;
            return !map.isEmpty() && zza().hasNext();
        }
        return true;
    }

    @Override // java.util.Iterator
    public final /* bridge */ /* synthetic */ Object next() {
        List list;
        List list2;
        this.zzc = true;
        int r1 = this.zzb + 1;
        this.zzb = r1;
        list = this.zza.zzb;
        if (r1 < list.size()) {
            list2 = this.zza.zzb;
            return (Map.Entry) list2.get(this.zzb);
        }
        return (Map.Entry) zza().next();
    }

    @Override // java.util.Iterator
    public final void remove() {
        List list;
        if (!this.zzc) {
            throw new IllegalStateException("remove() was called before next()");
        }
        this.zzc = false;
        this.zza.zzn();
        int r0 = this.zzb;
        list = this.zza.zzb;
        if (r0 < list.size()) {
            zzmh zzmhVar = this.zza;
            int r1 = this.zzb;
            this.zzb = r1 - 1;
            zzmhVar.zzl(r1);
            return;
        }
        zza().remove();
    }
}
