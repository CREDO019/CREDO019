package com.google.android.gms.internal.ads;

import android.content.Context;
import java.util.Collections;
import java.util.List;
import javax.annotation.ParametersAreNonnullByDefault;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzdyl implements zzdft, com.google.android.gms.ads.internal.client.zza, zzdcy, zzdds, zzddt, zzdem, zzddb, zzasb, zzfhq {
    private final List zza;
    private final zzdxz zzb;
    private long zzc;

    public zzdyl(zzdxz zzdxzVar, zzcok zzcokVar) {
        this.zzb = zzdxzVar;
        this.zza = Collections.singletonList(zzcokVar);
    }

    private final void zze(Class cls, String str, Object... objArr) {
        this.zzb.zza(this.zza, "Event-".concat(String.valueOf(cls.getSimpleName())), str, objArr);
    }

    @Override // com.google.android.gms.ads.internal.client.zza
    public final void onAdClicked() {
        zze(com.google.android.gms.ads.internal.client.zza.class, "onAdClicked", new Object[0]);
    }

    @Override // com.google.android.gms.internal.ads.zzddb
    public final void zza(com.google.android.gms.ads.internal.client.zze zzeVar) {
        zze(zzddb.class, "onAdFailedToLoad", Integer.valueOf(zzeVar.zza), zzeVar.zzb, zzeVar.zzc);
    }

    @Override // com.google.android.gms.internal.ads.zzdft
    public final void zzb(zzfde zzfdeVar) {
    }

    @Override // com.google.android.gms.internal.ads.zzdft
    public final void zzbE(zzcba zzcbaVar) {
        this.zzc = com.google.android.gms.ads.internal.zzt.zzB().elapsedRealtime();
        zze(zzdft.class, "onAdRequest", new Object[0]);
    }

    @Override // com.google.android.gms.internal.ads.zzfhq
    public final void zzbF(zzfhj zzfhjVar, String str) {
        zze(zzfhi.class, "onTaskCreated", str);
    }

    @Override // com.google.android.gms.internal.ads.zzfhq
    public final void zzbG(zzfhj zzfhjVar, String str, Throwable th) {
        zze(zzfhi.class, "onTaskFailed", str, th.getClass().getSimpleName());
    }

    @Override // com.google.android.gms.internal.ads.zzddt
    public final void zzbq(Context context) {
        zze(zzddt.class, "onDestroy", context);
    }

    @Override // com.google.android.gms.internal.ads.zzddt
    public final void zzbs(Context context) {
        zze(zzddt.class, "onPause", context);
    }

    @Override // com.google.android.gms.internal.ads.zzddt
    public final void zzbt(Context context) {
        zze(zzddt.class, "onResume", context);
    }

    @Override // com.google.android.gms.internal.ads.zzasb
    public final void zzbu(String str, String str2) {
        zze(zzasb.class, "onAppEvent", str, str2);
    }

    @Override // com.google.android.gms.internal.ads.zzdcy
    public final void zzbv() {
        zze(zzdcy.class, "onRewardedVideoCompleted", new Object[0]);
    }

    @Override // com.google.android.gms.internal.ads.zzfhq
    public final void zzc(zzfhj zzfhjVar, String str) {
        zze(zzfhi.class, "onTaskStarted", str);
    }

    @Override // com.google.android.gms.internal.ads.zzfhq
    public final void zzd(zzfhj zzfhjVar, String str) {
        zze(zzfhi.class, "onTaskSucceeded", str);
    }

    @Override // com.google.android.gms.internal.ads.zzdcy
    public final void zzj() {
        zze(zzdcy.class, "onAdClosed", new Object[0]);
    }

    @Override // com.google.android.gms.internal.ads.zzdds
    public final void zzl() {
        zze(zzdds.class, "onAdImpression", new Object[0]);
    }

    @Override // com.google.android.gms.internal.ads.zzdcy
    public final void zzm() {
        zze(zzdcy.class, "onAdLeftApplication", new Object[0]);
    }

    @Override // com.google.android.gms.internal.ads.zzdem
    public final void zzn() {
        long elapsedRealtime = com.google.android.gms.ads.internal.zzt.zzB().elapsedRealtime();
        long j = this.zzc;
        com.google.android.gms.ads.internal.util.zze.zza("Ad Request Latency : " + (elapsedRealtime - j));
        zze(zzdem.class, "onAdLoaded", new Object[0]);
    }

    @Override // com.google.android.gms.internal.ads.zzdcy
    public final void zzo() {
        zze(zzdcy.class, "onAdOpened", new Object[0]);
    }

    @Override // com.google.android.gms.internal.ads.zzdcy
    @ParametersAreNonnullByDefault
    public final void zzp(zzcbq zzcbqVar, String str, String str2) {
        zze(zzdcy.class, "onRewarded", zzcbqVar, str, str2);
    }

    @Override // com.google.android.gms.internal.ads.zzdcy
    public final void zzr() {
        zze(zzdcy.class, "onRewardedVideoStarted", new Object[0]);
    }
}
