package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Bundle;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzdck {
    private final Context zza;
    private final zzfdn zzb;
    private final Bundle zzc;
    private final zzfdf zzd;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzdck(zzdci zzdciVar, zzdcj zzdcjVar) {
        this.zza = zzdci.zza(zzdciVar);
        this.zzb = zzdci.zzi(zzdciVar);
        this.zzc = zzdci.zzb(zzdciVar);
        this.zzd = zzdci.zzh(zzdciVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Context zza(Context context) {
        return this.zza;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Bundle zzb() {
        return this.zzc;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final zzdci zzc() {
        zzdci zzdciVar = new zzdci();
        zzdciVar.zzc(this.zza);
        zzdciVar.zzf(this.zzb);
        zzdciVar.zzd(this.zzc);
        return zzdciVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final zzfdf zzd() {
        return this.zzd;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final zzfdn zze() {
        return this.zzb;
    }
}
