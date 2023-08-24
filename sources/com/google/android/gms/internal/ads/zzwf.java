package com.google.android.gms.internal.ads;

import java.util.Arrays;
import java.util.Objects;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzwf {
    private int zza;
    private int zzb;
    private int zzc = 0;
    private zzvy[] zzd = new zzvy[100];

    public zzwf(boolean z, int r2) {
    }

    public final synchronized int zza() {
        return this.zzb * 65536;
    }

    public final synchronized zzvy zzb() {
        zzvy zzvyVar;
        this.zzb++;
        int r0 = this.zzc;
        if (r0 > 0) {
            zzvy[] zzvyVarArr = this.zzd;
            int r02 = r0 - 1;
            this.zzc = r02;
            zzvyVar = zzvyVarArr[r02];
            Objects.requireNonNull(zzvyVar);
            zzvyVarArr[r02] = null;
        } else {
            zzvyVar = new zzvy(new byte[65536], 0);
            int r03 = this.zzb;
            zzvy[] zzvyVarArr2 = this.zzd;
            int length = zzvyVarArr2.length;
            if (r03 > length) {
                this.zzd = (zzvy[]) Arrays.copyOf(zzvyVarArr2, length + length);
                return zzvyVar;
            }
        }
        return zzvyVar;
    }

    public final synchronized void zzc(zzvy zzvyVar) {
        zzvy[] zzvyVarArr = this.zzd;
        int r1 = this.zzc;
        this.zzc = r1 + 1;
        zzvyVarArr[r1] = zzvyVar;
        this.zzb--;
        notifyAll();
    }

    public final synchronized void zzd(zzvz zzvzVar) {
        while (zzvzVar != null) {
            zzvy[] zzvyVarArr = this.zzd;
            int r1 = this.zzc;
            this.zzc = r1 + 1;
            zzvyVarArr[r1] = zzvzVar.zzc();
            this.zzb--;
            zzvzVar = zzvzVar.zzd();
        }
        notifyAll();
    }

    public final synchronized void zze() {
        zzf(0);
    }

    public final synchronized void zzf(int r2) {
        int r0 = this.zza;
        this.zza = r2;
        if (r2 < r0) {
            zzg();
        }
    }

    public final synchronized void zzg() {
        int max = Math.max(0, zzel.zze(this.zza, 65536) - this.zzb);
        int r1 = this.zzc;
        if (max >= r1) {
            return;
        }
        Arrays.fill(this.zzd, max, r1, (Object) null);
        this.zzc = max;
    }
}
