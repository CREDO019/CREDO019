package com.google.android.gms.internal.ads;

import androidx.core.app.NotificationCompat;
import com.google.android.exoplayer2.source.rtsp.SessionDescription;
import java.util.HashMap;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzckt implements Runnable {
    final /* synthetic */ String zza;
    final /* synthetic */ String zzb;
    final /* synthetic */ int zzc;
    final /* synthetic */ int zzd;
    final /* synthetic */ zzckz zze;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzckt(zzckz zzckzVar, String str, String str2, int r4, int r5, boolean z) {
        this.zze = zzckzVar;
        this.zza = str;
        this.zzb = str2;
        this.zzc = r4;
        this.zzd = r5;
    }

    @Override // java.lang.Runnable
    public final void run() {
        HashMap hashMap = new HashMap();
        hashMap.put(NotificationCompat.CATEGORY_EVENT, "precacheProgress");
        hashMap.put("src", this.zza);
        hashMap.put("cachedSrc", this.zzb);
        hashMap.put("bytesLoaded", Integer.toString(this.zzc));
        hashMap.put("totalBytes", Integer.toString(this.zzd));
        hashMap.put("cacheReady", SessionDescription.SUPPORTED_SDP_VERSION);
        zzckz.zza(this.zze, "onPrecacheEvent", hashMap);
    }
}
