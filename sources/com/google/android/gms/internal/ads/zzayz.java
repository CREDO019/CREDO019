package com.google.android.gms.internal.ads;

import android.util.SparseArray;
import android.util.SparseBooleanArray;
import java.util.Arrays;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public abstract class zzayz extends zzazd {
    private final SparseArray zza = new SparseArray();
    private final SparseBooleanArray zzb = new SparseBooleanArray();
    private zzayx zzc;

    protected abstract zzayt[] zzb(zzasy[] zzasyVarArr, zzayp[] zzaypVarArr, int[][][] r3) throws zzase;

    @Override // com.google.android.gms.internal.ads.zzazd
    public final zzaze zzc(zzasy[] zzasyVarArr, zzayp zzaypVar) throws zzase {
        int[] r12;
        int[] r4 = new int[3];
        zzayo[][] zzayoVarArr = new zzayo[3];
        int[][][] r10 = new int[3][];
        for (int r6 = 0; r6 < 3; r6++) {
            int r7 = zzaypVar.zzb;
            zzayoVarArr[r6] = new zzayo[r7];
            r10[r6] = new int[r7];
        }
        int r13 = 2;
        int[] r9 = new int[2];
        for (int r62 = 0; r62 < 2; r62++) {
            zzasyVarArr[r62].zze();
            r9[r62] = 4;
        }
        int r63 = 0;
        while (r63 < zzaypVar.zzb) {
            zzayo zzb = zzaypVar.zzb(r63);
            int r8 = 0;
            int r11 = 0;
            int r14 = 2;
            while (true) {
                if (r8 >= r13) {
                    r8 = r14;
                    break;
                }
                zzasy zzasyVar = zzasyVarArr[r8];
                int r122 = 0;
                while (true) {
                    int r132 = zzb.zza;
                    if (r122 <= 0) {
                        int zzG = zzasyVar.zzG(zzb.zzb(r122)) & 3;
                        if (zzG > r11) {
                            if (zzG == 3) {
                                break;
                            }
                            r14 = r8;
                            r11 = zzG;
                        }
                        r122++;
                    }
                }
                r8++;
                r13 = 2;
            }
            if (r8 == 2) {
                int r123 = zzb.zza;
                r12 = new int[1];
            } else {
                zzasy zzasyVar2 = zzasyVarArr[r8];
                int r133 = zzb.zza;
                int[] r134 = new int[1];
                int r142 = 0;
                while (true) {
                    int r15 = zzb.zza;
                    if (r142 > 0) {
                        break;
                    }
                    r134[r142] = zzasyVar2.zzG(zzb.zzb(r142));
                    r142++;
                }
                r12 = r134;
            }
            int r135 = r4[r8];
            zzayoVarArr[r8][r135] = zzb;
            r10[r8][r135] = r12;
            r4[r8] = r135 + 1;
            r63++;
            r13 = 2;
        }
        zzayp[] zzaypVarArr = new zzayp[2];
        int[] r72 = new int[2];
        int r3 = 0;
        for (int r64 = 2; r3 < r64; r64 = 2) {
            int r65 = r4[r3];
            zzaypVarArr[r3] = new zzayp((zzayo[]) Arrays.copyOf(zzayoVarArr[r3], r65));
            r10[r3] = (int[][]) Arrays.copyOf(r10[r3], r65);
            r72[r3] = zzasyVarArr[r3].zzc();
            r3++;
        }
        zzayp zzaypVar2 = new zzayp((zzayo[]) Arrays.copyOf(zzayoVarArr[2], r4[2]));
        zzayt[] zzb2 = zzb(zzasyVarArr, zzaypVarArr, r10);
        int r42 = 0;
        for (int r32 = 2; r42 < r32; r32 = 2) {
            if (this.zzb.get(r42)) {
                zzb2[r42] = null;
            } else {
                zzayp zzaypVar3 = zzaypVarArr[r42];
                Map map = (Map) this.zza.get(r42);
                if ((map == null ? null : (zzayy) map.get(zzaypVar3)) != null) {
                    throw null;
                }
            }
            r42++;
        }
        zzayx zzayxVar = new zzayx(r72, zzaypVarArr, r9, r10, zzaypVar2);
        zzasz[] zzaszVarArr = new zzasz[2];
        for (int r124 = 0; r124 < 2; r124++) {
            zzaszVarArr[r124] = zzb2[r124] != null ? zzasz.zza : null;
        }
        return new zzaze(zzaypVar, new zzazb(zzb2, null), zzayxVar, zzaszVarArr);
    }

    @Override // com.google.android.gms.internal.ads.zzazd
    public final void zzd(Object obj) {
        this.zzc = (zzayx) obj;
    }

    public final void zze(int r2, boolean z) {
        if (this.zzb.get(r2) == z) {
            return;
        }
        this.zzb.put(r2, z);
        zzg();
    }
}
