package com.google.android.gms.internal.clearcut;

import java.io.IOException;
import java.util.Arrays;

/* loaded from: classes2.dex */
public final class zzgz extends zzfu<zzgz> implements Cloneable {
    private byte[] zzbjb = zzgb.zzse;
    private String zzbjc = "";
    private byte[][] zzbjd = zzgb.zzsd;
    private boolean zzbje = false;

    public zzgz() {
        this.zzrj = null;
        this.zzrs = -1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // com.google.android.gms.internal.clearcut.zzfu, com.google.android.gms.internal.clearcut.zzfz
    /* renamed from: zzgc */
    public final zzgz clone() {
        try {
            zzgz zzgzVar = (zzgz) super.clone();
            byte[][] bArr = this.zzbjd;
            if (bArr != null && bArr.length > 0) {
                zzgzVar.zzbjd = (byte[][]) bArr.clone();
            }
            return zzgzVar;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof zzgz) {
            zzgz zzgzVar = (zzgz) obj;
            if (Arrays.equals(this.zzbjb, zzgzVar.zzbjb)) {
                String str = this.zzbjc;
                if (str == null) {
                    if (zzgzVar.zzbjc != null) {
                        return false;
                    }
                } else if (!str.equals(zzgzVar.zzbjc)) {
                    return false;
                }
                if (zzfy.zza(this.zzbjd, zzgzVar.zzbjd)) {
                    return (this.zzrj == null || this.zzrj.isEmpty()) ? zzgzVar.zzrj == null || zzgzVar.zzrj.isEmpty() : this.zzrj.equals(zzgzVar.zzrj);
                }
                return false;
            }
            return false;
        }
        return false;
    }

    public final int hashCode() {
        int hashCode = (((getClass().getName().hashCode() + 527) * 31) + Arrays.hashCode(this.zzbjb)) * 31;
        String str = this.zzbjc;
        int r2 = 0;
        int hashCode2 = (((((hashCode + (str == null ? 0 : str.hashCode())) * 31) + zzfy.zza(this.zzbjd)) * 31) + 1237) * 31;
        if (this.zzrj != null && !this.zzrj.isEmpty()) {
            r2 = this.zzrj.hashCode();
        }
        return hashCode2 + r2;
    }

    @Override // com.google.android.gms.internal.clearcut.zzfu, com.google.android.gms.internal.clearcut.zzfz
    public final void zza(zzfs zzfsVar) throws IOException {
        if (!Arrays.equals(this.zzbjb, zzgb.zzse)) {
            zzfsVar.zza(1, this.zzbjb);
        }
        byte[][] bArr = this.zzbjd;
        if (bArr != null && bArr.length > 0) {
            int r0 = 0;
            while (true) {
                byte[][] bArr2 = this.zzbjd;
                if (r0 >= bArr2.length) {
                    break;
                }
                byte[] bArr3 = bArr2[r0];
                if (bArr3 != null) {
                    zzfsVar.zza(2, bArr3);
                }
                r0++;
            }
        }
        String str = this.zzbjc;
        if (str != null && !str.equals("")) {
            zzfsVar.zza(4, this.zzbjc);
        }
        super.zza(zzfsVar);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.clearcut.zzfu, com.google.android.gms.internal.clearcut.zzfz
    public final int zzen() {
        int zzen = super.zzen();
        if (!Arrays.equals(this.zzbjb, zzgb.zzse)) {
            zzen += zzfs.zzb(1, this.zzbjb);
        }
        byte[][] bArr = this.zzbjd;
        if (bArr != null && bArr.length > 0) {
            int r1 = 0;
            int r3 = 0;
            int r4 = 0;
            while (true) {
                byte[][] bArr2 = this.zzbjd;
                if (r1 >= bArr2.length) {
                    break;
                }
                byte[] bArr3 = bArr2[r1];
                if (bArr3 != null) {
                    r4++;
                    r3 += zzfs.zzh(bArr3);
                }
                r1++;
            }
            zzen = zzen + r3 + (r4 * 1);
        }
        String str = this.zzbjc;
        return (str == null || str.equals("")) ? zzen : zzen + zzfs.zzb(4, this.zzbjc);
    }

    @Override // com.google.android.gms.internal.clearcut.zzfu
    public final /* synthetic */ zzgz zzeo() throws CloneNotSupportedException {
        return (zzgz) clone();
    }

    @Override // com.google.android.gms.internal.clearcut.zzfu, com.google.android.gms.internal.clearcut.zzfz
    public final /* synthetic */ zzfz zzep() throws CloneNotSupportedException {
        return (zzgz) clone();
    }
}