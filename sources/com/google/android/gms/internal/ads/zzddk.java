package com.google.android.gms.internal.ads;

import java.util.Set;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzddk extends zzdih implements zzddc {
    public zzddk(Set set) {
        super(set);
    }

    @Override // com.google.android.gms.internal.ads.zzddc
    public final void zza(final com.google.android.gms.ads.internal.client.zze zzeVar) {
        zzo(new zzdig() { // from class: com.google.android.gms.internal.ads.zzddh
            @Override // com.google.android.gms.internal.ads.zzdig
            public final void zza(Object obj) {
                ((zzddo) obj).zzk(com.google.android.gms.ads.internal.client.zze.this);
            }
        });
    }

    @Override // com.google.android.gms.internal.ads.zzddc
    public final void zzb() {
        zzo(new zzdig() { // from class: com.google.android.gms.internal.ads.zzddi
            @Override // com.google.android.gms.internal.ads.zzdig
            public final void zza(Object obj) {
                ((zzddo) obj).zzk(zzfem.zzd(11, null, null));
            }
        });
    }

    @Override // com.google.android.gms.internal.ads.zzddc
    public final void zzc(final zzdmm zzdmmVar) {
        zzo(new zzdig() { // from class: com.google.android.gms.internal.ads.zzddj
            @Override // com.google.android.gms.internal.ads.zzdig
            public final void zza(Object obj) {
                ((zzddo) obj).zzk(zzfem.zzd(12, zzdmm.this.getMessage(), null));
            }
        });
    }
}
