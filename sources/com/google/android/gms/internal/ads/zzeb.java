package com.google.android.gms.internal.ads;

import android.content.Context;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Looper;
import com.reactnativecommunity.netinfo.BroadcastReceiverConnectivityReceiver;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzeb {
    private static zzeb zza;
    private final Handler zzb = new Handler(Looper.getMainLooper());
    private final CopyOnWriteArrayList zzc = new CopyOnWriteArrayList();
    private final Object zzd = new Object();
    private int zze = 0;

    private zzeb(Context context) {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(BroadcastReceiverConnectivityReceiver.CONNECTIVITY_ACTION);
        zzel.zzA(context, new zzea(this, null), intentFilter);
    }

    public static synchronized zzeb zzb(Context context) {
        zzeb zzebVar;
        synchronized (zzeb.class) {
            if (zza == null) {
                zza = new zzeb(context);
            }
            zzebVar = zza;
        }
        return zzebVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zzc(zzeb zzebVar, int r4) {
        synchronized (zzebVar.zzd) {
            if (zzebVar.zze == r4) {
                return;
            }
            zzebVar.zze = r4;
            Iterator it = zzebVar.zzc.iterator();
            while (it.hasNext()) {
                WeakReference weakReference = (WeakReference) it.next();
                zzwg zzwgVar = (zzwg) weakReference.get();
                if (zzwgVar != null) {
                    zzwgVar.zza.zzk(r4);
                } else {
                    zzebVar.zzc.remove(weakReference);
                }
            }
        }
    }

    public final int zza() {
        int r1;
        synchronized (this.zzd) {
            r1 = this.zze;
        }
        return r1;
    }

    public final void zzd(final zzwg zzwgVar) {
        Iterator it = this.zzc.iterator();
        while (it.hasNext()) {
            WeakReference weakReference = (WeakReference) it.next();
            if (weakReference.get() == null) {
                this.zzc.remove(weakReference);
            }
        }
        this.zzc.add(new WeakReference(zzwgVar));
        this.zzb.post(new Runnable(zzwgVar, null) { // from class: com.google.android.gms.internal.ads.zzdx
            public final /* synthetic */ zzwg zzb;

            @Override // java.lang.Runnable
            public final void run() {
                zzeb zzebVar = zzeb.this;
                zzwg zzwgVar2 = this.zzb;
                zzwgVar2.zza.zzk(zzebVar.zza());
            }
        });
    }
}
