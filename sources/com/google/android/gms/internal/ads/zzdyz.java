package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.dynamic.ObjectWrapper;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzdyz implements zzdyn {
    private final long zza;
    private final zzenw zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzdyz(long j, Context context, zzdys zzdysVar, zzcok zzcokVar, String str) {
        this.zza = j;
        zzfax zzt = zzcokVar.zzt();
        zzt.zzc(context);
        zzt.zza(new com.google.android.gms.ads.internal.client.zzq());
        zzt.zzb(str);
        zzenw zza = zzt.zzd().zza();
        this.zzb = zza;
        zza.zzD(new zzdyy(this, zzdysVar));
    }

    @Override // com.google.android.gms.internal.ads.zzdyn
    public final void zza() {
        this.zzb.zzx();
    }

    @Override // com.google.android.gms.internal.ads.zzdyn
    public final void zzb(com.google.android.gms.ads.internal.client.zzl zzlVar) {
        this.zzb.zzaa(zzlVar);
    }

    @Override // com.google.android.gms.internal.ads.zzdyn
    public final void zzc() {
        this.zzb.zzW(ObjectWrapper.wrap(null));
    }
}
