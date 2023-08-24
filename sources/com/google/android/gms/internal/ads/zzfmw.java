package com.google.android.gms.internal.ads;

import androidx.exifinterface.media.ExifInterface;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzfmw implements zzfmx {
    private static final zzamx zza;

    static {
        zzamh zza2 = zzamx.zza();
        zza2.zzw(ExifInterface.LONGITUDE_EAST);
        zza = (zzamx) zza2.zzal();
    }

    @Override // com.google.android.gms.internal.ads.zzfmx
    public final zzamx zza() {
        return zza;
    }
}
