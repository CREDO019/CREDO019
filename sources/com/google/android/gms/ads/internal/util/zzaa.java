package com.google.android.gms.ads.internal.util;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.media.AudioManager;
import android.telephony.TelephonyManager;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebResourceResponse;
import androidx.autofill.HintConstants;
import com.google.android.gms.internal.ads.zzbel;
import com.google.android.gms.internal.ads.zzcmn;
import com.google.android.gms.internal.ads.zzcmu;
import com.google.android.gms.internal.ads.zzcnp;
import java.io.InputStream;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public class zzaa {
    private zzaa() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzaa(zzz zzzVar) {
    }

    public static zzaa zzm(int r1) {
        return r1 >= 30 ? new zzy() : r1 >= 28 ? new zzx() : r1 >= 26 ? new zzv() : r1 >= 24 ? new zzu() : r1 >= 21 ? new zzt() : new zzaa();
    }

    public int zza() {
        return 1;
    }

    public CookieManager zzb(Context context) {
        com.google.android.gms.ads.internal.zzt.zzq();
        if (zzs.zzB()) {
            return null;
        }
        try {
            CookieSyncManager.createInstance(context);
            return CookieManager.getInstance();
        } catch (Throwable th) {
            zze.zzh("Failed to obtain CookieManager.", th);
            com.google.android.gms.ads.internal.zzt.zzp().zzt(th, "ApiLevelUtil.getCookieManager");
            return null;
        }
    }

    public WebResourceResponse zzc(String str, String str2, int r3, String str3, Map map, InputStream inputStream) {
        return new WebResourceResponse(str, str2, inputStream);
    }

    public zzcmu zzd(zzcmn zzcmnVar, zzbel zzbelVar, boolean z) {
        return new zzcnp(zzcmnVar, zzbelVar, z);
    }

    public boolean zze(Activity activity, Configuration configuration) {
        return false;
    }

    public void zzg(Context context) {
    }

    public int zzh(Context context, TelephonyManager telephonyManager) {
        return 1001;
    }

    public int zzi(AudioManager audioManager) {
        return 0;
    }

    public void zzj(Activity activity) {
    }

    public int zzl(Context context) {
        return ((TelephonyManager) context.getSystemService(HintConstants.AUTOFILL_HINT_PHONE)).getNetworkType();
    }
}
