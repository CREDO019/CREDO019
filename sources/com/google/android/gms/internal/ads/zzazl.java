package com.google.android.gms.internal.ads;

import java.util.Arrays;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzazl {
    private int zzb;
    private int zzc;
    private int zzd = 0;
    private zzazf[] zze = new zzazf[100];
    private final zzazf[] zza = new zzazf[1];

    public zzazl(boolean z, int r2) {
    }

    public final synchronized int zza() {
        return this.zzc * 65536;
    }

    public final synchronized zzazf zzb() {
        zzazf zzazfVar;
        this.zzc++;
        int r0 = this.zzd;
        if (r0 > 0) {
            zzazf[] zzazfVarArr = this.zze;
            int r02 = r0 - 1;
            this.zzd = r02;
            zzazfVar = zzazfVarArr[r02];
            zzazfVarArr[r02] = null;
        } else {
            zzazfVar = new zzazf(new byte[65536], 0);
        }
        return zzazfVar;
    }

    public final synchronized void zzc(zzazf zzazfVar) {
        zzazf[] zzazfVarArr = this.zza;
        zzazfVarArr[0] = zzazfVar;
        zzd(zzazfVarArr);
    }

    public final synchronized void zzd(zzazf[] zzazfVarArr) {
        int length = this.zzd + zzazfVarArr.length;
        zzazf[] zzazfVarArr2 = this.zze;
        int length2 = zzazfVarArr2.length;
        if (length >= length2) {
            this.zze = (zzazf[]) Arrays.copyOf(zzazfVarArr2, Math.max(length2 + length2, length));
        }
        for (zzazf zzazfVar : zzazfVarArr) {
            byte[] bArr = zzazfVar.zza;
            zzazf[] zzazfVarArr3 = this.zze;
            int r3 = this.zzd;
            this.zzd = r3 + 1;
            zzazfVarArr3[r3] = zzazfVar;
        }
        this.zzc -= zzazfVarArr.length;
        notifyAll();
    }

    public final synchronized void zze() {
        zzf(0);
    }

    public final synchronized void zzf(int r2) {
        int r0 = this.zzb;
        this.zzb = r2;
        if (r2 < r0) {
            zzg();
        }
    }

    public final synchronized void zzg() {
        int max = Math.max(0, zzban.zzd(this.zzb, 65536) - this.zzc);
        int r1 = this.zzd;
        if (max >= r1) {
            return;
        }
        Arrays.fill(this.zze, max, r1, (Object) null);
        this.zzd = max;
    }
}
