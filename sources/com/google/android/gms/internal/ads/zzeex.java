package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzeex implements zzdem, zzddb {
    private static final Object zza = new Object();
    private static int zzb;
    private final com.google.android.gms.ads.internal.util.zzg zzc;
    private final zzefh zzd;

    public zzeex(zzefh zzefhVar, com.google.android.gms.ads.internal.util.zzg zzgVar) {
        this.zzd = zzefhVar;
        this.zzc = zzgVar;
    }

    private final void zzb(boolean z) {
        int r1;
        int intValue;
        if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzfj)).booleanValue() && !this.zzc.zzP()) {
            Object obj = zza;
            synchronized (obj) {
                r1 = zzb;
                intValue = ((Integer) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzfk)).intValue();
            }
            if (r1 >= intValue) {
                return;
            }
            this.zzd.zzd(z);
            synchronized (obj) {
                zzb++;
            }
        }
    }

    @Override // com.google.android.gms.internal.ads.zzddb
    public final void zza(com.google.android.gms.ads.internal.client.zze zzeVar) {
        zzb(false);
    }

    @Override // com.google.android.gms.internal.ads.zzdem
    public final void zzn() {
        zzb(true);
    }
}
