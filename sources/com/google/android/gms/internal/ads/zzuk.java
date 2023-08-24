package com.google.android.gms.internal.ads;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzuk extends zzum {
    private final zzwe zzd;
    private final zzfuv zze;
    private final zzde zzf;

    /* JADX INFO: Access modifiers changed from: protected */
    public zzuk(zzcp zzcpVar, int[] r6, int r7, zzwe zzweVar, long j, long j2, long j3, int r15, int r16, float f, float f2, List list, zzde zzdeVar) {
        super(zzcpVar, r6, 0);
        this.zzd = zzweVar;
        this.zze = zzfuv.zzm(list);
        this.zzf = zzdeVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* bridge */ /* synthetic */ zzfuv zzf(zzvp[] zzvpVarArr) {
        int r5;
        int r6;
        int[] r10;
        ArrayList arrayList = new ArrayList();
        char c = 0;
        int r2 = 0;
        while (true) {
            r5 = 2;
            r6 = 1;
            if (r2 >= 2) {
                break;
            }
            zzvp zzvpVar = zzvpVarArr[r2];
            if (zzvpVar == null || zzvpVar.zzb.length <= 1) {
                arrayList.add(null);
            } else {
                zzfus zzi = zzfuv.zzi();
                zzi.zze(new zzui(0L, 0L));
                arrayList.add(zzi);
            }
            r2++;
        }
        long[][] jArr = new long[2];
        for (int r7 = 0; r7 < 2; r7++) {
            zzvp zzvpVar2 = zzvpVarArr[r7];
            if (zzvpVar2 == null) {
                jArr[r7] = new long[0];
            } else {
                jArr[r7] = new long[zzvpVar2.zzb.length];
                int r9 = 0;
                while (true) {
                    if (r9 >= zzvpVar2.zzb.length) {
                        break;
                    }
                    jArr[r7][r9] = zzvpVar2.zza.zzb(r10[r9]).zzi;
                    r9++;
                }
                Arrays.sort(jArr[r7]);
            }
        }
        int[] r72 = new int[2];
        long[] jArr2 = new long[2];
        for (int r92 = 0; r92 < 2; r92++) {
            long[] jArr3 = jArr[r92];
            jArr2[r92] = jArr3.length == 0 ? 0L : jArr3[0];
        }
        zzg(arrayList, jArr2);
        zzfvg zza = zzfvy.zzc(zzfwd.zzc()).zzb(2).zza();
        int r4 = 0;
        while (r4 < r5) {
            int length = jArr[r4].length;
            if (length > r6) {
                double[] dArr = new double[length];
                int r11 = 0;
                while (true) {
                    long[] jArr4 = jArr[r4];
                    double d = 0.0d;
                    if (r11 >= jArr4.length) {
                        break;
                    }
                    long j = jArr4[r11];
                    if (j != -1) {
                        d = Math.log(j);
                    }
                    dArr[r11] = d;
                    r11++;
                }
                int r93 = length - 1;
                double d2 = dArr[r93] - dArr[c];
                int r112 = 0;
                while (r112 < r93) {
                    int r12 = r112 + 1;
                    zza.zzs(Double.valueOf(d2 == 0.0d ? 1.0d : (((dArr[r112] + dArr[r12]) * 0.5d) - dArr[c]) / d2), Integer.valueOf(r4));
                    r112 = r12;
                    c = 0;
                }
            }
            r4++;
            c = 0;
            r5 = 2;
            r6 = 1;
        }
        zzfuv zzm = zzfuv.zzm(zza.zzt());
        for (int r3 = 0; r3 < zzm.size(); r3++) {
            int intValue = ((Integer) zzm.get(r3)).intValue();
            int r52 = r72[intValue] + 1;
            r72[intValue] = r52;
            jArr2[intValue] = jArr[intValue][r52];
            zzg(arrayList, jArr2);
        }
        for (int r1 = 0; r1 < 2; r1++) {
            if (arrayList.get(r1) != null) {
                long j2 = jArr2[r1];
                jArr2[r1] = j2 + j2;
            }
        }
        zzg(arrayList, jArr2);
        zzfus zzi2 = zzfuv.zzi();
        for (int r22 = 0; r22 < arrayList.size(); r22++) {
            zzfus zzfusVar = (zzfus) arrayList.get(r22);
            zzi2.zze(zzfusVar == null ? zzfuv.zzo() : zzfusVar.zzg());
        }
        return zzi2.zzg();
    }

    private static void zzg(List list, long[] jArr) {
        long j = 0;
        for (int r3 = 0; r3 < 2; r3++) {
            j += jArr[r3];
        }
        for (int r0 = 0; r0 < list.size(); r0++) {
            zzfus zzfusVar = (zzfus) list.get(r0);
            if (zzfusVar != null) {
                zzfusVar.zze(new zzui(j, jArr[r0]));
            }
        }
    }
}
