package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Binder;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzbrk implements zzajt {
    private volatile zzbqx zza;
    private final Context zzb;

    public zzbrk(Context context) {
        this.zzb = context;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* bridge */ /* synthetic */ void zzc(zzbrk zzbrkVar) {
        if (zzbrkVar.zza == null) {
            return;
        }
        zzbrkVar.zza.disconnect();
        Binder.flushPendingCommands();
    }

    @Override // com.google.android.gms.internal.ads.zzajt
    public final zzajw zza(zzaka zzakaVar) throws zzakj {
        Parcelable.Creator<zzbqy> creator = zzbqy.CREATOR;
        Map zzl = zzakaVar.zzl();
        int size = zzl.size();
        String[] strArr = new String[size];
        String[] strArr2 = new String[size];
        int r5 = 0;
        int r6 = 0;
        for (Map.Entry entry : zzl.entrySet()) {
            strArr[r6] = (String) entry.getKey();
            strArr2[r6] = (String) entry.getValue();
            r6++;
        }
        zzbqy zzbqyVar = new zzbqy(zzakaVar.zzk(), strArr, strArr2);
        long elapsedRealtime = com.google.android.gms.ads.internal.zzt.zzB().elapsedRealtime();
        try {
            zzchf zzchfVar = new zzchf();
            this.zza = new zzbqx(this.zzb, com.google.android.gms.ads.internal.zzt.zzu().zzb(), new zzbri(this, zzchfVar), new zzbrj(this, zzchfVar));
            this.zza.checkAvailabilityAndConnect();
            zzfyx zzo = zzfyo.zzo(zzfyo.zzn(zzchfVar, new zzbrg(this, zzbqyVar), zzcha.zza), ((Integer) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzdB)).intValue(), TimeUnit.MILLISECONDS, zzcha.zzd);
            zzo.zzc(new zzbrh(this), zzcha.zza);
            ParcelFileDescriptor parcelFileDescriptor = (ParcelFileDescriptor) zzo.get();
            long elapsedRealtime2 = com.google.android.gms.ads.internal.zzt.zzB().elapsedRealtime();
            com.google.android.gms.ads.internal.util.zze.zza("Http assets remote cache took " + (elapsedRealtime2 - elapsedRealtime) + "ms");
            zzbra zzbraVar = (zzbra) new zzcay(parcelFileDescriptor).zza(zzbra.CREATOR);
            if (zzbraVar == null) {
                return null;
            }
            if (zzbraVar.zza) {
                throw new zzakj(zzbraVar.zzb);
            }
            if (zzbraVar.zze.length != zzbraVar.zzf.length) {
                return null;
            }
            HashMap hashMap = new HashMap();
            while (true) {
                String[] strArr3 = zzbraVar.zze;
                if (r5 < strArr3.length) {
                    hashMap.put(strArr3[r5], zzbraVar.zzf[r5]);
                    r5++;
                } else {
                    return new zzajw(zzbraVar.zzc, zzbraVar.zzd, hashMap, zzbraVar.zzg, zzbraVar.zzh);
                }
            }
        } catch (InterruptedException | ExecutionException unused) {
            long elapsedRealtime3 = com.google.android.gms.ads.internal.zzt.zzB().elapsedRealtime();
            com.google.android.gms.ads.internal.util.zze.zza("Http assets remote cache took " + (elapsedRealtime3 - elapsedRealtime) + "ms");
            return null;
        } catch (Throwable th) {
            long elapsedRealtime4 = com.google.android.gms.ads.internal.zzt.zzB().elapsedRealtime();
            com.google.android.gms.ads.internal.util.zze.zza("Http assets remote cache took " + (elapsedRealtime4 - elapsedRealtime) + "ms");
            throw th;
        }
    }
}
