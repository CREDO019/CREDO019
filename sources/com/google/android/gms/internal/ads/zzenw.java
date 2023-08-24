package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzenw extends com.google.android.gms.ads.internal.client.zzbr {
    private final com.google.android.gms.ads.internal.client.zzq zza;
    private final Context zzb;
    private final zzfav zzc;
    private final String zzd;
    private final zzcgt zze;
    private final zzeno zzf;
    private final zzfbv zzg;
    private zzdle zzh;
    private boolean zzi = ((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzaA)).booleanValue();

    public zzenw(Context context, com.google.android.gms.ads.internal.client.zzq zzqVar, String str, zzfav zzfavVar, zzeno zzenoVar, zzfbv zzfbvVar, zzcgt zzcgtVar) {
        this.zza = zzqVar;
        this.zzd = str;
        this.zzb = context;
        this.zzc = zzfavVar;
        this.zzf = zzenoVar;
        this.zzg = zzfbvVar;
        this.zze = zzcgtVar;
    }

    private final synchronized boolean zze() {
        boolean z;
        zzdle zzdleVar = this.zzh;
        if (zzdleVar != null) {
            z = zzdleVar.zza() ? false : true;
        }
        return z;
    }

    @Override // com.google.android.gms.ads.internal.client.zzbs
    public final void zzA() {
    }

    @Override // com.google.android.gms.ads.internal.client.zzbs
    public final synchronized void zzB() {
        Preconditions.checkMainThread("resume must be called on the main UI thread.");
        zzdle zzdleVar = this.zzh;
        if (zzdleVar != null) {
            zzdleVar.zzm().zzc(null);
        }
    }

    @Override // com.google.android.gms.ads.internal.client.zzbs
    public final void zzC(com.google.android.gms.ads.internal.client.zzbc zzbcVar) {
    }

    @Override // com.google.android.gms.ads.internal.client.zzbs
    public final void zzD(com.google.android.gms.ads.internal.client.zzbf zzbfVar) {
        Preconditions.checkMainThread("setAdListener must be called on the main UI thread.");
        this.zzf.zze(zzbfVar);
    }

    @Override // com.google.android.gms.ads.internal.client.zzbs
    public final void zzE(com.google.android.gms.ads.internal.client.zzbw zzbwVar) {
        Preconditions.checkMainThread("setAdMetadataListener must be called on the main UI thread.");
    }

    @Override // com.google.android.gms.ads.internal.client.zzbs
    public final void zzF(com.google.android.gms.ads.internal.client.zzq zzqVar) {
    }

    @Override // com.google.android.gms.ads.internal.client.zzbs
    public final void zzG(com.google.android.gms.ads.internal.client.zzbz zzbzVar) {
        Preconditions.checkMainThread("setAppEventListener must be called on the main UI thread.");
        this.zzf.zzi(zzbzVar);
    }

    @Override // com.google.android.gms.ads.internal.client.zzbs
    public final void zzH(zzbdi zzbdiVar) {
    }

    @Override // com.google.android.gms.ads.internal.client.zzbs
    public final void zzI(com.google.android.gms.ads.internal.client.zzw zzwVar) {
    }

    @Override // com.google.android.gms.ads.internal.client.zzbs
    public final void zzJ(com.google.android.gms.ads.internal.client.zzcg zzcgVar) {
        this.zzf.zzs(zzcgVar);
    }

    @Override // com.google.android.gms.ads.internal.client.zzbs
    public final void zzK(com.google.android.gms.ads.internal.client.zzdo zzdoVar) {
    }

    @Override // com.google.android.gms.ads.internal.client.zzbs
    public final synchronized void zzL(boolean z) {
        Preconditions.checkMainThread("setImmersiveMode must be called on the main UI thread.");
        this.zzi = z;
    }

    @Override // com.google.android.gms.ads.internal.client.zzbs
    public final void zzM(zzbzj zzbzjVar) {
    }

    @Override // com.google.android.gms.ads.internal.client.zzbs
    public final void zzN(boolean z) {
    }

    @Override // com.google.android.gms.ads.internal.client.zzbs
    public final synchronized void zzO(zzbjt zzbjtVar) {
        Preconditions.checkMainThread("setOnCustomRenderedAdLoadedListener must be called on the main UI thread.");
        this.zzc.zzi(zzbjtVar);
    }

    @Override // com.google.android.gms.ads.internal.client.zzbs
    public final void zzP(com.google.android.gms.ads.internal.client.zzde zzdeVar) {
        Preconditions.checkMainThread("setPaidEventListener must be called on the main UI thread.");
        this.zzf.zzh(zzdeVar);
    }

    @Override // com.google.android.gms.ads.internal.client.zzbs
    public final void zzQ(zzbzm zzbzmVar, String str) {
    }

    @Override // com.google.android.gms.ads.internal.client.zzbs
    public final void zzR(String str) {
    }

    @Override // com.google.android.gms.ads.internal.client.zzbs
    public final void zzS(zzcbw zzcbwVar) {
        this.zzg.zzf(zzcbwVar);
    }

    @Override // com.google.android.gms.ads.internal.client.zzbs
    public final void zzT(String str) {
    }

    @Override // com.google.android.gms.ads.internal.client.zzbs
    public final void zzU(com.google.android.gms.ads.internal.client.zzff zzffVar) {
    }

    @Override // com.google.android.gms.ads.internal.client.zzbs
    public final synchronized void zzW(IObjectWrapper iObjectWrapper) {
        if (this.zzh == null) {
            com.google.android.gms.ads.internal.util.zze.zzj("Interstitial can not be shown before loaded.");
            this.zzf.zzk(zzfem.zzd(9, null, null));
            return;
        }
        this.zzh.zzc(this.zzi, (Activity) ObjectWrapper.unwrap(iObjectWrapper));
    }

    @Override // com.google.android.gms.ads.internal.client.zzbs
    public final synchronized void zzX() {
        Preconditions.checkMainThread("showInterstitial must be called on the main UI thread.");
        zzdle zzdleVar = this.zzh;
        if (zzdleVar == null) {
            com.google.android.gms.ads.internal.util.zze.zzj("Interstitial can not be shown before loaded.");
            this.zzf.zzk(zzfem.zzd(9, null, null));
            return;
        }
        zzdleVar.zzc(this.zzi, null);
    }

    @Override // com.google.android.gms.ads.internal.client.zzbs
    public final synchronized boolean zzY() {
        return this.zzc.zza();
    }

    @Override // com.google.android.gms.ads.internal.client.zzbs
    public final synchronized boolean zzZ() {
        Preconditions.checkMainThread("isLoaded must be called on the main UI thread.");
        return zze();
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x006b A[DONT_GENERATE] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x006d A[Catch: all -> 0x008c, TRY_ENTER, TRY_LEAVE, TryCatch #0 {, blocks: (B:3:0x0001, B:5:0x0010, B:9:0x0025, B:13:0x0042, B:15:0x004e, B:17:0x0052, B:19:0x005b, B:22:0x0065, B:26:0x006d, B:12:0x003d), top: B:32:0x0001 }] */
    @Override // com.google.android.gms.ads.internal.client.zzbs
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final synchronized boolean zzaa(com.google.android.gms.ads.internal.client.zzl r6) {
        /*
            r5 = this;
            monitor-enter(r5)
            com.google.android.gms.internal.ads.zzbka r0 = com.google.android.gms.internal.ads.zzbkm.zzi     // Catch: java.lang.Throwable -> L8c
            java.lang.Object r0 = r0.zze()     // Catch: java.lang.Throwable -> L8c
            java.lang.Boolean r0 = (java.lang.Boolean) r0     // Catch: java.lang.Throwable -> L8c
            boolean r0 = r0.booleanValue()     // Catch: java.lang.Throwable -> L8c
            r1 = 0
            if (r0 == 0) goto L24
            com.google.android.gms.internal.ads.zzbiq r0 = com.google.android.gms.internal.ads.zzbiy.zziG     // Catch: java.lang.Throwable -> L8c
            com.google.android.gms.internal.ads.zzbiw r2 = com.google.android.gms.ads.internal.client.zzay.zzc()     // Catch: java.lang.Throwable -> L8c
            java.lang.Object r0 = r2.zzb(r0)     // Catch: java.lang.Throwable -> L8c
            java.lang.Boolean r0 = (java.lang.Boolean) r0     // Catch: java.lang.Throwable -> L8c
            boolean r0 = r0.booleanValue()     // Catch: java.lang.Throwable -> L8c
            if (r0 == 0) goto L24
            r0 = 1
            goto L25
        L24:
            r0 = 0
        L25:
            com.google.android.gms.internal.ads.zzcgt r2 = r5.zze     // Catch: java.lang.Throwable -> L8c
            int r2 = r2.zzc     // Catch: java.lang.Throwable -> L8c
            com.google.android.gms.internal.ads.zzbiq r3 = com.google.android.gms.internal.ads.zzbiy.zziH     // Catch: java.lang.Throwable -> L8c
            com.google.android.gms.internal.ads.zzbiw r4 = com.google.android.gms.ads.internal.client.zzay.zzc()     // Catch: java.lang.Throwable -> L8c
            java.lang.Object r3 = r4.zzb(r3)     // Catch: java.lang.Throwable -> L8c
            java.lang.Integer r3 = (java.lang.Integer) r3     // Catch: java.lang.Throwable -> L8c
            int r3 = r3.intValue()     // Catch: java.lang.Throwable -> L8c
            if (r2 < r3) goto L3d
            if (r0 != 0) goto L42
        L3d:
            java.lang.String r0 = "loadAd must be called on the main UI thread."
            com.google.android.gms.common.internal.Preconditions.checkMainThread(r0)     // Catch: java.lang.Throwable -> L8c
        L42:
            com.google.android.gms.ads.internal.zzt.zzq()     // Catch: java.lang.Throwable -> L8c
            android.content.Context r0 = r5.zzb     // Catch: java.lang.Throwable -> L8c
            boolean r0 = com.google.android.gms.ads.internal.util.zzs.zzD(r0)     // Catch: java.lang.Throwable -> L8c
            r2 = 0
            if (r0 == 0) goto L65
            com.google.android.gms.ads.internal.client.zzc r0 = r6.zzs     // Catch: java.lang.Throwable -> L8c
            if (r0 != 0) goto L65
            java.lang.String r6 = "Failed to load the ad because app ID is missing."
            com.google.android.gms.ads.internal.util.zze.zzg(r6)     // Catch: java.lang.Throwable -> L8c
            com.google.android.gms.internal.ads.zzeno r6 = r5.zzf     // Catch: java.lang.Throwable -> L8c
            if (r6 == 0) goto L63
            r0 = 4
            com.google.android.gms.ads.internal.client.zze r0 = com.google.android.gms.internal.ads.zzfem.zzd(r0, r2, r2)     // Catch: java.lang.Throwable -> L8c
            r6.zza(r0)     // Catch: java.lang.Throwable -> L8c
        L63:
            monitor-exit(r5)
            return r1
        L65:
            boolean r0 = r5.zze()     // Catch: java.lang.Throwable -> L8c
            if (r0 == 0) goto L6d
            monitor-exit(r5)
            return r1
        L6d:
            android.content.Context r0 = r5.zzb     // Catch: java.lang.Throwable -> L8c
            boolean r1 = r6.zzf     // Catch: java.lang.Throwable -> L8c
            com.google.android.gms.internal.ads.zzfeh.zza(r0, r1)     // Catch: java.lang.Throwable -> L8c
            r5.zzh = r2     // Catch: java.lang.Throwable -> L8c
            com.google.android.gms.internal.ads.zzfav r0 = r5.zzc     // Catch: java.lang.Throwable -> L8c
            java.lang.String r1 = r5.zzd     // Catch: java.lang.Throwable -> L8c
            com.google.android.gms.internal.ads.zzfao r2 = new com.google.android.gms.internal.ads.zzfao     // Catch: java.lang.Throwable -> L8c
            com.google.android.gms.ads.internal.client.zzq r3 = r5.zza     // Catch: java.lang.Throwable -> L8c
            r2.<init>(r3)     // Catch: java.lang.Throwable -> L8c
            com.google.android.gms.internal.ads.zzenv r3 = new com.google.android.gms.internal.ads.zzenv     // Catch: java.lang.Throwable -> L8c
            r3.<init>(r5)     // Catch: java.lang.Throwable -> L8c
            boolean r6 = r0.zzb(r6, r1, r2, r3)     // Catch: java.lang.Throwable -> L8c
            monitor-exit(r5)
            return r6
        L8c:
            r6 = move-exception
            monitor-exit(r5)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzenw.zzaa(com.google.android.gms.ads.internal.client.zzl):boolean");
    }

    @Override // com.google.android.gms.ads.internal.client.zzbs
    public final void zzab(com.google.android.gms.ads.internal.client.zzcd zzcdVar) {
    }

    @Override // com.google.android.gms.ads.internal.client.zzbs
    public final Bundle zzd() {
        Preconditions.checkMainThread("getAdMetadata must be called on the main UI thread.");
        return new Bundle();
    }

    @Override // com.google.android.gms.ads.internal.client.zzbs
    public final com.google.android.gms.ads.internal.client.zzq zzg() {
        return null;
    }

    @Override // com.google.android.gms.ads.internal.client.zzbs
    public final com.google.android.gms.ads.internal.client.zzbf zzi() {
        return this.zzf.zzc();
    }

    @Override // com.google.android.gms.ads.internal.client.zzbs
    public final com.google.android.gms.ads.internal.client.zzbz zzj() {
        return this.zzf.zzd();
    }

    @Override // com.google.android.gms.ads.internal.client.zzbs
    public final synchronized com.google.android.gms.ads.internal.client.zzdh zzk() {
        if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzfN)).booleanValue()) {
            zzdle zzdleVar = this.zzh;
            if (zzdleVar == null) {
                return null;
            }
            return zzdleVar.zzl();
        }
        return null;
    }

    @Override // com.google.android.gms.ads.internal.client.zzbs
    public final com.google.android.gms.ads.internal.client.zzdk zzl() {
        return null;
    }

    @Override // com.google.android.gms.ads.internal.client.zzbs
    public final IObjectWrapper zzn() {
        return null;
    }

    @Override // com.google.android.gms.ads.internal.client.zzbs
    public final synchronized String zzr() {
        return this.zzd;
    }

    @Override // com.google.android.gms.ads.internal.client.zzbs
    public final synchronized String zzs() {
        zzdle zzdleVar = this.zzh;
        if (zzdleVar == null || zzdleVar.zzl() == null) {
            return null;
        }
        return zzdleVar.zzl().zzg();
    }

    @Override // com.google.android.gms.ads.internal.client.zzbs
    public final synchronized String zzt() {
        zzdle zzdleVar = this.zzh;
        if (zzdleVar == null || zzdleVar.zzl() == null) {
            return null;
        }
        return zzdleVar.zzl().zzg();
    }

    @Override // com.google.android.gms.ads.internal.client.zzbs
    public final synchronized void zzx() {
        Preconditions.checkMainThread("destroy must be called on the main UI thread.");
        zzdle zzdleVar = this.zzh;
        if (zzdleVar != null) {
            zzdleVar.zzm().zza(null);
        }
    }

    @Override // com.google.android.gms.ads.internal.client.zzbs
    public final void zzy(com.google.android.gms.ads.internal.client.zzl zzlVar, com.google.android.gms.ads.internal.client.zzbi zzbiVar) {
        this.zzf.zzf(zzbiVar);
        zzaa(zzlVar);
    }

    @Override // com.google.android.gms.ads.internal.client.zzbs
    public final synchronized void zzz() {
        Preconditions.checkMainThread("pause must be called on the main UI thread.");
        zzdle zzdleVar = this.zzh;
        if (zzdleVar != null) {
            zzdleVar.zzm().zzb(null);
        }
    }
}
