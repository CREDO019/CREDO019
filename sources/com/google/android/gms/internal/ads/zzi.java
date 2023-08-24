package com.google.android.gms.internal.ads;

import android.media.AudioAttributes;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzi {
    public final AudioAttributes zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzi(zzk zzkVar, zzh zzhVar) {
        AudioAttributes.Builder usage = new AudioAttributes.Builder().setContentType(0).setFlags(0).setUsage(1);
        if (zzel.zza >= 29) {
            zzf.zza(usage, 1);
        }
        if (zzel.zza >= 32) {
            zzg.zza(usage, 0);
        }
        this.zza = usage.build();
    }
}
