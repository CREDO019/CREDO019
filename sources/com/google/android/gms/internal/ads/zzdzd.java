package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.dynamic.ObjectWrapper;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzdzd implements zzdyn {
    private final long zza;
    private final String zzb;
    private final zzdys zzc;
    private final zzfcj zzd;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzdzd(long j, Context context, zzdys zzdysVar, zzcok zzcokVar, String str) {
        this.zza = j;
        this.zzb = str;
        this.zzc = zzdysVar;
        zzfcl zzu = zzcokVar.zzu();
        zzu.zzb(context);
        zzu.zza(str);
        this.zzd = zzu.zzc().zza();
    }

    @Override // com.google.android.gms.internal.ads.zzdyn
    public final void zza() {
    }

    @Override // com.google.android.gms.internal.ads.zzdyn
    public final void zzb(com.google.android.gms.ads.internal.client.zzl zzlVar) {
        try {
            this.zzd.zzf(zzlVar, new zzdzb(this));
        } catch (RemoteException e) {
            com.google.android.gms.ads.internal.util.zze.zzl("#007 Could not call remote method.", e);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzdyn
    public final void zzc() {
        try {
            this.zzd.zzk(new zzdzc(this));
            this.zzd.zzm(ObjectWrapper.wrap(null));
        } catch (RemoteException e) {
            com.google.android.gms.ads.internal.util.zze.zzl("#007 Could not call remote method.", e);
        }
    }
}
