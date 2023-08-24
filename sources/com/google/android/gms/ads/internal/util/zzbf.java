package com.google.android.gms.ads.internal.util;

import java.util.ArrayList;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzbf {
    private final String[] zza;
    private final double[] zzb;
    private final double[] zzc;
    private final int[] zzd;
    private int zze;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzbf(zzbd zzbdVar, zzbe zzbeVar) {
        List list;
        List list2;
        List list3;
        List list4;
        list = zzbdVar.zzb;
        int size = list.size();
        list2 = zzbdVar.zza;
        this.zza = (String[]) list2.toArray(new String[size]);
        list3 = zzbdVar.zzb;
        this.zzb = zzc(list3);
        list4 = zzbdVar.zzc;
        this.zzc = zzc(list4);
        this.zzd = new int[size];
        this.zze = 0;
    }

    private static final double[] zzc(List list) {
        int size = list.size();
        double[] dArr = new double[size];
        for (int r2 = 0; r2 < size; r2++) {
            dArr[r2] = ((Double) list.get(r2)).doubleValue();
        }
        return dArr;
    }

    public final List zza() {
        ArrayList arrayList = new ArrayList(this.zza.length);
        int r1 = 0;
        while (true) {
            String[] strArr = this.zza;
            if (r1 >= strArr.length) {
                return arrayList;
            }
            String str = strArr[r1];
            double d = this.zzc[r1];
            double d2 = this.zzb[r1];
            int r12 = this.zzd[r1];
            arrayList.add(new zzbc(str, d, d2, r12 / this.zze, r12));
            r1++;
        }
    }

    public final void zzb(double d) {
        this.zze++;
        int r0 = 0;
        while (true) {
            double[] dArr = this.zzc;
            if (r0 >= dArr.length) {
                return;
            }
            double d2 = dArr[r0];
            if (d2 <= d && d < this.zzb[r0]) {
                int[] r1 = this.zzd;
                r1[r0] = r1[r0] + 1;
            }
            if (d < d2) {
                return;
            }
            r0++;
        }
    }
}
