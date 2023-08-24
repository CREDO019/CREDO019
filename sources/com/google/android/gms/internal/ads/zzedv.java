package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Binder;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import com.google.android.gms.common.util.IOUtils;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import org.json.JSONObject;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzedv extends zzcar {
    private final Context zza;
    private final Executor zzb;
    private final zzfyy zzc;
    private final zzcbl zzd;
    private final zzctr zze;
    private final ArrayDeque zzf;
    private final zzfje zzg;
    private final zzcbm zzh;
    private final zzeea zzi;

    public zzedv(Context context, Executor executor, zzfyy zzfyyVar, zzcbm zzcbmVar, zzctr zzctrVar, zzcbl zzcblVar, ArrayDeque arrayDeque, zzeea zzeeaVar, zzfje zzfjeVar, byte[] bArr) {
        zzbiy.zzc(context);
        this.zza = context;
        this.zzb = executor;
        this.zzc = zzfyyVar;
        this.zzh = zzcbmVar;
        this.zzd = zzcblVar;
        this.zze = zzctrVar;
        this.zzf = arrayDeque;
        this.zzi = zzeeaVar;
        this.zzg = zzfjeVar;
    }

    private final synchronized zzeds zzl(String str) {
        Iterator it = this.zzf.iterator();
        while (it.hasNext()) {
            zzeds zzedsVar = (zzeds) it.next();
            if (zzedsVar.zzd.equals(str)) {
                it.remove();
                return zzedsVar;
            }
        }
        return null;
    }

    private final synchronized zzeds zzm(String str) {
        Iterator it = this.zzf.iterator();
        while (it.hasNext()) {
            zzeds zzedsVar = (zzeds) it.next();
            if (zzedsVar.zzc.equals(str)) {
                it.remove();
                return zzedsVar;
            }
        }
        return null;
    }

    private static zzfyx zzn(zzfyx zzfyxVar, zzfhp zzfhpVar, zzbuf zzbufVar, zzfjc zzfjcVar, zzfir zzfirVar) {
        zzbtv zza = zzbufVar.zza("AFMA_getAdDictionary", zzbuc.zza, new zzbtx() { // from class: com.google.android.gms.internal.ads.zzedm
            @Override // com.google.android.gms.internal.ads.zzbtx
            public final Object zza(JSONObject jSONObject) {
                return new zzcbd(jSONObject);
            }
        });
        zzfjb.zzd(zzfyxVar, zzfirVar);
        zzfgu zza2 = zzfhpVar.zzb(zzfhj.BUILD_URL, zzfyxVar).zzf(zza).zza();
        zzfjb.zzc(zza2, zzfjcVar, zzfirVar);
        return zza2;
    }

    private static zzfyx zzo(zzcba zzcbaVar, zzfhp zzfhpVar, final zzevf zzevfVar) {
        zzfxv zzfxvVar = new zzfxv() { // from class: com.google.android.gms.internal.ads.zzedg
            @Override // com.google.android.gms.internal.ads.zzfxv
            public final zzfyx zza(Object obj) {
                return zzevf.this.zzb().zza(com.google.android.gms.ads.internal.client.zzaw.zzb().zzh((Bundle) obj));
            }
        };
        return zzfhpVar.zzb(zzfhj.GMS_SIGNALS, zzfyo.zzi(zzcbaVar.zza)).zzf(zzfxvVar).zze(new zzfgs() { // from class: com.google.android.gms.internal.ads.zzedh
            @Override // com.google.android.gms.internal.ads.zzfgs
            public final Object zza(Object obj) {
                JSONObject jSONObject = (JSONObject) obj;
                com.google.android.gms.ads.internal.util.zze.zza("Ad request signals:");
                com.google.android.gms.ads.internal.util.zze.zza(jSONObject.toString(2));
                return jSONObject;
            }
        }).zza();
    }

    private final synchronized void zzp(zzeds zzedsVar) {
        zzq();
        this.zzf.addLast(zzedsVar);
    }

    private final synchronized void zzq() {
        int intValue = ((Long) zzbku.zzc.zze()).intValue();
        while (this.zzf.size() >= intValue) {
            this.zzf.removeFirst();
        }
    }

    private final void zzr(zzfyx zzfyxVar, zzcaw zzcawVar) {
        zzfyo.zzr(zzfyo.zzn(zzfyxVar, new zzfxv() { // from class: com.google.android.gms.internal.ads.zzedp
            @Override // com.google.android.gms.internal.ads.zzfxv
            public final zzfyx zza(Object obj) {
                final InputStream inputStream = (InputStream) obj;
                ParcelFileDescriptor[] createPipe = ParcelFileDescriptor.createPipe();
                ParcelFileDescriptor parcelFileDescriptor = createPipe[0];
                final ParcelFileDescriptor parcelFileDescriptor2 = createPipe[1];
                zzcha.zza.execute(new Runnable() { // from class: com.google.android.gms.internal.ads.zzfel
                    @Override // java.lang.Runnable
                    public final void run() {
                        InputStream inputStream2 = inputStream;
                        try {
                            ParcelFileDescriptor.AutoCloseOutputStream autoCloseOutputStream = new ParcelFileDescriptor.AutoCloseOutputStream(parcelFileDescriptor2);
                            try {
                                IOUtils.copyStream(inputStream2, autoCloseOutputStream);
                                autoCloseOutputStream.close();
                                if (inputStream2 != null) {
                                    inputStream2.close();
                                }
                            } catch (Throwable th) {
                                try {
                                    autoCloseOutputStream.close();
                                } catch (Throwable th2) {
                                    Throwable.class.getDeclaredMethod("addSuppressed", Throwable.class).invoke(th, th2);
                                }
                                throw th;
                            }
                        } catch (IOException unused) {
                        }
                    }
                });
                return zzfyo.zzi(parcelFileDescriptor);
            }
        }, zzcha.zza), new zzedr(this, zzcawVar), zzcha.zzf);
    }

    public final zzfyx zzb(final zzcba zzcbaVar, int r10) {
        if (!((Boolean) zzbku.zza.zze()).booleanValue()) {
            return zzfyo.zzh(new Exception("Split request is disabled."));
        }
        zzfff zzfffVar = zzcbaVar.zzi;
        if (zzfffVar == null) {
            return zzfyo.zzh(new Exception("Pool configuration missing from request."));
        }
        if (zzfffVar.zzc == 0 || zzfffVar.zzd == 0) {
            return zzfyo.zzh(new Exception("Caching is disabled."));
        }
        zzbuf zzb = com.google.android.gms.ads.internal.zzt.zzf().zzb(this.zza, zzcgt.zza(), this.zzg);
        zzevf zzp = this.zze.zzp(zzcbaVar, r10);
        zzfhp zzc = zzp.zzc();
        final zzfyx zzo = zzo(zzcbaVar, zzc, zzp);
        zzfjc zzd = zzp.zzd();
        final zzfir zza = zzfiq.zza(this.zza, 9);
        final zzfyx zzn = zzn(zzo, zzc, zzb, zzd, zza);
        return zzc.zza(zzfhj.GET_URL_AND_CACHE_KEY, zzo, zzn).zza(new Callable() { // from class: com.google.android.gms.internal.ads.zzedl
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return zzedv.this.zzj(zzn, zzo, zzcbaVar, zza);
            }
        }).zza();
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x006d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final com.google.android.gms.internal.ads.zzfyx zzc(com.google.android.gms.internal.ads.zzcba r17, int r18) {
        /*
            Method dump skipped, instructions count: 371
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzedv.zzc(com.google.android.gms.internal.ads.zzcba, int):com.google.android.gms.internal.ads.zzfyx");
    }

    public final zzfyx zzd(zzcba zzcbaVar, int r8) {
        zzbuf zzb = com.google.android.gms.ads.internal.zzt.zzf().zzb(this.zza, zzcgt.zza(), this.zzg);
        if (!((Boolean) zzbkz.zza.zze()).booleanValue()) {
            return zzfyo.zzh(new Exception("Signal collection disabled."));
        }
        zzevf zzp = this.zze.zzp(zzcbaVar, r8);
        final zzeuq zza = zzp.zza();
        zzbtv zza2 = zzb.zza("google.afma.request.getSignals", zzbuc.zza, zzbuc.zzb);
        zzfir zza3 = zzfiq.zza(this.zza, 22);
        zzfgu zza4 = zzp.zzc().zzb(zzfhj.GET_SIGNALS, zzfyo.zzi(zzcbaVar.zza)).zze(new zzfix(zza3)).zzf(new zzfxv() { // from class: com.google.android.gms.internal.ads.zzedn
            @Override // com.google.android.gms.internal.ads.zzfxv
            public final zzfyx zza(Object obj) {
                return zzeuq.this.zza(com.google.android.gms.ads.internal.client.zzaw.zzb().zzh((Bundle) obj));
            }
        }).zzb(zzfhj.JS_SIGNALS).zzf(zza2).zza();
        zzfjc zzd = zzp.zzd();
        zzd.zzd(zzcbaVar.zza.getStringArrayList("ad_types"));
        zzfjb.zzb(zza4, zzd, zza3);
        return zza4;
    }

    @Override // com.google.android.gms.internal.ads.zzcas
    public final void zze(zzcba zzcbaVar, zzcaw zzcawVar) {
        zzr(zzb(zzcbaVar, Binder.getCallingUid()), zzcawVar);
    }

    @Override // com.google.android.gms.internal.ads.zzcas
    public final void zzf(zzcba zzcbaVar, zzcaw zzcawVar) {
        zzr(zzd(zzcbaVar, Binder.getCallingUid()), zzcawVar);
    }

    @Override // com.google.android.gms.internal.ads.zzcas
    public final void zzg(zzcba zzcbaVar, zzcaw zzcawVar) {
        zzfyx zzc = zzc(zzcbaVar, Binder.getCallingUid());
        zzr(zzc, zzcawVar);
        if (((Boolean) zzbkm.zzj.zze()).booleanValue()) {
            zzc.zzc(new Runnable() { // from class: com.google.android.gms.internal.ads.zzedi
                @Override // java.lang.Runnable
                public final void run() {
                    zzchd.zza(zzedv.this.zzd.zza(), "persistFlags");
                }
            }, this.zzc);
        } else {
            zzc.zzc(new Runnable() { // from class: com.google.android.gms.internal.ads.zzedi
                @Override // java.lang.Runnable
                public final void run() {
                    zzchd.zza(zzedv.this.zzd.zza(), "persistFlags");
                }
            }, this.zzb);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzcas
    public final void zzh(String str, zzcaw zzcawVar) {
        zzr(zzi(str), zzcawVar);
    }

    public final zzfyx zzi(String str) {
        zzeds zzl;
        if (!((Boolean) zzbku.zza.zze()).booleanValue()) {
            return zzfyo.zzh(new Exception("Split request is disabled."));
        }
        zzedq zzedqVar = new zzedq(this);
        if (((Boolean) zzbku.zzd.zze()).booleanValue()) {
            zzl = zzm(str);
        } else {
            zzl = zzl(str);
        }
        if (zzl == null) {
            return zzfyo.zzh(new Exception("URL to be removed not found for cache key: ".concat(String.valueOf(str))));
        }
        return zzfyo.zzi(zzedqVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ InputStream zzj(zzfyx zzfyxVar, zzfyx zzfyxVar2, zzcba zzcbaVar, zzfir zzfirVar) throws Exception {
        String zzc = ((zzcbd) zzfyxVar.get()).zzc();
        String str = zzcbaVar.zzh;
        zzp(new zzeds((zzcbd) zzfyxVar.get(), (JSONObject) zzfyxVar2.get(), str, zzc, zzfirVar));
        return new ByteArrayInputStream(zzc.getBytes(zzfrs.zzc));
    }
}
