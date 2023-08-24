package com.google.android.gms.internal.clearcut;

import com.google.android.gms.internal.clearcut.zzcg;
import java.io.IOException;
import java.util.Arrays;

/* loaded from: classes2.dex */
public final class zzey {
    private static final zzey zzoz = new zzey(0, new int[0], new Object[0], false);
    private int count;
    private boolean zzfa;
    private int zzjq;
    private Object[] zzmj;
    private int[] zzpa;

    private zzey() {
        this(0, new int[8], new Object[8], true);
    }

    private zzey(int r2, int[] r3, Object[] objArr, boolean z) {
        this.zzjq = -1;
        this.count = r2;
        this.zzpa = r3;
        this.zzmj = objArr;
        this.zzfa = z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzey zza(zzey zzeyVar, zzey zzeyVar2) {
        int r0 = zzeyVar.count + zzeyVar2.count;
        int[] copyOf = Arrays.copyOf(zzeyVar.zzpa, r0);
        System.arraycopy(zzeyVar2.zzpa, 0, copyOf, zzeyVar.count, zzeyVar2.count);
        Object[] copyOf2 = Arrays.copyOf(zzeyVar.zzmj, r0);
        System.arraycopy(zzeyVar2.zzmj, 0, copyOf2, zzeyVar.count, zzeyVar2.count);
        return new zzey(r0, copyOf, copyOf2, true);
    }

    private static void zzb(int r2, Object obj, zzfr zzfrVar) throws IOException {
        int r0 = r2 >>> 3;
        int r22 = r2 & 7;
        if (r22 == 0) {
            zzfrVar.zzi(r0, ((Long) obj).longValue());
        } else if (r22 == 1) {
            zzfrVar.zzc(r0, ((Long) obj).longValue());
        } else if (r22 == 2) {
            zzfrVar.zza(r0, (zzbb) obj);
        } else if (r22 != 3) {
            if (r22 != 5) {
                throw new RuntimeException(zzco.zzbn());
            }
            zzfrVar.zzf(r0, ((Integer) obj).intValue());
        } else if (zzfrVar.zzaj() == zzcg.zzg.zzko) {
            zzfrVar.zzaa(r0);
            ((zzey) obj).zzb(zzfrVar);
            zzfrVar.zzab(r0);
        } else {
            zzfrVar.zzab(r0);
            ((zzey) obj).zzb(zzfrVar);
            zzfrVar.zzaa(r0);
        }
    }

    public static zzey zzea() {
        return zzoz;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzey zzeb() {
        return new zzey();
    }

    public final boolean equals(Object obj) {
        boolean z;
        boolean z2;
        if (this == obj) {
            return true;
        }
        if (obj != null && (obj instanceof zzey)) {
            zzey zzeyVar = (zzey) obj;
            int r2 = this.count;
            if (r2 == zzeyVar.count) {
                int[] r3 = this.zzpa;
                int[] r4 = zzeyVar.zzpa;
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
                    Object[] objArr = this.zzmj;
                    Object[] objArr2 = zzeyVar.zzmj;
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
        int[] r2 = this.zzpa;
        int r4 = 17;
        int r6 = 17;
        for (int r5 = 0; r5 < r0; r5++) {
            r6 = (r6 * 31) + r2[r5];
        }
        int r12 = (r1 + r6) * 31;
        Object[] objArr = this.zzmj;
        int r22 = this.count;
        for (int r3 = 0; r3 < r22; r3++) {
            r4 = (r4 * 31) + objArr[r3].hashCode();
        }
        return r12 + r4;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zza(zzfr zzfrVar) throws IOException {
        if (zzfrVar.zzaj() == zzcg.zzg.zzkp) {
            for (int r0 = this.count - 1; r0 >= 0; r0--) {
                zzfrVar.zza(this.zzpa[r0] >>> 3, this.zzmj[r0]);
            }
            return;
        }
        for (int r02 = 0; r02 < this.count; r02++) {
            zzfrVar.zza(this.zzpa[r02] >>> 3, this.zzmj[r02]);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zza(StringBuilder sb, int r5) {
        for (int r0 = 0; r0 < this.count; r0++) {
            zzdr.zza(sb, r5, String.valueOf(this.zzpa[r0] >>> 3), this.zzmj[r0]);
        }
    }

    public final int zzas() {
        int zze;
        int r0 = this.zzjq;
        if (r0 != -1) {
            return r0;
        }
        int r1 = 0;
        for (int r02 = 0; r02 < this.count; r02++) {
            int r2 = this.zzpa[r02];
            int r3 = r2 >>> 3;
            int r22 = r2 & 7;
            if (r22 == 0) {
                zze = zzbn.zze(r3, ((Long) this.zzmj[r02]).longValue());
            } else if (r22 == 1) {
                zze = zzbn.zzg(r3, ((Long) this.zzmj[r02]).longValue());
            } else if (r22 == 2) {
                zze = zzbn.zzc(r3, (zzbb) this.zzmj[r02]);
            } else if (r22 == 3) {
                zze = (zzbn.zzr(r3) << 1) + ((zzey) this.zzmj[r02]).zzas();
            } else if (r22 != 5) {
                throw new IllegalStateException(zzco.zzbn());
            } else {
                zze = zzbn.zzj(r3, ((Integer) this.zzmj[r02]).intValue());
            }
            r1 += zze;
        }
        this.zzjq = r1;
        return r1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zzb(int r4, Object obj) {
        if (!this.zzfa) {
            throw new UnsupportedOperationException();
        }
        int r0 = this.count;
        int[] r1 = this.zzpa;
        if (r0 == r1.length) {
            int r02 = r0 + (r0 < 4 ? 8 : r0 >> 1);
            this.zzpa = Arrays.copyOf(r1, r02);
            this.zzmj = Arrays.copyOf(this.zzmj, r02);
        }
        int[] r03 = this.zzpa;
        int r12 = this.count;
        r03[r12] = r4;
        this.zzmj[r12] = obj;
        this.count = r12 + 1;
    }

    public final void zzb(zzfr zzfrVar) throws IOException {
        if (this.count == 0) {
            return;
        }
        if (zzfrVar.zzaj() == zzcg.zzg.zzko) {
            for (int r0 = 0; r0 < this.count; r0++) {
                zzb(this.zzpa[r0], this.zzmj[r0], zzfrVar);
            }
            return;
        }
        for (int r02 = this.count - 1; r02 >= 0; r02--) {
            zzb(this.zzpa[r02], this.zzmj[r02], zzfrVar);
        }
    }

    public final int zzec() {
        int r0 = this.zzjq;
        if (r0 != -1) {
            return r0;
        }
        int r1 = 0;
        for (int r02 = 0; r02 < this.count; r02++) {
            r1 += zzbn.zzd(this.zzpa[r02] >>> 3, (zzbb) this.zzmj[r02]);
        }
        this.zzjq = r1;
        return r1;
    }

    public final void zzv() {
        this.zzfa = false;
    }
}
