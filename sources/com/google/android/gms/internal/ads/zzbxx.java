package com.google.android.gms.internal.ads;

import android.app.AlertDialog;
import android.content.Context;
import android.content.res.Resources;
import android.net.Uri;
import android.text.TextUtils;
import android.webkit.URLUtil;
import com.google.android.gms.ads.impl.C2114R;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzbxx extends zzbya {
    private final Map zza;
    private final Context zzb;

    public zzbxx(zzcmn zzcmnVar, Map map) {
        super(zzcmnVar, "storePicture");
        this.zza = map;
        this.zzb = zzcmnVar.zzk();
    }

    public final void zzb() {
        if (this.zzb == null) {
            zzg("Activity context is not available");
            return;
        }
        com.google.android.gms.ads.internal.zzt.zzq();
        if (!new zzbii(this.zzb).zzc()) {
            zzg("Feature is not supported by the device.");
            return;
        }
        String str = (String) this.zza.get("iurl");
        if (TextUtils.isEmpty(str)) {
            zzg("Image url cannot be empty.");
        } else if (URLUtil.isValidUrl(str)) {
            String lastPathSegment = Uri.parse(str).getLastPathSegment();
            com.google.android.gms.ads.internal.zzt.zzq();
            if (TextUtils.isEmpty(lastPathSegment) || !lastPathSegment.matches("([^\\s]+(\\.(?i)(jpg|png|gif|bmp|webp))$)")) {
                zzg("Image type not recognized: ".concat(String.valueOf(lastPathSegment)));
                return;
            }
            Resources zzd = com.google.android.gms.ads.internal.zzt.zzp().zzd();
            com.google.android.gms.ads.internal.zzt.zzq();
            AlertDialog.Builder zzG = com.google.android.gms.ads.internal.util.zzs.zzG(this.zzb);
            zzG.setTitle(zzd != null ? zzd.getString(C2114R.string.f256s1) : "Save image");
            zzG.setMessage(zzd != null ? zzd.getString(C2114R.string.f257s2) : "Allow Ad to store image in Picture gallery?");
            zzG.setPositiveButton(zzd != null ? zzd.getString(C2114R.string.f258s3) : "Accept", new zzbxv(this, str, lastPathSegment));
            zzG.setNegativeButton(zzd != null ? zzd.getString(C2114R.string.f259s4) : "Decline", new zzbxw(this));
            zzG.create().show();
        } else {
            zzg("Invalid image url: ".concat(String.valueOf(str)));
        }
    }
}
