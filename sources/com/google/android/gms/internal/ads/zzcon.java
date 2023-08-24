package com.google.android.gms.internal.ads;

import android.content.Context;
import java.lang.ref.WeakReference;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzcon {
    private final zzcgt zza;
    private final Context zzb;
    private final WeakReference zzc;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzcon(zzcol zzcolVar, zzcom zzcomVar) {
        zzcgt zzcgtVar;
        Context context;
        WeakReference weakReference;
        zzcgtVar = zzcolVar.zza;
        this.zza = zzcgtVar;
        context = zzcolVar.zzb;
        this.zzb = context;
        weakReference = zzcolVar.zzc;
        this.zzc = weakReference;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Context zza() {
        return this.zzb;
    }

    public final zzapb zzb() {
        return new zzapb(new com.google.android.gms.ads.internal.zzi(this.zzb, this.zza));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final zzblh zzc() {
        return new zzblh(this.zzb);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final zzcgt zzd() {
        return this.zza;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final String zze() {
        return com.google.android.gms.ads.internal.zzt.zzq().zzc(this.zzb, this.zza.zza);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final WeakReference zzf() {
        return this.zzc;
    }
}
