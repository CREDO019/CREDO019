package com.google.android.gms.internal.ads;

import android.content.Context;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzcuo implements zzddt {
    private final zzfei zza;

    public zzcuo(zzfei zzfeiVar) {
        this.zza = zzfeiVar;
    }

    @Override // com.google.android.gms.internal.ads.zzddt
    public final void zzbq(Context context) {
        try {
            this.zza.zzg();
        } catch (zzfds e) {
            com.google.android.gms.ads.internal.util.zze.zzk("Cannot invoke onDestroy for the mediation adapter.", e);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzddt
    public final void zzbs(Context context) {
        try {
            this.zza.zzs();
        } catch (zzfds e) {
            com.google.android.gms.ads.internal.util.zze.zzk("Cannot invoke onPause for the mediation adapter.", e);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzddt
    public final void zzbt(Context context) {
        try {
            this.zza.zzt();
            if (context != null) {
                this.zza.zzr(context);
            }
        } catch (zzfds e) {
            com.google.android.gms.ads.internal.util.zze.zzk("Cannot invoke onResume for the mediation adapter.", e);
        }
    }
}
