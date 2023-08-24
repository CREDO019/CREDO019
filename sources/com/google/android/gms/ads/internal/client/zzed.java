package com.google.android.gms.ads.internal.client;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.ads.AdInspectorError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.OnAdInspectorClosedListener;
import com.google.android.gms.ads.RequestConfiguration;
import com.google.android.gms.ads.initialization.AdapterStatus;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.internal.ads.zzbiy;
import com.google.android.gms.internal.ads.zzbkm;
import com.google.android.gms.internal.ads.zzbrl;
import com.google.android.gms.internal.ads.zzbrt;
import com.google.android.gms.internal.ads.zzbru;
import com.google.android.gms.internal.ads.zzbuy;
import com.google.android.gms.internal.ads.zzbvc;
import com.google.android.gms.internal.ads.zzcgc;
import com.google.android.gms.internal.ads.zzcgn;
import com.google.android.gms.internal.ads.zzfsu;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.annotation.Nullable;

/* compiled from: com.google.android.gms:play-services-ads-lite@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzed {
    private static zzed zza;
    private zzcm zzg;
    private final Object zzb = new Object();
    private boolean zzd = false;
    private boolean zze = false;
    private final Object zzf = new Object();
    @Nullable
    private OnAdInspectorClosedListener zzh = null;
    private RequestConfiguration zzi = new RequestConfiguration.Builder().build();
    private final ArrayList zzc = new ArrayList();

    private zzed() {
    }

    public static zzed zzf() {
        zzed zzedVar;
        synchronized (zzed.class) {
            if (zza == null) {
                zza = new zzed();
            }
            zzedVar = zza;
        }
        return zzedVar;
    }

    public static InitializationStatus zzw(List list) {
        HashMap hashMap = new HashMap();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            zzbrl zzbrlVar = (zzbrl) it.next();
            hashMap.put(zzbrlVar.zza, new zzbrt(zzbrlVar.zzb ? AdapterStatus.State.READY : AdapterStatus.State.NOT_READY, zzbrlVar.zzd, zzbrlVar.zzc));
        }
        return new zzbru(hashMap);
    }

    private final void zzx(Context context, @Nullable String str, @Nullable OnInitializationCompleteListener onInitializationCompleteListener) {
        try {
            zzbuy.zza().zzb(context, null);
            this.zzg.zzj();
            this.zzg.zzk(null, ObjectWrapper.wrap(null));
        } catch (RemoteException e) {
            zzcgn.zzk("MobileAdsSettingManager initialization failed", e);
        }
    }

    private final void zzy(Context context) {
        if (this.zzg == null) {
            this.zzg = (zzcm) new zzao(zzaw.zza(), context).zzd(context, false);
        }
    }

    private final void zzz(RequestConfiguration requestConfiguration) {
        try {
            this.zzg.zzs(new zzez(requestConfiguration));
        } catch (RemoteException e) {
            zzcgn.zzh("Unable to set request configuration parcel.", e);
        }
    }

    public final RequestConfiguration zzc() {
        return this.zzi;
    }

    public final InitializationStatus zze() {
        InitializationStatus zzw;
        synchronized (this.zzf) {
            Preconditions.checkState(this.zzg != null, "MobileAds.initialize() must be called prior to getting initialization status.");
            try {
                zzw = zzw(this.zzg.zzg());
            } catch (RemoteException unused) {
                zzcgn.zzg("Unable to get Initialization status.");
                return new InitializationStatus() { // from class: com.google.android.gms.ads.internal.client.zzdv
                    @Override // com.google.android.gms.ads.initialization.InitializationStatus
                    public final Map getAdapterStatusMap() {
                        zzed zzedVar = zzed.this;
                        HashMap hashMap = new HashMap();
                        hashMap.put("com.google.android.gms.ads.MobileAds", new zzdy(zzedVar));
                        return hashMap;
                    }
                };
            }
        }
        return zzw;
    }

    @Deprecated
    public final String zzh() {
        String zzc;
        synchronized (this.zzf) {
            Preconditions.checkState(this.zzg != null, "MobileAds.initialize() must be called prior to getting version string.");
            try {
                zzc = zzfsu.zzc(this.zzg.zzf());
            } catch (RemoteException e) {
                zzcgn.zzh("Unable to get version string.", e);
                return "";
            }
        }
        return zzc;
    }

    public final void zzl(Context context) {
        synchronized (this.zzf) {
            zzy(context);
            try {
                this.zzg.zzi();
            } catch (RemoteException unused) {
                zzcgn.zzg("Unable to disable mediation adapter initialization.");
            }
        }
    }

    public final void zzm(Context context, @Nullable String str, @Nullable OnInitializationCompleteListener onInitializationCompleteListener) {
        synchronized (this.zzb) {
            if (this.zzd) {
                if (onInitializationCompleteListener != null) {
                    this.zzc.add(onInitializationCompleteListener);
                }
            } else if (this.zze) {
                if (onInitializationCompleteListener != null) {
                    onInitializationCompleteListener.onInitializationComplete(zze());
                }
            } else {
                this.zzd = true;
                if (onInitializationCompleteListener != null) {
                    this.zzc.add(onInitializationCompleteListener);
                }
                if (context != null) {
                    synchronized (this.zzf) {
                        try {
                            zzy(context);
                            this.zzg.zzr(new zzec(this, null));
                            this.zzg.zzn(new zzbvc());
                            if (this.zzi.getTagForChildDirectedTreatment() != -1 || this.zzi.getTagForUnderAgeOfConsent() != -1) {
                                zzz(this.zzi);
                            }
                        } catch (RemoteException e) {
                            zzcgn.zzk("MobileAdsSettingManager initialization failed", e);
                        }
                        zzbiy.zzc(context);
                        if (((Boolean) zzbkm.zza.zze()).booleanValue()) {
                            if (((Boolean) zzay.zzc().zzb(zzbiy.zziF)).booleanValue()) {
                                zzcgn.zze("Initializing on bg thread");
                                zzcgc.zza.execute(new Runnable(context, null, onInitializationCompleteListener) { // from class: com.google.android.gms.ads.internal.client.zzdw
                                    public final /* synthetic */ Context zzb;
                                    public final /* synthetic */ OnInitializationCompleteListener zzc;

                                    {
                                        this.zzc = onInitializationCompleteListener;
                                    }

                                    @Override // java.lang.Runnable
                                    public final void run() {
                                        zzed.this.zzn(this.zzb, null, this.zzc);
                                    }
                                });
                            }
                        }
                        if (((Boolean) zzbkm.zzb.zze()).booleanValue()) {
                            if (((Boolean) zzay.zzc().zzb(zzbiy.zziF)).booleanValue()) {
                                zzcgc.zzb.execute(new Runnable(context, null, onInitializationCompleteListener) { // from class: com.google.android.gms.ads.internal.client.zzdx
                                    public final /* synthetic */ Context zzb;
                                    public final /* synthetic */ OnInitializationCompleteListener zzc;

                                    {
                                        this.zzc = onInitializationCompleteListener;
                                    }

                                    @Override // java.lang.Runnable
                                    public final void run() {
                                        zzed.this.zzo(this.zzb, null, this.zzc);
                                    }
                                });
                            }
                        }
                        zzcgn.zze("Initializing on calling thread");
                        zzx(context, null, onInitializationCompleteListener);
                    }
                    return;
                }
                throw new IllegalArgumentException("Context cannot be null.");
            }
        }
    }

    public final /* synthetic */ void zzn(Context context, String str, OnInitializationCompleteListener onInitializationCompleteListener) {
        synchronized (this.zzf) {
            zzx(context, null, onInitializationCompleteListener);
        }
    }

    public final /* synthetic */ void zzo(Context context, String str, OnInitializationCompleteListener onInitializationCompleteListener) {
        synchronized (this.zzf) {
            zzx(context, null, onInitializationCompleteListener);
        }
    }

    public final void zzp(Context context, OnAdInspectorClosedListener onAdInspectorClosedListener) {
        synchronized (this.zzf) {
            zzy(context);
            this.zzh = onAdInspectorClosedListener;
            try {
                this.zzg.zzl(new zzea(null));
            } catch (RemoteException unused) {
                zzcgn.zzg("Unable to open the ad inspector.");
                if (onAdInspectorClosedListener != null) {
                    onAdInspectorClosedListener.onAdInspectorClosed(new AdInspectorError(0, "Ad inspector had an internal error.", MobileAds.ERROR_DOMAIN));
                }
            }
        }
    }

    public final void zzq(Context context, String str) {
        synchronized (this.zzf) {
            Preconditions.checkState(this.zzg != null, "MobileAds.initialize() must be called prior to opening debug menu.");
            try {
                this.zzg.zzm(ObjectWrapper.wrap(context), str);
            } catch (RemoteException e) {
                zzcgn.zzh("Unable to open debug menu.", e);
            }
        }
    }

    public final void zzr(Class cls) {
        synchronized (this.zzf) {
            try {
                this.zzg.zzh(cls.getCanonicalName());
            } catch (RemoteException e) {
                zzcgn.zzh("Unable to register RtbAdapter", e);
            }
        }
    }

    public final void zzs(boolean z) {
        synchronized (this.zzf) {
            Preconditions.checkState(this.zzg != null, "MobileAds.initialize() must be called prior to setting app muted state.");
            try {
                this.zzg.zzo(z);
            } catch (RemoteException e) {
                zzcgn.zzh("Unable to set app mute state.", e);
            }
        }
    }

    public final void zzt(float f) {
        boolean z = true;
        Preconditions.checkArgument(f >= 0.0f && f <= 1.0f, "The app volume must be a value between 0 and 1 inclusive.");
        synchronized (this.zzf) {
            if (this.zzg == null) {
                z = false;
            }
            Preconditions.checkState(z, "MobileAds.initialize() must be called prior to setting the app volume.");
            try {
                this.zzg.zzp(f);
            } catch (RemoteException e) {
                zzcgn.zzh("Unable to set app volume.", e);
            }
        }
    }

    public final void zzu(RequestConfiguration requestConfiguration) {
        Preconditions.checkArgument(requestConfiguration != null, "Null passed to setRequestConfiguration.");
        synchronized (this.zzf) {
            RequestConfiguration requestConfiguration2 = this.zzi;
            this.zzi = requestConfiguration;
            if (this.zzg == null) {
                return;
            }
            if (requestConfiguration2.getTagForChildDirectedTreatment() != requestConfiguration.getTagForChildDirectedTreatment() || requestConfiguration2.getTagForUnderAgeOfConsent() != requestConfiguration.getTagForUnderAgeOfConsent()) {
                zzz(requestConfiguration);
            }
        }
    }

    public final float zza() {
        synchronized (this.zzf) {
            zzcm zzcmVar = this.zzg;
            float f = 1.0f;
            if (zzcmVar == null) {
                return 1.0f;
            }
            try {
                f = zzcmVar.zze();
            } catch (RemoteException e) {
                zzcgn.zzh("Unable to get app volume.", e);
            }
            return f;
        }
    }

    public final boolean zzv() {
        synchronized (this.zzf) {
            zzcm zzcmVar = this.zzg;
            boolean z = false;
            if (zzcmVar == null) {
                return false;
            }
            try {
                z = zzcmVar.zzt();
            } catch (RemoteException e) {
                zzcgn.zzh("Unable to get app mute state.", e);
            }
            return z;
        }
    }
}
