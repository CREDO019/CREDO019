package com.google.android.gms.internal.vision;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Objects;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.0 */
/* loaded from: classes3.dex */
public class zzfr extends zzfs {
    protected final byte[] zzse;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzfr(byte[] bArr) {
        Objects.requireNonNull(bArr);
        this.zzse = bArr;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int zzeu() {
        return 0;
    }

    @Override // com.google.android.gms.internal.vision.zzfh
    public byte zzan(int r2) {
        return this.zzse[r2];
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.vision.zzfh
    public byte zzao(int r2) {
        return this.zzse[r2];
    }

    @Override // com.google.android.gms.internal.vision.zzfh
    public int size() {
        return this.zzse.length;
    }

    @Override // com.google.android.gms.internal.vision.zzfh
    public final zzfh zzf(int r3, int r4) {
        int zzc = zzc(0, r4, size());
        if (zzc == 0) {
            return zzfh.zzrx;
        }
        return new zzfo(this.zzse, zzeu(), zzc);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.vision.zzfh
    public void zza(byte[] bArr, int r2, int r3, int r4) {
        System.arraycopy(this.zzse, 0, bArr, 0, r4);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.vision.zzfh
    public final void zza(zzfi zzfiVar) throws IOException {
        zzfiVar.zzc(this.zzse, zzeu(), size());
    }

    @Override // com.google.android.gms.internal.vision.zzfh
    protected final String zza(Charset charset) {
        return new String(this.zzse, zzeu(), size(), charset);
    }

    @Override // com.google.android.gms.internal.vision.zzfh
    public final boolean zzes() {
        int zzeu = zzeu();
        return zzjs.zzf(this.zzse, zzeu, size() + zzeu);
    }

    @Override // com.google.android.gms.internal.vision.zzfh
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if ((obj instanceof zzfh) && size() == ((zzfh) obj).size()) {
            if (size() == 0) {
                return true;
            }
            if (obj instanceof zzfr) {
                zzfr zzfrVar = (zzfr) obj;
                int zzet = zzet();
                int zzet2 = zzfrVar.zzet();
                if (zzet == 0 || zzet2 == 0 || zzet == zzet2) {
                    return zza(zzfrVar, 0, size());
                }
                return false;
            }
            return obj.equals(this);
        }
        return false;
    }

    @Override // com.google.android.gms.internal.vision.zzfs
    final boolean zza(zzfh zzfhVar, int r7, int r8) {
        if (r8 > zzfhVar.size()) {
            int size = size();
            StringBuilder sb = new StringBuilder(40);
            sb.append("Length too large: ");
            sb.append(r8);
            sb.append(size);
            throw new IllegalArgumentException(sb.toString());
        } else if (r8 > zzfhVar.size()) {
            int size2 = zzfhVar.size();
            StringBuilder sb2 = new StringBuilder(59);
            sb2.append("Ran off end of other: 0, ");
            sb2.append(r8);
            sb2.append(", ");
            sb2.append(size2);
            throw new IllegalArgumentException(sb2.toString());
        } else if (zzfhVar instanceof zzfr) {
            zzfr zzfrVar = (zzfr) zzfhVar;
            byte[] bArr = this.zzse;
            byte[] bArr2 = zzfrVar.zzse;
            int zzeu = zzeu() + r8;
            int zzeu2 = zzeu();
            int zzeu3 = zzfrVar.zzeu();
            while (zzeu2 < zzeu) {
                if (bArr[zzeu2] != bArr2[zzeu3]) {
                    return false;
                }
                zzeu2++;
                zzeu3++;
            }
            return true;
        } else {
            return zzfhVar.zzf(0, r8).equals(zzf(0, r8));
        }
    }

    @Override // com.google.android.gms.internal.vision.zzfh
    protected final int zzb(int r2, int r3, int r4) {
        return zzgt.zza(r2, this.zzse, zzeu(), r4);
    }
}
