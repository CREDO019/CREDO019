package com.google.android.gms.internal.ads;

import java.util.Iterator;
import java.util.Map;
import javax.annotation.CheckForNull;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzftb extends zzfvm {
    final /* synthetic */ zzftd zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzftb(zzftd zzftdVar) {
        this.zza = zzftdVar;
    }

    @Override // com.google.android.gms.internal.ads.zzfvm, java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean contains(@CheckForNull Object obj) {
        return zzftw.zza(this.zza.zza.entrySet(), obj);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public final Iterator iterator() {
        return new zzftc(this.zza);
    }

    @Override // com.google.android.gms.internal.ads.zzfvm, java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean remove(@CheckForNull Object obj) {
        if (contains(obj)) {
            Map.Entry entry = (Map.Entry) obj;
            entry.getClass();
            zzftq.zzq(this.zza.zzb, entry.getKey());
            return true;
        }
        return false;
    }

    @Override // com.google.android.gms.internal.ads.zzfvm
    final Map zza() {
        return this.zza;
    }
}
