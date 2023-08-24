package com.google.android.gms.internal.ads;

import android.content.Context;
import android.view.View;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzemf {
    private final zzdmf zza;

    public zzemf(Context context, zzdmf zzdmfVar) {
        this.zza = zzdmfVar;
    }

    public final /* bridge */ /* synthetic */ Object zza(zzfde zzfdeVar, zzfcs zzfcsVar, View view, zzemb zzembVar) {
        zzdlf zze = this.zza.zze(new zzczr(zzfdeVar, zzfcsVar, null), new zzemd(this, new zzdmn() { // from class: com.google.android.gms.internal.ads.zzemc
            @Override // com.google.android.gms.internal.ads.zzdmn
            public final void zza(boolean z, Context context, zzddl zzddlVar) {
            }
        }));
        zzembVar.zzd(new zzeme(this, zze));
        return zze.zzg();
    }
}
