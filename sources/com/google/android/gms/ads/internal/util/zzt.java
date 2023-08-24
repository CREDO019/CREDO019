package com.google.android.gms.ads.internal.util;

import android.content.Context;
import android.webkit.CookieManager;
import android.webkit.WebResourceResponse;
import com.google.android.gms.internal.ads.zzbel;
import com.google.android.gms.internal.ads.zzcmn;
import com.google.android.gms.internal.ads.zzcmu;
import com.google.android.gms.internal.ads.zzcnr;
import java.io.InputStream;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public class zzt extends zzaa {
    public zzt() {
        super(null);
    }

    @Override // com.google.android.gms.ads.internal.util.zzaa
    public final int zza() {
        return 16974374;
    }

    @Override // com.google.android.gms.ads.internal.util.zzaa
    public final CookieManager zzb(Context context) {
        com.google.android.gms.ads.internal.zzt.zzq();
        if (zzs.zzB()) {
            return null;
        }
        try {
            return CookieManager.getInstance();
        } catch (Throwable th) {
            zze.zzh("Failed to obtain CookieManager.", th);
            com.google.android.gms.ads.internal.zzt.zzp().zzt(th, "ApiLevelUtil.getCookieManager");
            return null;
        }
    }

    @Override // com.google.android.gms.ads.internal.util.zzaa
    public final WebResourceResponse zzc(String str, String str2, int r11, String str3, Map map, InputStream inputStream) {
        return new WebResourceResponse(str, str2, r11, str3, map, inputStream);
    }

    @Override // com.google.android.gms.ads.internal.util.zzaa
    public final zzcmu zzd(zzcmn zzcmnVar, zzbel zzbelVar, boolean z) {
        return new zzcnr(zzcmnVar, zzbelVar, z);
    }
}
