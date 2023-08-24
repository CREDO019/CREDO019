package com.google.android.gms.internal.ads;

import java.util.List;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzcdi implements zzfyk {
    final /* synthetic */ zzfyx zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzcdi(zzcdj zzcdjVar, zzfyx zzfyxVar) {
        this.zza = zzfyxVar;
    }

    @Override // com.google.android.gms.internal.ads.zzfyk
    public final void zza(Throwable th) {
        List list;
        list = zzcdj.zzc;
        list.remove(this.zza);
    }

    @Override // com.google.android.gms.internal.ads.zzfyk
    public final /* bridge */ /* synthetic */ void zzb(Object obj) {
        List list;
        Void r2 = (Void) obj;
        list = zzcdj.zzc;
        list.remove(this.zza);
    }
}
