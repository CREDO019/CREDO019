package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import android.view.View;
import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.AdFormat;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.formats.NativeCustomTemplateAd;
import com.google.android.gms.ads.mediation.Adapter;
import com.google.android.gms.ads.mediation.MediationAdapter;
import com.google.android.gms.ads.mediation.MediationBannerAdConfiguration;
import com.google.android.gms.ads.mediation.MediationBannerAdapter;
import com.google.android.gms.ads.mediation.MediationConfiguration;
import com.google.android.gms.ads.mediation.MediationInterscrollerAd;
import com.google.android.gms.ads.mediation.MediationInterstitialAd;
import com.google.android.gms.ads.mediation.MediationInterstitialAdConfiguration;
import com.google.android.gms.ads.mediation.MediationInterstitialAdapter;
import com.google.android.gms.ads.mediation.MediationNativeAdConfiguration;
import com.google.android.gms.ads.mediation.MediationNativeAdapter;
import com.google.android.gms.ads.mediation.MediationRewardedAd;
import com.google.android.gms.ads.mediation.MediationRewardedAdConfiguration;
import com.google.android.gms.ads.mediation.OnContextChangedListener;
import com.google.android.gms.ads.mediation.OnImmersiveModeUpdatedListener;
import com.google.android.gms.ads.mediation.UnifiedNativeAdMapper;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: com.google.android.gms:play-services-ads-lite@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzbwe extends zzbvh {
    private final Object zza;
    private zzbwg zzb;
    private zzccb zzc;
    private IObjectWrapper zzd;
    private View zze;
    private MediationInterstitialAd zzf;
    private UnifiedNativeAdMapper zzg;
    private MediationRewardedAd zzh;
    private MediationInterscrollerAd zzi;
    private final String zzj = "";

    public zzbwe(Adapter adapter) {
        this.zza = adapter;
    }

    private final Bundle zzR(com.google.android.gms.ads.internal.client.zzl zzlVar) {
        Bundle bundle;
        Bundle bundle2 = zzlVar.zzm;
        return (bundle2 == null || (bundle = bundle2.getBundle(this.zza.getClass().getName())) == null) ? new Bundle() : bundle;
    }

    private final Bundle zzS(String str, com.google.android.gms.ads.internal.client.zzl zzlVar, String str2) throws RemoteException {
        zzcgn.zze("Server parameters: ".concat(String.valueOf(str)));
        try {
            Bundle bundle = new Bundle();
            if (str != null) {
                JSONObject jSONObject = new JSONObject(str);
                Bundle bundle2 = new Bundle();
                Iterator<String> keys = jSONObject.keys();
                while (keys.hasNext()) {
                    String next = keys.next();
                    bundle2.putString(next, jSONObject.getString(next));
                }
                bundle = bundle2;
            }
            if (this.zza instanceof AdMobAdapter) {
                bundle.putString("adJson", str2);
                if (zzlVar != null) {
                    bundle.putInt("tagForChildDirectedTreatment", zzlVar.zzg);
                }
            }
            bundle.remove("max_ad_content_rating");
            return bundle;
        } catch (Throwable th) {
            zzcgn.zzh("", th);
            throw new RemoteException();
        }
    }

    private static final boolean zzT(com.google.android.gms.ads.internal.client.zzl zzlVar) {
        if (zzlVar.zzf) {
            return true;
        }
        com.google.android.gms.ads.internal.client.zzaw.zzb();
        return zzcgg.zzq();
    }

    private static final String zzU(String str, com.google.android.gms.ads.internal.client.zzl zzlVar) {
        String str2 = zzlVar.zzu;
        try {
            return new JSONObject(str).getString("max_ad_content_rating");
        } catch (JSONException unused) {
            return str2;
        }
    }

    @Override // com.google.android.gms.internal.ads.zzbvi
    public final void zzA(com.google.android.gms.ads.internal.client.zzl zzlVar, String str, String str2) throws RemoteException {
        Object obj = this.zza;
        if (!(obj instanceof Adapter)) {
            String canonicalName = Adapter.class.getCanonicalName();
            String canonicalName2 = this.zza.getClass().getCanonicalName();
            zzcgn.zzj(canonicalName + " #009 Class mismatch: " + canonicalName2);
            throw new RemoteException();
        }
        zzz(this.zzd, zzlVar, str, new zzbwh((Adapter) obj, this.zzc));
    }

    @Override // com.google.android.gms.internal.ads.zzbvi
    public final void zzB(IObjectWrapper iObjectWrapper, com.google.android.gms.ads.internal.client.zzl zzlVar, String str, zzbvl zzbvlVar) throws RemoteException {
        if (!(this.zza instanceof Adapter)) {
            String canonicalName = Adapter.class.getCanonicalName();
            String canonicalName2 = this.zza.getClass().getCanonicalName();
            zzcgn.zzj(canonicalName + " #009 Class mismatch: " + canonicalName2);
            throw new RemoteException();
        }
        zzcgn.zze("Requesting rewarded interstitial ad from adapter.");
        try {
            ((Adapter) this.zza).loadRewardedInterstitialAd(new MediationRewardedAdConfiguration((Context) ObjectWrapper.unwrap(iObjectWrapper), "", zzS(str, zzlVar, null), zzR(zzlVar), zzT(zzlVar), zzlVar.zzk, zzlVar.zzg, zzlVar.zzt, zzU(str, zzlVar), ""), new zzbwd(this, zzbvlVar));
        } catch (Exception e) {
            zzcgn.zzh("", e);
            throw new RemoteException();
        }
    }

    @Override // com.google.android.gms.internal.ads.zzbvi
    public final void zzC(IObjectWrapper iObjectWrapper) throws RemoteException {
        Context context = (Context) ObjectWrapper.unwrap(iObjectWrapper);
        Object obj = this.zza;
        if (obj instanceof OnContextChangedListener) {
            ((OnContextChangedListener) obj).onContextChanged(context);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzbvi
    public final void zzD() throws RemoteException {
        Object obj = this.zza;
        if (obj instanceof MediationAdapter) {
            try {
                ((MediationAdapter) obj).onPause();
            } catch (Throwable th) {
                zzcgn.zzh("", th);
                throw new RemoteException();
            }
        }
    }

    @Override // com.google.android.gms.internal.ads.zzbvi
    public final void zzE() throws RemoteException {
        Object obj = this.zza;
        if (obj instanceof MediationAdapter) {
            try {
                ((MediationAdapter) obj).onResume();
            } catch (Throwable th) {
                zzcgn.zzh("", th);
                throw new RemoteException();
            }
        }
    }

    @Override // com.google.android.gms.internal.ads.zzbvi
    public final void zzF(boolean z) throws RemoteException {
        Object obj = this.zza;
        if (obj instanceof OnImmersiveModeUpdatedListener) {
            try {
                ((OnImmersiveModeUpdatedListener) obj).onImmersiveModeUpdated(z);
                return;
            } catch (Throwable th) {
                zzcgn.zzh("", th);
                return;
            }
        }
        String canonicalName = OnImmersiveModeUpdatedListener.class.getCanonicalName();
        String canonicalName2 = this.zza.getClass().getCanonicalName();
        zzcgn.zze(canonicalName + " #009 Class mismatch: " + canonicalName2);
    }

    @Override // com.google.android.gms.internal.ads.zzbvi
    public final void zzG() throws RemoteException {
        if (!(this.zza instanceof MediationInterstitialAdapter)) {
            String canonicalName = MediationInterstitialAdapter.class.getCanonicalName();
            String canonicalName2 = this.zza.getClass().getCanonicalName();
            zzcgn.zzj(canonicalName + " #009 Class mismatch: " + canonicalName2);
            throw new RemoteException();
        }
        zzcgn.zze("Showing interstitial from adapter.");
        try {
            ((MediationInterstitialAdapter) this.zza).showInterstitial();
        } catch (Throwable th) {
            zzcgn.zzh("", th);
            throw new RemoteException();
        }
    }

    @Override // com.google.android.gms.internal.ads.zzbvi
    public final void zzH(IObjectWrapper iObjectWrapper) throws RemoteException {
        Object obj = this.zza;
        if ((obj instanceof Adapter) || (obj instanceof MediationInterstitialAdapter)) {
            if (obj instanceof MediationInterstitialAdapter) {
                zzG();
                return;
            }
            zzcgn.zze("Show interstitial ad from adapter.");
            MediationInterstitialAd mediationInterstitialAd = this.zzf;
            if (mediationInterstitialAd != null) {
                mediationInterstitialAd.showAd((Context) ObjectWrapper.unwrap(iObjectWrapper));
                return;
            } else {
                zzcgn.zzg("Can not show null mediation interstitial ad.");
                throw new RemoteException();
            }
        }
        String canonicalName = MediationInterstitialAdapter.class.getCanonicalName();
        String canonicalName2 = Adapter.class.getCanonicalName();
        String canonicalName3 = this.zza.getClass().getCanonicalName();
        zzcgn.zzj(canonicalName + " or " + canonicalName2 + " #009 Class mismatch: " + canonicalName3);
        throw new RemoteException();
    }

    @Override // com.google.android.gms.internal.ads.zzbvi
    public final void zzI(IObjectWrapper iObjectWrapper) throws RemoteException {
        if (!(this.zza instanceof Adapter)) {
            String canonicalName = Adapter.class.getCanonicalName();
            String canonicalName2 = this.zza.getClass().getCanonicalName();
            zzcgn.zzj(canonicalName + " #009 Class mismatch: " + canonicalName2);
            throw new RemoteException();
        }
        zzcgn.zze("Show rewarded ad from adapter.");
        MediationRewardedAd mediationRewardedAd = this.zzh;
        if (mediationRewardedAd != null) {
            mediationRewardedAd.showAd((Context) ObjectWrapper.unwrap(iObjectWrapper));
        } else {
            zzcgn.zzg("Can not show null mediation rewarded ad.");
            throw new RemoteException();
        }
    }

    @Override // com.google.android.gms.internal.ads.zzbvi
    public final void zzJ() throws RemoteException {
        if (!(this.zza instanceof Adapter)) {
            String canonicalName = Adapter.class.getCanonicalName();
            String canonicalName2 = this.zza.getClass().getCanonicalName();
            zzcgn.zzj(canonicalName + " #009 Class mismatch: " + canonicalName2);
            throw new RemoteException();
        }
        MediationRewardedAd mediationRewardedAd = this.zzh;
        if (mediationRewardedAd != null) {
            mediationRewardedAd.showAd((Context) ObjectWrapper.unwrap(this.zzd));
        } else {
            zzcgn.zzg("Can not show null mediated rewarded ad.");
            throw new RemoteException();
        }
    }

    @Override // com.google.android.gms.internal.ads.zzbvi
    public final boolean zzK() {
        return false;
    }

    @Override // com.google.android.gms.internal.ads.zzbvi
    public final boolean zzL() throws RemoteException {
        if (this.zza instanceof Adapter) {
            return this.zzc != null;
        }
        String canonicalName = Adapter.class.getCanonicalName();
        String canonicalName2 = this.zza.getClass().getCanonicalName();
        zzcgn.zzj(canonicalName + " #009 Class mismatch: " + canonicalName2);
        throw new RemoteException();
    }

    @Override // com.google.android.gms.internal.ads.zzbvi
    public final zzbvq zzM() {
        return null;
    }

    @Override // com.google.android.gms.internal.ads.zzbvi
    public final zzbvr zzN() {
        return null;
    }

    @Override // com.google.android.gms.internal.ads.zzbvi
    public final Bundle zze() {
        Object obj = this.zza;
        if (!(obj instanceof zzcoi)) {
            String canonicalName = zzcoi.class.getCanonicalName();
            String canonicalName2 = this.zza.getClass().getCanonicalName();
            zzcgn.zzj(canonicalName + " #009 Class mismatch: " + canonicalName2);
            return new Bundle();
        }
        return ((zzcoi) obj).zza();
    }

    @Override // com.google.android.gms.internal.ads.zzbvi
    public final Bundle zzf() {
        Object obj = this.zza;
        if (!(obj instanceof zzcoj)) {
            String canonicalName = zzcoj.class.getCanonicalName();
            String canonicalName2 = this.zza.getClass().getCanonicalName();
            zzcgn.zzj(canonicalName + " #009 Class mismatch: " + canonicalName2);
            return new Bundle();
        }
        return ((zzcoj) obj).getInterstitialAdapterInfo();
    }

    @Override // com.google.android.gms.internal.ads.zzbvi
    public final Bundle zzg() {
        return new Bundle();
    }

    @Override // com.google.android.gms.internal.ads.zzbvi
    public final com.google.android.gms.ads.internal.client.zzdk zzh() {
        Object obj = this.zza;
        if (obj instanceof com.google.android.gms.ads.mediation.zzb) {
            try {
                return ((com.google.android.gms.ads.mediation.zzb) obj).getVideoController();
            } catch (Throwable th) {
                zzcgn.zzh("", th);
            }
        }
        return null;
    }

    @Override // com.google.android.gms.internal.ads.zzbvi
    public final zzbmu zzi() {
        zzbwg zzbwgVar = this.zzb;
        if (zzbwgVar != null) {
            NativeCustomTemplateAd zza = zzbwgVar.zza();
            if (zza instanceof zzbmv) {
                return ((zzbmv) zza).zza();
            }
            return null;
        }
        return null;
    }

    @Override // com.google.android.gms.internal.ads.zzbvi
    public final zzbvo zzj() {
        MediationInterscrollerAd mediationInterscrollerAd = this.zzi;
        if (mediationInterscrollerAd != null) {
            return new zzbwf(mediationInterscrollerAd);
        }
        return null;
    }

    @Override // com.google.android.gms.internal.ads.zzbvi
    public final zzbvu zzk() {
        UnifiedNativeAdMapper unifiedNativeAdMapper;
        UnifiedNativeAdMapper zzb;
        Object obj = this.zza;
        if (!(obj instanceof MediationNativeAdapter)) {
            if (!(obj instanceof Adapter) || (unifiedNativeAdMapper = this.zzg) == null) {
                return null;
            }
            return new zzbwj(unifiedNativeAdMapper);
        }
        zzbwg zzbwgVar = this.zzb;
        if (zzbwgVar == null || (zzb = zzbwgVar.zzb()) == null) {
            return null;
        }
        return new zzbwj(zzb);
    }

    @Override // com.google.android.gms.internal.ads.zzbvi
    public final zzbxl zzl() {
        Object obj = this.zza;
        if (obj instanceof Adapter) {
            return zzbxl.zza(((Adapter) obj).getVersionInfo());
        }
        return null;
    }

    @Override // com.google.android.gms.internal.ads.zzbvi
    public final zzbxl zzm() {
        Object obj = this.zza;
        if (obj instanceof Adapter) {
            return zzbxl.zza(((Adapter) obj).getSDKVersionInfo());
        }
        return null;
    }

    @Override // com.google.android.gms.internal.ads.zzbvi
    public final IObjectWrapper zzn() throws RemoteException {
        Object obj = this.zza;
        if (obj instanceof MediationBannerAdapter) {
            try {
                return ObjectWrapper.wrap(((MediationBannerAdapter) obj).getBannerView());
            } catch (Throwable th) {
                zzcgn.zzh("", th);
                throw new RemoteException();
            }
        } else if (obj instanceof Adapter) {
            return ObjectWrapper.wrap(this.zze);
        } else {
            String canonicalName = MediationBannerAdapter.class.getCanonicalName();
            String canonicalName2 = Adapter.class.getCanonicalName();
            String canonicalName3 = this.zza.getClass().getCanonicalName();
            zzcgn.zzj(canonicalName + " or " + canonicalName2 + " #009 Class mismatch: " + canonicalName3);
            throw new RemoteException();
        }
    }

    @Override // com.google.android.gms.internal.ads.zzbvi
    public final void zzo() throws RemoteException {
        Object obj = this.zza;
        if (obj instanceof MediationAdapter) {
            try {
                ((MediationAdapter) obj).onDestroy();
            } catch (Throwable th) {
                zzcgn.zzh("", th);
                throw new RemoteException();
            }
        }
    }

    @Override // com.google.android.gms.internal.ads.zzbvi
    public final void zzp(IObjectWrapper iObjectWrapper, com.google.android.gms.ads.internal.client.zzl zzlVar, String str, zzccb zzccbVar, String str2) throws RemoteException {
        Object obj = this.zza;
        if (!(obj instanceof Adapter)) {
            String canonicalName = Adapter.class.getCanonicalName();
            String canonicalName2 = this.zza.getClass().getCanonicalName();
            zzcgn.zzj(canonicalName + " #009 Class mismatch: " + canonicalName2);
            throw new RemoteException();
        }
        this.zzd = iObjectWrapper;
        this.zzc = zzccbVar;
        zzccbVar.zzl(ObjectWrapper.wrap(obj));
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // com.google.android.gms.internal.ads.zzbvi
    public final void zzq(IObjectWrapper iObjectWrapper, zzbrp zzbrpVar, List list) throws RemoteException {
        char c;
        AdFormat adFormat;
        if (this.zza instanceof Adapter) {
            zzbvz zzbvzVar = new zzbvz(this, zzbrpVar);
            ArrayList arrayList = new ArrayList();
            Iterator it = list.iterator();
            while (it.hasNext()) {
                zzbrv zzbrvVar = (zzbrv) it.next();
                String str = zzbrvVar.zza;
                switch (str.hashCode()) {
                    case -1396342996:
                        if (str.equals("banner")) {
                            c = 0;
                            break;
                        }
                        c = 65535;
                        break;
                    case -1052618729:
                        if (str.equals("native")) {
                            c = 4;
                            break;
                        }
                        c = 65535;
                        break;
                    case -239580146:
                        if (str.equals("rewarded")) {
                            c = 2;
                            break;
                        }
                        c = 65535;
                        break;
                    case 604727084:
                        if (str.equals("interstitial")) {
                            c = 1;
                            break;
                        }
                        c = 65535;
                        break;
                    case 1911491517:
                        if (str.equals("rewarded_interstitial")) {
                            c = 3;
                            break;
                        }
                        c = 65535;
                        break;
                    default:
                        c = 65535;
                        break;
                }
                if (c == 0) {
                    adFormat = AdFormat.BANNER;
                } else if (c == 1) {
                    adFormat = AdFormat.INTERSTITIAL;
                } else if (c == 2) {
                    adFormat = AdFormat.REWARDED;
                } else if (c != 3) {
                    adFormat = c != 4 ? null : AdFormat.NATIVE;
                } else {
                    adFormat = AdFormat.REWARDED_INTERSTITIAL;
                }
                if (adFormat != null) {
                    arrayList.add(new MediationConfiguration(adFormat, zzbrvVar.zzb));
                }
            }
            ((Adapter) this.zza).initialize((Context) ObjectWrapper.unwrap(iObjectWrapper), zzbvzVar, arrayList);
            return;
        }
        throw new RemoteException();
    }

    @Override // com.google.android.gms.internal.ads.zzbvi
    public final void zzr(IObjectWrapper iObjectWrapper, zzccb zzccbVar, List list) throws RemoteException {
        zzcgn.zzj("Could not initialize rewarded video adapter.");
        throw new RemoteException();
    }

    @Override // com.google.android.gms.internal.ads.zzbvi
    public final void zzs(com.google.android.gms.ads.internal.client.zzl zzlVar, String str) throws RemoteException {
        zzA(zzlVar, str, null);
    }

    @Override // com.google.android.gms.internal.ads.zzbvi
    public final void zzt(IObjectWrapper iObjectWrapper, com.google.android.gms.ads.internal.client.zzq zzqVar, com.google.android.gms.ads.internal.client.zzl zzlVar, String str, zzbvl zzbvlVar) throws RemoteException {
        zzu(iObjectWrapper, zzqVar, zzlVar, str, null, zzbvlVar);
    }

    @Override // com.google.android.gms.internal.ads.zzbvi
    public final void zzu(IObjectWrapper iObjectWrapper, com.google.android.gms.ads.internal.client.zzq zzqVar, com.google.android.gms.ads.internal.client.zzl zzlVar, String str, String str2, zzbvl zzbvlVar) throws RemoteException {
        AdSize zzc;
        RemoteException remoteException;
        Object obj = this.zza;
        if ((obj instanceof MediationBannerAdapter) || (obj instanceof Adapter)) {
            zzcgn.zze("Requesting banner ad from adapter.");
            if (zzqVar.zzn) {
                zzc = com.google.android.gms.ads.zzb.zzd(zzqVar.zze, zzqVar.zzb);
            } else {
                zzc = com.google.android.gms.ads.zzb.zzc(zzqVar.zze, zzqVar.zzb, zzqVar.zza);
            }
            AdSize adSize = zzc;
            Object obj2 = this.zza;
            if (obj2 instanceof MediationBannerAdapter) {
                try {
                    MediationBannerAdapter mediationBannerAdapter = (MediationBannerAdapter) obj2;
                    List list = zzlVar.zze;
                    HashSet hashSet = list != null ? new HashSet(list) : null;
                    long j = zzlVar.zzb;
                    zzbvx zzbvxVar = new zzbvx(j == -1 ? null : new Date(j), zzlVar.zzd, hashSet, zzlVar.zzk, zzT(zzlVar), zzlVar.zzg, zzlVar.zzr, zzlVar.zzt, zzU(str, zzlVar));
                    Bundle bundle = zzlVar.zzm;
                    mediationBannerAdapter.requestBannerAd((Context) ObjectWrapper.unwrap(iObjectWrapper), new zzbwg(zzbvlVar), zzS(str, zzlVar, str2), adSize, zzbvxVar, bundle != null ? bundle.getBundle(mediationBannerAdapter.getClass().getName()) : null);
                    return;
                } finally {
                }
            } else if (obj2 instanceof Adapter) {
                try {
                    ((Adapter) obj2).loadBannerAd(new MediationBannerAdConfiguration((Context) ObjectWrapper.unwrap(iObjectWrapper), "", zzS(str, zzlVar, str2), zzR(zzlVar), zzT(zzlVar), zzlVar.zzk, zzlVar.zzg, zzlVar.zzt, zzU(str, zzlVar), adSize, this.zzj), new zzbwa(this, zzbvlVar));
                    return;
                } finally {
                }
            } else {
                return;
            }
        }
        String canonicalName = MediationBannerAdapter.class.getCanonicalName();
        String canonicalName2 = Adapter.class.getCanonicalName();
        String canonicalName3 = this.zza.getClass().getCanonicalName();
        zzcgn.zzj(canonicalName + " or " + canonicalName2 + " #009 Class mismatch: " + canonicalName3);
        throw new RemoteException();
    }

    @Override // com.google.android.gms.internal.ads.zzbvi
    public final void zzv(IObjectWrapper iObjectWrapper, com.google.android.gms.ads.internal.client.zzq zzqVar, com.google.android.gms.ads.internal.client.zzl zzlVar, String str, String str2, zzbvl zzbvlVar) throws RemoteException {
        if (!(this.zza instanceof Adapter)) {
            String canonicalName = Adapter.class.getCanonicalName();
            String canonicalName2 = this.zza.getClass().getCanonicalName();
            zzcgn.zzj(canonicalName + " #009 Class mismatch: " + canonicalName2);
            throw new RemoteException();
        }
        zzcgn.zze("Requesting interscroller ad from adapter.");
        try {
            Adapter adapter = (Adapter) this.zza;
            adapter.loadInterscrollerAd(new MediationBannerAdConfiguration((Context) ObjectWrapper.unwrap(iObjectWrapper), "", zzS(str, zzlVar, str2), zzR(zzlVar), zzT(zzlVar), zzlVar.zzk, zzlVar.zzg, zzlVar.zzt, zzU(str, zzlVar), com.google.android.gms.ads.zzb.zze(zzqVar.zze, zzqVar.zzb), ""), new zzbvy(this, zzbvlVar, adapter));
        } catch (Exception e) {
            zzcgn.zzh("", e);
            throw new RemoteException();
        }
    }

    @Override // com.google.android.gms.internal.ads.zzbvi
    public final void zzw(IObjectWrapper iObjectWrapper, com.google.android.gms.ads.internal.client.zzl zzlVar, String str, zzbvl zzbvlVar) throws RemoteException {
        zzx(iObjectWrapper, zzlVar, str, null, zzbvlVar);
    }

    @Override // com.google.android.gms.internal.ads.zzbvi
    public final void zzx(IObjectWrapper iObjectWrapper, com.google.android.gms.ads.internal.client.zzl zzlVar, String str, String str2, zzbvl zzbvlVar) throws RemoteException {
        RemoteException remoteException;
        Object obj = this.zza;
        if ((obj instanceof MediationInterstitialAdapter) || (obj instanceof Adapter)) {
            zzcgn.zze("Requesting interstitial ad from adapter.");
            Object obj2 = this.zza;
            if (obj2 instanceof MediationInterstitialAdapter) {
                try {
                    MediationInterstitialAdapter mediationInterstitialAdapter = (MediationInterstitialAdapter) obj2;
                    List list = zzlVar.zze;
                    HashSet hashSet = list != null ? new HashSet(list) : null;
                    long j = zzlVar.zzb;
                    zzbvx zzbvxVar = new zzbvx(j == -1 ? null : new Date(j), zzlVar.zzd, hashSet, zzlVar.zzk, zzT(zzlVar), zzlVar.zzg, zzlVar.zzr, zzlVar.zzt, zzU(str, zzlVar));
                    Bundle bundle = zzlVar.zzm;
                    mediationInterstitialAdapter.requestInterstitialAd((Context) ObjectWrapper.unwrap(iObjectWrapper), new zzbwg(zzbvlVar), zzS(str, zzlVar, str2), zzbvxVar, bundle != null ? bundle.getBundle(mediationInterstitialAdapter.getClass().getName()) : null);
                    return;
                } finally {
                }
            } else if (obj2 instanceof Adapter) {
                try {
                    ((Adapter) obj2).loadInterstitialAd(new MediationInterstitialAdConfiguration((Context) ObjectWrapper.unwrap(iObjectWrapper), "", zzS(str, zzlVar, str2), zzR(zzlVar), zzT(zzlVar), zzlVar.zzk, zzlVar.zzg, zzlVar.zzt, zzU(str, zzlVar), this.zzj), new zzbwb(this, zzbvlVar));
                    return;
                } finally {
                }
            } else {
                return;
            }
        }
        String canonicalName = MediationInterstitialAdapter.class.getCanonicalName();
        String canonicalName2 = Adapter.class.getCanonicalName();
        String canonicalName3 = this.zza.getClass().getCanonicalName();
        zzcgn.zzj(canonicalName + " or " + canonicalName2 + " #009 Class mismatch: " + canonicalName3);
        throw new RemoteException();
    }

    @Override // com.google.android.gms.internal.ads.zzbvi
    public final void zzy(IObjectWrapper iObjectWrapper, com.google.android.gms.ads.internal.client.zzl zzlVar, String str, String str2, zzbvl zzbvlVar, zzblo zzbloVar, List list) throws RemoteException {
        RemoteException remoteException;
        Object obj = this.zza;
        if ((obj instanceof MediationNativeAdapter) || (obj instanceof Adapter)) {
            zzcgn.zze("Requesting native ad from adapter.");
            Object obj2 = this.zza;
            if (obj2 instanceof MediationNativeAdapter) {
                try {
                    MediationNativeAdapter mediationNativeAdapter = (MediationNativeAdapter) obj2;
                    List list2 = zzlVar.zze;
                    HashSet hashSet = list2 != null ? new HashSet(list2) : null;
                    long j = zzlVar.zzb;
                    zzbwi zzbwiVar = new zzbwi(j == -1 ? null : new Date(j), zzlVar.zzd, hashSet, zzlVar.zzk, zzT(zzlVar), zzlVar.zzg, zzbloVar, list, zzlVar.zzr, zzlVar.zzt, zzU(str, zzlVar));
                    Bundle bundle = zzlVar.zzm;
                    Bundle bundle2 = bundle != null ? bundle.getBundle(mediationNativeAdapter.getClass().getName()) : null;
                    this.zzb = new zzbwg(zzbvlVar);
                    mediationNativeAdapter.requestNativeAd((Context) ObjectWrapper.unwrap(iObjectWrapper), this.zzb, zzS(str, zzlVar, str2), zzbwiVar, bundle2);
                    return;
                } finally {
                }
            } else if (obj2 instanceof Adapter) {
                try {
                    ((Adapter) obj2).loadNativeAd(new MediationNativeAdConfiguration((Context) ObjectWrapper.unwrap(iObjectWrapper), "", zzS(str, zzlVar, str2), zzR(zzlVar), zzT(zzlVar), zzlVar.zzk, zzlVar.zzg, zzlVar.zzt, zzU(str, zzlVar), this.zzj, zzbloVar), new zzbwc(this, zzbvlVar));
                    return;
                } finally {
                }
            } else {
                return;
            }
        }
        String canonicalName = MediationNativeAdapter.class.getCanonicalName();
        String canonicalName2 = Adapter.class.getCanonicalName();
        String canonicalName3 = this.zza.getClass().getCanonicalName();
        zzcgn.zzj(canonicalName + " or " + canonicalName2 + " #009 Class mismatch: " + canonicalName3);
        throw new RemoteException();
    }

    @Override // com.google.android.gms.internal.ads.zzbvi
    public final void zzz(IObjectWrapper iObjectWrapper, com.google.android.gms.ads.internal.client.zzl zzlVar, String str, zzbvl zzbvlVar) throws RemoteException {
        if (!(this.zza instanceof Adapter)) {
            String canonicalName = Adapter.class.getCanonicalName();
            String canonicalName2 = this.zza.getClass().getCanonicalName();
            zzcgn.zzj(canonicalName + " #009 Class mismatch: " + canonicalName2);
            throw new RemoteException();
        }
        zzcgn.zze("Requesting rewarded ad from adapter.");
        try {
            ((Adapter) this.zza).loadRewardedAd(new MediationRewardedAdConfiguration((Context) ObjectWrapper.unwrap(iObjectWrapper), "", zzS(str, zzlVar, null), zzR(zzlVar), zzT(zzlVar), zzlVar.zzk, zzlVar.zzg, zzlVar.zzt, zzU(str, zzlVar), ""), new zzbwd(this, zzbvlVar));
        } catch (Exception e) {
            zzcgn.zzh("", e);
            throw new RemoteException();
        }
    }

    public zzbwe(MediationAdapter mediationAdapter) {
        this.zza = mediationAdapter;
    }
}
