package com.google.android.gms.internal.ads;

import java.util.concurrent.Executor;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzezm implements zzfft {
    public final zzfag zza;
    public final zzfai zzb;
    public final com.google.android.gms.ads.internal.client.zzl zzc;
    public final String zzd;
    public final Executor zze;
    public final com.google.android.gms.ads.internal.client.zzw zzf;
    public final zzffi zzg;

    public zzezm(zzfag zzfagVar, zzfai zzfaiVar, com.google.android.gms.ads.internal.client.zzl zzlVar, String str, Executor executor, com.google.android.gms.ads.internal.client.zzw zzwVar, zzffi zzffiVar) {
        this.zza = zzfagVar;
        this.zzb = zzfaiVar;
        this.zzc = zzlVar;
        this.zzd = str;
        this.zze = executor;
        this.zzf = zzwVar;
        this.zzg = zzffiVar;
    }

    @Override // com.google.android.gms.internal.ads.zzfft
    public final zzffi zza() {
        return this.zzg;
    }

    @Override // com.google.android.gms.internal.ads.zzfft
    public final Executor zzb() {
        return this.zze;
    }
}
