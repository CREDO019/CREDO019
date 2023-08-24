package com.google.android.gms.internal.clearcut;

import com.google.android.gms.internal.clearcut.zzfu;
import java.io.IOException;

/* loaded from: classes2.dex */
public class zzfu<M extends zzfu<M>> extends zzfz {
    protected zzfw zzrj;

    @Override // com.google.android.gms.internal.clearcut.zzfz
    public void zza(zzfs zzfsVar) throws IOException {
        if (this.zzrj == null) {
            return;
        }
        for (int r0 = 0; r0 < this.zzrj.size(); r0++) {
            this.zzrj.zzaq(r0).zza(zzfsVar);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.clearcut.zzfz
    public int zzen() {
        if (this.zzrj != null) {
            for (int r0 = 0; r0 < this.zzrj.size(); r0++) {
                this.zzrj.zzaq(r0).zzen();
            }
        }
        return 0;
    }

    @Override // com.google.android.gms.internal.clearcut.zzfz
    /* renamed from: zzeo */
    public M clone() throws CloneNotSupportedException {
        M m = (M) super.clone();
        zzfy.zza(this, m);
        return m;
    }

    @Override // com.google.android.gms.internal.clearcut.zzfz
    public /* synthetic */ zzfz zzep() throws CloneNotSupportedException {
        return (zzfu) clone();
    }
}
