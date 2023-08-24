package com.google.android.gms.internal.ads;

import android.net.Uri;
import java.util.Collections;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzaj {
    private String zza;
    private Uri zzb;
    private final zzal zzc = new zzal();
    private final zzar zzd = new zzar(null);
    private final List zze = Collections.emptyList();
    private final zzfuv zzf = zzfuv.zzo();
    private final zzau zzg = new zzau();
    private final zzbd zzh = zzbd.zza;

    public final zzaj zza(String str) {
        this.zza = str;
        return this;
    }

    public final zzaj zzb(Uri uri) {
        this.zzb = uri;
        return this;
    }

    public final zzbg zzc() {
        Uri uri = this.zzb;
        zzba zzbaVar = uri != null ? new zzba(uri, null, null, null, this.zze, null, this.zzf, null, null) : null;
        String str = this.zza;
        if (str == null) {
            str = "";
        }
        return new zzbg(str, new zzap(this.zzc, null), zzbaVar, new zzaw(this.zzg), zzbm.zza, this.zzh, null);
    }
}
