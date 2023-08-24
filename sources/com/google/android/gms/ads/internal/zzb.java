package com.google.android.gms.ads.internal;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import com.google.android.gms.internal.ads.zzcao;
import com.google.android.gms.internal.ads.zzcdo;
import java.util.Collections;
import java.util.List;
import javax.annotation.ParametersAreNonnullByDefault;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
@ParametersAreNonnullByDefault
/* loaded from: classes2.dex */
public final class zzb {
    private final Context zza;
    private boolean zzb;
    private final zzcdo zzc;
    private final zzcao zzd = new zzcao(false, Collections.emptyList());

    public zzb(Context context, zzcdo zzcdoVar, zzcao zzcaoVar) {
        this.zza = context;
        this.zzc = zzcdoVar;
    }

    private final boolean zzd() {
        zzcdo zzcdoVar = this.zzc;
        return (zzcdoVar != null && zzcdoVar.zza().zzf) || this.zzd.zza;
    }

    public final void zza() {
        this.zzb = true;
    }

    public final void zzb(String str) {
        List<String> list;
        if (zzd()) {
            if (str == null) {
                str = "";
            }
            zzcdo zzcdoVar = this.zzc;
            if (zzcdoVar != null) {
                zzcdoVar.zzd(str, null, 3);
                return;
            }
            zzcao zzcaoVar = this.zzd;
            if (!zzcaoVar.zza || (list = zzcaoVar.zzb) == null) {
                return;
            }
            for (String str2 : list) {
                if (!TextUtils.isEmpty(str2)) {
                    String replace = str2.replace("{NAVIGATION_URL}", Uri.encode(str));
                    zzt.zzq();
                    com.google.android.gms.ads.internal.util.zzs.zzH(this.zza, "", replace);
                }
            }
        }
    }

    public final boolean zzc() {
        return !zzd() || this.zzb;
    }
}
