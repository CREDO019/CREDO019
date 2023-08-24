package com.google.android.gms.internal.ads;

import java.util.Set;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzddq extends zzdih {
    private boolean zzb;

    public zzddq(Set set) {
        super(set);
        this.zzb = false;
    }

    public final synchronized void zza() {
        if (this.zzb) {
            return;
        }
        zzo(new zzdig() { // from class: com.google.android.gms.internal.ads.zzddp
            @Override // com.google.android.gms.internal.ads.zzdig
            public final void zza(Object obj) {
                ((zzdds) obj).zzl();
            }
        });
        this.zzb = true;
    }
}
