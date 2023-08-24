package com.google.android.gms.internal.ads;

import android.content.Context;
import android.webkit.CookieManager;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzebc implements zzgur {
    private final zzgve zza;
    private final zzgve zzb;

    public zzebc(zzgve zzgveVar, zzgve zzgveVar2) {
        this.zza = zzgveVar;
        this.zzb = zzgveVar2;
    }

    @Override // com.google.android.gms.internal.ads.zzgve
    public final /* bridge */ /* synthetic */ Object zzb() {
        final CookieManager zzb = com.google.android.gms.ads.internal.zzt.zzr().zzb((Context) this.zzb.zzb());
        zzfhj zzfhjVar = zzfhj.WEBVIEW_COOKIE;
        zzfhg zzi = zzfgz.zza(new Callable() { // from class: com.google.android.gms.internal.ads.zzeaz
            @Override // java.util.concurrent.Callable
            public final Object call() {
                CookieManager cookieManager = zzb;
                if (cookieManager == null) {
                    return "";
                }
                return cookieManager.getCookie((String) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzaH));
            }
        }, zzfhjVar, (zzfhp) this.zza.zzb()).zzi(1L, TimeUnit.SECONDS);
        final zzeba zzebaVar = new zzfgs() { // from class: com.google.android.gms.internal.ads.zzeba
            @Override // com.google.android.gms.internal.ads.zzfgs
            public final Object zza(Object obj) {
                Exception exc = (Exception) obj;
                return "";
            }
        };
        return zzi.zzc(Exception.class, new zzfxv() { // from class: com.google.android.gms.internal.ads.zzfhc
            @Override // com.google.android.gms.internal.ads.zzfxv
            public final zzfyx zza(Object obj) {
                Exception exc = (Exception) ((Throwable) obj);
                return zzfyo.zzi("");
            }
        }).zza();
    }
}
