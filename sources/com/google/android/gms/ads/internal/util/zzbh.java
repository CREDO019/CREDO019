package com.google.android.gms.ads.internal.util;

import com.google.android.gms.internal.ads.zzake;
import com.google.android.gms.internal.ads.zzakj;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzbh implements zzake {
    final /* synthetic */ String zza;
    final /* synthetic */ zzbl zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzbh(zzbo zzboVar, String str, zzbl zzblVar) {
        this.zza = str;
        this.zzb = zzblVar;
    }

    @Override // com.google.android.gms.internal.ads.zzake
    public final void zza(zzakj zzakjVar) {
        String str = this.zza;
        String zzakjVar2 = zzakjVar.toString();
        zze.zzj("Failed to load URL: " + str + "\n" + zzakjVar2);
        this.zzb.zza((Object) null);
    }
}
