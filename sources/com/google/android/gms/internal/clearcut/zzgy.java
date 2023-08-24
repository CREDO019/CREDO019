package com.google.android.gms.internal.clearcut;

import java.io.IOException;

/* loaded from: classes2.dex */
public final class zzgy extends zzfu<zzgy> implements Cloneable {
    private String[] zzbiw = zzgb.zzsc;
    private String[] zzbix = zzgb.zzsc;
    private int[] zzbiy = zzgb.zzrx;
    private long[] zzbiz = zzgb.zzry;
    private long[] zzbja = zzgb.zzry;

    public zzgy() {
        this.zzrj = null;
        this.zzrs = -1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // com.google.android.gms.internal.clearcut.zzfu, com.google.android.gms.internal.clearcut.zzfz
    /* renamed from: zzgb */
    public final zzgy clone() {
        try {
            zzgy zzgyVar = (zzgy) super.clone();
            String[] strArr = this.zzbiw;
            if (strArr != null && strArr.length > 0) {
                zzgyVar.zzbiw = (String[]) strArr.clone();
            }
            String[] strArr2 = this.zzbix;
            if (strArr2 != null && strArr2.length > 0) {
                zzgyVar.zzbix = (String[]) strArr2.clone();
            }
            int[] r1 = this.zzbiy;
            if (r1 != null && r1.length > 0) {
                zzgyVar.zzbiy = (int[]) r1.clone();
            }
            long[] jArr = this.zzbiz;
            if (jArr != null && jArr.length > 0) {
                zzgyVar.zzbiz = (long[]) jArr.clone();
            }
            long[] jArr2 = this.zzbja;
            if (jArr2 != null && jArr2.length > 0) {
                zzgyVar.zzbja = (long[]) jArr2.clone();
            }
            return zzgyVar;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof zzgy) {
            zzgy zzgyVar = (zzgy) obj;
            if (zzfy.equals(this.zzbiw, zzgyVar.zzbiw) && zzfy.equals(this.zzbix, zzgyVar.zzbix) && zzfy.equals(this.zzbiy, zzgyVar.zzbiy) && zzfy.equals(this.zzbiz, zzgyVar.zzbiz) && zzfy.equals(this.zzbja, zzgyVar.zzbja)) {
                return (this.zzrj == null || this.zzrj.isEmpty()) ? zzgyVar.zzrj == null || zzgyVar.zzrj.isEmpty() : this.zzrj.equals(zzgyVar.zzrj);
            }
            return false;
        }
        return false;
    }

    public final int hashCode() {
        return ((((((((((((getClass().getName().hashCode() + 527) * 31) + zzfy.hashCode(this.zzbiw)) * 31) + zzfy.hashCode(this.zzbix)) * 31) + zzfy.hashCode(this.zzbiy)) * 31) + zzfy.hashCode(this.zzbiz)) * 31) + zzfy.hashCode(this.zzbja)) * 31) + ((this.zzrj == null || this.zzrj.isEmpty()) ? 0 : this.zzrj.hashCode());
    }

    @Override // com.google.android.gms.internal.clearcut.zzfu, com.google.android.gms.internal.clearcut.zzfz
    public final void zza(zzfs zzfsVar) throws IOException {
        String[] strArr = this.zzbiw;
        int r1 = 0;
        if (strArr != null && strArr.length > 0) {
            int r0 = 0;
            while (true) {
                String[] strArr2 = this.zzbiw;
                if (r0 >= strArr2.length) {
                    break;
                }
                String str = strArr2[r0];
                if (str != null) {
                    zzfsVar.zza(1, str);
                }
                r0++;
            }
        }
        String[] strArr3 = this.zzbix;
        if (strArr3 != null && strArr3.length > 0) {
            int r02 = 0;
            while (true) {
                String[] strArr4 = this.zzbix;
                if (r02 >= strArr4.length) {
                    break;
                }
                String str2 = strArr4[r02];
                if (str2 != null) {
                    zzfsVar.zza(2, str2);
                }
                r02++;
            }
        }
        int[] r03 = this.zzbiy;
        if (r03 != null && r03.length > 0) {
            int r04 = 0;
            while (true) {
                int[] r2 = this.zzbiy;
                if (r04 >= r2.length) {
                    break;
                }
                zzfsVar.zzc(3, r2[r04]);
                r04++;
            }
        }
        long[] jArr = this.zzbiz;
        if (jArr != null && jArr.length > 0) {
            int r05 = 0;
            while (true) {
                long[] jArr2 = this.zzbiz;
                if (r05 >= jArr2.length) {
                    break;
                }
                zzfsVar.zzi(4, jArr2[r05]);
                r05++;
            }
        }
        long[] jArr3 = this.zzbja;
        if (jArr3 != null && jArr3.length > 0) {
            while (true) {
                long[] jArr4 = this.zzbja;
                if (r1 >= jArr4.length) {
                    break;
                }
                zzfsVar.zzi(5, jArr4[r1]);
                r1++;
            }
        }
        super.zza(zzfsVar);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.clearcut.zzfu, com.google.android.gms.internal.clearcut.zzfz
    public final int zzen() {
        long[] jArr;
        int[] r4;
        int zzen = super.zzen();
        String[] strArr = this.zzbiw;
        int r2 = 0;
        if (strArr != null && strArr.length > 0) {
            int r1 = 0;
            int r3 = 0;
            int r42 = 0;
            while (true) {
                String[] strArr2 = this.zzbiw;
                if (r1 >= strArr2.length) {
                    break;
                }
                String str = strArr2[r1];
                if (str != null) {
                    r42++;
                    r3 += zzfs.zzh(str);
                }
                r1++;
            }
            zzen = zzen + r3 + (r42 * 1);
        }
        String[] strArr3 = this.zzbix;
        if (strArr3 != null && strArr3.length > 0) {
            int r12 = 0;
            int r32 = 0;
            int r43 = 0;
            while (true) {
                String[] strArr4 = this.zzbix;
                if (r12 >= strArr4.length) {
                    break;
                }
                String str2 = strArr4[r12];
                if (str2 != null) {
                    r43++;
                    r32 += zzfs.zzh(str2);
                }
                r12++;
            }
            zzen = zzen + r32 + (r43 * 1);
        }
        int[] r13 = this.zzbiy;
        if (r13 != null && r13.length > 0) {
            int r14 = 0;
            int r33 = 0;
            while (true) {
                r4 = this.zzbiy;
                if (r14 >= r4.length) {
                    break;
                }
                r33 += zzfs.zzs(r4[r14]);
                r14++;
            }
            zzen = zzen + r33 + (r4.length * 1);
        }
        long[] jArr2 = this.zzbiz;
        if (jArr2 != null && jArr2.length > 0) {
            int r15 = 0;
            int r34 = 0;
            while (true) {
                jArr = this.zzbiz;
                if (r15 >= jArr.length) {
                    break;
                }
                r34 += zzfs.zzo(jArr[r15]);
                r15++;
            }
            zzen = zzen + r34 + (jArr.length * 1);
        }
        long[] jArr3 = this.zzbja;
        if (jArr3 == null || jArr3.length <= 0) {
            return zzen;
        }
        int r16 = 0;
        while (true) {
            long[] jArr4 = this.zzbja;
            if (r2 >= jArr4.length) {
                return zzen + r16 + (jArr4.length * 1);
            }
            r16 += zzfs.zzo(jArr4[r2]);
            r2++;
        }
    }

    @Override // com.google.android.gms.internal.clearcut.zzfu
    public final /* synthetic */ zzgy zzeo() throws CloneNotSupportedException {
        return (zzgy) clone();
    }

    @Override // com.google.android.gms.internal.clearcut.zzfu, com.google.android.gms.internal.clearcut.zzfz
    public final /* synthetic */ zzfz zzep() throws CloneNotSupportedException {
        return (zzgy) clone();
    }
}
