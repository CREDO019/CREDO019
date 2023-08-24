package com.google.android.gms.internal.measurement;

import java.io.IOException;
import java.util.Arrays;

/* compiled from: com.google.android.gms:play-services-measurement-base@@20.1.2 */
/* loaded from: classes3.dex */
public final class zzmm {
    private static final zzmm zza = new zzmm(0, new int[0], new Object[0], false);
    private int zzb;
    private int[] zzc;
    private Object[] zzd;
    private int zze;
    private boolean zzf;

    private zzmm() {
        this(0, new int[8], new Object[8], true);
    }

    private zzmm(int r2, int[] r3, Object[] objArr, boolean z) {
        this.zze = -1;
        this.zzb = r2;
        this.zzc = r3;
        this.zzd = objArr;
        this.zzf = z;
    }

    public static zzmm zzc() {
        return zza;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzmm zzd(zzmm zzmmVar, zzmm zzmmVar2) {
        int r0 = zzmmVar.zzb + zzmmVar2.zzb;
        int[] copyOf = Arrays.copyOf(zzmmVar.zzc, r0);
        System.arraycopy(zzmmVar2.zzc, 0, copyOf, zzmmVar.zzb, zzmmVar2.zzb);
        Object[] copyOf2 = Arrays.copyOf(zzmmVar.zzd, r0);
        System.arraycopy(zzmmVar2.zzd, 0, copyOf2, zzmmVar.zzb, zzmmVar2.zzb);
        return new zzmm(r0, copyOf, copyOf2, true);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzmm zze() {
        return new zzmm(0, new int[8], new Object[8], true);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && (obj instanceof zzmm)) {
            zzmm zzmmVar = (zzmm) obj;
            int r2 = this.zzb;
            if (r2 == zzmmVar.zzb) {
                int[] r3 = this.zzc;
                int[] r4 = zzmmVar.zzc;
                int r5 = 0;
                while (true) {
                    if (r5 >= r2) {
                        Object[] objArr = this.zzd;
                        Object[] objArr2 = zzmmVar.zzd;
                        int r32 = this.zzb;
                        for (int r42 = 0; r42 < r32; r42++) {
                            if (objArr[r42].equals(objArr2[r42])) {
                            }
                        }
                        return true;
                    } else if (r3[r5] != r4[r5]) {
                        break;
                    } else {
                        r5++;
                    }
                }
            }
            return false;
        }
        return false;
    }

    public final int hashCode() {
        int r0 = this.zzb;
        int r1 = (r0 + 527) * 31;
        int[] r2 = this.zzc;
        int r4 = 17;
        int r6 = 17;
        for (int r5 = 0; r5 < r0; r5++) {
            r6 = (r6 * 31) + r2[r5];
        }
        int r12 = (r1 + r6) * 31;
        Object[] objArr = this.zzd;
        int r22 = this.zzb;
        for (int r3 = 0; r3 < r22; r3++) {
            r4 = (r4 * 31) + objArr[r3].hashCode();
        }
        return r12 + r4;
    }

    public final int zza() {
        int zzA;
        int zzB;
        int r2;
        int r0 = this.zze;
        if (r0 == -1) {
            int r1 = 0;
            for (int r02 = 0; r02 < this.zzb; r02++) {
                int r22 = this.zzc[r02];
                int r3 = r22 >>> 3;
                int r23 = r22 & 7;
                if (r23 != 0) {
                    if (r23 == 1) {
                        ((Long) this.zzd[r02]).longValue();
                        r2 = zzjj.zzA(r3 << 3) + 8;
                    } else if (r23 == 2) {
                        int zzA2 = zzjj.zzA(r3 << 3);
                        int zzd = ((zzjb) this.zzd[r02]).zzd();
                        r1 += zzA2 + zzjj.zzA(zzd) + zzd;
                    } else if (r23 == 3) {
                        int zzz = zzjj.zzz(r3);
                        zzA = zzz + zzz;
                        zzB = ((zzmm) this.zzd[r02]).zza();
                    } else if (r23 == 5) {
                        ((Integer) this.zzd[r02]).intValue();
                        r2 = zzjj.zzA(r3 << 3) + 4;
                    } else {
                        throw new IllegalStateException(zzkm.zza());
                    }
                    r1 += r2;
                } else {
                    long longValue = ((Long) this.zzd[r02]).longValue();
                    zzA = zzjj.zzA(r3 << 3);
                    zzB = zzjj.zzB(longValue);
                }
                r2 = zzA + zzB;
                r1 += r2;
            }
            this.zze = r1;
            return r1;
        }
        return r0;
    }

    public final int zzb() {
        int r0 = this.zze;
        if (r0 == -1) {
            int r1 = 0;
            for (int r02 = 0; r02 < this.zzb; r02++) {
                int r2 = this.zzc[r02];
                int zzA = zzjj.zzA(8);
                int zzd = ((zzjb) this.zzd[r02]).zzd();
                r1 += zzA + zzA + zzjj.zzA(16) + zzjj.zzA(r2 >>> 3) + zzjj.zzA(24) + zzjj.zzA(zzd) + zzd;
            }
            this.zze = r1;
            return r1;
        }
        return r0;
    }

    public final void zzf() {
        this.zzf = false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zzg(StringBuilder sb, int r5) {
        for (int r0 = 0; r0 < this.zzb; r0++) {
            zzll.zzb(sb, r5, String.valueOf(this.zzc[r0] >>> 3), this.zzd[r0]);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zzh(int r4, Object obj) {
        if (this.zzf) {
            int r0 = this.zzb;
            int[] r1 = this.zzc;
            if (r0 == r1.length) {
                int r02 = r0 + (r0 < 4 ? 8 : r0 >> 1);
                this.zzc = Arrays.copyOf(r1, r02);
                this.zzd = Arrays.copyOf(this.zzd, r02);
            }
            int[] r03 = this.zzc;
            int r12 = this.zzb;
            r03[r12] = r4;
            this.zzd[r12] = obj;
            this.zzb = r12 + 1;
            return;
        }
        throw new UnsupportedOperationException();
    }

    public final void zzi(zznd zzndVar) throws IOException {
        if (this.zzb != 0) {
            for (int r0 = 0; r0 < this.zzb; r0++) {
                int r1 = this.zzc[r0];
                Object obj = this.zzd[r0];
                int r3 = r1 >>> 3;
                int r12 = r1 & 7;
                if (r12 == 0) {
                    zzndVar.zzt(r3, ((Long) obj).longValue());
                } else if (r12 == 1) {
                    zzndVar.zzm(r3, ((Long) obj).longValue());
                } else if (r12 == 2) {
                    zzndVar.zzd(r3, (zzjb) obj);
                } else if (r12 == 3) {
                    zzndVar.zzE(r3);
                    ((zzmm) obj).zzi(zzndVar);
                    zzndVar.zzh(r3);
                } else if (r12 == 5) {
                    zzndVar.zzk(r3, ((Integer) obj).intValue());
                } else {
                    throw new RuntimeException(zzkm.zza());
                }
            }
        }
    }
}
