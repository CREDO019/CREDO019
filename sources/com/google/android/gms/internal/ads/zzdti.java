package com.google.android.gms.internal.ads;

import android.content.Context;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzdti implements Callable {
    private final com.google.android.gms.ads.internal.zza zza;
    private final zzcmz zzb;
    private final Context zzc;
    private final zzdxo zzd;
    private final zzfhz zze;
    private final zzefz zzf;
    private final Executor zzg;
    private final zzapb zzh;
    private final zzcgt zzi;
    private final zzfju zzj;

    public zzdti(Context context, Executor executor, zzapb zzapbVar, zzcgt zzcgtVar, com.google.android.gms.ads.internal.zza zzaVar, zzcmz zzcmzVar, zzefz zzefzVar, zzfju zzfjuVar, zzdxo zzdxoVar, zzfhz zzfhzVar) {
        this.zzc = context;
        this.zzg = executor;
        this.zzh = zzapbVar;
        this.zzi = zzcgtVar;
        this.zza = zzaVar;
        this.zzb = zzcmzVar;
        this.zzf = zzefzVar;
        this.zzj = zzfjuVar;
        this.zzd = zzdxoVar;
        this.zze = zzfhzVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* bridge */ /* synthetic */ Context zza(zzdti zzdtiVar) {
        return zzdtiVar.zzc;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* bridge */ /* synthetic */ zzapb zzb(zzdti zzdtiVar) {
        return zzdtiVar.zzh;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* bridge */ /* synthetic */ com.google.android.gms.ads.internal.zza zzc(zzdti zzdtiVar) {
        return zzdtiVar.zza;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* bridge */ /* synthetic */ zzcgt zzd(zzdti zzdtiVar) {
        return zzdtiVar.zzi;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* bridge */ /* synthetic */ zzcmz zze(zzdti zzdtiVar) {
        return zzdtiVar.zzb;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* bridge */ /* synthetic */ zzdxo zzf(zzdti zzdtiVar) {
        return zzdtiVar.zzd;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* bridge */ /* synthetic */ zzefz zzg(zzdti zzdtiVar) {
        return zzdtiVar.zzf;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* bridge */ /* synthetic */ zzfhz zzh(zzdti zzdtiVar) {
        return zzdtiVar.zze;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* bridge */ /* synthetic */ zzfju zzi(zzdti zzdtiVar) {
        return zzdtiVar.zzj;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* bridge */ /* synthetic */ Executor zzj(zzdti zzdtiVar) {
        return zzdtiVar.zzg;
    }

    @Override // java.util.concurrent.Callable
    public final /* bridge */ /* synthetic */ Object call() throws Exception {
        zzdtl zzdtlVar = new zzdtl(this);
        zzdtlVar.zzh();
        return zzdtlVar;
    }
}
