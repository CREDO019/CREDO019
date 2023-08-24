package com.google.android.gms.internal.ads;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzayv extends zzayz {
    private final AtomicReference zza;

    public zzayv() {
        this(null);
    }

    protected static boolean zza(int r3, boolean z) {
        int r32 = r3 & 3;
        return r32 == 3 || (z && r32 == 2);
    }

    private static int zzh(int r1, int r2) {
        if (r1 == -1) {
            return r2 != -1 ? -1 : 0;
        } else if (r2 == -1) {
            return 1;
        } else {
            return r1 - r2;
        }
    }

    @Override // com.google.android.gms.internal.ads.zzayz
    protected final zzayt[] zzb(zzasy[] zzasyVarArr, zzayp[] zzaypVarArr, int[][][] r23) throws zzase {
        zzayo zzayoVar;
        int[] r19;
        ArrayList arrayList;
        boolean z;
        boolean z2;
        int r0;
        int zzh;
        int r02 = 2;
        zzayt[] zzaytVarArr = new zzayt[2];
        zzayu zzayuVar = (zzayu) this.zza.get();
        int r5 = 0;
        boolean z3 = false;
        while (true) {
            int r8 = -1;
            int r9 = 1;
            if (r5 >= r02) {
                break;
            }
            if (zzasyVarArr[r5].zzc() == r02) {
                if (!z3) {
                    zzasy zzasyVar = zzasyVarArr[r5];
                    zzayp zzaypVar = zzaypVarArr[r5];
                    int[][] r10 = r23[r5];
                    int r11 = zzayuVar.zzd;
                    int r112 = zzayuVar.zze;
                    int r113 = zzayuVar.zzf;
                    boolean z4 = zzayuVar.zzc;
                    boolean z5 = zzayuVar.zzb;
                    int r114 = zzayuVar.zzi;
                    int r115 = zzayuVar.zzj;
                    boolean z6 = zzayuVar.zzk;
                    boolean z7 = zzayuVar.zzg;
                    boolean z8 = zzayuVar.zzh;
                    int r116 = -1;
                    int r12 = 0;
                    zzayo zzayoVar2 = null;
                    int r14 = 0;
                    int r15 = 0;
                    while (r12 < zzaypVar.zzb) {
                        zzayo zzb = zzaypVar.zzb(r12);
                        int r4 = zzb.zza;
                        ArrayList arrayList2 = new ArrayList(r9);
                        int r42 = 0;
                        while (true) {
                            int r92 = zzb.zza;
                            if (r42 > 0) {
                                break;
                            }
                            arrayList2.add(Integer.valueOf(r42));
                            r42++;
                        }
                        int[] r43 = r10[r12];
                        int r93 = 0;
                        while (true) {
                            int r2 = zzb.zza;
                            if (r93 <= 0) {
                                zzayp zzaypVar2 = zzaypVar;
                                if (zza(r43[r93], true)) {
                                    zzass zzb2 = zzb.zzb(r93);
                                    if (arrayList2.contains(Integer.valueOf(r93))) {
                                        int r6 = zzb2.zzj;
                                        int r62 = zzb2.zzk;
                                        int r63 = zzb2.zzb;
                                        zzayoVar = zzb;
                                        z = true;
                                        z2 = true;
                                    } else {
                                        zzayoVar = zzb;
                                        z = true;
                                        z2 = false;
                                    }
                                    if (z != z2) {
                                        arrayList = arrayList2;
                                        r0 = 1;
                                    } else {
                                        arrayList = arrayList2;
                                        r0 = 2;
                                    }
                                    r19 = r43;
                                    boolean zza = zza(r43[r93], false);
                                    if (zza) {
                                        r0 += 1000;
                                    }
                                    boolean z9 = r0 > r15;
                                    if (r0 == r15) {
                                        if (zzb2.zza() != r116) {
                                            zzh = zzh(zzb2.zza(), r116);
                                        } else {
                                            zzh = zzh(zzb2.zzb, r8);
                                        }
                                        z9 = !(zza && z2) ? zzh >= 0 : zzh <= 0;
                                    }
                                    if (z9) {
                                        r8 = zzb2.zzb;
                                        r116 = zzb2.zza();
                                        r15 = r0;
                                        r14 = r93;
                                        zzayoVar2 = zzayoVar;
                                    }
                                } else {
                                    zzayoVar = zzb;
                                    r19 = r43;
                                    arrayList = arrayList2;
                                }
                                r93++;
                                zzaypVar = zzaypVar2;
                                zzb = zzayoVar;
                                arrayList2 = arrayList;
                                r43 = r19;
                            }
                        }
                        r12++;
                        r9 = 1;
                    }
                    zzayw zzaywVar = zzayoVar2 == null ? null : new zzayw(zzayoVar2, r14, 0, null);
                    zzaytVarArr[r5] = zzaywVar;
                    z3 = zzaywVar != null;
                }
                int r03 = zzaypVarArr[r5].zzb;
            }
            r5++;
            r02 = 2;
        }
        int r44 = 0;
        boolean z10 = false;
        while (r44 < r02) {
            if (zzasyVarArr[r44].zzc() == 1 && !z10) {
                zzayp zzaypVar3 = zzaypVarArr[r44];
                int[][] r52 = r23[r44];
                String str = zzayuVar.zza;
                boolean z11 = zzayuVar.zzh;
                boolean z12 = zzayuVar.zzb;
                int r64 = 0;
                int r7 = 0;
                int r94 = -1;
                int r102 = -1;
                while (r64 < zzaypVar3.zzb) {
                    zzayo zzb3 = zzaypVar3.zzb(r64);
                    int[] r122 = r52[r64];
                    int r13 = r102;
                    int r103 = r94;
                    int r95 = r7;
                    int r72 = 0;
                    while (true) {
                        int r142 = zzb3.zza;
                        if (r72 <= 0) {
                            if (zza(r122[r72], true)) {
                                zzass zzb4 = zzb3.zzb(r72);
                                int r04 = r122[r72];
                                int r143 = 1 != (zzb4.zzx & 1) ? 1 : 2;
                                if (zza(r04, false)) {
                                    r143 += 1000;
                                }
                                if (r143 > r95) {
                                    r103 = r64;
                                    r13 = r72;
                                    r95 = r143;
                                }
                            }
                            r72++;
                        }
                    }
                    r64++;
                    r7 = r95;
                    r94 = r103;
                    r102 = r13;
                }
                zzayw zzaywVar2 = r94 == -1 ? null : new zzayw(zzaypVar3.zzb(r94), r102, 0, null);
                zzaytVarArr[r44] = zzaywVar2;
                z10 = zzaywVar2 != null;
            }
            r44++;
            r02 = 2;
        }
        return zzaytVarArr;
    }

    public zzayv(zzaza zzazaVar) {
        this.zza = new AtomicReference(new zzayu());
    }
}
