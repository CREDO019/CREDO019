package com.google.android.gms.internal.vision;

import com.google.android.gms.internal.vision.zzgs;
import java.io.IOException;
import java.util.Arrays;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.0 */
/* loaded from: classes3.dex */
public final class zzjm {
    private static final zzjm zzaaj = new zzjm(0, new int[0], new Object[0], false);
    private int count;
    private int[] zzaak;
    private boolean zzrj;
    private int zzwe;
    private Object[] zzyv;

    public static zzjm zzig() {
        return zzaaj;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzjm zzih() {
        return new zzjm();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzjm zza(zzjm zzjmVar, zzjm zzjmVar2) {
        int r0 = zzjmVar.count + zzjmVar2.count;
        int[] copyOf = Arrays.copyOf(zzjmVar.zzaak, r0);
        System.arraycopy(zzjmVar2.zzaak, 0, copyOf, zzjmVar.count, zzjmVar2.count);
        Object[] copyOf2 = Arrays.copyOf(zzjmVar.zzyv, r0);
        System.arraycopy(zzjmVar2.zzyv, 0, copyOf2, zzjmVar.count, zzjmVar2.count);
        return new zzjm(r0, copyOf, copyOf2, true);
    }

    private zzjm() {
        this(0, new int[8], new Object[8], true);
    }

    private zzjm(int r2, int[] r3, Object[] objArr, boolean z) {
        this.zzwe = -1;
        this.count = r2;
        this.zzaak = r3;
        this.zzyv = objArr;
        this.zzrj = z;
    }

    public final void zzdp() {
        this.zzrj = false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zza(zzkg zzkgVar) throws IOException {
        if (zzkgVar.zzfj() == zzgs.zzf.zzwx) {
            for (int r0 = this.count - 1; r0 >= 0; r0--) {
                zzkgVar.zza(this.zzaak[r0] >>> 3, this.zzyv[r0]);
            }
            return;
        }
        for (int r02 = 0; r02 < this.count; r02++) {
            zzkgVar.zza(this.zzaak[r02] >>> 3, this.zzyv[r02]);
        }
    }

    public final void zzb(zzkg zzkgVar) throws IOException {
        if (this.count == 0) {
            return;
        }
        if (zzkgVar.zzfj() == zzgs.zzf.zzww) {
            for (int r0 = 0; r0 < this.count; r0++) {
                zzb(this.zzaak[r0], this.zzyv[r0], zzkgVar);
            }
            return;
        }
        for (int r02 = this.count - 1; r02 >= 0; r02--) {
            zzb(this.zzaak[r02], this.zzyv[r02], zzkgVar);
        }
    }

    private static void zzb(int r2, Object obj, zzkg zzkgVar) throws IOException {
        int r0 = r2 >>> 3;
        int r22 = r2 & 7;
        if (r22 == 0) {
            zzkgVar.zzi(r0, ((Long) obj).longValue());
        } else if (r22 == 1) {
            zzkgVar.zzc(r0, ((Long) obj).longValue());
        } else if (r22 == 2) {
            zzkgVar.zza(r0, (zzfh) obj);
        } else if (r22 != 3) {
            if (r22 == 5) {
                zzkgVar.zzk(r0, ((Integer) obj).intValue());
                return;
            }
            throw new RuntimeException(zzhc.zzgr());
        } else if (zzkgVar.zzfj() == zzgs.zzf.zzww) {
            zzkgVar.zzbj(r0);
            ((zzjm) obj).zzb(zzkgVar);
            zzkgVar.zzbk(r0);
        } else {
            zzkgVar.zzbk(r0);
            ((zzjm) obj).zzb(zzkgVar);
            zzkgVar.zzbj(r0);
        }
    }

    public final int zzii() {
        int r0 = this.zzwe;
        if (r0 != -1) {
            return r0;
        }
        int r1 = 0;
        for (int r02 = 0; r02 < this.count; r02++) {
            r1 += zzga.zzd(this.zzaak[r02] >>> 3, (zzfh) this.zzyv[r02]);
        }
        this.zzwe = r1;
        return r1;
    }

    public final int zzgf() {
        int zze;
        int r0 = this.zzwe;
        if (r0 != -1) {
            return r0;
        }
        int r1 = 0;
        for (int r02 = 0; r02 < this.count; r02++) {
            int r2 = this.zzaak[r02];
            int r3 = r2 >>> 3;
            int r22 = r2 & 7;
            if (r22 == 0) {
                zze = zzga.zze(r3, ((Long) this.zzyv[r02]).longValue());
            } else if (r22 == 1) {
                zze = zzga.zzg(r3, ((Long) this.zzyv[r02]).longValue());
            } else if (r22 == 2) {
                zze = zzga.zzc(r3, (zzfh) this.zzyv[r02]);
            } else if (r22 == 3) {
                zze = (zzga.zzba(r3) << 1) + ((zzjm) this.zzyv[r02]).zzgf();
            } else if (r22 == 5) {
                zze = zzga.zzo(r3, ((Integer) this.zzyv[r02]).intValue());
            } else {
                throw new IllegalStateException(zzhc.zzgr());
            }
            r1 += zze;
        }
        this.zzwe = r1;
        return r1;
    }

    public final boolean equals(Object obj) {
        boolean z;
        boolean z2;
        if (this == obj) {
            return true;
        }
        if (obj != null && (obj instanceof zzjm)) {
            zzjm zzjmVar = (zzjm) obj;
            int r2 = this.count;
            if (r2 == zzjmVar.count) {
                int[] r3 = this.zzaak;
                int[] r4 = zzjmVar.zzaak;
                int r5 = 0;
                while (true) {
                    if (r5 >= r2) {
                        z = true;
                        break;
                    } else if (r3[r5] != r4[r5]) {
                        z = false;
                        break;
                    } else {
                        r5++;
                    }
                }
                if (z) {
                    Object[] objArr = this.zzyv;
                    Object[] objArr2 = zzjmVar.zzyv;
                    int r32 = this.count;
                    int r42 = 0;
                    while (true) {
                        if (r42 >= r32) {
                            z2 = true;
                            break;
                        } else if (!objArr[r42].equals(objArr2[r42])) {
                            z2 = false;
                            break;
                        } else {
                            r42++;
                        }
                    }
                    if (z2) {
                        return true;
                    }
                }
            }
            return false;
        }
        return false;
    }

    public final int hashCode() {
        int r0 = this.count;
        int r1 = (r0 + 527) * 31;
        int[] r2 = this.zzaak;
        int r4 = 17;
        int r6 = 17;
        for (int r5 = 0; r5 < r0; r5++) {
            r6 = (r6 * 31) + r2[r5];
        }
        int r12 = (r1 + r6) * 31;
        Object[] objArr = this.zzyv;
        int r22 = this.count;
        for (int r3 = 0; r3 < r22; r3++) {
            r4 = (r4 * 31) + objArr[r3].hashCode();
        }
        return r12 + r4;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zza(StringBuilder sb, int r5) {
        for (int r0 = 0; r0 < this.count; r0++) {
            zzid.zza(sb, r5, String.valueOf(this.zzaak[r0] >>> 3), this.zzyv[r0]);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zzb(int r4, Object obj) {
        if (!this.zzrj) {
            throw new UnsupportedOperationException();
        }
        int r0 = this.count;
        int[] r1 = this.zzaak;
        if (r0 == r1.length) {
            int r02 = r0 + (r0 < 4 ? 8 : r0 >> 1);
            this.zzaak = Arrays.copyOf(r1, r02);
            this.zzyv = Arrays.copyOf(this.zzyv, r02);
        }
        int[] r03 = this.zzaak;
        int r12 = this.count;
        r03[r12] = r4;
        this.zzyv[r12] = obj;
        this.count = r12 + 1;
    }
}
