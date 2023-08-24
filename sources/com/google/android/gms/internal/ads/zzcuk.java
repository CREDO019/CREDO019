package com.google.android.gms.internal.ads;

import android.content.Context;
import android.text.TextUtils;
import android.webkit.CookieManager;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzcuk implements zzctu {
    private final CookieManager zza;

    public zzcuk(Context context) {
        this.zza = com.google.android.gms.ads.internal.zzt.zzr().zzb(context);
    }

    @Override // com.google.android.gms.internal.ads.zzctu
    public final void zza(Map map) {
        if (this.zza == null) {
            return;
        }
        if (((String) map.get("clear")) != null) {
            String str = (String) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzaH);
            String cookie = this.zza.getCookie(str);
            if (cookie == null) {
                return;
            }
            List zzf = zzfss.zzc(zzfrr.zzc(';')).zzf(cookie);
            for (int r2 = 0; r2 < zzf.size(); r2++) {
                CookieManager cookieManager = this.zza;
                Iterator it = zzfss.zzc(zzfrr.zzc('=')).zzd((String) zzf.get(r2)).iterator();
                Objects.requireNonNull(it);
                if (it.hasNext()) {
                    cookieManager.setCookie(str, String.valueOf((String) it.next()).concat(String.valueOf((String) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzau))));
                } else {
                    throw new IndexOutOfBoundsException("position (0) must be less than the number of elements that remained (0)");
                }
            }
            return;
        }
        String str2 = (String) map.get("cookie");
        if (TextUtils.isEmpty(str2)) {
            return;
        }
        this.zza.setCookie((String) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzaH), str2);
    }
}
