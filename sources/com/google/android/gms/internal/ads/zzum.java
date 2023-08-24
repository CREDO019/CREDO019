package com.google.android.gms.internal.ads;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public class zzum implements zzvq {
    protected final zzcp zza;
    protected final int zzb;
    protected final int[] zzc;
    private final zzaf[] zzd;
    private int zze;

    public zzum(zzcp zzcpVar, int[] r5, int r6) {
        int length = r5.length;
        zzdd.zzf(length > 0);
        Objects.requireNonNull(zzcpVar);
        this.zza = zzcpVar;
        this.zzb = length;
        this.zzd = new zzaf[length];
        for (int r62 = 0; r62 < r5.length; r62++) {
            this.zzd[r62] = zzcpVar.zzb(r5[r62]);
        }
        Arrays.sort(this.zzd, new Comparator() { // from class: com.google.android.gms.internal.ads.zzul
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return ((zzaf) obj2).zzi - ((zzaf) obj).zzi;
            }
        });
        this.zzc = new int[this.zzb];
        for (int r0 = 0; r0 < this.zzb; r0++) {
            this.zzc[r0] = zzcpVar.zza(this.zzd[r0]);
        }
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            zzum zzumVar = (zzum) obj;
            if (this.zza == zzumVar.zza && Arrays.equals(this.zzc, zzumVar.zzc)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        int r0 = this.zze;
        if (r0 == 0) {
            int identityHashCode = (System.identityHashCode(this.zza) * 31) + Arrays.hashCode(this.zzc);
            this.zze = identityHashCode;
            return identityHashCode;
        }
        return r0;
    }

    @Override // com.google.android.gms.internal.ads.zzvu
    public final int zza(int r2) {
        return this.zzc[0];
    }

    @Override // com.google.android.gms.internal.ads.zzvu
    public final int zzb(int r3) {
        for (int r0 = 0; r0 < this.zzb; r0++) {
            if (this.zzc[r0] == r3) {
                return r0;
            }
        }
        return -1;
    }

    @Override // com.google.android.gms.internal.ads.zzvu
    public final int zzc() {
        return this.zzc.length;
    }

    @Override // com.google.android.gms.internal.ads.zzvu
    public final zzaf zzd(int r2) {
        return this.zzd[r2];
    }

    @Override // com.google.android.gms.internal.ads.zzvu
    public final zzcp zze() {
        return this.zza;
    }
}
