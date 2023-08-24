package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzezv {
    public static void zza(AtomicReference atomicReference, zzezu zzezuVar) {
        Object obj = atomicReference.get();
        if (obj == null) {
            return;
        }
        try {
            zzezuVar.zza(obj);
        } catch (RemoteException e) {
            com.google.android.gms.ads.internal.util.zze.zzl("#007 Could not call remote method.", e);
        } catch (NullPointerException e2) {
            com.google.android.gms.ads.internal.util.zze.zzk("NullPointerException occurs when invoking a method from a delegating listener.", e2);
        }
    }
}
