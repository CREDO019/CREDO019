package com.google.android.gms.internal.clearcut;

import java.io.IOException;
import java.nio.charset.Charset;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class zzbi extends zzbh {
    protected final byte[] zzfp;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzbi(byte[] bArr) {
        this.zzfp = bArr;
    }

    @Override // com.google.android.gms.internal.clearcut.zzbb
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if ((obj instanceof zzbb) && size() == ((zzbb) obj).size()) {
            if (size() == 0) {
                return true;
            }
            if (obj instanceof zzbi) {
                zzbi zzbiVar = (zzbi) obj;
                int zzab = zzab();
                int zzab2 = zzbiVar.zzab();
                if (zzab == 0 || zzab2 == 0 || zzab == zzab2) {
                    return zza(zzbiVar, 0, size());
                }
                return false;
            }
            return obj.equals(this);
        }
        return false;
    }

    @Override // com.google.android.gms.internal.clearcut.zzbb
    public int size() {
        return this.zzfp.length;
    }

    @Override // com.google.android.gms.internal.clearcut.zzbb
    protected final int zza(int r2, int r3, int r4) {
        return zzci.zza(r2, this.zzfp, zzac(), r4);
    }

    @Override // com.google.android.gms.internal.clearcut.zzbb
    public final zzbb zza(int r3, int r4) {
        int zzb = zzb(0, r4, size());
        return zzb == 0 ? zzbb.zzfi : new zzbe(this.zzfp, zzac(), zzb);
    }

    @Override // com.google.android.gms.internal.clearcut.zzbb
    protected final String zza(Charset charset) {
        return new String(this.zzfp, zzac(), size(), charset);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.clearcut.zzbb
    public final void zza(zzba zzbaVar) throws IOException {
        zzbaVar.zza(this.zzfp, zzac(), size());
    }

    @Override // com.google.android.gms.internal.clearcut.zzbh
    final boolean zza(zzbb zzbbVar, int r7, int r8) {
        if (r8 > zzbbVar.size()) {
            int size = size();
            StringBuilder sb = new StringBuilder(40);
            sb.append("Length too large: ");
            sb.append(r8);
            sb.append(size);
            throw new IllegalArgumentException(sb.toString());
        } else if (r8 > zzbbVar.size()) {
            int size2 = zzbbVar.size();
            StringBuilder sb2 = new StringBuilder(59);
            sb2.append("Ran off end of other: 0, ");
            sb2.append(r8);
            sb2.append(", ");
            sb2.append(size2);
            throw new IllegalArgumentException(sb2.toString());
        } else if (zzbbVar instanceof zzbi) {
            zzbi zzbiVar = (zzbi) zzbbVar;
            byte[] bArr = this.zzfp;
            byte[] bArr2 = zzbiVar.zzfp;
            int zzac = zzac() + r8;
            int zzac2 = zzac();
            int zzac3 = zzbiVar.zzac();
            while (zzac2 < zzac) {
                if (bArr[zzac2] != bArr2[zzac3]) {
                    return false;
                }
                zzac2++;
                zzac3++;
            }
            return true;
        } else {
            return zzbbVar.zza(0, r8).equals(zza(0, r8));
        }
    }

    @Override // com.google.android.gms.internal.clearcut.zzbb
    public final boolean zzaa() {
        int zzac = zzac();
        return zzff.zze(this.zzfp, zzac, size() + zzac);
    }

    protected int zzac() {
        return 0;
    }

    @Override // com.google.android.gms.internal.clearcut.zzbb
    public byte zzj(int r2) {
        return this.zzfp[r2];
    }
}
