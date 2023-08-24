package com.google.android.gms.ads.internal.util;

import android.content.Context;
import androidx.browser.trusted.sharing.ShareTarget;
import com.google.android.gms.common.util.ClientLibraryUtils;
import com.google.android.gms.internal.ads.zzaji;
import com.google.android.gms.internal.ads.zzakd;
import com.google.android.gms.internal.ads.zzalh;
import com.google.android.gms.internal.ads.zzbiy;
import com.google.android.gms.internal.ads.zzcgm;
import com.google.android.gms.internal.ads.zzchf;
import com.google.android.gms.internal.ads.zzfyx;
import java.util.Map;
import javax.annotation.ParametersAreNonnullByDefault;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
@ParametersAreNonnullByDefault
/* loaded from: classes2.dex */
public final class zzbo {
    private static zzakd zzb;
    private static final Object zzc = new Object();
    @Deprecated
    public static final zzbj zza = new zzbg();

    public zzbo(Context context) {
        zzakd zza2;
        context = context.getApplicationContext() != null ? context.getApplicationContext() : context;
        synchronized (zzc) {
            if (zzb == null) {
                zzbiy.zzc(context);
                if (!ClientLibraryUtils.isPackageSide()) {
                    if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzdz)).booleanValue()) {
                        zza2 = zzax.zzb(context);
                        zzb = zza2;
                    }
                }
                zza2 = zzalh.zza(context, null);
                zzb = zza2;
            }
        }
    }

    public final zzfyx zza(String str) {
        zzchf zzchfVar = new zzchf();
        zzb.zza(new zzbn(str, null, zzchfVar));
        return zzchfVar;
    }

    public final zzfyx zzb(int r15, String str, Map map, byte[] bArr) {
        zzbl zzblVar = new zzbl(null);
        zzbh zzbhVar = new zzbh(this, str, zzblVar);
        zzcgm zzcgmVar = new zzcgm(null);
        zzbi zzbiVar = new zzbi(this, r15, str, zzblVar, zzbhVar, bArr, map, zzcgmVar);
        if (zzcgm.zzl()) {
            try {
                zzcgmVar.zzd(str, ShareTarget.METHOD_GET, zzbiVar.zzl(), zzbiVar.zzx());
            } catch (zzaji e) {
                zze.zzj(e.getMessage());
            }
        }
        zzb.zza(zzbiVar);
        return zzblVar;
    }
}
