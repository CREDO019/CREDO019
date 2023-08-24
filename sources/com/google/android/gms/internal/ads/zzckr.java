package com.google.android.gms.internal.ads;

import java.util.concurrent.Callable;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzckr extends com.google.android.gms.ads.internal.util.zzb {
    final zzciw zza;
    final zzckz zzb;
    private final String zzc;
    private final String[] zzd;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzckr(zzciw zzciwVar, zzckz zzckzVar, String str, String[] strArr) {
        this.zza = zzciwVar;
        this.zzb = zzckzVar;
        this.zzc = str;
        this.zzd = strArr;
        com.google.android.gms.ads.internal.zzt.zzz().zzb(this);
    }

    @Override // com.google.android.gms.ads.internal.util.zzb
    public final void zza() {
        try {
            this.zzb.zzr(this.zzc, this.zzd);
        } finally {
            com.google.android.gms.ads.internal.util.zzs.zza.post(new zzckq(this));
        }
    }

    @Override // com.google.android.gms.ads.internal.util.zzb
    public final zzfyx zzb() {
        if (!((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzbI)).booleanValue() || !(this.zzb instanceof zzcli)) {
            return super.zzb();
        }
        return zzcha.zze.zzb(new Callable() { // from class: com.google.android.gms.internal.ads.zzckp
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return zzckr.this.zzd();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ Boolean zzd() throws Exception {
        return Boolean.valueOf(this.zzb.zzs(this.zzc, this.zzd, this));
    }

    public final String zze() {
        return this.zzc;
    }
}
