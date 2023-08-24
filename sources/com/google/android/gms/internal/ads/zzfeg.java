package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.IBinder;
import android.os.RemoteException;
import com.google.android.gms.dynamite.descriptors.com.google.android.gms.ads.dynamite.ModuleDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzfeg {
    private static zzfeg zza;
    private final Context zzb;
    private final com.google.android.gms.ads.internal.client.zzcj zzc;
    private final AtomicReference zzd = new AtomicReference();

    zzfeg(Context context, com.google.android.gms.ads.internal.client.zzcj zzcjVar) {
        this.zzb = context;
        this.zzc = zzcjVar;
    }

    static com.google.android.gms.ads.internal.client.zzcj zza(Context context) {
        try {
            return com.google.android.gms.ads.internal.client.zzci.asInterface((IBinder) context.getClassLoader().loadClass("com.google.android.gms.ads.internal.client.LiteSdkInfo").getConstructor(Context.class).newInstance(context));
        } catch (ClassCastException | ClassNotFoundException | IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e) {
            com.google.android.gms.ads.internal.util.zze.zzh("Failed to retrieve lite SDK info.", e);
            return null;
        }
    }

    public static zzfeg zzd(Context context) {
        synchronized (zzfeg.class) {
            zzfeg zzfegVar = zza;
            if (zzfegVar != null) {
                return zzfegVar;
            }
            Context applicationContext = context.getApplicationContext();
            long longValue = ((Long) zzbkq.zzb.zze()).longValue();
            com.google.android.gms.ads.internal.client.zzcj zzcjVar = null;
            if (longValue > 0 && longValue <= 222508000) {
                zzcjVar = zza(applicationContext);
            }
            zzfeg zzfegVar2 = new zzfeg(applicationContext, zzcjVar);
            zza = zzfegVar2;
            return zzfegVar2;
        }
    }

    public final zzbvf zzb() {
        return (zzbvf) this.zzd.get();
    }

    public final zzcgt zzc(int r4, boolean z, int r6) {
        com.google.android.gms.ads.internal.zzt.zzq();
        boolean zzA = com.google.android.gms.ads.internal.util.zzs.zzA(this.zzb);
        zzcgt zzcgtVar = new zzcgt(ModuleDescriptor.MODULE_VERSION, r6, true, zzA);
        if (((Boolean) zzbkq.zzc.zze()).booleanValue()) {
            com.google.android.gms.ads.internal.client.zzcj zzcjVar = this.zzc;
            com.google.android.gms.ads.internal.client.zzeh zzehVar = null;
            if (zzcjVar != null) {
                try {
                    zzehVar = zzcjVar.getLiteSdkVersion();
                } catch (RemoteException unused) {
                }
            }
            return zzehVar == null ? zzcgtVar : new zzcgt(ModuleDescriptor.MODULE_VERSION, zzehVar.zza(), true, zzA);
        }
        return zzcgtVar;
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x001e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void zze(com.google.android.gms.internal.ads.zzbvf r4) {
        /*
            r3 = this;
            com.google.android.gms.internal.ads.zzbka r0 = com.google.android.gms.internal.ads.zzbkq.zza
            java.lang.Object r0 = r0.zze()
            java.lang.Boolean r0 = (java.lang.Boolean) r0
            boolean r0 = r0.booleanValue()
            r1 = 0
            if (r0 == 0) goto L23
            com.google.android.gms.ads.internal.client.zzcj r0 = r3.zzc
            if (r0 != 0) goto L15
        L13:
            r0 = r1
            goto L19
        L15:
            com.google.android.gms.internal.ads.zzbvf r0 = r0.getAdapterCreator()     // Catch: android.os.RemoteException -> L13
        L19:
            java.util.concurrent.atomic.AtomicReference r2 = r3.zzd
            if (r0 != 0) goto L1e
            goto L1f
        L1e:
            r4 = r0
        L1f:
            com.google.android.gms.internal.ads.zzfef.zza(r2, r1, r4)
            return
        L23:
            java.util.concurrent.atomic.AtomicReference r0 = r3.zzd
            com.google.android.gms.internal.ads.zzfef.zza(r0, r1, r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzfeg.zze(com.google.android.gms.internal.ads.zzbvf):void");
    }
}
