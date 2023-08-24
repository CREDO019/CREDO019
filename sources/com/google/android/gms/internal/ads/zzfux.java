package com.google.android.gms.internal.ads;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzfux {
    Object[] zza;
    int zzb;
    zzfuw zzc;

    public zzfux() {
        this(4);
    }

    private final void zzd(int r3) {
        int r32 = r3 + r3;
        Object[] objArr = this.zza;
        int length = objArr.length;
        if (r32 > length) {
            this.zza = Arrays.copyOf(objArr, zzfup.zzd(length, r32));
        }
    }

    public final zzfux zza(Object obj, Object obj2) {
        zzd(this.zzb + 1);
        zzftu.zzb(obj, obj2);
        Object[] objArr = this.zza;
        int r1 = this.zzb;
        int r2 = r1 + r1;
        objArr[r2] = obj;
        objArr[r2 + 1] = obj2;
        this.zzb = r1 + 1;
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final zzfux zzb(Iterable iterable) {
        if (iterable instanceof Collection) {
            zzd(this.zzb + iterable.size());
        }
        Iterator it = iterable.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            zza(entry.getKey(), entry.getValue());
        }
        return this;
    }

    public final zzfuy zzc() {
        zzfuw zzfuwVar = this.zzc;
        if (zzfuwVar == null) {
            zzfwj zzi = zzfwj.zzi(this.zzb, this.zza, this);
            zzfuw zzfuwVar2 = this.zzc;
            if (zzfuwVar2 == null) {
                return zzi;
            }
            throw zzfuwVar2.zza();
        }
        throw zzfuwVar.zza();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzfux(int r1) {
        this.zza = new Object[r1 + r1];
        this.zzb = 0;
    }
}
