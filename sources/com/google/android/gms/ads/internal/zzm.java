package com.google.android.gms.ads.internal;

import android.os.RemoteException;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.google.android.gms.ads.internal.client.zzbf;
import com.google.android.gms.internal.ads.zzfem;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzm extends WebViewClient {
    final /* synthetic */ zzs zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzm(zzs zzsVar) {
        this.zza = zzsVar;
    }

    @Override // android.webkit.WebViewClient
    public final void onReceivedError(WebView webView, WebResourceRequest webResourceRequest, WebResourceError webResourceError) {
        zzbf zzbfVar;
        zzbf zzbfVar2;
        zzbf zzbfVar3;
        zzbf zzbfVar4;
        zzs zzsVar = this.zza;
        zzbfVar = zzsVar.zzg;
        if (zzbfVar != null) {
            try {
                zzbfVar2 = zzsVar.zzg;
                zzbfVar2.zzf(zzfem.zzd(1, null, null));
            } catch (RemoteException e) {
                com.google.android.gms.ads.internal.util.zze.zzl("#007 Could not call remote method.", e);
            }
        }
        zzs zzsVar2 = this.zza;
        zzbfVar3 = zzsVar2.zzg;
        if (zzbfVar3 != null) {
            try {
                zzbfVar4 = zzsVar2.zzg;
                zzbfVar4.zze(0);
            } catch (RemoteException e2) {
                com.google.android.gms.ads.internal.util.zze.zzl("#007 Could not call remote method.", e2);
            }
        }
    }

    @Override // android.webkit.WebViewClient
    public final boolean shouldOverrideUrlLoading(WebView webView, String str) {
        zzbf zzbfVar;
        zzbf zzbfVar2;
        zzbf zzbfVar3;
        zzbf zzbfVar4;
        zzbf zzbfVar5;
        zzbf zzbfVar6;
        zzbf zzbfVar7;
        zzbf zzbfVar8;
        zzbf zzbfVar9;
        zzbf zzbfVar10;
        zzbf zzbfVar11;
        zzbf zzbfVar12;
        zzbf zzbfVar13;
        if (str.startsWith(this.zza.zzq())) {
            return false;
        }
        if (str.startsWith("gmsg://noAdLoaded")) {
            zzs zzsVar = this.zza;
            zzbfVar10 = zzsVar.zzg;
            if (zzbfVar10 != null) {
                try {
                    zzbfVar11 = zzsVar.zzg;
                    zzbfVar11.zzf(zzfem.zzd(3, null, null));
                } catch (RemoteException e) {
                    com.google.android.gms.ads.internal.util.zze.zzl("#007 Could not call remote method.", e);
                }
            }
            zzs zzsVar2 = this.zza;
            zzbfVar12 = zzsVar2.zzg;
            if (zzbfVar12 != null) {
                try {
                    zzbfVar13 = zzsVar2.zzg;
                    zzbfVar13.zze(3);
                } catch (RemoteException e2) {
                    com.google.android.gms.ads.internal.util.zze.zzl("#007 Could not call remote method.", e2);
                }
            }
            this.zza.zzV(0);
            return true;
        } else if (str.startsWith("gmsg://scriptLoadFailed")) {
            zzs zzsVar3 = this.zza;
            zzbfVar6 = zzsVar3.zzg;
            if (zzbfVar6 != null) {
                try {
                    zzbfVar7 = zzsVar3.zzg;
                    zzbfVar7.zzf(zzfem.zzd(1, null, null));
                } catch (RemoteException e3) {
                    com.google.android.gms.ads.internal.util.zze.zzl("#007 Could not call remote method.", e3);
                }
            }
            zzs zzsVar4 = this.zza;
            zzbfVar8 = zzsVar4.zzg;
            if (zzbfVar8 != null) {
                try {
                    zzbfVar9 = zzsVar4.zzg;
                    zzbfVar9.zze(0);
                } catch (RemoteException e4) {
                    com.google.android.gms.ads.internal.util.zze.zzl("#007 Could not call remote method.", e4);
                }
            }
            this.zza.zzV(0);
            return true;
        } else if (str.startsWith("gmsg://adResized")) {
            zzs zzsVar5 = this.zza;
            zzbfVar4 = zzsVar5.zzg;
            if (zzbfVar4 != null) {
                try {
                    zzbfVar5 = zzsVar5.zzg;
                    zzbfVar5.zzi();
                } catch (RemoteException e5) {
                    com.google.android.gms.ads.internal.util.zze.zzl("#007 Could not call remote method.", e5);
                }
            }
            this.zza.zzV(this.zza.zzb(str));
            return true;
        } else if (str.startsWith("gmsg://")) {
            return true;
        } else {
            zzs zzsVar6 = this.zza;
            zzbfVar = zzsVar6.zzg;
            if (zzbfVar != null) {
                try {
                    zzbfVar2 = zzsVar6.zzg;
                    zzbfVar2.zzc();
                    zzbfVar3 = this.zza.zzg;
                    zzbfVar3.zzh();
                } catch (RemoteException e6) {
                    com.google.android.gms.ads.internal.util.zze.zzl("#007 Could not call remote method.", e6);
                }
            }
            zzs.zzw(this.zza, zzs.zzo(this.zza, str));
            return true;
        }
    }
}
