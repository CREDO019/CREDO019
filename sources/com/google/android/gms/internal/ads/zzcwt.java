package com.google.android.gms.internal.ads;

import android.content.Context;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzcwt extends zzczc {
    private final zzcmn zzc;
    private final int zzd;
    private final Context zze;
    private final zzcwb zzf;
    private final zzdmn zzg;
    private final zzdju zzh;
    private final zzddl zzi;
    private final boolean zzj;
    private boolean zzk;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzcwt(zzczb zzczbVar, Context context, zzcmn zzcmnVar, int r4, zzcwb zzcwbVar, zzdmn zzdmnVar, zzdju zzdjuVar, zzddl zzddlVar) {
        super(zzczbVar);
        this.zzk = false;
        this.zzc = zzcmnVar;
        this.zze = context;
        this.zzd = r4;
        this.zzf = zzcwbVar;
        this.zzg = zzdmnVar;
        this.zzh = zzdjuVar;
        this.zzi = zzddlVar;
        this.zzj = ((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzeq)).booleanValue();
    }

    @Override // com.google.android.gms.internal.ads.zzczc
    public final void zzV() {
        super.zzV();
        zzcmn zzcmnVar = this.zzc;
        if (zzcmnVar != null) {
            zzcmnVar.destroy();
        }
    }

    public final int zza() {
        return this.zzd;
    }

    public final void zzc(zzbcz zzbczVar) {
        zzcmn zzcmnVar = this.zzc;
        if (zzcmnVar != null) {
            zzcmnVar.zzaj(zzbczVar);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:0:?, code lost:
        r3 = r3;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v11, types: [android.content.Context] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void zzd(android.app.Activity r3, com.google.android.gms.internal.ads.zzbdm r4, boolean r5) throws android.os.RemoteException {
        /*
            r2 = this;
            if (r3 != 0) goto L4
            android.content.Context r3 = r2.zze
        L4:
            boolean r4 = r2.zzj
            if (r4 == 0) goto Ld
            com.google.android.gms.internal.ads.zzdju r4 = r2.zzh
            r4.zzb()
        Ld:
            com.google.android.gms.internal.ads.zzbiq r4 = com.google.android.gms.internal.ads.zzbiy.zzay
            com.google.android.gms.internal.ads.zzbiw r0 = com.google.android.gms.ads.internal.client.zzay.zzc()
            java.lang.Object r4 = r0.zzb(r4)
            java.lang.Boolean r4 = (java.lang.Boolean) r4
            boolean r4 = r4.booleanValue()
            if (r4 == 0) goto L61
            com.google.android.gms.ads.internal.zzt.zzq()
            boolean r4 = com.google.android.gms.ads.internal.util.zzs.zzC(r3)
            if (r4 == 0) goto L61
            java.lang.String r4 = "Interstitials that show when your app is in the background are a violation of AdMob policies and may lead to blocked ad serving. To learn more, visit  https://googlemobileadssdk.page.link/admob-interstitial-policies"
            com.google.android.gms.ads.internal.util.zze.zzj(r4)
            com.google.android.gms.internal.ads.zzddl r4 = r2.zzi
            r4.zzb()
            com.google.android.gms.internal.ads.zzbiq r4 = com.google.android.gms.internal.ads.zzbiy.zzaz
            com.google.android.gms.internal.ads.zzbiw r5 = com.google.android.gms.ads.internal.client.zzay.zzc()
            java.lang.Object r4 = r5.zzb(r4)
            java.lang.Boolean r4 = (java.lang.Boolean) r4
            boolean r4 = r4.booleanValue()
            if (r4 == 0) goto L94
            com.google.android.gms.internal.ads.zzfmq r4 = new com.google.android.gms.internal.ads.zzfmq
            android.content.Context r3 = r3.getApplicationContext()
            com.google.android.gms.ads.internal.util.zzbv r5 = com.google.android.gms.ads.internal.zzt.zzu()
            android.os.Looper r5 = r5.zzb()
            r4.<init>(r3, r5)
            com.google.android.gms.internal.ads.zzfde r3 = r2.zza
            com.google.android.gms.internal.ads.zzfdd r3 = r3.zzb
            com.google.android.gms.internal.ads.zzfcv r3 = r3.zzb
            java.lang.String r3 = r3.zzb
            r4.zza(r3)
            return
        L61:
            boolean r4 = r2.zzk
            if (r4 == 0) goto L76
            java.lang.String r4 = "App open interstitial ad is already visible."
            com.google.android.gms.ads.internal.util.zze.zzj(r4)
            com.google.android.gms.internal.ads.zzddl r4 = r2.zzi
            r0 = 10
            r1 = 0
            com.google.android.gms.ads.internal.client.zze r0 = com.google.android.gms.internal.ads.zzfem.zzd(r0, r1, r1)
            r4.zza(r0)
        L76:
            boolean r4 = r2.zzk
            if (r4 != 0) goto L94
            com.google.android.gms.internal.ads.zzdmn r4 = r2.zzg     // Catch: com.google.android.gms.internal.ads.zzdmm -> L8e
            com.google.android.gms.internal.ads.zzddl r0 = r2.zzi     // Catch: com.google.android.gms.internal.ads.zzdmm -> L8e
            r4.zza(r5, r3, r0)     // Catch: com.google.android.gms.internal.ads.zzdmm -> L8e
            boolean r3 = r2.zzj     // Catch: com.google.android.gms.internal.ads.zzdmm -> L8e
            if (r3 == 0) goto L8a
            com.google.android.gms.internal.ads.zzdju r3 = r2.zzh     // Catch: com.google.android.gms.internal.ads.zzdmm -> L8e
            r3.zza()     // Catch: com.google.android.gms.internal.ads.zzdmm -> L8e
        L8a:
            r3 = 1
            r2.zzk = r3
            return
        L8e:
            r3 = move-exception
            com.google.android.gms.internal.ads.zzddl r4 = r2.zzi
            r4.zzc(r3)
        L94:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzcwt.zzd(android.app.Activity, com.google.android.gms.internal.ads.zzbdm, boolean):void");
    }

    public final void zze(long j, int r4) {
        this.zzf.zza(j, r4);
    }
}
