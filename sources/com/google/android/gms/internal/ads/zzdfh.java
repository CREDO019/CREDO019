package com.google.android.gms.internal.ads;

import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzdfh extends zzdih implements zzdds, zzdex {
    private final zzfcs zzb;
    private final AtomicBoolean zzc;

    public zzdfh(Set set, zzfcs zzfcsVar) {
        super(set);
        this.zzc = new AtomicBoolean();
        this.zzb = zzfcsVar;
    }

    private final void zzb() {
        com.google.android.gms.ads.internal.client.zzs zzsVar;
        if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzgE)).booleanValue() && this.zzc.compareAndSet(false, true) && (zzsVar = this.zzb.zzag) != null && zzsVar.zza == 3) {
            zzo(new zzdig() { // from class: com.google.android.gms.internal.ads.zzdfg
                @Override // com.google.android.gms.internal.ads.zzdig
                public final void zza(Object obj) {
                    zzdfh.this.zza((zzdfj) obj);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void zza(zzdfj zzdfjVar) throws Exception {
        zzdfjVar.zzg(this.zzb.zzag);
    }

    @Override // com.google.android.gms.internal.ads.zzdex
    public final void zzh() {
        if (this.zzb.zzb == 1) {
            zzb();
        }
    }

    @Override // com.google.android.gms.internal.ads.zzdds
    public final void zzl() {
        int r0 = this.zzb.zzb;
        if (r0 == 2 || r0 == 5 || r0 == 4 || r0 == 6 || r0 == 7) {
            zzb();
        }
    }
}
