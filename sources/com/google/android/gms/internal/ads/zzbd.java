package com.google.android.gms.internal.ads;

import android.net.Uri;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzbd {
    public static final zzbd zza = new zzbd(new zzbc());
    public static final zzn zzb = new zzn() { // from class: com.google.android.gms.internal.ads.zzbb
    };
    public final Uri zzc = null;
    public final String zzd = null;

    private zzbd(zzbc zzbcVar) {
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof zzbd) {
            zzbd zzbdVar = (zzbd) obj;
            Uri uri = zzbdVar.zzc;
            if (zzel.zzT(null, null)) {
                String str = zzbdVar.zzd;
                if (zzel.zzT(null, null)) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    public final int hashCode() {
        return 0;
    }
}
