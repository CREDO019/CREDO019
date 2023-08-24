package com.google.android.gms.internal.ads;

import java.lang.ref.WeakReference;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzdfm implements Runnable {
    private final WeakReference zza;

    @Override // java.lang.Runnable
    public final void run() {
        zzdfn zzdfnVar = (zzdfn) this.zza.get();
        if (zzdfnVar != null) {
            zzdfnVar.zzo(new zzdig() { // from class: com.google.android.gms.internal.ads.zzdfk
                @Override // com.google.android.gms.internal.ads.zzdig
                public final void zza(Object obj) {
                    ((zzdfo) obj).zza();
                }
            });
        }
    }
}
