package com.google.android.gms.ads.internal.util;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import androidx.browser.customtabs.CustomTabsIntent;
import com.google.android.gms.internal.ads.zzbju;
import com.google.android.gms.internal.ads.zzbjw;
import com.google.android.gms.internal.ads.zzgvf;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzn implements zzbju {
    final /* synthetic */ zzbjw zza;
    final /* synthetic */ Context zzb;
    final /* synthetic */ Uri zzc;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzn(zzs zzsVar, zzbjw zzbjwVar, Context context, Uri uri) {
        this.zza = zzbjwVar;
        this.zzb = context;
        this.zzc = uri;
    }

    @Override // com.google.android.gms.internal.ads.zzbju
    public final void zza() {
        CustomTabsIntent build = new CustomTabsIntent.Builder(this.zza.zza()).build();
        build.intent.setPackage(zzgvf.zza(this.zzb));
        build.launchUrl(this.zzb, this.zzc);
        this.zza.zzf((Activity) this.zzb);
    }
}
