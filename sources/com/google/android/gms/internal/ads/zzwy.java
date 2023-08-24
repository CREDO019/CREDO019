package com.google.android.gms.internal.ads;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzwy {
    private static final Comparator zza = new Comparator() { // from class: com.google.android.gms.internal.ads.zzwu
        @Override // java.util.Comparator
        public final int compare(Object obj, Object obj2) {
            return ((zzwx) obj).zza - ((zzwx) obj2).zza;
        }
    };
    private static final Comparator zzb = new Comparator() { // from class: com.google.android.gms.internal.ads.zzwv
        @Override // java.util.Comparator
        public final int compare(Object obj, Object obj2) {
            return Float.compare(((zzwx) obj).zzc, ((zzwx) obj2).zzc);
        }
    };
    private int zzf;
    private int zzg;
    private int zzh;
    private final zzwx[] zzd = new zzwx[5];
    private final ArrayList zzc = new ArrayList();
    private int zze = -1;

    public zzwy(int r1) {
    }

    public final float zza(float f) {
        if (this.zze != 0) {
            Collections.sort(this.zzc, zzb);
            this.zze = 0;
        }
        float f2 = this.zzg * 0.5f;
        int r1 = 0;
        for (int r0 = 0; r0 < this.zzc.size(); r0++) {
            zzwx zzwxVar = (zzwx) this.zzc.get(r0);
            r1 += zzwxVar.zzb;
            if (r1 >= f2) {
                return zzwxVar.zzc;
            }
        }
        if (this.zzc.isEmpty()) {
            return Float.NaN;
        }
        ArrayList arrayList = this.zzc;
        return ((zzwx) arrayList.get(arrayList.size() - 1)).zzc;
    }

    public final void zzb(int r4, float f) {
        zzwx zzwxVar;
        if (this.zze != 1) {
            Collections.sort(this.zzc, zza);
            this.zze = 1;
        }
        int r0 = this.zzh;
        if (r0 > 0) {
            zzwx[] zzwxVarArr = this.zzd;
            int r02 = r0 - 1;
            this.zzh = r02;
            zzwxVar = zzwxVarArr[r02];
        } else {
            zzwxVar = new zzwx(null);
        }
        int r1 = this.zzf;
        this.zzf = r1 + 1;
        zzwxVar.zza = r1;
        zzwxVar.zzb = r4;
        zzwxVar.zzc = f;
        this.zzc.add(zzwxVar);
        this.zzg += r4;
        while (true) {
            int r42 = this.zzg;
            if (r42 <= 2000) {
                return;
            }
            int r43 = r42 - 2000;
            zzwx zzwxVar2 = (zzwx) this.zzc.get(0);
            int r12 = zzwxVar2.zzb;
            if (r12 <= r43) {
                this.zzg -= r12;
                this.zzc.remove(0);
                int r44 = this.zzh;
                if (r44 < 5) {
                    zzwx[] zzwxVarArr2 = this.zzd;
                    this.zzh = r44 + 1;
                    zzwxVarArr2[r44] = zzwxVar2;
                }
            } else {
                zzwxVar2.zzb = r12 - r43;
                this.zzg -= r43;
            }
        }
    }

    public final void zzc() {
        this.zzc.clear();
        this.zze = -1;
        this.zzf = 0;
        this.zzg = 0;
    }
}
