package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import androidx.browser.customtabs.CustomTabsIntent;
import com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel;
import java.util.concurrent.Executor;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzehz implements zzegk {
    private final Context zza;
    private final zzdmf zzb;
    private final Executor zzc;
    private final zzfcr zzd;

    public zzehz(Context context, Executor executor, zzdmf zzdmfVar, zzfcr zzfcrVar) {
        this.zza = context;
        this.zzb = zzdmfVar;
        this.zzc = executor;
        this.zzd = zzfcrVar;
    }

    private static String zzd(zzfcs zzfcsVar) {
        try {
            return zzfcsVar.zzw.getString("tab_url");
        } catch (Exception unused) {
            return null;
        }
    }

    @Override // com.google.android.gms.internal.ads.zzegk
    public final zzfyx zza(final zzfde zzfdeVar, final zzfcs zzfcsVar) {
        String zzd = zzd(zzfcsVar);
        final Uri parse = zzd != null ? Uri.parse(zzd) : null;
        return zzfyo.zzn(zzfyo.zzi(null), new zzfxv() { // from class: com.google.android.gms.internal.ads.zzehx
            @Override // com.google.android.gms.internal.ads.zzfxv
            public final zzfyx zza(Object obj) {
                return zzehz.this.zzc(parse, zzfdeVar, zzfcsVar, obj);
            }
        }, this.zzc);
    }

    @Override // com.google.android.gms.internal.ads.zzegk
    public final boolean zzb(zzfde zzfdeVar, zzfcs zzfcsVar) {
        Context context = this.zza;
        return (context instanceof Activity) && zzbjw.zzg(context) && !TextUtils.isEmpty(zzd(zzfcsVar));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ zzfyx zzc(Uri uri, zzfde zzfdeVar, zzfcs zzfcsVar, Object obj) throws Exception {
        try {
            CustomTabsIntent build = new CustomTabsIntent.Builder().build();
            build.intent.setData(uri);
            com.google.android.gms.ads.internal.overlay.zzc zzcVar = new com.google.android.gms.ads.internal.overlay.zzc(build.intent, null);
            final zzchf zzchfVar = new zzchf();
            zzdlf zze = this.zzb.zze(new zzczr(zzfdeVar, zzfcsVar, null), new zzdli(new zzdmn() { // from class: com.google.android.gms.internal.ads.zzehy
                @Override // com.google.android.gms.internal.ads.zzdmn
                public final void zza(boolean z, Context context, zzddl zzddlVar) {
                    zzchf zzchfVar2 = zzchf.this;
                    try {
                        com.google.android.gms.ads.internal.zzt.zzj();
                        com.google.android.gms.ads.internal.overlay.zzm.zza(context, (AdOverlayInfoParcel) zzchfVar2.get(), true);
                    } catch (Exception unused) {
                    }
                }
            }, null));
            zzchfVar.zzd(new AdOverlayInfoParcel(zzcVar, null, zze.zza(), null, new zzcgt(0, 0, false, false, false), null, null));
            this.zzd.zza();
            return zzfyo.zzi(zze.zzg());
        } catch (Throwable th) {
            com.google.android.gms.ads.internal.util.zze.zzh("Error in CustomTabsAdRenderer", th);
            throw th;
        }
    }
}
