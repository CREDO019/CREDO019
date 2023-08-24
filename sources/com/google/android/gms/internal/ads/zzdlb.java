package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.VideoController;
import java.util.Set;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzdlb extends zzdih {
    private boolean zzb;

    /* JADX INFO: Access modifiers changed from: protected */
    public zzdlb(Set set) {
        super(set);
    }

    public final void zza() {
        zzo(new zzdig() { // from class: com.google.android.gms.internal.ads.zzdkx
            @Override // com.google.android.gms.internal.ads.zzdig
            public final void zza(Object obj) {
                ((VideoController.VideoLifecycleCallbacks) obj).onVideoEnd();
            }
        });
    }

    public final void zzb() {
        zzo(new zzdig() { // from class: com.google.android.gms.internal.ads.zzdkz
            @Override // com.google.android.gms.internal.ads.zzdig
            public final void zza(Object obj) {
                ((VideoController.VideoLifecycleCallbacks) obj).onVideoPause();
            }
        });
    }

    public final synchronized void zzc() {
        if (!this.zzb) {
            zzo(zzdky.zza);
            this.zzb = true;
        }
        zzo(new zzdig() { // from class: com.google.android.gms.internal.ads.zzdla
            @Override // com.google.android.gms.internal.ads.zzdig
            public final void zza(Object obj) {
                ((VideoController.VideoLifecycleCallbacks) obj).onVideoPlay();
            }
        });
    }

    public final synchronized void zzd() {
        zzo(zzdky.zza);
        this.zzb = true;
    }
}
