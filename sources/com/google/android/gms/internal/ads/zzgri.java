package com.google.android.gms.internal.ads;

import java.io.IOException;
import java.util.Arrays;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzgri {
    private static final zzgri zza = new zzgri(0, new int[0], new Object[0], false);
    private int zzb;
    private int[] zzc;
    private Object[] zzd;
    private int zze;
    private boolean zzf;

    private zzgri() {
        this(0, new int[8], new Object[8], true);
    }

    private zzgri(int r2, int[] r3, Object[] objArr, boolean z) {
        this.zze = -1;
        this.zzb = r2;
        this.zzc = r3;
        this.zzd = objArr;
        this.zzf = z;
    }

    public static zzgri zzc() {
        return zza;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzgri zzd(zzgri zzgriVar, zzgri zzgriVar2) {
        int r0 = zzgriVar.zzb + zzgriVar2.zzb;
        int[] copyOf = Arrays.copyOf(zzgriVar.zzc, r0);
        System.arraycopy(zzgriVar2.zzc, 0, copyOf, zzgriVar.zzb, zzgriVar2.zzb);
        Object[] copyOf2 = Arrays.copyOf(zzgriVar.zzd, r0);
        System.arraycopy(zzgriVar2.zzd, 0, copyOf2, zzgriVar.zzb, zzgriVar2.zzb);
        return new zzgri(r0, copyOf, copyOf2, true);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzgri zze() {
        return new zzgri(0, new int[8], new Object[8], true);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && (obj instanceof zzgri)) {
            zzgri zzgriVar = (zzgri) obj;
            int r2 = this.zzb;
            if (r2 == zzgriVar.zzb) {
                int[] r3 = this.zzc;
                int[] r4 = zzgriVar.zzc;
                int r5 = 0;
                while (true) {
                    if (r5 >= r2) {
                        Object[] objArr = this.zzd;
                        Object[] objArr2 = zzgriVar.zzd;
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
        int zzE;
        int zzF;
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
                        r2 = zzgnu.zzE(r3 << 3) + 8;
                    } else if (r23 == 2) {
                        int zzE2 = zzgnu.zzE(r3 << 3);
                        int zzd = ((zzgnf) this.zzd[r02]).zzd();
                        r1 += zzE2 + zzgnu.zzE(zzd) + zzd;
                    } else if (r23 == 3) {
                        int zzD = zzgnu.zzD(r3);
                        zzE = zzD + zzD;
                        zzF = ((zzgri) this.zzd[r02]).zza();
                    } else if (r23 == 5) {
                        ((Integer) this.zzd[r02]).intValue();
                        r2 = zzgnu.zzE(r3 << 3) + 4;
                    } else {
                        throw new IllegalStateException(zzgoz.zza());
                    }
                    r1 += r2;
                } else {
                    long longValue = ((Long) this.zzd[r02]).longValue();
                    zzE = zzgnu.zzE(r3 << 3);
                    zzF = zzgnu.zzF(longValue);
                }
                r2 = zzE + zzF;
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
                int zzE = zzgnu.zzE(8);
                int zzd = ((zzgnf) this.zzd[r02]).zzd();
                r1 += zzE + zzE + zzgnu.zzE(16) + zzgnu.zzE(r2 >>> 3) + zzgnu.zzE(24) + zzgnu.zzE(zzd) + zzd;
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
            zzgpz.zzb(sb, r5, String.valueOf(this.zzc[r0] >>> 3), this.zzd[r0]);
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

    public final void zzi(zzgnv zzgnvVar) throws IOException {
        if (this.zzb != 0) {
            for (int r0 = 0; r0 < this.zzb; r0++) {
                int r1 = this.zzc[r0];
                Object obj = this.zzd[r0];
                int r3 = r1 >>> 3;
                int r12 = r1 & 7;
                if (r12 == 0) {
                    zzgnvVar.zzt(r3, ((Long) obj).longValue());
                } else if (r12 == 1) {
                    zzgnvVar.zzm(r3, ((Long) obj).longValue());
                } else if (r12 == 2) {
                    zzgnvVar.zzd(r3, (zzgnf) obj);
                } else if (r12 == 3) {
                    zzgnvVar.zzE(r3);
                    ((zzgri) obj).zzi(zzgnvVar);
                    zzgnvVar.zzh(r3);
                } else if (r12 == 5) {
                    zzgnvVar.zzk(r3, ((Integer) obj).intValue());
                } else {
                    throw new RuntimeException(zzgoz.zza());
                }
            }
        }
    }
}
