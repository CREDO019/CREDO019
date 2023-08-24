package com.google.android.gms.internal.ads;

import android.content.Context;
import android.net.TrafficStats;
import android.os.StrictMode;
import javax.annotation.ParametersAreNonnullByDefault;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
@ParametersAreNonnullByDefault
/* loaded from: classes2.dex */
public final class zzcmz {
    /* JADX WARN: Type inference failed for: r0v2, types: [com.google.android.gms.internal.ads.zzcmn, java.lang.Object] */
    public static final zzcmn zza(final Context context, final zzcoc zzcocVar, final String str, final boolean z, final boolean z2, final zzapb zzapbVar, final zzbjx zzbjxVar, final zzcgt zzcgtVar, zzbjn zzbjnVar, final com.google.android.gms.ads.internal.zzl zzlVar, final com.google.android.gms.ads.internal.zza zzaVar, final zzbel zzbelVar, final zzfcs zzfcsVar, final zzfcv zzfcvVar) throws zzcmy {
        zzbiy.zzc(context);
        try {
            zzfsv zzfsvVar = new zzfsv(context, zzcocVar, str, z, z2, zzapbVar, zzbjxVar, zzcgtVar, null, zzlVar, zzaVar, zzbelVar, zzfcsVar, zzfcvVar) { // from class: com.google.android.gms.internal.ads.zzcmv
                public final /* synthetic */ Context zza;
                public final /* synthetic */ zzcoc zzb;
                public final /* synthetic */ String zzc;
                public final /* synthetic */ boolean zzd;
                public final /* synthetic */ boolean zze;
                public final /* synthetic */ zzapb zzf;
                public final /* synthetic */ zzbjx zzg;
                public final /* synthetic */ zzcgt zzh;
                public final /* synthetic */ com.google.android.gms.ads.internal.zzl zzi;
                public final /* synthetic */ com.google.android.gms.ads.internal.zza zzj;
                public final /* synthetic */ zzbel zzk;
                public final /* synthetic */ zzfcs zzl;
                public final /* synthetic */ zzfcv zzm;

                {
                    this.zzi = zzlVar;
                    this.zzj = zzaVar;
                    this.zzk = zzbelVar;
                    this.zzl = zzfcsVar;
                    this.zzm = zzfcvVar;
                }

                @Override // com.google.android.gms.internal.ads.zzfsv
                public final Object zza() {
                    Context context2 = this.zza;
                    zzcoc zzcocVar2 = this.zzb;
                    String str2 = this.zzc;
                    boolean z3 = this.zzd;
                    boolean z4 = this.zze;
                    zzapb zzapbVar2 = this.zzf;
                    zzbjx zzbjxVar2 = this.zzg;
                    zzcgt zzcgtVar2 = this.zzh;
                    com.google.android.gms.ads.internal.zzl zzlVar2 = this.zzi;
                    com.google.android.gms.ads.internal.zza zzaVar2 = this.zzj;
                    zzbel zzbelVar2 = this.zzk;
                    zzfcs zzfcsVar2 = this.zzl;
                    zzfcv zzfcvVar2 = this.zzm;
                    try {
                        TrafficStats.setThreadStatsTag(264);
                        int r2 = zzcng.zza;
                        zzcnc zzcncVar = new zzcnc(new zzcng(new zzcob(context2), zzcocVar2, str2, z3, z4, zzapbVar2, zzbjxVar2, zzcgtVar2, null, zzlVar2, zzaVar2, zzbelVar2, zzfcsVar2, zzfcvVar2));
                        zzcncVar.setWebViewClient(com.google.android.gms.ads.internal.zzt.zzr().zzd(zzcncVar, zzbelVar2, z4));
                        zzcncVar.setWebChromeClient(new zzcmm(zzcncVar));
                        return zzcncVar;
                    } finally {
                        TrafficStats.clearThreadStatsTag();
                    }
                }
            };
            StrictMode.ThreadPolicy threadPolicy = StrictMode.getThreadPolicy();
            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder(threadPolicy).permitDiskReads().permitDiskWrites().build());
            ?? zza = zzfsvVar.zza();
            StrictMode.setThreadPolicy(threadPolicy);
            return zza;
        } catch (Throwable th) {
            throw new zzcmy("Webview initialization failed.", th);
        }
    }
}
