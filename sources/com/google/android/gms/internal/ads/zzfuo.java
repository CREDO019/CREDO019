package com.google.android.gms.internal.ads;

import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
class zzfuo extends zzfup {
    Object[] zza;
    int zzb = 0;
    boolean zzc;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzfuo(int r1) {
        this.zza = new Object[r1];
    }

    private final void zze(int r4) {
        Object[] objArr = this.zza;
        int length = objArr.length;
        if (length < r4) {
            this.zza = Arrays.copyOf(objArr, zzd(length, r4));
            this.zzc = false;
        } else if (this.zzc) {
            this.zza = (Object[]) objArr.clone();
            this.zzc = false;
        }
    }

    @Override // com.google.android.gms.internal.ads.zzfup
    public /* bridge */ /* synthetic */ zzfup zzb(Object obj) {
        throw null;
    }

    public final zzfup zzc(Iterable iterable) {
        if (iterable instanceof Collection) {
            Collection collection = (Collection) iterable;
            zze(this.zzb + collection.size());
            if (collection instanceof zzfuq) {
                this.zzb = ((zzfuq) collection).zza(this.zza, this.zzb);
                return this;
            }
        }
        for (Object obj : iterable) {
            zzb(obj);
        }
        return this;
    }

    public final zzfuo zza(Object obj) {
        Objects.requireNonNull(obj);
        zze(this.zzb + 1);
        Object[] objArr = this.zza;
        int r1 = this.zzb;
        this.zzb = r1 + 1;
        objArr[r1] = obj;
        return this;
    }
}
