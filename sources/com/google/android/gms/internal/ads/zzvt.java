package com.google.android.gms.internal.ads;

import android.util.Pair;
import java.util.Arrays;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public abstract class zzvt extends zzvw {
    private zzvs zza;

    protected abstract Pair zzb(zzvs zzvsVar, int[][][] r2, int[] r3, zzsg zzsgVar, zzcn zzcnVar) throws zzgy;

    @Override // com.google.android.gms.internal.ads.zzvw
    public final zzvx zzn(zzjz[] zzjzVarArr, zzue zzueVar, zzsg zzsgVar, zzcn zzcnVar) throws zzgy {
        boolean z;
        int[] r6;
        int[] r2 = new int[3];
        zzcp[][] zzcpVarArr = new zzcp[3];
        int[][][] r11 = new int[3][];
        for (int r4 = 0; r4 < 3; r4++) {
            int r5 = zzueVar.zzc;
            zzcpVarArr[r4] = new zzcp[r5];
            r11[r4] = new int[r5];
        }
        int r1 = 2;
        int[] r13 = new int[2];
        for (int r42 = 0; r42 < 2; r42++) {
            r13[r42] = zzjzVarArr[r42].zze();
        }
        int r43 = 0;
        while (r43 < zzueVar.zzc) {
            zzcp zzb = zzueVar.zzb(r43);
            int r62 = zzb.zzd;
            int r7 = 0;
            int r8 = 2;
            int r9 = 0;
            boolean z2 = true;
            while (r7 < r1) {
                zzjz zzjzVar = zzjzVarArr[r7];
                int r12 = 0;
                int r14 = 0;
                while (true) {
                    int r15 = zzb.zzb;
                    if (r12 > 0) {
                        break;
                    }
                    r14 = Math.max(r14, zzjzVar.zzO(zzb.zzb(r12)) & 7);
                    r12++;
                }
                boolean z3 = r2[r7] == 0;
                if (r14 > r9) {
                    z2 = z3;
                    r8 = r7;
                    r9 = r14;
                } else if (r14 == r9 && r62 == 5 && !z2 && z3) {
                    r8 = r7;
                    r9 = r14;
                    z2 = true;
                }
                r7++;
                r1 = 2;
            }
            if (r8 == r1) {
                int r16 = zzb.zzb;
                r6 = new int[1];
            } else {
                zzjz zzjzVar2 = zzjzVarArr[r8];
                int r72 = zzb.zzb;
                int[] r73 = new int[1];
                int r17 = 0;
                while (true) {
                    int r92 = zzb.zzb;
                    if (r17 > 0) {
                        break;
                    }
                    r73[r17] = zzjzVar2.zzO(zzb.zzb(r17));
                    r17++;
                }
                r6 = r73;
            }
            int r18 = r2[r8];
            zzcpVarArr[r8][r18] = zzb;
            r11[r8][r18] = r6;
            r2[r8] = r18 + 1;
            r43++;
            r1 = 2;
        }
        zzue[] zzueVarArr = new zzue[r1];
        String[] strArr = new String[r1];
        int[] r63 = new int[r1];
        int r0 = 0;
        while (r0 < r1) {
            int r19 = r2[r0];
            zzueVarArr[r0] = new zzue((zzcp[]) zzel.zzae(zzcpVarArr[r0], r19));
            r11[r0] = (int[][]) zzel.zzae(r11[r0], r19);
            strArr[r0] = zzjzVarArr[r0].zzK();
            r63[r0] = zzjzVarArr[r0].zzb();
            r0++;
            r1 = 2;
        }
        zzvs zzvsVar = new zzvs(strArr, r63, zzueVarArr, r13, r11, new zzue((zzcp[]) zzel.zzae(zzcpVarArr[2], r2[2])));
        Pair zzb2 = zzb(zzvsVar, r11, r13, zzsgVar, zzcnVar);
        zzvu[] zzvuVarArr = (zzvu[]) zzb2.second;
        List[] listArr = new List[zzvuVarArr.length];
        for (int r44 = 0; r44 < zzvuVarArr.length; r44++) {
            zzvu zzvuVar = zzvuVarArr[r44];
            listArr[r44] = zzvuVar != null ? zzfuv.zzp(zzvuVar) : zzfuv.zzo();
        }
        zzfus zzfusVar = new zzfus();
        for (int r45 = 0; r45 < 2; r45++) {
            zzue zzd = zzvsVar.zzd(r45);
            List list = listArr[r45];
            for (int r82 = 0; r82 < zzd.zzc; r82++) {
                zzcp zzb3 = zzd.zzb(r82);
                boolean z4 = zzvsVar.zza(r45, r82, false) != 0;
                int r112 = zzb3.zzb;
                int[] r122 = new int[1];
                boolean[] zArr = new boolean[1];
                int r113 = 0;
                while (true) {
                    int r142 = zzb3.zzb;
                    if (r113 <= 0) {
                        r122[r113] = zzvsVar.zzb(r45, r82, r113) & 7;
                        int r143 = 0;
                        while (true) {
                            if (r143 >= list.size()) {
                                z = false;
                                break;
                            }
                            zzvu zzvuVar2 = (zzvu) list.get(r143);
                            if (zzvuVar2.zze().equals(zzb3) && zzvuVar2.zzb(r113) != -1) {
                                z = true;
                                break;
                            }
                            r143++;
                        }
                        zArr[r113] = z;
                        r113++;
                    }
                }
                zzfusVar.zze(new zzcx(zzb3, z4, r122, zArr));
            }
        }
        zzue zze = zzvsVar.zze();
        for (int r10 = 0; r10 < zze.zzc; r10++) {
            zzcp zzb4 = zze.zzb(r10);
            int r52 = zzb4.zzb;
            int[] r64 = new int[1];
            Arrays.fill(r64, 0);
            int r83 = zzb4.zzb;
            zzfusVar.zze(new zzcx(zzb4, false, r64, new boolean[1]));
        }
        return new zzvx((zzka[]) zzb2.first, (zzvq[]) zzb2.second, new zzcy(zzfusVar.zzg()), zzvsVar);
    }

    @Override // com.google.android.gms.internal.ads.zzvw
    public final void zzo(Object obj) {
        this.zza = (zzvs) obj;
    }
}
